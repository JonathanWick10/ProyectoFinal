package com.jonathan.proyectofinal.data;

public class PhysicalExerciseEntity {

    private String uriImage;
    private String nameExercise;
    private String descripcion;
    private int time;
    private int image;

    public PhysicalExerciseEntity() {
    }

    public PhysicalExerciseEntity(String uriImage, String nameExercise, String descripcion, int time, int image) {
        this.uriImage = uriImage;
        this.nameExercise = nameExercise;
        this.descripcion = descripcion;
        this.time = time;
        this.image = image;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public String getNameExercise() {
        return nameExercise;
    }

    public void setNameExercise(String nameExercise) {
        this.nameExercise = nameExercise;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
