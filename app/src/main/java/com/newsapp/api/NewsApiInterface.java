package com.newsapp.api;

import com.newsapp.model.CategoryModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NewsApiInterface {
    @GET("/sources?language=en&country=us&apiKey=abaa310d2b2741d3ad89784f4f296798")
    Observable<CategoryModel> getCategoryList( );
}
