package com.example.hely.models;

public class EditorModel {

    int image;
    String name;
    Float rating;
    String comment;

    public EditorModel(int image, String name, Float rating, String comment) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
