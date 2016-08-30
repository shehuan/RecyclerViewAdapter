package com.othershe.recyclerviewadapter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.othershe.baseadapter.OnItemClickListeners;
import com.othershe.baseadapter.OnLoadMoreListener;
import com.othershe.baseadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RefreshAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private boolean isFailed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //初始化adapter
        mAdapter = new RefreshAdapter(this, null, true);

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

        //设置item点击事件监听
        mAdapter.setOnItemClickListener(new OnItemClickListeners<String>() {

            @Override
            public void onItemClick(ViewHolder viewHolder, String data, int position) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
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
                for (int i = 0; i < 10; i++) {
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

                if (mAdapter.getItemCount() > 19 && isFailed) {
                    isFailed = false;
                    //加载失败，更新footer view提示
                    mAdapter.setLoadFailedView(R.layout.load_failed_layout);
                } else if (mAdapter.getItemCount() > 23) {
                    //加载完成，更新footer view提示
                    mAdapter.setLoadEndView(R.layout.load_end_layout);
                } else {
                    final List<String> data = new ArrayList<>();
                    for (int i = 0; i < 2; i++) {
                        data.add("item--" + (mAdapter.getItemCount() + i - 1));
                    }
                    //刷新数据
                    mAdapter.setLoadMoreData(data);
                }
            }
        }, 2000);
    }
}
