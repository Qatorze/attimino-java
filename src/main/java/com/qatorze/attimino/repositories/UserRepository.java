package com.qatorze.attimino.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qatorze.attimino.models.User;

//On ajoute ici si necessaire des fonctions spécialisées pour des query spécifiques.

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	// Metodo per contare tutti i clienti
    long count();
}
