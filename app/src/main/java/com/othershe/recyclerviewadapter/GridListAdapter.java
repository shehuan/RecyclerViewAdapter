package com.othershe.recyclerviewadapter;

import android.content.Context;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class GridListAdapter extends CommonBaseAdapter<String> {

    public GridListAdapter(Context context, List<String> datas, boolean isLoadMore) {
        super(context, datas, isLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, final String data, int position) {
        holder.setText(R.id.item_title, data);
    }


    @Override
    protected int getItemLayoutId() {
        return R.layout.item_layout2;
    }
}
