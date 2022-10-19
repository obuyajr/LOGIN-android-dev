package com.example.login;

import static android.os.Build.VERSION_CODES.O;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class landing extends AppCompatActivity {

    private EditText email,password;
    private  MaterialButton login;

    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        //initialize objs
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (MaterialButton) findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        //forgot password
        TextView forgpass = (TextView) findViewById(R.id.forgotPassword);
        forgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(landing.this,forgotpassword.class);
                startActivity(intent);
                //finish();
            }
        });
        //take you to signup page
        TextView txt = (TextView) findViewById(R.id.have_accountt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(landing.this,signup.class);
                startActivity(intent);
                //finish();
            }
        });
        //remain logged in until you decide to log out
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!= null){
            startActivity(new Intent(landing.this,home.class));
        }


    }

    private void userLogin() {
        String emailaddress = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        //validation
        if(emailaddress.isEmpty()){
            email.setError("Emailaddress Required!!");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Password required!!");
            password.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailaddress,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(landing.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(landing.this, home.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(landing.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    //progressBar.setVisibility(View.GONE);

                }

            }
        });

    }

    }






