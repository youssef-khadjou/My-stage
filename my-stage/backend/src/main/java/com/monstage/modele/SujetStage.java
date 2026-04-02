package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Représente un sujet de stage proposé par une entreprise.
 * Une entreprise peut proposer plusieurs sujets (PROPOSER 0,n).
 * Un sujet peut faire l'objet de plusieurs candidatures.
 */
@Entity
@Table(name = "sujet_stage")
public class SujetStage {

    /** Statut de disponibilité du sujet */
    public enum Statut {
        EN_ATTENTE,     // sujet soumis, en attente de validation
        VALIDE,         // sujet validé par l'administration
        REFUSE,         // sujet refusé
        POURVU,         // un étudiant a été accepté sur ce sujet
        ARCHIVE         // sujet archivé (plus disponible)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sujet")
    private Long id;

    /** Titre du stage (ex: "Développement d'une API REST") */
    @Column(name = "intitule", nullable = false)
    private String intitule;

    /** Description détaillée des missions du stage */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /** Compétences techniques requises pour ce stage */
    @Column(name = "competences_requises", columnDefinition = "TEXT")
    private String competencesRequises;

    /** Durée du stage en nombre de semaines */
    @Column(name = "duree_semaines")
    private Integer dureeSemaines;

    /** Date de début souhaitée */
    @Column(name = "date_debut")
    private LocalDate dateDebut;

    /** Date de fin souhaitée */
    @Column(name = "date_fin")
    private LocalDate dateFin;

    /** Rémunération mensuelle proposée (peut être null si non rémunéré) */
    @Column(name = "remuneration")
    private Double remuneration;

    /** Secteur d'activité (ex: "Informatique", "Finance"...) */
    @Column(name = "secteur_activite")
    private String secteurActivite;

    /** Lieu du stage (ville, adresse) */
    @Column(name = "lieu_travail")
    private String lieuTravail;

    /** Statut actuel du sujet */
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut = Statut.EN_ATTENTE;

    /** Motif de refus si le sujet a été refusé */
    @Column(name = "motif_refus")
    private String motifRefus;

    /** Date à laquelle le sujet a été proposé */
    @Column(name = "date_proposition")
    private LocalDateTime dateProposition;

    /** Date à laquelle le sujet a été validé ou refusé */
    @Column(name = "date_validation")
    private LocalDateTime dateValidation;

    /** L'entreprise qui propose ce sujet */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entreprise", nullable = false)
    private Entreprise entreprise;

    // ---- Constructeurs ----

    public SujetStage() {}

    public SujetStage(String intitule, String description, Entreprise entreprise) {
        this.intitule = intitule;
        this.description = description;
        this.entreprise = entreprise;
        this.dateProposition = LocalDateTime.now();
        this.statut = Statut.EN_ATTENTE;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCompetencesRequises() { return competencesRequises; }
    public void setCompetencesRequises(String competencesRequises) { this.competencesRequises = competencesRequises; }

    public Integer getDureeSemaines() { return dureeSemaines; }
    public void setDureeSemaines(Integer dureeSemaines) { this.dureeSemaines = dureeSemaines; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Double getRemuneration() { return remuneration; }
    public void setRemuneration(Double remuneration) { this.remuneration = remuneration; }

    public String getSecteurActivite() { return secteurActivite; }
    public void setSecteurActivite(String secteurActivite) { this.secteurActivite = secteurActivite; }

    public String getLieuTravail() { return lieuTravail; }
    public void setLieuTravail(String lieuTravail) { this.lieuTravail = lieuTravail; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public String getMotifRefus() { return motifRefus; }
    public void setMotifRefus(String motifRefus) { this.motifRefus = motifRefus; }

    public LocalDateTime getDateProposition() { return dateProposition; }
    public void setDateProposition(LocalDateTime dateProposition) { this.dateProposition = dateProposition; }

    public LocalDateTime getDateValidation() { return dateValidation; }
    public void setDateValidation(LocalDateTime dateValidation) { this.dateValidation = dateValidation; }

    public Entreprise getEntreprise() { return entreprise; }
    public void setEntreprise(Entreprise entreprise) { this.entreprise = entreprise; }
}