package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidatures")
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @Column(length = 1000)
    private String lettreMotivation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statut;

    @Column(nullable = false)
    private LocalDateTime datePostulation;

    public enum Statut {
        EN_ATTENTE,
        ACCEPTEE,
        REFUSEE,
        EN_COURS_TRAITEMENT
    }

    @PrePersist
    protected void onCreate() {
        datePostulation = LocalDateTime.now();
        if (statut == null) statut = Statut.EN_ATTENTE;
    }

    // Constructeurs
    public Candidature() {}
    
    public Candidature(Long id, Etudiant etudiant, Stage stage, String lettreMotivation, Statut statut, LocalDateTime datePostulation) {
        this.id = id;
        this.etudiant = etudiant;
        this.stage = stage;
        this.lettreMotivation = lettreMotivation;
        this.statut = statut;
        this.datePostulation = datePostulation;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public LocalDateTime getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(LocalDateTime datePostulation) {
        this.datePostulation = datePostulation;
    }
}