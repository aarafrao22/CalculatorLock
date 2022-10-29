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
    // this is the action code we use in our intent,
    // this way we know we're looking at the response from our own action
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
        Intent intent = new Intent();
        intent.setType(IMAGE_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
         intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_MULTIPLE_PICTURE);
    }

    public String getPath(Uri uri) {

        // just some safety built in
        if (uri == null) {
            // perform some logging or show user feedback
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            Log.d(MainActivity.class.getSimpleName(), "Failed to parse image path from image URI " + uri);
            return null;
        }

        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here, thanks to the answer from @mad indicating this is needed for
        // working code based on images selected using other file managers
        return uri.getPath();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTURE) {

                Uri selectedImageUri = data.getData();
                try {
                    selectedImagePreview.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                } catch (IOException e) {
                    Log.e(MainActivity.class.getSimpleName(), "Failed to load image", e);
                }

            } else if (requestCode == SELECT_MULTIPLE_PICTURE) {
                //And in the Result handling check for that parameter:
                if (Intent.ACTION_SEND_MULTIPLE.equals(data.getAction())
                        && data.hasExtra(Intent.EXTRA_STREAM)) {
                    // retrieve a collection of selected images
                    ArrayList<Parcelable> list = data.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                    // iterate over these images
                    if (list != null) {
                        for (Parcelable parcel : list) {
                            Uri uri = (Uri) parcel;
                            // handle the images one by one here
                        }
                    }

                    // for now just show the last picture
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

}
