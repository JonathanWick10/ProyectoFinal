package com.jonathan.proyectofinal.data;

public class MemoramaEntity {
    //Todo Recordar pedir que creen el pojo en firebase
    private String image;
    private boolean show;
    private int imageGrup;
    private int position;
    private boolean clickeable;
    private boolean found;

    public MemoramaEntity(String image, int imageGrup ) {
        this.image = image;
        this.show = true;
        this.imageGrup = imageGrup;
        this.position = -1;
        this.clickeable = false;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isClickeable() {
        return clickeable;
    }

    public void setClickeable(boolean clickeable) {
        this.clickeable = clickeable;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
