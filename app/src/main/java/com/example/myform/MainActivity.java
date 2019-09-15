package com.example.myform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView NameTextView;
    TextView ClassTextView;
    TextView DateofbirthTextView;
    TextView GenderTextView;

    TextView emailTextView;
    TextView passTextView;
    Button passwordTextView;
    Button profileTextView;
    SharedPreferences sharedPreferences;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NameTextView=findViewById(R.id.Name);
        ClassTextView=findViewById(R.id.Class);
        DateofbirthTextView=findViewById(R.id.Dateofbirth);
        GenderTextView=findViewById(R.id.Gender);
        emailTextView=findViewById(R.id.email);
        passTextView=findViewById(R.id.pass);
        passwordTextView=findViewById(R.id.password);
        profileTextView=findViewById(R.id.profile);

        fetchFirebaseData();
        profileTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

            }
        });
        passwordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);

            }
        });



    }
    private void fetchFirebaseData() {
        String uni_id =android.provider.Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference reference=firebaseDatabase.getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    NameTextView.setText(dataSnapshot.child("NAME").getValue().toString());
                    ClassTextView.setText(dataSnapshot.child("CLASS").getValue().toString());
                    DateofbirthTextView.setText(dataSnapshot.child("DATEOFBIRTH").getValue().toString());
                    GenderTextView.setText(dataSnapshot.child("GENDER").getValue().toString());
                    emailTextView.setText(dataSnapshot.child("EMAIL").getValue().toString());
                    passTextView.setText(dataSnapshot.child("PASSWORD").getValue().toString());
                }
                else {
                    Toast.makeText(MainActivity.this,"NO DATA FOUND",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });




    }
   /* public void fetchData(){
        sharedPreferences=getSharedPreferences("DATABASE",MODE_PRIVATE);
        NameTextView.setText(sharedPreferences.getString("NAME",""));
        ClassTextView.setText(sharedPreferences.getString("CLASS",""));
        DateofbirthTextView.setText(sharedPreferences.getString("DATEOFBIRTH",""));
        GenderTextView.setText(sharedPreferences.getString("GENDER",""));
        emailTextView.setText(sharedPreferences.getString("EMAIL",""));
        passTextView.setText(sharedPreferences.getString("PASSWORD",""));
    }*/

    @Override
    protected  void onResume() {
        super.onResume();

        fetchFirebaseData();
    }
}



