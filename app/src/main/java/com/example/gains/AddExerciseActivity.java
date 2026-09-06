package com.example.gains;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button; // To use the button ofc
import android.widget.EditText; //Reading what the user types
import android.widget.Toast; //To show a popup
import android.content.Intent; // Carrying information between activities

public class AddExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_exercise);
        EditText exerciseNameInput = findViewById(R.id.exerciseNameInput);
        Button saveExerciseButton = findViewById(R.id.saveExerciseButton);
        saveExerciseButton.setOnClickListener(v -> {

            String exerciseName = exerciseNameInput.getText().toString().trim();

            if (exerciseName.isEmpty()) {

                Toast.makeText(
                        AddExerciseActivity.this,
                        "Please enter an exercise name",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Intent resultIntent = new Intent();

                resultIntent.putExtra("EXERCISE_NAME", exerciseName);

                setResult(RESULT_OK, resultIntent);

                finish();

            }

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}