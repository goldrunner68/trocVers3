package com.example.myapplicationtroc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.example.myapplicationtroc.article.TrocDisplayActivity;
import com.example.myapplicationtroc.article.TrocList;
import com.example.myapplicationtroc.bddManager.TrocManager;
import com.example.myapplicationtroc.bddManager.TrocTable;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class AddTrocActivity extends AppCompatActivity {


    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 99;
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    private static final int REQUEST_ID_VIDEO_CAPTURE = 101;
    private static final String TAG = "tag" ;
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
       this.idAjouterTroc =(Button) this.findViewById(R.id.idAjouterTroc);
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
      String titre =idEditTitreDuTroc.getText().toString();
        String descrip =idEditDescription.getText().toString();
        String etat =idEditEtat.getText().toString();
        String img = imageView20.getDrawable().toString();
       ;
     // m.addTroc(new Troc(titre,img2,descrip,etat));
      m.addTroc(new TrocTable(0,titre,descrip,img,etat));
      m.close();
        startActivity(new Intent(AddTrocActivity.this , TrocDisplayActivity.class));
    }

    private void captureImage() {
        // Create an implicit intent, for image capture.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Start camera and wait for the results.
        this.startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
    }

    private void askPermissionAndCaptureVideo() {

        // With Android Level >= 23, you have to ask the user
        // for permission to read/write data on the device.
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            // Check if we have read/write permission
            int readPermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (writePermission != PackageManager.PERMISSION_GRANTED ||
                    readPermission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE ,
                                Manifest.permission.READ_EXTERNAL_STORAGE} ,
                        REQUEST_ID_READ_WRITE_PERMISSION
                );
                return;
            }}

    }


    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case REQUEST_ID_READ_WRITE_PERMISSION: {

                // Note: si la requete est refuse, le resultat est vide.
                // Permission lecture ecriture
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission accordée!", Toast.LENGTH_LONG).show();

                }
                // Permission refusée ou non
                else {
                    Toast.makeText(this, "Permission refusée!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ID_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                this.imageView20.setImageBitmap(bp);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action annulée", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Petite érreur", Toast.LENGTH_LONG).show();
            }
        }
    }
    //getPhotoFileUri : cette fonction, qui prend un paramètre une String correpondant au nom du fichier, va nous retourner un
    // URI vers l'image prise et stockée la photo sur le disque avec le nom du fichier.

    public Uri getPhotoFile( String fileName){
if(isExternalStorageAvailable()){
    File mediaStorageDir = new File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);
    if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
        Log.d(TAG , "erreur de creation du repertoire");
    }
    return Uri.fromFile(new File(mediaStorageDir.getPath()+ File.separator + fileName));

}
return null;
    }
    //isExternalStorageAvailable : cette méthode nous permet de savoir
    // si un stockage externe pour les photos est disponible
        private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
        }

}
