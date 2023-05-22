package com.example.vkmeans;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class single extends AppCompatActivity {
    public EditText t3;
    public TextView v;
    public Spinner sp;
    DatabaseReference db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        t3=(EditText) findViewById(R.id.size);
        v=(TextView) findViewById(R.id.vi);
        sp=findViewById(R.id.items);
        String[] it=new String[]{"---Select---","Suit","Shrit","Kurta","Pant"};
        ArrayAdapter<String> ad=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,it);
        sp.setAdapter(ad);

        findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3.setText(t3.getText().toString()+"|");
            }
        });
        findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3.setText(t3.getText().toString()+"||");
            }
        });        findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3.setText(t3.getText().toString()+"|||");
            }
        });        findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3.setText(t3.getText().toString()+"||||");
            }
        });
        Intent i=getIntent();
        v.setText("Customer Name: "+i.getStringExtra("name")+"\nMobile Number: "+i.getStringExtra("num"));
        findViewById(R.id.sub1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getSelectedItem().toString().equals("---Select---") || t3.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Select the item in drop down list or Enter the Size !!!!!", Toast.LENGTH_SHORT).show();
                }else{
                    db= FirebaseDatabase.getInstance().getReference().child("single").child(i.getStringExtra("num"));
                    data d=new data(i.getStringExtra("name"),i.getStringExtra("num"),t3.getText().toString(),sp.getSelectedItem().toString());
                    db.setValue(d);
                    Toast.makeText(getApplicationContext(), "Data Saved !!!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        findViewById(R.id.sub2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st="\t\t\t\t\t\uD83C\uDD85 \uD83C\uDD7A \uD83C\uDD7C\uD83C\uDD74\uD83C\uDD70\uD83C\uDD7D\uD83C\uDD82\n\n\tCustomer Name: "
                        +i.getStringExtra("name")+"\n"+"\tMobile Number: "
                        +i.getStringExtra("num")+"\n"+"\tType: "
                        +sp.getSelectedItem().toString()+"\n"+"\tSize: "
                        +t3.getText().toString();
                Intent j=new Intent();
                j.setAction(Intent.ACTION_SEND);
                j.putExtra(Intent.EXTRA_TEXT,st);
                j.setType("text/plain");
                j.setPackage("com.whatsapp");
                startActivity(j);

            }
        });
    }
}