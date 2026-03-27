package com.monstage.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "entreprise")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}