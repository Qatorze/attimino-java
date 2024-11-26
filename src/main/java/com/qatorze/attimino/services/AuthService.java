package com.qatorze.attimino.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qatorze.attimino.dtos.RegisterRequestDTO;
import com.qatorze.attimino.dtos.UserResponseDTO;
import com.qatorze.attimino.exceptions.UserEmailAlreadyInUse;
import com.qatorze.attimino.exceptions.InvalidCredentialsException;
import com.qatorze.attimino.mapper.UserConverter;
import com.qatorze.attimino.models.User;
import com.qatorze.attimino.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
		 
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserResponseDTO login(String loginEmail, String loginPassword) {
		
		Optional<User> optUser = userRepository.findByEmail(loginEmail);
		
		if (optUser.isEmpty()) {
			throw new InvalidCredentialsException(); 
		}
		
		User user = optUser.get();
		System.out.println(user);
		// Vérifit si la pwd enregistrée dans le database correspond à celle passé dans le formulaire de login
	    if (!passwordEncoder.matches(loginPassword, user.getPassword())) {
	        throw new InvalidCredentialsException();
	    }  
	    /* Convertit l'objet User retrouveé dans notre base de données grace à l'Email reçue par le LoginRequestDTO au niveau du AuthRestController 
	     * et donc retire la password afin de respecter la structure du UserResponseDTO. */
	    return userConverter.convertUserToUserResponseDTO(user);
	}
	
	
	@Transactional
	public UserResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        
		Optional<User> optUser = userRepository.findByEmail(registerRequestDTO.getEmail());
        if (optUser.isPresent()) {
            throw new UserEmailAlreadyInUse();
        }  
        
        /* Créait un nouveau User à partir des données reçues de la part du RegisterRequestDTO au niveau du AuthRestController */
        User newUser = userConverter.convertRegisterRequestDTO_ToUser(registerRequestDTO);
        // Cryptage du pwd avant de save dans le database.
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole("user"); // Imposta il ruolo predefinito a "user" car à ce stade On a le Model User et non plus le RegisterRequestDTO

        // Salva il nuovo utente nel repository
        User savedUser = userRepository.save(newUser);
        
        // Convertit l'objet User en UserResponseDTO pour la reponse qui ira au front-end evidemment dans la password.
        return userConverter.convertUserToUserResponseDTO(savedUser);  
    }
}
