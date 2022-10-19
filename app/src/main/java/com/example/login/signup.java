package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity implements View.OnClickListener {
    //declare vars
    private EditText fname, lname,email, phonenumber,password;
    private MaterialButton signup;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        signup = (MaterialButton) findViewById(R.id.signupbtn);
        signup.setOnClickListener(this);

        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        password = (EditText) findViewById(R.id.password);


        TextView txt = (TextView) findViewById(R.id.have_accountt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take you to signup page
                Intent intent = new Intent(signup.this,landing.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    public void onClick(View view) {

        registerUser();
    }

    private void registerUser() {
        String firstname = fname.getText().toString().trim();
        String lastname = lname.getText().toString().trim();
        String emailaddress = email.getText().toString().trim();
        String phone = phonenumber.getText().toString().trim();
        String pass = password.getText().toString().trim();

        //authenticate these fields
        if(firstname.isEmpty()){
          fname.setError("Enter name..");
          fname.requestFocus();
          return;
        }
        if (lastname.isEmpty()){
            lname.setError("Enter lastname");
            lname.requestFocus();
            return;
        }
        if (emailaddress.isEmpty()){
            email.setError("Enter email address..");
            email.requestFocus();
            return;
        }
//        if (Patterns.EMAIL_ADDRESS.matcher(emailaddress).matches()){
//            email.setError("Invalid email address");
//            email.requestFocus();
//            return;
//
//        }
        if (phone.isEmpty()){
            phonenumber.setError("Enter Phonenumber");
            phonenumber.requestFocus();
            return;
        }
        if (pass.isEmpty()){
            password.setError("Enter password");
            password.requestFocus();
            return;
        }
        if (pass.length()<6){
            password.setError("Password too short.!");
            password.requestFocus();
            return;
        }
        //gets connection with firebase
        mAuth.createUserWithEmailAndPassword(emailaddress,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //check if user is registered
                        if (task.isSuccessful()){
                            users user = new users(firstname,lastname,emailaddress,phone,pass);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(signup.this, "User Registered Successfully", Toast.LENGTH_LONG).show();

                                                //take you to login after successfully registering
                                                Intent intent = new Intent(signup.this,landing.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else {

                                                Toast.makeText(signup.this, "failed", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(signup.this, "failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

}