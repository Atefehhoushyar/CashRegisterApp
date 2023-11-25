package com.example.cashregister;

import android.app.Application;

import java.util.ArrayList;

public class AppItems extends Application {

    //ArrayList<> appStockList;
    ArrayList<StockItem> appStockList;
    ArrayList<HistoryList> appHistoryList= new ArrayList<>(0);

    public ArrayList<StockItem> getAppStockList(){
        if (appStockList == null){
            appStockList = new ArrayList<>(4);
            appStockList.add(new StockItem("Shirts",50, 25.25));
            appStockList.add(new StockItem("Pants",10, 20.44));
            appStockList.add(new StockItem("Shoes",100, 10.44));
            appStockList.add(new StockItem("Hats",30, 5.90));
        }
        return appStockList;
    }


    public ArrayList<StockItem> getappStockList(){
        getAppStockList();
        return appStockList;
    }


    public ArrayList<HistoryList> getAppHistoryList(){
        if(appHistoryList == null){
            appHistoryList = new ArrayList<>(0);
        }
        return appHistoryList;
    }

    public void addNewHistoryList(HistoryList newHistoryList){
        appHistoryList.add(newHistoryList);
    }
}
