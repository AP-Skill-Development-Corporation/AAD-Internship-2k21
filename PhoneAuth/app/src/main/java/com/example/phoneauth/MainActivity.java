package com.example.phoneauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    EditText number,code;
    FirebaseAuth auth;
    /*To get the Otp , Auto Verification and to capture the failed cases*/
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    /*This token is used to resend the otp*/
    PhoneAuthProvider.ForceResendingToken token;
    /*To store the verification id*/
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.phone);
        code = findViewById(R.id.otp);
        auth = FirebaseAuth.getInstance();
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull  String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                // It holds the verification code
                id = s;
                //It holds the token to resend otp
                token = forceResendingToken;
            }

            @Override
            public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {
                //tp perform Auto Verification
                signPhoneAuth(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull  FirebaseException e) {
                Toast.makeText(MainActivity.this, "Failed",
                        Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void signPhoneAuth(PhoneAuthCredential phoneAuthCredential) {
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this,
                                    HomeActivity.class));
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void send(View view) {
        String n = number.getText().toString();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+91"+n)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        Toast.makeText(this, "Otp Sent", Toast.LENGTH_SHORT).show();
    }

    public void verify(View view) {
        String otp = code.getText().toString().trim();
        if (otp.isEmpty()){
            code.setError("Can't be Empty");
        }else{
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id,otp);
            signPhoneAuth(credential);
        }
    }

    public void resend(View view) {
        String n = number.getText().toString();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+91"+n)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .setForceResendingToken(token)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        Toast.makeText(this, "Otp Re-Sent", Toast.LENGTH_SHORT).show();
    }
}