package com.jonathan.proyectofinal.data;

public class MotorExcercises  {

    private Integer idExcercise;
    private String uriGifExcercise;
    private String nameExcercise;
    private String descriptionExcercise;
    private String longDescriptionExcercise;
    private Integer timeExcercise;

    public MotorExcercises() {
    }

    public MotorExcercises(Integer idExcercise, String uriGifExcercise, String nameExcercise, String descriptionExcercise, String longDescriptionExcercise, Integer timeExcercise) {
        this.idExcercise = idExcercise;
        this.uriGifExcercise = uriGifExcercise;
        this.nameExcercise = nameExcercise;
        this.descriptionExcercise = descriptionExcercise;
        this.longDescriptionExcercise = longDescriptionExcercise;
        this.timeExcercise = timeExcercise;
    }

    public Integer getIdExcercise() {
        return idExcercise;
    }

    public void setIdExcercise(Integer idExcercise) {
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

    public Integer getTimeExcercise() {
        return timeExcercise;
    }

    public void setTimeExcercise(Integer timeExcercise) {
        this.timeExcercise = timeExcercise;
    }
}
