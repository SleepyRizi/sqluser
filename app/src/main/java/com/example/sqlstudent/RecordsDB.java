package com.example.sqlstudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RecordsDB {
    public static final String KEY_NAM="Sname";
    public static final String KEY_ID="Sid";
    public static final String KEY_EMAIL="Semail";
    public static final String KEY_DEPARTMENT="Sdepartment";
    public static final String KEY_MOBILE="Smobile";
    public static final String KEY_ADDRESS="S_address";
    public final String DATABASE_NAME="STUDENT_RECORDS.db";
    public final String DATABASE_TABLE="Record_table";



    private final int DATABASE_VERSION=1;

    public RecordsDB(Context context){
        this.mycontext=context;
    }

    private DBhelper myhelper;
    private Context mycontext;
    private SQLiteDatabase mydatabase;
    private Context helperContext;

    private class DBhelper extends SQLiteOpenHelper{

        public DBhelper(@Nullable  Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlcode = " CREATE TABLE " + DATABASE_TABLE + " ("
                    + KEY_ID + " TEXT PRIMARY KEY, "
                    + KEY_NAM + " TEXT NOT NULL, "
                    + KEY_DEPARTMENT + " TEXT NOT NULL, "
                    + KEY_EMAIL + " TEXT NOT NULL, "
                    + KEY_MOBILE + " TEXT NOT NULL, "
                    + KEY_ADDRESS + " TEXT);" ;
            db.execSQL(sqlcode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
                onCreate(db);
        }
    }

    public RecordsDB open(){
        myhelper = new DBhelper(mycontext);
        mydatabase=myhelper.getWritableDatabase();
        return this;
    }
    public void close(){
        myhelper.close();

    }
    public long addrecords(String name, String email, String mobile, String id, String department, String address){
        ContentValues conval = new ContentValues();
        //SQLiteDatabase db =this.myhelper.getWritableDatabase();
        conval.put(KEY_NAM,name);
        conval.put(KEY_EMAIL,email);
        conval.put(KEY_ID,id);
        conval.put(KEY_MOBILE,mobile);
        conval.put(KEY_ADDRESS,address);
        conval.put(KEY_DEPARTMENT,department);

        long result= mydatabase.insert(DATABASE_TABLE,null,conval);
        if(result==-1){
            Toast.makeText(mycontext, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mycontext, "Data Saved", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

        Cursor readrecords(){
        String code= " SELECT * FROM " + DATABASE_TABLE;
            DBhelper myhelper = new DBhelper(mycontext);

            SQLiteDatabase db = myhelper.getReadableDatabase();


            Cursor cursor = null;

        if(db != null){
            cursor= db.rawQuery(code,null);

        }
        return cursor;
    }
    void updatedata(String id,String name,String email,String mobile,String department,String address){

        DBhelper myhelper = new DBhelper(mycontext);
       SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID,id);
        cv.put(KEY_NAM,name);
        cv.put(KEY_EMAIL,email);
        cv.put(KEY_MOBILE,mobile);
        cv.put(KEY_DEPARTMENT,department);
        cv.put(KEY_ADDRESS,address);


        long result = db.update(DATABASE_TABLE,cv,"Sid=?",new String[]{id});
        if(result == -1){
            Toast.makeText(mycontext, "Something wrong", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mycontext, "Updated successfully", Toast.LENGTH_SHORT).show();
        }
    }


}
