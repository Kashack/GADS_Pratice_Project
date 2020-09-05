package com.elias.gadspraticeproject.services;

import com.elias.gadspraticeproject.model.Hours;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningLeaders {
    @GET("/api/hours")
    Call<List<Hours>> getAllHours();
}
