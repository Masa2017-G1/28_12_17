package com.sheygam.masa_2017_28_12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = findViewById(R.id.imageView);

        Picasso.with(this)
                .load("https://wallpaperscraft.com/image/bridge_rocks_river_city_city_on_the_water_reflection_58661_3840x2400.jpg")
                .into(imageView);
    }
}
