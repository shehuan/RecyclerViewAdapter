# RecyclerViewAdapter

##简介
一个支持RecyclerView加载更多的BaseAdapter

[原理、效果]()

[demo下载](http://fir.im/k7dl)

##导入
```
dependencies {
    compile 'com.othershe:BaseAdapter:1.0.2'
}
```

##用法

####1、创建一个Adapter
```
public class RefreshAdapter extends BaseAdapter<T> {

    public RefreshAdapter(Context context, List<T> datas, boolean isLoadMore) {
        super(context, datas, isLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, T data) {
        //init item
    }

    @Override
    protected int getItemLayoutId() {
        return item_layout;
    }
}
```

####2、初始化Adapter
```
RefreshAdapter mAdapter = new RefreshAdapter(this, data, true);
```
true代表是否开启加载更多

####3、初始化EmptyView
```
View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
mAdapter.setEmptyView(emptyView);
```

####4、初始化加载中、加载失败、加载完成的Footer View布局
```
mAdapter.setLoadingView(view);

mAdapter.setLoadFailedView(view);

mAdapter.setLoadEndView(view);
```

####5、设置加载更多的回调
```
mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                //do your load more;
            }
        });
```

####6、设置item的点击回调
```
mAdapter.setOnItemClickListener(new OnItemClickListeners<T>() {

            @Override
            public void onItemClick(ViewHolder viewHolder, T data, int position) {

            }
        });
```

####7、更新列表的相关方法
```
//新data插入到原data的尾部
mAdapter.setLoadMoreData(data);

//新data直接替换原data
mAdapter.setNewData(data);

//新data插入到原data头部
mAdapter.setData(data);
```