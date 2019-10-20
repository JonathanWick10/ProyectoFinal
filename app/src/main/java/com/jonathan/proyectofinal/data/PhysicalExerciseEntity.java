package com.jonathan.proyectofinal.data;

import java.io.StreamCorruptedException;

public class PhysicalExerciseEntity {
    private String nameExercise;
    private String Descripcion;
    private int time;
    private int image;

    public PhysicalExerciseEntity(String nameExercise, String descripcion, int time, int image) {
        this.nameExercise = nameExercise;
        Descripcion = descripcion;
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
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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
