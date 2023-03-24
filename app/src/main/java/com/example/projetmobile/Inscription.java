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

public class Inscription extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;

    private EditText User, Mdp;
    DBHandler db;

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