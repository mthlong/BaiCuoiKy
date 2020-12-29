package com.hlong.finallt;

public class OrderGioHang {
    int id;
    String restaurant_name;
    String food_name;
    String iteams;
    String gia;
    String imageFood;

    public OrderGioHang(int id,String restaurant_name, String food_name, String iteams, String gia, String imageFood) {
        this.id=id;
        this.restaurant_name = restaurant_name;
        this.food_name = food_name;
        this.iteams = iteams;
        this.gia = gia;
        this.imageFood = imageFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getIteams() {
        return iteams;
    }

    public void setIteams(String iteams) {
        this.iteams = iteams;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }
}

