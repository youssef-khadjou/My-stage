package com.monstage.repository;

import com.monstage.modele.CreneauSoutenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

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
}