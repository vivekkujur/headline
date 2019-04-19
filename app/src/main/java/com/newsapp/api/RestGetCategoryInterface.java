package com.newsapp.api;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface RestGetCategoryInterface {
    void oncategorySuccess(JSONObject response);
    void oncategoryError(VolleyError error);
}
