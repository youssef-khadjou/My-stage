package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "soutenances")
public class Soutenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateHeure;
    private String salle;
    private String lienVideoconference;

    @OneToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;
    
    // Constructeurs
    public Soutenance() {}
    
    public Soutenance(Long id, LocalDateTime dateHeure, String salle, String lienVideoconference, Stage stage, Professeur professeur) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.salle = salle;
        this.lienVideoconference = lienVideoconference;
        this.stage = stage;
        this.professeur = professeur;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getLienVideoconference() {
        return lienVideoconference;
    }

    public void setLienVideoconference(String lienVideoconference) {
        this.lienVideoconference = lienVideoconference;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }
}