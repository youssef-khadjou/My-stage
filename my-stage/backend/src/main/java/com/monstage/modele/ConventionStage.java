package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Représente la convention de stage tripartite entre :
 * - l'Etudiant
 * - l'Entreprise
 * - l'Université (représentée par un Professeur/responsable)
 *
 * Une convention est générée quand une candidature est acceptée.
 * Elle doit être signée par les trois parties avant le début du stage.
 */
@Entity
@Table(name = "convention_stage")
public class ConventionStage {

    /** Statut de la convention */
    public enum Statut {
        GENEREE,            // convention créée, en attente de signatures
        SIGNEE_ETUDIANT,    // signée par l'étudiant uniquement
        SIGNEE_ENTREPRISE,  // signée par l'entreprise uniquement
        SIGNEE_UNIVERSITE,  // signée par l'université uniquement
        VALIDEE,            // toutes les signatures obtenues
        ANNULEE             // convention annulée
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convention")
    private Long id;

    /** Numéro unique de la convention (ex: CONV-2024-0042) */
    @Column(name = "numero_convention", unique = true)
    private String numeroConvention;

    /** Date à laquelle l'étudiant a signé */
    @Column(name = "date_signature_etudiant")
    private LocalDate dateSignatureEtudiant;

    /** Date à laquelle l'entreprise a signé */
    @Column(name = "date_signature_entreprise")
    private LocalDate dateSignatureEntreprise;

    /** Date à laquelle l'université a signé */
    @Column(name = "date_signature_universite")
    private LocalDate dateSignatureUniversite;

    /** Statut actuel de la convention */
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private Statut statut = Statut.GENEREE;

    /** Date de création de la convention */
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    /** Date de validation finale (quand toutes les signatures sont obtenues) */
    @Column(name = "date_validation")
    private LocalDateTime dateValidation;

    /** URL vers le fichier PDF de la convention signée */
    @Column(name = "fichier_convention_url")
    private String fichierConventionUrl;

    /** L'étudiant concerné par cette convention */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant etudiant;

    /** Le stage auquel cette convention est rattachée */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stage", nullable = false)
    private Stage stage;

    // ---- Constructeurs ----

    public ConventionStage() {}

    public ConventionStage(Etudiant etudiant, Stage stage, String numeroConvention) {
        this.etudiant = etudiant;
        this.stage = stage;
        this.numeroConvention = numeroConvention;
        this.statut = Statut.GENEREE;
        this.dateCreation = LocalDateTime.now();
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroConvention() { return numeroConvention; }
    public void setNumeroConvention(String numeroConvention) { this.numeroConvention = numeroConvention; }

    public LocalDate getDateSignatureEtudiant() { return dateSignatureEtudiant; }
    public void setDateSignatureEtudiant(LocalDate dateSignatureEtudiant) { this.dateSignatureEtudiant = dateSignatureEtudiant; }

    public LocalDate getDateSignatureEntreprise() { return dateSignatureEntreprise; }
    public void setDateSignatureEntreprise(LocalDate dateSignatureEntreprise) { this.dateSignatureEntreprise = dateSignatureEntreprise; }

    public LocalDate getDateSignatureUniversite() { return dateSignatureUniversite; }
    public void setDateSignatureUniversite(LocalDate dateSignatureUniversite) { this.dateSignatureUniversite = dateSignatureUniversite; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public LocalDateTime getDateValidation() { return dateValidation; }
    public void setDateValidation(LocalDateTime dateValidation) { this.dateValidation = dateValidation; }

    public String getFichierConventionUrl() { return fichierConventionUrl; }
    public void setFichierConventionUrl(String fichierConventionUrl) { this.fichierConventionUrl = fichierConventionUrl; }

    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }

    public Stage getStage() { return stage; }
    public void setStage(Stage stage) { this.stage = stage; }
}