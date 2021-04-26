package com.example.ahmeddiab_180470;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateSQL extends AppCompatActivity {
    DatabaseHelper myData;
    EditText id, field, newVal,deleteID;
    Button Update, delete,back;
    Weather weatherimage=new Weather();

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myData= new DatabaseHelper(this);
        setContentView(R.layout.activity_update_s_q_l);
        id = (EditText) findViewById(R.id.editUpdateIDSQL);
        newVal = (EditText) findViewById(R.id.editUpdateRequiredSQL);
        deleteID=(EditText)findViewById(R.id.editDeleteValSQL);
        Update = (Button) findViewById(R.id.bttnupdateSQL);
        delete = (Button) findViewById(R.id.bttndeleteSQL);
        back = (Button) findViewById(R.id.bttnbackSQL);
        img=(ImageView)findViewById(R.id.weatherimage);//linking to UI
        img.setImageResource(weatherimage.getImgResource());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateSQL.this, MainActivity.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result=myData.delete(deleteID.getText().toString()); //returns number of deleted rows

                if(result>=1)
                    Toast.makeText(UpdateSQL.this,+result+"Rows susscessfully deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UpdateSQL.this,"Please try again",Toast.LENGTH_LONG).show();

            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myData.update(id.getText().toString(),newVal.getText().toString());
            }
        });
    }
}