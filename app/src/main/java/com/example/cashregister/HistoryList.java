package com.example.cashregister;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class HistoryList implements Parcelable {
    String productName;
    double totalCost;
    int PurchesNumber;
    String PurchesDate;

    protected HistoryList(Parcel in) {
        productName = in.readString();
        totalCost = in.readDouble();
        PurchesNumber = in.readInt();
        PurchesDate = in.readString();
    }

    public static final Creator<HistoryList> CREATOR = new Creator<HistoryList>() {
        @Override
        public HistoryList createFromParcel(Parcel in) {
            return new HistoryList(in);
        }

        @Override
        public HistoryList[] newArray(int size) {
            return new HistoryList[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getPurchesNumber() {
        return PurchesNumber;
    }

    public String getPurchesDate() {
        return PurchesDate;
    }

    public HistoryList(String productName, double totalCost, int purchesNumber, String purchesDate) {
        this.productName = productName;
        this.totalCost = totalCost;
        PurchesNumber = purchesNumber;
        PurchesDate = purchesDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeDouble(totalCost);
        dest.writeInt(PurchesNumber);
        dest.writeString(PurchesDate);
    }
}