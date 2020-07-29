package com.www.automation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    EditText et;
    Button btn;
    SwitchCompat switch1;
    SwitchCompat switch2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switch1=findViewById(R.id.switch1);
        switch2=findViewById(R.id.switch2);

        databaseReference = db.getReference("Data");

        read();


        // for on//off the switches
    switch1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(switch1.isChecked()){
                databaseReference.child("S1").setValue(1);
                Toast.makeText(MainActivity.this, "Bulb is on", Toast.LENGTH_SHORT).show();
            }
            else if(!switch1.isChecked()){
                databaseReference.child("S1").setValue(0);
                Toast.makeText(MainActivity.this, "Bulb is off", Toast.LENGTH_SHORT).show();
            }
        }
    });


    switch2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(switch2.isChecked()){
                databaseReference.child("S2").setValue(1);
                Toast.makeText(MainActivity.this, "Fan is on", Toast.LENGTH_SHORT).show();

            }
            else if(!switch2.isChecked()){
                databaseReference.child("S2").setValue(0);
                Toast.makeText(MainActivity.this, "Fan is off", Toast.LENGTH_SHORT).show();
            }
        }
    });

    }

//    public void senddata(){
//        String et_data= et.getText().toString().trim();
//        String id= databaseReference.push().getKey();
//
//        if(!et_data.isEmpty()){
//            Data data= new Data(et_data,id);
//            databaseReference.child(id).setValue(data);
//            Toast.makeText(this, "Data is send", Toast.LENGTH_SHORT).show();
//
//        }
//        else{
//            Toast.makeText(this, "Please provide the input", Toast.LENGTH_SHORT).show();
//        }
//
//
//
//
//    }

    // for reaading the data from the firebase
    public void read(){
        databaseReference= db.getReference().child("Data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s1= dataSnapshot.child("S1").getValue().toString();
                String s2= dataSnapshot.child("S2").getValue().toString();

                if(s1.equals("1")){
                    switch1.setChecked(true);
                }
                else if(s1.equals("0")){
                    switch1.setChecked(false);

                }

                if(s2.equals("1")){
                    switch2.setChecked(true);
                }
                else if(s2.equals("0")){
                    switch2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}