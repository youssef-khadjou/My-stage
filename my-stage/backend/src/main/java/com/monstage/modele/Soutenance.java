package com.monstage.modele;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "soutenances")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
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
}