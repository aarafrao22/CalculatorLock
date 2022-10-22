package com.aarafrao.calculatorapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aarafrao.calculatorapp.Adapters.GridViewAdapter;
import com.aarafrao.calculatorapp.Model.ProductModel;
import com.aarafrao.calculatorapp.R;

import java.util.ArrayList;
import java.util.List;

public class HiddenActivity extends AppCompatActivity {
    private List<ProductModel> list ;
    GridViewAdapter gridViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);

        recyclerView = findViewById(R.id.recyclerView);

        list= new ArrayList<>();


        listData();
        gridViewAdapter = new GridViewAdapter(list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HiddenActivity.this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridViewAdapter);


    }

    private void listData() {
        list.add(new ProductModel(R.drawable.ic_launcher_background,"TItle","Desc","Price"));
        list.add(new ProductModel(R.drawable.ic_launcher_background,"TItle","Desc","Price"));
        list.add(new ProductModel(R.drawable.ic_launcher_background,"TItle","Desc","Price"));
        list.add(new ProductModel(R.drawable.ic_launcher_background,"TItle","Desc","Price"));
        list.add(new ProductModel(R.drawable.ic_launcher_background,"TItle","Desc","Price"));
    }
}