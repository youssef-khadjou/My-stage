package com.monstage.repository;

import com.monstage.modele.Stage;
import com.monstage.modele.Stage.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByEntrepriseId(Long entrepriseId);
    List<Stage> findByStatut(Statut statut);
    List<Stage> findByDateDebutAfter(LocalDate date);
    List<Stage> findByDateFinBefore(LocalDate date);
    List<Stage> findByLieu(String lieu);
    List<Stage> findByTitreContaining(String titre);
}