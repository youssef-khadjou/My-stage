package com.monstage.repository;

import com.monstage.modele.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByStageId(Long stageId);
    List<Evaluation> findByEtudiantId(Long etudiantId);
    List<Evaluation> findByTypeEvaluation(String type);
}