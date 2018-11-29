package com.example.thebinarytrio.showmeapp.adaptor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.thebinarytrio.showmeapp.api_DetailActivity;
import com.example.thebinarytrio.showmeapp.R;
import com.example.thebinarytrio.showmeapp.model.api_Movie;
import java.util.List;

public class api_MoviesAdaptor extends RecyclerView.Adapter<api_MoviesAdaptor.MyViewHolder>
{
    private Context mContext;
    private List<api_Movie> movieList;

    // constructor
    public api_MoviesAdaptor(Context mContext, List<api_Movie> movieList)
    {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public int getItemCount() {return movieList.size();}

    @Override
    public api_MoviesAdaptor.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int pos)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.api_movie_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final api_MoviesAdaptor.MyViewHolder viewHolder, int pos)
    {
        // movie title
        viewHolder.title.setText(movieList.get(pos).getOriginalTitle());

        // movie average vote
        String vote = Double.toString(movieList.get(pos).getVoteAverage());
        viewHolder.userrating.setText(vote);

        // movie poster
        Glide.with(mContext).load(movieList.get(pos).getPosterPath()).placeholder(R.drawable.loading).into(viewHolder.thumbnail);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, userrating;
        public ImageView thumbnail;

        // constructor
        MyViewHolder(View view)
        {
            super(view);
            title = view.findViewById(R.id.title);
            userrating = view.findViewById(R.id.userrating);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        api_Movie clickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContext, api_DetailActivity.class);
                        intent.putExtra("original_title", movieList.get(pos).getOriginalTitle());
                        intent.putExtra("poster_path", movieList.get(pos).getPosterPath());
                        intent.putExtra("original_title", movieList.get(pos).getOriginalTitle());
                        intent.putExtra("overview", movieList.get(pos).getOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(pos).getVoteAverage()));
                        intent.putExtra("release_date", movieList.get(pos).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "Clicked " + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}