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

public class SellerlabourActivity extends AppCompatActivity {
    private TextView textlabourName, textlabourPhoneno, textlabourDescription,textlabourservicetype,textlabourexpertice,textlabourexperiance,spinner,textlabourprice ;
    private Button labouradd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerpainter);
        getSupportActionBar().setTitle("LABOUR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textlabourName =(TextView) findViewById(R.id.textpaintername);
        textlabourPhoneno = (TextView) findViewById(R.id.textpainterphoneno);
        textlabourDescription = (TextView) findViewById(R.id.textpainterdescription6);
        textlabourservicetype = (TextView) findViewById(R.id.textpainterdescription2);
        textlabourexpertice = (TextView) findViewById(R.id.textpainterdescription);
        textlabourexperiance = (TextView) findViewById(R.id.textpainterdescription5);
        textlabourprice = (TextView) findViewById(R.id.textpainterdescription3);
        labouradd = (Button) findViewById(R.id.sellerpainterbutton) ;

        labouradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addinglabourdata();
            }
        });
    }

    private void addinglabourdata() {
        String labour_Name = textlabourName.getText().toString();
        String labour_phoneno = textlabourPhoneno.getText().toString();
        String labour_description = textlabourDescription.getText().toString();
        String labour_expertise = textlabourexpertice.getText().toString();
        String labour_experiance = textlabourexperiance.getText().toString();
        String labour_servicetype = textlabourservicetype.getText().toString();
        String labour_price = textlabourprice.getText().toString();

        if (TextUtils.isEmpty(labour_Name)){
            Toast.makeText(this,"please write your name...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(labour_phoneno)){
            Toast.makeText(this,"please write your phoneno...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(labour_description)){
            Toast.makeText(this,"please write your description...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(labour_expertise)){
            Toast.makeText(this,"please write your expertise...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(labour_experiance)){
            Toast.makeText(this,"please write your experiance...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(labour_servicetype)){
            Toast.makeText(this,"please write your servicetype...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(labour_price)){
            Toast.makeText(this,"please write your price...",Toast.LENGTH_SHORT).show();
        }

        else
        {
            ValidatePhoneno(labour_Name,labour_phoneno,labour_description,labour_expertise,labour_experiance,labour_servicetype,labour_price);
        }
    }

    private void ValidatePhoneno(final String labour_name,final String labour_phoneno,final String labour_description,final String labour_expertise,final String labour_experiance,final String labour_servicetype,final String labour_price) {
        final DatabaseReference labourRootRef;
        labourRootRef= FirebaseDatabase.getInstance().getReference();
        labourRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (!(datasnapshot.child("Labour").child(labour_phoneno).exists()))
                {
                    HashMap<String, Object> labourdataMap = new HashMap<>();
                    labourdataMap.put("labourname", labour_name);
                    labourdataMap.put("labourphoneno", labour_phoneno);
                    labourdataMap.put("labourdescription", labour_description);
                    labourdataMap.put("labourexpertise", labour_expertise);
                    labourdataMap.put("labourexperiance", labour_experiance);
                    labourdataMap.put("labourservicetype", labour_servicetype);
                    labourdataMap.put("labourprice", labour_price);


                    labourRootRef.child("Labour").child(labour_phoneno).updateChildren(labourdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SellerlabourActivity.this, "succussfully added", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

            }   }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
