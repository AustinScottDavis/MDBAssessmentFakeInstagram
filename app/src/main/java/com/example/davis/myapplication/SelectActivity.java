package com.example.davis.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.net.URI;

public class SelectActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    ImageView temppicture;
    String tempcaption;
    Uri selectedpic;
    EditText editcaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        findViewById(R.id.tempCaption).setVisibility(View.GONE);
        editcaption = findViewById(R.id.tempCaption);
        temppicture = findViewById(R.id.temppicture);
        select();

    }

    public void select() {
        findViewById(R.id.selectImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, REQUEST_CODE);
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent, REQUEST_CODE);
            }
        });

        editcaption.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tempcaption = s.toString();
            }
        });

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post p = new Post(selectedpic, tempcaption);
                ListActivity.allPosts.add(p);
                Intent i = new Intent(SelectActivity.this, ListActivity.class);
                SelectActivity.this.startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //Bundle bundle = data.getExtras();
            //temppicture.setImageBitmap((Bitmap) bundle.get("data"));
            selectedpic = data.getData();
            findViewById(R.id.tempCaption).setVisibility(View.VISIBLE);
            temppicture.setImageURI(selectedpic);
            select();
        }

    }

    public void setPicture() {
        temppicture.setImageURI(selectedpic);
    }
}
