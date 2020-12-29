package com.hlong.finallt.Fragments;

public class FoodStore {
    int id;
    String anh, ten, mota, diachi, mucgia;

    public FoodStore(int id, String anh, String ten, String mota, String diachi, String mucgia) {
        this.id = id;
        this.anh = anh;
        this.ten = ten;
        this.mota = mota;
        this.diachi = diachi;
        this.mucgia = mucgia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMucgia() {
        return mucgia;
    }

    public void setMucgia(String mucgia) {
        this.mucgia = mucgia;
    }
}

