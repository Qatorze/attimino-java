package com.qatorze.attimino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qatorze.attimino.models.City;

/**
 * Repository pour accéder aux données des villes dans la base de données.
 * Cette interface étend JpaRepository, ce qui permet d'utiliser les fonctionnalités
 * de Spring Data JPA pour gérer les entités "City".
 */
@Repository
public interface CityRepository extends JpaRepository<City, String> {

    // Aucune méthode personnalisée n'est ajoutée ici pour l'instant.
    // Si des requêtes spécifiques sont nécessaires pour des opérations particulières, 
    // elles peuvent être ajoutées dans cette interface.

}
