package com.example.projetmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    private List<Movie> ListMovie;

    public SearchResultAdapter (List<Movie> data) {
        ListMovie = data;
    }

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

        new ChargementImage(ListMovie.get(position).getPosterUrl(),((SearchResultViewHolder)
                holder).getImageViewPosterURL() ).execute();
        ((SearchResultViewHolder)
                holder).getTextViewTitle().setText(ListMovie.get(position).getTitle());
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

    public class ChargementImage extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ChargementImage(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }

}


