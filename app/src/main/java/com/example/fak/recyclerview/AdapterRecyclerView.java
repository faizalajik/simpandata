package com.example.fak.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.DataViewHolder> {
    private ArrayList<DataReminder> dataList;
    private Context context;


    public AdapterRecyclerView(Context context) {
        this.context = context;

    }
    public AdapterRecyclerView(Context context, ArrayList<DataReminder> dataList){
        this.dataList = dataList;
    }

    public DataViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v;
        v = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new DataViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        holder.tvTanggal.setText(dataList.get(position).getTanggal());
        holder.tvTotal.setText(dataList.get(position).getTotal());
        holder.tvWaktu.setText(dataList.get(position).getWaktu());
        //holder.tvNote.setText(dataList.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotal, tvTanggal,tvWaktu,tvNote;
        public DataViewHolder(View itemView) {
            super(itemView);
            tvTotal = (TextView) itemView.findViewById(R.id.tv_total);
            tvTanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);
            tvWaktu = (TextView) itemView.findViewById(R.id.tv_waktu);
        }
    }
}
