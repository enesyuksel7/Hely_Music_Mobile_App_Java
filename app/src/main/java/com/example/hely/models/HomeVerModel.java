package com.example.hely.models;

import java.io.Serializable;

public class HomeVerModel implements Serializable {

    String Kapak;
    String Ad;
    String Sanatci;
    String Rating;
    String Kategori;

    public HomeVerModel(String kapak, String ad, String sanatci, String rating, String kategori) {
        Kapak = kapak;
        Ad = ad;
        Sanatci = sanatci;
        Rating = rating;
        Kategori = kategori;
    }

    public HomeVerModel(){

    }

    public String getKapak() {
        return Kapak;
    }

    public void setKapak(String kapak) {
        Kapak = kapak;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSanatci() {
        return Sanatci;
    }

    public void setSanatci(String sanatci) {
        Sanatci = sanatci;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String kategori) {
        Kategori = kategori;
    }
}
