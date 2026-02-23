package com.example.expenseapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private final ArrayList<Transaction> transactionList;

    public TransactionAdapter(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);

        holder.tvDescription.setText(transaction.getDescription());
        holder.tvAmount.setText(String.valueOf(transaction.getAmount()));
        holder.tvDate.setText(String.valueOf(transaction.getDate()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription, tvAmount, tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.description);
            tvAmount = itemView.findViewById(R.id.value);
            tvDate =itemView.findViewById(R.id.date);
        }
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}