package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText roll_no, name, add, email, branch;
    Button reg, show;
    FirebaseDatabase fd;
    DatabaseReference dbr;
    int maxid = 0;
    Student s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll_no = findViewById(R.id.roll_no);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        add = findViewById(R.id.add);
        branch = findViewById(R.id.branch);
        reg = findViewById(R.id.register);
        show = findViewById(R.id.show_data);
        s = new Student();
        dbr = FirebaseDatabase.getInstance().getReference().child("StudentDetails");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    maxid = (int) snapshot.getChildrenCount();
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                s.setRoll_no(Integer.parseInt(roll_no.getText().toString()));
                s.setName(name.getText().toString());
                s.setAdd(add.getText().toString());
                s.setBranch(branch.getText().toString());
                s.setEmail(email.getText().toString());
                dbr.child(String.valueOf(maxid + 1)).setValue(s);
                //dbr.setValue(s);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ShowData.class);
                startActivity(intent);
            }
        });
    }
}
