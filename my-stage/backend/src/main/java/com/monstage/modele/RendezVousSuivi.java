package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Représente un rendez-vous de suivi entre un professeur encadrant et un étudiant.
 * Ces rendez-vous permettent au professeur de suivre l'avancement du stage
 * sans nécessairement se déplacer en entreprise (peut être en présentiel ou à distance).
 * Relation : FAIRE L'OBJET DE (1,1) avec la candidature.
 */
@Entity
@Table(name = "rendez_vous_suivi")
public class RendezVousSuivi {

    /** Type de rendez-vous */
    public enum TypeRendezVous {
        PRESENTIEL,     // en face à face
        VISIOCONFERENCE, // Teams, Zoom, etc.
        TELEPHONE,      // appel téléphonique
        EMAIL           // suivi par email
    }

    /** Statut du rendez-vous */
    public enum Statut {
        PLANIFIE,   // prévu mais pas encore eu lieu
        REALISE,    // rendez-vous effectué
        ANNULE,     // annulé par l'une des parties
        REPORTE     // reporté à une autre date
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rdv")
    private Long id;

    /** Date et heure du rendez-vous */
    @Column(name = "date_rdv", nullable = false)
    private LocalDateTime dateRdv;

    /** Type de rendez-vous (présentiel, visio...) */
    @Enumerated(EnumType.STRING)
    @Column(name = "type_rdv")
    private TypeRendezVous typeRdv;

    /** Compte-rendu rédigé après le rendez-vous */
    @Column(name = "compte_rendu", columnDefinition = "TEXT")
    private String compteRendu;

    /** Statut actuel du rendez-vous */
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut = Statut.PLANIFIE;

    /** La candidature (et donc le stage) concernée par ce suivi */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_candidature", nullable = false)
    private Candidature candidature;

    // ---- Constructeurs ----

    public RendezVousSuivi() {}

    public RendezVousSuivi(LocalDateTime dateRdv, TypeRendezVous typeRdv, Candidature candidature) {
        this.dateRdv = dateRdv;
        this.typeRdv = typeRdv;
        this.candidature = candidature;
        this.statut = Statut.PLANIFIE;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateRdv() { return dateRdv; }
    public void setDateRdv(LocalDateTime dateRdv) { this.dateRdv = dateRdv; }

    public TypeRendezVous getTypeRdv() { return typeRdv; }
    public void setTypeRdv(TypeRendezVous typeRdv) { this.typeRdv = typeRdv; }

    public String getCompteRendu() { return compteRendu; }
    public void setCompteRendu(String compteRendu) { this.compteRendu = compteRendu; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public Candidature getCandidature() { return candidature; }
    public void setCandidature(Candidature candidature) { this.candidature = candidature; }
}