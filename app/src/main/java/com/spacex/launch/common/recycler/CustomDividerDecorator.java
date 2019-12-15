package com.spacex.launch.common.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.spacex.launch.R;

/**
 * Custom Item decorator to show a divider line between the items in recyclerview.
 *
 * @author Hari Hara Sudhan.N
 */
public class CustomDividerDecorator extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int left = 0, top = 0, right = 0, bottom = 0;

    public CustomDividerDecorator(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);
    }

    /**
     * Sets all the margins.
     *
     * @param left    left margin
     * @param top     top margin
     * @param right   right margin
     * @param bottom  bottom margin
     */
    public void setMargins(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c,
                           @NonNull RecyclerView parent,
                           @NonNull RecyclerView.State state) {

        int left = parent.getPaddingLeft() + this.left;
        int right = parent.getWidth() - parent.getPaddingRight() - this.right;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin - this.top;
            int bottom = top + mDivider.getIntrinsicHeight() - this.bottom;

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
