package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Représente la soutenance de fin de stage d'un étudiant.
 * Une soutenance est liée à une ConventionStage (SOUTENIR 0,n)
 * et se déroule dans un CreneauSoutenance (SE DÉROULE 1,1).
 */
@Entity
@Table(name = "soutenance")
public class Soutenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_soutenance")
    private Long id;

    /** URL vers le rapport de stage déposé par l'étudiant */
    @Column(name = "rapport_url")
    private String rapportUrl;

    /** Note attribuée lors de la soutenance (sur 20) */
    @Column(name = "note_stage")
    private Double noteStage;

    /** Rôle du professeur dans cette soutenance (ex: "président", "rapporteur") */
    @Column(name = "role_soutenance")
    private String roleSoutenance;

    /** Commentaires du jury sur le travail de l'étudiant */
    @Column(name = "commentaires_jury", columnDefinition = "TEXT")
    private String commentairesJury;

    /** Date à laquelle la soutenance a été évaluée et la note saisie */
    @Column(name = "date_evaluation")
    private LocalDate dateEvaluation;

    /**
     * Le créneau dans lequel cette soutenance se déroule.
     * Relation SE DÉROULE (1,1) : une soutenance occupe exactement un créneau.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creneau")
    private CreneauSoutenance creneauSoutenance;

    /**
     * La convention de stage associée à cette soutenance.
     * Relation SOUTENIR (0,n) : un étudiant peut avoir plusieurs soutenances
     * (cas de redoublement, de stage annulé, etc.)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_convention")
    private ConventionStage convention;

    // ---- Constructeurs ----

    public Soutenance() {}

    public Soutenance(ConventionStage convention, CreneauSoutenance creneau) {
        this.convention = convention;
        this.creneauSoutenance = creneau;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRapportUrl() { return rapportUrl; }
    public void setRapportUrl(String rapportUrl) { this.rapportUrl = rapportUrl; }

    public Double getNoteStage() { return noteStage; }
    public void setNoteStage(Double noteStage) { this.noteStage = noteStage; }

    public String getRoleSoutenance() { return roleSoutenance; }
    public void setRoleSoutenance(String roleSoutenance) { this.roleSoutenance = roleSoutenance; }

    public String getCommentairesJury() { return commentairesJury; }
    public void setCommentairesJury(String commentairesJury) { this.commentairesJury = commentairesJury; }

    public LocalDate getDateEvaluation() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation = dateEvaluation; }

    public CreneauSoutenance getCreneauSoutenance() { return creneauSoutenance; }
    public void setCreneauSoutenance(CreneauSoutenance creneauSoutenance) { this.creneauSoutenance = creneauSoutenance; }

    public ConventionStage getConvention() { return convention; }
    public void setConvention(ConventionStage convention) { this.convention = convention; }
}