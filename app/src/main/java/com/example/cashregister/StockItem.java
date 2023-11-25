package com.example.cashregister;

public class StockItem {
    private String NameOfProduct;
    private int QuantityOfProduct;
    private double PriceOfProduct;

    public StockItem() {

    }

    public String getNameOfProduct() {
        return NameOfProduct;
    }

    public int getQuantityOfProduct() {
        return QuantityOfProduct;
    }

    public double getPriceOfProduct() {
        return PriceOfProduct;
    }


    public StockItem(String nameOfProduct, int quantityOfProduct, double priceOfProduct) {
        NameOfProduct = nameOfProduct;
        QuantityOfProduct = quantityOfProduct;
        PriceOfProduct = priceOfProduct;

    }

    public void setNameOfProduct(String nameOfProduct) {
        NameOfProduct = nameOfProduct;
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        QuantityOfProduct = quantityOfProduct;
    }


    public boolean newquant(int i) {
        this.QuantityOfProduct += i;
        return true;
    }

    public void setPriceOfProduct(double priceOfProduct) {
        PriceOfProduct = priceOfProduct;
    }

    public void   addQuantity(int quantity) {
        this.QuantityOfProduct += quantity;
    }

}