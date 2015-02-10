package com.tamrefrank.www.cardviewapp;

/**
 * Created by v on 10-Feb-15.
 */
public class ProductsData {
    String name;
    String price;
    String description;

    int id_;
    int image;

    public ProductsData(String name,String description, String price, int id_, int image){

        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.id_ = id_;
    }

 public String getName(){
     return name;
 }


    public String getDescription(){
        return description;
    }


    public String getPrice(){
        return price;
    }


    public int getImage(){
        return image;
    }



    public int getId(){
        return id_;
    }

}
