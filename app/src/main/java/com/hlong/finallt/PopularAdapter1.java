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

public class PopularAdapter1 extends RecyclerView.Adapter<PopularAdapter1.PopularViewHolder> {
    private Context context;
    private List<Popular> PopularList;

    public PopularAdapter1(Context context, List<Popular> popularList) {
        this.context = context;
        PopularList = popularList;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_recycler_items, parent, false);
        return new PopularAdapter1.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, final int position) {
        final Popular sp = PopularList.get(position);
        String image = sp.getImageUrl();
        int resId = ((Activity)context).getResources().getIdentifier(image, "drawable", ((Activity)context).getPackageName());
        holder.mImg.setImageResource(resId);
        holder.mTitle.setText(sp.getName());
        holder.mGia.setText(sp.getPrice());
        holder.mDiem.setText(sp.getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetails.class);
                intent.putExtra("id", PopularList.get(position).getId());
                intent.putExtra("idch", PopularList.get(position).getStore());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PopularList.size();
    }

    public static final class PopularViewHolder extends RecyclerView.ViewHolder{
        ImageView mImg;
        TextView mTitle,mGia,mDiem;


        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.popular_image);
            mTitle = itemView.findViewById(R.id.popular_name);
            mGia = itemView.findViewById(R.id.price);
            mDiem = itemView.findViewById(R.id.rating);
        }
    }
}

