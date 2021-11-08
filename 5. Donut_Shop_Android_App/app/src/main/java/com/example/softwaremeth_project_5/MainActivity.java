package com.example.softwaremeth_project_5;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Main menu for RUCafe App, allows user to navigate to all other screens
 *
 * @author Anuraj Dubey, Chenghao Lin
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * sends user to donuts page when clicked
     */
    public void donutsButton(View view) {
        Intent intent = new Intent(this, DonutsActivity.class);
        startActivity(intent);
    }
    /**
     * sends user to Coffee page when clicked
     */
    public void coffeeButton(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }
    /**
     * sends user to orderdetails page when clicked
     */
    public void orderdetailsButton(View view) {
        Intent intent = new Intent(this, OrdersdetailActivity.class);
        //Bundle order = getIntent().getExtras();
        //intent.putExtras(order);
        startActivity(intent);
    }
    /**
     * sends user to storeorders page when clicked
     */
    public void storeordersButton(View view) {
        Intent intent = new Intent(this, StoreordersActivity.class);
        startActivity(intent);
    }


}
