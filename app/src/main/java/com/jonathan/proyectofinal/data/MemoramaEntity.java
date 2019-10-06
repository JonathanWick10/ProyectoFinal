package com.jonathan.proyectofinal.data;

public class MemoramaEntity {
    //Todo Recordar pedir que creen el pojo en firebase
    private String image;
    private boolean show;
    private int imageGrup;
    private boolean found;

    public MemoramaEntity(String image, int imageGrup) {
        this.image = image;
        this.show = false;
        this.imageGrup = imageGrup;
        this.found = false;
    }

    public int getImageGrup() {
        return imageGrup;
    }

    public void setImageGrup(int imageGrup) {
        this.imageGrup = imageGrup;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
