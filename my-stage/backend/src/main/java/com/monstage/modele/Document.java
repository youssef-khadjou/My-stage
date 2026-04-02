package com.monstage.modele;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Représente un document déposé par un utilisateur.
 * Un document peut être : CV, lettre de motivation, convention signée, rapport, etc.
 * Relation : un Utilisateur peut déposer plusieurs Documents (1,n).
 */
@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private Long id;

    /** Nom original du fichier (ex: "cv_dupont.pdf") */
    @Column(name = "nom_fichier", nullable = false)
    private String nomFichier;

    /** URL ou chemin de stockage du fichier sur le serveur */
    @Column(name = "url_fichier", nullable = false)
    private String urlFichier;

    /** Taille du fichier en octets */
    @Column(name = "taille_octets")
    private Long tailleOctets;

    /** Date à laquelle le document a été uploadé */
    @Column(name = "date_upload")
    private LocalDateTime dateUpload;

    /** Utilisateur qui a déposé ce document */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_par", nullable = false)
    private Utilisateur uploadPar;

    // ---- Constructeurs ----

    public Document() {}

    public Document(String nomFichier, String urlFichier, Long tailleOctets, Utilisateur uploadPar) {
        this.nomFichier = nomFichier;
        this.urlFichier = urlFichier;
        this.tailleOctets = tailleOctets;
        this.uploadPar = uploadPar;
        this.dateUpload = LocalDateTime.now();
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomFichier() { return nomFichier; }
    public void setNomFichier(String nomFichier) { this.nomFichier = nomFichier; }

    public String getUrlFichier() { return urlFichier; }
    public void setUrlFichier(String urlFichier) { this.urlFichier = urlFichier; }

    public Long getTailleOctets() { return tailleOctets; }
    public void setTailleOctets(Long tailleOctets) { this.tailleOctets = tailleOctets; }

    public LocalDateTime getDateUpload() { return dateUpload; }
    public void setDateUpload(LocalDateTime dateUpload) { this.dateUpload = dateUpload; }

    public Utilisateur getUploadPar() { return uploadPar; }
    public void setUploadPar(Utilisateur uploadPar) { this.uploadPar = uploadPar; }
}