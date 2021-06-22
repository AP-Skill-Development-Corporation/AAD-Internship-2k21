package com.example.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText mail,pass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        String email = mail.getText().toString().trim();
        String password = pass.getText().toString();
        if (email.isEmpty()|password.isEmpty()){
            Toast.makeText(this, "Fill all the details",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(MainActivity.this
                                        ,HomeActivity.class));
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Authentication Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void register(View view) {
        startActivity(new Intent(this,RegisterActivity.class));

    }

    public void reset(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.reset,null,false);
        EditText email = v.findViewById(R.id.editEmailAddress);
        b.setView(v);
        b.setCancelable(false);
        b.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String e = email.getText().toString();
                if (e.isEmpty()){
                    email.setError("Can't be Empty");
                }else{
                    auth.sendPasswordResetEmail(e).addOnCompleteListener(MainActivity.this,
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(MainActivity.this,
                                                "Reset Link Sent", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(MainActivity.this, "Invalid Mail",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.show();
    }
}