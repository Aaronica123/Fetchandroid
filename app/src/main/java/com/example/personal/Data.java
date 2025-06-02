package com.example.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Data extends AppCompatActivity {
TextView text1,text2,text3;
String take1,fetch1,fetch2,fetch3;

String hold,hold1;
EditText fetch,name,marks;
Button butt,butt1,butt2;

BeanM3 bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);
     text1=findViewById(R.id.text1);
     text2=findViewById(R.id.text2);
     text3=findViewById(R.id.text3);
     fetch=findViewById(R.id.take);
        butt=findViewById(R.id.Result);
     take1=fetch.getText().toString();
     butt2=findViewById(R.id.packag);
     butt1=findViewById(R.id.retrieve);

     name=findViewById(R.id.fname);
     marks=findViewById(R.id.marks);


     butt1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(Data.this,ResultsDisplay.class);
             startActivity(intent);
         }
     });

     butt2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             hold=name.getText().toString();
             hold1=marks.getText().toString();
             bean=new BeanM3(hold,hold1);
             taketoFirebase(bean);
         }
     });

         butt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            fetchFirebase();
        }
    });
    }

    private void taketoFirebase(BeanM3 bean) {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference results=db.getReference("message");
        DatabaseReference hold=results.child("Marks");
        if(bean!=null){
            DatabaseReference Entry = hold.push();
            Entry.setValue(bean);
            Toast.makeText(Data.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    private void fetchFirebase() {
        take1=fetch.getText().toString();
        if (take1.isEmpty()) {
            Toast.makeText(this, "Please enter an ID to search.", Toast.LENGTH_SHORT).show();
            text1.setText("No ID entered.");
            text2.setText("");
            text3.setText("");
            return; // Exit the method if no ID is entered
        }
        FirebaseDatabase date1=FirebaseDatabase.getInstance();
        DatabaseReference jog=date1.getReference("message").child("Table");
        Query query1=jog.orderByChild("text3").equalTo(take1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Toast.makeText(Data.this, "Success", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()){
                        BeanM2 bean1=childSnapshot.getValue(BeanM2.class);
                        Toast.makeText(Data.this, "Success1", Toast.LENGTH_SHORT).show();

                        if (bean1 != null){
                            fetch1= bean1.getText1();
                            fetch2= bean1.getText2();
                            fetch3= bean1.getText3();

                            text1.setText("Name: " + fetch1);
                            text2.setText("Email: " + fetch2);
                            text3.setText("ID: " + fetch3);
                        }

                    }

                    Toast.makeText(Data.this, "Success1", Toast.LENGTH_SHORT).show();
                }/*
                    for (DataSnapshot childSnapshot : snapshot.getChildren()){
                        BeanM2 bean=childSnapshot.getValue(BeanM2.class);

                        if (bean != null) {
                            // Data mapping successful, retrieve values and ensure they are not null
                            fetch1 = bean.getText1() != null ? bean.getText1() : "N/A";
                            fetch2 = bean.getText2() != null ? bean.getText2() : "N/A";
                            fetch3 = bean.getText3() != null ? bean.getText3() : "N/A";

                            // Set the retrieved text to the respective TextViews
                            text1.setText("Name: " + fetch1);
                            text2.setText("Email: " + fetch2);
                            text3.setText("ID: " + fetch3);
                            break; // Assuming you only want to display the first match
                        }*/
                        else {
                    Toast.makeText(Data.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}