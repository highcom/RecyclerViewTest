package com.highcom.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.Listener {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ItemTouchHelper mHelper;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        data = new ArrayList<>();
        data.add("うなすけ");
        data.add("やぎにい");
        data.add("アドベントカレンダー");
        data.add("やっていき！");

        mRecyclerViewAdapter = new RecyclerViewAdapter(this, data, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        // セル間に区切り線を実装する
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        // ドラックアンドドロップの操作を実装する
        ItemTouchHelper itemDecor = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    final int fromPos = viewHolder.getAdapterPosition();
                    final int toPos = target.getAdapterPosition();
                    mRecyclerViewAdapter.notifyItemMoved(fromPos, toPos);
                    return true;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    final int fromPos = viewHolder.getAdapterPosition();
                    data.remove(fromPos);
                    mRecyclerViewAdapter.notifyItemRemoved(fromPos);
                }
            });
        itemDecor.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onRecyclerClicked(View v, int position) {
        TextView textView = (TextView)v.findViewById(R.id.text);
        Toast.makeText(this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
