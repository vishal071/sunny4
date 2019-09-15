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

public class Main2Activity extends AppCompatActivity {
    EditText NameEditText;
    EditText ClassEditText;
    EditText DateofbirthEditText;
    EditText GenderEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText confirmpasswordEditText;
    Button saveEditText;
    SharedPreferences sharedPreferences;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        NameEditText = findViewById(R.id.NameEditText);
        ClassEditText = findViewById(R.id.ClassEditText);
        DateofbirthEditText = findViewById(R.id.DateofbirthEditText);
        GenderEditText = findViewById(R.id.GenderEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmpasswordEditText = findViewById(R.id.confirmpasswordEditText);
        saveEditText = findViewById(R.id.saveEditText);
        String uni_id =android.provider.Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        saveEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameData = NameEditText.getText().toString();
                String ClassData = ClassEditText.getText().toString();
                String DateData = DateofbirthEditText.getText().toString();
                String GenderData = GenderEditText.getText().toString();
                String emailData = emailEditText.getText().toString();
                String passwordData = passwordEditText.getText().toString();
                String confirmpasswordData = confirmpasswordEditText.getText().toString();
                String to="password did not match with confirm password";
                if (passwordData.compareTo(confirmpasswordData) == 0) {
                    reference.child("NAME").setValue(NameData);
                    reference.child("CLASS").setValue(ClassData);
                    reference.child("DATEOFBIRTH").setValue(DateData);
                    reference.child("GENDER").setValue(GenderData);
                    reference.child("EMAIL").setValue(emailData);
                    reference.child("PASSWORD").setValue(passwordData);
                    finish();
                }
                else
                {
                    Toast.makeText(Main2Activity.this,to,Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}
