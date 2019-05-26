package com.newsapp.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.newsapp.R;
import com.newsapp.adapter.CategoryRecyclerAdapter;
import com.newsapp.adapter.SourceRecyclerAdapter;
import com.newsapp.api.NewsApiService;
import com.newsapp.api.RestGetCategoryApi;
import com.newsapp.api.RestGetCategoryInterface;
import com.newsapp.model.CategoryModel;
import com.newsapp.root.App;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RestGetCategoryInterface {

    @SuppressLint("StaticFieldLeak")
    private  static RecyclerView sourcerecycler,categoryRecycler;
    private  static LinearLayoutManager sourcelayoutManager, categoryLayoutManager;
    @Inject
    SourceRecyclerAdapter sourceRecyclerAdapter;

    private  static CategoryRecyclerAdapter categoryRecyclerAdapter;
    private  static RestGetCategoryApi restGetCategoryApi;


    List<String> repos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Api def*/
        restGetCategoryApi= new RestGetCategoryApi(this);

        init();
        initCatRecycler();

        repos = new ArrayList<>();

        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .myAdapterModule(new MyAdapterModule(repos))
                .appComponent(((App) getApplication()).getAppComponent())
                .build();
        homeComponent.inject(this);

        App  app = (App)getApplication();
        NewsApiService newsAPIService = app.getAPIService();

        newsAPIService.getCategoryList("abaa310d2b2741d3ad89784f4f296798","us")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Observer<CategoryModel> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryModel  categoryModels) {
                        System.out.println("data on next :");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );



        /*default first screen*/
        restGetCategoryApi.GetCategory("us");


    }
    /*initialize all view  */
    private void init() {
        sourcerecycler= findViewById(R.id.SourceRecycler);
        categoryRecycler = findViewById(R.id.categoryRecyclerToolbar);
    }

    /* select country recycler view show on the top of main page */
    private void initCatRecycler(){
        categoryRecyclerAdapter = new CategoryRecyclerAdapter();
        categoryLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        categoryRecycler.setLayoutManager(categoryLayoutManager);
        categoryRecycler.setAdapter(categoryRecyclerAdapter);
    }

    /*select source according to country */
    private void initSourceRecycler(JSONObject response){
        sourceRecyclerAdapter = new SourceRecyclerAdapter(this,response);
        sourcelayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        sourcerecycler.setLayoutManager(sourcelayoutManager);
        sourcerecycler.setAdapter(sourceRecyclerAdapter);
    }
    public static void selectLang(String key) {
        restGetCategoryApi.GetCategory(key);

    }


    /*api callback*/
    @Override
    public void oncategorySuccess(JSONObject response) {
        initSourceRecycler(response);
    }

    @Override
    public void oncategoryError(VolleyError error) {

    }
}
