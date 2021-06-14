package com.example.sqlstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextInputLayout edfullname,edemail,edmobile,edstudentid,edDepartment,edAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


    public void init(){

        edfullname = (TextInputLayout) findViewById(R.id.edfullname);
        edemail = (TextInputLayout) findViewById(R.id.edEmail);
        edmobile = (TextInputLayout) findViewById(R.id.edmobile);
        edstudentid = (TextInputLayout) findViewById(R.id.edstudentid);
        edDepartment = (TextInputLayout) findViewById(R.id.edDepartment);
        edAddress = (TextInputLayout) findViewById(R.id.edAddress);
    }

    //crossImage
    public void movetolist(View view) {
        Intent i = new Intent(MainActivity.this,com.example.sqlstudent.listRecords.class);
        startActivity(i);
        finish();
    }


    public void btnSave(View view) {
        String name = edfullname.getEditText().getText().toString().toUpperCase().trim();
        String email = edemail.getEditText().getText().toString().trim();
        String mobile = edmobile.getEditText().getText().toString().trim();
        String id = edstudentid.getEditText().getText().toString().trim();
        String department = edDepartment.getEditText().getText().toString().toUpperCase().trim();
        String address = edAddress.getEditText().getText().toString().trim();

        RecordsDB recordsDB = new RecordsDB(this);
        recordsDB.open();
        recordsDB.addrecords(name,email,mobile,id,department,address);
        recordsDB.close();


    }

    public void btnBack(View view) {
        Intent i = new Intent(MainActivity.this,com.example.sqlstudent.listRecords.class);
        startActivity(i);
        finish();

    }

}