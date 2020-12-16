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

import com.example.ecommerce.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sellerloginactivity extends AppCompatActivity
{
    private Button signup;
    private EditText Inputusername, Inputpassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerloginactivity);
        getSupportActionBar().setTitle("SELLER LOGIN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        signup= (Button) findViewById(R.id.sellersignup);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(sellerloginactivity.this,sellerregisteractivity.class);
                startActivity(intent);
            }
        });




        LoginButton = (Button) findViewById(R.id.sellerlogin);
        Inputusername = (EditText) findViewById(R.id.sellerusername);
        Inputpassword = (EditText) findViewById(R.id.sellerpassword);
        loadingBar = new ProgressDialog(this);


        LoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });
    }

    private void LoginUser()
    {
        String username = Inputusername.getText().toString();
        String password = Inputpassword.getText().toString();

        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, " please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, " please write your password...", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait , while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

          System.out.println("Seller username-"+username+" and seller password-"+password);

            AllowAccessToAccount(username, password);
        }
    }

    private void AllowAccessToAccount(final String username, final String password)
    {
        //final DatabaseReference RootRef;
        final DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference().child("users");

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("db exists" + dataSnapshot.getChildren());

                System.out.println("db exists" + dataSnapshot.getValue());

                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Users usersData = ds.getValue(Users.class);
                        System.out.println("username from Firebase-"+usersData.getUsername());
                        System.out.println("username from Firebase-"+usersData.getPassword());
                        if (usersData.getUsername().equals(username)) {
                            if (usersData.getPassword().equals(password)) {
                                System.out.println("logged in success");
                                Toast.makeText(sellerloginactivity.this, "Logged in Successfully....", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(sellerloginactivity.this, SellerhomeactivityActivity.class);
                                startActivity(intent);

                                break;

                            } else {
                                Toast.makeText(sellerloginactivity.this, "Account with this " + username + "username do not exist", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });


    }
}
