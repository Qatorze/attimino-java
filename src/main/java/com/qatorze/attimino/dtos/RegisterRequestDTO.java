package com.qatorze.attimino.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Objet utilisé pour ne recevoir que certaines données de la part du client lors de la registration
public class RegisterRequestDTO {

	@NotBlank(message = "Il cognome non può essere vuoto")
    @Size(max = 20, message = "Il cognome non può essere più lungo di 20 caratteri")
    private String surname;

    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(max = 20, message = "Il nome non può essere più lungo di 20 caratteri")
    private String name;

    @NotBlank(message = "L'email non può essere vuota")
    @Email(message = "L'email deve essere valida")
    @Size(max = 30, message = "L'email non può essere più lunga di 30 caratteri")
    private String email;

    @NotBlank(message = "La password non può essere vuota")
    @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    private String password;

    public RegisterRequestDTO() {}

	public RegisterRequestDTO(
			@NotBlank(message = "Il cognome non può essere vuoto") @Size(max = 20, message = "Il cognome non può essere più lungo di 20 caratteri") String surname,
			@NotBlank(message = "Il nome non può essere vuoto") @Size(max = 20, message = "Il nome non può essere più lungo di 20 caratteri") String name,
			@NotBlank(message = "L'email non può essere vuota") @Email(message = "L'email deve essere valida") @Size(max = 30, message = "L'email non può essere più lunga di 30 caratteri") String email,
			@NotBlank(message = "La password non può essere vuota") @Size(min = 8, message = "La password deve avere almeno 8 caratteri") String password) {
		super();
		this.surname = surname;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
