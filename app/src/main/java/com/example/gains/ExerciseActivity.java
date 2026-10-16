package com.example.gains;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);
        EditText weightInput = findViewById(R.id.weightInput);
        EditText repsInput = findViewById(R.id.repsInput);
        Button saveSetButton = findViewById(R.id.saveSetButton);

        saveSetButton.setOnClickListener(v -> {
            String weight = weightInput.getText().toString().trim();
            String reps = repsInput.getText().toString().trim();

            if (weight.isEmpty() || reps.isEmpty()) {
                Toast.makeText(this, "Enter weight and reps", Toast.LENGTH_SHORT).show();
            } else {
                weightInput.setText("");
                repsInput.setText("");
            }
        });


        String exerciseName = getIntent().getStringExtra("EXERCISE_NAME");

        TextView exerciseTitle = findViewById(R.id.exerciseTitle);
        exerciseTitle.setText(exerciseName);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}