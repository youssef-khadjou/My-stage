package com.monstage.repository;

import com.monstage.modele.Candidature;
import com.monstage.modele.Candidature.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByEtudiantId(Long etudiantId);
    List<Candidature> findByStageId(Long stageId);
    List<Candidature> findByStatut(Statut statut);
    List<Candidature> findByStageIdAndStatut(Long stageId, Statut statut);
    boolean existsByEtudiantIdAndStageId(Long etudiantId, Long stageId);
}