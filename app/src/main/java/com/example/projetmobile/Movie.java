package com.example.projetmobile;

public class Movie {
    String title;
    String image;

    public Movie(String title, String image){
        this.title=title;
        this.image=image;
    }

    public String getTitle(){return title;}
    public String getImage(){return image;}
    public void setTitle(String title){this.title=title;}
    public void setImage(String image){this.image=image;}
}
