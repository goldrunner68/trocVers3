package com.example.myapplicationtroc.Article;

import android.database.Cursor;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplicationtroc.R;
import com.example.myapplicationtroc.bddManager.TrocManager;
import android.database.Cursor;
import java.util.ArrayList;

public class TrocDisplayActivity extends AppCompatActivity {
        private ArrayList<Troc> mesTroc;
        private ListView mMesTrocV;//m(variable)debut et fin V(View)
    private TrocAdaptateur trocAdaptateur;
    private TrocManager trocManager;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troc_display);

        mMesTrocV = (ListView)findViewById(R.id.mesTroc);
        trocAdaptateur = new TrocAdaptateur(getApplicationContext(),0);
        mesTroc = new ArrayList<>();

        TrocManager trocManager = new TrocManager(this); // gestionnaire de la table "troc"
        trocManager.open(); // ouverture de la table en lecture/écriture
        Cursor c = trocManager.getTroc();
        if (c.moveToFirst())
        {
            do {

                String leTitre = c.getString(c.getColumnIndex(TrocManager.KEY_TITRE));
                String limage = c.getString(c.getColumnIndex(TrocManager.KEY_IMAGE));
                String leDescriptif = c.getString(c.getColumnIndex(TrocManager.KEY_DESCRIPTIF));

                String letat = c.getString(c.getColumnIndex(TrocManager.KEY_ETAT));


                        mesTroc.add(new Troc(leTitre ,R.drawable.limage , leDescriptif , letat));

            }
            while (c.moveToNext());
        }
       //mesTroc.add(new Troc("table",R.drawable.troclogo,"Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","neuf"));
       //mesTroc.add(new Troc("chaise",R.drawable.troclogo,"Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","occasion"));
       //mesTroc.add(new Troc("canape",R.drawable.troclogo,"Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","occasion"));
       //mesTroc.add(new Troc("Television",R.drawable.troclogo,"Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","neuf"));
   // j affecte mon addapteur a ma liste
        mMesTrocV.setAdapter(trocAdaptateur);
        trocAdaptateur.addAll(mesTroc);
    }
}
