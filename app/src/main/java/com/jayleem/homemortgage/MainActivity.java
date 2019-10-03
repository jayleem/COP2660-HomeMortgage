package com.jayleem.homemortgage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float decMonthlyPayment;
    int intNumYears;
    float decPrincipalAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //controls instantiated
        final EditText monthlyPayment = (EditText) findViewById(R.id.monthlyPayment);
        final EditText numYears = (EditText) findViewById(R.id.numYears);
        final EditText principalAmt = (EditText) findViewById(R.id.principalAmt);

        //SharedObject instantiated
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        //register button event listener
        Button compute = (Button) findViewById(R.id.btnCompute);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPref.edit();

                //error checking
                if (monthlyPayment.getText().length() > 0) {
                    decMonthlyPayment = Float.parseFloat(monthlyPayment.getText().toString());
                    editor.putFloat("key1", decMonthlyPayment);
                } else {
                    Toast.makeText(MainActivity.this, "Monthly payment must be entered", Toast.LENGTH_LONG).show();
                }
                if (numYears.getText().length() > 0) {
                    if (Integer.parseInt(numYears.getText().toString()) == 10 || Integer.parseInt(numYears.getText().toString()) == 20 || Integer.parseInt(numYears.getText().toString()) == 30) {
                        intNumYears = Integer.parseInt(numYears.getText().toString());
                        editor.putInt("key2", intNumYears);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid amount for number of years. Valid amount is 10, 20, or 30.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Number of years must be entered. Valid amount is 10, 20, or 30.", Toast.LENGTH_LONG).show();
                }
                if (principalAmt.getText().length() > 0) {
                    decPrincipalAmt = Float.parseFloat(principalAmt.getText().toString());
                    editor.putFloat("key3", decPrincipalAmt);
                } else {
                    Toast.makeText(MainActivity.this, "Principal amount must be entered", Toast.LENGTH_LONG).show();
                }

                editor.commit();
                startActivity(new Intent(MainActivity.this, MinorActivity.class));
            }
        });
    }
}
