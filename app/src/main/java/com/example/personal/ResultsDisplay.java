package com.example.personal;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.widget.ListView;
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

import java.util.ArrayList;

public class ResultsDisplay extends AppCompatActivity {
ListView list1;
ArrayList<BeanM3> act;

adapterRes adapt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results_display);
list1=findViewById(R.id.list1);
act=new ArrayList<>();
adapt2=new adapterRes(this, R.layout.display,act);

list1.setAdapter(adapt2);
fetchDB();



    }

    private void fetchDB() {
        FirebaseDatabase june=FirebaseDatabase.getInstance();
        DatabaseReference jump=june.getReference("message").child("Marks");

        jump.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                act.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren() ){
                    BeanM3 beanM3=snapshot1.getValue(BeanM3.class);
                    act.add(beanM3);

                           }
                adapt2.notifyDataSetChanged();
                list1.setVisibility(VISIBLE);
                if(act.isEmpty()){
                    Toast.makeText(ResultsDisplay.this, "Notfound", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ResultsDisplay.this, "found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}