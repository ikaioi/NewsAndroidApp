package com.example.news.ui.main;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.R;
import com.example.news.model.APIResponse;
import com.example.news.networking.NewsRepository;

public class MainViewModel extends ViewModel {


    private MutableLiveData<APIResponse> mutableLiveData;
    private NewsRepository newsRepository;

    public MainViewModel(){}

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getNews("de", "1ba53465beb141228728677c2d70390c");

    }

    public LiveData<APIResponse> getNewsRepository() {
        return mutableLiveData;
    }


}