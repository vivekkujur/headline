package com.newsapp.ui.home;


import com.newsapp.adapter.SourceRecyclerAdapter;
import com.newsapp.di.ActivityScope;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class MyAdapterModule {
    List<String> dataset;

    public MyAdapterModule(List<String> dataset) {
        this.dataset = dataset;
    }

    @Provides
    @ActivityScope
    SourceRecyclerAdapter provideMyAdapter(){
        return new SourceRecyclerAdapter(dataset);
    }
}
