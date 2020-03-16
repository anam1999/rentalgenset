package com.example.tekmulang;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


public class FragmentActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        loadFragment(new Fragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.maps_menu:
                getSupportActionBar().setTitle("Maps");
                fragment = new MapsReset();
                break;
            case R.id.rental_menu:
                getSupportActionBar().setTitle("Rental");
                fragment = new PilihReset();
                break;
            case R.id.notif_menu:
                getSupportActionBar().setTitle("Notifikasi");
                fragment = new NotifikasiReset();
                break;
            case R.id.account_menu:
                getSupportActionBar().setTitle("Account");
                fragment = new AccountReset();
                break;


        }

                return loadFragment(fragment);
        }

        public void P1(View view) {
            Intent r1 = new Intent(getApplicationContext(), RentalReset.class);
            startActivity(r1);
        }
        public void P2(View view) {
            Intent r2 = new Intent(getApplicationContext(), Rental1Reset.class);
            startActivity(r2);
        }
        public void P3(View view) {
            Intent r3 = new Intent(getApplicationContext(),Rental2Reset.class);
            startActivity(r3);
        }
        public void info (View view) {
            Intent r3 = new Intent(getApplicationContext(), EditProfilActivity.class);
            startActivity(r3);
        }


    }



