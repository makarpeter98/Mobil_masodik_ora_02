package com.example.mobil_02_02;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultTextView = findViewById(R.id.resultTextView);

    }

    public void handleButtonClick(View view) {

        Button myButton = (Button) view;
        String inputString = myButton.getText().toString();
        Log.d("button", inputString);

        switch (inputString){
            case "=" : resultTextView.setText(calculate(resultTextView.getText().toString()));break;
            case "CE": resultTextView.setText("0"); break;
            case "+":
            case "-":
            case "*":
            case "/": if ("+-*/".indexOf(
                    resultTextView.getText().toString().charAt(
                            resultTextView.getText().toString().length()-1
                    )) > -1) break;
            default : resultTextView.append(myButton.getText());
        }
    }

    private String calculate(String expression)
    {
        try {
            String op1Str = expression.split("[+\\-*/]")[0];
            int op1 = Integer.parseInt(op1Str);
            String op2Str = expression.split("[+\\-*/]")[1];
            int op2 = Integer.parseInt(op2Str);

            char operator = expression.charAt(op1Str.length());
            Log.d("exp", op1 + "_" + operator + op2);

            switch (operator) {
                case '+':
                    return "" + (op1 + op2);
                case '-':
                    return "" + (op1 - op2);
                case '*':
                    return "" + (op1 * op2);
                case '/':
                    return "" + (op1 / op2);
                default:
                    return "";

            }
        }
        catch (Exception e)
        {
            Log.d("Exception", e.toString());
            return "";
        }
    }

}