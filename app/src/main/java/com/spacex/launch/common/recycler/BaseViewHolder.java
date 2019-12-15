package com.spacex.launch.common.recycler;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Base class for the Recyclerview's ViewHolder.
 *
 * @author Hari Hara Sudhan.N
 */
public abstract class BaseViewHolder<T, L extends OnItemClickListener>
        extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Bind data to the item and set listener if needed.
     *
     * @param item      object, associated with the item.
     * @param listener  listener a listener {@link OnItemClickListener}
     *                   which has to b set at the item (if not `null`).
     */
    public abstract void onBind(T item, @Nullable L listener);
}
