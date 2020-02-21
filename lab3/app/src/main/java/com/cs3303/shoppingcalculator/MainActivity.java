package com.cs3303.shoppingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
/*
    Lab 3 -- Shopping Calculator
    Group Members -- Andrew Lee (Submission directory CS3303-01)(50% contribution)
                     Alexander Valle (50% contribution)
    Description -- A calculator to compute the total cost after tax of items of any given
                   name, price, and quantity. The items are stored in a list tht can be viewed after
                   adding any number of items to the list. The total will remain calculated and the
                   list will continue to hold the name, price, and quantity until the program's
                   termination.
 */
public class MainActivity extends AppCompatActivity {
    static double price = 0, quantity = 0, taxAmount = 0, totalAmount = 0, tax = 0;
    static int numberOfItems = 0;
    static String name;
    ArrayList<String> list = new ArrayList<String>();
    final int MY_REQUEST_CODE = 1;
    Button computeButton, listButton, itemButton;
    EditText itemIn, priceIn, quantityIn, taxIn, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        computeButton = findViewById(R.id.compute);
        listButton = findViewById(R.id.show_list);
        itemButton = findViewById(R.id.add_item);
        itemIn = findViewById(R.id.name);
        priceIn = findViewById(R.id.price);
        quantityIn = findViewById(R.id.subtotal);
        taxIn = findViewById(R.id.tax);
        total = findViewById(R.id.total);
        total.setVisibility(View.INVISIBLE);
        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computeDisplay();
            }
        });
        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItemActivity(v);
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowListActivity(v);
            }
        });
    }

    /*
    Going to the add item activity
     */

    public void AddItemActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
        intent.putExtra("ITEMS", numberOfItems);
        intent.putExtra("TAX", tax);
        intent.putExtra("TOTAL", totalAmount);
        intent.putStringArrayListExtra("LIST", list);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    /*
    Show list activity
     */

    public void ShowListActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), ShowListActivity.class);
        intent.putExtra("TOTAL", totalAmount);
        intent.putStringArrayListExtra("LIST", list);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }
    /*
    computing the total value when they click the compute button
     */

    private void computeDisplay() {
        boolean flag = false;
        if(itemIn.getText().toString().length() == 0) {
            flag = true;
        }
        else {
            name = itemIn.getText().toString();
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
        if(taxIn.getText().toString().length() == 0) {
            flag = true;
        }
        else {
            tax = Double.parseDouble(taxIn.getText().toString());
        }
        if(!flag) {
            taxAmount = price * quantity * (tax / 100);
            totalAmount = taxAmount + (price * quantity);
            priceIn.setText(String.format("%, .2f", price));
            quantityIn.setText(String.format("%, .0f", quantity));
            taxIn.setText(String.format("%, .2f", tax));
            total.setText(String.format("$%, .2f", totalAmount));
            itemIn.setEnabled(false);
            priceIn.setEnabled(false);
            quantityIn.setEnabled(false);
            taxIn.setEnabled(false);
            total.setVisibility(View.VISIBLE);
            computeButton.setEnabled(false);
            listButton.setEnabled(true);
            itemButton.setEnabled(true);
            numberOfItems++;
            list.add(String.format("%-16s $%,.2f %,.0f", name, price, quantity));
        }
        else {
            Toast.makeText(this, "Missing field value", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    get the values from additional activities.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                    totalAmount = data.getExtras().getDouble("TOTAL");
            }
        }
        list = data.getStringArrayListExtra("LIST");
        totalAmount = data.getExtras().getDouble("TOTAL");
        total.setText(String.format("$%, .2f", totalAmount));
        itemIn.setEnabled(false);
        priceIn.setEnabled(false);
        quantityIn.setEnabled(false);
        taxIn.setEnabled(false);
    }
}
