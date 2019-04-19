package com.newsapp.config;

import com.newsapp.R;

import java.util.HashMap;
import java.util.Map;

public class Setting {
    public static  String Base_Url="https://newsapi.org/v2/";
    public static HashMap<String, String> COUNTRY;
    public static HashMap<String, Integer> COUNTRYFLAG;

    static {
        COUNTRY = new HashMap<>();
        COUNTRY.put("INDIA", "in");
        COUNTRY.put("USA", "us");
        COUNTRY.put("AUSTRALIA", "au");
        COUNTRY.put("UNITED KINGDOM", "gb");
        COUNTRY.put("RUSSIA", "ru");
    }
    static {
        COUNTRYFLAG = new HashMap<>();
        COUNTRYFLAG.put("INDIA", R.drawable.india);
        COUNTRYFLAG.put("USA", R.drawable.united_states);
        COUNTRYFLAG.put("AUSTRALIA",R.drawable.australia);
        COUNTRYFLAG.put("UNITED KINGDOM",R.drawable.united_kingdom);
        COUNTRYFLAG.put("RUSSIA", R.drawable.russia);
    }
}
