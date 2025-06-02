package com.example.personal;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Log extends AppCompatActivity {
LinearLayout main1,main2,main3,main0,mainr;

EditText name,email;
TextView text;
Button butt,butt1,check;
BeanM2 bean;
    BeanM3 bean1;
String stname, stemail,stid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log);
main1=findViewById(R.id.main);
main2=findViewById(R.id.main2);
main3=findViewById(R.id.main3);
main0=findViewById(R.id.main0);
mainr=findViewById(R.id.mainr);
mainr.setVisibility(GONE);
        main3.setVisibility(GONE);
main0.setVisibility(GONE);
name=findViewById(R.id.Name);
email=findViewById(R.id.Email);
text=findViewById(R.id.value);
        butt=findViewById(R.id.Submit);
butt1=findViewById(R.id.Login);

check=findViewById(R.id.Check);
        generateUniqueRandomId();
check.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        stname=name.getText().toString();

        stemail=email.getText().toString();
        if(!stemail.isEmpty() && !stname.isEmpty()){
            main3.setVisibility(VISIBLE);
            main0.setVisibility(VISIBLE);

        }
        else{
            main3.setVisibility(GONE);
            main0.setVisibility(GONE);
        }
    }
});
butt.setOnClickListener(new View.OnClickListener() {
    @Override

    public void onClick(View v) {
        stname=name.getText().toString();

        stemail=email.getText().toString();


        stid=text.getText().toString();
mainr.setVisibility(VISIBLE);
            bean=new BeanM2(stname,stemail,stid);
setFirebase(bean);



    }
});

    }
    private boolean check(String j){
        boolean fir=false;
        String p="";

        if(j.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }


    private void setFirebase1(BeanM3 bean) {
        FirebaseDatabase data= FirebaseDatabase.getInstance();
        DatabaseReference ref1=data.getReference("message");
        DatabaseReference next=ref1.child("Table");
        if(bean!=null){
            DatabaseReference newEntryRef = next.push();
            newEntryRef.setValue(bean);
            Toast.makeText(Log.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();

        }

    }
    private void setFirebase(BeanM2 bean) {
        FirebaseDatabase data= FirebaseDatabase.getInstance();
        DatabaseReference ref1=data.getReference("message");
DatabaseReference next=ref1.child("Table");
        if(bean!=null){
            DatabaseReference newEntryRef = next.push();
            newEntryRef.setValue(bean);
            Toast.makeText(Log.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();

        }

    }
    public void jump7(View view){
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, Confirm.class);
        startActivity(intent);
    }
    private void generateUniqueRandomId() {
        Random random = new Random();
        int min = 100; // Smallest 5-digit number
        int max = 999; // Largest 5-digit number
        String generatedId = String.valueOf(random.nextInt(max - min + 1) + min);
        FirebaseDatabase data= FirebaseDatabase.getInstance();
        DatabaseReference ref1=data.getReference("message");
        DatabaseReference next=ref1.child("Table");
    next.child(generatedId).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                generateUniqueRandomId();

            }
            else{

                text.setText(generatedId);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

    }

    }