# 仿梨视频Android客户端
PearVideoClient，一款仿梨视频的Android客户端，基于MVP+RxJava+Retrofit+Glide，基本涵盖了当前 Android 端开发最常用的主流框架，基于此框架可以快速开发一个app。

本App截图于原型对比：

|            | 原型                                                         | 本App截图                                                    |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| splash页面 | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904230839.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904230905.png) |
| 推荐页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904231327.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904231253.png) |
| 万象页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904231649.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904231458.png) |
| 城市页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904231736.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904231809.png) |
| 频道页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904231939.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904231842.png) |
| 视频页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232143.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232047.png) |
| 作者页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232326.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232218.png) |
| 作者页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232453.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232419.png) |
| 报料页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232508.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232425.png) |
| 关注页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232934.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232429.png) |
| 关注列表页 | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904233029.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232431.png) |
| 我的页面   | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232513.jpg) | ![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904232435.png) |

以上是基本功能页面。

数据采用Charles进行抓包获得：

![](https://raw.githubusercontent.com/zhujiaqqq/daily-pic/master/img/20190904233412.png)

**目前模块包括：**

- 首页：万象、推荐、城市（可选城市列表）

- 频道：社会、体育、生活、科技。。。
- 报料：报料视频
- 关注：关注用户视频列表页面、推荐关注列表页面
- 我的：登录

**项目亮点：**

- mvp模式：解耦model、view层，契约类管理mvp，一目了然，实现纵向解耦，基类完美封装，避免频繁new对象

- 响应式编程，对服务器请求、缓存、变换、线程调度进行封装

- 尝试在部分页面使用MVVM、DataBinding模式

- 封装多种工具类

- 后期将拆分视频播放功能组成单独的模块

**用到的开源库：**

- 'com.squareup.okhttp3:okhttp:4.0.0'
- 'com.squareup.retrofit2:retrofit:2.4.0'
- 'io.reactivex.rxjava2:rxjava:2.2.2'
- 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.40'
- 'com.scwang.smartrefresh:SmartRefreshLayout:1.1.0-alpha-31'
- 'com.github.bumptech.glide:glide:4.3.1'
- 'tv.danmaku.ijk.media:ijkplayer-java:0.8.8'
