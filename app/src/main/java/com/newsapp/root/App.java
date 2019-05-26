package com.newsapp.root;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.newsapp.api.NewsApiService;

public class App extends Application {

    NewsApiService newsApiService;
    AppComponent appComponent;

   public static Context context;

    public void onCreate() {
        super.onCreate();

             context = getApplicationContext();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }
    public NewsApiService getAPIService(){

        return appComponent.getNewsAPIService();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

  public static void  addToRequestQueue(Request req, String tag) {
        req.setTag(tag);
        Volley.newRequestQueue(context).add(req);
    }
    public static void cancelPendingRequest( String req) {
        RequestQueue mRequestQueue=Volley.newRequestQueue(context) ;
        mRequestQueue.cancelAll(req);
    }
}