package com.qatorze.attimino.dtos;

/* Cette classe me servira pour renvoyer que les données necessaires au client. Elle n'a pas l'attribut
 * "String password" car il s'agit d'une données sensible.*/
public class UserResponseDTO {
	
    private Long id;
    private String surname;
    private String name;
    private String role;
    private String email;
    private String imagePath;
    
    public UserResponseDTO() {
		super();
	}

	public UserResponseDTO(Long id, String surname, String name, String role, String email, String imagePath) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.role = role;
        this.email = email;
        this.imagePath = imagePath;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setNome(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
      
}
