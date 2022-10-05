package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        //
        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView)  findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin1234")){

                    //if password is correct
                    Toast.makeText(landing.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(landing.this,home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(landing.this, "WRONG CREDENTIALS", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        TextView txt = (TextView) findViewById(R.id.have_accountt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(landing.this,signup.class);
                startActivity(intent);
                finish();
            }
        });
        //EditText password = (EditText) findViewById(R.id.password);

        //


    }
}