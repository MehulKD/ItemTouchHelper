package com.hanks.swipedismissbehavior;

import android.graphics.Canvas;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> dateList = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 100; i++) {
            dateList.add("simple text item : " + i);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

        final ItemTouchHelper.Callback simpleItemTouchCallback = new ItemTouchHelper.Callback() {
            @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                if (viewHolder.getAdapterPosition() < target.getAdapterPosition()) {
                    for (int i = viewHolder.getAdapterPosition(); i < target.getAdapterPosition(); i++) {
                        Collections.swap(dateList, i, i + 1);
                    }
                } else {
                    for (int i = viewHolder.getAdapterPosition(); i > target.getAdapterPosition(); i--) {
                        Collections.swap(dateList, i, i - 1);
                    }
                }
                myAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override public void onSwiped(final RecyclerView.ViewHolder viewHolder, final int swipeDir) {
                myAdapter.notifyItemRemoved(swipeDir);
                dateList.remove(swipeDir);
            }

            @Override public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                //                View itemView = viewHolder.itemView;
                //                ImageView delete_image = (ImageView) itemView.findViewById(R.id.delete_image);
                //                delete_image.setY(itemView.getTop());
                //                if (isCurrentlyActive) {
                //                    delete_image.setVisibility(View.VISIBLE);
                //                } else {
                //                    delete_image.setVisibility(View.GONE);
                //                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            //
            @Override public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recylerview, parent, false);
            return new MyViewHolder(view);
        }

        @Override public void onBindViewHolder(MyViewHolder holder, int position) {
            //            holder.text.setText(dateList.get(position));
        }

        @Override public int getItemCount() {
            return dateList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }

    }
}
