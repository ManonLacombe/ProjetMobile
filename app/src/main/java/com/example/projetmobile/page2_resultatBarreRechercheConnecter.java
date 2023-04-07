package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class page2_resultatBarreRechercheConnecter extends AppCompatActivity {

   /* private Button retourPagePrincipale;
    Intent IntentRetour;
    private TextView title;

    private RecyclerView searchResultRecyclerView;
    private SearchResultAdapter searchResultAdapter;

    ArrayList<Movie> finalResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_resultat_barre_recherche_connecter);

        finalResponse = new ArrayList<Movie>();
        Intent in = getIntent();
        String Recherche = String.valueOf(in.getStringExtra("search_edit_text"));
        TextView t=findViewById(R.id.SearchResult);
        t.setText(Recherche);
        page2_resultatBarreRecherche.RequestTask rt = new page2_resultatBarreRecherche.RequestTask();
        rt.execute(Recherche);

        // Création de l'instance du RecyclerView
        RecyclerView searchResultRecyclerView = findViewById(R.id.search_result_recycler_view);
        // Création de l'instance de SearchResultAdapter en passant les données
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(finalResponse);

        // Assignation de l'instance de SearchResultAdapter au RecyclerView
        searchResultRecyclerView.setAdapter(searchResultAdapter);

        title=(TextView) findViewById(R.id.SearchResult);
        IntentRetour=new Intent(page2_resultatBarreRechercheConnecter.this, MainActivity.class);

    }

    public void retour(View v) {
        if(v.getId()==R.id.retourPagePrincipale){
            startActivity(IntentRetour);
        }
    }

    */
}