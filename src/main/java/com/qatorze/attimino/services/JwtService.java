package com.qatorze.attimino.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.qatorze.attimino.dtos.UserResponseDTO;

@Service
public class JwtService {
	
	@Value("${jwt.secret.key}")
	private String SECRET_KEY;

	public String generateToken(UserResponseDTO user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
        		  .withSubject(user.getEmail()) // Puoi anche utilizzare "user.getEmail()" come subject se necessario
                  .withIssuedAt(new Date())
                  .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 ore
                  .withClaim("id", user.getId()) // Aggiungi tutti gli attributi come claim
                  .withClaim("surname", user.getSurname())
                  .withClaim("name", user.getName())
                  .withClaim("role", user.getRole())
                  .withClaim("email", user.getEmail())
                  .withClaim("imagePath", user.getImagePath())
                  .sign(algorithm);
    }

	public UserResponseDTO validateTokenAndGetUser(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        // Estrai i dati dal token JWT
        Long id = decodedJWT.getClaim("id").asLong();
        String surname = decodedJWT.getClaim("surname").asString();
        String name = decodedJWT.getClaim("name").asString();
        String role = decodedJWT.getClaim("role").asString();
        String email = decodedJWT.getClaim("email").asString();
        String imagePath = decodedJWT.getClaim("imagePath").asString();

        // Crea un oggetto UserResponseDTO con i dati estratti dal token
        UserResponseDTO user = new UserResponseDTO(id, surname, name, role, email, imagePath);
        return user;
    }
}
