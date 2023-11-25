package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StockItemBaseAdapter extends BaseAdapter {

    ArrayList<StockItem> stockList;
    Context context;

    public StockItemBaseAdapter(ArrayList<StockItem> stockList, Context context) {
        this.stockList = stockList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stockList.size();
    }

    @Override
    public Object getItem(int i) {
        return stockList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

       LayoutInflater sli =( LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       View v = sli.inflate(R.layout.row_base_adapter,viewGroup,false);

        TextView itemName =v.findViewById(R.id.row_itemName);
        itemName.setText(stockList.get(i).getNameOfProduct());

        TextView unitPrice = v.findViewById(R.id.row_unitprice);
        unitPrice.setText(String.valueOf(stockList.get(i).getPriceOfProduct()));

        TextView qtyInStock = v.findViewById(R.id.row_qtyInStock);
        qtyInStock.setText(String.valueOf(stockList.get(i).getQuantityOfProduct()));
        return v;
    }
}
