package com.cs3303.djiacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/*
Author: Andrew Lee
Date: 03/12/20
Title: DJIA Calculator (Lab 5)
 */
public class MainActivity extends AppCompatActivity {
    TextView averageTxt, timeTxt;
    Button compute;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        averageTxt = findViewById(R.id.textView1);
        timeTxt = findViewById(R.id.textView2);
        compute = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalcAverage().execute("AAPL","AXP","BA","CAT","CSCO","CVX","DIS","DOW","GS",
                        "HD","IBM","INTC","JNJ","JPM","KO","MCD","MMM","MRK","MSFT","NKE","PFE",
                        "PG","TRV","UNH","UTX","V","VZ","WBA" ,"WMT", "XOM");
            }
        });

    }

    private class CalcAverage extends AsyncTask<String, Integer, String> {
        String st = "";
        double begin, end, timeTaken;
        @Override
        protected String doInBackground(String... strings) {
            double result = 0;
            int n = 0, count = 1;
            String [] ar;
            String [] s1;
            for (int i = 0; i < 30; i++) {
                try {
                    URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/" + strings[i] +
                            "?interval=2m");
                    URLConnection urlConnection = url.openConnection(); // creates a URL connection object
                    InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream()); // create InputStreamReader instance
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // create a BufferedReader instance
                    String line = bufferedReader.readLine(); // line contains the stock price information
                    ar = line.split("\"symbol\":");
                    s1 = ar[1].split(",");  // s1[0] now has the ticker symbol
                    ar = line.split("\"previousClose\":");
                    s1 = ar[1].split(","); //a1[0] must now have the price
                    result += Double.parseDouble(s1[0]);
                    inputStreamReader.close();
                    bufferedReader.close();
                    n++;
                    if (n == 3) {
                        n = 0;
                        publishProgress(count++);
                    }

                } catch (MalformedURLException mle) {
                    mle.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            result = result / 0.14748071991788;
            st = String.format("%,.2f", result);
            return st;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            begin = System.nanoTime();
            averageTxt.setText("DJIA computation in progress...");
        }

        @Override
        // this method maybe used to communicate with the UI thread
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            end = System.nanoTime();
            timeTaken = (end - begin)/1000000000;
            averageTxt.setText("DJIA for the previous day is " + s);
            timeTxt.setText(String.format("Time taken for the computation is %.2f sec\n", timeTaken));

        }

        @Override
        // this method talks to the UI thread
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }
}
