package com.example.mydatabookindividual;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AboutActivity extends AppCompatActivity {
    ActionBar ab;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        iv = findViewById(R.id.iv);

        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/80/Republic_Polytechnic_Logo.jpg";

            Picasso.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(iv);



    }
}