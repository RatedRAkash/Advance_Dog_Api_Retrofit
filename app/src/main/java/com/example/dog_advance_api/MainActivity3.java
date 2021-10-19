package com.example.dog_advance_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    String dogBreedName;

    TextView textView;
    ImageView imageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = findViewById(R.id.textViewId);
        button = findViewById(R.id.buttonId);
        imageView = findViewById(R.id.imageViewId);

        //Agger Activity theke Data Nilam
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        dogBreedName = (String) args.getSerializable("SINGLE_STRING");
        //Data newa END

        button.setText(dogBreedName);
        button.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        //RETROFIT er kaaj
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Dog_Api.BASE_URL+dogBreedName+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Dog_Api dog_api = retrofit.create(Dog_Api.class);

        Call<DogClass> call = dog_api.getDogImage();

        call.enqueue(new Callback<DogClass>() {
            @Override
            public void onResponse(Call<DogClass> call, Response<DogClass> response) {

                DogClass dog = response.body();

                textView.setText(dog.getMessage());
                Picasso.with(MainActivity3.this).load(dog.getMessage()).into(imageView);
            }

            @Override
            public void onFailure(Call<DogClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}