package com.monstage.repository;

import com.monstage.modele.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByNumeroEtudiant(String numeroEtudiant);
    boolean existsByNumeroEtudiant(String numeroEtudiant);
}