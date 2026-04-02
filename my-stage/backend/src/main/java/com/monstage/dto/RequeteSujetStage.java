package com.monstage.dto;

/**
 * DTO pour la création ou modification d'un SujetStage.
 * Utilisé par le contrôleur pour recevoir les données du frontend.
 */
public class RequeteSujetStage {

    /** Titre du stage */
    private String intitule;

    /** Description des missions */
    private String description;

    /** Compétences requises */
    private String competencesRequises;

    /** Durée en semaines */
    private Integer dureeSemaines;

    /** Date de début souhaitée (format ISO : "2024-09-01") */
    private String dateDebut;

    /** Date de fin souhaitée */
    private String dateFin;

    /** Rémunération mensuelle (null si stage non rémunéré) */
    private Double remuneration;

    /** Secteur d'activité */
    private String secteurActivite;

    /** Lieu du stage */
    private String lieuTravail;

    // ---- Getters & Setters ----

    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCompetencesRequises() { return competencesRequises; }
    public void setCompetencesRequises(String competencesRequises) { this.competencesRequises = competencesRequises; }

    public Integer getDureeSemaines() { return dureeSemaines; }
    public void setDureeSemaines(Integer dureeSemaines) { this.dureeSemaines = dureeSemaines; }

    public String getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }

    public String getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }

    public Double getRemuneration() { return remuneration; }
    public void setRemuneration(Double remuneration) { this.remuneration = remuneration; }

    public String getSecteurActivite() { return secteurActivite; }
    public void setSecteurActivite(String secteurActivite) { this.secteurActivite = secteurActivite; }

    public String getLieuTravail() { return lieuTravail; }
    public void setLieuTravail(String lieuTravail) { this.lieuTravail = lieuTravail; }
}