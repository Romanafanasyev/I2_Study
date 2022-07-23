package com.example.i2study.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.i2study.http.ApiClient;
import com.example.i2study.http.response.GroupByIdResponse;
import com.example.i2study.models.DecodedToken;
import com.example.i2study.http.request.LoginRequest;
import com.example.i2study.http.response.LoginResponse;
import com.example.i2study.R;
import com.example.i2study.SessionManager;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLogin, editTextPassword;
    private Button buttonLogIn;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogIn);

        //Initialize SessionManager
        sessionManager = new SessionManager(getApplicationContext());

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn(view);
            }
        });

        //If user already logged in
        if (sessionManager.getIsAuthorised()){
            //Create Intent (Where to go)
            Intent intentActual = new Intent(this, ActualActivity.class);
            //Go to Actual page
            startActivity(intentActual);
            //finish activity
            finish();
        }
    }

    public void logIn(View v){
        //Create Intent (Where to go)
        Intent intentActual = new Intent(this, ActualActivity.class);
        Intent intentAdmin = new Intent(this, AdminActivity.class);

        //Set username and password
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(editTextLogin.getText().toString());
        loginRequest.setPassword(editTextPassword.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                //If login and password correct
                if(response.isSuccessful()){
                    //Store is authorized in session
                    sessionManager.setIsAuthorized(true);
                    //Store login in session
                    sessionManager.setLogin(loginRequest.getLogin());

                    //parse data from token
                    DecodedToken my_token = null;
                    try {
                        my_token = DecodedToken.getDecoded(response.body().getToken());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    //Store data in session
                    sessionManager.setUsername(my_token.name);
                    sessionManager.setUserId(my_token.id);
                    sessionManager.setUserGroupId(my_token.groupId);
                    sessionManager.setRole(my_token.role);
                    sessionManager.setUserProfilePic(my_token.profile_pic);

                    loadDataFromServerToSessionManager(v);

                    //If user role is admin
                    if(sessionManager.getRole().equals("ADMIN")){
                        //Go to Admin page;
                        startActivity(intentAdmin);
                    }else{
                        //Go to Actual page;
                        startActivity(intentActual);
                    }

                    //finish activity
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadDataFromServerToSessionManager(View v){

        Call<GroupByIdResponse> groupByIdResponseCall = ApiClient.getGroupService().getGroupById(sessionManager.getUserGroupId());
        groupByIdResponseCall.enqueue(new Callback<GroupByIdResponse>() {
            @Override
            public void onResponse(Call<GroupByIdResponse> call, Response<GroupByIdResponse> response) {
                sessionManager.setUserGroupName(response.body().getGroupName());
            }

            @Override
            public void onFailure(Call<GroupByIdResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}