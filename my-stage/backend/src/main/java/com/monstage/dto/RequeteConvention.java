package com.monstage.dto;

/**
 * DTO pour la création d'une ConventionStage.
 * Utilisé quand une candidature est acceptée et qu'on génère la convention.
 */
public class RequeteConvention {

    /** ID de l'étudiant concerné */
    private Long etudiantId;

    /** ID du stage concerné */
    private Long stageId;

    /** Numéro de convention généré (ex: "CONV-2024-0042") */
    private String numeroConvention;

    // ---- Getters & Setters ----

    public Long getEtudiantId() { return etudiantId; }
    public void setEtudiantId(Long etudiantId) { this.etudiantId = etudiantId; }

    public Long getStageId() { return stageId; }
    public void setStageId(Long stageId) { this.stageId = stageId; }

    public String getNumeroConvention() { return numeroConvention; }
    public void setNumeroConvention(String numeroConvention) { this.numeroConvention = numeroConvention; }
}