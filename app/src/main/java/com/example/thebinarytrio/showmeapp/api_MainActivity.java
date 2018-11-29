package com.example.thebinarytrio.showmeapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.thebinarytrio.showmeapp.adaptor.api_MoviesAdaptor;
import com.example.thebinarytrio.showmeapp.api.api_Client;
import com.example.thebinarytrio.showmeapp.api.api_Service;
import com.example.thebinarytrio.showmeapp.model.api_Movie;
import com.example.thebinarytrio.showmeapp.model.api_MoviesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class api_MainActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private api_MoviesAdaptor adaptor;
    private List<api_Movie> movieList;
    ProgressDialog progressDialog;
    private RelativeLayout swipeContainer;
    //private SwipeRefreshLayout swipeContainer;
    public static final String LOG_TAG = api_MoviesAdaptor.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_main);

        // initialize views
        initViews();

        swipeContainer = findViewById(R.id.main_content);

        /*
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                // reinitialize views on refresh
                initViews();
                Toast.makeText(api_MainActivity.this, "Movies Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    public Activity getActivity()
    {
        Context context = this;
        while (context instanceof ContextWrapper){
            if (context instanceof Activity){
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private void initViews()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching movies...");
        progressDialog.setCancelable(false);                    // make sure it finishes
        progressDialog.show();

        recyclerView = findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        adaptor = new api_MoviesAdaptor(this, movieList);

        // if phone in portrait mode, 2 columns
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // if phone in landscape mode, 4 columns
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();

        loadJSON();
    }

    private void loadJSON()
    {
        try{
            if (BuildConfig.THE_MOVIE_DB_API_KEY.isEmpty())                                             // if no API key
            {
                Toast.makeText(getApplicationContext(), "Missing API key! Obtain one from TheMovieDB.org", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }

            api_Client client = new api_Client();
            api_Service apiService = client.getClient().create(api_Service.class);
            Call<api_MoviesResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY);
            call.enqueue(new Callback<api_MoviesResponse>()
            {
                @Override
                public void onResponse(Call<api_MoviesResponse> call, Response<api_MoviesResponse> response)    // on success
                {
                    List<api_Movie> movies = response.body().getResults();
                    recyclerView.setAdapter(new api_MoviesAdaptor(getApplicationContext(), movies));
                    recyclerView.smoothScrollToPosition(0);

                    /*
                    if (swipeContainer.isRefreshing())
                        swipeContainer.setRefreshing(false);
                    */

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<api_MoviesResponse> call, Throwable t)                           // on failure
                {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(api_MainActivity.this, "ERROR: error fetching data!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
