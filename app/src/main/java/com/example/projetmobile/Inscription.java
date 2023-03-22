package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Inscription extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar myToolBar3 =(Toolbar) findViewById(R.id.my_toolbar3);
        setSupportActionBar(myToolBar3);
        IntentConnexion=new Intent(Inscription.this,Connexion.class);
        IntentInscription=new Intent(Inscription.this,Inscription.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu3, menu);
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