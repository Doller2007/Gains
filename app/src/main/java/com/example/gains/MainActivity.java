package com.example.gains;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MainActivity extends AppCompatActivity {
    private TextView exerciseNameText;
    private LinearLayout emptyState;
    private LinearLayout exerciseCard;
    private final ActivityResultLauncher<Intent> addExerciseLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                            String exerciseName =
                                    result.getData().getStringExtra("EXERCISE_NAME");

                            if (exerciseName != null) {
                                emptyState.setVisibility(View.GONE);

                                exerciseCard.setVisibility(View.VISIBLE);

                                exerciseNameText.setText(exerciseName);
                            }

                        }

                    }
            );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        exerciseNameText = findViewById(R.id.exerciseNameText);
        emptyState = findViewById(R.id.emptyState);
        exerciseCard = findViewById(R.id.exerciseCard);

        Button addExerciseButton = findViewById(R.id.addExerciseButton);

        addExerciseButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExerciseActivity.class);
            addExerciseLauncher.launch(intent);
        });
        exerciseCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
            intent.putExtra("EXERCISE_NAME", exerciseNameText.getText().toString());
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}