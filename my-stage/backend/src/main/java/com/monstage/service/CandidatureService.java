package com.monstage.service;

import com.monstage.dto.RequeteCandidature;
import com.monstage.modele.Candidature;
import com.monstage.modele.Etudiant;
import com.monstage.modele.Stage;
import com.monstage.repository.CandidatureRepository;
import com.monstage.repository.EtudiantRepository;
import com.monstage.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatureService {

    private final CandidatureRepository candidatureRepository;
    private final EtudiantRepository etudiantRepository;
    private final StageRepository stageRepository;

    // Constructeur manuel
    public CandidatureService(CandidatureRepository candidatureRepository,
                              EtudiantRepository etudiantRepository,
                              StageRepository stageRepository) {
        this.candidatureRepository = candidatureRepository;
        this.etudiantRepository = etudiantRepository;
        this.stageRepository = stageRepository;
    }

    public Candidature postuler(RequeteCandidature requete, Long etudiantId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        Stage stage = stageRepository.findById(requete.getStageId())
                .orElseThrow(() -> new RuntimeException("Stage non trouvé"));

        if (candidatureRepository.existsByEtudiantIdAndStageId(etudiantId, requete.getStageId())) {
            throw new RuntimeException("Vous avez déjà postulé à ce stage");
        }

        Candidature candidature = new Candidature();
        candidature.setEtudiant(etudiant);
        candidature.setStage(stage);
        candidature.setLettreMotivation(requete.getLettreMotivation());
        candidature.setStatut(Candidature.Statut.EN_ATTENTE);

        return candidatureRepository.save(candidature);
    }

    public List<Candidature> candidaturesEtudiant(Long etudiantId) {
        return candidatureRepository.findByEtudiantId(etudiantId);
    }

    public List<Candidature> candidaturesStage(Long stageId) {
        return candidatureRepository.findByStageId(stageId);
    }

    public Candidature changerStatut(Long id, Candidature.Statut statut) {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));
        candidature.setStatut(statut);
        return candidatureRepository.save(candidature);
    }
}
