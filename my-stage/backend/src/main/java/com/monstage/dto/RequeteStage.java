package com.monstage.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequeteStage {
    private String titre;
    private String description;
    private Integer duree;
    private String lieu;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer nbPlaces;
    private String competencesRequises;
    private BigDecimal remuneration;
}