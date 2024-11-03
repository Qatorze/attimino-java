package com.qatorze.attimino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qatorze.attimino.enums.ImageType;
import com.qatorze.attimino.models.Image;

//On ajoute ici si necessaire des fonctions spécialisées pour des query spécifiques.

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
	// 1 - Trouve toutes les images pour un model spécifique(City, Theater, Show)
    List<Image> findByEntityIdAndEntityType(String entityId, ImageType entityType);
    
}