package com.example.thebinarytrio.showmeapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class api_MoviesResponse
{
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<api_Movie> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;


    public int getPage(){ return page; }
    public void setPage(int page){ this.page = page; }


    public List<api_Movie> getResults(){ return results; }
    public void setResults(List<api_Movie> results){ this.results = results; }


    public int getTotalResults(){ return totalResults; }
    public void setTotalResults(int totalResults){ this.totalResults = totalResults; }


    public int getTotalPages(){ return totalPages; }
    public void setTotalPages(int totalPages){ this.totalPages = totalPages; }

}
