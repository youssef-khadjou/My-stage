package com.monstage.repository;

import com.monstage.modele.RendezVousSuivi;
import com.monstage.modele.RendezVousSuivi.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository pour les opérations CRUD sur RendezVousSuivi.
 */
@Repository
public interface RendezVousSuiviRepository extends JpaRepository<RendezVousSuivi, Long> {

    /** Récupère tous les rendez-vous liés à une candidature */
    List<RendezVousSuivi> findByCandidatureId(Long candidatureId);

    /** Récupère les rendez-vous d'une candidature selon leur statut */
    List<RendezVousSuivi> findByCandidatureIdAndStatut(Long candidatureId, Statut statut);
}