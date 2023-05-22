package com.example.vkmeans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    public EditText t1,t2;
    public CheckBox ch1,ch2;
    DatabaseReference db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        t1=(EditText)findViewById(R.id.name);
        t2=(EditText)findViewById(R.id.num);
        ch1=(CheckBox) findViewById(R.id.c1);
        ch2=(CheckBox) findViewById(R.id.c2);

        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().equals("") || t2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Name or Mobile Number !!", Toast.LENGTH_SHORT).show();
                } else if (!t1.getText().toString().matches("^[a-zA-Z]*$")){
                    Toast.makeText(MainActivity.this, "Enter Valid Name", Toast.LENGTH_SHORT).show();
                }
                else if(t2.getText().toString().length()>10 || t2.getText().toString().length()<10){
                    Toast.makeText(getApplicationContext(), "Enter Valid Mobile Number !!", Toast.LENGTH_SHORT).show();
                }
                else if(ch1.isChecked() && ch2.isChecked()){
                    Toast.makeText(getApplicationContext(), "Select Any one Check box Single or Combo !!!", Toast.LENGTH_SHORT).show();
                } else if (ch1.isChecked()) {
                    Intent i=new Intent(getApplicationContext(),single.class);
                    i.putExtra("name",t1.getText().toString());
                    i.putExtra("num",t2.getText().toString());
                    startActivity(i);
                } else if (ch2.isChecked()) {
                    Intent ii=new Intent(getApplicationContext(),combo.class);
                    ii.putExtra("name",t1.getText().toString());
                    ii.putExtra("num",t2.getText().toString());
                    startActivity(ii);
                }else{
                    Toast.makeText(getApplicationContext(), "Plz Select the Check Box ......", Toast.LENGTH_SHORT).show();
                }
                t1.setText("");
                t2.setText("");
            }
        });

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ShowData.class);
                startActivity(i);
            }
        });
    }
}