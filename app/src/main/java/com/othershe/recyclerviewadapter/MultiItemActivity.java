package com.othershe.recyclerviewadapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.othershe.baseadapter.interfaces.OnItemChildClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.othershe.baseadapter.interfaces.OnMultiItemClickListeners;
import com.othershe.baseadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MultiItemActivity extends AppCompatActivity {

    private MultiRefreshAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private boolean isFailed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("item--" + i);
        }
        //初始化adapter
        mAdapter = new MultiRefreshAdapter(this, data, true);

        //初始化 开始加载更多的loading View
        mAdapter.setLoadingView(R.layout.load_loading_layout);
        //加载失败，更新footer view提示
        mAdapter.setLoadFailedView(R.layout.load_failed_layout);
        //加载完成，更新footer view提示
        mAdapter.setLoadEndView(R.layout.load_end_layout);

        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });

        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<String>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, String data, int position, int viewType) {
                Toast.makeText(MultiItemActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

        //item子view点击事件
        mAdapter.setOnItemChildClickListener(R.id.item_btn, new OnItemChildClickListener<String>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, String data, int position) {
                Toast.makeText(MultiItemActivity.this, "我是" + data + "的button", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mAdapter);


//        List<String> data = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            data.add("item--" + i);
//        }
//        //刷新数据
//        mAdapter.setNewData(data);
    }

    private void loadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mAdapter.getItemCount() >= 10 && isFailed) {
                    isFailed = false;
                    mAdapter.loadFailed();
                } else if (mAdapter.getItemCount() >= 20) {
                    mAdapter.loadEnd();
                } else {
                    final List<String> data = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        data.add("item--" + (mAdapter.getItemCount() + i - 1));
                    }
                    //刷新数据
                    mAdapter.setLoadMoreData(data);
                }
            }
        }, 2000);
    }
}
