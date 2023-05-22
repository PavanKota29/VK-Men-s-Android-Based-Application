package com.example.vkmeans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ShowData extends AppCompatActivity {
    public TextView t1,t2,t3;
    public EditText ed;
    public String temp1="",temp2="",st="",name1="\n",num1="\n",typ1="\n",name="\n",num="\n",typ="\n";
    public DatabaseReference db,db1,db2;
    boolean f1=false,f2=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        t1=(TextView) findViewById(R.id.name);
        t2=(TextView) findViewById(R.id.num);
        t3=(TextView) findViewById(R.id.typ);
        ed=(EditText) findViewById(R.id.tv);
        disply();

        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed.getText().toString().equals("")){
                    name="";
                    num="";
                    typ="";
                    disply();
                }
            }
        });
        findViewById(R.id.find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db1=FirebaseDatabase.getInstance().getReference().child("single");
                db2=FirebaseDatabase.getInstance().getReference().child("combo");
                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot s:snapshot.getChildren()){
                                    if(s.child("num").getValue(String.class).equals(ed.getText().toString())){
                                        f1=true;
                                        name1=s.child("name").getValue(String.class)+"\n";
                                        num1=s.child("num").getValue(String.class)+"\n";
                                        typ1=s.child("typ").getValue(String.class)+"\n";
                                        temp1=s.child("size").getValue(String.class);


                                }

                        }
                        if(!ed.getText().toString().equals("") && f1==true) {
                            t1.setText(name1);
                            t2.setText(num1);
                            t3.setText(typ1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                db2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot s:snapshot.getChildren()){
                                if(s.child("num").getValue(String.class).equals(ed.getText().toString())){
                                    f2=true;
                                    name1=s.child("name").getValue(String.class)+"\n";
                                    num1=s.child("num").getValue(String.class)+"\n";
                                    typ1=s.child("typ").getValue(String.class)+"\n";
                                    HashMap<String,String> c=new HashMap<>();
                                    c=(HashMap) s.child("size").getValue(Object.class);
                                    for(Map.Entry<String,String> e:c.entrySet()){
                                        temp2=temp2+e.getKey()+":"+e.getValue()+"\n\t";
                                    }

                            }
                        }
                        if(!ed.getText().toString().equals("") && f2==true) {
                            t1.setText(name1);
                            t2.setText(num1);
                            t3.setText(typ1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if(ed.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Mobile Number !!!", Toast.LENGTH_SHORT).show();
                }
                f1=false;
                f2=false;
            }
        });
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ed.getText().toString().equals("")) {
                    if (f1==true) {
                        st = "\t\t\t\t\t\uD83C\uDD85 \uD83C\uDD7A \uD83C\uDD7C\uD83C\uDD74\uD83C\uDD70\uD83C\uDD7D\uD83C\uDD82\n\n\tCustomer Name: "
                                + name1 + "\tMobile Number: "
                                + num1 + "\tType: "
                                + typ1 + "\tSize: "
                                + temp1;
                        Intent j = new Intent();
                        j.setAction(Intent.ACTION_SEND);
                        j.putExtra(Intent.EXTRA_TEXT, st);
                        j.setType("text/plain");
                        j.setPackage("com.whatsapp");
                        startActivity(j);
                        temp1 = "";
                    } else if (f2==true) {
                        st = "\t\t\t\t\t\uD83C\uDD85 \uD83C\uDD7A \uD83C\uDD7C\uD83C\uDD74\uD83C\uDD70\uD83C\uDD7D\uD83C\uDD82\n\n\tCustomer Name: "
                                + name1 + "\tMobile Number: "
                                + num1 + "\tType: "
                                + typ1 + "\tSize: \n" +
                                "\t" + temp2;
                        temp2 = "";
                        Intent j = new Intent();
                        j.setAction(Intent.ACTION_SEND);
                        j.putExtra(Intent.EXTRA_TEXT, st);
                        j.setType("text/plain");
                        j.setPackage("com.whatsapp");
                        startActivity(j);


                    }else{
                        Toast.makeText(getApplicationContext(), "Data Not Found !! ", Toast.LENGTH_SHORT).show();
                        f1=false;
                        f2=false;
                        name="";num="";typ="";
                        disply();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Enter Mobile Number !!", Toast.LENGTH_SHORT).show();
                    f1=false;
                    f2=false;
                    name="";num="";typ="";
                    disply();

                }
            }
        });

    }
    public void disply(){
        db=FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snp:snapshot.getChildren()){
                    for(DataSnapshot s:snp.getChildren()){
                        name=name+s.child("name").getValue(String.class)+"\n";
                        num=num+s.child("num").getValue(String.class)+"\n";
                        typ=typ+s.child("typ").getValue(String.class)+"\n";
                    }
                }
                t1.setText(name);
                t2.setText(num);
                t3.setText(typ);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

