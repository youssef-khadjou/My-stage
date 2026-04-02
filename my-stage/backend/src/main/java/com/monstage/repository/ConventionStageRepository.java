package com.monstage.repository;

import com.monstage.modele.ConventionStage;
import com.monstage.modele.ConventionStage.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository pour les opérations CRUD sur ConventionStage.
 */
@Repository
public interface ConventionStageRepository extends JpaRepository<ConventionStage, Long> {

    /** Trouve la convention liée à un stage précis */
    Optional<ConventionStage> findByStageId(Long stageId);

    /** Récupère toutes les conventions d'un étudiant */
    List<ConventionStage> findByEtudiantId(Long etudiantId);

    /** Récupère les conventions selon leur statut */
    List<ConventionStage> findByStatut(Statut statut);

    /** Trouve une convention par son numéro unique */
    Optional<ConventionStage> findByNumeroConvention(String numeroConvention);
}