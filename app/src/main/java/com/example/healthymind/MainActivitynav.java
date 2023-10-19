package com.example.healtypsycho;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivitynav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnav);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    openFragment(new HomeFragment());
                    return  true;
                } else if (itemId == R.id.nav_calendar) {
                    openFragment(new CalendarFragment());

                    return true;
                } else if (itemId == R.id.nav_noti) {
                    openFragment(new NotiFragment());
                    return  true;
                } else if (itemId == R.id.nav_profile) {
                    openFragment(new ProfileFragment());
                    return  true;
                }


                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private  void openFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

    }
}
