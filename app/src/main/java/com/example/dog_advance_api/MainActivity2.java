package com.example.dog_advance_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements RecyclerViewClickInterface{

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<String> dogBreedNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Agger Activity theke Data Nilam
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        dogBreedNames = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        //Data newa END


        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(dogBreedNames,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, dogBreedNames.get(position), Toast.LENGTH_SHORT).show();

        //NEXT Activity te jabo BEGIN
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        Bundle args = new Bundle();
        args.putSerializable("SINGLE_STRING",(Serializable)dogBreedNames.get(position));
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
        //NEXT Activity END

    }

    @Override
    public void onLongItemClick(int position) {

    }
}