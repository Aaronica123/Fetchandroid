package com.example.personal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
Button fetch,send,login;
String streg1,streg2,streg3;

BeanM bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

send=findViewById(R.id.Send);

login=findViewById(R.id.Login);






    }

    public void jump6(View view){
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, Confirm.class);
        startActivity(intent);
    }

    public void jump5(View view){
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, Log.class);
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