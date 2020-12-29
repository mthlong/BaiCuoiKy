package com.hlong.finallt;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Order_History_RecyclerViewAdapter extends RecyclerView.Adapter<Order_History_RecyclerViewAdapter.OrderHistory_ViewHolder> {
    Context context;
    List<Order_History> order_histories;

    public Order_History_RecyclerViewAdapter(Context context, List<Order_History> order_histories) {
        this.context = context;
        this.order_histories = order_histories;
    }

    @NonNull
    @Override
    public OrderHistory_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_history_iteam, parent, false);
        return new Order_History_RecyclerViewAdapter.OrderHistory_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistory_ViewHolder holder, final int position) {
        Order_History ch = order_histories.get(position);
        final String image = ch.getImageFood();
        int resId = ((Activity) context).getResources().getIdentifier(image, "drawable", ((Activity) context).getPackageName());
        holder.imgFood.setImageResource(resId);
        holder.txtStore.setText(order_histories.get(position).getRestaurant_name());
        holder.txtFoodName.setText(order_histories.get(position).getFood_name());
        holder.txtIteam.setText(order_histories.get(position).getIteams());
        holder.txtGia.setText(order_histories.get(position).getGia());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return order_histories.size();
    }

    public static final class OrderHistory_ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFood;
        TextView txtStore,  txtGia, txtFoodName, txtIteam;

        public OrderHistory_ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFood = itemView.findViewById(R.id.food_image);
            txtStore = itemView.findViewById(R.id.restaurant_name);
            txtFoodName = itemView.findViewById(R.id.food_name);
            txtGia = itemView.findViewById(R.id.gia);
            txtIteam = itemView.findViewById(R.id.iteam);
        }
    }
}
