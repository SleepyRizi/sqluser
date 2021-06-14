package com.example.sqlstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateRecords extends AppCompatActivity {
    TextInputLayout upfullname,upstudentid,upEmail,upDepartment,upmobile,upAddress;
    Button upbackbtn,btnupdate;
    String name,id,email,department,mobile,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_records);
        init();
        upbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateRecords.this, listRecords.class);
                startActivity(i);
                finish();
            }
        });
        catchintent();
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecordsDB updateDB = new RecordsDB(UpdateRecords.this);
                updateDB.updatedata(id,name,email,mobile,department,address);

            }
        });




    }

    private void catchintent() {
        if(getIntent().hasExtra("name")
                && getIntent().hasExtra("email")
                && getIntent().hasExtra("id")
                && getIntent().hasExtra("mobile")
                && getIntent().hasExtra("address")
                && getIntent().hasExtra("department")){


                name=getIntent().getStringExtra("name");
                id=getIntent().getStringExtra("id");
                email=getIntent().getStringExtra("email");
                department=getIntent().getStringExtra("department");
                mobile=getIntent().getStringExtra("mobile");
                address=getIntent().getStringExtra("address");

                //set data
            upfullname.getEditText().setText(name);
            upstudentid.getEditText().setText(id);
            upEmail.getEditText().setText(email);
            upDepartment.getEditText().setText(department);
            upmobile.getEditText().setText(mobile);
            upAddress.getEditText().setText(address);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void init(){
        upbackbtn = (Button) findViewById(R.id.upbackbutton);
        btnupdate=(Button)findViewById(R.id.btnupdate);
        upfullname=(TextInputLayout)findViewById(R.id.upfullname);
        upstudentid=(TextInputLayout)findViewById(R.id.upstudentid);
        upEmail=(TextInputLayout)findViewById(R.id.upEmail);
        upDepartment=(TextInputLayout)findViewById(R.id.upDepartment);
        upmobile=(TextInputLayout)findViewById(R.id.upmobile);
        upAddress=(TextInputLayout)findViewById(R.id.upAddress);

    }


}