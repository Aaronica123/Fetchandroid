package com.example.personal;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Confirm extends AppCompatActivity {
EditText login;
Button butt;
BeanM3 bean1;
String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm);
    login=findViewById(R.id.Login1);
    butt=findViewById(R.id.Submitt);


butt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        id=login.getText().toString();
        bean1=new BeanM3(id);
        checkfromfirebase(id);
    }
});
    }

    private void checkfromfirebase(String id) {
        String id1=id;
        FirebaseDatabase ref1=FirebaseDatabase.getInstance();
        DatabaseReference fre=ref1.getReference("message").child("Table");
        Query query1=fre.orderByChild("text3").equalTo(id1);

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() ){
                    Toast.makeText(Confirm.this, "Exists", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Confirm.this, "Void", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}