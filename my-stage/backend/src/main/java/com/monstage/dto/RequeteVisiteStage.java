package com.monstage.dto;

/**
 * DTO pour créer ou mettre à jour une VisiteStage.
 */
public class RequeteVisiteStage {

    /** ID du stage visité */
    private Long stageId;

    /** Date de la visite (format ISO : "2024-11-15") */
    private String dateVisite;

    /** Le tuteur était-il présent ? */
    private Boolean present;

    /** Compte-rendu de la visite */
    private String compteRendu;

    /** Nom du tuteur rencontré */
    private String nomTuteur;

    // ---- Getters & Setters ----

    public Long getStageId() { return stageId; }
    public void setStageId(Long stageId) { this.stageId = stageId; }

    public String getDateVisite() { return dateVisite; }
    public void setDateVisite(String dateVisite) { this.dateVisite = dateVisite; }

    public Boolean getPresent() { return present; }
    public void setPresent(Boolean present) { this.present = present; }

    public String getCompteRendu() { return compteRendu; }
    public void setCompteRendu(String compteRendu) { this.compteRendu = compteRendu; }

    public String getNomTuteur() { return nomTuteur; }
    public void setNomTuteur(String nomTuteur) { this.nomTuteur = nomTuteur; }
}