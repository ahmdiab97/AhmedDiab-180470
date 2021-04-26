package com.example.ahmeddiab_180470;

public class User {
 String idd ="ID: ";
    String userId;
    public String id;
    public String lastName;
    public String phoneNumber;
    public String firstName;
    public String emailAddress;
    public User(){
        // constructor
    }
    public User(String userId,String id,String firstName, String emailAddress,String phoneNumber,String lastName){
        this.id=id;
        this.userId=userId;
        this.phoneNumber=phoneNumber;
        this.lastName=lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
    }
    public String toString(){
        return this.idd+id+" \nFName: "+firstName+"\nLName: "+lastName+"\nPhoneNumber: "+phoneNumber+" \nEmail: "+emailAddress;
    }
}
