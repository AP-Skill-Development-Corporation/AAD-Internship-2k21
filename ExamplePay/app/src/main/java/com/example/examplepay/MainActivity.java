package com.example.examplepay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    EditText cname,camount;
    Checkout checkout;
    String apikey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cname = findViewById(R.id.cname);
        camount = findViewById(R.id.camount);

    }

    public void pay(View view) {
        apikey = "rzp_test_0kx2HIbnBEeOJw";
        String name = cname.getText().toString();
        String amount = camount.getText().toString();

        checkout = new Checkout();
        checkout.setKeyID(apikey);
        try {
            JSONObject object = new JSONObject();
            object.put("name", name);
            object.put("amount",amount);
            object.put("currency","INR");
            object.put("description","This is payment");
            checkout.open(this,object);
        }catch (Exception e){

        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        Log.i("APSSDC",s);
    }

    @Override
    public void onPaymentError(int i, String s) {
      Log.i("APSSDC",s);

    }
}