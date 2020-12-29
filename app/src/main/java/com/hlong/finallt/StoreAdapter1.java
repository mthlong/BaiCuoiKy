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

import com.hlong.finallt.Fragments.FoodStore;

import java.util.List;

public class StoreAdapter1 extends RecyclerView.Adapter<StoreAdapter1.StoreViewHolder> {
    private Context context;
    private List<FoodStore> cuaHangFoodList;

    public StoreAdapter1(Context context, List<FoodStore> cuaHangFoodList) {
        this.context = context;
        this.cuaHangFoodList = cuaHangFoodList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_sale_row_item, parent, false);
        return new StoreAdapter1.StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, final int position) {
        FoodStore ch = cuaHangFoodList.get(position);
        String image = ch.getAnh();
        int resId = ((Activity) context).getResources().getIdentifier(image, "drawable", ((Activity) context).getPackageName());
        holder.mImage.setImageResource(resId);
        holder.mTen.setText(cuaHangFoodList.get(position).getTen());
        holder.mDiaChi.setText(cuaHangFoodList.get(position).getDiachi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InforStoreActivity.class);
                intent.putExtra("id", cuaHangFoodList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cuaHangFoodList.size();
    }

    public static final class StoreViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mTen,mDiaChi;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.food_image);
            mTen = itemView.findViewById(R.id.food_name);
            mDiaChi = itemView.findViewById(R.id.edt_location);
        }
    }
}

