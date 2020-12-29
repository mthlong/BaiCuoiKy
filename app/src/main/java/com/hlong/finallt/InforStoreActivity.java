package com.hlong.finallt;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InforStoreActivity extends AppCompatActivity {


    ImageView img, back;
    TextView proName, proName1, proDC;
    Store cuaHang;
    int id;
    RecyclerView recyclerView;
    PopularAdapter1 adapter;
    ArrayList<Popular> arrayList;
    TruyVan truyVan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_store);

        List<Popular> popularFoods = new ArrayList<>();
        List<Recommended> saleFoods = new ArrayList<>();
        List<Allmenu> allFoods = new ArrayList<>();

        Intent i = getIntent();
        id = i.getIntExtra("id", 0);

        cuaHang = new Store(this);
        img=findViewById(R.id.imageStore);
        proName = findViewById(R.id.textView);
        proDC = findViewById(R.id.textViewAddress);
        SQLiteDatabase db = cuaHang.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM dsCuahang WHERE id = ? ",new String[]{id+""});
        if (cs.moveToFirst()){
            do {
                int id = cs.getInt(0);
                String image = cs.getString(1);
                String title = cs.getString(2);
                String des = cs.getString(3);
                String diachi = cs.getString(4);
                String mucgia = cs.getString(5);

                proName.setText(title);
//                    proName1.setText(title);
                int resId = getResources().getIdentifier(image, "drawable", getPackageName());
                img.setImageResource(resId);
                proDC.setText(diachi);
            }while (cs.moveToNext());
        }
        db.close();

        truyVan = new TruyVan(this);
        arrayList = truyVan.getAll();
        recyclerView = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PopularAdapter1(InforStoreActivity.this, arrayList);
        recyclerView.setAdapter(adapter);

        back = findViewById(R.id.imageback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public class TruyVan{
        Store cuaHang;
        public TruyVan(Context context){
            cuaHang = new Store(context);
        }
        public ArrayList<Popular> getAll(){
            ArrayList<Popular> ds = new ArrayList<>();
            SQLiteDatabase db = cuaHang.getReadableDatabase();
            Cursor cs = db.rawQuery("SELECT * FROM dsSanpham WHERE cuahang = ?", new String[]{id+""});
            cs.moveToFirst();
            while (!cs.isAfterLast()){
                int id = cs.getInt(0);
                String image = cs.getString(1);
                String title = cs.getString(2);
                String cuahang = cs.getString(3);
                String gia = cs.getString(4);
                String diem = cs.getString(5);
                Popular a = new Popular(id, image, title, cuahang, gia, diem);
                ds.add(a);
                cs.moveToNext();
            }
            cs.close();
            return ds;
        }
    }


}

