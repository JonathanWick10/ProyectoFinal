package com.jonathan.proyectofinal.data;

public class MedicationAssignment {

    private String recipeDate;
    private String hours;
    private String frequency;
    private String dose;
    private String statement;

    public MedicationAssignment() {
    }

    public MedicationAssignment(String recipeDate, String hours, String frequency, String dose, String statement) {
        this.recipeDate = recipeDate;
        this.hours = hours;
        this.frequency = frequency;
        this.dose = dose;
        this.statement = statement;
    }

    public String getRecipeDate() {
        return recipeDate;
    }

    public void setRecipeDate(String recipeDate) {
        this.recipeDate = recipeDate;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "MedicationAssignment{" +
                "recipeDate='" + recipeDate + '\'' +
                ", hours='" + hours + '\'' +
                ", frequency='" + frequency + '\'' +
                ", dose='" + dose + '\'' +
                ", statement='" + statement + '\'' +
                '}';
    }
}
