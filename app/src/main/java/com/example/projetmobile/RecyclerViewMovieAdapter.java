package com.example.projetmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Movie> data;

    RecyclerViewMovieAdapter(List<Movie>data){ this.data=data;}

    public static class ViewHolderQuestion extends RecyclerView.ViewHolder{
        private final TextView title;
        private final ImageView image;

        public ViewHolderQuestion(View view){
            super(view);
            title=(TextView) view.findViewById(R.id.textViewTitle);
            image=(ImageView) view.findViewById(R.id.imageViewPoster);
        }

        public TextView getTextViewTitle(){return title;}
        public ImageView getImageViewImage(){return image;}
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_page2_recycler_view, parent, false);
        return new ViewHolderQuestion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        ((ViewHolderQuestion) holder).getTextViewTitle().setText(data.get(position).getTitle());
        ((ViewHolderQuestion) holder).getImageViewImage().setImage(data.get(position).getImage());

    }

    @Override
    public int getItemCount(){return data.size();}

}
