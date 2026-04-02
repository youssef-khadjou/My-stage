package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation")
    private Long id;

    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    @Column(name = "date_evaluation")
    private LocalDate dateEvaluation;

    @Column(name = "note")
    private Integer note;

    @Column(name = "note_soutenance")
    private Integer noteSoutenance;

    @Column(name = "texte_stage", columnDefinition = "TEXT")
    private String texteStage;

    @Column(name = "type_evaluation")
    private String typeEvaluation;

    @OneToOne
    @JoinColumn(name = "id_stage")
    private Stage stage;

    @OneToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;

    public Evaluation() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public Stage getStage() { return stage; }
    public void setStage(Stage stage) { this.stage = stage; }

    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
}