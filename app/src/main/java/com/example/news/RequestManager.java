package com.example.news;

import android.content.Context;
import android.widget.Toast;

import com.example.news.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    // class is for TO manage api call....

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    //api call manage from onFetchDataLisetner....

public void getNewsHeadlines(OnFetchDataListener listener, String category, String query){

    CallNewsApi callNewsApi;
    callNewsApi = retrofit.create(CallNewsApi.class);
    Call<NewsApiResponse> call = callNewsApi.callHeadlines("in",category,query,context.getString(R.string.api_key));

    try{
        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Error!! from RequestManger class", Toast.LENGTH_SHORT).show();
                }

                //parameter from onFetchDataListner interface class
                listener.onFetchData(response.body().getArticles(), response.message());
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                //parameter from onFetchDataListner interface class
                listener.onError("Request Failed...");
            }
        });
    }
    catch (Exception e){
        e.printStackTrace();
    }
}

    // create a constructor for requestManager class...
    public RequestManager(Context context) {
        this.context = context;
    }

    // Here manage api calls.. call api interface
    public interface CallNewsApi{
        @GET("top-headlines")    //end-point from api link..
        Call<NewsApiResponse> callHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("q") String query,
            @Query("apiKey") String api_key
        );
    }


}

