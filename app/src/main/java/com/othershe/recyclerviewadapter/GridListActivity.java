package com.othershe.recyclerviewadapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class GridListActivity extends AppCompatActivity {

    private GridListAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private boolean isFailed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //初始化adapter
        mAdapter = new GridListAdapter(this, null, true);

        //初始化EmptyView
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.setEmptyView(emptyView);

        TextView t1 = new TextView(this);
        t1.setText("我是header-1");
        mAdapter.addHeaderView(t1);
        TextView t2 = new TextView(this);
        t2.setText("我是header-2");
        mAdapter.addHeaderView(t2);

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

        //设置item点击事件监听
        mAdapter.setOnItemClickListener(new OnItemClickListener<String>() {

            @Override
            public void onItemClick(ViewHolder viewHolder, String data, int position) {
                Toast.makeText(GridListActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

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
        }, 2000);
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
                        data.add("item--" + (mAdapter.getDataCount() + i));
                    }
                    //刷新数据
                    mAdapter.setLoadMoreData(data);
                }
            }
        }, 2000);
    }
}
