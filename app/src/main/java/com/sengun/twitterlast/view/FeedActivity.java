package com.sengun.twitterlast.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sengun.twitterlast.Profil;
import com.sengun.twitterlast.adapter.TweetAdepter;
import com.sengun.twitterlast.databinding.ActivityFeedBinding;
import com.sengun.twitterlast.model.Tweet;
import com.sengun.twitterlast.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.sengun.twitterlast.model.Tweet;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<Tweet> tweetArrayList;
    private ActivityFeedBinding binding;
    TweetAdepter tweetAdepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tweetArrayList = new ArrayList<Tweet>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getDataFromFirestore();


        //RecyclerView

        binding.recyclerviewTweets.setLayoutManager(new LinearLayoutManager(this));
        tweetAdepter = new TweetAdepter(tweetArrayList);
        binding.recyclerviewTweets.setAdapter(tweetAdepter);

    }

    public void tweetShareClicked1 (View view){

        Button tweetShare = (Button) findViewById(R.id.tweetShare1);

        tweetShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedActivity.this, Upload.class));
            }
        });

    }

    //public void profilFotoClick (View view){

      //  ImageView profil = (ImageView) findViewById(R.id.profilFoto);

       // profil.setOnClickListener(new View.OnClickListener() {
        //    @Override
           // public void onClick(View v) {
              //  startActivity(new Intent(FeedActivity.this, Upload.class));
          //  }
       // });
    //}

    public void ProfilClick (View view){
        Button Profil = (Button) findViewById(R.id.Profil);

        Profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedActivity.this, Profil.class));
            }
        });


    }

    public void cikisYapClick (View view){

        TextView cikisYap = (TextView) findViewById(R.id.cikisYap);

        cikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedActivity.this,SignIn.class));
            }
        });
    }

    public void getDataFromFirestore() {

        CollectionReference collectionReference = firebaseFirestore.collection("Tweets");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(FeedActivity.this,e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String,Object> data = snapshot.getData();

                        //Casting
                        String tweet = (String) data.get("tweet");
                        String userEmail = (String) data.get("useremail");
                        String downloadUrl = (String) data.get("downloadurl");

                        Tweet post = new Tweet(userEmail,tweet,downloadUrl);

                        tweetArrayList.add(post);

                    }

                    tweetAdepter.notifyDataSetChanged();


                }

            }
        });


    }
}