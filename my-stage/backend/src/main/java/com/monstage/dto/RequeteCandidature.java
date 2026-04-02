package com.monstage.dto;

public class RequeteCandidature {
    private Long etudiantId;
    private Long stageId;
    private String lettreMotivation;
    private String cvUrl;

    public RequeteCandidature() {}

    public RequeteCandidature(Long etudiantId, Long stageId, String lettreMotivation, String cvUrl) {
        this.etudiantId = etudiantId;
        this.stageId = stageId;
        this.lettreMotivation = lettreMotivation;
        this.cvUrl = cvUrl;
    }

    // Getters et Setters
    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }
}