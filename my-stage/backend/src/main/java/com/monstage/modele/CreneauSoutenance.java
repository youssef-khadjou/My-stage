package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Représente un créneau horaire disponible pour une soutenance.
 * Les créneaux appartiennent à une SemaineSoutenance et peuvent être
 * attribués à une Soutenance d'étudiant.
 * Relation : SE DÉROULE (0,1) avec Soutenance — un créneau peut être libre ou occupé.
 */
@Entity
@Table(name = "creneau_soutenance")
public class CreneauSoutenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_creneau")
    private Long id;

    /** Date du créneau */
    @Column(name = "date_soutenance", nullable = false)
    private LocalDate dateSoutenance;

    /** Heure de début du créneau */
    @Column(name = "heure_debut", nullable = false)
    private LocalTime heureDebut;

    /** Heure de fin du créneau */
    @Column(name = "heure_fin", nullable = false)
    private LocalTime heureFin;

    /** Salle où se déroule la soutenance (ex: "Amphi B", "Salle 204") */
    @Column(name = "salle")
    private String salle;

    /** Indique si ce créneau est encore disponible */
    @Column(name = "disponible")
    private Boolean disponible = true;

    /**
     * La semaine de soutenance à laquelle appartient ce créneau.
     * Relation PLANIFIER (1,n) : une semaine contient plusieurs créneaux.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semaine", nullable = false)
    private SemaineSoutenance semaineSoutenance;

    /**
     * La soutenance qui occupe ce créneau (peut être null si le créneau est libre).
     * Relation SE DÉROULE (0,1) : un créneau peut accueillir au plus une soutenance.
     */
    @OneToOne(mappedBy = "creneauSoutenance", fetch = FetchType.LAZY)
    private Soutenance soutenance;

    // ---- Constructeurs ----

    public CreneauSoutenance() {}

    public CreneauSoutenance(LocalDate dateSoutenance, LocalTime heureDebut, LocalTime heureFin,
                              String salle, SemaineSoutenance semaineSoutenance) {
        this.dateSoutenance = dateSoutenance;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
        this.semaineSoutenance = semaineSoutenance;
        this.disponible = true;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDateSoutenance() { return dateSoutenance; }
    public void setDateSoutenance(LocalDate dateSoutenance) { this.dateSoutenance = dateSoutenance; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }

    public String getSalle() { return salle; }
    public void setSalle(String salle) { this.salle = salle; }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }

    public SemaineSoutenance getSemaineSoutenance() { return semaineSoutenance; }
    public void setSemaineSoutenance(SemaineSoutenance semaineSoutenance) { this.semaineSoutenance = semaineSoutenance; }

    public Soutenance getSoutenance() { return soutenance; }
    public void setSoutenance(Soutenance soutenance) { this.soutenance = soutenance; }
}