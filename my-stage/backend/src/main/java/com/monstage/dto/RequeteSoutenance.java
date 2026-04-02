package com.monstage.dto;

import java.time.LocalDate;

public class RequeteSoutenance {
    private String rapportUrl;
    private Integer noteStage;
    private Integer noteSoutenance;
    private String commentaireJury;
    private LocalDate dateEvaluation;
    private Long idEtudiant;
    private Long idCreneau;

    public RequeteSoutenance() {}

    // Getters et Setters
    public String getRapportUrl() { return rapportUrl; }
    public void setRapportUrl(String rapportUrl) { this.rapportUrl = rapportUrl; }

    public Integer getNoteStage() { return noteStage; }
    public void setNoteStage(Integer noteStage) { this.noteStage = noteStage; }

    public Integer getNoteSoutenance() { return noteSoutenance; }
    public void setNoteSoutenance(Integer noteSoutenance) { this.noteSoutenance = noteSoutenance; }

    public String getCommentaireJury() { return commentaireJury; }
    public void setCommentaireJury(String commentaireJury) { this.commentaireJury = commentaireJury; }

    public LocalDate getDateEvaluation() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation = dateEvaluation; }

    public Long getIdEtudiant() { return idEtudiant; }
    public void setIdEtudiant(Long idEtudiant) { this.idEtudiant = idEtudiant; }

    public Long getIdCreneau() { return idCreneau; }
    public void setIdCreneau(Long idCreneau) { this.idCreneau = idCreneau; }
}