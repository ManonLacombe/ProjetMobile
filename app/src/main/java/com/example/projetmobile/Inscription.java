package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * cette classe permet d'inscrire un utilisateur dans la base de données
 */
public class Inscription extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;
    private EditText User, Mdp;
    DBHandler db;

    /**
     * Cette méthode est appelée lors de la
     * création de l’activité. Elle
     * permet de faire le lien entre données et
     * affichages. Elle instancie tous les
     * objets et variables nécéssaires au
     * fonctionnement de l’activité.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar myToolBar3 =(Toolbar) findViewById(R.id.my_toolbar3);
        setSupportActionBar(myToolBar3);
        IntentConnexion=new Intent(Inscription.this,Connexion.class);
        IntentInscription=new Intent(Inscription.this,Inscription.class);
        db = new DBHandler(this);
        User=(EditText) findViewById(R.id.User);
        Mdp=(EditText) findViewById(R.id.Mdp);
    }

    /**
     * Cette méthode fait le lien avec le layout
     * utilisé pour le menu
     * @param menu
     * @return true si le lien avec le menu s'est bien fait
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    /**
     * cette méthode permet de rediriger vers une
     * autre page selon l'item sélectionnée
     * @param item est l'item du menu
     * @return vrai si la redirection vers la page sélectionnée se passe bien
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.connexion:
                startActivity(IntentConnexion);
                return (true);
            case R.id.inscription:
                startActivity(IntentInscription);
                return (true);
        }
        return true;
    }

    /**
     * 
     * @param view
     */
    public void add(View view) {
        //méthode appelée lors du clic sur le bouton valider
        String user=User.getText().toString();
        String mdp=Mdp.getText().toString();
        db.insertSession(user, mdp);
        // à placer dans le clic:  Toast.makeText(Inscription.this, "Inscription Validé", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}