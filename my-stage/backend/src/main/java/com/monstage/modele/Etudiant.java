package com.monstage.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "etudiant")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}