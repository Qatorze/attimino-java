package com.qatorze.attimino.repositories;

import com.qatorze.attimino.models.Image;
import com.qatorze.attimino.enums.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface Repository pour l'accès aux données des images.
 * Permet de rechercher les images selon l'ID de l'entité et son type.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    /**
     * Trouve les images associées à une entité donnée (par ID et type d'entité).
     * @param entityId l'ID de l'entité (ex: ID d'une ville)
     * @param entityType le type d'entité (ex: CITY)
     * @return une liste d'images associées à l'entité
     */
    List<Image> findByEntityIdAndEntityType(String entityId, ImageType entityType);
}
