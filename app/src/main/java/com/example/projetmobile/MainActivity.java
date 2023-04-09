package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

/**
 * this class is the main class. It allows to launch the first activity
 */
public class MainActivity extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;
    Intent intent;
    private EditText searchEditText;
    private Button searchButton;

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
        setContentView(R.layout.activity_main);
        Toolbar myToolBar =(Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);
        IntentConnexion=new Intent(MainActivity.this,Connexion.class);
        IntentInscription=new Intent(MainActivity.this,Inscription.class);

        searchEditText = findViewById(R.id.searchView);
        searchButton = findViewById(R.id.search_button);

        intent=new Intent(MainActivity.this, page2_resultatBarreRecherche.class);
    }

    /**
     * This method allows to manage the event when clicking
     * @param v is the view
     */
    public void onClick(View v) {
        // Code à exécuter lors du clic sur le bouton de recherche
        String searchTitle = searchEditText.getText().toString();
        intent.putExtra("search_edit_text", searchTitle);
        startActivity(intent);
    }

    /**
     * This method makes the link with the layout used for the menu
     * @param menu Specifies the menu file to be considered
     * @return True if the link with the menu is well established
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
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
}