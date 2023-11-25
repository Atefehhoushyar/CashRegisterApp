package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryListRecyclerAdapter extends RecyclerView.Adapter<HistoryListRecyclerAdapter.HistoryListViewHolder>{

    interface HistoryClickListener {
        void HistoryClicked(int i);
    }
    ArrayList<HistoryList> History_List;
    Context context;
    HistoryClickListener listener;
    public HistoryListRecyclerAdapter(ArrayList<HistoryList> history_List, Context context) {
        History_List = history_List;
        this.context = context;
    }

        class HistoryListViewHolder extends RecyclerView.ViewHolder{
            public HistoryListViewHolder(@NonNull View itemView) {
                super(itemView);

            }
            }
    @NonNull
    @Override
    public HistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_recycle_adapter,parent,false);
        return new HistoryListViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull HistoryListViewHolder holder, int position) {
        TextView ProductName = holder.itemView.findViewById(R.id.row_ProductName);
        TextView ProductQuantity = holder.itemView.findViewById(R.id.row_Quantity);
        TextView TotalPrice = holder.itemView.findViewById(R.id.row_totalPrice);

        ProductName.setText(History_List.get(position).getProductName());
        ProductQuantity.setText(String.valueOf(History_List.get(position).getPurchesNumber()));
        TotalPrice.setText(String.valueOf(History_List.get(position).getTotalCost()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.HistoryClicked(holder.getAdapterPosition());
                return ;
            }
        });

    }

    @Override
    public int getItemCount() {
        return History_List.size();
    }


}
