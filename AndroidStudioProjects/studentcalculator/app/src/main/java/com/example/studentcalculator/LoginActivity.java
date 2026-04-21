package com.example.studentcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etStudentId;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // [BLANK 5]
        setContentView(R.layout.activity_login);

        etStudentId = findViewById(R.id.etStudentId);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId = etStudentId.getText().toString().trim();

                if (studentId.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your Student ID", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, CalculatorActivity.class);
                    intent.putExtra("STUDENT_ID", studentId);
                    startActivity(intent); // [BLANK 3]
                    finish();
                }
            }
        });
    }
}

