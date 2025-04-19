package com.example.bustrasit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FeedbackActivity extends AppCompatActivity {

    RatingBar ratingBar;
    EditText editTextFeedback;
    Button buttonSubmitFeedback;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar = findViewById(R.id.ratingBar);
        editTextFeedback = findViewById(R.id.commentBox);
        buttonSubmitFeedback = findViewById(R.id.submitButton);
        databaseHelper = new DatabaseHelper(this);

        buttonSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId = getSharedPreferences("USER_SESSION", MODE_PRIVATE).getString("student_id", null);
                int rating = (int) ratingBar.getRating();
                String comment = editTextFeedback.getText().toString().trim();

                if (studentId == null) {
                    Toast.makeText(FeedbackActivity.this, "User not logged in", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rating == 0) {
                    Toast.makeText(FeedbackActivity.this, "Please select a rating", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save feedback
                databaseHelper.getWritableDatabase().execSQL("INSERT INTO feedback (student_id, rating, comment) VALUES (?, ?, ?)",
                        new Object[]{studentId, rating, comment});

                Toast.makeText(FeedbackActivity.this, "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                finish(); // Close activity after submission
            }
        });
    }
}
