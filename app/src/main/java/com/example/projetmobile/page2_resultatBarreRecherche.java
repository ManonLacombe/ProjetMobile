package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class page2_resultatBarreRecherche extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;

    ArrayList<Movie> finalResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_resultat_barre_recherche);
        //Toolbar myToolBar =(Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolBar);
        IntentConnexion=new Intent(page2_resultatBarreRecherche.this,Connexion.class);
        IntentInscription=new Intent(page2_resultatBarreRecherche.this,Inscription.class);
        finalResponse=new ArrayList<Movie>();
        Intent in = getIntent();
        String nom = String.valueOf(in.getStringExtra("searchView"));
        RequestTask rt=new RequestTask();
        rt.execute(nom);
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

    private class RequestTask extends AsyncTask<String, Void, ArrayList<Movie>> {
        @Override
        protected ArrayList<Movie> doInBackground(String... nom){
            ArrayList<Movie> response = new ArrayList<>();
            try {
                HttpURLConnection connection = null;
                URL url = new URL("https://search.imdbot.workers.dev/?q=" + URLEncoder.encode(String.valueOf(nom[0]), "utf-8"));
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String totalLine = "";
                String ligne = bufferedReader.readLine();
                while (ligne != null) {
                    totalLine += ligne;
                    ligne = bufferedReader.readLine();
                }
                JSONObject toDecode = new JSONObject(totalLine);
                // Decode l'objet JSON et récupère le ArrayList
                response = decodeJSON(toDecode);
            } catch (UnsupportedEncodingException e) {
                Log.e("ERROR", "problème d'encodage");
            } catch (MalformedURLException e) {
                Log.e("ERROR", "problème d'url");
            } catch (IOException e) {
                Log.e("ERROR", "problème d'entrée sortie");
            } catch (Exception e) {
                Log.e("ERROR", "autre erreur");
            }
            return response;
        }

        private ArrayList<Movie> decodeJSON(JSONObject jso) throws Exception{
            ArrayList<Movie> response = new ArrayList<>();
            String jsocod = jso.getString("ok");
            if (jsocod.equals("true")) {
                JSONArray jsoMovie = jso.getJSONArray("description");
                for (int i = 0; i < jsoMovie.length(); i++){
                    Spanned title , image ;
                    title = (Html.fromHtml(jsoMovie.getJSONObject(i).getString("#TITLE"), Html.FROM_HTML_MODE_LEGACY));
                    image = (Html.fromHtml(jsoMovie.getJSONObject(i).getString("#IMG_POSTER"), Html.FROM_HTML_MODE_LEGACY));
                    Movie question = new Movie(title.toString(),image.toString());
                    response.add(question);
                }
            } else {
                Log.e("ERROR","\n Code erreur retourné par le serveur : \n\n \t Code = " + jsocod + "\n\n \t Message : " + jso.getString("message"));
            }
            return response;
        }

        private void generateTextViewTitle(String title, int index, LinearLayout layout){
            TextView t;
            t = new TextView(getApplicationContext());
            t.setText(title);
            t.setTextColor(Color.WHITE);
            t.setId(index);
            layout.addView(t);
        }

        private  void generateImageViewImage(String image, int index, LinearLayout layout){
            ImageView i = new ImageView(getApplicationContext());
            i.setImageURI(Uri.parse(image));
            i.setId(index);
            layout.addView(i);
        }

        protected void onPostExecute(ArrayList<Movie> result) {
            if (result.size()>=1){
                RecyclerViewMovieAdapter myAdapter;
                RecyclerView recycler = (RecyclerView) findViewById(R.id.search_result_recycler_view);
                recycler.setLayoutManager(new LinearLayoutManager(page2_resultatBarreRecherche.this));
                myAdapter = new RecyclerViewMovieAdapter(result);
                recycler.setAdapter(myAdapter);
                recycler.addItemDecoration(new DividerItemDecoration(page2_resultatBarreRecherche.this, DividerItemDecoration.HORIZONTAL));
            }
        }
    }

}