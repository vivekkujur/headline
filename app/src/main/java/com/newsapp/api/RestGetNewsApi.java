package com.newsapp.api;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.newsapp.config.Setting;
import com.newsapp.root.App;

import org.json.JSONObject;

import java.util.HashMap;

public class RestGetNewsApi  implements Response.Listener,Response.ErrorListener  {

    public static final String Get_news = "Get_news";
    RestGetNewsInterface listener;

    public RestGetNewsApi(RestGetNewsInterface l) {
        listener = l;
    }

    public void GetHeadlines(String id) {

        String url = Setting.Base_Url +"/top-headlines?sources="+id+"&apiKey=abaa310d2b2741d3ad89784f4f296798";

        HashMap<String, String> mRequestParams = new HashMap<>();
        final JSONObject jsonObject = new JSONObject(mRequestParams);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, jsonObject, this, this);

        App.cancelPendingRequest(Get_news);
        App.addToRequestQueue(jsonObjectRequest, Get_news);

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("response", "err: "+ error.toString());
        listener.onNewsError(error);
    }

    @Override
    public void onResponse(Object response) {
        JSONObject jsonObject = (JSONObject) response;
        listener.onNewsSuccess(jsonObject);
    }
}
