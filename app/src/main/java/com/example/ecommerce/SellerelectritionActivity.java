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

public class SellerelectritionActivity extends AppCompatActivity
{

    private TextView textelectritionName, textelectritionPhoneno, textelectritionDescription,textelectritionservicetype,textelectritionexpertice,textelectritionexperiance,spinner,textelectritionprice ;
    private Button electritionadd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerpainter);
        getSupportActionBar().setTitle("ELECTRITION");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textelectritionName =(TextView) findViewById(R.id.textpaintername);
        textelectritionPhoneno = (TextView) findViewById(R.id.textpainterphoneno);
        textelectritionDescription = (TextView) findViewById(R.id.textpainterdescription6);
        textelectritionservicetype = (TextView) findViewById(R.id.textpainterdescription2);
        textelectritionexpertice = (TextView) findViewById(R.id.textpainterdescription);
        textelectritionexperiance = (TextView) findViewById(R.id.textpainterdescription5);
        textelectritionprice = (TextView) findViewById(R.id.textpainterdescription3);
        electritionadd = (Button) findViewById(R.id.sellerpainterbutton) ;


        electritionadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingelectriciandata();
            }
        });


    }

    private void addingelectriciandata()
    {
        String electrition_Name = textelectritionName.getText().toString();
        String electition_phoneno = textelectritionPhoneno.getText().toString();
        String electrition_description = textelectritionDescription.getText().toString();
        String electrition_expertise = textelectritionexpertice.getText().toString();
        String electrition_experiance = textelectritionexperiance.getText().toString();
        String electrition_servicetype = textelectritionservicetype.getText().toString();
        String electrition_price = textelectritionprice.getText().toString();
        if (TextUtils.isEmpty(electrition_Name)){
            Toast.makeText(this,"please write your name...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(electition_phoneno)){
            Toast.makeText(this,"please write your phoneno...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(electrition_description)){
            Toast.makeText(this,"please write your description...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(electrition_expertise)){
            Toast.makeText(this,"please write your expertise...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(electrition_experiance)){
            Toast.makeText(this,"please write your experiance...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(electrition_servicetype)){
            Toast.makeText(this,"please write your servicetype...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(electrition_price)){
            Toast.makeText(this,"please write your price...",Toast.LENGTH_SHORT).show();
        }

        else
        {
            ValidatePhoneno(electrition_Name,electition_phoneno,electrition_description,electrition_expertise,electrition_experiance,electrition_servicetype,electrition_price);
        }


    }

    private void ValidatePhoneno(final String electrition_name, final String electition_phoneno, final String electrition_description,final String electrition_expertise,final String electrition_experiance,final String electrition_servicetype,final String electrition_price) {
        final DatabaseReference electritionRootRef;
        electritionRootRef=FirebaseDatabase.getInstance().getReference();
        electritionRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("ELectrition").child(electition_phoneno).exists())){
                    HashMap<String, Object>  electritiondataMap = new HashMap<>();
                    electritiondataMap.put("electritionname",electrition_name);
                    electritiondataMap.put("electritionphoneno",electition_phoneno);
                    electritiondataMap.put("electritiondescription",electrition_description);
                    electritiondataMap.put("electritionexpertise",electrition_expertise);
                    electritiondataMap.put("electritionexperiance", electrition_experiance);
                    electritiondataMap.put("electritionservicetype", electrition_servicetype);
                    electritiondataMap.put("electritionprice", electrition_price);

                    electritionRootRef.child("ELectrition").child(electition_phoneno).updateChildren(electritiondataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SellerelectritionActivity.this, "succussfully added", Toast.LENGTH_SHORT).show();
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