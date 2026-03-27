package com.monstage.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "etudiant")
public class Etudiant {
    
    @Id
    private Long id;
    
    @Column(name = "numero_etudiant", unique = true, nullable = false, length = 20)
    private String numeroEtudiant;
    
    @Column(nullable = false, length = 100)
    private String filiere;
    
    @Column(nullable = false, length = 50)
    private String niveau;
    
    @Column(name = "annee_scolaire", nullable = false, length = 20)
    private String anneeScolaire;
    
    @Column(name = "cv_path")
    private String cvPath;
    
    @Column(name = "lettre_motivation_path")
    private String lettreMotivationPath;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Utilisateur utilisateur;
    
    // Constructeurs
    public Etudiant() {}
    
    public Etudiant(Long id, String numeroEtudiant, String filiere, String niveau, 
                    String anneeScolaire, String cvPath, String lettreMotivationPath, Utilisateur utilisateur) {
        this.id = id;
        this.numeroEtudiant = numeroEtudiant;
        this.filiere = filiere;
        this.niveau = niveau;
        this.anneeScolaire = anneeScolaire;
        this.cvPath = cvPath;
        this.lettreMotivationPath = lettreMotivationPath;
        this.utilisateur = utilisateur;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public String getLettreMotivationPath() {
        return lettreMotivationPath;
    }

    public void setLettreMotivationPath(String lettreMotivationPath) {
        this.lettreMotivationPath = lettreMotivationPath;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}