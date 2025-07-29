package com.ex.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    private TextView txtCount;
    private TextView txtTotalDuration;
    private TextView txtLastWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        txtCount = findViewById(R.id.txtCount);
        txtTotalDuration = findViewById(R.id.txtTotalDuration);
        txtLastWorkout = findViewById(R.id.txtLastWorkout);

        Intent intent = getIntent();
        ArrayList<Workout> workouts = (ArrayList<Workout>) intent.getSerializableExtra("workouts");

        if (workouts == null || workouts.isEmpty()) {
            txtCount.setText("Ei treenejä.");
            txtTotalDuration.setText("-");
            txtLastWorkout.setText("-");
            return;
        }
        int count = workouts.size();
        int total = 0;
        Workout last = workouts.get(0);

        for (Workout w : workouts) {
            total += w.getDuration();
        }
        txtCount.setText("Treenejä yhteensä: " + count);
        txtTotalDuration.setText("Kokonaiskesto: " + total + " min");
        txtLastWorkout.setText("Viimeisin: " + last.getDate() + " - " + last.getType());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}