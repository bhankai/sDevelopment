package com.ex.project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class AddWorkoutActivity extends AppCompatActivity {

    private Spinner spinnerType;
    private EditText editDuration, editNotes;
    private TextView txtDate;
    private Button btnSave;
    private String selectedDate = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_workout);

        spinnerType = findViewById(R.id.spinnerType);
        editDuration = findViewById(R.id.editDuration);
        editNotes = findViewById(R.id.editNotes);
        txtDate = findViewById(R.id.txtDate);
        btnSave = findViewById(R.id.btnSave);

        String[] types = {"Juoksu", "Kuntosali", "Pyöräily", "Uinti"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        spinnerType.setAdapter(adapter);

        txtDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePicker = new DatePickerDialog(this,
                    (view1, year, month, dayOfMonth) -> {
                        selectedDate = dayOfMonth + "." + (month + 1) + "." + year;
                        txtDate.setText(selectedDate);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        });

        btnSave.setOnClickListener(view -> {
            String type = spinnerType.getSelectedItem().toString();
            String durationStr = editDuration.getText().toString();
            String notes = editNotes.getText().toString();

            if (selectedDate.isEmpty() || durationStr.isEmpty()) {
                Toast.makeText(this, "Täytä päivämäärä ja kesto", Toast.LENGTH_SHORT).show();
                return;
            }

            int duration = Integer.parseInt(durationStr);

            Workout newWorkout = new Workout(type, selectedDate, duration, notes);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("workout", newWorkout);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}