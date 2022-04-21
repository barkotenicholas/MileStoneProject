package com.example.gbv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gbv.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    public static final String TAG = SignupActivity.class.getSimpleName();

    private String userName;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private ActivitySignupBinding activitySignupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = activitySignupBinding.getRoot();
        setContentView(view);

        activitySignupBinding.clSignUpCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view ==activitySignupBinding.clSignUpCreateAccount){

                    createNewUser();

                }

                if (view==activitySignupBinding.tvSignUpSignIn){

                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); /*FLAG_ACTIVITY_CLEAR_TASK will cause any existing task that would be associated with the activity to be cleared before the activity is started. This prevents this Activity from being unnecessarily accessed via the system back button. FLAG_ACTIVITY_NEW_TASK will make the activity we are navigating to the start of a brand new task on this history stack.*/
                    startActivity(intent);
                    finish();

                }
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();

        createAuthStateListener();
    }

    private void createNewUser() {
        userName = activitySignupBinding.tvSignUpName.getText().toString().trim();

        //capture input from user.
        final String name = activitySignupBinding.tvSignUpName.getText().toString().trim();
        final String email = activitySignupBinding.tvSignUpEmail.getText().toString().trim();
        String password = activitySignupBinding.tvSignUpPassword.getText().toString().trim();
        String confirmPassword = activitySignupBinding.tvSignUpConfirmPassword.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validUserName = isValidUserName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validUserName || !validPassword) return;



        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, task -> {

            if (task.isSuccessful()){

                Log.d(TAG, "Firebase Authentication is successful.");

                createFirebaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));

                Toast.makeText(SignupActivity.this, "Firebase Authentication is successful.", Toast.LENGTH_SHORT).show();

            }else {

                Toast.makeText(SignupActivity.this, "Firebase Authentication has failed.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void createFirebaseUserProfile(FirebaseUser firebaseUser) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder().setDisplayName(userName).build();

        firebaseUser.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if (task.isSuccessful()) {

                    Log.d(TAG, Objects.requireNonNull(firebaseUser.getDisplayName()));

                    String inputFirebaseUserName = Objects.requireNonNull(firebaseUser.getDisplayName());

                    Toast.makeText(SignupActivity.this, "The display name has been set", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.putExtra("inputFirebaseUserName", inputFirebaseUserName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); /*FLAG_ACTIVITY_CLEAR_TASK will cause any existing task that would be associated with the activity to be cleared before the activity is started. This prevents this Activity from being unnecessarily accessed via the system back button. FLAG_ACTIVITY_NEW_TASK will make the activity we are navigating to the start of a brand new task on this history stack.*/
                    startActivity(intent);

                    finish();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {

            activitySignupBinding.tvSignUpPassword.setError("Please create a password containing at least 6 characters");
            return false;

        } else if (!password.equals(confirmPassword)) {

            activitySignupBinding.tvSignUpConfirmPassword.setError("Passwords do not match");
            return false;

        }

        return true;
    }

    private boolean isValidUserName(String name) {
        if (name.equals("")) {

            activitySignupBinding.tvSignUpName.setError("Please enter your name");
            return false;

        }

        return true;
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =(email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());

        if (!isGoodEmail) {

            activitySignupBinding.tvSignUpEmail.setError("Please enter a valid email address");
            return false;

        }
        return isGoodEmail;
    }

    private void createAuthStateListener() {
        firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                final FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();

                if (firebaseUser!=null){

                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }

            }
        };
    }
}