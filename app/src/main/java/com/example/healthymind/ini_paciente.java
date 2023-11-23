package com.example.healthymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.healthymind.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ini_paciente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini_paciente);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_date:
                    startActivity(new Intent(getApplicationContext(), listapsicologos_cita.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_perfil:
                    startActivity(new Intent(getApplicationContext(), perfil_paciente.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}