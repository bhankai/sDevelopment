package com.ex.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_WORKOUT = 1;

    private RecyclerView recyclerView;
    private WorkoutAdapter adapter;
    private List<Workout> workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        workoutList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new WorkoutAdapter(workoutList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LinearLayout main = findViewById(R.id.main);
        Button btnAdd = new Button(this);
        btnAdd.setText("Lisää treeni");
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddWorkoutActivity.class);
            startActivityForResult(intent, ADD_WORKOUT);
        });
        main.addView(btnAdd, 0);

        Button btnSummary = new Button(this);
        btnSummary.setText("Näytä yhteenveto");
        btnSummary.setOnClickListener(v -> {
            Intent intent = new Intent(this, SummaryActivity.class);
            intent.putExtra("workouts", new ArrayList<>(workoutList));
            startActivity(intent);
        });
        main.addView(btnSummary);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_WORKOUT && resultCode == RESULT_OK) {
            Workout workout = (Workout) data.getSerializableExtra("workout");
            workoutList.add(0, workout);
            adapter.notifyItemInserted(0);
        }
    }
}