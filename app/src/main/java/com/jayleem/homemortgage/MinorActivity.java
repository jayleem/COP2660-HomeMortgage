package com.jayleem.homemortgage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MinorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minor);

        //instantiate controls
        TextView totalLabel = (TextView) findViewById(R.id.totalIntPaid);
        ImageView imgV = (ImageView) findViewById(R.id.imageView2);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        float decMonthlyPayment = sharedPref.getFloat("key1", 0);
        int intNumYears= sharedPref.getInt("key2", 0);
        float decPrincipalAmt = sharedPref.getFloat("key3", 0);

        //currency formatter
        float total = decMonthlyPayment * (intNumYears*12) - decPrincipalAmt;
        DecimalFormat currency = new DecimalFormat("$###,###.##");
        totalLabel.setText("Total interest paid " + currency.format(total));

        //change image
        if (intNumYears == 10) {
            imgV.setImageResource(R.drawable.ten);
        }
        if (intNumYears == 20) {
            imgV.setImageResource(R.drawable.twenty);
        }
        if (intNumYears == 30) {
            imgV.setImageResource(R.drawable.thirty);
        }
    }
}
