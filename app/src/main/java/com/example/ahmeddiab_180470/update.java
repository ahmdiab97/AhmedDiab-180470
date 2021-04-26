package com.example.ahmeddiab_180470;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class update extends AppCompatActivity {
    EditText id, field, newVal,deleteID;
    Button Update, delete,back;
    FirebaseDatabase myRef;
    DatabaseReference reference;
    Weather weatherimage=new Weather();

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Firebase firebase = new Firebase();
        id = (EditText) findViewById(R.id.editUpdateID);
        field = (EditText) findViewById(R.id.editUpdateRequiredfield);
        newVal = (EditText) findViewById(R.id.editUpdateRequired);
        deleteID=(EditText)findViewById(R.id.editDeleteVal);
        Update = (Button) findViewById(R.id.bttnubdate2);
        delete = (Button) findViewById(R.id.bttndelete);
        back = (Button) findViewById(R.id.bttnback);
        img=(ImageView)findViewById(R.id.weatherimage);//linking to UI
        img.setImageResource(weatherimage.getImgResource());
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = id.getText().toString();
                String Field = field.getText().toString();
                String newValue = newVal.getText().toString();

                    if(TextUtils.isEmpty(Id)||TextUtils.isEmpty(Field)||TextUtils.isEmpty(newValue)){
                        Toast.makeText(update.this,"please enter all required feilds",Toast.LENGTH_LONG).show();
                    }
                    else {
                        firebase.change(Id, Field, newValue);
                    }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String delete =deleteID.getText().toString();
                if(TextUtils.isEmpty(delete)){
                    Toast.makeText(update.this,"please enter user id ",Toast.LENGTH_LONG).show();
                }
                else {
                    firebase.delete(delete);
                }
            }
        });
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(update.this,MainActivity.class));
    }
});
    }
}

