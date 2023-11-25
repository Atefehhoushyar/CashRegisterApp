package com.example.cashregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RestockPage extends AppCompatActivity {

    int newQuantity;
    private Button OkBut, CancelBut;

    private EditText newQuantityEditText;
    private ListView listView;
    private ArrayList<StockItem> products;
    private StockItemBaseAdapter baseAdaptor;
    StockItem selectedProduct;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.turquoise)));


        newQuantityEditText = findViewById(R.id.RestockText);
        OkBut = findViewById(R.id.OkButton);
        CancelBut = findViewById(R.id.CancelButton);

        products = ((AppItems) getApplication()).appStockList;



        baseAdaptor = new StockItemBaseAdapter(products, this);
        listView = findViewById(R.id.RestockList);
        listView.setAdapter(baseAdaptor);
        listView.setOnItemClickListener((parent, view, position, id) -> onRowClick(position));

        OkBut.setOnClickListener(v -> onOkClick());
        CancelBut.setOnClickListener(v -> oncanclClick());
    }

    private void oncanclClick() {
        newQuantityEditText.setText("");
        Intent RestockIntent = new Intent(RestockPage.this, MainActivity.class);
        startActivity(RestockIntent);

    }

    private void onOkClick() {
        if (selectedProduct == null) {
            Toast.makeText(this, "Please select product!!!", Toast.LENGTH_SHORT).show();
        } else {
            newQuantity = Integer.parseInt(newQuantityEditText.getText().toString());
            selectedProduct.addQuantity(newQuantity);
            baseAdaptor.notifyDataSetChanged(); // Update the ListView
            newQuantityEditText.setText("");
        }
    }

    private void onRowClick(int position) {
        selectedProduct = products.get(position);
    }

   
}