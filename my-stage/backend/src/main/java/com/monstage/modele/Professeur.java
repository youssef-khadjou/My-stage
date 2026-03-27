package com.monstage.modele;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "professeurs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String specialite;
    private String bureau;
    private String telephone;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    private List<Stage> stages;
}