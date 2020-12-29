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

public class ConfirmOrder_RecyclerAdapter extends RecyclerView.Adapter<ConfirmOrder_RecyclerAdapter.ConfirmOrder_ViewHolder> {
    Context context;
    List<ConfirmOrder> confirmOrders;

    public ConfirmOrder_RecyclerAdapter(Context context, List<ConfirmOrder> confirmOrders) {
        this.context = context;
        this.confirmOrders = confirmOrders;
    }

    @NonNull
    @Override
    public ConfirmOrder_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.confit_item, parent, false);
        return new ConfirmOrder_RecyclerAdapter.ConfirmOrder_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmOrder_ViewHolder holder, final int position) {
        ConfirmOrder ch = confirmOrders.get(position);
        final String image = ch.getImageFood();
        int resId = ((Activity) context).getResources().getIdentifier(image, "drawable", ((Activity) context).getPackageName());
        holder.imgFood.setImageResource(resId);
        holder.txtStore.setText(confirmOrders.get(position).getRestaurant_name());
        holder.txtFoodName.setText(confirmOrders.get(position).getFood_name());
        holder.txtIteam.setText(confirmOrders.get(position).getIteams());
        holder.txtGia.setText(confirmOrders.get(position).getGia());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ConfirmGetIteamActivity.class);
                intent.putExtra("idgorder",confirmOrders.get(position).getId());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return confirmOrders.size();
    }

    public static final class ConfirmOrder_ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFood;
        TextView txtStore,  txtGia, txtFoodName, txtIteam;

        public ConfirmOrder_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.food_image);
            txtStore = itemView.findViewById(R.id.restaurant_name);
            txtFoodName = itemView.findViewById(R.id.food_name);
            txtGia = itemView.findViewById(R.id.gia);
            txtIteam = itemView.findViewById(R.id.iteam);
        }
    }
}

