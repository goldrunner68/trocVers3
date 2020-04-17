package com.example.myapplicationtroc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.myapplicationtroc.article.TrocDisplayActivity;
import com.example.myapplicationtroc.bddManager.TrocManager;
import com.example.myapplicationtroc.bddManager.TrocTable;

import java.io.File;

public class AddTrocActivity extends AppCompatActivity {


    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 99;
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    private static final int REQUEST_ID_VIDEO_CAPTURE = 101;
    private static final String TAG = "tag";
    private VideoView videoView;
    private Button idPrendrePhoto;
    private Button idFileImage;
    private ImageView imageView20;
    private EditText idEditTitreDuTroc, idEditDescription, idEditEtat;
    private Button idAjouterTroc;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_troc);
        //j instancie mes buttons
        this.idPrendrePhoto = (Button) this.findViewById(R.id.idPrendrePhoto);
        this.idFileImage = (Button) this.findViewById(R.id.idfileImage);
        this.imageView20 = (ImageView) this.findViewById(R.id.imageView20);
        this.idEditTitreDuTroc = (EditText) this.findViewById(R.id.idEditTitreDuTroc);
        this.idEditDescription = (EditText) this.findViewById(R.id.idEditDescription);
        this.idEditEtat = (EditText) this.findViewById(R.id.idEditEtat);
        this.idAjouterTroc = (Button) this.findViewById(R.id.idAjouterTroc);
        // j ecoute mes Buttons car il attend un evenement
        idPrendrePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// ouvre la camera si clic sur btn camera
                captureImage();

            }
        });
        // j ecoute mes Buttons Ajouter image ,description titre dans la bdd
        idAjouterTroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// ouvre la camera si clic sur btn camera
                ajouterToutBdd();

            }
        });
    }
    private void ajouterToutBdd() {
        TrocManager m = new TrocManager(this); // gestionnaire de la table "animal"
        m.open(); // ouverture de la table en lecture/écriture

// insertion. L'id sera attribué automatiquement par incrément
        String titre = idEditTitreDuTroc.getText().toString();
        String descrip = idEditDescription.getText().toString();
        String etat = idEditEtat.getText().toString();
        String img = imageView20.getDrawable().toString();
        ;
        // m.addTroc(new Troc(titre,img2,descrip,etat));
        m.addTroc(new TrocTable(0 , titre , descrip , img , etat));
        m.close();
        startActivity(new Intent(AddTrocActivity.this , TrocDisplayActivity.class));
    }

    private void captureImage() {
        // Create an implicit intent, for image capture.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Start camera and wait for the results.
        this.startActivityForResult(intent , REQUEST_ID_IMAGE_CAPTURE);
    }










}
