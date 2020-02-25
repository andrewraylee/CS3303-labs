package com.cs3303.exam1converttemp;
/*
    EXAM 1 PART 2
    Author: Andrew Lee
 */
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button getButton;
    EditText tempIn;
    TextView tempOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getButton = findViewById(R.id.compute);
        tempIn = findViewById(R.id.temp);
        tempOut = findViewById(R.id.output);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeDisplay();
            }
        });
    }
    private void computeDisplay() {
        double tempInput = 0, tempOutput = 0;
        boolean flag = false;
        if (tempIn.getText().toString().length() == 0)
            flag = true;
        else
            tempInput = Double.parseDouble(tempIn.getText().toString());
        if(!flag){
            tempOutput = (tempInput - 32.0) * 5.0/9.0;
            tempOut.setText(String.format("%.2f C",tempOutput));
            tempIn.setText(String.format("%.2f F", tempInput));
            tempOut.setTextColor(Color.parseColor("#D32F2F"));
        }
        else
            Toast.makeText(this,"Missing field value", Toast.LENGTH_SHORT).show();
    }
}
