# RecyclerViewAdapter

##简介
一个支持RecyclerView加载更多的BaseAdapter

[原理、效果](http://www.jianshu.com/p/66c065874848)

[demo下载](http://fir.im/k7dl)

##导入
```java
dependencies {
    compile 'com.othershe:BaseAdapter:1.1.0'
}
```

##用法

####1、创建Adapter
(1)创建只有一种ItemView的Adaptr
```java
public class CommonRefreshAdapter extends CommonBaseAdapter<T> {

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
(2)创建有多种ItemView的Adapter
```java
public class MultiRefreshAdapter extends MultiBaseAdapter<T> {

    public MultiRefreshAdapter(Context context, List<T> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, T data, int viewType) {
        
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        
    }

    @Override
    protected int getViewType(int position, T data) {
       
    }
}
```

####2、初始化Adapter
（1）初始化只有一种ItemView的Adaptr
```java
CommonRefreshAdapter mAdapter = new CommonRefreshAdapter(this, data, true);
```
（2）初始化只有一种ItemView的Adaptr
```java
MultiRefreshAdapter mAdapter = new MultiRefreshAdapter(this, data, true);
```

PS：true代表是否开启加载更多，否则不开启。

####3
3.1、初始化EmptyView
```java
View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
mAdapter.setEmptyView(emptyView);
```
3.2、直接移除EmptyView
```java
mAdapter.removeEmptyView();
```
3.3、显示首次预加载无数据或失败的view,以便重新加载或提示用户
```java
View reloadLayout = LayoutInflater.from(this).inflate(R.layout.reload_layout, (ViewGroup) mRecyclerView.getParent(), false);
mAdapter.setReloadView(reloadLayout);
```
PS:3.2和3.3任选其一即可

####4、初始化加载中、加载失败、加载完成的Footer View布局
```java
mAdapter.setLoadingView(view);

mAdapter.setLoadFailedView(view);

mAdapter.setLoadEndView(view);
```

####5、设置加载更多的回调
```java
mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                //do your load more;
            }
        });
```

####6、设置item的点击回调
(1)设置只有一种ItemView的Item点击回调
```java
mAdapter.setOnItemClickListener(new OnItemClickListeners<T>() {

            @Override
            public void onItemClick(ViewHolder viewHolder, T data, int position) {

            }
        });
```

(2)设置有多种ItemView类型的Item点击回调
```java
mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<T>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, T data, int position, int viewType) {
                
            }
        });
```

####7、更新列表的相关方法
```java
//新data插入到原data的尾部
mAdapter.setLoadMoreData(data);

//新data直接替换原data
mAdapter.setNewData(data);

//新data插入到原data头部
mAdapter.setData(data);
```
