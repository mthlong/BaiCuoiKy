package com.hlong.finallt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class OrderActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Order_fragmentAdapter orderFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        orderFragmentAdapter = new Order_fragmentAdapter(getSupportFragmentManager());
        //add
        orderFragmentAdapter.AddFragment(new Order_GioHang_fragment(), "Giỏ Hàng");
        orderFragmentAdapter.AddFragment(new Order_History_Fragment(), "Lịch Sử");
        orderFragmentAdapter.AddFragment(new ConfirmOrder_fragment(), "Đang Giao");
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(orderFragmentAdapter);
    }



}
