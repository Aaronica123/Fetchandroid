package com.example.personal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
EditText reg1,reg2,reg3;
Button fetch,send;
String streg1,streg2,streg3;

BeanM bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
reg1=findViewById(R.id.Reg1);
reg2=findViewById(R.id.Reg2);
reg3=findViewById(R.id.Reg3);
send=findViewById(R.id.Send);
        fetch = findViewById(R.id.Fetch);




        send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        streg1=reg1.getText().toString();
        streg2=reg2.getText().toString();
        streg3=reg3.getText().toString();
        bean=new BeanM(streg1,streg2,streg3);
        sendtoFirebase(bean);
    }
});
    }

    public void jump5(View view){
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, Resu.class);
        startActivity(intent);
    }
    private void sendtoFirebase(BeanM bean) {
FirebaseDatabase database= FirebaseDatabase.getInstance();
DatabaseReference ref=database.getReference("message");

if(bean!=null){
    ref.push().setValue(bean);


}
    }
}