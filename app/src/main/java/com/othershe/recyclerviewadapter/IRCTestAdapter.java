package com.othershe.recyclerviewadapter;

import android.content.Context;
import android.util.Log;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

/**
 * Author: shehuan
 * Date: 2018/9/11 9:06
 * Description:
 */
public class IRCTestAdapter extends CommonBaseAdapter<String> {
    public IRCTestAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, String data, int position) {
        holder.setText(R.id.item_title1, data);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_layout1;
    }
}
