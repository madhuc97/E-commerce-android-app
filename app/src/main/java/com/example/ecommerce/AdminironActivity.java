package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminironActivity extends AppCompatActivity {


    DatabaseReference reff;
    Iron_Register iron_register;



    int[] IMAGES = {R.drawable.tataironrods,R.drawable.srmbtmt,R.drawable.essar,R.drawable.jsw,R.drawable.kamdhenu};

    String[] NAMES = {"TATA IRONROD", "SRMBTMT", "ESSAR IRONROD", "JSW IRONROD", "KAMDHENU IRONROD"};

    ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminiron);


        reff = FirebaseDatabase.getInstance().getReference().child("Admin").child("Iron_Register");
        mListView = findViewById(R.id.ListView);
        iron_register = new Iron_Register();


        ListView listView=(ListView)findViewById(R.id.ListView);
        CustomAdopter customAdopter=new CustomAdopter();

        listView.setAdapter(customAdopter);
    }



    class CustomAdopter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.activity_sellercement, null);
            System.out.println("inside getView!!!!" + i);

            ImageView imageView = (ImageView) view.findViewById(R.id.etimageView);
            TextView textView_name = (TextView) view.findViewById(R.id.textView_name);

            final EditText stockPrice = (EditText) view.findViewById(R.id.etsellerstockprice);
            final EditText stockName = (EditText) view.findViewById(R.id.etsellerstock);

            Button add = view.findViewById(R.id.selleradd);

            imageView.setImageResource(IMAGES[i]);
            textView_name.setText(NAMES[i]);

            final int position = i;
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("inside add onlclick!!!---" + stockName.getText().toString() + " stock price-" + stockPrice.getText().toString());
                    Toast.makeText(getApplicationContext(), "name=" + NAMES[position] + "=" + stockName.getText() + "=" + stockPrice.getText(), Toast.LENGTH_SHORT).show();


                    iron_register.setIronName(NAMES[position]);
                    iron_register.setIronStock(stockName.getText().toString());
                    iron_register.setIronPrice(stockPrice.getText().toString());


                    HashMap<String, Object> map = new HashMap<>();
                    map.put(NAMES[position], iron_register);
                    reff.updateChildren(map);

                }

            });

            return view;

        }

    }
}