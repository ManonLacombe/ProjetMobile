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

public class MainActivity extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;

    private EditText searchEditText;
    private Button searchButton;


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
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code à exécuter lors du clic sur le bouton de recherche
                String searchTitle = searchEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, page2_resultatBarreRecherche.class);
                intent.putExtra("searchTitle", searchTitle);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

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