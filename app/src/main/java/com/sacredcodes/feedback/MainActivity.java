package com.sacredcodes.feedback;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    //Layouts
    LinearLayout signInLayout ;
    LinearLayout signUpLayout;

    //Buttons
    Button signInButton;
    Button signUpForm;
    Button signUpMain;

    //SignInLayout TextFields

    EditText userEmail ;
    EditText userPassword;

    //SignUpLayout textFields

    EditText userEmailSignUp;
    EditText userPasswordSignUp;
    EditText userNameSignUp;
    EditText userPhoneSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //firebase
        FirebaseApp.initializeApp(MainActivity.this);
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference myRef;


        FirebaseUser currentUser = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();

        //Layouts
        signInLayout = findViewById(R.id.signInLayout) ;
        signUpLayout =  findViewById(R.id.signUpLayout);

        //Buttons
        signInButton = findViewById(R.id.signInButton);
        signUpForm = findViewById(R.id.signUpForm);
        signUpMain = findViewById(R.id.signUpMain);

        //SignInLayout TextFields

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);

        //SignUpLayout textFields

        userEmailSignUp = findViewById(R.id.userEmailSignUp);
        userPasswordSignUp = findViewById(R.id.userPasswordSignUp);
        userNameSignUp = findViewById(R.id.userNameSignUp);
        userPhoneSignUp = findViewById(R.id.userPhoneSignUp);


        //Visibility
        signInLayout.setVisibility(View.VISIBLE);
        signUpLayout.setVisibility(View.INVISIBLE);

        //Sign In Button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_a = userEmail.getText().toString().trim();
                String password_a = userPassword.getText().toString().trim();

                SignIn(email_a, password_a);
                 }
        });

        //SignUpForm Button
        signUpForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInLayout.setVisibility(View.INVISIBLE);
                signUpLayout.setVisibility(View.VISIBLE);
            }
        });

        signUpMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_a = userEmailSignUp.getText().toString().trim();
                String password_a = userPasswordSignUp.getText().toString().trim();
                String name_a= userNameSignUp.getText().toString().trim();
                String phone_a= userPhoneSignUp.getText().toString().trim();
                User user = new User(name_a, phone_a, email_a);
                SignUp(user, password_a);

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseApp.initializeApp(MainActivity.this);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

        if(currentUser != null)
        {

          //  String userEmail= currentUser.getEmail();
           Intent i = new Intent(this, HomeActivity.class);
            //i.putExtra("userEmail", userEmail);
            startActivity(i);
        }


    }


    public void SignUp(final User user1, String password) {

        mAuth.createUserWithEmailAndPassword(user1.getUserEmail(), password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            public static final String TAG = "Tag" ;

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    User user = new User(user1.userName, user1.userPhone,user1.userEmail);
                    mDatabase= FirebaseDatabase.getInstance().getReference();
                    FirebaseUser user2 = mAuth.getCurrentUser();
                    mDatabase.child("Users").child(user2.getUid()).setValue(user);


                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    Toast.makeText(MainActivity.this, "Sign Up .",
                            Toast.LENGTH_SHORT).show();

                    updateUI(user2);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }

        });

    }

    public void SignIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public static final String TAG = "Tag" ;
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }


                    }

                });
    }
}
