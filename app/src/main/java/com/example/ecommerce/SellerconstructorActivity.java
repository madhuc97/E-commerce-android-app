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

public class SellerconstructorActivity extends AppCompatActivity {


    private TextView textconstructorName, textconstructorPhoneno, textconstructorDescription,textconstructorservicetype,textconstructorexpertice,textconstructorexperiance,spinner,textconstructorprice ;
    private Button constructoradd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerpainter);
        getSupportActionBar().setTitle("CONSTRUCTOR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        textconstructorName =(TextView) findViewById(R.id.textpaintername);
        textconstructorPhoneno = (TextView) findViewById(R.id.textpainterphoneno);
        textconstructorDescription = (TextView) findViewById(R.id.textpainterdescription6);
        textconstructorservicetype = (TextView) findViewById(R.id.textpainterdescription2);
        textconstructorexpertice = (TextView) findViewById(R.id.textpainterdescription);
        textconstructorexperiance = (TextView) findViewById(R.id.textpainterdescription5);
        textconstructorprice = (TextView) findViewById(R.id.textpainterdescription3);
        constructoradd = (Button) findViewById(R.id.sellerpainterbutton) ;


        constructoradd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addingconstructordata();
            }
        });


    }


    private void addingconstructordata()
    {
        String constructor_Name = textconstructorName.getText().toString();
        String constructor_Phoneno = textconstructorPhoneno.getText().toString();
        String constructor_Description = textconstructorDescription.getText().toString();
        String constructor_expertise = textconstructorexpertice.getText().toString();
        String constructor_experiance = textconstructorexperiance.getText().toString();
        String constructor_servicetype = textconstructorservicetype.getText().toString();
        String constructor_price = textconstructorprice.getText().toString();


        if (TextUtils.isEmpty(constructor_Name))
        {
            Toast.makeText(this, " please write your name...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(constructor_Phoneno))
        {
            Toast.makeText(this, " please write your phone no...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(constructor_Description))
        {
            Toast.makeText(this, " please write your description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(constructor_expertise)){
            Toast.makeText(this,"please write your expertise...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(constructor_experiance)){
            Toast.makeText(this,"please write your experiance...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(constructor_servicetype)){
            Toast.makeText(this,"please write your servicetype...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(constructor_price)){
            Toast.makeText(this,"please write your price...",Toast.LENGTH_SHORT).show();
        }

        else
            {
                ValidatephoneNumber(constructor_Name, constructor_Phoneno, constructor_Description,constructor_expertise,constructor_experiance,constructor_servicetype,constructor_price);
            }


    }


    private void ValidatephoneNumber(final String constructor_name, final String constructor_phoneno, final String constructor_description,final String constructor_expertise,final String constructor_experiance,final String constructor_servicetype,final String constructor_price)
    {
        final DatabaseReference constructorRootRef;
        constructorRootRef=  FirebaseDatabase.getInstance().getReference();
        constructorRootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
               if (!(dataSnapshot.child("Constructor").child(constructor_phoneno).exists()))
               {
                   HashMap<String, Object> constructordataMap = new HashMap<>();
                   constructordataMap.put("constructorname", constructor_name);
                   constructordataMap.put("constructorphoneno", constructor_phoneno);
                   constructordataMap.put("constructordescription", constructor_description);
                   constructordataMap.put("constructorexpertise", constructor_expertise);
                   constructordataMap.put("constructorexperiance", constructor_experiance);
                   constructordataMap.put("constructorservicetype", constructor_servicetype);
                   constructordataMap.put("constructorprice", constructor_price);

                   constructorRootRef.child("Constructor").child(constructor_phoneno).updateChildren(constructordataMap)
                           .addOnCompleteListener(new OnCompleteListener<Void>()
                           {
                               @Override
                               public void onComplete(@NonNull Task<Void> task)
                               {
                                   if (task.isSuccessful())
                                   {
                                       Toast.makeText(SellerconstructorActivity.this, "succussfully added", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
               }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }
}
