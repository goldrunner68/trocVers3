package com.example.myapplicationtroc.article;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplicationtroc.AddTrocActivity;
import com.example.myapplicationtroc.ProfilActivity;
import com.example.myapplicationtroc.R;
import com.example.myapplicationtroc.bddManager.TrocManager;
import com.example.myapplicationtroc.bddManager.TrocTable;

import java.util.ArrayList;

public class TrocDisplayActivity extends AppCompatActivity {
    private ArrayList<TrocList> mesTrocList;
    private ListView mMesTrocV;//m(variable)debut et fin V(View)
    private TrocAdaptateur trocAdaptateur;
    private TrocManager trocManager;
    private Button idAdd;
    private Button idprofil;
    private CheckBox idcheckbox;



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troc_display);
        this.idAdd = (Button) this.findViewById(R.id.idAdd);
        this.mMesTrocV = (ListView) this.findViewById(R.id.mesTroc);
        this.idprofil = (Button) this.findViewById(R.id.idprofil);

        trocAdaptateur = new TrocAdaptateur(getApplicationContext() , 0);
        mesTrocList = new ArrayList<>();
        // j ecoute mes Buttons car il attend un evenement
        idAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// depart de page,  vers l autre page
                startActivity(new Intent(TrocDisplayActivity.this , AddTrocActivity.class));
            }
        });
        idprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// depart de page,  vers l autre page
                startActivity(new Intent(TrocDisplayActivity.this , ProfilActivity.class));
            }
        });

        TrocManager trocManager = new TrocManager(this); // gestionnaire de la table "troc"
        trocManager.open(); // ouverture de la table en lecture/écriture

        Cursor c = trocManager.getTroc();
        if (c.moveToFirst()) {
            do {
                // je lis les donnees de ma  BDD comme en PHP
                String leTitre = c.getString(c.getColumnIndex(TrocManager.KEY_TITRE));
                String limage = c.getString(c.getColumnIndex(TrocManager.KEY_IMAGE));
                String leDescriptif = c.getString(c.getColumnIndex(TrocManager.KEY_DESCRIPTIF));
                String letat = c.getString(c.getColumnIndex(TrocManager.KEY_ETAT));

                // cast mon String limage en int , car un drawable attend un INT
                // attention ne pas mettre d extention jpg ou autre dans la BDD pour les noms de fichiers
                int img = getResources().getIdentifier(limage , "drawable" , getPackageName());
                System.out.println(img);
                //je les ajoutes dans l arraylist(listView)
                mesTrocList.add(new TrocList("Type de produit : " + leTitre , img , "Descriptif : " + leDescriptif , "Etat du produit : " + letat));

               // trocManager.supTroc(trocTable.getId_troc());


            }
            while (c.moveToNext());
        }

        // version sans BDD en dur
        //mesTroc.add(new Troc("table",R.drawable.troclogo,"Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","neuf"));
        //mesTroc.add(new Troc("chaise",R.drawable.troclogo,"Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","occasion"));
        //mesTroc.add(new Troc("canape",R.drawable.troclogo,"Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","occasion"));
        //mesTroc.add(new Troc("Television",R.drawable.troclogo,"Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","neuf"));

        // j affecte mon addapteur a ma liste
        mMesTrocV.setAdapter(trocAdaptateur);
        trocAdaptateur.addAll(mesTrocList);
    }
}
