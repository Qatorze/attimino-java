package com.qatorze.attimino.restcontroller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qatorze.attimino.dtos.LoginRequestDTO;
import com.qatorze.attimino.dtos.RegisterRequestDTO;
import com.qatorze.attimino.dtos.UserResponseDTO;
import com.qatorze.attimino.services.AuthService;
import com.qatorze.attimino.services.JwtService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Auth", description = "Endpoint pour la gestion de l'authentification")
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthRestController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        UserResponseDTO userResponse = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        
        // Genère le token JWT
        String token = jwtService.generateToken(userResponse);
        
        // parametre le token comme cookie
        setTokenInCookie(response, token);

        // Renvoie le use avec le token inclus (optionnel)
        userResponse.setToken(token);
        return ResponseEntity.ok(userResponse);
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO, HttpServletResponse response) {
    	UserResponseDTO newUserDTO = authService.register(registerRequestDTO);
    	
    	 // Genera il token JWT
        String token = jwtService.generateToken(newUserDTO);
        
        // Imposta il token come cookie
        setTokenInCookie(response, token);

        // Restituisce l'utente con il token incluso (opzionale)
        newUserDTO.setToken(token);
        return ResponseEntity.ok(newUserDTO);
    }
    
    private void setTokenInCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true); // Protège des attaques XSS empèchant au javascript d'accéder au cookie
       // cookie.setSecure(true); // Seulement HTTPS à décommenter
        cookie.setPath("/"); //Pour le rendre disponible dans toutes l'application
        cookie.setMaxAge(24 * 60 * 60); // Valide pour 24 ore
        response.addCookie(cookie);
    }
}