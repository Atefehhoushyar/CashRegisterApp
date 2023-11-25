package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleViewHistoryListActivity extends AppCompatActivity implements HistoryListRecyclerAdapter.HistoryClickListener{

    RecyclerView ProductlistRecyclerView;
    ArrayList<HistoryList> ListOfHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_history_list);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.turquoise)));

        ArrayList<HistoryList> HistoryList = ((AppItems)getApplication()).getAppHistoryList();

        ProductlistRecyclerView = findViewById(R.id.RecycleList);
       HistoryListRecyclerAdapter Historyadapter = new HistoryListRecyclerAdapter(HistoryList,this);
        Historyadapter.listener= this;
        ProductlistRecyclerView.setAdapter(Historyadapter);
        ProductlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void HistoryClicked(int i) {
        Intent HistoryListIntent = new Intent(RecycleViewHistoryListActivity.this, HistoryDetailActivity.class);
        HistoryList historyObject = ((AppItems)getApplication()).getAppHistoryList().get(i);
        HistoryListIntent.putExtra("historyObject",historyObject);
        startActivity(HistoryListIntent);

    }
}