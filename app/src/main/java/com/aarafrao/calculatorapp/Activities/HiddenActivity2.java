package com.aarafrao.calculatorapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aarafrao.calculatorapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class HiddenActivity2 extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden2);

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

    private void createFolder(String name) {
        File mydir = getApplicationContext().getExternalFilesDir(name); //Creating an internal directory;
        if (!mydir.exists()) {
            mydir.mkdirs();
            Toast.makeText(this, "Created" + " -> " + mydir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}
