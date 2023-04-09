package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityConnecter extends AppCompatActivity {

    Intent IntentRetour;
    private WebView web;
    MyReceiver myReceiver;
    private EditText searchEditText;
    private Button searchButton;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_connecter);
        Toolbar myToolBar4 =(Toolbar) findViewById(R.id.my_toolbar4);
        setSupportActionBar(myToolBar4);
        myReceiver=new MyReceiver();
        web=(WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://www.cgrcinemas.fr/films-a-l-affiche#");
        web.setWebViewClient(new WebViewClient());
        searchEditText = findViewById(R.id.searchView);
        searchButton = findViewById(R.id.search_button);
        intent=new Intent(MainActivityConnecter.this, page2_resultatBarreRechercheConnecter.class);
        IntentRetour=new Intent(MainActivityConnecter.this, MainActivity.class);

    }

    public void onClick(View v) {
        // Code à exécuter lors du clic sur le bouton de recherche
        String searchTitle = searchEditText.getText().toString();
        intent.putExtra("search_edit_text", searchTitle);
        startActivity(intent);
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
                startActivity(IntentRetour);
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
