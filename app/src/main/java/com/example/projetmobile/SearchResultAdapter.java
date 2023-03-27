package com.example.projetmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    private ArrayList<Movie> ListMovie;
    public static class SearchResultViewHolder extends RecyclerView.ViewHolder {
       private final ImageView posterURL;
       private final TextView title;

        /**
         * méthode qui permettra de faire le lien entre les données
         * et les éléments graphiques du layout défini précédemment
         * @param view
         */
        public SearchResultViewHolder( View view) {
            super(view);
            posterURL = (ImageView)view.findViewById(R.id.imageViewPoster);
            title = (TextView)view.findViewById(R.id.textViewTitle);
        }
        public ImageView getImageViewPosterURL() {
            return posterURL;
        }
        public TextView getTextViewTitle() {
            return title;
        }
    }

    /**
     * Cette méthode fait le lien entre l’Adapter, le ViewHolder et le Layout
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @Override
    public SearchResultAdapter.SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_page2_recycler_view, parent, false);
        return new SearchResultViewHolder(view);
    }

    /*
    * méthode qui permet de faire le lien entre vues et données
    */
    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.SearchResultViewHolder holder, int position) {
        ((SearchResultViewHolder)
                holder).getTextViewTitle().setText(ListMovie.get(position).getTitle());
        ((SearchResultViewHolder)
                holder).getImageViewPosterURL().setImage(ListMovie.get(position).getPosterURL());
    }

    /**
     * méthode qui retourne la taille du jeu
     * de données défini en attribut de la classe.
     * @return
     */
    @Override
    public int getItemCount() {
        return ListMovie.size();
    }


}


