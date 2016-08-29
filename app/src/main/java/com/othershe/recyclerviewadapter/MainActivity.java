package com.othershe.recyclerviewadapter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.othershe.recyclerviewadapter.adapter.OnItemClickListener;
import com.othershe.recyclerviewadapter.adapter.OnLoadMoreListener;
import com.othershe.recyclerviewadapter.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RefreshAdapter mAdapter;
    private List<String> mDatas;

    private RecyclerView mRecyclerView;

    private boolean isFailed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        initData();

        mAdapter = new RefreshAdapter(this, mDatas, true);
        mAdapter.setLoadingView(R.layout.loading_layout);
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener<String>() {

            @Override
            public void onItemClick(ViewHolder viewHolder, String data, int position) {
                Log.e("tag", data);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mDatas.add("item--" + i);
        }
    }

    private void loadMore() {
        if (mAdapter.getItemCount() == 15 && isFailed) {
            isFailed = false;
            mAdapter.setLoadFailedView(R.layout.load_failed_layout);
        } else if (mAdapter.getItemCount() == 19) {
            mAdapter.setLoadEndView(R.layout.load_end_layout);
        } else {
            final List<String> data = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                data.add(System.currentTimeMillis() + "");
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.setLoadMoreData(data);
                    Log.e("tag", "add data");
                }
            }, 2000);
        }
    }
}
