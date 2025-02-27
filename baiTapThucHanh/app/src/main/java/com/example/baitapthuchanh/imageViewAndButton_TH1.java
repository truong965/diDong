package com.example.baitapthuchanh;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class imageViewAndButton_TH1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image_view_and_button_th1);
        ImageView imageView = findViewById(R.id.imageView2);
        ImageButton superior= findViewById(R.id.superior);
        ImageButton superior1= findViewById(R.id.superior1);
        ImageButton superior2= findViewById(R.id.superior2);
        ImageButton superior3= findViewById(R.id.superior3);
        superior.setOnClickListener(v -> {
            imageView.setImageResource(R.drawable.superior);
        });
        superior1.setOnClickListener(v -> {
            imageView.setImageResource(R.drawable.superior1);
        });
        superior2.setOnClickListener(v -> {
            imageView.setImageResource(R.drawable.superior2);
        });
        superior3.setOnClickListener(v -> {
            imageView.setImageResource(R.drawable.superior3);
        });
    }
}