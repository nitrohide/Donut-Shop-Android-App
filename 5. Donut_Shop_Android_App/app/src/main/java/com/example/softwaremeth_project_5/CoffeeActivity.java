package com.example.softwaremeth_project_5;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


/**
 * Coffee activity  for the coffee ordering page, allows users to order coffee, with choice of addins
 *
 * @author Anuraj Dubey, Chenghao Lin
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final double ADDIN_COST = 0.20;
    private static final double MIN_COST = 1.99;
    private static final double SIZE_INCREASE_COST = 0.50;
    double subtotal = 0;
    double addinTotal = 0;
    CheckBox milk;
    CheckBox cream;
    CheckBox syrup;
    CheckBox caramel;
    CheckBox whippedCream;
    Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        Spinner spinner = findViewById(R.id.selectsize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        milk = findViewById(R.id.milk);
        cream = findViewById(R.id.cream);
        syrup = findViewById(R.id.syrup);
        caramel = findViewById(R.id.caramel);
        whippedCream = findViewById(R.id.whippedcream);
        placeOrder = findViewById(R.id.orderDonutButton);
    }

    /**
     * checks items selected and fills the quantity dropdown menu, calculates subtotal of sizes
     *
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        TextView subtotalText = findViewById(R.id.subtotal);
        switch (parent.getItemAtPosition(position).toString()){
            case "Short":
                subtotal = MIN_COST;
                break;
            case "Tall":
                subtotal = MIN_COST + SIZE_INCREASE_COST;
                break;
            case "Grande":
                subtotal = MIN_COST + (SIZE_INCREASE_COST * 2);
                break;
            case "Venti":
                subtotal = MIN_COST + (SIZE_INCREASE_COST * 3);
                break;
        }
        subtotal += addinTotal;
        subtotal = subtotalHelper(subtotal);
        subtotalText.setText("Subtotal: " + subtotal);
    }

    /**
     * action when none of the size dropdown menu are selected
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /**
     * action when milk checkbox is clicked
     */
    public void milkClicked(View view) {
        TextView subtotalText = findViewById(R.id.subtotal);
        if (milk.isChecked()){
            subtotal += ADDIN_COST;
            addinTotal += ADDIN_COST;
        }
        else if (!milk.isChecked()){
            subtotal -= ADDIN_COST;
            addinTotal -= ADDIN_COST;
        }
        subtotal = subtotalHelper(subtotal);
        subtotalText.setText("Subtotal: " + subtotal);
    }
    /**
     * action when cream checkbox is clicked
     */
    public void creamClicked(View view) {
        TextView subtotalText = findViewById(R.id.subtotal);
        if (cream.isChecked()){
            subtotal += ADDIN_COST;
            addinTotal += ADDIN_COST;

        }
        else if (!cream.isChecked()){
            subtotal -= ADDIN_COST;
            addinTotal -= ADDIN_COST;
        }
        subtotal = subtotalHelper(subtotal);
        subtotalText.setText("Subtotal: " + subtotal);
    }
    /**
     * action when syrup checkbox is clicked
     */
    public void syrupClicked(View view) {
        TextView subtotalText = findViewById(R.id.subtotal);
        if (syrup.isChecked()){
            subtotal += ADDIN_COST;
            addinTotal += ADDIN_COST;
        }
        else if (!syrup.isChecked()){
            subtotal -= ADDIN_COST;
            addinTotal -= ADDIN_COST;
        }
        subtotal = subtotalHelper(subtotal);
        subtotalText.setText("Subtotal: " + subtotal);
    }
    /**
     * action when caramel checkbox is clicked
     */
    public void caramelClicked(View view) {
        TextView subtotalText = findViewById(R.id.subtotal);
        if (caramel.isChecked()){
            subtotal += ADDIN_COST;
            addinTotal += ADDIN_COST;

        }
        else if (!caramel.isChecked()){
            subtotal -= ADDIN_COST;
            addinTotal -= ADDIN_COST;

        }
        subtotal = subtotalHelper(subtotal);
        subtotalText.setText("Subtotal: " + subtotal);
    }
    /**
     * action when whippedCream checkbox is clicked
     */
    public void whippedCreamClicked(View view) {
        TextView subtotalText = findViewById(R.id.subtotal);
        if (whippedCream.isChecked()){
            subtotal += ADDIN_COST;
            addinTotal += ADDIN_COST;
        }
        else if (!whippedCream.isChecked()){
            subtotal -= ADDIN_COST;
            addinTotal -= ADDIN_COST;
        }
        subtotal = subtotalHelper(subtotal);
        subtotalText.setText("Subtotal: " + subtotal);
    }
    /**
     * helps round numbers when subtotal is being altered
     */
    public double subtotalHelper(double subtotal){
        subtotal = Math.round(subtotal*100.0)/100.0;
        return subtotal;
    }
    /**
     * place order button, sends a bundle with an intent to main menu.
     */
    public void placeOrder(View view) {
        Context context = getApplicationContext();
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("SUBTOTAL", subtotal);
        intent.putExtras(bundle);
        if (subtotal == 0){
            Toast.makeText(context, "Order Empty",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Order Placed",Toast.LENGTH_SHORT).show();
        }
    }

}