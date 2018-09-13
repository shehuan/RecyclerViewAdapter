# RecyclerViewAdapter

## 简介
一个支持RecyclerView加载更多、添加HeaderView的BaseAdapter

[原理、效果](http://www.jianshu.com/p/66c065874848)

[demo下载](https://fir.im/e5pz)

## 导入

**1. 添加JitPack仓库**

在当前项目根目录下的 `build.gradle` 文件中添加如下内容:
``` gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
**2. 添加项目依赖**
``` gradle
dependencies {
        implementation 'com.github.SheHuan:RecyclerViewAdapter:1.2.6'
}
```

## 用法

### 一、创建Adapter
#### 1、创建只有一种ItemView的Adapter
```java
public class CommonRefreshAdapter extends CommonBaseAdapter<T> {

    public CommonRefreshAdapter (Context context, List<T> datas, boolean isLoadMore) {
        super(context, datas, isLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, T data, int position) {
        //init item
    }

    @Override
    protected int getItemLayoutId() {
        return item_layout;
    }
}
```
#### 2、创建有多种ItemView的Adapter
```java
public class MultiRefreshAdapter extends MultiBaseAdapter<T> {

    public MultiRefreshAdapter(Context context, List<T> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, T data, int position, int viewType) {
        
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        
    }

    @Override
    protected int getViewType(int position, T data) {
       
    }
}
```

### 二、初始化Adapter
#### 1、 初始化只有一种ItemView的Adapter
```java
CommonRefreshAdapter mAdapter = new CommonRefreshAdapter(this, data, true);
```
#### 2、初始化只有一种ItemView的Adapter
```java
MultiRefreshAdapter mAdapter = new MultiRefreshAdapter(this, data, true);
```
PS：true代表开启加载更多。

#### 3、设置化EmptyView
```java
View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
mAdapter.setEmptyView(emptyView);
```
#### 4、直接移除EmptyView
```java
mAdapter.removeEmptyView();
```
#### 5、显示首次预加载无数据或失败的view,以便重新加载或提示用户
```java
View reloadLayout = LayoutInflater.from(this).inflate(R.layout.reload_layout, (ViewGroup) mRecyclerView.getParent(), false);
mAdapter.setReloadView(reloadLayout);
```

#### 6、设置化加载中、加载失败、加载完成的FooterView布局
```java
mAdapter.setLoadingView(view);

mAdapter.setLoadFailedView(view);

mAdapter.setLoadEndView(view);
```

#### 7、设置加载更多的回调
```java
mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                //do your load more;
            }
        });
```

#### 8、设置只有一种ItemView的Item点击回调
```java
mAdapter.setOnItemClickListener(new OnItemClickListeners<T>() {

            @Override
            public void onItemClick(ViewHolder viewHolder, T data, int position) {

            }
        });
```

#### 9、设置有多种ItemView类型的Item点击回调
```java
mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<T>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, T data, int position, int viewType) {
                
            }
        });
```

#### 10、设置Item子view点击事件
```java
mAdapter.setOnItemChildClickListener(R.id.child, new OnItemChildClickListener<String>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, String data, int position) {

            }
        });
```

#### 11、给RecyclerView添加HeaderView
```
// 可添加多个
mAdapter.addHeaderView(view)
```

#### 12、控制HeaderView的显示
```
mAdapter.isShowHeaderView()
```

#### 13、数据不满一屏时默认关闭自动加载更多，可用如下方法开启
```
mAdapter.openAutoLoadMore()
```

#### 14、某些情况你可能需要移除LoadView
```
mAdapter.clearLoadView()
```

### 三、更多操作

#### 1、显示加载更多数据失败时的FooterView
```java
mAdapter.loadFailed();
```
#### 2、显示加载数据结束的FooterView
```java
mAdapter.loadEnd();
```
#### 3、重置Adapter
```java
mAdapter.reset();
```
#### 4、更新列表的相关方法
```java
// 新data插入到原data的尾部（为加载更多封装的方法）
mAdapter.setLoadMoreData(data);

// 新data直接替换原data（为加载更多封装的方法）
mAdapter.setNewData(data);

// 通用插入数据
mAdapter.insert()

// 通用删除数据
mAdapter.remove()

// 通用更新数据
mAdapter.change()

```
