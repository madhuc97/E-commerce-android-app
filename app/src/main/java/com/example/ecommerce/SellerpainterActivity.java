package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerpainterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textpainterName, textpainterPhoneno, textpainterDescription,textpainterservicetype,textpainterexpertice,textpainterexperiance,spinner,textpainterprice ;
    private Button painteradd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerpainter);
        getSupportActionBar().setTitle("PAINTER");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Spinner spinner = findViewById(R.id.spinner);
        textpainterName =(TextView) findViewById(R.id.textpaintername);
        textpainterPhoneno = (TextView) findViewById(R.id.textpainterphoneno);
        textpainterDescription = (TextView) findViewById(R.id.textpainterdescription6);
        textpainterservicetype = (TextView) findViewById(R.id.textpainterdescription2);
        textpainterexpertice = (TextView) findViewById(R.id.textpainterdescription);
        textpainterexperiance = (TextView) findViewById(R.id.textpainterdescription5);
        textpainterprice = (TextView) findViewById(R.id.textpainterdescription3);
        painteradd = (Button) findViewById(R.id.sellerpainterbutton) ;

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Price,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/

        painteradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addingpainterdata();
            }
        });

    }

    private void addingpainterdata() {
        String painter_Name = textpainterName.getText().toString();
        String painter_phoneno = textpainterPhoneno.getText().toString();
        String painter_description = textpainterDescription.getText().toString();
        String painter_expertise = textpainterexpertice.getText().toString();
        String painter_experiance = textpainterexperiance.getText().toString();
        String painter_servicetype = textpainterservicetype.getText().toString();
        String painter_price = textpainterprice.getText().toString();

        //String painter_spinner = spinner.getText().toString();
        if (TextUtils.isEmpty(painter_Name)){
            Toast.makeText(this,"please write your name...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(painter_phoneno)){
            Toast.makeText(this,"please write your phoneno...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(painter_description)){
            Toast.makeText(this,"please write your description...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(painter_expertise)){
            Toast.makeText(this,"please write your expertise...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(painter_experiance)){
            Toast.makeText(this,"please write your experiance...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(painter_servicetype)){
            Toast.makeText(this,"please write your servicetype...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(painter_price)){
            Toast.makeText(this,"please write your price...",Toast.LENGTH_SHORT).show();
        }

        else
        {
            ValidatePhoneno(painter_Name,painter_phoneno,painter_description,painter_expertise,painter_experiance,painter_servicetype,painter_price);
        }
    }

    private void ValidatePhoneno(final String painter_name, final String painter_phoneno,final String painter_description,final String painter_expertise,final String painter_experiance,final String painter_servicetype,final String painter_price)
    {
        final DatabaseReference painterRootRef;
        painterRootRef= FirebaseDatabase.getInstance().getReference();
        painterRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Painter").child(painter_phoneno).exists())) {
                    HashMap<String, Object> painterdataMap = new HashMap<>();
                    painterdataMap.put("paintername", painter_name);
                    painterdataMap.put("painterphoneno", painter_phoneno);
                    painterdataMap.put("painterdescription", painter_description);
                    painterdataMap.put("painterexpertise", painter_expertise);
                    painterdataMap.put("painterexperiance", painter_experiance);
                    painterdataMap.put("painterservicetype", painter_servicetype);
                    painterdataMap.put("painterprice", painter_price);
                   // painterdataMap.put("painterspinner", painter_spinner);



                    painterRootRef.child("Painter").child(painter_phoneno).updateChildren(painterdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SellerpainterActivity.this, "succussfully added", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}