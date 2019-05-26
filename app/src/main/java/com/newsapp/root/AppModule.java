package com.newsapp.root;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newsapp.api.NewsApiService;
import com.newsapp.di.AppScope;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module

public class AppModule {

    @Provides
    @AppScope

    Gson provideGson(){
        Gson gson=new GsonBuilder()
                .setLenient()

                .create();

        return gson;
    }

    @Provides
    @AppScope

    Retrofit provideRetrofit(Gson gson){



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return  retrofit;

    }

    @Provides
    @AppScope

    NewsApiService provideNewsAPIService(Gson gson, Retrofit retrofit){
        NewsApiService newsAPIService = new NewsApiService(gson, retrofit);
        return newsAPIService;
    }
}