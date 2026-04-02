package com.monstage.repository;

import com.monstage.modele.SemaineSoutenance;
import com.monstage.modele.SemaineSoutenance.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository pour les opérations CRUD sur SemaineSoutenance.
 */
@Repository
public interface SemaineSoutenanceRepository extends JpaRepository<SemaineSoutenance, Long> {

    /** Récupère toutes les semaines d'une année scolaire donnée */
    List<SemaineSoutenance> findByAnneeScolaire(String anneeScolaire);

    /** Récupère les semaines ouvertes (où on peut encore affecter des créneaux) */
    List<SemaineSoutenance> findByStatut(Statut statut);
}