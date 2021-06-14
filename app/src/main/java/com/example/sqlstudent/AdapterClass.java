package com.example.sqlstudent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
    private final Context context;
    private final ArrayList<String> a_fullname;
    private final ArrayList<String> a_email;
    private final ArrayList<String> a_mobile;
    private final ArrayList<String> a_studentid;
    private final ArrayList<String> a_department;
    private final ArrayList<String> a_address;
    Activity activity;

    AdapterClass(Activity activity,Context context,
                 ArrayList<String> fullname,
                 ArrayList email,
                 ArrayList mobile,
                 ArrayList studentid,
                 ArrayList department,
                 ArrayList address){
        this.activity=activity;
        this.context= context;
        this.a_fullname=fullname;
        this.a_email=email;
        this.a_mobile=mobile;
        this.a_studentid=studentid;
        this.a_department=department;
        this.a_address=address;

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rvid,rvname;
        ConstraintLayout myrecord;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                rvid=(TextView) itemView.findViewById(R.id.rvid);
                rvname=(TextView) itemView.findViewById(R.id.rvname);
                myrecord=(ConstraintLayout)itemView.findViewById(R.id.singleitem);


        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerecorditem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.rvname.setText(a_fullname.get(position));
        holder.rvid.setText(a_studentid.get(position));
        holder.myrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateRecords.class);
                intent.putExtra("name",a_fullname.get(position));
                intent.putExtra("email",a_email.get(position));
                intent.putExtra("department",a_department.get(position));
                intent.putExtra("id",a_studentid.get(position));
                intent.putExtra("mobile",a_mobile.get(position));
                intent.putExtra("address",a_address.get(position));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return a_studentid.size();
    }



}
