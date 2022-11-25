package com.example.hely.models;

public class YorumModel {

    String yorumlarKisi;
    String yorumlarListeYorum;
    Float yorumlarRating;

    public YorumModel(String yorumlarKisi, String yorumlarListeYorum, Float yorumlarRating) {
        this.yorumlarKisi = yorumlarKisi;
        this.yorumlarListeYorum = yorumlarListeYorum;
        this.yorumlarRating = yorumlarRating;
    }

    public String getYorumlarKisi() {
        return yorumlarKisi;
    }

    public void setYorumlarKisi(String yorumlarKisi) {
        this.yorumlarKisi = yorumlarKisi;
    }

    public String getYorumlarListeYorum() {
        return yorumlarListeYorum;
    }

    public void setYorumlarListeYorum(String yorumlarListeYorum) {
        this.yorumlarListeYorum = yorumlarListeYorum;
    }

    public Float getYorumlarRating() {
        return yorumlarRating;
    }

    public void setYorumlarRating(Float yorumlarRating) {
        this.yorumlarRating = yorumlarRating;
    }
}
