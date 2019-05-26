package com.newsapp.root;

import com.newsapp.api.NewsApiService;
import com.newsapp.di.AppScope;

import dagger.Component;

@Component(modules = {AppModule.class})
@AppScope

public interface AppComponent {
    NewsApiService getNewsAPIService();
}
