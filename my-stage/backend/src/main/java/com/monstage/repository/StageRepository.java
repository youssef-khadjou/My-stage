package com.monstage.repository;

import com.monstage.modele.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByEntrepriseId(Long entrepriseId);
    List<Stage> findByStatut(Stage.Statut statut);
}