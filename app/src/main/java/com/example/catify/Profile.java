package com.example.catify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        ArrayList<String> list = intent.getStringArrayListExtra("ls");
        TextView name, des, temp, origin, lifeSpan;
        name = findViewById(R.id.cat_name);
        name.setText(list.get(0));

        des = findViewById(R.id.description);
        des.setText(list.get(1));

        lifeSpan = findViewById(R.id.lifeSpan);
        lifeSpan.setText(list.get(2) + getString(R.string.unit));

        origin = findViewById(R.id.origin);
        origin.setText(list.get(3));

        temp = findViewById(R.id.temprament);
        temp.setText(list.get(4));

        ImageView profilePic = findViewById(R.id.catImage);

        String imageUrl = list.get(5);
        String wikiUrl = list.get(6);

        if (!imageUrl.isEmpty() || !imageUrl.equals(""))
        {
            Picasso.get()
                    .load(imageUrl)
                    .resize(900, 900)
                    .centerCrop()
                    .into(profilePic);
        }
        else
        {
            profilePic.setImageResource(R.drawable.logo);
        }

        Button wiki = findViewById(R.id.wikiButton);
        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!wikiUrl.isEmpty() || !wikiUrl.equals(""))
                {
                    try{
                        Uri uri = Uri.parse(wikiUrl);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    }catch (Exception e)
                    {
                        TextView err = findViewById(R.id.err);
                        err.setText(R.string.err_message);
                    }
                }
                else
                {
                    TextView err = findViewById(R.id.err);
                    err.setText(R.string.err_message);
                }
            }
        });
    }
}