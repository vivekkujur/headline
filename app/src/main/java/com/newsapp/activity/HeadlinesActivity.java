package com.newsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.newsapp.R;
import com.newsapp.adapter.HeadlineRecyclerAdapter;
import com.newsapp.api.RestGetNewsApi;
import com.newsapp.api.RestGetNewsInterface;

import org.json.JSONObject;

public class HeadlinesActivity extends AppCompatActivity implements RestGetNewsInterface {

    RecyclerView recyclerHeadline;
    HeadlineRecyclerAdapter headlineRecyclerAdapter;
    private static RestGetNewsApi restGetNewsApi;
    private static  String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        restGetNewsApi= new RestGetNewsApi(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Id= bundle.getString("id");
        }
        init();

        /*api call for get headline of source*/
        restGetNewsApi.GetHeadlines(Id);

    }

    private void init() {
        recyclerHeadline = findViewById(R.id.HeadlineRecycler);

    }

    private void initrecycler(JSONObject response){
        headlineRecyclerAdapter= new HeadlineRecyclerAdapter(this,response);
        recyclerHeadline.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerHeadline.setAdapter(headlineRecyclerAdapter);
    }

    @Override
    public void onNewsSuccess(JSONObject response) {
        initrecycler(response);
    }

    @Override
    public void onNewsError(VolleyError error) {

    }
}
