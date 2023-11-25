package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ManagerPanel extends AppCompatActivity {
     Button HistoryButton,RestckButton;
    ArrayList<HistoryList> ListOfPurches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.turquoise)));

        RestckButton =findViewById(R.id.RestockBut);
        HistoryButton = findViewById(R.id.HistoryBut);
        HistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent HistoryListIntent = new Intent(ManagerPanel.this, RecycleViewHistoryListActivity.class);
                HistoryListIntent.putExtra("List",ListOfPurches);
                startActivity(HistoryListIntent);
               // return false;
            }
        });

        RestckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RestockIntent = new Intent(ManagerPanel.this, RestockPage.class);
                startActivity(RestockIntent);

              //  return false;
            }
        });


    }
}