package com.qatorze.attimino.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qatorze.attimino.dtos.LoginRequestDTO;
import com.qatorze.attimino.dtos.RegisterRequestDTO;
import com.qatorze.attimino.dtos.UserResponseDTO;
import com.qatorze.attimino.services.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "Endpoint pour la gestion de l'authentification")
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UserResponseDTO userResponse = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        return ResponseEntity.ok(userResponse);
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
    	UserResponseDTO nuovoClienteDTO = authService.register(registerRequestDTO);
        return ResponseEntity.ok(nuovoClienteDTO);
    }
}