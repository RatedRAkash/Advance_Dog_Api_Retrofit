package com.example.dog_advance_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;

    List<String> dogBreedNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonId);
        // Agge DogBreedNames gula API theke collect kore DogBreedNames ee dukalam ekn pore eita pass korbo recyclerAdapter er kase
        //DogBreedNames = LoadBreedNamesInView();

        button.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        //RETROFIT er kaaj
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Breed_Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Breed_Api breed_api = retrofit.create(Breed_Api.class);

        Call<BreedClass> call = breed_api.getBreed();

        call.enqueue(new Callback<BreedClass>() {
            @Override
            public void onResponse(Call<BreedClass> call, Response<BreedClass> response) {

                BreedClass breedClass = response.body();

                HashMap<String,ArrayList<String>> Map = breedClass.getMessage();

                dogBreedNames = new ArrayList<>();

                for (HashMap.Entry<String, ArrayList<String>> set : Map.entrySet()) { //HashMap ke iterate korlam
                    //s+= set.getKey() + " ";
                    if(!set.getValue().isEmpty())
                    {
                        for(String str : set.getValue())
                        {
                            String full_name= str+" "+set.getKey();
                            //Log.d("onResponse: ",set.getKey()+"--->"+str);
                            Log.d("onResponse: ",full_name);

                            dogBreedNames.add(full_name);
                        }
                    }

                    else
                    {
                        dogBreedNames.add(set.getKey());
                        Log.d("onResponse: ",set.getKey()+"--->");
                    }

                }

                String names="";
                for(String str : dogBreedNames)
                {
                    names+=" "+str;
                }

                //NEXT Activity te jabo BEGIN
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)dogBreedNames);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
                //NEXT Activity END

            }

            @Override
            public void onFailure(Call<BreedClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //RETROFIT END


    }


}