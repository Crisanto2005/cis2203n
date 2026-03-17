package com.example.mock_midterm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private TextView tvDisplay;


    private Button btn0, btn1, btn2, btn3, btn4;
    private Button btn5, btn6, btn7, btn8, btn9;


    private Button btnPlus, btnMinus, btnMultiply, btnDivide;

    private Button btnEquals, btnClear, btnCustom;

    private String operandA = "";
    private String operandB = "";
    private String operator = "";
    private boolean typingOperandB = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        tvDisplay = findViewById(R.id.textView);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);

        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);
        btnCustom = findViewById(R.id.btnCustom);

        View.OnClickListener numberListener = v -> {
            Button pressed = (Button) v;
            String digit = pressed.getText().toString();

            if (typingOperandB) {
                operandB += digit;
                tvDisplay.setText(operandB);
            } else {
                operandA += digit;
                tvDisplay.setText(operandA);
            }
        };

        btn0.setOnClickListener(numberListener);
        btn1.setOnClickListener(numberListener);
        btn2.setOnClickListener(numberListener);
        btn3.setOnClickListener(numberListener);
        btn4.setOnClickListener(numberListener);
        btn5.setOnClickListener(numberListener);
        btn6.setOnClickListener(numberListener);
        btn7.setOnClickListener(numberListener);
        btn8.setOnClickListener(numberListener);
        btn9.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = v -> {
            Button pressed = (Button) v;
            operator = pressed.getText().toString();
            typingOperandB = true;
            tvDisplay.setText("0");
        };

        btnPlus.setOnClickListener(operatorListener);
        btnMinus.setOnClickListener(operatorListener);
        btnMultiply.setOnClickListener(operatorListener);
        btnDivide.setOnClickListener(operatorListener);

        btnEquals.setOnClickListener(v -> {
            if (operandA.isEmpty() || operandB.isEmpty() || operator.isEmpty()) {
                return;
            }

            double numA = Double.parseDouble(operandA);
            double numB = Double.parseDouble(operandB);
            double result = 0;

            switch (operator) {
                case "+":
                    result = numA + numB;
                    break;
                case "-":
                    result = numA - numB;
                    break;
                case "*":
                    result = numA * numB;
                    break;
                case "/":
                    if (numB == 0) {
                        tvDisplay.setText("Cannot divide by zero");
                        operandA = "";
                        operandB = "";
                        operator = "";
                        typingOperandB = false;
                        return;
                    }
                    result = numA / numB;
                    break;
            }

            tvDisplay.setText(String.valueOf(result));
            operandA = String.valueOf(result);
            operandB = "";
            operator = "";
            typingOperandB = false;
        });

        btnClear.setOnClickListener(v -> {
            operandA = "";
            operandB = "";
            operator = "";
            typingOperandB = false;
            tvDisplay.setText("0");
        });

        btnCustom.setOnClickListener(v -> {
            if (!operandA.isEmpty()) {
                double num = Double.parseDouble(operandA);
                double result = num * 3.69;
                tvDisplay.setText(String.valueOf(result));
                operandA = String.valueOf(result);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("OPERAND_A", operandA);
        outState.putString("OPERAND_B", operandB);
        outState.putString("OPERATOR", operator);
        outState.putBoolean("TYPING_B", typingOperandB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operandA = savedInstanceState.getString("OPERAND_A");
        operandB = savedInstanceState.getString("OPERAND_B");
        operator = savedInstanceState.getString("OPERATOR");
        typingOperandB = savedInstanceState.getBoolean("TYPING_B");

        if (typingOperandB && !operandB.isEmpty()) {
            tvDisplay.setText(operandB);
        } else if (!operandA.isEmpty()) {
            tvDisplay.setText(operandA);
        } else {
            tvDisplay.setText("0");
        }
    }



}