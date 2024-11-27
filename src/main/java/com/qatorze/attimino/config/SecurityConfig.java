package com.qatorze.attimino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration // Indique à spring que cette classe contient des confugurations à gérer come "bean". Spring va créer et gérer les objets définient dans cette classe
@EnableWebSecurity //Autorise les fonctionnalités de sécurité de Spring Security permettant la configuration de la protection pour les resource web.
public class SecurityConfig {

    @Bean // Rend l'objet disponible dans le contest de l'application, permettant aux autres composants de l'injecter ou' necessaire, par exemple dans les service d'authentification
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // "disable car pas necessaire dans des application stateless, comme celle basée sur JWT car il n'existe pas de session coté server
            .authorizeHttpRequests((requests) -> requests
            		.requestMatchers("/api/auth/**").permitAll() // Endpoint public, comme login e register, accéssibles sans authentication, donc sans token.
                 	.anyRequest().authenticated() // Tous les endpoints necessittent un token JWT valide pour l'access.
             )
            .sessionManagement(management -> management
            		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // On applique une gestion des sessions "stateless" (aucun session entre deux requètes lato server)

             return http.build(); 
         }
}