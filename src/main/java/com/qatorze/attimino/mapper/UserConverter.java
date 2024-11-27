package com.qatorze.attimino.mapper;

import org.springframework.stereotype.Component;
import com.qatorze.attimino.dtos.UserResponseDTO;
import com.qatorze.attimino.dtos.LoginRequestDTO;
import com.qatorze.attimino.dtos.RegisterRequestDTO;
import com.qatorze.attimino.dtos.UserRequestDTO;
import com.qatorze.attimino.models.User;
import com.qatorze.attimino.utils.EmailMaskingUtil;

@Component
public class UserConverter {
	
	/* Cette méthode convertit un objet User en UserResponseDTO, destiné à l’affichage au front-end.*/
    public UserResponseDTO convertUserToUserResponseDTO(User user) {
    	// maskingEmail permette di nascondere alcune lettere dell'email prima di rimandarla al front-end
    	String maskedEmail = EmailMaskingUtil.maskEmail(user.getEmail());
    	
    	return new UserResponseDTO(
            user.getId(),
            user.getSurname(),
            user.getName(), // Assicurati che qui ci sia il nome corretto
            user.getRole(),
            maskedEmail,
            user.getImagePath()
            
        );
    }

    /* Convertit un objet LoginRequestDTO en User. Cet objet ne contient que l'email et le mot de passe, ce qui est suffisant pour une tentative de connexion.
    Les autres champs de User comme le nom (name), le prénom (surname), le rôle (role) et l'image (imagePath) sont définis à null.*/
    public User convertLoginRequestDTO_ToUser(LoginRequestDTO loginRequestDTO) {
        return new User(
            null, // surname
            null, // name
            null, // role
            loginRequestDTO.getEmail(),
            loginRequestDTO.getPassword(),
            null  // imagePath
        );
    }

    /* Cette méthode convertit un RegisterRequestDTO en un objet User, principalement pour l'inscription d'un nouvel utilisateur.*/
    public User convertRegisterRequestDTO_ToUser(RegisterRequestDTO registerRequestDTO) {
        return new User(
            registerRequestDTO.getSurname(),
            registerRequestDTO.getName(),
            null, // Le role ici est prédéfit dans la methode "register" du AuthService
            registerRequestDTO.getEmail(),
            registerRequestDTO.getPassword(),
            null  // imagePath
        );
    }
    
    /* Cette méthode convertit un UserRequestDTO en un objet User, probablement pour mettre à jour les informations d’un utilisateur existant.*/
    public User convertUserRequestDTO_ToUser(UserRequestDTO userRequestDTO) {
        return new User(
            userRequestDTO.getId(), // Settiamo l'ID
            userRequestDTO.getSurname(),
            userRequestDTO.getName(),
            null, // Le role au max est géré par l'admin
            null, // l'email on ne veut pas donner la possibilité de la modifier / Pour le momment.
            userRequestDTO.getPassword(), // La password può essere aggiornata
            userRequestDTO.getImagePath() // Aggiorna le path de l'immage
        );
    }
    
}
