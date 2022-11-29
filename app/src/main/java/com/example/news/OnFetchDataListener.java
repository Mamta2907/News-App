package com.example.news;

import com.example.news.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    // listener class for handle the responces from mainActivity...

    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
