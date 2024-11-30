package com.qatorze.attimino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.qatorze.attimino.services.AuthService;
import com.qatorze.attimino.services.JwtService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration // Indique à spring que cette classe contient des confugurations à gérer come "bean". Spring va créer et gérer les objets définient dans cette classe
@EnableWebSecurity //Autorise les fonctionnalités de sécurité de Spring Security permettant la configuration de la protection pour les resource web.
public class SecurityConfig {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtService jwtService; // Service per il JWT
	private final AuthService authService; // Aggiungi il servizio AuthService

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtService jwtService,@Lazy AuthService authService) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtService = jwtService;
        this.authService = authService; // Inizializza AuthService
    }
    
    @Bean // Rend l'objet disponible dans le contest de l'application, permettant aux autres composants de l'injecter ou' necessaire, par exemple dans les service d'authentification
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configurazione del filtro di sicurezza
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	
    	 // Creazione del filtro di autenticazione JWT personalizzato
         JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
                 authenticationManager(authenticationConfiguration),
                 jwtService, // Passaggio del servizio JWT al filtro
                 authService // Passa AuthService al filtro
         );
    	 
         // Impostazione del gestore degli errori per il filtro JWT
    	 jwtAuthenticationFilter.setAuthenticationFailureHandler((request, response, exception) -> {
        	 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             response.setContentType("application/json");
             response.getWriter().write("{\"error\": \"Login failed\"}");
         });
    	
        // Configurazione di sicurezza
        http
            .csrf(csrf -> csrf.disable()) // "disable car pas necessaire dans des application stateless, comme celle basée sur JWT car il n'existe pas de session coté server
            .authorizeHttpRequests((requests) -> requests
            		.requestMatchers("/api/auth/**").permitAll() // Endpoint public, comme login e register, accéssibles sans authentication, donc sans token.
            		.requestMatchers("/api/admin/**").hasRole("admin") // Questa riga specifica che solo gli utenti con il ruolo admin possono accedere agli endpoint che iniziano con /api/admin/.
            		.requestMatchers("/api/user/**").hasRole("user") // Questa riga specifica che solo gli utenti con il ruolo user possono accedere agli endpoint che iniziano con /api/user/.
            		.anyRequest().authenticated() // Tous les endpoints necessittent un token JWT valide pour l'access.
             )
            .exceptionHandling(exception -> exception
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint) // Gestione degli errori di autenticazione
             )
            .sessionManagement(management -> management
            		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // On applique une gestion des sessions "stateless" (aucun session entre deux requètes lato server)
            )	
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Aggiungi il filtro JWT prima del filtro predefinito

         return http.build(); 
         }

    // Bean per gestire l'AuthenticationManager
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}