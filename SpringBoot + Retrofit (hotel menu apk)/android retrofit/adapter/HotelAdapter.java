package com.example.hotelpracticetodelete;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelpracticetodelete.databinding.EachRvBinding;
import com.example.hotelpracticetodelete.retrofit.Hotel_Menu_Model;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> {

    List<Hotel_Menu_Model> menuList;

    public HotelAdapter(List<Hotel_Menu_Model> menuList){
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Hotel_Menu_Model model = menuList.get(position);
        String convPrice = ""+model.getDish_price();
        holder.dishTv.setText(model.getDish());
        holder.dishPriceTv.setText(convPrice);
    }

    public Hotel_Menu_Model getPosition(int position){
        return menuList.get(position);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dishTv,dishPriceTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dishTv = itemView.findViewById(R.id.dishTv);
            dishPriceTv = itemView.findViewById(R.id.dishPriceTv);

        }
    }
}
