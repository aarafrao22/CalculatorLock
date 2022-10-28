package com.aarafrao.calculatorapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aarafrao.calculatorapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class HiddenActivity2 extends AppCompatActivity
        implements View.OnClickListener {
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden2);

        initId();
        clickListeners();

        int context = getIntent().getIntExtra("position", 10);
        switch (context) {
            case 0:
                //gallery
                createFolder(".images");

                break;
            case 1:
                //Video
                createFolder(".videos");

                break;
            case 2:
                //Audio
                createFolder(".audios");

                break;

            case 3:
                //document
                createFolder(".document");
                break;
        }

    }

    private void clickListeners() {
        floatingActionButton.setOnClickListener(this);
    }

    private void initId() {

        floatingActionButton = findViewById(R.id.floatingActionButton);
    }

    private void createFolder(String name) {
        File mydir = getApplicationContext().getExternalFilesDir(name); //Creating an internal directory;
        if (!mydir.exists()) {
            mydir.mkdirs();
            Toast.makeText(this, "Created" + " -> " + mydir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.floatingActionButton:
                openFileManager();
                break;
        }
    }

    private void openFileManager() {
    }
}
