package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {
    private MaterialButton logout,shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MaterialButton logout = (MaterialButton) findViewById(R.id.logoutbtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(home.this, "User Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(home.this,landing.class));
                finish();
            }
        });
        //to goods page
        shop = (MaterialButton) findViewById(R.id.shopbtn);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,goods.class));

            }
        });

        
    }
}