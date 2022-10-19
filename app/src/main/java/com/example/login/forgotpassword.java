package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private MaterialButton reset;
    private EditText email;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        //initialize objs
        email = (EditText) findViewById(R.id.emailaddress);
        mAuth = FirebaseAuth.getInstance();
        reset = (MaterialButton) findViewById(R.id.resetbtn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword() {
        String emailaddr = email.getText().toString().trim();

        if (emailaddr.isEmpty()){
            email.setError("Emailaddress Required!");
            email.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(emailaddr).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(forgotpassword.this, "Check your email for reset link!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}