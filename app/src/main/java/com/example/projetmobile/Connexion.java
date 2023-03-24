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

public class Connexion extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;
    Intent mainActivityConnecter;
    private EditText User, Mdp;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        Toolbar myToolBar2 =(Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolBar2);
        IntentConnexion=new Intent(Connexion.this,Connexion.class);
        IntentInscription=new Intent(Connexion.this, Inscription.class);
        mainActivityConnecter=new Intent(Connexion.this,MainActivityConnecter.class);
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

    public void connexion(View view) {
        //méthode appelée lors du clic sur le bouton valider
        String userDonner=User.getText().toString();
        String mdpDonner=Mdp.getText().toString();
        CoupleId couple=new CoupleId(userDonner, mdpDonner);
        if(couple==db.selectByUser(userDonner)){
            startActivity(mainActivityConnecter);
        }
        else {
            Toast.makeText(Connexion.this, "Identifiant ou mot de passe invalide", Toast.LENGTH_LONG).show();
        }

    }
}