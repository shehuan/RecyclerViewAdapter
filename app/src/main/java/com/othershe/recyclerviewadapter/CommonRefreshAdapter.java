package com.othershe.recyclerviewadapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.othershe.baseadapter.ViewHolder;

import java.util.List;

/**
 * Author: Othershe
 * Time: 2016/8/29 15:40
 */
public class CommonRefreshAdapter extends CommonBaseAdapter<String> {

    public CommonRefreshAdapter(Context context, List<String> datas, boolean isLoadMore) {
        super(context, datas, isLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, final String data, int position) {
        holder.setText(R.id.item_title, data);
        holder.setOnClickListener(R.id.item_btn, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "我是" + data + "的button", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected int getItemLayoutId() {
        return R.layout.item_layout;
    }
}
