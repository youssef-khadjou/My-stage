package com.monstage.repository;

import com.monstage.modele.Encadreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EncadreurRepository extends JpaRepository<Encadreur, Long> {
    List<Encadreur> findByEntrepriseId(Long entrepriseId);
    List<Encadreur> findByStageId(Long stageId);
    List<Encadreur> findByEstEncadrantPrincipalTrue();
}