package com.hlong.finallt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Order_GioHang_fragment extends Fragment {
    View v;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<OrderGioHang> arrayList;
    QueryStore dsCuaHang;
    Order_GioHang_RecyclerAdapter adapter;
    public Order_GioHang_fragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.order_giohang_list, container, false);
        recyclerView=v.findViewById(R.id.order_giohang_recycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        dsCuaHang = new QueryStore(getActivity());
        arrayList = new ArrayList<OrderGioHang>();
        arrayList = dsCuaHang.getAll0();
        if (arrayList!=null){
            adapter = new Order_GioHang_RecyclerAdapter(getActivity(), arrayList) {
            };
            recyclerView.setAdapter(adapter);
        }
        else {
            Toast.makeText(getActivity(),"Bạn Không có đơn hàng nào trong giỏ",Toast.LENGTH_LONG).show();
        }

        return v;
    }
}

