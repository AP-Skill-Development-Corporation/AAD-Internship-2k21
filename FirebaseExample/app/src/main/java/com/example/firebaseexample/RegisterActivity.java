package com.example.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText rmail,rpass,rrepass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rmail = findViewById(R.id.rmail);
        rpass = findViewById(R.id.rpass);
        rrepass = findViewById(R.id.rrepass);
        auth = FirebaseAuth.getInstance();
    }

    public void signup(View view) {
        String mail = rmail.getText().toString().trim();
        String pass = rpass.getText().toString();
        String repass = rrepass.getText().toString();
        if (mail.isEmpty()|pass.isEmpty()|repass.isEmpty()){
            Toast.makeText(this, "Please fill all the details",
                    Toast.LENGTH_SHORT).show();
        }else if(pass.length()<6){
            rpass.setError("Minimum 6 digits");
        }
        else if(!pass.equals(repass)){
           rrepass.setError("Not same!!!");
        }else{
            auth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(RegisterActivity.this,
                                        MainActivity.class));
                                Toast.makeText(RegisterActivity.this, "Registered"
                                        , Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}