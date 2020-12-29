package com.hlong.finallt;

public class Popular {
    private int id;
    private String imageUrl;
    private String name;
    private String store;
    private String price;
    private String rating;

    public Popular(int id, String imageUrl, String name, String store, String price, String rating) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.store = store;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

