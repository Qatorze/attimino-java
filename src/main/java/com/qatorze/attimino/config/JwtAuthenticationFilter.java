package com.qatorze.attimino.config;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qatorze.attimino.dtos.RegisterRequestDTO;
import com.qatorze.attimino.dtos.UserResponseDTO;
import com.qatorze.attimino.services.AuthService;
import com.qatorze.attimino.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService; // Iniezione del servizio per la gestione del JWT
    private final AuthService authService; // Service per gestire il login e la registrazione
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.authService = authService;
        setFilterProcessesUrl("/login"); // Specifica l'endpoint di login
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // Verifica se la richiesta è di login o registrazione
        if (request.getRequestURI().contains("/register")) {
            return handleRegister(request, response);
        }
        return handleLogin(request, response);
    }
    
    private Authentication handleLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private Authentication handleRegister(HttpServletRequest request, HttpServletResponse response) {
        try {     
            String surname = request.getParameter("surname");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            RegisterRequestDTO registerRequest = new RegisterRequestDTO(surname, name, email, password);
            // Invoca il servizio di registrazione
            authService.register(registerRequest); // Registrazione dell'utente

            return null; // Non serve autenticare dopo la registrazione, perché restituiremo il token direttamente
        } catch (Exception e) {
            throw new RuntimeException("Registration failed");
        }
    }
    
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
    	// Risposta in caso di fallimento dell'autenticazione
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // errore 401
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Invalid email or password\"}");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        // Ottieni i dettagli dell'utente autenticato
        UserResponseDTO user = (UserResponseDTO) authResult.getPrincipal();

        // Genera il token JWT usando JwtService
        String token = jwtService.generateToken(user);

        // Prepara la risposta JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Restituisci il token JWT nella risposta
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);

        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }
}