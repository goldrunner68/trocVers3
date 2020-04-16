package com.example.myapplicationtroc.bddManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersManager {

    private static final String TABLE_USERS = "users";
    public static final String KEY_ID_USERS = "id_users";
    public static final String KEY_NOM = "nom";
    public static final String KEY_PRENOM = "prenom";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PSEUDO = "pseudo";

    // attention a la virgule apres l avant dernier "TEXT" sinon genere erreur
    // has no column named image
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS +
            " (" +
            " " + KEY_ID_USERS + " INTEGER primary key," +
            " " + KEY_NOM + " TEXT," +
            " " + KEY_PRENOM + " TEXT," +
            " " + KEY_EMAIL + " TEXT," +
            " " + KEY_PSEUDO + " TEXT" +
            ");";

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public UsersManager( Context context ) {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open() {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addUsers( UsersTable usersTable ) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM , usersTable.getNom());
        values.put(KEY_PRENOM , usersTable.getPrenom());
        values.put(KEY_EMAIL , usersTable.getEmail());
        values.put(KEY_PSEUDO , usersTable.getPseudo());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_USERS , null , values);
    }

    public int modUsers( UsersTable usersTable ) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM , usersTable.getNom());
        values.put(KEY_PRENOM , usersTable.getPrenom());
        values.put(KEY_EMAIL , usersTable.getEmail());
        values.put(KEY_PSEUDO , usersTable.getPseudo());

        String where = KEY_ID_USERS + " = ?";
        String[] whereArgs = {usersTable.getId_users() + ""};

        return db.update(TABLE_USERS , values , where , whereArgs);
    }

    public int supUsers( UsersTable usersTable ) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_USERS + " = ?";
        String[] whereArgs = {usersTable.getId_users() + ""};

        return db.delete(TABLE_USERS , where , whereArgs);
    }

    public UsersTable getUsers( int id ) {
        // Retourne le USERS dont l'id est passé en paramètre

        UsersTable a = new UsersTable(0 , "" , "" , "" , "");

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ID_USERS + "=" + id , null);
        if (c.moveToFirst()) {
            a.setId_users(c.getInt(c.getColumnIndex(KEY_ID_USERS)));
            a.setNom(c.getString(c.getColumnIndex(KEY_NOM)));
            a.setPrenom(c.getString(c.getColumnIndex(KEY_PRENOM)));
            a.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
            a.setPseudo(c.getString(c.getColumnIndex(KEY_PSEUDO)));

            c.close();
        }

        return a;
    }

    public Cursor getUsers() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM " + TABLE_USERS , null);
    }

}