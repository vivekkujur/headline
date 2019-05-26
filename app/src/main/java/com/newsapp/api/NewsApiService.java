package com.newsapp.api;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.newsapp.model.CategoryModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class NewsApiService {

    Gson gson;
    Retrofit retrofit;

    public NewsApiService(Gson gson, Retrofit retrofit) {
        this.gson = gson;
        this.retrofit = retrofit;
    }
    public Observable<CategoryModel>  getCategoryList(String apikey, String key){
        NewsApiInterface newsApiInterface = retrofit.create(NewsApiInterface.class);
        Observable<CategoryModel>  observableRepo = newsApiInterface.getCategoryList();

        System.out.println(observableRepo.elementAt(0));

        return observableRepo;
    }
}
