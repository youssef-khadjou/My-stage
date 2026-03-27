package com.monstage.modele;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidatures")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @Column(length = 1000)
    private String lettreMotivation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statut;

    @Column(nullable = false)
    private LocalDateTime datePostulation;

    public enum Statut {
        EN_ATTENTE,
        ACCEPTEE,
        REFUSEE,
        EN_COURS_TRAITEMENT
    }

    @PrePersist
    protected void onCreate() {
        datePostulation = LocalDateTime.now();
        if (statut == null) statut = Statut.EN_ATTENTE;
    }
}