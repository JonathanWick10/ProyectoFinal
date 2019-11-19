package com.jonathan.proyectofinal.data;

public class CognitiveExcercisesAssignment {

    private String uidPatient;
    private String uriImageExcercise;
    private String idExcercise;
    private String nameExcercise;
    private String descriptionExcercise;
    private Integer level;
    private Integer bestScore;
    private String statement;
    private Integer rating;

    public CognitiveExcercisesAssignment() {
    }

    public CognitiveExcercisesAssignment(String uidPatient, String uriImageExcercise, String idExcercise, String nameExcercise, String descriptionExcercise, Integer level, Integer bestScore, String statement, Integer rating) {
        this.uidPatient = uidPatient;
        this.uriImageExcercise = uriImageExcercise;
        this.idExcercise = idExcercise;
        this.nameExcercise = nameExcercise;
        this.descriptionExcercise = descriptionExcercise;
        this.level = level;
        this.bestScore = bestScore;
        this.statement = statement;
        this.rating = rating;
    }

    public String getUidPatient() {
        return uidPatient;
    }

    public void setUidPatient(String uidPatient) {
        this.uidPatient = uidPatient;
    }

    public String getUriImageExcercise() {
        return uriImageExcercise;
    }

    public void setUriImageExcercise(String uriImageExcercise) {
        this.uriImageExcercise = uriImageExcercise;
    }

    public String getIdExcercise() {
        return idExcercise;
    }

    public void setIdExcercise(String idExcercise) {
        this.idExcercise = idExcercise;
    }

    public String getNameExcercise() {
        return nameExcercise;
    }

    public void setNameExcercise(String nameExcercise) {
        this.nameExcercise = nameExcercise;
    }

    public String getDescriptionExcercise() {
        return descriptionExcercise;
    }

    public void setDescriptionExcercise(String descriptionExcercise) {
        this.descriptionExcercise = descriptionExcercise;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getBestScore() {
        return bestScore;
    }

    public void setBestScore(Integer bestScore) {
        this.bestScore = bestScore;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
