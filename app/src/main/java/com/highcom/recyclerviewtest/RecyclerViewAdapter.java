package com.highcom.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<String> mData;
    private Listener mListener;

    // アイテムタップ用interface
    public interface Listener {
        void onRecyclerClicked(View v, int position);
    }

    public RecyclerViewAdapter(Context context, ArrayList<String> data, Listener listener) {
        mInflater = LayoutInflater.from(context);
        mData = data;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRecyclerClicked(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        else {
            return mData.size();
        }
    }

    // ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.text);
        }
    }
}
