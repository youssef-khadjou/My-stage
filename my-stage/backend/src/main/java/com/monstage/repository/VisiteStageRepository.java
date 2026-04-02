package com.monstage.repository;

import com.monstage.modele.VisiteStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository pour les opérations CRUD sur VisiteStage.
 */
@Repository
public interface VisiteStageRepository extends JpaRepository<VisiteStage, Long> {

    /** Récupère toutes les visites liées à un stage */
    List<VisiteStage> findByStageId(Long stageId);

    /** Récupère les visites d'un stage triées par date (la plus récente en premier) */
    List<VisiteStage> findByStageIdOrderByDateVisiteDesc(Long stageId);
}