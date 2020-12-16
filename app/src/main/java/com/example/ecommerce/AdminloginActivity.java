package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminloginActivity extends AppCompatActivity {
    private EditText AdminUserName, AdminPassword;
    private Button AdminButton;

    String correct_username = "admin";
    String correct_password = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        AdminUserName = (EditText) findViewById(R.id.adminusername);
        AdminPassword = (EditText) findViewById(R.id.adminpassword);
        AdminButton = (Button) findViewById(R.id.adminlogin);


        AdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(AdminUserName.getText().toString()) || TextUtils.isEmpty(AdminPassword.getText().toString())) {
                    Toast.makeText(AdminloginActivity.this, "Empty Data is not provided", Toast.LENGTH_LONG).show();
                } else if (AdminUserName.getText().toString().equals(correct_username)) {
                    if (AdminPassword.getText().toString().equals(correct_password)) {
                        Toast.makeText(AdminloginActivity.this, "Successful Login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AdminloginActivity.this, AdminhomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AdminloginActivity.this, "Invalid Username and Password", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
    }
}

