# Android - HeaderAndFooterRecyclerView #

[![Build Status](https://travis-ci.org/TakWolf/Android-HeaderAndFooterRecyclerView.svg?branch=master)](https://travis-ci.org/TakWolf/Android-HeaderAndFooterRecyclerView)
[![Download](https://api.bintray.com/packages/takwolf/maven/Android-HeaderAndFooterRecyclerView/images/download.svg)](https://bintray.com/takwolf/maven/Android-HeaderAndFooterRecyclerView/_latestVersion)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg?style=flat)](https://www.android.com)
[![API](https://img.shields.io/badge/API-9%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![License](https://img.shields.io/github/license/TakWolf/Android-HeaderAndFooterRecyclerView.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Let RecyclerView support add HeaderView and FooterView.

一个支持添加 HeaderView 和 FooterView 的 RecyclerView。特征如下：

- 无侵入式，使用方式和原有 RecyclerView 相同，不需要修改业务 Adapter。

- 支持 LinearLayoutManager、GridLayoutManager 和 StaggeredGridLayoutManager 三种布局管理器的横向和纵向布局。

- 支持动态添加删除 HeaderView 和 FooterView。

- 支持动态切换 Adapter 和 LayoutManager。

示例工程中包含了所有布局类型的使用范例，以及一个下拉刷新和加载更多效果的实现范例。

请在[这里](https://fir.im/hfrecyclerview)下载示例程序。

## Usage ##

### Gradle ###

``` gradle
compile 'com.takwolf.android:hf-recyclerview:0.1.0'
```

### Layout ###

``` xml
<com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView
    android:id="@+id/recycler_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### Java ###

``` java
HeaderAndFooterRecyclerView recyclerView = (HeaderAndFooterRecyclerView) findViewById(R.id.recycler_view);
recyclerView.setLayoutManager(new LinearLayoutManager(context));
recyclerView.setAdapter(adapter);

View headerView = LayoutInflater.from(context).inflate(R.layout.header, recyclerView.getHeaderContainer(), false);
recyclerView.addHeaderView(headerView);

View footerView = LayoutInflater.from(context).inflate(R.layout.footer, recyclerView.getFooterContainer(), false);
recyclerView.addFooterView(footerView);
```

## Author ##

TakWolf

[takwolf@foxmail.com](mailto:takwolf@foxmail.com)

[http://takwolf.com](http://takwolf.com)

## License ##

```
Copyright 2017 TakWolf

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
