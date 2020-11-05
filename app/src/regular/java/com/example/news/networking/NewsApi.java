package com.example.news.networking;

import com.example.news.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<APIResponse> getNewsList(@Query("sources") String sources,
                                  @Query("apiKey") String apiKey);
}
