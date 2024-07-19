package com.subi.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button signUpButton = findViewById(R.id.SigUpSlapt);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Welcome.this, SignUpScreen.class);


                startActivity(intent);
            }

        });
        Button dangnhapButton = findViewById(R.id.dangnhapButton);
        dangnhapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Welcome.this, SignInScreen.class);


                startActivity(intent);
            }

        });
    }
}