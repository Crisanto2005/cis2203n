package com.example.exercise1_part2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int mCounter = 0;
    private TextView tvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        Button btnIncrement = findViewById(R.id.btnIncrement);

        // Restore counter if coming back from rotation
        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt("COUNT_KEY");
            tvCounter.setText(String.valueOf(mCounter));
        }

        btnIncrement.setOnClickListener(v -> {
            mCounter++;
            tvCounter.setText(String.valueOf(mCounter));
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("COUNT_KEY", mCounter);
    }
}