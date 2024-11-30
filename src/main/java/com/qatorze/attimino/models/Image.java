package com.qatorze.attimino.models;

import com.qatorze.attimino.enums.ImageType;
import jakarta.persistence.*;

/**
 * Représente une image associée à une entité comme une ville, un théâtre, un spectacle, etc.
 * Cette entité est mappée à la table "images" dans la base de données.
 */
@Entity
@Table(name = "images")
public class Image {

    // L'identifiant unique de l'image
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // L'URL de l'image stockée
    @Column(name = "url", length = 1200, nullable = false)
    private String url;

    // Indique si l'image est l'image principale associée à l'entité
    @Column(name = "is_main", nullable = false)
    private boolean isMain;

    // ID de l'entité associée à l'image (ex: ID de City, Theater, Show)
    @Column(name = "entity_id", nullable = false)
    private String entityId;

    // Le type de l'entité associée à l'image (City, Theater, Show)
    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private ImageType entityType;

    // Constructeur sans paramètres
    public Image() {}

    // Constructeur avec les informations de l'image
    public Image(String url, boolean isMain, String entityId, ImageType entityType) {
        this.url = url;
        this.isMain = isMain;
        this.entityId = entityId;
        this.entityType = entityType;
    }

    // Getters et setters pour chaque attribut

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public ImageType getEntityType() {
        return entityType;
    }

    public void setEntityType(ImageType entityType) {
        this.entityType = entityType;
    }

    /**
     * Représentation textuelle de l'image pour les logs.
     * @return une chaîne de caractères contenant toutes les informations de l'image.
     */
    @Override
    public String toString() {
        return "Image{" +
               "id=" + id +
               ", url='" + url + '\'' +
               ", isMain=" + isMain +
               ", entityId='" + entityId + '\'' +
               ", entityType=" + entityType +
               '}';
    }
}
