package com.monstage.dto;

import com.monstage.modele.Utilisateur;

public class RequeteInscription {
    // Commun
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String telephone;
    private Utilisateur.Role role;

    // Etudiant
    private String numeroEtudiant;
    private String filiere;
    private String niveau;
    private String anneeScolaire;

    // Entreprise
    private String siret;
    private String nomEntreprise;
    private String adresse;
    private String ville;
    private String codePostal;
    private String secteurActivite;

    // Professeur
    private String specialite;
    
    // Constructeurs
    public RequeteInscription() {}
    
    public RequeteInscription(String email, String motDePasse, String nom, String prenom, 
                              String telephone, Utilisateur.Role role) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.role = role;
    }
    
    // Getters et Setters - Commun
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public Utilisateur.Role getRole() {
        return role;
    }
    
    public void setRole(Utilisateur.Role role) {
        this.role = role;
    }
    
    // Getters et Setters - Etudiant
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
    
    // Getters et Setters - Entreprise
    public String getSiret() {
        return siret;
    }
    
    public void setSiret(String siret) {
        this.siret = siret;
    }
    
    public String getNomEntreprise() {
        return nomEntreprise;
    }
    
    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getVille() {
        return ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public String getCodePostal() {
        return codePostal;
    }
    
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    public String getSecteurActivite() {
        return secteurActivite;
    }
    
    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }
    
    // Getters et Setters - Professeur
    public String getSpecialite() {
        return specialite;
    }
    
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}