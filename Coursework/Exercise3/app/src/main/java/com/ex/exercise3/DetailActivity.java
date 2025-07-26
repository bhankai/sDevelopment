package com.ex.exercise3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {
    ImageView detailImage;
    TextView detailName;
    TextView detailDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        detailDesc = findViewById(R.id.detailDesc);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("description");
        int imageResId = intent.getIntExtra("imageResId", 0);

        detailName.setText(name);
        detailDesc.setText(desc);
        detailImage.setImageResource(imageResId);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}