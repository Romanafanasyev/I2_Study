package com.example.i2study.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.i2study.R;
import com.example.i2study.SessionManager;
import com.example.i2study.urlStorage;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private TextView name, group;
    private Button buttonLogOut, buttonBack;
    private ImageView profile_pic;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(getApplicationContext());

        profile_pic = findViewById(R.id.imageViewProfilePic);
        String path = urlStorage.getPath() + sessionManager.getUserProfilePic();
        Picasso.get().load(path).into(profile_pic);

        name = findViewById(R.id.textViewName);
        String txtName = sessionManager.getUsername();
        name.setText(txtName);

        group = findViewById(R.id.textViewGroup);
        String txtGroup = sessionManager.getUserGroupName();
        group.setText(txtGroup);

        buttonLogOut = findViewById(R.id.buttonLogOut);
        buttonBack = findViewById(R.id.buttonBack);

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut(view);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(view);
            }
        });
    }

    public void logOut(View v){
        //Set isAuthorized false
        sessionManager.setIsAuthorized(false);
        //Ser username empty
        sessionManager.setUsername("");
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }

    public void goBack(View v){
        String txtRole = sessionManager.getRole();
        if (txtRole.equals("ADMIN")) {
            Intent intentAdmin = new Intent(this, AdminActivity.class);
            startActivity(intentAdmin);
        } else{
            Intent intentActual = new Intent(this, ActualActivity.class);
            startActivity(intentActual);
        }
    }
}
