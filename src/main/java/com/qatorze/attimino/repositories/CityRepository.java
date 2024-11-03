package com.qatorze.attimino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.qatorze.attimino.models.City;
//On ajoute ici si necessaire des fonctions spécialisées pour des query spécifiques.

@Repository
public interface CityRepository extends JpaRepository<City, String> {
	
}
