package com.hlong.finallt;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmOrderActivity extends AppCompatActivity {
    ImageView img, back,imgGiohang;
    TextView proName, proName1, proDC,price,total,slg;

    Store cuaHang;
    String imgrs,nameFood;
    int id;
    String namech;
    EditText edt_hoten,edt_sdt,edt_diachi;
    int tong ;
    Button btn_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        final Intent i = getIntent();
        id = i.getIntExtra("idgh", 0);
        namech=i.getStringExtra("namech");

        cuaHang = new Store(this);
        price=findViewById(R.id.price);

        slg=findViewById(R.id.txt_slg);
        total=findViewById(R.id.tongtien);

        proName1=findViewById(R.id.nameStore);
        proName = findViewById(R.id.name);
        img = findViewById(R.id.imageView5 );
        proDC = findViewById(R.id.txtLocation);

        SQLiteDatabase db = cuaHang.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsGioHang WHERE id =?",new String[]{id+""});
        if (cs.moveToFirst()){
            do {
                int id = cs.getInt(0);
                String image = cs.getString(1);
                String tench = cs.getString(2);
                String tenmon = cs.getString(3);
                String soluong = cs.getString(4);
                String tongtien = cs.getString(5);
                nameFood=tenmon;
                tong=Integer.parseInt(soluong);
                proName1.setText(tench);
                proName.setText(tenmon);
                int resId = getResources().getIdentifier(image, "drawable", getPackageName());
                img.setImageResource(resId);
                imgrs=image;
                total.setText(tongtien);
                slg.setText(soluong);
            }while (cs.moveToNext());
        }
        db.close();

        back = findViewById(R.id.imageback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        edt_diachi=findViewById(R.id.edt_diachi);
        edt_hoten=findViewById(R.id.edt_hoten);
        edt_sdt=findViewById(R.id.edt_sdt);

        btn_order=findViewById(R.id.btn_dathang);
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkempty();
                insetConfirmOrder();
            }
        });
        imgGiohang=findViewById(R.id.img_giohang);
        imgGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ConfirmOrderActivity.this,OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    public void insetConfirmOrder(){
        try {
            Store ch= new Store(this);
            SQLiteDatabase sqLiteDatabase =ch.getWritableDatabase();
            ContentValues values= new ContentValues();
            values.put("anh",imgrs);
            values.put("tench",proName1.getText().toString());
            values.put("tenmon",nameFood);
            values.put("soluong",slg.getText().toString());
            values.put("tongtien",total.getText().toString());
            values.put("diachi",edt_diachi.getText().toString());
            values.put("tenkh",edt_hoten.getText().toString());
            values.put("sdt",edt_sdt.getText().toString());
            sqLiteDatabase.insert("dsOrder",null,values);
            Toast.makeText(ConfirmOrderActivity.this,"Đặt Hàng Thành Công",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(ConfirmOrderActivity.this,""+e,Toast.LENGTH_SHORT).show();
        }
    }
    private void checkempty() {
        final String fullName = edt_hoten.getText().toString();
        final String location = edt_diachi.getText().toString();
        final String phoneNum = edt_sdt.getText().toString();

        if (fullName.isEmpty()) {
            edt_hoten.setError("Nhập họ tên");
            edt_hoten.requestFocus();
            return;
        }

        if (phoneNum.isEmpty()) {
            edt_sdt.setError("Nhập vào số điện thoại");
            edt_sdt.requestFocus();
            return;
        }
        if (location.isEmpty()) {
            edt_diachi.setError("Nhập vào địa chỉ");
            edt_diachi.requestFocus();
            return;
        }
    }
}
