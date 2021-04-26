package com.example.ahmeddiab_180470;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myData;
   EditText userId,firstName,lastName, phoneNumber,email;
   Button addFB,updateFB,viewFB,addSQL,updateSQL,viewSQL,back,fetch;
    Weather weatherimage=new Weather();

    ImageView img;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myData= new DatabaseHelper(this);
        Firebase firebase = new Firebase();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        mDatabase = FirebaseDatabase.getInstance();
        img=(ImageView)findViewById(R.id.weatherimage);//linking to UI
        img.setImageResource(weatherimage.getImgResource());
        userId=(EditText) findViewById(R.id.EdituserId);
        firstName=(EditText)findViewById(R.id.editfirstName);
        lastName=(EditText)findViewById(R.id.editlastName);
        phoneNumber=(EditText)findViewById(R.id.editTextPhone);
        email=(EditText)findViewById(R.id.editEmailAddress);
        addFB=(Button)findViewById(R.id.bttnAddFB);
        updateFB=(Button)findViewById(R.id.bttnUpdateFB);
        viewFB=(Button)findViewById(R.id.bttnView);
        addSQL=(Button)findViewById(R.id.bttnAddlocal);
        updateSQL=(Button)findViewById(R.id.bttnUpdatelocal);
        viewSQL=(Button)findViewById(R.id.bttnViewlocal);
        fetch=(Button)findViewById(R.id.bttnfetchData);
        back=(Button)findViewById(R.id.bttnBack);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Start.class));
            }
        });
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchData(myData);
            }
        });
        addFB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String Id=userId.getText().toString();
               String FName=firstName.getText().toString();
               String LName=lastName.getText().toString();
               String Email=email.getText().toString();
               String Phone=phoneNumber.getText().toString();
               if(TextUtils.isEmpty(Id)||TextUtils.isEmpty(FName)||TextUtils.isEmpty(LName)||TextUtils.isEmpty(Phone)||TextUtils.isEmpty(Email)){
                   Toast.makeText(MainActivity.this,"please enter all required feilds",Toast.LENGTH_LONG).show();
               }
               else {
               firebase.writeNewUser(Id,Id,Phone,LName,FName,Email);
           }}
       });
            updateFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, update.class));

                            }
            });
            viewFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, ViewData.class));
                }
            });
            addSQL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (myData.addData(userId.getText().toString(), firstName.getText().toString(), lastName.getText().toString(),
                                phoneNumber.getText().toString(), email.getText().toString()) == false) {
                            Toast.makeText(MainActivity.this, "User: " + Integer.parseInt(userId.getText().toString())
                                    + " already exists!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "User " + Integer.parseInt(userId.getText().toString())
                                    + " was successfully added", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Make sure you enter all inputs ", Toast.LENGTH_LONG).show();
                    }
                }
            });

        updateSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UpdateSQL.class));

            }
        });
        viewSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewSQL.class));

            }
        });
        }
    public void fetchData(DatabaseHelper myDB){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String key = ds.getKey();
                        String email = ds.child("emailAddress").getValue().toString();
                        String fname = ds.child("firstName").getValue().toString();
                        String id=ds.child("id").getValue().toString();
                        String lName=ds.child("lastName").getValue().toString();
                        String phone=ds.child("phoneNumber").getValue().toString();
                        myDB.addData(id,fname,lName,phone,email);
                    }


                }catch (Exception e) {
                    Log.d("Ahmed", "for loop exception " + e.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    }





