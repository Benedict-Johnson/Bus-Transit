package com.example.bustrasit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editTextId, editTextPassword;
    Button buttonLogin;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String studentId = getSharedPreferences("USER_SESSION", MODE_PRIVATE).getString("student_id", null);
        if (studentId != null) {
            // Redirect to DashboardActivity
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish(); // Close LoginActivity
            return;
        }
        setContentView(R.layout.activity_login);
        editTextId = findViewById(R.id.edtStudentId);
        editTextPassword = findViewById(R.id.edtPassword);
        buttonLogin = findViewById(R.id.btnLogin);
        databaseHelper = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (id.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter ID and password", Toast.LENGTH_SHORT).show();
                } else if (databaseHelper.checkLogin(id, password)) {
                    // Save session (to keep user logged in)
                    getSharedPreferences("USER_SESSION", MODE_PRIVATE)
                            .edit()
                            .putString("student_id", id)
                            .apply();

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    // Navigate to Dashboard
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();  // Close login screen
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid ID or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}