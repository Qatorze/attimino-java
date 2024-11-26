package com.qatorze.attimino.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id // Pour marquer cet attribut comme clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "surname", length = 20, nullable = false)
	private String surname;
	
	@Column(name = "name", length = 20, nullable = false)
	private String name;
	
	@Column(name = "role", length = 20, nullable = true)
	private String role;
	
	@Column(name = "email", length = 30, nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", length = 60, nullable = false)
	private String password;

	@Column(name = "image_path", length = 255, nullable = true)
	private String imagePath;

	public User() {
		super();
	}

	public User(String surname, String name, String role, String email, String password, String imagePath) {
		super();
		this.surname = surname;
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
		this.imagePath = imagePath;
	}
	
	public User(Long id, String surname, String name, String role, String email, String password, String imagePath) {
		super();
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
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

	public void setName(String name) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", name=");
		builder.append(name);
		builder.append(", role=");
		builder.append(role);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", imagePath=");
		builder.append(imagePath);
		builder.append("]");
		return builder.toString();
	}
	
}
