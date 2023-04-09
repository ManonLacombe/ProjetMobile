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

/**
 * this class allows custom visualization of large volume of data
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    private List<Movie> ListMovie;

    /**
     * Constructor
     * @param data is a list of movies
     */
    public SearchResultAdapter (List<Movie> data) {
        ListMovie = data;
    }

    /**
     * it is an asynchronous inner class.
     * It describes the display of each of the elements to be displayed
     * It allows to make the link between the textview defined by the layout
     */
    public static class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private final ImageView posterURL;
        private final TextView title;

        /**
         * this methode will make the link between the data
         * and the graphic elements of the layout defined previously
         * @param view is the view
         */
        public SearchResultViewHolder( View view) {
            super(view);
            posterURL = (ImageView)view.findViewById(R.id.imageViewPoster);
            title = (TextView)view.findViewById(R.id.textViewTitle);
        }

        /**
         * this method allows to obtain an image
         * @return an ImageView containing the image
         */
        public ImageView getImageViewPosterURL() {
            return posterURL;
        }

        /**
         * this method allows to obtain the title
         * @return a TextView containing the title
         */
        public TextView getTextViewTitle() {
            return title;
        }
    }

    /**
     * This method makes the link between the Adapter, the ViewHolder and the Layout
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return the data to diplay
     */
    @Override
    public SearchResultAdapter.SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_page2_recycler_view, parent, false);
        return new SearchResultViewHolder(view);
    }


    /**
     * method that makes it possible to make the link between views and data
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.SearchResultViewHolder holder, int position) {

        new ChargementImage(ListMovie.get(position).getPosterUrl(),((SearchResultViewHolder)
                holder).getImageViewPosterURL() ).execute();
        ((SearchResultViewHolder)
                holder).getTextViewTitle().setText(ListMovie.get(position).getTitle());
    }

    /**
     * This method counts the number of items
     * @return the size of the dataset defined as an attribute of the class
     */
    @Override
    public int getItemCount() {
        return ListMovie.size();
    }

    /**
     * it is an asynchronous inner class.
     * it allows to load an image
     */
    public class ChargementImage extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        /**
         * Constructor
         * @param url is the url of the image
         * @param imageView is the location to display the image
         */
        public ChargementImage(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        /**
         * this method defines the code to be executed as an asynchronous task.
         * Here, establishing and processing the connection
         * @param params params is void, which means there are no parameters specified
         *               for this method and it takes no arguments when called
         * @return a digital image composed of a matrix of dots (Bitmap)
         */
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

        /**
         * This method allows you to do post-processing on the result once the task is finished
         * @param result is a bitmap
         */
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }
    }
}