package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

/**
 * this class allows to generate a list of movies corresponding to the search
 */
public class page2_resultatBarreRecherche extends AppCompatActivity {

    Intent IntentConnexion;
    Intent IntentInscription;
    private Button retourPagePrincipale;
    Intent IntentRetour;
    private TextView title;

    private RecyclerView searchResultRecyclerView;
    private SearchResultAdapter searchResultAdapter;

    ArrayList<Movie> finalResponse;

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
        setContentView(R.layout.activity_page2_resultat_barre_recherche);
        Toolbar myToolBar5 =(Toolbar) findViewById(R.id.my_toolbar5);
        setSupportActionBar(myToolBar5);
        IntentConnexion=new Intent(page2_resultatBarreRecherche.this,Connexion.class);
        IntentInscription=new Intent(page2_resultatBarreRecherche.this,Inscription.class);

        finalResponse = new ArrayList<Movie>();
        Intent in = getIntent();
        String Recherche = String.valueOf(in.getStringExtra("search_edit_text"));
        TextView t=findViewById(R.id.SearchResult);
        t.setText(Recherche);
        RequestTask rt = new RequestTask();
        rt.execute(Recherche);

        // Création de l'instance du RecyclerView
        RecyclerView searchResultRecyclerView = findViewById(R.id.search_result_recycler_view);
        // Création de l'instance de SearchResultAdapter en passant les données
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(finalResponse);

        // Assignation de l'instance de SearchResultAdapter au RecyclerView
        searchResultRecyclerView.setAdapter(searchResultAdapter);

        title=(TextView) findViewById(R.id.SearchResult);
        IntentRetour=new Intent(page2_resultatBarreRecherche.this, MainActivity.class);

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

    /**
     * This method allows to manage the event when clicking
     * @param v is the view
     */
    public void retour(View v) {
        if(v.getId()==R.id.retourPagePrincipale){
            startActivity(IntentRetour);
        }
    }

    /**
     * The internal asynchronous class allows the realization of the HTTP request and the processing of the received data
     * Takes as input a String which is the title of the search to be produced.
     * Returns an ArrayList containing the search and these responses.
     */
    private class RequestTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        /**
         * this method launches the asynchronous task
         * @param recherche is the title of the film to generate
         * @return an array list with titles and the answers
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

        /**
         * this method decodes the JSON object
         * Extract search and answers and add them to the arrayList
         * @param jso is the JSON object
         * @return an ArrayList of movies
         * @throws Exception is the exception thrown if the request returns an error code
         */
        private ArrayList<Movie> decodeJSON(JSONObject jso) throws Exception{
            ArrayList<Movie> response = new ArrayList<>();
            String jsocod = jso.getString("ok");
            if (jsocod.equals("true")) {
                JSONArray jsorecherche = jso.getJSONArray("description");
                for (int i = 0; i < jsorecherche.length(); i++){
                    Spanned title , posterUrl;
                    title = (Html.fromHtml(jsorecherche.getJSONObject(i).getString("#TITLE"), Html.FROM_HTML_MODE_LEGACY));
                    posterUrl = (Html.fromHtml(jsorecherche.getJSONObject(i).getString("#IMG_POSTER"), Html.FROM_HTML_MODE_LEGACY));
                    Movie movie = new Movie(title.toString(),posterUrl.toString());
                    response.add(movie);
                }
            } else {
                Log.e("ERROR","\n Code erreur retourné par le serveur : \n\n \t Code = " + jsocod + "\n\n \t Message : " + jso.getString("message"));
            }
            return response;
        }

        /**
         * this method is called at the end of the asynchronous request
         * Generates the TextView and EditText based on the received data
         * @param result is the list of movies
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


        /**
         * Generates a textview containing a title
         * @param title is the title
         * @param index is the id
         * @param layout is the layout in which we will include this textView
         */
        private void generateTextViewTitle (String title, int index, LinearLayout layout){
            TextView t;
            t = new TextView(getApplicationContext());
            t.setText(title);
            t.setId(index);
            layout.addView(t);
        }

        /**
         * Generates a text box in which the search response will be displayed (Film image)
         * @param i is the id
         * @param layout the layout in which we want to include the zone
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