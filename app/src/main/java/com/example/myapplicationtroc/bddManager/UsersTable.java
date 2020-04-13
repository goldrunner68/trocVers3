package com.example.myapplicationtroc.bddManager;

public class UsersTable {

    private int id_Users;
    private  String nom;
    private String prenom;
    private String email;
    private String pseudo;

    public UsersTable( int id_Users , String nom , String prenom , String pseudo , String email ) {
        this.id_Users = id_Users;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;

    }

    public int getId_Users() {
        return id_Users;
    }

    public void setId_Users( int id_Users ) {
        this.id_Users = id_Users;
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