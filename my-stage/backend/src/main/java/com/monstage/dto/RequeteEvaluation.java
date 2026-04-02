package com.monstage.dto;

import java.time.LocalDate;

public class RequeteEvaluation {
    private String commentaire;
    private LocalDate dateEvaluation;
    private Integer note;
    private Integer noteSoutenance;
    private String texteStage;
    private String typeEvaluation;
    private Long idStage;
    private Long idEtudiant;

    public RequeteEvaluation() {}

    // Getters et Setters
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public LocalDate getDateEvaluation() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation = dateEvaluation; }

    public Integer getNote() { return note; }
    public void setNote(Integer note) { this.note = note; }

    public Integer getNoteSoutenance() { return noteSoutenance; }
    public void setNoteSoutenance(Integer noteSoutenance) { this.noteSoutenance = noteSoutenance; }

    public String getTexteStage() { return texteStage; }
    public void setTexteStage(String texteStage) { this.texteStage = texteStage; }

    public String getTypeEvaluation() { return typeEvaluation; }
    public void setTypeEvaluation(String typeEvaluation) { this.typeEvaluation = typeEvaluation; }

    public Long getIdStage() { return idStage; }
    public void setIdStage(Long idStage) { this.idStage = idStage; }

    public Long getIdEtudiant() { return idEtudiant; }
    public void setIdEtudiant(Long idEtudiant) { this.idEtudiant = idEtudiant; }
}