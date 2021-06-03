package com.example.inputcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //Declare all the views here
    EditText name,mail,number;
    Spinner branch;
    RadioGroup rg;
    RadioButton rb;
    CheckBox tel,hin,eng;
    TextView dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialise all the views here
        name = findViewById(R.id.name);
        mail=findViewById(R.id.email);
        number = findViewById(R.id.number);
        branch = findViewById(R.id.sp);
        rg = findViewById(R.id.rg);
        tel = findViewById(R.id.tel);
        hin = findViewById(R.id.hin);
        eng = findViewById(R.id.eng);
        dob = findViewById(R.id.dob);
    }

    public void save(View view) {
        String n = name.getText().toString();
        String m = mail.getText().toString().trim();
        String num = number.getText().toString();
        String b = branch.getSelectedItem().toString();
        int id = rg.getCheckedRadioButtonId();
        rb = findViewById(id);
        StringBuilder sb = new StringBuilder();
        if (tel.isChecked()){
            sb.append("Telugu"+"\n");
        }
        if (hin.isChecked()){
            sb.append("Hindi"+"\n");
        }
        if (eng.isChecked()){
            sb.append("English");
        }
        Log.i("APSSDC",n+"\n"+m+"\n"+num+"\n"+b+"\n"+rb.getText()
                +"\n"+sb.toString()+"\n"+dob.getText().toString());
    }

    public void dob(View view) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mnth = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,mnth,day);
        dpd.show();
    }
}