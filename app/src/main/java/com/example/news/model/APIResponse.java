package com.example.news.model;

import com.google.gson.annotations.Expose;
import java.util.List;

public class APIResponse {

    @Expose
    private String status;

    @Expose
    private Integer totalResults;

    @Expose
    private List<NewsArticle> articles;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }
}

