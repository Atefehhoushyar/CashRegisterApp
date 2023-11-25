package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {

    TextView reportText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.turquoise)));


        reportText = findViewById(R.id.reporttext);
        HistoryList historyObject =getIntent().getParcelableExtra("historyObject");
        reportText.setText("Product:" + historyObject.getProductName() + "\n"
                + "total Price: " + historyObject.getTotalCost() + " \n"
                + "Purchase Date: " + historyObject.getPurchesDate() + "\n");


    }
}