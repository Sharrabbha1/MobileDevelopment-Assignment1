package com.example.mobiledevelopment_assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{

    EditText Principal, interestRate, tenure; // show the edit texts
    TextView result; //show the textview
    Button calculate; //show the button

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Principal = findViewById(R.id.loanAmount);   // it will find by id for principal
        interestRate = findViewById(R.id.interestRate); // id for rate
        tenure = findViewById(R.id.tenure); // id for time
        result = findViewById(R.id.result); // id for result
        calculate = findViewById(R.id.calculate); // id for calculate
        calculate.setOnClickListener(view -> calculateEMI());
    }

    private void calculateEMI()
    {
        String principalInput = Principal.getText().toString();
        String rateInput = interestRate.getText().toString();
        String tenureTimeInput = tenure.getText().toString();

        if (principalInput.isEmpty() || rateInput.isEmpty() || tenureTimeInput.isEmpty())
        {
            result.setText("Error please reenter all of the values.");
            return;
        }
        if(!isNumeric(principalInput)|| !isNumeric(rateInput) || !isNumeric(tenureTimeInput))
        {
            result.setText("Invalid input ");
        }


        double principal = Double.parseDouble(principalInput);
        double annualInterestRate = Double.parseDouble(rateInput);
        double tenuretimeYears = Double.parseDouble(tenureTimeInput);

        if (principal < 10000)
        {
            result.setText("Sorry but the principal amount cannot be less than 10000");  // if the value is less than 10000 it will give an error message
            return;
        }
        double annualInterestRatefomrat=(annualInterestRate/100);  // make the rate into percent format
        double monthlyInterestRate=annualInterestRatefomrat/12;
        double tenureInMonths=tenuretimeYears * 12; // turn the year part into months
        double factor=(Math.pow(1+monthlyInterestRate,tenureInMonths)); // enter the factor part having the math power function
        double numerator= principal * monthlyInterestRate * factor;
        double denominator = factor-1;
        double emi =(numerator/denominator);

        DecimalFormat formattedDecimalForm = new DecimalFormat("#.##");
        String resultFormatted = formattedDecimalForm.format(emi);
        result.setText("the calculated monthly EMI is: " + resultFormatted);  // will print the statement in the terminal along with the value
    }
    private boolean isNumeric(String str)   //checking if the string value is numeric if so convert to double
    {
        try
        {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
