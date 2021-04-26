package com.example.ahmeddiab_180470;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Firebase {
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;






    public Firebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        mDatabase = FirebaseDatabase.getInstance();

    }






    public void writeNewUser(String userId,String id, String phoneNumber, String lastName, String firstName, String emailAddress) {

        User user = new User( userId, id,firstName, emailAddress, phoneNumber, lastName);
        myRef.child(userId).setValue(user);
    }
    public void change(String Id, String Field, String newValue) {
            myRef.child(Id).child(Field).setValue(newValue);
    }


    public void delete(String val){
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    for(DataSnapshot dsw:ds.getChildren())
                    {
                        if(dsw.getKey().contentEquals("id"))
                        {
                            if(dsw.getValue().toString().contentEquals(val))
                            {
                                myRef.child(ds.getKey()).setValue(null);
                            }
                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void view(){

    }



}




