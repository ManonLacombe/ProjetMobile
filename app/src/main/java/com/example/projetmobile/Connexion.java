package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Définis les méthodes pour se connecter en tant qu'utilisateur
 */
public class Connexion extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;

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
        setContentView(R.layout.activity_connexion);
        Toolbar myToolBar2 = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolBar2);
        IntentConnexion = new Intent(Connexion.this, Connexion.class);
        IntentInscription = new Intent(Connexion.this, Inscription.class);
    }

    /**
     * Cette méthode fait le lien avec le layout
     * utilisé pour le menu
     * @param menu
     * @return true si le lien avec le menu s'est bien fait
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.connexion:
                startActivity(IntentConnexion);
                return (true);
            case R.id.inscription:
                startActivity(IntentInscription);
                return (true);
        }
        return true;
    }
}