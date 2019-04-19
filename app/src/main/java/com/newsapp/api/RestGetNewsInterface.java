package com.newsapp.api;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface RestGetNewsInterface {

    void onNewsSuccess(JSONObject response);
    void onNewsError(VolleyError error);
}
