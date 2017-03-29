package com.adadlab.hamza.justfab;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DevBlaan on 29/03/2017.
 */

public class App extends Application {


    public static ApiInterface apiInterface;
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
    }

    public static ApiInterface getApiInterface(){
        if(apiInterface == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://hamza.adadlab.com/")
                    .build();
            apiInterface =  retrofit.create(ApiInterface.class);
        }
        return apiInterface;
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
