package com.aarafrao.calculatorapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.aarafrao.calculatorapp.Adapters.GridViewAdapter;
import com.aarafrao.calculatorapp.ClickListener;
import com.aarafrao.calculatorapp.Model.ProductModel;
import com.aarafrao.calculatorapp.R;

import java.util.ArrayList;
import java.util.List;

public class HiddenActivity extends AppCompatActivity implements ClickListener {
    private List<ProductModel> list;
    GridViewAdapter gridViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);

        recyclerView = findViewById(R.id.recyclerView);

        list = new ArrayList<>();

        listData();

        gridViewAdapter = new GridViewAdapter(list, this, HiddenActivity.this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HiddenActivity.this, 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridViewAdapter);


    }

    private void listData() {
        list.add(new ProductModel(R.drawable.ic_launcher_background, "Gallery", "Hidden Gallery", "Price"));
        list.add(new ProductModel(R.drawable.ic_launcher_background, "Video", "Hidden Video", "Price"));
        list.add(new ProductModel(R.drawable.ic_launcher_background, "Audio", "Hidden Audio", "Price"));
        list.add(new ProductModel(R.drawable.ic_launcher_background, "Documents", "Hidden Documents", "Price"));
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(this, HiddenActivity2.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}