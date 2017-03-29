package com.adadlab.hamza.justfab;

import com.adadlab.hamza.justfab.models.Pages;



import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by DevBlaan on 29/03/2017.
 */

public interface ApiInterface {

    @GET("justfab")
    Observable<Pages> getPages();
}
