package com.example.myapplicationtroc;

import java.util.Date;


public class CategorieData {

    private int idArticle;
    private String name;
    private String categorie;
    private Date when;


    public CategorieData( int idArticle, String name, String categorie, Date when) {
        this.setIdArticle( idArticle );
        this.setName( name );
        this.setCategorie( categorie );
        this.setWhen( when );
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle( int idArticle ) {
        this.idArticle = idArticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie( String categorie ) {
        this.categorie = categorie;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Override
    public String toString() {

        return idArticle + ": " + name + " -> " + categorie + " at " + when.toString();
    }
}