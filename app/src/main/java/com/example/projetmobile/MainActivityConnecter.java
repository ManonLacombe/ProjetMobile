package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivityConnecter extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;
    private WebView web;
    MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_connecter);
        Toolbar myToolBar4 =(Toolbar) findViewById(R.id.my_toolbar4);
        setSupportActionBar(myToolBar4);
        IntentConnexion=new Intent(MainActivityConnecter.this,Connexion.class);
        IntentInscription=new Intent(MainActivityConnecter.this,Inscription.class);
        myReceiver=new MyReceiver();
        web=(WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://www.cgrcinemas.fr/films-a-l-affiche#");
        web.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.deconnexion:
                startActivity(IntentConnexion);
                return (true);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(myReceiver, filter);
    }

    @Override
    public void onStop(){
        super.onStop();
        unregisterReceiver(myReceiver);
    }
}
