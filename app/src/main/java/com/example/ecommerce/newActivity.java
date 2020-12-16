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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newActivity extends AppCompatActivity
{
    DatabaseReference reff;
    Cement_Register cement_register;

    int[] IMAGES = {R.drawable.acccement, R.drawable.ambujacement, R.drawable.jswimg, R.drawable.mycemcement, R.drawable.ultrtechcement, R.drawable.mahacement};
    String[] NAMES = {"ACC CEMENT", "AMBUJA CEMENT", "JSW CEMENT", "MYSEM CEMENT", "ULTRATECH CEMENT", "MAHA CEMENT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        ListView listView = (ListView)findViewById(R.id.etListView);

        cement_register = new Cement_Register();
        reff = FirebaseDatabase.getInstance().getReference().child("Cement_Register");


        CustomAdapter customadapter = new CustomAdapter();
        listView.setAdapter(customadapter);

    }
    class CustomAdapter extends BaseAdapter
    {

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
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.etimageView);
            TextView textview_name = (TextView)view.findViewById(R.id.ettextView);
            EditText stock_avail = (EditText)view.findViewById(R.id.etstockavailability);
            EditText price_per = (EditText)view.findViewById(R.id.etsellerstockprice);
            Button add1 = view.findViewById(R.id.etadd);

            imageView.setImageResource(IMAGES[i]);
            textview_name.setText(NAMES[i]);



            return null;
        }
    }

}