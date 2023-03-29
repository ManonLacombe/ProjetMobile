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
 * Defines methods for logging in as a user
 */
public class Connexion extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;

    /**
     * This method is called when the activity is created. It makes the link
     * between data and display. It instantiates all the objects and variables
     * necessary for the running of the activity.
     *
     * @param savedInstanceState Refers to the saving of the state of the instance
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
     * This method makes the link with the layout used for the menu
     * @param menu Specifies the menu file to be considered
     * @return True if the link with the menu is well established
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    /**
     * This method allows redirecting to another page depending on the selected item
     * @param item is the menu item
     * @return True if the redirection to the selected page was successful
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