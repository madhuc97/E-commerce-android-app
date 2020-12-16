package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SellerplumberActivity extends AppCompatActivity {

    private TextView textplumberName, textplumberPhoneno, textplumberDescription,textplumberexpertise,textplumberexperiance,textplumberservicetype,textplumberprice;
    private Button plumberadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerpainter);
        getSupportActionBar().setTitle("PLUMBER");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textplumberName =(TextView) findViewById(R.id.textpaintername);
        textplumberPhoneno = (TextView) findViewById(R.id.textpainterphoneno);
        textplumberDescription = (TextView) findViewById(R.id.textpainterdescription6);
        textplumberservicetype = (TextView) findViewById(R.id.textpainterdescription2);
        textplumberexpertise = (TextView) findViewById(R.id.textpainterdescription);
        textplumberexperiance = (TextView) findViewById(R.id.textpainterdescription5);
        textplumberprice = (TextView) findViewById(R.id.textpainterdescription3);
        plumberadd = (Button) findViewById(R.id.sellerpainterbutton) ;

        plumberadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addingplumberdata();
            }
        });
    }

    private void addingplumberdata() {
        String plumber_Name = textplumberName.getText().toString();
        String plumber_phoneno = textplumberPhoneno.getText().toString();
        String plumber_description = textplumberDescription.getText().toString();
        String plumber_expertise = textplumberexpertise.getText().toString();
        String plumber_experiance = textplumberexperiance.getText().toString();
        String plumber_servicetype = textplumberservicetype.getText().toString();
        String plumber_price = textplumberprice.getText().toString();

        if (TextUtils.isEmpty(plumber_Name)){
            Toast.makeText(this,"please write your name...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(plumber_phoneno)){
            Toast.makeText(this,"please write your phoneno...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(plumber_description)){
            Toast.makeText(this,"please write your description...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(plumber_expertise)){
            Toast.makeText(this,"please write your expertise...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(plumber_experiance)){
            Toast.makeText(this,"please write your experiance...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(plumber_servicetype)){
            Toast.makeText(this,"please write your servicetype...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(plumber_price)){
            Toast.makeText(this,"please write your price...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ValidatePhoneno(plumber_Name,plumber_phoneno,plumber_description,plumber_expertise,plumber_experiance,plumber_price,plumber_servicetype);
        }
    }

    private void ValidatePhoneno(final String plumber_name, final String plumber_phoneno, final String plumber_description,final String plumber_expertise,final String plumber_experiance,final String plumber_price,final String plumber_servicetype) {
        final DatabaseReference plumberRootRef;
        plumberRootRef= FirebaseDatabase.getInstance().getReference();
        plumberRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (!(datasnapshot.child("plumber").child(plumber_phoneno).exists())) {
                    HashMap<String, Object> plumberdataMap = new HashMap<>();
                    plumberdataMap.put("plumbername", plumber_name);
                    plumberdataMap.put("plumberphoneno", plumber_phoneno);
                    plumberdataMap.put("plumberdescription", plumber_description);
                    plumberdataMap.put("plumberexpertise", plumber_expertise);
                    plumberdataMap.put("plumberexperiance", plumber_experiance);
                    plumberdataMap.put("plumberservicetype", plumber_servicetype);
                    plumberdataMap.put("plumberprice", plumber_price);


                    plumberRootRef.child("Plumber").child(plumber_phoneno).updateChildren(plumberdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SellerplumberActivity.this, "succussfully added", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}