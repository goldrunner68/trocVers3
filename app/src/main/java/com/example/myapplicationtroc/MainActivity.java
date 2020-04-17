package com.example.myapplicationtroc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplicationtroc.bddManager.TrocManager;


public class MainActivity extends AppCompatActivity {

    private Button idConnexion;
    private Button idInscription;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //j instancie mes buttons
        this.idConnexion = (Button) this.findViewById(R.id.idConnexion);
        this.idInscription = (Button) this.findViewById(R.id.idInscription);

        // j ecoute mes Buttons car il attend un evenement
        idConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// depart de page,  vers l autre page
                startActivity(new Intent(MainActivity.this , ConnexionActivity.class));
            }
        });

        // j ecoute mes Buttons car il attend un evenement
        idInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// depart de page,  vers l autre page
                startActivity(new Intent(MainActivity.this , InscriptionActivity.class));
            }
        });

        TrocManager trocManager = new TrocManager(this); // gestionnaire de la table "troc"
        trocManager.open(); // ouverture de la table en lecture/écriture


    }
}
