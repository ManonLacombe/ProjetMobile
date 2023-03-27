package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
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

    private RecyclerView searchResultRecyclerView;
    private SearchResultAdapter searchResultAdapter;

    ArrayList<Movie> finalResponse;

    /*
     * La méthode de base de création de l'activité
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolBar =(Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);
        IntentConnexion=new Intent(page2_resultatBarreRecherche.this,Connexion.class);
        IntentInscription=new Intent(page2_resultatBarreRecherche.this,Inscription.class);

        setContentView(R.layout.activity_page2_resultat_barre_recherche);
        finalResponse = new ArrayList<Movie>();
        Intent in = getIntent();
        String Recherche = in.getStringExtra("Movie");
        RequestTask rt = new RequestTask();
        rt.execute(Recherche);

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

    /*
     * La classe interne asynchrone permet la réalisation de la requête HTTP et le traitement des donénes reçues
     * Prend en entrée un String qui est le titre de la recherche à générer.
     * Retourne un ArrayList contenant la recherche et ces réponses.
     */
    private class RequestTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        /*
         * Lance la tâche asynchrone
         * @param titre du film à générer
         * @return un array list avec les titres et les réponses
         */
        @Override
        protected ArrayList<Movie> doInBackground(String... recherche) {
            ArrayList<Movie> response = new ArrayList<>();
            try {
                //requete HTTP
                //récupération des données
                HttpURLConnection connection = null;
                URL url = new URL("https://search.imdbot.workers.dev/?q="+
                        URLEncoder.encode(String.valueOf(recherche[0]),"utf-8"));
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

        //décodage du JSON et retourne la chaîne de caractère à afficher
        /*
         * Méthode qui décode l'objet JSON
         * Extrait la recherche et les réponses et les ajoute à l'arrayList
         * @param jso L'objet JSON
         * @return ArrayList<Movie>
         * @throws Exception
         */

        private ArrayList<Movie> decodeJSON(JSONObject jso) throws Exception{
            ArrayList<Movie> response = new ArrayList<>();
            int jsocod = jso.getInt("response_code");
            if (jsocod == 0) {
                JSONArray jsorecherche = jso.getJSONArray("results");
                for (int i = 0; i < jsorecherche.length(); i++){
                    Spanned title , posterUrl ;
                    title = (Html.fromHtml(jsorecherche.getJSONObject(i).getString("recherche"), Html.FROM_HTML_MODE_LEGACY));
                    posterUrl = (Html.fromHtml(jsorecherche.getJSONObject(i).getString("reponse_Recherche"), Html.FROM_HTML_MODE_LEGACY));
                    Movie movie1 = new Movie(title.toString(),posterUrl.toString());
                    response.add(movie1);
                }
            } else {
                Log.e("ERROR","\n Code erreur retourné par le serveur : \n\n \t Code = " + jsocod + "\n\n \t Message : " + jso.getString("message"));
            }
            return response;
        }

        /*
         * Méthode qui va être appelée à la fin de la requête asynchrone.
         * Génère les TextView et EditText sur la base des données reçues
         * @param result
         */
        protected void onPostExecute(ArrayList<Movie> result) {
            if (result.size()>1){
                SearchResultAdapter myAdapter;
                RecyclerView recycler = (RecyclerView)
                        findViewById(R.id.search_result_recycler_view);
                recycler.setLayoutManager(new
                        LinearLayoutManager(page2_resultatBarreRecherche.this));
                myAdapter = new SearchResultAdapter(result);
                recycler.setAdapter(myAdapter);
            }
        }


        /*
         * Génère un textview contenant un titre
         * @param titre  le titre
         * @param index l'id
         * @param layout le layout dans lequel on va inclure ce textview
         */
        private void generateTextViewTitle (String title, int index, LinearLayout layout){
            TextView t;
            t = new TextView(getApplicationContext());
            t.setText(title);
            t.setId(index);
            layout.addView(t);
        }

        /*
         * Génère une zone de texte dans laquel on affichera la réponse de la recherche (Image film)
         * @param index l'id
         * @param layout le layout dans lequel on veut inclure la zone
         */
        private void generateTextViewPostURL(String PostURL, int i, LinearLayout layout) {
            TextView t;
            t = new TextView(getApplicationContext());
            t.setText(PostURL);
            t.setId(i);
            layout.addView(t);
        }
    }

}