package com.aspegrenide.sms_gpt;

public class User {
    private String phoneNumber;
    private String name;
    private String email;
    // Add more fields as needed

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String phoneNumber, String name, String email) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    // Add more getters as needed
}
