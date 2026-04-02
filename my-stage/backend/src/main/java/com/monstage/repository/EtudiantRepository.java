package com.monstage.repository;

import com.monstage.modele.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    
    Optional<Etudiant> findByNumeroEtudiant(String numeroEtudiant);
    
    Optional<Etudiant> findByUtilisateurId(Long utilisateurId);
    
    List<Etudiant> findByFiliere(String filiere);
    
    List<Etudiant> findByNiveau(String niveau);
    
    List<Etudiant> findByAnneeScolaire(String anneeScolaire);
    
    boolean existsByNumeroEtudiant(String numeroEtudiant);
}