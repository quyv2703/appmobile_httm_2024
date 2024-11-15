package com.ltq27.baotrimaylanh;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WrapContentLinearLayoutManager extends LinearLayoutManager {

    public WrapContentLinearLayoutManager(Context context) {
        super(context);
    }

    public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            if (view != null) {
                measureChild(view, widthSpec, heightSpec);
                height += view.getMeasuredHeight();
                recycler.recycleView(view);
            }
        }
        height = Math.min(height, View.MeasureSpec.getSize(heightSpec));
        setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height);
    }
}
