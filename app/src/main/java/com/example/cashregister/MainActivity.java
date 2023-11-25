package com.example.cashregister;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.widget.Toast;

import java.nio.channels.NonReadableChannelException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,clearButton,BuyButton,ManagmentBut;
    TextView productName,totalPrice,quantityText;
    int stockIndex = -1;
    String quantity = ("");

    Double totalSellingPrice= 0.0;
    boolean stockAvailable,itemSelected = false;
    StockItem currentStockItem = null;
    StockItemBaseAdapter stockAdapter;
    HistoryListRecyclerAdapter Historyadapter;

    ArrayList<HistoryList> historyList;
    ArrayList<StockItem> StockList;
    String product_name;
    Double PriceofItem =0.0;




    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.turquoise)));

        ManagmentBut = findViewById(R.id.ManagmentButton);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);
        clearButton=findViewById(R.id.clearButton);
        historyList =((AppItems)getApplication()).getAppHistoryList();
        StockList=((AppItems)getApplication()).getappStockList();

        BuyButton = findViewById(R.id.BuyButton);
        productName=findViewById(R.id.ProductTxt);

        totalPrice=findViewById(R.id.totalPrice);
        quantityText=findViewById(R.id.quantityTxt);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        BuyButton.setOnClickListener(this);

        ListView stockItemList =findViewById(R.id.stockItemList);
        stockAdapter =new StockItemBaseAdapter(((AppItems)getApplication()).getAppStockList(),this);
        stockItemList.setAdapter(stockAdapter);



        stockItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                stockIndex = i;
                currentStockItem = ((AppItems)getApplication()).getAppStockList().get(stockIndex);
                productName.setText(currentStockItem.getNameOfProduct());
                itemSelected = true;
                if(quantity != ""){
                    totalSellingPrice= currentStockItem.getPriceOfProduct()* Integer.parseInt(quantity);
                    totalPrice.setText(String.format("%2f",totalSellingPrice));
                }

            }
        });
        ManagmentBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ManagerPanel.class));
              // return false;
            }
        });


    }
    @SuppressLint({"NonConstantResourceId"})
    @Override
    public void onClick(View view) {
        int buttonId = view.getId();
        String value;
        switch (buttonId){
            case R.id.clearButton:
                clearAllFields();
                itemSelected = false;
                break;
            case R.id.BuyButton:

                if (stockIndex != -1 && quantity != ""){
                    int selectedQty = Integer.parseInt(quantity);
                    stockAvailable = validateQty (stockIndex , selectedQty);
                    if(stockAvailable) {
                        purchaseItem(stockIndex, selectedQty);
                    }else {
                        Toast.makeText(this,R.string.insufficient_qty, Toast.LENGTH_SHORT).show();
                        clearAllFields();
                    }
                    itemSelected = false;
                }
                else {
                    Toast.makeText(this,R.string.Missing_Fields, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                value = ((Button)view).getText().toString();
                quantity +=value;
                quantityText.setText(quantity);
                if (itemSelected){
                   //totalSellingPrice = currentStockItem.getPriceOfProduct() * Integer.parseInt(quantity);
                   totalPrice.setText(String.format("%2f",totalSellingPrice));
                }
        }
    }
    boolean validateQty (int itemIndex,int qtyNeeded){
        int availaleQty = ((AppItems)getApplication()).getAppStockList().get(stockIndex).getQuantityOfProduct();
        if (qtyNeeded <= availaleQty)
            return true;
        else
            return false;
    }
    void clearAllFields(){
        stockIndex = -1;
        productName.setText("");
        quantity = "";
        quantityText.setText("");
        totalPrice.setText("");
    }

    void purchaseItem(int stockIndex,int selectQty){
         int availableQty = ((AppItems)getApplication()).getAppStockList().get(stockIndex).getQuantityOfProduct();
        ((AppItems)getApplication()).getAppStockList().get(stockIndex).setQuantityOfProduct(availableQty - selectQty);

        totalSellingPrice =currentStockItem.getPriceOfProduct() * selectQty;
        totalPrice.setText(String.format("%2f",totalSellingPrice));
        totalSellingPrice = Double.parseDouble(String.format("%2f",totalSellingPrice));
        DateTimeFormatter dateOfOrder = DateTimeFormatter.ofPattern("yyyy/MM/dd");//3/ bring code for date and time now from google
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = dateOfOrder.format(now);
        HistoryList newHistory = new HistoryList(currentStockItem.getNameOfProduct(),totalSellingPrice,selectQty, formattedDate);
        ((AppItems)getApplication()).addNewHistoryList(newHistory);
       //Date purchasedTime = Calender.getInstance().getTime();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("THANK YOU FOR YOUR PURCHES" + "\n "+"You Have Purchased " + selectQty + " " + currentStockItem.getNameOfProduct()+" for "+ totalSellingPrice);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {  clearAllFields();}
        });
        builder.create().show();
        stockAdapter.stockList= ((AppItems)getApplication()).getAppStockList();
        stockAdapter.notifyDataSetChanged();


    }
}