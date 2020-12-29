package com.hlong.finallt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Store extends SQLiteOpenHelper {
    public Store(@Nullable Context context) {
        super(context, "CuaHang9", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = "CREATE TABLE IF NOT EXISTS dsCuahang(id INTEGER PRIMARY KEY AUTOINCREMENT, anh text, ten text, mota text, diachi text, mucgia text)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO dsCuahang(anh, ten, mota, diachi) VALUES('ch1','Chasu - Sinh Tố Sửa Chua Nếp Cẩm','Sữa chua uống siêu ngon','87 Cao Thắng, P.Thanh Bình, Q.Hải Châu')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO dsCuahang(anh, ten, mota, diachi) VALUES('ch2','Nhà Hàng Parsley - Steak, Pasta!','Sẽ quay lại ủng hộ','12 Cao Thắng, P.Thanh Bình, Q.Hải Châu')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO dsCuahang(anh, ten, mota, diachi) VALUES('ch3','Bánh 9 Sạch - Bánh Sầu Riêng','Crepe ngàn lớp này đã có mặt ở Q2 bánh rất thơm và dể ăn','55 Cao Thắng, P.Thanh Bình, Q.Hải Châu')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO dsCuahang(anh, ten, mota, diachi) VALUES('ch4','Chic Chic - Gà Rán Hàn Quốc','Món ăn vĩa hè ngon chất lượng - độc, lạ','81 Cao Thắng, P.Thanh Bình, Q.Hải Châu')";
        sqLiteDatabase.execSQL(sql1);

        String sql2 = "CREATE TABLE dsSanpham(id INTEGER PRIMARY KEY AUTOINCREMENT, anh text, ten text, cuahang text, gia text, diem text)";
        sqLiteDatabase.execSQL(sql2);
        sql2 = "INSERT INTO dsSanPham(anh, ten, cuahang, gia, diem) " +
                "VALUES('sp1_1','Sinh Tố Sữa Chua Thơm', '1', '24000', '')," +
                "('sp1_2','Sữa Chua nếp cẩm', '1', '24000', '')," +
                "('sp1_3','Sinh tố Sữa chua dâu', '1', '24000', '')," +
                "('sp1_4','Sinh tố Sữa chua nếp cẩm', '1', '24000', '')," +
                "('sp1_5','Sinh tố Sữa chua Kiwi', '1', '28000', '')," +
                "('sp1_6','Sinh tố Sữa chua dưa lưới', '1', '28000', '')," +
                "('sp1_7','Sinh tố Sữa chua Kiwi', '1', '28000', '')," +
                "('sp1_1','Sinh tố Sữa chua dưa lưới', '1', '28000', '')," +
                "('sp1_5','Sinh tố Sữa chua dâu', '1', '29600', '')";
        sqLiteDatabase.execSQL(sql2);
        sql2 = "INSERT INTO dsSanpham(anh, ten, cuahang, gia, diem) " +
                "VALUES('sp2_1', 'Steak thăn ngoại bò Úc','2', '163900','')," +
                "('sp2_2','Mì Ý sốt bò bằm','2', '108000','')," +
                "('sp2_3','Súp kem bí đỏ','2', '60000','')," +
                "('sp2_4','Steak thăn lưng bò Úc','2', '203000','')," +
                "('sp2_5', 'Steak thăn nội bò Úc','2', '263000','')," +
                "('sp2_6', 'Salad giấm nho Ý','2', '65000','')," +
                "('sp2_7', 'Salad cá hồi','2', '65000','')," +
                "('sp2_6', 'Salad cá hồi','2', '65000','')," +
                "('sp2_9', 'Nachos phủ bò bằm phô mai','2', '94000','')";
        sqLiteDatabase.execSQL(sql2);
        sql2 = "INSERT INTO dsSanpham(anh, ten, cuahang, gia, diem) VALUES('sp3_1','Bánh crepe sầu 6 cái','3','80000','10')," +
                "('sp3_2','Bánh sầu riêng ngàn lớp','3','135000','')," +
                "('sp3_3','Combo Bánh sầu riêng + bánh crepe','3','155000','')," +
                "('sp3_4','Bánh crepe ngập sầu 4 cái','3','55000','')," +
                "('sp3_5','Bánh sầu riêng ngàn lớp size 16','3','170000','')," +
                "('sp3_6','Bánh crepe ngập sầu 8 cái','3','105000','')," +
                "('sp3_7','Bánh sầu riêng ngàn lớp size 16','3','170000','')," +
                "('sp3_6','Bánh crepe ngập sầu 8 cái','3','105000','')," +
                "('sp3_6','Bánh crepe ngập sầu 2 cái','3','30000','')";
        sqLiteDatabase.execSQL(sql2);
        sql2 = "INSERT INTO dsSanpham(anh, ten, cuahang, gia, diem) VALUES('sp4_1','Cơm Rong Biển','4','31000','')," +
                "('sp4_2','Combo Gà BBQ 1/2 con','4','108000','')," +
                "('sp4_3','Gà Truyền thống + canh Rong Biển','4','31000','')," +
                "('sp4_4','Trà cam đào','4','17000','')," +
                "('sp4_5','Combo Gà phô Mai','4','31000','')," +
                "('sp4_6','Combo Gà phô Mai 1/2 con','4','107000','')," +
                "('sp4_7','Combo Gà phô Mai','4','31000','')," +
                "('sp4_7','Combo Gà phô Mai 1/2 con','4','107000','')," +
                "('sp4_2','Combo Gà BBQ','4','44000','')";
        sqLiteDatabase.execSQL(sql2);
        String sql3 = "CREATE TABLE dsGioHang(id INTEGER PRIMARY KEY AUTOINCREMENT, anh text, tench text,tenmon,soluong text, tongtien text)";
        sqLiteDatabase.execSQL(sql3);
        String sql4 = "CREATE TABLE dsOrder(id INTEGER PRIMARY KEY AUTOINCREMENT, anh text, tench text,tenmon,soluong text, tongtien text,diachi text,tenkh text, sdt text)";
        sqLiteDatabase.execSQL(sql4);
        String sql5 = "CREATE TABLE listHistory(id INTEGER PRIMARY KEY AUTOINCREMENT, anh text, tench text,tenmon,soluong text, tongtien text,diachi text,tenkh text, sdt text)";
        sqLiteDatabase.execSQL(sql5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}


