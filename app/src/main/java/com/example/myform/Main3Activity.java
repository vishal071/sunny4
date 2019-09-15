package com.example.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {
    EditText password2EditText;
    EditText confirmpassword2EditText;
    Button save2Button;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        password2EditText=findViewById(R.id.password2);
        confirmpassword2EditText=findViewById(R.id.confirmpassword2);
        save2Button=findViewById(R.id.save2);
        String uni_id=android.provider.Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        save2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password2Data = password2EditText.getText().toString();
                String confirmpassword2Data= confirmpassword2EditText.getText().toString();
                String to="password did not match with confirm password";
                String p="Password successfully changed";

                if(password2Data.compareTo(confirmpassword2Data)==0) {
                    reference.child("PASSWORD").setValue(password2Data);
                    Toast.makeText(Main3Activity.this,p,Toast.LENGTH_SHORT).show();

                    finish();
                }
                else
                {
                    Toast.makeText(Main3Activity.this,to,Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
