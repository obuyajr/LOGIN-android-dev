package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class signup extends AppCompatActivity {
    private EditText fname,lname,email,phonenumber,password;
    private MaterialButton signupbtn;
    private FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //
        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplication(),landing.class));
        }

        reference =  FirebaseDatabase.getInstance().getReference();
        //locate on xml file
        fname = findViewById(R.id.firstname);
        lname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);
        signupbtn = findViewById(R.id.signupbtn);
        firebaseAuth = FirebaseAuth.getInstance();


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });

        TextView txt = (TextView) findViewById(R.id.have_accountt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this,landing.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void registerUser(){

        String firstName = fname.getText().toString().trim();
        String lastName = lname.getText().toString().trim();
        String emailadress = email.getText().toString().trim();
        String phoneNo = phonenumber.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(firstName)){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(lastName)){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(emailadress)){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phoneNo)){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(emailadress,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //pd.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(signup.this,"Reg Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),landing.class));
                        }else {
                            Toast.makeText(signup.this,"not reg pass",Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}