package com.aspegrenide.sms_gpt;

public class SMS {
    private String sender;
    private String message;
    private long timestamp;

    public SMS() {
        // Default constructor required for calls to DataSnapshot.getValue(SMS.class)
    }

    public SMS(String sender, String message, long timestamp) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
