package com.monstage.repository;

import com.monstage.modele.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
        
    Optional<Enseignant> findByUtilisateurId(Long utilisateurId);
    
    List<Enseignant> findBySpecialite(String specialite);
    
    List<Enseignant> findByNomContaining(String nom);
    
    List<Enseignant> findByPrenomContaining(String prenom);
}