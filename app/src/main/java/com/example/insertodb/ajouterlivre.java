package com.example.insertodb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ajouterlivre extends AppCompatActivity {
    EditText titreLivre,desLivre,prixLivre;
    Button ajouterLivre;
    private DB_Helper db_Helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouterlivre);

        db_Helper = new DB_Helper(getApplicationContext());
        titreLivre = findViewById(R.id.Livre_Titre);
        desLivre = findViewById(R.id.Livre_Description);
        prixLivre = findViewById(R.id.Livre_prix);
        ajouterLivre = findViewById(R.id.Livre_Ajouter);


        ajouterLivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monTitre = titreLivre.getText().toString();
                String monDes = desLivre.getText().toString();
                String monPrix = prixLivre.getText().toString();

                db_Helper.ajouterLivre(new Livre(monTitre,monDes,monPrix));
            }
        });
    }
}