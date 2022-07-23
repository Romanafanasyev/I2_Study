package com.example.i2study.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.i2study.R;
import com.example.i2study.SessionManager;
import com.example.i2study.urlStorage;
import com.squareup.picasso.Picasso;

public class AttendanceActivity extends AppCompatActivity {

    private TextView name;
    private ImageButton imageButtonPoints, imageButtonSchedule, imageButtonActual, imageButtonAttendance, imageButtonProfile;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        imageButtonPoints = findViewById(R.id.imageButtonPoints);
        imageButtonSchedule = findViewById(R.id.imageButtonSchedule);
        imageButtonActual = findViewById(R.id.imageButtonActual);
        imageButtonAttendance = findViewById(R.id.imageButtonAttendance);
        imageButtonProfile = findViewById(R.id.imageButtonProfile);


        sessionManager = new SessionManager(getApplicationContext());

        name = findViewById(R.id.textViewName);
        String txtName = sessionManager.getUsername();
        name.setText(txtName);

        imageButtonPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPoints(view);
            }
        });
        imageButtonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSchedule(view);
            }
        });
        imageButtonActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActual(view);
            }
        });
        imageButtonAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAttendance(view);
            }
        });
        imageButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile(view);
            }
        });
    }

    public void goToPoints(View v){
        Intent intent = new Intent(this, PointActivity.class);
        startActivity(intent);
    }
    public void goToSchedule(View v){
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }
    public void goToActual(View v){
        Intent intent = new Intent(this, ActualActivity.class);
        startActivity(intent);
    }
    public void goToAttendance(View v){
        Intent intent = new Intent(this, AttendanceActivity.class);
        startActivity(intent);
    }
    public void goToProfile(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}