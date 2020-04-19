package com.example.myapplicationtroc;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplicationtroc.article.TrocDisplayActivity;
import com.example.myapplicationtroc.bddManager.TrocManager;
import com.example.myapplicationtroc.bddManager.TrocTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class AddTrocActivity extends AppCompatActivity {



    private static final String TAG = "tag";
    private static final int CAMERA_REQUEST = 1;
    private VideoView videoView;
    private Button idPrendrePhoto;
    private Button idFileImage;
    private ImageView imageView20;
    private EditText idEditTitreDuTroc, idEditDescription, idEditEtat;
    private Button idAjouterTroc;
    private AddTrocActivity addTrocActivity;

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

        // j ecoute mes Buttons Ajouter image ,description titre dans la bdd
        idAjouterTroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// ouvre la camera si clic sur btn camera
                ajouterToutBdd();

            }
        });

        // j ecoute mes Buttons car il attend un evenement
        idPrendrePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {// capture de l image
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    });

}// affiche l image l activity actuelle, avec son request code 1
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode , data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView20.setImageBitmap(bitmap);
            try {
                File sdCard = getExternalFilesDir(Environment.DIRECTORY_DCIM);
                assert sdCard != null;
                File dir = new File(sdCard.getAbsolutePath() + "/camtest");
                dir.mkdirs();
                @SuppressLint("DefaultLocale") String fileName = String.format("%d.jpg", System.currentTimeMillis());
                File outFile = new File(dir, fileName);

                FileOutputStream outStream = new FileOutputStream(outFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                outStream.flush();
                outStream.close();
                Log.d(TAG, "photo prise et sauvegarder dans " + outFile.getAbsolutePath());


            } catch (IOException e) {

                e.printStackTrace();
            } finally {
            }
        }
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






        }
