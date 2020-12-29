package com.hlong.finallt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class Order_GioHang_RecyclerAdapter extends RecyclerView.Adapter<Order_GioHang_RecyclerAdapter.Order_GioHang_ViewHolder> {
    Context context;
    List<OrderGioHang> order_gioHangs;

    public Order_GioHang_RecyclerAdapter(Context context, List<OrderGioHang> order_gioHangs) {
        this.context = context;
        this.order_gioHangs = order_gioHangs;
    }


    @NonNull
    @Override
    public Order_GioHang_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_gio_hang_item, parent, false);
        return new Order_GioHang_RecyclerAdapter.Order_GioHang_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order_GioHang_ViewHolder holder, final int position) {
        OrderGioHang ch = order_gioHangs.get(position);
        final String image = ch.getImageFood();
        int resId = ((Activity) context).getResources().getIdentifier(image, "drawable", ((Activity) context).getPackageName());
        holder.imgFood.setImageResource(resId);
        holder.txtStore.setText(order_gioHangs.get(position).getRestaurant_name());
        holder.txtFoodName.setText(order_gioHangs.get(position).getFood_name());
        holder.txtIteam.setText(order_gioHangs.get(position).getIteams());
        holder.txtGia.setText(order_gioHangs.get(position).getGia());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ConfirmOrderActivity.class);
                intent.putExtra("idgh", order_gioHangs.get(position).getId());
                intent.putExtra("namech", order_gioHangs.get(position).getRestaurant_name());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return order_gioHangs.size();
    }

    public static final class Order_GioHang_ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView txtStore, txtGia, txtFoodName, txtIteam;

        public Order_GioHang_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.food_image);
            txtStore = itemView.findViewById(R.id.restaurant_name);
            txtFoodName = itemView.findViewById(R.id.food_name);
            txtGia = itemView.findViewById(R.id.gia);
            txtIteam = itemView.findViewById(R.id.iteam);
        }
    }
}
