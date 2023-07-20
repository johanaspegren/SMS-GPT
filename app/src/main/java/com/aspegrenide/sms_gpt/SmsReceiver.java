package com.aspegrenide.sms_gpt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG = "==>";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "sms received" + intent.toString());
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                String phoneNumber = smsMessage.getOriginatingAddress();

                Log.d(TAG, "sms messageBody" + messageBody);
                Log.d(TAG, "sms phoneNumber" + phoneNumber);

                // TODO: Respond to the SMS
                storeInFirebase(messageBody, phoneNumber);
                respond(messageBody, phoneNumber);
            }
        }
    }

    private void respond(String messageBody, String phoneNumber) {
        String replyMessage;
        String keyword = messageBody.trim().toUpperCase();

        switch (keyword) {
            case "INFO":
                replyMessage = "This is the info you requested...";
                break;

            case "NEWS":
                replyMessage = "Here are the latest news...";
                break;

            default:
                replyMessage = "Sorry, I didn't understand your request.";
                break;
        }
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, replyMessage, null, null);
    }

    private void storeInFirebase(String messageBody, String phoneNumber) {
        // Create new SMS object
        SMS sms = new SMS(phoneNumber, messageBody, System.currentTimeMillis());
        User user = new User(phoneNumber, null, null);

        // Get a reference to the Firebase Realtime Database'
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference msgRef = database.getReference("messages");
        DatabaseReference userRef = database.getReference("users");
        // Write the SMS object to the database
        msgRef.push().setValue(sms);
        userRef.push().setValue(user);
    }

}
