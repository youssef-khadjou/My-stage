package com.monstage.repository;

import com.monstage.modele.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    
    Optional<Entreprise> findByNomEntreprise(String nom);
    
    Optional<Entreprise> findBySiret(String siret);
    
    Optional<Entreprise> findByUtilisateurId(Long utilisateurId);
    
    List<Entreprise> findBySecteurActivite(String secteur);
    
    List<Entreprise> findByVille(String ville);
    
    boolean existsByNomEntreprise(String nom);
    
    boolean existsBySiret(String siret);
}