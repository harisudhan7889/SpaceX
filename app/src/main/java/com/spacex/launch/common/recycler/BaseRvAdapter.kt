package com.spacex.launch.common.recycler

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Base RecyclerView adapter.
 * Handles basic logic such as adding/removing items,
 * setting listener, binding ViewHolders.
 * Extend the adapter for appropriate use case.
 *
 * @param <T>  type of objects, which will be used in the adapter's dataset
 * @param <L>  click listener {@link OnItemClickListener}
 * @param <VH> ViewHolder {@link BaseViewHolder}
 *
 * @author Hari Hara Sudhan.N
 */
abstract class BaseRvAdapter<T, L : OnItemClickListener<T>, VH : BaseViewHolder<T, L>>(val context: Context)
    : androidx.recyclerview.widget.RecyclerView.Adapter<VH>() {

    private var itemClickListner: L? = null
    private val items: ArrayList<T> by lazy {
        ArrayList<T>()
    }
    private val layoutInflator = LayoutInflater.from(context)
    /**
     * To be implemented in as specific adapter
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the itemView to reflect the item at the given
     * position.
     *
     * @param viewHolder   The ViewHolder which should be updated to represent the contents of the
     *                     item at the given position in the data set.
     * @param position     The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(viewHolder: VH, position: Int) {
        val item = items[position]
        viewHolder.onBind(item, itemClickListner)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Set recyclerview's item click listener
     *
     * @param itemClickListner item click listener
     */
    fun setItemClickListener(itemClickListner: L?) {
        this.itemClickListner = itemClickListner
    }

    /**
     * Sets items to the adapter and notifies that data set has been changed.
     *
     * @param items items to set to the adapter
     * @throws IllegalArgumentException in case of setting `null` items
     */
    fun setItems(items: ArrayList<T>?) {
        if (items == null) {
            throw IllegalArgumentException("Cannot set null item to the recyclerview adapter")
        }
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * Returns an item from the data set at a certain position.
     *
     * @return A item in this adapter.
     * @throws IndexOutOfBoundsException in case of position is larger than the item count
     */
    fun getItem(position: Int): T {
        if (position >= itemCount) {
            throw IndexOutOfBoundsException("$position is larger than total size $itemCount")
        }
        return items[position]
    }

    /**
     * Adds item to the end of the data set.
     * Notifies that item has been inserted.
     *
     * @param item item which has to be added to the adapter.
     */
    fun addItem(item: T) {
        if (item == null) {
            throw IllegalArgumentException("Cannot add null item to the recyclerview adapter")
        }
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    /**
     * Adds list of items to the end of the adapter's data set.
     * Notifies that item has been inserted.
     *
     * @param items items which has to be added to the adapter.
     */
    fun addAllItems(items: ArrayList<T>?) {
        if (items == null) {
            throw IllegalArgumentException("Cannot add null items to the recyclerview adapter")
        }
        this.items.addAll(items)
        notifyItemRangeInserted(this.items.size - items.size, items.size)
    }

    /**
     * Removes an item from the adapter.
     * Notifies that item has been removed.
     *
     * @param item to be removed
     */
    fun removeItem(item: T) {
        val position = items.indexOf(item)
        if (position > -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * Clears all the items in the adapter.
     */
    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    /**
     * Inflates a view.
     *
     * @param layout       layout to me inflater
     * @param parent       container where to inflate
     * @param attachToRoot whether to attach to root or not
     * @return inflated View
     */
    protected fun inflate(@LayoutRes layout: Int, parent: ViewGroup, attachToRoot: Boolean): View {
        return layoutInflator.inflate(layout, parent, attachToRoot)
    }

    /**
     * Inflates a view.
     *
     * @param layout layout to me inflater
     * @param parent container where to inflate
     * @return inflated View
     */
    protected fun inflate(@LayoutRes layout: Int, parent: ViewGroup): View {
        return layoutInflator.inflate(layout, parent, false)
    }
}