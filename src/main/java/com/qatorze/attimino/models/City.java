package com.qatorze.attimino.models;

import com.qatorze.attimino.enums.ImageType;
import com.qatorze.attimino.repositories.ImageRepository;
import jakarta.persistence.*;
import java.util.List;

/**
 * Représente une ville dans le système.
 * Cette entité est mappée à la table "cities" dans la base de données.
 */
@Entity
@Table(name = "cities")
public class City {

    // L'identifiant unique de la ville
    @Id
    @Column(name = "id", length = 4, nullable = false)
    private String id;

    // Le nom de la ville
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    // La description de la ville
    @Column(name = "description", length = 255, nullable = false)
    private String description;

    // Constructeur sans paramètres
    public City() {}

    // Constructeur avec l'ID de la ville
    public City(String id) {
        this.id = id;
    }

    // Constructeur avec le nom et la description
    public City(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters et setters pour chaque attribut

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode pour récupérer les images associées à cette ville depuis le repository.
     * Cette méthode ne persiste pas l'image dans la base de données, elle sert juste à la récupérer.
     * @param imageRepository le repository des images
     * @return une liste d'images associées à la ville
     */
    @Transient
    public List<Image> getImages(ImageRepository imageRepository) {
        return imageRepository.findByEntityIdAndEntityType(this.id, ImageType.CITY);
    }

    /**
     * Représentation textuelle de la ville pour les logs.
     * @return une chaîne de caractères contenant toutes les informations de la ville.
     */
    @Override
    public String toString() {
        return "City{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
