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

public class CarpentorActivity extends AppCompatActivity {

    private TextView textName, textPhoneno, textDescription,textservicetype,textexpertice,textexperiance,spinner,textprice ;
    private Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerpainter);
        getSupportActionBar().setTitle("CARPENTOR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        textName =(TextView) findViewById(R.id.textpaintername);
            textPhoneno = (TextView) findViewById(R.id.textpainterphoneno);
            textDescription = (TextView) findViewById(R.id.textpainterdescription6);
        textservicetype = (TextView) findViewById(R.id.textpainterdescription2);
        textexpertice = (TextView) findViewById(R.id.textpainterdescription);
        textexperiance = (TextView) findViewById(R.id.textpainterdescription5);
        textprice = (TextView) findViewById(R.id.textpainterdescription3);
            add = (Button) findViewById(R.id.sellerpainterbutton) ;


            add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    addingcarpentordata();
                }
            });


    }


    private void addingcarpentordata()
    {
        String carpentor_Name = textName.getText().toString();
        String carpentor_Phoneno = textPhoneno.getText().toString();
        String carpentor_Description = textDescription.getText().toString();
        String carpentor_expertise = textexpertice.getText().toString();
        String carpentor_experiance = textexperiance.getText().toString();
        String carpentor_servicetype = textservicetype.getText().toString();
        String carpentor_price = textprice.getText().toString();


        if (TextUtils.isEmpty(carpentor_Name))
        {
            Toast.makeText(this, " please write your name...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(carpentor_Phoneno))
        {
            Toast.makeText(this, " please write your phone no...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(carpentor_Description))
        {
            Toast.makeText(this, " please write your description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(carpentor_expertise)){
            Toast.makeText(this,"please write your expertise...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(carpentor_experiance)){
            Toast.makeText(this,"please write your experiance...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(carpentor_servicetype)){
            Toast.makeText(this,"please write your servicetype...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(carpentor_price)){
            Toast.makeText(this,"please write your price...",Toast.LENGTH_SHORT).show();
        }
        else
            {
                ValidatephoneNumber(carpentor_Name,carpentor_Phoneno,carpentor_Description,carpentor_expertise,carpentor_experiance,carpentor_servicetype,carpentor_price);
            }
    }
    private void ValidatephoneNumber(final String carpentor_name, final String carpentor_phoneno,final String carpentor_description,final String carpentor_expertise,final String carpentor_experiance,final String carpentor_servicetype,final String carpentor_price)
    {
            final DatabaseReference carpentorRootRef;
            carpentorRootRef=  FirebaseDatabase.getInstance().getReference();
            carpentorRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (!(dataSnapshot.child("Carpentor").child(carpentor_phoneno).exists()))
                    {
                        HashMap<String, Object> carpentordataMap =new HashMap<>();
                        carpentordataMap.put("carpentorname",carpentor_name);
                        carpentordataMap.put("carpentorphoneno", carpentor_phoneno);
                        carpentordataMap.put("carpentordescription",carpentor_description);
                        carpentordataMap.put("carpentorexpertise", carpentor_expertise);
                        carpentordataMap.put("carpentorexperiance", carpentor_experiance);
                        carpentordataMap.put("carpentorservicetype", carpentor_servicetype);
                        carpentordataMap.put("carpentorprice", carpentor_price);

                        carpentorRootRef.child("Carpentor").child(carpentor_phoneno).updateChildren(carpentordataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(CarpentorActivity.this,"succussfully added",Toast.LENGTH_SHORT).show();
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


