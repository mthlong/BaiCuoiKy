package com.hlong.finallt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hlong.finallt.Fragments.FoodStore;

import java.util.ArrayList;

public class QueryStore {
    Store store;

    public QueryStore(Context context) {
        store = new Store(context);
    }
    public ArrayList<OrderGioHang> getAll0() {
        ArrayList<OrderGioHang> ds = new ArrayList<>();
        SQLiteDatabase db = store.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsGioHang", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String image = cs.getString(1);
            String tench = cs.getString(2);
            String tenmon = cs.getString(3);
            String soluong = cs.getString(4);
            String tongtien = cs.getString(5);
            OrderGioHang a = new OrderGioHang(id,tench,tenmon,soluong,tongtien,image);
            ds.add(a);
            cs.moveToNext();
        }
        cs.close();
        return ds;
    }
    public ArrayList<Nearby1> getAll1() {
        ArrayList<Nearby1> ds = new ArrayList<>();
        SQLiteDatabase db = store.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsCuahang", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String image = cs.getString(1);
            String title = cs.getString(2);
            String description = cs.getString(3);
            String diachi = cs.getString(4);
            String mucgia = cs.getString(5);
            Nearby1 a = new Nearby1(id,title,diachi,mucgia,image,description,30,4);
            ds.add(a);
            cs.moveToNext();
        }
        cs.close();
        return ds;
    }

    public ArrayList<Order_History> getAll3() {
        ArrayList<Order_History> ds = new ArrayList<>();
        SQLiteDatabase db = store.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM listHistory", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String image = cs.getString(1);
            String tench = cs.getString(2);
            String tenmon = cs.getString(3);
            String soluong = cs.getString(4);
            String tongtien = cs.getString(5);
            String diachi = cs.getString(5);
            String tenkh = cs.getString(5);
            String sdt = cs.getString(5);
            Order_History a = new Order_History(id,image,tench,tenmon,soluong,tongtien,diachi,tenkh,sdt);
            ds.add(a);
            cs.moveToNext();
        }
        cs.close();
        return ds;
    }
    public ArrayList<FoodStore> getAll() {
        ArrayList<FoodStore> ds = new ArrayList<>();
        SQLiteDatabase db = store.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsCuahang", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String image = cs.getString(1);
            String title = cs.getString(2);
            String description = cs.getString(3);
            String diachi = cs.getString(4);
            String mucgia = cs.getString(5);
            FoodStore a = new FoodStore(id, image, title, description, diachi, mucgia);
            ds.add(a);
            cs.moveToNext();
        }
        cs.close();
        return ds;
    }
    public ArrayList<ConfirmOrder> getAllConfirm() {
        ArrayList<ConfirmOrder> ds = new ArrayList<>();
        SQLiteDatabase db = store.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsOrder", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String image = cs.getString(1);
            String tench = cs.getString(2);
            String tenmon = cs.getString(3);
            String soluong = cs.getString(4);
            String tongtien = cs.getString(5);
            String diachi = cs.getString(5);
            String tenkh = cs.getString(5);
            String sdt = cs.getString(5);
            ConfirmOrder a = new ConfirmOrder(id,image,tench,tenmon,soluong,tongtien,diachi,tenkh,sdt);
            ds.add(a);
            cs.moveToNext();
        }
        cs.close();
        return ds;
    }
}


