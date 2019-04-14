package com.sacredcodes.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    FirebaseUser currentUser;
    //Layouts
    LinearLayout homeLayout;
    LinearLayout questionsLayout;
    LinearLayout endLayout;

    //Homelayout
    Button feedbackButton;

    //questionslayout
    Button submitButton;

    Button logOutButton;


    String que1ans;
    String que2ans;
    String que3ans;
    String que4ans;
    String que5ans;
    String que6ans;
    QueAns qa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //firbase
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference myRef;


        currentUser = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        //layouts
        homeLayout = findViewById(R.id.homeLayout);
        questionsLayout = findViewById(R.id.questionsLayout);
        endLayout = findViewById(R.id.endLayout);
        //buttons
        feedbackButton = findViewById(R.id.feedbackButton);
        submitButton = findViewById(R.id.submitButton);
        logOutButton = findViewById(R.id.logOutButton);

        homeLayout.setVisibility(View.VISIBLE);
        questionsLayout.setVisibility(View.GONE);
        endLayout.setVisibility(View.GONE);


        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeLayout.setVisibility(View.GONE);
                questionsLayout.setVisibility(View.VISIBLE);
                endLayout.setVisibility(View.GONE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               submitFeedback();
                homeLayout.setVisibility(View.GONE);
                questionsLayout.setVisibility(View.GONE);
                endLayout.setVisibility(View.VISIBLE);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });



    }



    public void onRadioButtonClicked1(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.que1ans1:
                if (checked)
                    que1ans = "Yes";// Pirates are the best
                    break;
            case R.id.que1ans2:
                if (checked)
                    que1ans = "No"; // Ninjas rule
                    break;
        }
    }

    public void onRadioButtonClicked2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.que2ans1:
                if (checked)
                    que2ans = "Yes";// Pirates are the best
                    break;
            case R.id.que2ans2:
                if (checked)
                    que2ans = "No";// Ninjas rule
                    break;
        }
    }


    public void onRadioButtonClicked3(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.que3ans1:
                if (checked)
                    que3ans = "Yes";// Pirates are the best
                    break;
            case R.id.que3ans2:
                if (checked)
                    que3ans = "No"; // Ninjas rule
                    break;
        }
    }


    public void onRadioButtonClicked4(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.que4ans1:
                if (checked)
                    que4ans = "Yes"; // Pirates are the best
                    break;
            case R.id.que4ans2:
                if (checked)
                    que4ans = "No"; // Ninjas rule
                    break;
            case R.id.que4ans3:
                if (checked)
                    que4ans = "Maybe";// Ninjas rule
                    break;
        }
    }


    public void onRadioButtonClicked5(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.que5ans1:
                if (checked)
                    que5ans = "Yes";// Pirates are the best
                    break;
            case R.id.que5ans2:
                if (checked)
                    que5ans = "No"; // Ninjas rule
                    break;
            case R.id.que5ans3:
                if (checked)
                    que5ans = "Maybe";// Ninjas rule
                    break;
        }
    }


    public void onRadioButtonClicked6(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.que6ans1:
                if (checked)
                   que6ans= "Everyday"; // Pirates are the best
                    break;
            case R.id.que6ans2:
                if (checked)
                    que6ans= "Once a week";// Ninjas rule
                    break;
            case R.id.que6ans3:
                if (checked)
                    que6ans= "Multiple times a day";// Ninjas rule
                    break;
            case R.id.que6ans4:
                if (checked)
                    que6ans= "Rarely";// Ninjas rule
                    break;
        }
    }

    public void submitFeedback(){
        String userEmail= currentUser.getEmail();
        qa = new QueAns(userEmail, que1ans, que2ans, que3ans, que4ans, que5ans, que6ans);

        uploadFeedback(qa);
    }

    private void uploadFeedback(QueAns qa) {

        FirebaseUser user2 = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("FeedbackAnswers").child(user2.getUid()).setValue(qa);
    }

}
