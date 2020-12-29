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

public class Order_History_Fragment extends Fragment {
    View v;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<Order_History> arrayList;
    QueryStore dsCuaHang;
    Order_History_RecyclerViewAdapter adapter;

    public Order_History_Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.oder_history_list, container, false);
        recyclerView = v.findViewById(R.id.order_history_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dsCuaHang = new QueryStore(getActivity());
        arrayList = new ArrayList<Order_History>();
        arrayList = dsCuaHang.getAll3();
        if (arrayList != null) {
            adapter = new Order_History_RecyclerViewAdapter(getActivity(), arrayList) {
            };
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), "Bạn Không có đơn hàng nào trong giỏ", Toast.LENGTH_LONG).show();
        }

        return v;
    }
}
