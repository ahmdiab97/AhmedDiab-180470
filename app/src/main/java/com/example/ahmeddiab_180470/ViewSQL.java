package com.example.ahmeddiab_180470;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewSQL extends AppCompatActivity {
    DatabaseHelper myData;
    ArrayList<String> list;
    ArrayList<String> list2;
    ListView view;
    ArrayAdapter <String>arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myData= new DatabaseHelper(this);
        setContentView(R.layout.activity_view_s_q_l);
        Button back =(Button)findViewById(R.id.buttonback) ;
        list =new ArrayList<String>();
        list2 =new ArrayList<String>();
        view=(ListView)findViewById(R.id.list2);
        view.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        Cursor cursor =myData.getListContents();
        StringBuffer localBuffer =new StringBuffer();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewSQL.this, MainActivity.class));
            }
        });
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] s= list.get(position).split("\n",6);
                Toast.makeText(ViewSQL.this,s[0]+" "+s[1],Toast.LENGTH_LONG).show();

            }
        });
            while (cursor.moveToNext()){
               for (int i=0;i<cursor.getColumnCount();i++){
                   localBuffer.append(cursor.getColumnName(i)+": "+cursor.getString(i)+ "\n");
            }
                localBuffer.append("\n");
        }
        int p = 0;
        int vv = 0;
        for (int i = 0; i < localBuffer.length(); i++) {
            if (localBuffer.charAt(i) == '\n') {
                vv = vv + 1;
            }
            try {
                if (vv % 6 == 0 && vv > 0) {
                    list.add(localBuffer.substring(p, i - 1));
                    vv = 0;
                    p = i + 1;
                }
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
                view.setAdapter(arrayAdapter);
            } catch (Exception e) {
                Toast.makeText(ViewSQL.this, "Error in last method", Toast.LENGTH_LONG).show();
            }




        }

    }

}