package com.example.login;

public class users {
    public String firstName,lastname,emailAddress,phoneNumber,password;

    public users(){}
//store the user data
    public users(String firstName,String lastname,String emailAddress,String phoneNumber,String password){
        this.firstName = firstName;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

}
