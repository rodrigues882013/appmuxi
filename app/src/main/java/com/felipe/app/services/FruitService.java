package com.felipe.app.services;

import com.felipe.app.models.FruitsJSON;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by felipe on 7/11/17.
 */

public interface FruitService {

    @GET("fruits.json")
    Call<FruitsJSON>listFruits();
}