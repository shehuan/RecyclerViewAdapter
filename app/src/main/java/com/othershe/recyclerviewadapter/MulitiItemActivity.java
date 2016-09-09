package com.othershe.recyclerviewadapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.othershe.baseadapter.interfaces.OnMultiItemClickListeners;
import com.othershe.baseadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MulitiItemActivity extends AppCompatActivity {

    private MultiRefreshAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private boolean isFailed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //初始化adapter
        mAdapter = new MultiRefreshAdapter(this, null, true);

        //初始化EmptyView
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.setEmptyView(emptyView);

        //初始化 开始加载更多的loading View
        mAdapter.setLoadingView(R.layout.load_loading_layout);

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
                Toast.makeText(MulitiItemActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mAdapter);


        //延时3s刷新列表
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    data.add("item--" + i);
                }
                //刷新数据
                mAdapter.setNewData(data);
            }
        }, 3000);
    }


    private void loadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mAdapter.getItemCount() > 15 && isFailed) {
                    isFailed = false;
                    //加载失败，更新footer view提示
                    mAdapter.setLoadFailedView(R.layout.load_failed_layout);
                } else if (mAdapter.getItemCount() > 17) {
                    //加载完成，更新footer view提示
                    mAdapter.setLoadEndView(R.layout.load_end_layout);
                } else {
                    final List<String> data = new ArrayList<>();
                    for (int i = 0; i < 12; i++) {
                        data.add("item--" + (mAdapter.getItemCount() + i - 1));
                    }
                    //刷新数据
                    mAdapter.setLoadMoreData(data);
                }
            }
        }, 2000);
    }
}
