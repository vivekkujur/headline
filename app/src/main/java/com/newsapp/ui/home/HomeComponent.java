package com.newsapp.ui.home;


import com.newsapp.di.ActivityScope;
import com.newsapp.root.AppComponent;

import dagger.Component;

@Component(modules = {MyAdapterModule.class}, dependencies = {AppComponent.class})
@ActivityScope
public interface HomeComponent {
    //MyAdapter getMyAdapter();
    void inject(MainActivity target);
}
