package com.khilman.uploadimage.InitRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 9/22/17.
 */

public class InitLibrary {
    // url server
    public static String SERVER_URL = "http://192.168.95.196/retro_image/";


    public static Retrofit setInit(){
        return new Retrofit.Builder().baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
