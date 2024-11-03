package com.qatorze.attimino.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Objet utilisé pour ne recevoir que certaines données de la part du client lors de l'update car on ne veut pas donnéer la possibilité d'update n'import quel champ.
public class UserRequestDTO {

    private Long id; // ID dell'utente

    @NotBlank(message = "Il cognome non può essere vuoto")
    @Size(max = 20, message = "Il cognome non può essere più lungo di 20 caratteri")
    private String surname;

    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(max = 20, message = "Il nome non può essere più lungo di 20 caratteri")
    private String name;

    @NotBlank(message = "La password non può essere vuota")
    @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    private String password;

    private String imagePath; // Percorso dell'immagine

    public UserRequestDTO() {}

    public UserRequestDTO(Long id, String surname, String name, String password, String imagePath) {
        this.id = id;
        this.surname = surname;
        this.name = name;
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
    
}
