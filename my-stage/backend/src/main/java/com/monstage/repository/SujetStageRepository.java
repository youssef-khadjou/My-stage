package com.monstage.repository;

import com.monstage.modele.SujetStage;
import com.monstage.modele.SujetStage.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository pour les opérations CRUD sur SujetStage.
 */
@Repository
public interface SujetStageRepository extends JpaRepository<SujetStage, Long> {

    /** Récupère tous les sujets proposés par une entreprise */
    List<SujetStage> findByEntrepriseId(Long entrepriseId);

    /** Récupère les sujets selon leur statut (ex: tous les sujets VALIDE) */
    List<SujetStage> findByStatut(Statut statut);

    /** Récupère les sujets validés d'une entreprise spécifique */
    List<SujetStage> findByEntrepriseIdAndStatut(Long entrepriseId, Statut statut);
}