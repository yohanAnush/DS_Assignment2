package com.sliit.ds.FastFoodOnlineIT16032798.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Map;

@Document(collection = "user")
public class User {
    @Id
    private long uid;   // is auto generated.
    private String email;
    private String name;
    private ArrayList<Integer> mobileNumbers = new ArrayList<>();

    public User() { }

    public User(String email, String name, int mobileNumber) {
        this.uid = (email+name).hashCode();
        this.email = email;
        this.name = name;
        this.mobileNumbers.add(mobileNumber);

    }

    public User(Map<String, Object> payload) {
        this.email = payload.get("email").toString();
        this.name = payload.get("name").toString();
        this.uid = (this.email+this.name).hashCode();
        this.mobileNumbers.add(Integer.parseInt(payload.get("mobile").toString()));
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(int [] mobileNumbers) {
        for (int mobileNumber: mobileNumbers) {
            this.mobileNumbers.add(mobileNumber);
        }
    }
    // we need to use this when we are updating a user since the user object only returns an ArrayList
    // for mobileNumbers.
    public void setMobileNumbers(ArrayList<Integer> mobileNumbers) {
        for (int mobileNumber: mobileNumbers) {
            this.mobileNumbers.add(mobileNumber);
        }
    }
}
