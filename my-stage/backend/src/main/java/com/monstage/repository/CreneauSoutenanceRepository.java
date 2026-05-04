package com.monstage.repository;

import com.monstage.modele.CreneauSoutenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository pour les opérations CRUD sur CreneauSoutenance.
 */
@Repository
public interface CreneauSoutenanceRepository extends JpaRepository<CreneauSoutenance, Long> {

    /** Récupère tous les créneaux d'une semaine de soutenance */
    List<CreneauSoutenance> findBySemaineSoutenanceId(Long semaineId);

    /** Récupère les créneaux disponibles d'une semaine (pour affecter une soutenance) */
    List<CreneauSoutenance> findBySemaineSoutenanceIdAndDisponibleTrue(Long semaineId);

    /** Récupère les créneaux d'une salle à une date précise (pour éviter les doublons) */
    List<CreneauSoutenance> findBySalleAndDateSoutenance(String salle, LocalDate date);

    List<CreneauSoutenance> findBySalleOrderByDateSoutenanceAscHeureDebutAsc(String salle);

    @Query("SELECT DISTINCT c.salle FROM CreneauSoutenance c WHERE c.salle IS NOT NULL")
    List<String> findDistinctSalles();
}