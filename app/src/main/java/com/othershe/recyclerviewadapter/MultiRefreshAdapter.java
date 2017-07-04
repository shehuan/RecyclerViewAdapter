package com.othershe.recyclerviewadapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.othershe.baseadapter.base.MultiBaseAdapter;
import com.othershe.baseadapter.ViewHolder;

import java.util.List;

/**
 * Author: Othershe
 * Time: 2016/9/9 17:24
 */
public class MultiRefreshAdapter extends MultiBaseAdapter<String> {

    public MultiRefreshAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, final String data, int position, int viewType) {
        if (viewType == 0) {
            holder.setText(R.id.item_title, data);
            holder.setOnClickListener(R.id.item_btn, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "我是" + data + "的button", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            holder.setText(R.id.item_title1, data);
        }
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.item_layout;
        }
        return R.layout.item_layout1;
    }

    @Override
    protected int getViewType(int position, String data) {
        if (position % 2 == 0) {
            return 0;
        }
        return 1;
    }

}
