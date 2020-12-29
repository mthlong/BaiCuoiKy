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

public class FoodDetails extends AppCompatActivity {
    ImageView img, back,add,remove,imgGiohang;
    TextView proName, proName1, proDC,price,total,slg,tmp;
    Store cuaHang;
    String price1,imgrs,nameStore,nameFood;
    int id;
    String idch;
    int tong =1,tongTien;
    Button btn_giohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        final Intent i = getIntent();
        id = i.getIntExtra("id", 0);
        idch=i.getStringExtra("idch");

        cuaHang = new Store(this);
        price=findViewById(R.id.price);
        add=findViewById(R.id.addslg);
        slg=findViewById(R.id.slg);
        total=findViewById(R.id.giatong);
        remove=findViewById(R.id.removeslg);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tong=tong+1;
                tongTien=tong*Integer.parseInt(price1.trim());
                slg.setText(String.valueOf(tong));
                tmp.setText(String.valueOf(tongTien));
                total.setText(String.valueOf(tongTien)+"  Đ");
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tong >1){
                    tong=tong-1;
                    tongTien=tong*Integer.parseInt(price1.trim());
                    slg.setText(String.valueOf(tong));
                    tmp.setText(String.valueOf(tongTien));
                    total.setText(String.valueOf(tongTien)+"  Đ");
                }
                else {
                    Toast.makeText(FoodDetails.this,"Đặt ít nhất một món",Toast.LENGTH_SHORT).show();
                }
            }
        });



        proName1=findViewById(R.id.nameStore);
        proName = findViewById(R.id.name);
        img = findViewById(R.id.imageView5 );
        proDC = findViewById(R.id.txtLocation);

        SQLiteDatabase db = cuaHang.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsSanpham WHERE id =?",new String[]{id+""});
        if (cs.moveToFirst()){
            do {
                int id = cs.getInt(0);
                String image = cs.getString(1);
                String title = cs.getString(2);
                String gia = cs.getString(4);
                nameFood=title;
                proName.setText(title);
                int resId = getResources().getIdentifier(image, "drawable", getPackageName());
                img.setImageResource(resId);
                price1=gia;
                price.setText(gia+"  Đ");
                imgrs=image;
                total.setText(gia+"  Đ");

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
        getNameStore();

        imgGiohang=findViewById(R.id.img_giohang);
        imgGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FoodDetails.this,OrderActivity.class);
                startActivity(intent);
            }
        });

        btn_giohang=findViewById(R.id.btn_giohang);
        btn_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertGioHang();
            }
        });
    }
    public  void getNameStore(){
        Store cuaHang = new Store(this);
        SQLiteDatabase db = cuaHang.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsCuahang WHERE id = ? ",new String[]{idch+""});
        if (cs.moveToFirst()){
            do {
                int id = cs.getInt(0);
                String image = cs.getString(1);
                String title = cs.getString(2);
                String diachi = cs.getString(4);
                nameStore=title;
                proName1.setText(title);
                proDC.setText(diachi);
            }while (cs.moveToNext());
        }
        db.close();

    }
    public void insertGioHang(){
        try {
            Store ch= new Store(this);
            SQLiteDatabase sqLiteDatabase =ch.getWritableDatabase();
            ContentValues values= new ContentValues();
            values.put("anh",imgrs);
            values.put("tench",nameStore);
            values.put("tenmon",nameFood);
            values.put("soluong",slg.getText().toString());
            values.put("tongtien",total.getText().toString());
            sqLiteDatabase.insert("dsGioHang",null,values);
            Toast.makeText(FoodDetails.this,"Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(FoodDetails.this,""+e,Toast.LENGTH_SHORT).show();
        }
    }

}

