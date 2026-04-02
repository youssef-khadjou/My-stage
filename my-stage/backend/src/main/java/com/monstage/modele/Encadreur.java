package com.monstage.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "encadreur")
public class Encadreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encadreur")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "fonction")
    private String fonction;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "est_encadrant_principal")
    private Boolean estEncadrantPrincipal = false;

    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "id_stage")
    private Stage stage;

    @OneToOne
    @JoinColumn(name = "id_convention")
    private ConventionStage convention;

    public Encadreur() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getFonction() { return fonction; }
    public void setFonction(String fonction) { this.fonction = fonction; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public Boolean getEstEncadrantPrincipal() { return estEncadrantPrincipal; }
    public void setEstEncadrantPrincipal(Boolean estEncadrantPrincipal) { this.estEncadrantPrincipal = estEncadrantPrincipal; }

    public Entreprise getEntreprise() { return entreprise; }
    public void setEntreprise(Entreprise entreprise) { this.entreprise = entreprise; }

    public Stage getStage() { return stage; }
    public void setStage(Stage stage) { this.stage = stage; }

    public ConventionStage getConvention() { return convention; }
    public void setConvention(ConventionStage convention) { this.convention = convention; }
}