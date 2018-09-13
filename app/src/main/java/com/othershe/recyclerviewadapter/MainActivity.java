package com.othershe.recyclerviewadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void start(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    public void button0(View view) {
        start(CommonItemActivity.class);
    }

    public void button1(View view) {
        start(MultiItemActivity.class);
    }

    public void button2(View view) {
        start(InitLoadActivity.class);
    }

    public void button3(View view) {
        start(GridListActivity.class);
    }

    public void button4(View view) {
        start(IRCTestActivity.class);
    }
}
