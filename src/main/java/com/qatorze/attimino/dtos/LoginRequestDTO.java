package com.qatorze.attimino.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Objet utilisé pour ne recevoir que certaines données de la part du client lors du login
public class LoginRequestDTO {

	@NotBlank(message = "L'email non può essere vuota")
	@Email(message = "L'email deve essere valida")
	@Size(max = 30, message = "L'email non può essere più lunga di 30 caratteri")
    private String email;

	@NotBlank(message = "La password non può essere vuota")
    @Size(min = 8, message = "La password deve avere almeno 6 caratteri")
    private String password;

    public LoginRequestDTO() {}

	public LoginRequestDTO(
			@NotBlank(message = "L'email non può essere vuota") @Email(message = "L'email deve essere valida") @Size(max = 30, message = "L'email non può essere più lunga di 30 caratteri") String email,
			@NotBlank(message = "La password non può essere vuota") @Size(min = 8, message = "La password deve avere almeno 6 caratteri") String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
