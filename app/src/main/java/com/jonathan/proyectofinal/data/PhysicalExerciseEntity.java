package com.jonathan.proyectofinal.data;

public class PhysicalExerciseEntity {
    private String nameExercise;
    private String descripcion;
    private int time;
    private int image;

    public PhysicalExerciseEntity(String nameExercise, String descripcion, int time, int image) {
        this.nameExercise = nameExercise;
        this.descripcion = descripcion;
        this.time = time;
        this.image = image;
    }

    public PhysicalExerciseEntity() { }

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
