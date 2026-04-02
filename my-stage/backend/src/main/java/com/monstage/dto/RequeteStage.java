package com.monstage.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RequeteStage {
    private String titre;
    private String description;
    private Integer duree;
    private String lieu;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer nbPlaces;
    private String competencesRequises;
    private BigDecimal remuneration;
    private Long entrepriseId;

    public RequeteStage() {}

    // Getters
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public Integer getDuree() { return duree; }
    public String getLieu() { return lieu; }
    public LocalDate getDateDebut() { return dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public Integer getNbPlaces() { return nbPlaces; }
    public String getCompetencesRequises() { return competencesRequises; }
    public BigDecimal getRemuneration() { return remuneration; }
    public Long getEntrepriseId() { return entrepriseId; }

    // Setters
    public void setTitre(String titre) { this.titre = titre; }
    public void setDescription(String description) { this.description = description; }
    public void setDuree(Integer duree) { this.duree = duree; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public void setNbPlaces(Integer nbPlaces) { this.nbPlaces = nbPlaces; }
    public void setCompetencesRequises(String competencesRequises) { this.competencesRequises = competencesRequises; }
    public void setRemuneration(BigDecimal remuneration) { this.remuneration = remuneration; }
    public void setEntrepriseId(Long entrepriseId) { this.entrepriseId = entrepriseId; }
}