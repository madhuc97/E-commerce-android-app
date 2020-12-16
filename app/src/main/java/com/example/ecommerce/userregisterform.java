package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class userregisterform<DatabaseRefernce, string> extends AppCompatActivity

{

    private Button CreateAccountButton;
    private EditText InputFirstname, InputLastname,  InputUserName,InputPhoneNumber ,InputEmailId,  InputPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregisterform);

        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        InputFirstname = (EditText) findViewById(R.id.register_firstname_input);
        InputLastname= (EditText) findViewById(R.id.register_lastname_input);
        InputUserName = (EditText) findViewById(R.id.register_username_input);
        InputPassword = (EditText) findViewById(R.id.editTextTextPassword);
        InputEmailId = (EditText) findViewById(R.id.register_Email_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_number_input);
        loadingBar = new ProgressDialog(this);


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override



            public void onClick(View view)
            {
                CreateAccount();
            }
        });


    }

    private void CreateAccount()
    {
        String firstname = InputFirstname.getText().toString();
        String lastname = InputLastname.getText().toString();
        String username = InputUserName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String Emailid = InputEmailId.getText().toString();
        String password = InputPassword.getText().toString();


        if (TextUtils.isEmpty(firstname))
        {
            Toast.makeText(this, " please write your name...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(lastname))
        {
            Toast.makeText(this, " please write your name...", Toast.LENGTH_SHORT).show();
        }




        else if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, " please write your name...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(Emailid))
        {
            Toast.makeText(this, " please write your name...", Toast.LENGTH_SHORT).show();
        }


        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, " please write your phone number...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, " please write your password...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait , while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber( firstname, lastname,username,Emailid,phone, password);


        }
    }

    private void ValidatephoneNumber( final String firstname, final String lastname, final String username,final String emailid ,final String phone, final String password)

    {
        final DatabaseReference RootRef;
        RootRef =  FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                if (!(dataSnapshot.child("users").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("username",  username);
                    userdataMap.put("firstname", firstname);
                    userdataMap.put("lastname", lastname);
                    userdataMap.put("emailid", emailid);

                    RootRef.child("users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(userregisterform.this, "congrats your account has been created", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(userregisterform.this, UserloginActivity.class);
                                        startActivity(intent);


                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(userregisterform.this, "network error", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }
                else
                {
                    Toast.makeText(userregisterform.this, "This"+ phone + "already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(userregisterform.this, "please try again using another phone number", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(userregisterform.this, WelcomeActivity.class);
                    startActivity(intent);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}