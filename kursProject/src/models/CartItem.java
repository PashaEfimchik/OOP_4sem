package models;

import javafx.beans.property.SimpleIntegerProperty;

public class CartItem extends Product {
    private SimpleIntegerProperty quantity;
    public CartItem(String supplierName, String article, String name, int price, int quantity) {
        super(supplierName, article, name, price);
        this.quantity = new SimpleIntegerProperty(quantity);
    }
    public Integer getQuantity(){
        return quantity.get();
    }
    public void setQuantity(Integer value){
        quantity.set(value);
    }
    public Integer getTotalPrice(){
        return getPrice() * getQuantity();
    }
}