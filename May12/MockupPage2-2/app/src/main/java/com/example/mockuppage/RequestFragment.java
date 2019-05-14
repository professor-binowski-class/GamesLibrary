package com.example.mockuppage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class RequestFragment extends Fragment {

    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.email_request, container, false);


        mEditTextTo = v.findViewById(R.id.edit_text_to);
        mEditTextSubject = v.findViewById(R.id.edit_text_subject);
        mEditTextMessage = v.findViewById(R.id.edit_text_message);


        if (mEditTextSubject.getText().toString().length() == 0 ) {
            mEditTextSubject.setError("Student email is required");
        }



        Button buttonSend = v.findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        return v;
    }

    private void sendMail() {
        //String recipientList = mEditTextTo.getText().toString();      //not for our usecase
        //String[] recipients = recipientList.split(",");
        String[] recipients = {"someone@hardcodedemail.com"};
        String idSpacer = "Student ID: ";

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, idSpacer + message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

}

