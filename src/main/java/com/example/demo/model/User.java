package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Mobile Num must not be empty")
    private String mobileNum;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    public User(){

    }

    public User(long id, String name, String email, String mobileNum, String password){
        super();

        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNum = mobileNum;
        this.password = password;


    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getMobileNum(){
        return mobileNum;
    }

    public void setMobileNum(String mobileNum){
        this.mobileNum = mobileNum;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    
}
