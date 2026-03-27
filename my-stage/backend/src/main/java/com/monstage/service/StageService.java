package com.monstage.service;

import com.monstage.dto.RequeteStage;
import com.monstage.modele.Entreprise;
import com.monstage.modele.Stage;
import com.monstage.repository.EntrepriseRepository;
import com.monstage.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    private final StageRepository stageRepository;
    private final EntrepriseRepository entrepriseRepository;

    // Constructeur manuel
    public StageService(StageRepository stageRepository, EntrepriseRepository entrepriseRepository) {
        this.stageRepository = stageRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    public List<Stage> tousLesStages() {
        return stageRepository.findAll();
    }

    public List<Stage> stagesValides() {
        return stageRepository.findByStatut(Stage.Statut.VALIDE);
    }

    public Stage trouverParId(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage non trouvé : " + id));
    }

    public List<Stage> stagesParEntreprise(Long entrepriseId) {
        return stageRepository.findByEntrepriseId(entrepriseId);
    }

    public Stage creer(RequeteStage requete, Long entrepriseId) {
        Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

        Stage stage = new Stage();
        stage.setTitre(requete.getTitre());
        stage.setDescription(requete.getDescription());
        stage.setDuree(requete.getDuree());
        stage.setLieu(requete.getLieu());
        stage.setDateDebut(requete.getDateDebut());
        stage.setDateFin(requete.getDateFin());
        stage.setNbPlaces(requete.getNbPlaces());
        stage.setCompetencesRequises(requete.getCompetencesRequises());
        stage.setRemuneration(requete.getRemuneration());
        stage.setStatut(Stage.Statut.EN_ATTENTE);
        stage.setEntreprise(entreprise);

        return stageRepository.save(stage);
    }

    public Stage modifier(Long id, RequeteStage requete) {
        Stage stage = trouverParId(id);
        stage.setTitre(requete.getTitre());
        stage.setDescription(requete.getDescription());
        stage.setDuree(requete.getDuree());
        stage.setLieu(requete.getLieu());
        stage.setDateDebut(requete.getDateDebut());
        stage.setDateFin(requete.getDateFin());
        stage.setNbPlaces(requete.getNbPlaces());
        stage.setCompetencesRequises(requete.getCompetencesRequises());
        stage.setRemuneration(requete.getRemuneration());
        return stageRepository.save(stage);
    }

    public Stage changerStatut(Long id, Stage.Statut statut) {
        Stage stage = trouverParId(id);
        stage.setStatut(statut);
        return stageRepository.save(stage);
    }

    public void supprimer(Long id) {
        stageRepository.deleteById(id);
    }
}
