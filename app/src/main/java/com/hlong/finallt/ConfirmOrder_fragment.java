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

public class ConfirmOrder_fragment  extends Fragment {
    View v;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<ConfirmOrder> arrayList;
    QueryStore dsCuaHang;
    Store store;
    ConfirmOrder_RecyclerAdapter adapter;
    public ConfirmOrder_fragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.confirm_list, container, false);
        recyclerView=v.findViewById(R.id.confirm_order_recycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        dsCuaHang = new QueryStore(getActivity());
        arrayList = new ArrayList<ConfirmOrder>();
        arrayList = dsCuaHang.getAllConfirm();
        if (arrayList!=null){
            adapter = new ConfirmOrder_RecyclerAdapter(getActivity(), arrayList) {
            };
            recyclerView.setAdapter(adapter);
        }
        else {
            Toast.makeText(getActivity(),"Bạn Không có đơn hàng nào trong giỏ",Toast.LENGTH_LONG).show();
        }


        return v;
    }

}