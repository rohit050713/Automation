package com.www.automation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    EditText et;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et);
        btn=findViewById(R.id.btn);

        databaseReference = db.getReference("Data");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senddata();
            }
        });


    }

    public void senddata(){
        String et_data= et.getText().toString().trim();
        String id= databaseReference.push().getKey();

        if(!et_data.isEmpty()){
            Data data= new Data(et_data,id);
            databaseReference.child(id).setValue(data);
            Toast.makeText(this, "Data is send", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Please provide the input", Toast.LENGTH_SHORT).show();
        }




    }
}