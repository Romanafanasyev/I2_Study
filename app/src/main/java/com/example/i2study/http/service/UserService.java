package com.example.i2study.http.service;

import com.example.i2study.http.request.LoginRequest;
import com.example.i2study.http.response.LoginResponse;
import com.example.i2study.http.request.RegistrationRequest;
import com.example.i2study.http.response.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

        @POST("user/login/")
        Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

        @POST("user/registration/")
        Call<RegistrationResponse> userRegistration(@Body RegistrationRequest registrationRequest);
}
