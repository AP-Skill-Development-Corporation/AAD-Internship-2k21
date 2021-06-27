package com.example.firedb;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.firedb.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

        ActivityResultLauncher<String> mgetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                uri = result;
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    binding.iv.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        binding.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mgetContent.launch("image/*");
            }
        });
    }

    public void save(View view) {
        storageReference.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        String name = binding.name.getText().toString();
                        String mail = binding.mail.getText().toString();
                        String num = binding.number.getText().toString();
                        MyModel myModel = new MyModel(url,name,mail,num);

                      /*  String id = databaseReference.push().getKey();*/
                        databaseReference.child(num).setValue(myModel);
                        Toast.makeText(MainActivity.this, "Data Inserted",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void fetch(View view) {
        startActivity(new Intent(this,DisplayActivity.class));
    }
}