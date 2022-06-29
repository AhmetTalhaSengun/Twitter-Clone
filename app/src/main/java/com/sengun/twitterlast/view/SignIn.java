package com.sengun.twitterlast.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sengun.twitterlast.R;
import com.sengun.twitterlast.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth= FirebaseAuth.getInstance();

    }



    public void kaydolTextClick (View view){

        TextView kaydolButton = (TextView)findViewById(R.id.kaydolButton);

        kaydolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUpActivity.class));
            }
        });

    }

    public void girisyapClicked (View view){
        String email = binding.UsernameSignIn.getText().toString();
        String password = binding.passwordSignIn.getText().toString();
        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Email ve şifrenizi giriniz.",Toast.LENGTH_LONG).show();
        } else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(SignIn.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignIn.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });



        }

    }

}