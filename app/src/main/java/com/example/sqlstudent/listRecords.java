package com.example.sqlstudent;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listRecords extends AppCompatActivity {
    ArrayList<String> a_fullname,a_email,a_mobile,a_studentid,a_department,a_address;
    RecordsDB myDB;
    AdapterClass myadapter;
    private RecyclerView rvcycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_records);
        rvcycle = (RecyclerView) findViewById(R.id.rvcycle);

        myDB = new RecordsDB(listRecords.this);

        a_fullname=new ArrayList<>();
        a_email=new ArrayList<>();
        a_mobile=new ArrayList<>();
        a_studentid=new ArrayList<>();
        a_department=new ArrayList<>();
        a_address=new ArrayList<>();
        storingDatainArray();

        //set recyclerview
        myadapter = new AdapterClass(listRecords.this,listRecords.this,a_fullname,a_email,a_mobile,a_studentid,a_department,a_address);
        rvcycle.setLayoutManager(new LinearLayoutManager(listRecords.this));
        rvcycle.setAdapter(myadapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0){
            recreate();
        }
    }

    void storingDatainArray(){
        Cursor cursor = myDB.readrecords();
        if(cursor.getCount()==0){
            Toast.makeText(this, "Nothing to show.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                a_studentid.add(cursor.getString(0));
                a_fullname.add(cursor.getString(1));
                a_department.add(cursor.getString(2));
                a_email.add(cursor.getString(3));
                a_mobile.add(cursor.getString(4));
                a_address.add(cursor.getString(5));
            }
        }
    }

    public void movetoenter(View view) {
        Intent intent = new Intent(listRecords.this, com.example.sqlstudent.MainActivity.class);
        startActivity(intent);
        finish();
    }
}