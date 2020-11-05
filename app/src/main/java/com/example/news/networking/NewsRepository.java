package com.example.news.networking;


import androidx.lifecycle.MutableLiveData;

import com.example.news.model.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsApi newsApi;

    public NewsRepository(){
        newsApi = RetrofitService.createService(NewsApi.class);
    }

    public MutableLiveData<APIResponse> getNews(String source, String key){
        MutableLiveData<APIResponse> newsData = new MutableLiveData<>();
        newsApi.getNewsList(source, key).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
