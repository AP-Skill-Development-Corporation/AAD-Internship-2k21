package com.example.firedb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.firedb.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    StorageReference storageReference;
    ActivityMainBinding binding;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_main);*/
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        storageReference = FirebaseStorage.getInstance().getReference()
                .child("Images/"+ UUID.randomUUID().toString());

        binding.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/");
                //for pdf : i.setType("application/pdf")
                i.setAction(Intent.ACTION_GET_CONTENT);
                /*This is used to select an image from your device*/
                startActivityForResult(Intent.createChooser(i,"Select Image"),0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            binding.iv.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(View view) {
    }

    public void fetch(View view) {
    }
}