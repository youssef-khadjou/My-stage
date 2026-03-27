package com.monstage.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "entreprise")
public class Entreprise {
    
    @Id
    private Long id;
    
    @Column(unique = true, nullable = false, length = 14)
    private String siret;
    
    @Column(name = "nom_entreprise", nullable = false, length = 100)
    private String nomEntreprise;
    
    @Column(length = 255)
    private String adresse;
    
    @Column(length = 100)
    private String ville;
    
    @Column(name = "code_postal", length = 10)
    private String codePostal;
    
    @Column(name = "secteur_activite", length = 100)
    private String secteurActivite;
    
    @Column(name = "site_web", length = 255)
    private String siteWeb;
    
    @Column(name = "telephone_contact", length = 20)
    private String telephoneContact;
    
    @Column(name = "nom_contact", length = 100)
    private String nomContact;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Utilisateur utilisateur;
    
    // Constructeurs
    public Entreprise() {}
    
    public Entreprise(Long id, String siret, String nomEntreprise, String adresse, String ville, 
                      String codePostal, String secteurActivite, String siteWeb, 
                      String telephoneContact, String nomContact, Utilisateur utilisateur) {
        this.id = id;
        this.siret = siret;
        this.nomEntreprise = nomEntreprise;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.secteurActivite = secteurActivite;
        this.siteWeb = siteWeb;
        this.telephoneContact = telephoneContact;
        this.nomContact = nomContact;
        this.utilisateur = utilisateur;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getTelephoneContact() {
        return telephoneContact;
    }

    public void setTelephoneContact(String telephoneContact) {
        this.telephoneContact = telephoneContact;
    }

    public String getNomContact() {
        return nomContact;
    }

    public void setNomContact(String nomContact) {
        this.nomContact = nomContact;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}