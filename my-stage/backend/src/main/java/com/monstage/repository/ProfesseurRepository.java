package com.monstage.repository;

import com.monstage.modele.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    Optional<Professeur> findByMatricule(String matricule);
    Optional<Professeur> findByUtilisateurId(Long utilisateurId);
    List<Professeur> findByDepartement(String departement);
    List<Professeur> findBySpecialite(String specialite);
    List<Professeur> findByEstJuryTrue();
    List<Professeur> findByEstLaboratoireTrue();
}