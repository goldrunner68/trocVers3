package com.example.myapplicationtroc.bddManager;

public class UsersTable {

    private int id_users;
    private String nom;
    private String prenom;
    private String email;
    private String pseudo;

    public UsersTable( int id_users , String nom , String prenom , String email , String pseudo ) {
        this.id_users = id_users;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pseudo = pseudo;


    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users( int id_users ) {
        this.id_users = id_users;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }
}