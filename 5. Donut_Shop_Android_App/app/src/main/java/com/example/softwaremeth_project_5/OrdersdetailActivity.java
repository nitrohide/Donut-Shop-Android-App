package com.example.softwaremeth_project_5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * order details activity, stores the current order that can be placed, allows users to delete items from order
 *
 * @author Anuraj Dubey, Chenghao Lin
 */
public class OrdersdetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersdetail);
        /*
        Bundle order = getIntent().getExtras();
        double subtotal = order.getDouble("SUBTOTAL");
        TextView subtotalText = (TextView)findViewById(R.id.orderSubtotal);
        subtotalText.setText("Subtotal" + subtotal);
        */
    }

}