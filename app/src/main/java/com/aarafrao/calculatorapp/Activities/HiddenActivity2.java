package com.aarafrao.calculatorapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aarafrao.calculatorapp.Model.UserPicture;
import com.aarafrao.calculatorapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HiddenActivity2 extends AppCompatActivity
        implements View.OnClickListener {
    FloatingActionButton floatingActionButton;
    String currentPath = "";

    private static final int SELECT_SINGLE_PICTURE = 101;
    private static final int SELECT_MULTIPLE_PICTURE = 201;
    public static final String IMAGE_TYPE = "image/*";
    private ImageView selectedImagePreview;

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
        selectedImagePreview = findViewById(R.id.selectedImagePreview);
    }

    private void createFolder(String name) {
        File mydir = getApplicationContext().getExternalFilesDir(name); //Creating an internal directory;
        if (!mydir.exists()) {
            mydir.mkdirs();
            Toast.makeText(this, "Created" + " -> " + mydir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } else {
            currentPath = mydir.getPath();
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
        Intent intent = new Intent();
        intent.setType(IMAGE_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_MULTIPLE_PICTURE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_MULTIPLE_PICTURE) {
                if (Intent.ACTION_SEND_MULTIPLE.equals(data.getAction())
                        && data.hasExtra(Intent.EXTRA_STREAM)) {

                    ArrayList<Parcelable> list = data.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                    if (list != null) {
                        for (Parcelable parcel : list) {
                            Uri uri = (Uri) parcel;
                            moveFiles(uri);

                        }
                    }

                    if (!list.isEmpty()) {
                        Uri imageUri = (Uri) list.get(list.size() - 1);

                        try {
                            selectedImagePreview.setImageBitmap(new UserPicture(imageUri, getContentResolver()).getBitmap());
                        } catch (IOException e) {
                            Log.e(MainActivity.class.getSimpleName(), "Failed to load image", e);
                        }
                    }
                }
            }
        } else {
            // report failure
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            Log.d(MainActivity.class.getSimpleName(), "Failed to get intent data, result code is " + resultCode);
        }
    }

    private void moveFiles(Uri uri) {
        File from = new File(String.valueOf(uri));
        File to = new File(currentPath);
        from.renameTo(to);
    }

}
