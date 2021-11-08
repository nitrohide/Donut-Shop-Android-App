package com.example.softwaremeth_project_5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * This class is responsible for all activities related to the donut ordering page.
 * It extends functionality to AppCompatActivity and implements AdapterView.OnItemSelectedListener.
 * @author Anuraj Dubey, Chenghao Lin
 */
public class DonutsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final double DONUT_COST = 1.39;
    public double current_subtotal = 0;

    Button addDonutsButton;
    ListView lv;
    ArrayList<String> donutList;
    ArrayAdapter<String> adapterArrList;
    /**
     * Default method created when nothing is selected.
     * @param parent AdapterView object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);
        final Spinner donutSpinner = findViewById(R.id.selectDonut);   //donut spinner
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.donuts, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donutSpinner.setAdapter(adapter1);
        donutSpinner.setOnItemSelectedListener(this);

        final Spinner quantitySpinner = findViewById(R.id.selectQuantity);  //quantity spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.quantities_Donuts, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter2);
        quantitySpinner.setOnItemSelectedListener(this);



        addDonutsButton = findViewById(R.id.addDonutsButton);
        lv = findViewById(R.id.donutOrderLV);

        donutList = new ArrayList<>();
        adapterArrList = new ArrayAdapter<>(DonutsActivity.this, android.R.layout.simple_list_item_1, donutList);
        lv.setAdapter(adapterArrList);

        addDonutsButton.setOnClickListener(new View.OnClickListener()  {  //add button logic

            /**
             * Method that acts whenever the add button is clicked. Takes values from both the
             * donut and quantity spinners.
             * @param v is just a View object for the interface.
             */
            @Override
            public void onClick(View v){
                String donutType = donutSpinner.getSelectedItem().toString();
                String qty = quantitySpinner.getSelectedItem().toString();
                String donutOrder = donutType + "(" + qty + ")" ;
                donutList.add(donutOrder);
                adapterArrList.notifyDataSetChanged();
                addToSubtotal(qty);
                TextView tv  = findViewById(R.id.subtotalValue);
                tv.setText("$" + String.format("%.2f", current_subtotal));

            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            /**
             * Acts whenever a user clicks an item they want removed from the list.
             * @param parent the AdapterView object.
             * @param view View object.
             * @param position position of item to remove in the arraylist.
             * @param id the id of the item.
             * @return
             */
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(DonutsActivity.this)
                        .setTitle("Are you sure you want to remove this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            /**
                             * Whenever a user clicks Yes to delete an item.
                             * @param dialog DialogInterface object.
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringTokenizer str = new StringTokenizer(donutList.get(position), "(");
                                String token1 = str.nextToken();
                                String token2 = str.nextToken();
                                double qty = token2.charAt(0) - '0';
                                removeFromSubtotal(qty);
                                TextView tv  = findViewById(R.id.subtotalValue);
                                tv.setText("$" + String.format("%.2f", current_subtotal));
                                donutList.remove(position);
                                adapterArrList.notifyDataSetChanged();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    /**
                     * Whenever a user clicks Cancel, to cancel their removal.
                     * @param dialog DialogInterface object.
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                return false;
            }
        });


    }

    public void addToSubtotal(String qty){
        double qtyDouble = Double.parseDouble(qty);
        current_subtotal += DONUT_COST * qtyDouble;
    }
    public void removeFromSubtotal(double qty){
        current_subtotal -= DONUT_COST * qty;
    }

    /**
     * The Toast effect given whenever an item is selected.
     * @param parent AdapterView object.
     * @param view View object
     * @param position Position in arrayList.
     * @param id id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addtoOrder(View view) {
        Context context = getApplicationContext();
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("SUBTOTAL", current_subtotal);
        intent.putExtras(bundle);
        if (current_subtotal == 0){
            Toast.makeText(context, "Order Empty",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Order Placed",Toast.LENGTH_SHORT).show();
        }
    }
}