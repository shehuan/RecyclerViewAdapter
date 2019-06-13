package com.othershe.baseadapter.interfaces;

import com.othershe.baseadapter.ViewHolder;

/**
 * Author: SheHuan
 * Time: 2019/6/13 16:48
 */
public interface OnItemLongClickListener<T> {
    void onItemLongClick(ViewHolder viewHolder, T data, int position);
}
