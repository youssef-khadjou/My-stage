package com.monstage.dto;

public class RequeteCandidature {
    private Long stageId;
    private String lettreMotivation;
    
    // Constructeurs
    public RequeteCandidature() {}
    
    public RequeteCandidature(Long stageId, String lettreMotivation) {
        this.stageId = stageId;
        this.lettreMotivation = lettreMotivation;
    }
    
    // Getters et Setters
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
}