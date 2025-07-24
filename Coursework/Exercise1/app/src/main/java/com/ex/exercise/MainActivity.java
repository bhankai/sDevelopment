package com.ex.exercise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText firstNumberEditText;
    private EditText secondNumberEditText;
    private TextView resultTextView;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        firstNumberEditText = findViewById(R.id.firstNumber);
        secondNumberEditText = findViewById(R.id.secondNumber);
        resultTextView = findViewById(R.id.resultTextView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number1 = Integer.parseInt(firstNumberEditText.getText().toString());
                int number2 = Integer.parseInt(secondNumberEditText.getText().toString());
                int summa = number1 + number2;

                resultTextView.setText(String.valueOf("Result: " + summa));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}