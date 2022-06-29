package com.sengun.twitterlast.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sengun.twitterlast.R;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth= FirebaseAuth.getInstance();

        FirebaseUser user =auth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent (MainActivity.this,FeedActivity.class);
            startActivity(intent);
            finish();
        }



    }



    public void signupcliked1 (View view){

        Button btn = (Button)findViewById(R.id.singup1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

    }

    public void signincliked1 (View view){

         TextView singin = (TextView)findViewById(R.id.singin1);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignIn.class));
            }
        });

    }
    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }
}