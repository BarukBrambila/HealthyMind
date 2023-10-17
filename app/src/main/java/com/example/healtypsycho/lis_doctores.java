package com.example.healtypsycho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class lis_doctores extends AppCompatActivity {

    private String[] buttonsLabels = {"image_button1", "image_button2", "image_button3"};
    private String[] getButtonsLabels = {"button1", "button2", "button3"};
    private String[] getGetButtonsLabels = {"button1a", "button2a", "button3a"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lis_doctores);

        LinearLayout button_container = findViewById(R.id.button_container);
        LinearLayout button_container2 = findViewById(R.id.button_container2);
        LinearLayout button_container3 = findViewById(R.id.button_container3);

        for (String label : buttonsLabels) {
            Button button = new Button(this);
            button.setText(label);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setGravity(Gravity.CENTER);
            NestedScrollView buttoncontenedor_boton = null;
            buttoncontenedor_boton.addView(button);
        }

        for (String label : getButtonsLabels) {
            Button button = new Button(this);
            button.setText(label);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setGravity(Gravity.CENTER);
            NestedScrollView buttoncontenedor_boton = null;
            buttoncontenedor_boton.addView(button);
        }

        for (String label : getGetButtonsLabels) {
            Button button = new Button(this);
            button.setText(label);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setGravity(Gravity.CENTER);
            NestedScrollView buttoncontenedor_boton = null;
            buttoncontenedor_boton.addView(button);

        }
    }
}
