package com.example.catify;

public class DataModel {

    private String temperament;
    private String description;
    private String name;
    private String origin;
    private String vetstreet_url;

    private Weight weight;
    private Image image;

    public String getVetstreet_url() {
        return vetstreet_url;
    }

    public Image getImage() {
        return image;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }


    public Weight getWeight() {
        return weight;
    }
}
