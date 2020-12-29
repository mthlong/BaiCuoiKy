package com.hlong.finallt;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmGetIteamActivity extends AppCompatActivity {

    ImageView img, back, imgGiohang;
    TextView proName, proName1, proDC, price, total, slg;
    Store cuaHang;
    String imgrs, nameStore, nameFood;
    int id;
    String namech;
    TextView txt_hoten, txt_sdt, txt_diachi;
    int tong;
    Button btn_xn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_history);
        final Intent i = getIntent();
        id = i.getIntExtra("idgorder", 0);
        namech = i.getStringExtra("namech");
        txt_diachi = findViewById(R.id.txt_diachi);
        txt_hoten = findViewById(R.id.txt_hoten);
        txt_sdt = findViewById(R.id.txt_sdt);


        cuaHang = new Store(this);

        slg = findViewById(R.id.txt_slg);
        total = findViewById(R.id.tongtien);

        proName1 = findViewById(R.id.nameStore);
        proName = findViewById(R.id.name);
        img = findViewById(R.id.imageView5);
        proDC = findViewById(R.id.txtLocation);

        SQLiteDatabase db = cuaHang.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsOrder WHERE id =?", new String[]{id + ""});
        if (cs.moveToFirst()) {
            do {
                int id = cs.getInt(0);
                String image = cs.getString(1);
                String tench = cs.getString(2);
                String tenmon = cs.getString(3);
                String soluong = cs.getString(4);
                String tongtien = cs.getString(5);
                String diachi = cs.getString(6);
                String tenkh = cs.getString(7);
                String sdt = cs.getString(8);
                nameFood = tenmon;
                tong = Integer.parseInt(soluong);
                proName1.setText(tench);
                proName.setText(tenmon);
                int resId = getResources().getIdentifier(image, "drawable", getPackageName());
                img.setImageResource(resId);
                imgrs = image;
                total.setText(tongtien);
                slg.setText(soluong);
                txt_diachi.setText(diachi);
                txt_hoten.setText(tenkh);
                txt_sdt.setText(sdt);
            } while (cs.moveToNext());
        }
        db.close();

        back = findViewById(R.id.imageback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btn_xn = findViewById(R.id.btn_xnNhanhang);
        btn_xn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insetHistory();

            }
        });
        imgGiohang = findViewById(R.id.img_giohang);
        imgGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmGetIteamActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    public void insetHistory() {
        try {
            Store ch = new Store(this);
            SQLiteDatabase sqLiteDatabase = ch.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("anh", imgrs);
            values.put("tench", nameStore);
            values.put("tenmon", nameFood);
            values.put("soluong", slg.getText().toString());
            values.put("tongtien", total.getText().toString());
            values.put("diachi", txt_diachi.getText().toString());
            values.put("tenkh", txt_hoten.getText().toString());
            values.put("sdt", txt_sdt.getText().toString());
            sqLiteDatabase.insert("listHistory", null, values);
            Toast.makeText(ConfirmGetIteamActivity.this, "Cảm ơn bạn đã đặt hàng qua FoodDeliver", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(ConfirmGetIteamActivity.this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteItemOrder() {
        try {

            Store ch = new Store(this);
            SQLiteDatabase db = ch.getWritableDatabase();
            long rows = db.delete("dsOrder", "MemberID = ?", new String[]{String.valueOf(id)});
            db.close();
        } catch (Exception e) {


        }
    }
}