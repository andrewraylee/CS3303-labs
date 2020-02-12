package com.cs3303.calculateprice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button getButton;
    EditText priceIn, quantityIn, newSubtotal, newTax, newTotal;
    //TextView subLabel, taxLabel, totalLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getButton = findViewById(R.id.compute);
        priceIn = findViewById(R.id.price);
        quantityIn = findViewById(R.id.quantity);
        newSubtotal = findViewById(R.id.subtotal);
        newTax = findViewById(R.id.tax);
        newTotal = findViewById(R.id.total);
        //disLabel = findViewById(R.id.discount_label);
        //disLabel.setVisibility(View.INVISIBLE);
        newSubtotal.setVisibility(View.INVISIBLE);
        newTax.setVisibility(View.INVISIBLE);
        newTotal.setVisibility(View.INVISIBLE);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeDisplay();

            }
        });
    }
    private void computeDisplay(){
        double price = 0, tax = 0, subtotal = 0, total = 0;
        int quantity = 0;
        boolean flag = false;
        if((priceIn.getText().toString().length() == 0) || (quantityIn.getText().toString().length() == 0)){
            flag = true;
        }
        else {
            price = Double.parseDouble(priceIn.getText().toString());
            quantity = Integer.parseInt(quantityIn.getText().toString());
        }
        if(!flag){
            subtotal = price * quantity;
            tax = (subtotal * 7.5)/100;
            total = subtotal + tax;

            newSubtotal.setText(String.format("$%,.2f", subtotal));
            newTax.setText(String.format("$%,.2f", tax));
            newTotal.setText(String.format("$%,.2f", total));
            priceIn.setText(String.format("$%,.2f", price));
            quantityIn.setText(String.format("%,d", quantity));
            newSubtotal.setVisibility(View.VISIBLE);
            newTax.setVisibility(View.VISIBLE);
            newTotal.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(this,"Missing field value", Toast.LENGTH_SHORT).show();
        }
    }
}
