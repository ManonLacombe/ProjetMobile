package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class page3_descriptif_film_methode extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3_descriptif_film);
        Toolbar myToolBar6 =(Toolbar) findViewById(R.id.my_toolbar6);
        setSupportActionBar(myToolBar6);
        IntentConnexion=new Intent(page3_descriptif_film_methode.this,Connexion.class);
        IntentInscription=new Intent(page3_descriptif_film_methode.this,Inscription.class);
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