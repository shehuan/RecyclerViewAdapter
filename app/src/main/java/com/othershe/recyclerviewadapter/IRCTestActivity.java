package com.othershe.recyclerviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class IRCTestActivity extends AppCompatActivity {

    private RecyclerView rvList;

    private IRCTestAdapter adapter;

    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irctest);

        rvList = findViewById(R.id.rv_list);

        initData();
        adapter = new IRCTestAdapter(this, datas, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            datas.add("item-" + System.currentTimeMillis());
        }
    }


    /**
     * 添加单个
     *
     * @param view
     */
    public void insertSingle(View view) {
        adapter.insert("item-" + System.currentTimeMillis());
        rvList.scrollToPosition(adapter.getDataCount() - 1);
    }

    /**
     * 添加多个
     *
     * @param view
     */
    public void insertMulti(View view) {
        List<String> data = new ArrayList<>();
        data.add("item-" + System.currentTimeMillis());
        data.add("item-" + System.currentTimeMillis());
        data.add("item-" + System.currentTimeMillis());
        adapter.insert(data);
        rvList.scrollToPosition(adapter.getDataCount() - 1);
    }

    /**
     * 删除
     *
     * @param view
     */
    public void remove(View view) {
        adapter.remove(22);
        rvList.scrollToPosition(22);
    }

    /**
     * 更新
     *
     * @param view
     */
    public void change(View view) {
        adapter.getAllData().remove(adapter.getDataCount() - 1);
        adapter.getAllData().add("item-" + System.currentTimeMillis());
        adapter.change(adapter.getDataCount() - 1);
        rvList.scrollToPosition(adapter.getDataCount() - 1);
    }
}
