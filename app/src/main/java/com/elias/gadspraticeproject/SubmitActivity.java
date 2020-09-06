package com.elias.gadspraticeproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elias.gadspraticeproject.services.SubmitBuilder;
import com.elias.gadspraticeproject.services.SubmitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    private EditText firstName,lastName,emailAddress,project;
    private Button submit;
    private ImageButton back_btn;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        Handler handler = new Handler();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.secondName);
        emailAddress = findViewById(R.id.email_address);
        project = findViewById(R.id.github_link);
        submit = findViewById(R.id.submit_button);
        back_btn = findViewById(R.id.back_btn);
        progressBar = findViewById(R.id.progressBar);

        back_btn.setOnClickListener(view -> onBackPressed());
        submit.setOnClickListener(view -> {
            if (firstName.getText().toString().isEmpty() ||
                    lastName.getText().toString().isEmpty() ||
                    emailAddress.getText().toString().isEmpty() ||
                    project.getText().toString().isEmpty()){
                Toast.makeText(this,"All fields is required",Toast.LENGTH_LONG).show();
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
                View views = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.decision_dialog, null);
                builder.setView(views);
                alertDialog = builder.create();
                views.findViewById(R.id.yes_button).setOnClickListener(view1 -> {
                    alertDialog.dismiss();
                    handler.postDelayed(() -> confirmationDialog(), 2000);
                    progressBar.setVisibility(View.VISIBLE);
                });
                views.findViewById(R.id.cancel_btn).setOnClickListener(view12 -> alertDialog.dismiss());
                alertDialog.show();
            }
        });

    }


    private void confirmationDialog() {
        progressBar.setVisibility(View.GONE);
        SubmitConnection submitConnection = SubmitBuilder.buildServices(SubmitConnection.class);
        Call<Void> createRequest = submitConnection.submitApi(firstName.getText().toString(),
                lastName.getText().toString(),
                emailAddress.getText().toString(),
                project.getText().toString());
        createRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
                    View views = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.decision_dialog, null);
                    builder.setView(views);
                    alertDialog = builder.create();
                    views.findViewById(R.id.sub1).setVisibility(View.GONE);
                    views.findViewById(R.id.sub2).setVisibility(View.VISIBLE);
                    ImageView image = views.findViewById(R.id.sub_image);
                    image.setImageResource(R.drawable.ic_checked);
                    alertDialog.show();
                }else{
                    Toast.makeText(SubmitActivity.this,"Failure Respond",Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
                    View views = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.decision_dialog, null);
                    builder.setView(views);
                    alertDialog = builder.create();
                    views.findViewById(R.id.sub1).setVisibility(View.GONE);
                    views.findViewById(R.id.sub2).setVisibility(View.VISIBLE);
                    ImageView image = views.findViewById(R.id.sub_image);
                    image.setImageResource(R.drawable.ic_warning_24);
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SubmitActivity.this,"Failure",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
                View views = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.decision_dialog, null);
                builder.setView(views);
                alertDialog = builder.create();
                views.findViewById(R.id.sub1).setVisibility(View.GONE);
                views.findViewById(R.id.sub2).setVisibility(View.VISIBLE);
                ImageView image = views.findViewById(R.id.sub_image);
                image.setImageResource(R.drawable.ic_warning_24);
                TextView speech = views.findViewById(R.id.submission_speech);
                speech.setText("Submission not Successful");
                alertDialog.show();
            }
        });

    }
}