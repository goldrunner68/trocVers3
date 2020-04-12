package com.example.myapplicationtroc.Article;

import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplicationtroc.R;

import java.util.ArrayList;

public class TrocDisplayActivity extends AppCompatActivity {
        private ArrayList<Troc> mesTroc;
        private ListView mMesTrocV;//m(variable)debut et fin V(View)
    private TrocAdaptateur trocAdaptateur;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troc_display);

        mMesTrocV = (ListView)findViewById(R.id.mesTroc);
        trocAdaptateur = new TrocAdaptateur(getApplicationContext(),0);
        mesTroc = new ArrayList<>();
        mesTroc.add(new Troc("table","Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","neuf"));
        mesTroc.add(new Troc("chaise","Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","occasion"));
        mesTroc.add(new Troc("canape","Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","occasion"));
        mesTroc.add(new Troc("Television","Lorem Ipsum est tout simplement un texte factice de l'industrie de l'impression et de la composition","neuf"));
   // j affecte mon addapteur a ma liste
        mMesTrocV.setAdapter(trocAdaptateur);
        trocAdaptateur.addAll(mesTroc);
    }
}
