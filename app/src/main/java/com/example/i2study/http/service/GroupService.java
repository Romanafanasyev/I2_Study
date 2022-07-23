package com.example.i2study.http.service;

import com.example.i2study.http.response.GroupByIdResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GroupService {

    @GET("group")
    Call<GroupByIdResponse> getGroupById(
            @Query("id") int id
    );

}
