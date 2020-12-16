package com.example.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Model.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

import cameo.code.imageslider.SliderFragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class userhomeActivity extends AppCompatActivity
      implements NavigationView.OnNavigationItemSelectedListener {


    private SliderFragment mSliderFragment;
    //private ImageView mImageView;
    private ArrayList<String> mImagesUrl = new ArrayList<>();
    private ArrayList<Integer> mImagesRes = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView Display_username;
    private ImageView cement,iron,paint,bricks,electricals,hardware;
    private ImageView carpenter,constructor,labour,painter,electrition,plumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);


        cement = (ImageView) findViewById(R.id.cement);
        iron = (ImageView) findViewById(R.id.ironrod);
        bricks = (ImageView) findViewById(R.id.brick);
        paint = (ImageView) findViewById(R.id.paint);
        hardware = (ImageView) findViewById(R.id.hardware);
        electricals = (ImageView) findViewById(R.id.electricals);
        carpenter = (ImageView) findViewById(R.id.carpenter);
        constructor = (ImageView) findViewById(R.id.constructor);
        plumber = (ImageView) findViewById(R.id.plumber);
        painter = (ImageView) findViewById(R.id.painter);
        labour = (ImageView) findViewById(R.id.labour);
        electrition = (ImageView) findViewById(R.id.electrition);




        cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UsercementActivity.class);
                startActivity(intent);
            }
        });

        iron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UseruserironrodActivity.class);
                startActivity(intent);
            }
        });


        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserpaintActivity.class);
                startActivity(intent);
            }
        });


        carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, Usercarpentor.class);
                startActivity(intent);
            }
        });
        constructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserconstructorActivity.class);
                startActivity(intent);
            }
        });
        labour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserlabourActivity.class);
                startActivity(intent);
            }
        });
        painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserpainterActivity.class);
                startActivity(intent);
            }
        });

        electrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserelectritionActivity.class);
                startActivity(intent);
            }
        });

        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserplumberActivity.class);
                startActivity(intent);
            }
        });

        bricks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserbricksActivity.class);
                startActivity(intent);
            }
        });
        electricals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserelectricalsActivity.class);
                startActivity(intent);
            }
        });

        hardware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, UserhardwareActivity.class);
                startActivity(intent);
            }
        });



        mImagesRes.add(R.drawable.construction1);
        mImagesRes.add(R.drawable.construction2);
        mImagesRes.add(R.drawable.constructionimag);
        mImagesRes.add(R.drawable.construction3);
        mImagesRes.add(R.drawable.construction4);

        //mSliderFragment = SliderFragment.createWithPath(mImagesUrl);
        mSliderFragment = SliderFragment.createWithRes(mImagesRes);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.slider_panel, mSliderFragment);
        transaction.commit();





        Display_username = (TextView) findViewById(R.id.user_profile_name);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhomeActivity.this, CartActivity.class);
                startActivity(intent);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);


        View headerView = navigationView.getHeaderView(0);
        TextView usernameTextview = headerView.findViewById(R.id.user_profile_name);
        CircleImageView ProfileImageView = headerView.findViewById(R.id.profile_image);

       usernameTextview.setText(Prevalent.currentOnlineUser.getUsername());

        //recyclerView = (RecyclerView) findViewById(R.id.recycler_menu);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference products = database.getReference("");


        products.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapsho go and check how you guys are using it in other screen .. any bdy thr
                // yes sir ,
                //we are copied and paste from another project


                Users user = new Users();

                ArrayList<Users> data = new ArrayList<>();

                if (data != null) {
                    user.setUsername("mc");


                    //update_recycler_view(data);


                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "No Items Currently on sale", Toast.LENGTH_LONG);
                    toast.show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.userhome, menu);
        return true;
    }



   @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart)
        {
          Intent intent = new Intent(getApplicationContext(), CartActivity.class);
            startActivity(intent);
        }
        if (id == R.id.orders)
        {
            Intent intent = new Intent(getApplicationContext(), OrderactivityActivity.class);
            startActivity(intent);
        }
        if (id == R.id.settings)
        {
            /*Intent intent = new Intent(getApplicationContext(), settings.class);
            startActivity(intent);*/
        }
        if (id == R.id.logout)
        {
            Intent intent = new Intent(getApplicationContext(), UserloginActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /*class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


        ArrayList<Users> mUser = null;


        MyRecyclerViewAdapter(ArrayList<Users> users) {

            mUser = users;


        }

        @NonNull
        @Override
        public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userhomeactivity, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position)
        {


            holder.itemStock.setText(mUser.get(position).getFirstname());
           holder.itemPrice.setText(mCementReg.get(position).getCementPrice());
            holder.itemName.setText(mCementReg.get(position).getCementName());
            holder.imageView.setImageResource(IMAGES[position]);


        }*/

    /*@Override
        public int getItemCount() {
            return mUser.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            public TextView itemName, itemPrice, itemStock;
            ImageView imageView;
            //public ImageView imageView;
            //public ImageView itemimage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                itemName = itemView.findViewById(R.id.textView_name);
                itemPrice = itemView.findViewById(R.id.etsellerprice);
                itemStock = itemView.findViewById(R.id.etsellerstock);
                imageView = (ImageView) itemView.findViewById(R.id.etimageView);

            }
        }
    }*/


}
