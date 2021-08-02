package com.kalvin.myvolley;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedIstanceState){
        super.onCreate(savedIstanceState);
        setContentView(R.layout.activity_profile);
        //Initialize the views using the ID's you set in activity_profile.xml
        ImageView profileImageview = findViewById(R.id.profileImageview);
        TextView userNameTextview = findViewById(R.id.usernameTextview);
        ImageButton shareProfile = findViewById(R.id.shareProfile);
        TextView developerUrl = findViewById(R.id.developerUrl);

        Intent intent = getIntent();
        final String username = intent.getStringExtra(DevelopersAdapter.KEY_NAME);
        String image = intent.getStringExtra(DevelopersAdapter.KEY_IMAGE);
        final String profileUrl = intent.getStringExtra(DevelopersAdapter.KEY_URL);
        //setting the views
        Picasso.with(this)
                .load(image)
                .into(profileImageview);
        userNameTextview.setText(username);
        developerUrl.setText(profileUrl);

        //set on click listener to developerUrl so as to open the developer link using implicit intent
        developerUrl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = profileUrl;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        //set onclickListener to the image button shareProfilw and implement implicit intent for
        //sharing developers profile
        shareProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Check out this awesome developer" +
                        username + ", " + profileUrl);
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
                if(shareIntent.resolveActivity(getPackageManager()) !=null){
                    startActivity(chooser);
                }
            }
        });

    }
}
