package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Représente une semaine dédiée aux soutenances de stage.
 * L'administration définit des périodes pendant lesquelles les soutenances
 * peuvent être planifiées, avec des durées différentes selon le niveau (Licence/Master).
 * Relation : PLANIFIER (1,n) avec des créneaux de soutenance.
 */
@Entity
@Table(name = "semaine_soutenance")
public class SemaineSoutenance {

    /** Statut de la semaine */
    public enum Statut {
        OUVERTE,    // les créneaux peuvent être affectés
        FERMEE,     // semaine finalisée, plus de modifications
        ANNULEE     // semaine annulée
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_semaine")
    private Long id;

    /** Année scolaire concernée (ex: "2023-2024") */
    @Column(name = "annee_scolaire", nullable = false)
    private String anneeScolaire;

    /** Date de début de la semaine de soutenance */
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    /** Date de fin de la semaine de soutenance */
    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    /** Durée d'une soutenance Licence en minutes (ex: 30) */
    @Column(name = "duree_licence_minutes")
    private Integer dureeLicenceMinutes;

    /** Durée d'une soutenance Master en minutes (ex: 45) */
    @Column(name = "duree_master_minutes")
    private Integer dureeMasterMinutes;

    /** Statut actuel de la semaine */
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut = Statut.OUVERTE;

    // ---- Constructeurs ----

    public SemaineSoutenance() {}

    public SemaineSoutenance(String anneeScolaire, LocalDate dateDebut, LocalDate dateFin) {
        this.anneeScolaire = anneeScolaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = Statut.OUVERTE;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAnneeScolaire() { return anneeScolaire; }
    public void setAnneeScolaire(String anneeScolaire) { this.anneeScolaire = anneeScolaire; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Integer getDureeLicenceMinutes() { return dureeLicenceMinutes; }
    public void setDureeLicenceMinutes(Integer dureeLicenceMinutes) { this.dureeLicenceMinutes = dureeLicenceMinutes; }

    public Integer getDureeMasterMinutes() { return dureeMasterMinutes; }
    public void setDureeMasterMinutes(Integer dureeMasterMinutes) { this.dureeMasterMinutes = dureeMasterMinutes; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }
}