package com.jonathan.proyectofinal.data;

public class CognitivesExcercises {

    private int idExcercise;
    private String uriImageExcercise;
    private String nameExcercise;
    private String descriptionExcercise;

    public CognitivesExcercises() {
    }

    public CognitivesExcercises(int idExcercise, String uriImageExcercise, String nameExcercise, String descriptionExcercise) {
        this.idExcercise = idExcercise;
        this.uriImageExcercise = uriImageExcercise;
        this.nameExcercise = nameExcercise;
        this.descriptionExcercise = descriptionExcercise;
    }

    public int getIdExcercise() {
        return idExcercise;
    }

    public void setIdExcercise(int idExcercise) {
        this.idExcercise = idExcercise;
    }

    public String getUriImageExcercise() {
        return uriImageExcercise;
    }

    public void setUriImageExcercise(String uriImageExcercise) {
        this.uriImageExcercise = uriImageExcercise;
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
}
