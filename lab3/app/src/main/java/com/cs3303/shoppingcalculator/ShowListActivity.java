package com.cs3303.shoppingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {
    static TextView textBox;
    static double total;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        textBox = (TextView)findViewById(R.id.textView1);
        Bundle extras = getIntent().getExtras();
        list = extras.getStringArrayList("LIST");
        total = extras.getDouble("TOTAL");

        for (int i = 0; i < list.size(); i++) {
            textBox.setText(textBox.getText() + list.get(i) + "\n");
        }
    }
    @Override
    public void finish(){
        Intent intent = new Intent();
        intent.putExtra("TOTAL", total);
        intent.putStringArrayListExtra("LIST", list);
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
