package com.monstage.dto;

public class RequeteEncadreur {
    private String nom;
    private String prenom;
    private String fonction;
    private String email;
    private String telephone;
    private Boolean estEncadrantPrincipal;
    private Long idEntreprise;
    private Long idStage;
    private Long idConvention;

    public RequeteEncadreur() {}

    // Getters et Setters
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

    public Long getIdEntreprise() { return idEntreprise; }
    public void setIdEntreprise(Long idEntreprise) { this.idEntreprise = idEntreprise; }

    public Long getIdStage() { return idStage; }
    public void setIdStage(Long idStage) { this.idStage = idStage; }

    public Long getIdConvention() { return idConvention; }
    public void setIdConvention(Long idConvention) { this.idConvention = idConvention; }
}