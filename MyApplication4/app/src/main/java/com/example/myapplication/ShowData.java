package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowData extends AppCompatActivity {
    RecyclerView rcv;
    DatabaseReference dbr;
    FirebaseDatabase fd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        rcv=findViewById(R.id.rec);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        fd=FirebaseDatabase.getInstance();
        dbr=fd.getReference("StudentDetails");
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Student,DataHolder> fra=new FirebaseRecyclerAdapter<Student, DataHolder>(Student.class,R.layout.mycustomlayout,DataHolder.class,dbr)
        {
            @Override
            protected void populateViewHolder(DataHolder dataHolder,Student student,int i)
            {
               dataHolder.setView(getApplicationContext(),student.getName(),student.getRoll_no(),student.getEmail(),student.getAdd(),student.getBranch());
            }
        };
        rcv.setAdapter(fra);
    }

}
