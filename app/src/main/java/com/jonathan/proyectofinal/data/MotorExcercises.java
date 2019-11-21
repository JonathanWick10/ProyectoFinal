package com.jonathan.proyectofinal.data;

public class MotorExcercises  {

    private int idExcercise;
    private String uriGifExcercise;
    private String nameExcercise;
    private String descriptionExcercise;
    private String longDescriptionExcercise;
    private int timeExcercise;

    public MotorExcercises() {
    }

    public MotorExcercises(int idExcercise, String uriGifExcercise, String nameExcercise, String descriptionExcercise, String longDescriptionExcercise, int timeExcercise) {
        this.idExcercise = idExcercise;
        this.uriGifExcercise = uriGifExcercise;
        this.nameExcercise = nameExcercise;
        this.descriptionExcercise = descriptionExcercise;
        this.longDescriptionExcercise = longDescriptionExcercise;
        this.timeExcercise = timeExcercise;
    }

    public int getIdExcercise() {
        return idExcercise;
    }

    public void setIdExcercise(int idExcercise) {
        this.idExcercise = idExcercise;
    }

    public String getUriGifExcercise() {
        return uriGifExcercise;
    }

    public void setUriGifExcercise(String uriGifExcercise) {
        this.uriGifExcercise = uriGifExcercise;
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

    public String getLongDescriptionExcercise() {
        return longDescriptionExcercise;
    }

    public void setLongDescriptionExcercise(String longDescriptionExcercise) {
        this.longDescriptionExcercise = longDescriptionExcercise;
    }

    public int getTimeExcercise() {
        return timeExcercise;
    }

    public void setTimeExcercise(int timeExcercise) {
        this.timeExcercise = timeExcercise;
    }
}
