package com.elias.gadspraticeproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.elias.gadspraticeproject.model.Submit;
import com.elias.gadspraticeproject.services.ServiceBuilder;
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
    private Submit submits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.secondName);
        emailAddress = findViewById(R.id.email_address);
        project = findViewById(R.id.github_link);
        submit = findViewById(R.id.submit_button);
        back_btn = findViewById(R.id.back_btn);
        progressBar = findViewById(R.id.progressBar);

        submits = new Submit();
        submits.setFirstName(firstName.getText().toString());
        submits.setLastName(lastName.getText().toString());
        submits.setEmailAddress(emailAddress.getText().toString());
        submits.setGithubLink(project.getText().toString());

        back_btn.setOnClickListener(view -> onBackPressed());
        submit.setOnClickListener(view -> {
            if (submits.getFirstName().isEmpty() || submits.getLastName().isEmpty() || submits.getEmailAddress().isEmpty() ||submits.getGithubLink().isEmpty()){
                Toast.makeText(this,"All fields is required",Toast.LENGTH_LONG).show();
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
                View views = LayoutInflater.from(SubmitActivity.this).inflate(
                        R.layout.decision_dialog,
                        findViewById(R.id.layout_dialog)
                );
                builder.setView(views);
                alertDialog = builder.create();
                views.findViewById(R.id.yes_button).setOnClickListener(view1 -> {
                    alertDialog.dismiss();
                    progressBar.setVisibility(View.VISIBLE);
                    confirmationDialog();
                });
                views.findViewById(R.id.cancel_btn).setOnClickListener(view12 -> alertDialog.dismiss());
                alertDialog.show();
            }
        });

    }


    private void confirmationDialog() {

        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        View views = LayoutInflater.from(SubmitActivity.this).inflate(
                R.layout.decision_dialog,
                findViewById(R.id.sub2)
        );
        views.findViewById(R.id.sub2).setVisibility(View.VISIBLE);
        views.findViewById(R.id.sub1).setVisibility(View.GONE);
        builder.setView(views);
        AlertDialog alertDialog = builder.create();
        ImageView image = views.findViewById(R.id.sub_image);
        image.setImageResource(R.drawable.ic_checked);
        alertDialog.show();

//        SubmitConnection submitConnection = ServiceBuilder.buildServices(SubmitConnection.class);
//        Call<Void> createRequest = submitConnection.merchartRest(
//                submits.getFirstName(),
//                submits.getLastName(),
//                submits.getEmailAddress(),
//                submits.getGithubLink());

//        createRequest.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()){
//                    progressBar.setVisibility(View.GONE);
//                    AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
//                    View views = LayoutInflater.from(SubmitActivity.this).inflate(
//                            R.layout.decision_dialog,
//                            findViewById(R.id.sub1)
//                    );
//                    builder.setView(views);
//                    AlertDialog alertDialog = builder.create();
//                    ImageView image = views.findViewById(R.id.sub_image);
//                    image.setImageResource(R.drawable.ic_checked);
//                    alertDialog.show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
//                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
//                View views = LayoutInflater.from(SubmitActivity.this).inflate(
//                        R.layout.decision_dialog,
//                        findViewById(R.id.sub)
//                );
//                builder.setView(views);
//                AlertDialog alertDialog = builder.create();
//                EditText speech = views.findViewById(R.id.submission_speech);
//                speech.setText("Submission not Successful");
//                ImageView image = views.findViewById(R.id.sub_image);
//                image.setImageResource(R.drawable.ic_warning_24);
//                alertDialog.show();
//            }
//        });

    }
}