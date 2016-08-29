package com.othershe.recyclerviewadapter;

import android.content.Context;

import com.othershe.recyclerviewadapter.adapter.BaseAdapter;
import com.othershe.recyclerviewadapter.adapter.ViewHolder;

import java.util.List;

/**
 * Author: Othershe
 * Time: 2016/8/29 15:40
 */
public class RefreshAdapter extends BaseAdapter<String> {

    public RefreshAdapter(Context context, List<String> datas, boolean isLoadMore) {
        super(context, datas, isLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, String data) {
        holder.setText(R.id.item_title, data);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_layout;
    }
}
