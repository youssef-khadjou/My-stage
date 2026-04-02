package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Représente une visite de stage effectuée par un professeur encadrant.
 * Le professeur se rend en entreprise pour suivre l'étudiant.
 * Un stage peut faire l'objet de plusieurs visites (FAIRE L'OBJET DE 1,n).
 */
@Entity
@Table(name = "visite_stage")
public class VisiteStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visite")
    private Long id;

    /** Date à laquelle la visite a eu lieu */
    @Column(name = "date_visite", nullable = false)
    private LocalDate dateVisite;

    /** Indique si le tuteur en entreprise était présent lors de la visite */
    @Column(name = "present")
    private Boolean present;

    /** Compte-rendu rédigé par le professeur après la visite */
    @Column(name = "compte_rendu", columnDefinition = "TEXT")
    private String compteRendu;

    /** Indique si le compte-rendu a été signé par l'entreprise */
    @Column(name = "signe_par_entreprise")
    private Boolean signeParEntreprise = false;

    /** Nom du tuteur de l'entreprise rencontré lors de la visite */
    @Column(name = "nom_tuteur")
    private String nomTuteur;

    /** Date et heure de saisie du compte-rendu */
    @Column(name = "date_saisie")
    private LocalDateTime dateSaisie;

    /** Signature numérique ou référence à la signature */
    @Column(name = "date_signature")
    private LocalDate dateSignature;

    /** Le stage visité */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stage", nullable = false)
    private Stage stage;

    /** La candidature associée (lien via FAIRE L'OBJET DE dans le MCD) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_candidature")
    private Candidature candidature;

    // ---- Constructeurs ----

    public VisiteStage() {}

    public VisiteStage(LocalDate dateVisite, Stage stage) {
        this.dateVisite = dateVisite;
        this.stage = stage;
        this.dateSaisie = LocalDateTime.now();
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDateVisite() { return dateVisite; }
    public void setDateVisite(LocalDate dateVisite) { this.dateVisite = dateVisite; }

    public Boolean getPresent() { return present; }
    public void setPresent(Boolean present) { this.present = present; }

    public String getCompteRendu() { return compteRendu; }
    public void setCompteRendu(String compteRendu) { this.compteRendu = compteRendu; }

    public Boolean getSigneParEntreprise() { return signeParEntreprise; }
    public void setSigneParEntreprise(Boolean signeParEntreprise) { this.signeParEntreprise = signeParEntreprise; }

    public String getNomTuteur() { return nomTuteur; }
    public void setNomTuteur(String nomTuteur) { this.nomTuteur = nomTuteur; }

    public LocalDateTime getDateSaisie() { return dateSaisie; }
    public void setDateSaisie(LocalDateTime dateSaisie) { this.dateSaisie = dateSaisie; }

    public LocalDate getDateSignature() { return dateSignature; }
    public void setDateSignature(LocalDate dateSignature) { this.dateSignature = dateSignature; }

    public Stage getStage() { return stage; }
    public void setStage(Stage stage) { this.stage = stage; }

    public Candidature getCandidature() { return candidature; }
    public void setCandidature(Candidature candidature) { this.candidature = candidature; }
}