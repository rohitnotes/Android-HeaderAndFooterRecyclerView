# Android - HeaderAndFooterRecyclerView #

[![Build Status](https://travis-ci.org/TakWolf/Android-HeaderAndFooterRecyclerView.svg?branch=master)](https://travis-ci.org/TakWolf/Android-HeaderAndFooterRecyclerView)
[![Download](https://api.bintray.com/packages/takwolf/maven/Android-HeaderAndFooterRecyclerView/images/download.svg)](https://bintray.com/takwolf/maven/Android-HeaderAndFooterRecyclerView/_latestVersion)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg?style=flat)](https://www.android.com)
[![API](https://img.shields.io/badge/API-9%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![License](https://img.shields.io/github/license/TakWolf/Android-HeaderAndFooterRecyclerView.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Let RecyclerView support add HeaderView and FooterView.

## Usage ##

### Gradle ###

```
compile 'com.takwolf.android:hf-recyclerview:0.0.2'
```

### Layout ###

```
<com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView
    android:id="@+id/recycler_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### Java ###

```
HeaderAndFooterRecyclerView recyclerView = (HeaderAndFooterRecyclerView) findViewById();
recyclerView.setLayoutManager(......);
recyclerView.setAdapter(......);
recyclerView.addHeaderView(......);
recyclerView.addFooterView(......);
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
