package com.example.projetmobile;

public class Movie {
    private String title;
    private String posterUrl;
    private String actors;
    private int years;

    public Movie(String title, String posterUrl, String actors, int years) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.actors = actors;
        this.years = years;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getActors() {
        return actors;
    }

    public int getYears() {
        return years;
    }
}


