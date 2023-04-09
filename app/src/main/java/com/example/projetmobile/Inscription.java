package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * this class allows to register a user in the database
 */
public class Inscription extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;
    private EditText User, Mdp;
    DBHandler db;

    /**
     * This method is called when the activity is created.
     * It allows to make the link between data and displays.
     * It instantiates all the objects and variables necessary
     * for the of the activity.
     *
     * @param savedInstanceState is the state of the saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar myToolBar3 = (Toolbar) findViewById(R.id.my_toolbar3);
        setSupportActionBar(myToolBar3);
        IntentConnexion = new Intent(Inscription.this, Connexion.class);
        IntentInscription = new Intent(Inscription.this, Inscription.class);
        db = new DBHandler(this);
        User = (EditText) findViewById(R.id.User);
        Mdp = (EditText) findViewById(R.id.Mdp);
    }

    /**
     * This method makes the link with the layout used for the menu
     *
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
     *
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

    /**
     * This method allows to manage the event when clicking
     *
     * @param view is the view
     */
    public void add(View view) {
        //méthode appelée lors du clic sur le bouton valider
        String user = User.getText().toString();
        String mdp = Mdp.getText().toString();
        if (user.isEmpty() || mdp.isEmpty()) {
            Toast.makeText(Inscription.this, "Identifiant ou mot de passe invalide", Toast.LENGTH_LONG).show();
        } else {
            try {
                db.insertSession(user, mdp);
                if (!user.isEmpty() && !mdp.isEmpty() && (db.selectByUser(user, mdp)).size() > 0) {
                    startActivity(IntentConnexion);
                }
            } catch (SQLiteConstraintException e) {
                Log.e("ERROR", "Cet utilisateur existe déjà");
                Toast.makeText(Inscription.this, "Cet utilisateur existe déjà.", Toast.LENGTH_LONG).show();
            }
        }
    }

    /*private boolean checkUserExists(String user, String password) {
        boolean exists = false;
        if (!user.isEmpty() && !password.isEmpty()) {
            String hashedPassword = db.getHashedPassword(password);
            if (db.selectByUser(user, hashedPassword).size() > 0) {
                exists = true;
            }
        }
        return exists;
    }*/

    /**
     * this method frees the resources used by the activity, when it is destroyed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}