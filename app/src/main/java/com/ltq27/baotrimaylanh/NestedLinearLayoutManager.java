package com.ltq27.baotrimaylanh;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NestedLinearLayoutManager extends LinearLayoutManager {

    public NestedLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false; // Ngăn chặn việc cuộn trong RecyclerView con
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            if (view != null) {
                measureChild(view, widthSpec, heightSpec);
                height += view.getMeasuredHeight();
            }
        }
        setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height);
    }
}
