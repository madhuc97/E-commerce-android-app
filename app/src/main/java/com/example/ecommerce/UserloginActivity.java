package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserloginActivity extends AppCompatActivity
{
    private Button signup;
    private EditText InputUserName, InputPassword;
    private Button LoginButton;

    private ProgressDialog loadingBar;
    private String parentDbName = "users";



    String TAG="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userloginactivity);
        getSupportActionBar().setTitle("USER LOGIN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        signup= (Button) findViewById(R.id.usersignup);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(UserloginActivity.this, userregisterform.class);
                startActivity(intent);
            }
        });




        LoginButton = (Button) findViewById(R.id.userlogin);
        InputUserName = (EditText) findViewById(R.id.userusername);
        InputPassword = (EditText) findViewById(R.id.userpassword);
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
        String username = InputUserName.getText().toString();
        String password = InputPassword.getText().toString();

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
            System.out.println("Username-"+username+" and password-"+password);
            AllowAccessToAccount(username, password);
        }
    }

    private void AllowAccessToAccount(final String username, final String password)
    {

        Log.d(TAG,"AllowAccessToAccount");
System.out.println("entered inside AllowAccess Account!!!!!");
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("users");

            System.out.println("username-"+username);


        Log.d(TAG,"AllowAccessToAccount="+rootRef.getKey());

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Log.d(TAG,"db exists"+dataSnapshot.getChildren());

                Log.d(TAG,"db exists"+dataSnapshot.getValue());


                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Users usersData = ds.getValue(Users.class);
                       System.out.println("username from Firebase-"+usersData.getUsername());
                        System.out.println("username from Firebase-"+usersData.getPassword());
                        if (usersData.getUsername().equals(username)) {
                            if (usersData.getPassword().equals(password)) {
                                Log.d(TAG, "logged in success");

                                Toast.makeText(UserloginActivity.this, "Logged in Successfully....", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(UserloginActivity.this, userhomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);

                                break;

                            } else {
                                Toast.makeText(UserloginActivity.this, "Account with this " + username + "username do not exist", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    }
                }catch (Exception e){

                }


/*
                if (dataSnapshot.child(username).exists()) {

                    Log.d(TAG,"db exists");

                    try {
                     */
/* Users usersData = dataSnapshot.child(parentDbName).child(username).getValue(Users.class);

                        Log.d(TAG,"db exists"+usersData.getUsername());*//*


                        if (usersData.getUsername().equals(username)) {


                          if (usersData.getPassword().equals(password)) {
                              Log.d(TAG,"logged in success");

                              Toast.makeText(LoginActivity.this, "Logged in Successfully....", Toast.LENGTH_SHORT).show();
                              loadingBar.dismiss();
                              Intent intent = new Intent(LoginActivity.this, Homeactivity.class);


                                                 SharedPreferences mPreferences = getSharedPreferences("test", MODE_PRIVATE);
 mP

                    //for store
                   SharedPreferences mPreferences = getSharedPreferences("test", MODE_PRIVATE);

                   SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString("username", "" +""+username);
                    editor.commit();

                          } else {
                              Toast.makeText(LoginActivity.this, "Account with this " + username + "username do not exist", Toast.LENGTH_SHORT).show();
                              loadingBar.dismiss();
                          }

                      }else{

                          Log.d(TAG,"logged in not success");

                      }
                  }catch (Exception e){

                  }
                }
*/



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

                Log.d(TAG,""+error.getMessage());

            }
        });


    }
}










