package com.cs3303.shoppingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {
    static double price = 0, quantity = 0, taxAmount = 0, totalAmount = 0, tax = 0;
    static int numberOfItems;
    static String name;
    static TextView numberItems;
    static EditText nameIn, priceIn, quantityIn;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        numberItems = findViewById(R.id.itemNumber);
        nameIn = findViewById(R.id.name);
        priceIn = findViewById(R.id.price);
        quantityIn = findViewById(R.id.quantity);
        Bundle extras = getIntent().getExtras();
        numberOfItems = extras.getInt("ITEMS");
        tax = extras.getDouble("TAX");
        totalAmount = extras.getDouble("TOTAL");
        list = extras.getStringArrayList("LIST");

        numberItems.setText(String.valueOf(list.size()));

        Log.i("AddItemActivity", "totalAmount = " + totalAmount);

    }

    @Override
    public void finish() {

        boolean flag = false;
        if (nameIn.getText().toString().length() == 0) {
            flag = true;
        }
        else {
            name = nameIn.getText().toString();
        }
        if(priceIn.getText().toString().length() == 0) {
            flag = true;
        }
        else {
            price = Double.parseDouble(priceIn.getText().toString());
        }
        if(quantityIn.getText().toString().length() == 0) {
            flag = true;
        }
        else {
            quantity = Double.parseDouble(quantityIn.getText().toString());
        }
        if(!flag) {
            taxAmount = price * quantity * (tax / 100);
            totalAmount = totalAmount+ taxAmount + (price * quantity);
            priceIn.setText(String.format("%, .2f", price));
            quantityIn.setText(String.format("%, .0f", quantity));
            numberOfItems++;
            list.add(String.format("%-16s $%,.2f %,.0f", name, price, quantity));

            Log.i("AddItemActivity", "totalAmount in finish = " + totalAmount);

        }
        else {
            Toast.makeText(this, "Missing field value", Toast.LENGTH_SHORT).show();
        }

        Intent i = new Intent();
        i.putExtra("TOTAL", totalAmount);
        i.putExtra("ITEMS", numberOfItems);
        i.putStringArrayListExtra("LIST", list);
        setResult(RESULT_OK, i);
        super.finish();
    }
}
