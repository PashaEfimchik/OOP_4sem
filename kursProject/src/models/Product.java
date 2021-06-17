package models;

import javafx.beans.property.*;

public class Product {
    protected SimpleStringProperty supplierName;
    protected SimpleStringProperty article;
    protected SimpleStringProperty name;
    protected SimpleIntegerProperty price;

    public Product(String supplierName, String article, String name, int price){
        this.supplierName = new SimpleStringProperty(supplierName);
        this.article = new SimpleStringProperty(article);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
    }

    public String getSupplierName(){
        return supplierName.get();
    }
    public void setSupplierName(String value){
        supplierName.set(value);
    }
    public String getArticle(){
        return article.get();
    }
    public void setArticle(String value){
        article.set(value);
    }
    public String getName(){
        return name.get();
    }
    public void setName(String value){
        name.set(value);
    }
    public Integer getPrice(){
        return price.get();
    }
    public void setPrice(Integer value){
        price.set(value);
    }
}