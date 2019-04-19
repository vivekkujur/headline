package com.newsapp.api;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.newsapp.config.Setting;
import com.newsapp.utils.App;

import org.json.JSONObject;

import java.util.HashMap;

public class RestGetCategoryApi  implements Response.Listener,Response.ErrorListener  {

    public static final String GET_CATEGORY = "GET_CATEGORY";
    RestGetCategoryInterface listener;

    public RestGetCategoryApi(RestGetCategoryInterface l) {
        listener = l;
    }

    public void GetCategory(String key) {

        String url = Setting.Base_Url +"/sources?language=en&country="+key+"&apiKey=abaa310d2b2741d3ad89784f4f296798";

        HashMap<String, String> mRequestParams = new HashMap<>();

        final JSONObject jsonObject = new JSONObject(mRequestParams);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, jsonObject, this, this);

        App.cancelPendingRequest(GET_CATEGORY);
        App.addToRequestQueue(jsonObjectRequest, GET_CATEGORY);

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("response", "err: "+ error.toString());
        listener.oncategoryError(error);
    }

    @Override
    public void onResponse(Object response) {
        JSONObject jsonObject = (JSONObject) response;
        listener.oncategorySuccess(jsonObject);
    }
}
