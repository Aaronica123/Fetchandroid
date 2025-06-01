package com.example.personal;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.widget.ListView;
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

import java.util.ArrayList;

public class Resu extends AppCompatActivity {
ListView list;

ArrayList<BeanM> acting;

TextView text;
adapterM adapt1;

    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resu);
        list=findViewById(R.id.list);
        acting=new ArrayList<>();
        text=findViewById(R.id.text);

     adapt1=new adapterM(this,R.layout.refer,acting);
     list.setAdapter(adapt1);


        mDatabase = FirebaseDatabase.getInstance().getReference("message");

        fetchfromDatabase();





    }

    private void fetchfromDatabase() {
        text.setVisibility(VISIBLE);
        list.setVisibility(GONE);
mDatabase.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
acting.clear();
for(DataSnapshot snapshot1 : snapshot.getChildren() ){
    BeanM beanM=snapshot1.getValue(BeanM.class);
  // Add null check for the retrieved object
        acting.add(beanM);

}
adapt1.notifyDataSetChanged();
        list.setVisibility(VISIBLE);
        text.setVisibility(GONE);

if(acting.isEmpty()){
    Toast.makeText(Resu.this, "Notfound", Toast.LENGTH_SHORT).show();
}else{
    Toast.makeText(Resu.this, "found", Toast.LENGTH_SHORT).show();
}

    }


    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(Resu.this, "fail", Toast.LENGTH_SHORT).show();
    }
});
    }
}