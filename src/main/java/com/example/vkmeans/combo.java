package com.example.vkmeans;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class combo extends AppCompatActivity {
    public EditText t3,t4;
    public Spinner sp;
    public TextView v;
    public TextView t1,t2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);
        sp=findViewById(R.id.items);
        t3=(EditText) findViewById(R.id.size);
        t4=(EditText)findViewById(R.id.size1);
        t1=(TextView) findViewById(R.id.typ1);
        t2=(TextView) findViewById(R.id.typ2);
        List<String> li=new ArrayList<String>();
        li.add("---Select---");
        li.add("Suit & Pant");
        li.add("Shrit & Pant");
        li.add("Kurta & Pant");
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,li);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
        v=(TextView) findViewById(R.id.vi);
        Intent i=getIntent();
        v.setText("Customer Name: "+i.getStringExtra("name")+"\nMobile Number: "+i.getStringExtra("num"));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Suit & Pant")){
                    t1.setText("For Suit");
                    t2.setText("For Pant");
                } else if (adapterView.getItemAtPosition(i).toString().equals("Shrit & Pant")) {
                    t1.setText("For Shrit");
                    t2.setText("For Pant");
                } else if (adapterView.getItemAtPosition(i).toString().equals("Kurta & Pant")) {
                    t1.setText("For Kurta");
                    t2.setText("For Pant");
                }else{
                    t1.setText("");
                    t2.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

        findViewById(R.id.one1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4.setText(t4.getText().toString()+"|");
            }
        });
        findViewById(R.id.two2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4.setText(t4.getText().toString()+"||");
            }
        });        findViewById(R.id.three3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4.setText(t4.getText().toString()+"|||");
            }
        });        findViewById(R.id.four4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4.setText(t4.getText().toString()+"||||");
            }
        });

        findViewById(R.id.sub2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st="\t\t\t\t\t\uD83C\uDD85 \uD83C\uDD7A \uD83C\uDD7C\uD83C\uDD74\uD83C\uDD70\uD83C\uDD7D\uD83C\uDD82\n\n\tCustomer Name: "
                        +i.getStringExtra("name")+"\n"+"\tMobile Number: "
                        +i.getStringExtra("num")+"\n"+"\tType: "
                        +sp.getSelectedItem().toString()+"\n"+"\t"+t1.getText().toString()+": "+t3.getText().toString()+"\n"+
                        "\t"+t2.getText().toString()+": "+t4.getText().toString();
                Intent j=new Intent();
                j.setAction(Intent.ACTION_SEND);
                j.putExtra(Intent.EXTRA_TEXT,st);
                j.setType("text/plain");
                j.setPackage("com.whatsapp");
                startActivity(j);
            }
        });

        findViewById(R.id.sub1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t3.getText().toString().equals("") || t4.getText().toString().equals("") || sp.getSelectedItem().toString().equals("---Select---")){
                    Toast.makeText(getApplicationContext(), "Enter Size of Customer Or Select the Item in dropdown list !!", Toast.LENGTH_SHORT).show();
                }else{
                    data1 d=new data1(i.getStringExtra("name").toString(),i.getStringExtra("num").toString(),sp.getSelectedItem().toString());
                    DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("combo").child(i.getStringExtra("num").toString());
                    db.setValue(d);
                    Map<String,String> c=new HashMap<>();
                    c.put(t1.getText().toString(),t3.getText().toString());
                    c.put(t2.getText().toString(),t4.getText().toString());
                    db.child("size").setValue(c);
                    Toast.makeText(getApplicationContext(), "Data Saved !!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}