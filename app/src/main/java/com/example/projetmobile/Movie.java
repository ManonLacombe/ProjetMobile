package com.example.projetmobile;

/**
 * This class defines the Movie object
 */
public class Movie {
    private String title;
    private String posterUrl;

    /**
     * Constructor
     * @param title is the title of the film
     * @param posterUrl is the link of the image
     */
    public Movie(String title, String posterUrl) {
        this.title = title;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}