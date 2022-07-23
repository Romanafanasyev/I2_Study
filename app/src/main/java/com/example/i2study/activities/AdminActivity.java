package com.example.i2study.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.i2study.R;
import com.example.i2study.SessionManager;
import com.example.i2study.http.ApiClient;
import com.example.i2study.http.request.RegistrationRequest;
import com.example.i2study.http.response.RegistrationResponse;
import com.example.i2study.urlStorage;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    private EditText editTextLogin, editTextPassword, editTextRole;
    private Button buttonRegIn;
    private ImageButton imageButtonProfile;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRole = findViewById(R.id.editTextRole);
        imageButtonProfile = findViewById(R.id.imageButtonProfile);
        buttonRegIn = findViewById(R.id.buttonRegIn);

        //Initialize SessionManager
        sessionManager = new SessionManager(getApplicationContext());

        imageButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile(view);
            }
        });

        buttonRegIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regIn(view);
            }
        });
    }

    public void goToProfile(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void regIn(View v){

        //Set username, password and role
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setLogin(editTextLogin.getText().toString());
        registrationRequest.setPassword(editTextPassword.getText().toString());
        registrationRequest.setRole(editTextRole.getText().toString());

        Call<RegistrationResponse> registrationResponseCall = ApiClient.getUserService().userRegistration(registrationRequest);
        registrationResponseCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(AdminActivity.this, "Успешно добавлен пользователь", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(AdminActivity.this, "Не получилось добавить пользователя", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Toast.makeText(AdminActivity.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}