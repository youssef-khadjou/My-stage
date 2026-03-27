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
    
    // Constructeurs
    public RequeteStage() {}
    
    public RequeteStage(String titre, String description, Integer duree, String lieu, 
                        LocalDate dateDebut, LocalDate dateFin, Integer nbPlaces, 
                        String competencesRequises, BigDecimal remuneration) {
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbPlaces = nbPlaces;
        this.competencesRequises = competencesRequises;
        this.remuneration = remuneration;
    }
    
    // Getters et Setters
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getDuree() {
        return duree;
    }
    
    public void setDuree(Integer duree) {
        this.duree = duree;
    }
    
    public String getLieu() {
        return lieu;
    }
    
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public LocalDate getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    
    public Integer getNbPlaces() {
        return nbPlaces;
    }
    
    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
    
    public String getCompetencesRequises() {
        return competencesRequises;
    }
    
    public void setCompetencesRequises(String competencesRequises) {
        this.competencesRequises = competencesRequises;
    }
    
    public BigDecimal getRemuneration() {
        return remuneration;
    }
    
    public void setRemuneration(BigDecimal remuneration) {
        this.remuneration = remuneration;
    }
}