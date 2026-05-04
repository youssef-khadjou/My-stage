package com.monstage.repository;

import com.monstage.modele.Disponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {
    List<Disponibilite> findByUtilisateurIdOrderByDateAscHeureDebutAsc(Long utilisateurId);
}