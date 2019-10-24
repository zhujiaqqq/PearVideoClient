package com.example.pearvideoclient

import com.example.pearvideoclient.entity.AlbumContBean
import com.example.pearvideoclient.entity.AuthorHomeBean
import com.example.pearvideoclient.entity.CategoryBean
import com.example.pearvideoclient.entity.CategoryContsBean
import com.example.pearvideoclient.entity.CityListBean
import com.example.pearvideoclient.entity.ContPraise
import com.example.pearvideoclient.entity.DomainListBean
import com.example.pearvideoclient.entity.FollowUsersBean
import com.example.pearvideoclient.entity.LocalContsBean
import com.example.pearvideoclient.entity.LoginBean
import com.example.pearvideoclient.entity.LogoutBean
import com.example.pearvideoclient.entity.MsgMarkBean
import com.example.pearvideoclient.entity.MyFollowContBean
import com.example.pearvideoclient.entity.MyReadHisListBean
import com.example.pearvideoclient.entity.NewsBean
import com.example.pearvideoclient.entity.PaikeFineVideoBean
import com.example.pearvideoclient.entity.RecommendBean
import com.example.pearvideoclient.entity.UserAlbumsBean
import com.example.pearvideoclient.entity.UserConts
import com.example.pearvideoclient.entity.UserFollowBean
import com.example.pearvideoclient.entity.UserInfoBean
import com.example.pearvideoclient.entity.UserPostsBean
import com.example.pearvideoclient.entity.content.ContentBean

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author jiazhu
 */
interface Api {

    /**
     * 查分类
     *
     * @return
     */
    @get:GET("clt/jsp/v4/getCategorys.jsp")
    val category: Observable<CategoryBean>

    /**
     * 获取我的浏览数据
     *
     * @return
     */
    @get:POST("clt/jsp/v4/myReadHisList.jsp")
    val myReadHisList: Observable<MyReadHisListBean>

    /**
     * 获取万象页面数据
     * http://app.pearvideo.com/clt/jsp/v4/getNewsList.jsp
     *
     * @return
     */
    @get:GET("clt/jsp/v4/getNewsList.jsp")
    val newsList: Observable<NewsBean>

    /**
     * 获取拍客页面数据
     *
     * @return
     */
    @get:GET("clt/jsp/v4/getPaikeFineVideos.jsp")
    val paikeFineVideos: Observable<PaikeFineVideoBean>

    /**
     * 查单类中的数据
     *
     * @param hotPageidx
     * @param categoryId
     * @param start
     * @return
     */
    @GET("clt/jsp/v4/getCategoryConts.jsp")
    fun getCategoryConts(
            @Query("hotPageidx") hotPageidx: Int,
            @Query("categoryId") categoryId: String,
            @Query("start") start: Int): Observable<CategoryContsBean>

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    @GET("/clt/jsp/v4/userInfo.jsp")
    fun getUserInfo(@Query("userId") userId: String?): Observable<UserInfoBean>

    /**
     * 获取用户主页信息
     *
     * @param userId
     * @param start
     * @return
     */
    @POST("/clt/jsp/v4/userHome.jsp")
    @FormUrlEncoded
    fun getUserHome(@Query("start") start: String, @Field("userId") userId: String): Observable<AuthorHomeBean>

    /**
     * 登出
     *
     * @return
     */
    @GET("/clt/v4/logout.msp")
    fun logout(): Observable<LogoutBean>

    /**
     * 刷新用户状态
     *
     * @param lastSysTime
     * @return
     */
    @GET("clt/v4/getMsgMark.msp")
    fun getMsgMark(@Query("lastSysTime") lastSysTime: String): Observable<MsgMarkBean>


    /**
     * 登录
     *
     * @param loginName 用户名
     * @param pwd       密码（MD5加密）
     * @return 登录信息
     */
    @FormUrlEncoded
    @POST("clt/v4/login.msp")
    fun login(@Field("loginName") loginName: String, @Field("pwd") pwd: String): Observable<LoginBean>

    /**
     * 获取视频内容
     *
     * @param contId 视频Id
     * @return 内容
     */
    @GET("clt/jsp/v4/content.jsp")
    fun getContent(@Query("contId") contId: String): Observable<ContentBean>

    /**
     * 给视频star
     *
     * @return
     */
    @POST("clt/v4/contPraise.msp")
    @FormUrlEncoded
    fun toStar(@Field("contId") contId: String): Observable<ContPraise>

    /**
     * 获取作者信息
     *
     * @param start  索引
     * @param userId 用户ID
     * @return 作者信息
     */
    @POST("clt/jsp/v4/getUserConts.jsp")
    @FormUrlEncoded
    fun getUserConts(@Field("start") start: String, @Field("userId") userId: String): Observable<UserConts>

    /**
     * 获取作者专辑列表
     *
     * @param start  索引
     * @param userId 用户ID
     * @return 专辑列表
     */
    @POST("clt/jsp/v4/getUserAlbums.jsp")
    @FormUrlEncoded
    fun getUserAlbums(@Field("start") start: String, @Field("userId") userId: String): Observable<UserAlbumsBean>

    /**
     * 关注/取关用户
     *
     * @param opt     1：关注 2：取关
     * @param userIds 用户ID
     * @return 返回结果
     */
    @POST("clt/v4/optUserFollow.msp")
    @FormUrlEncoded
    fun optUserFollow(@Field("opt") opt: String, @Field("userIds") userIds: String): Observable<UserFollowBean>

    /**
     * 获取我关注的用户列表
     *
     * @param start 页码索引
     * @return 返回关注列表
     */
    @POST("clt/jsp/v4/myFollowContList.jsp")
    fun myFollowContList(@Query("start") start: String): Observable<MyFollowContBean>

    @POST("clt/jsp/v4/getUserPosts.jsp")
    @FormUrlEncoded
    fun getUserPosts(@Field("start") start: String, @Field("userId") userId: String, @Field("score") score: String?): Observable<UserPostsBean>

    /**
     * 获取万象页面数据
     * http://app.pearvideo.com/clt/jsp/v4/getNewsList.jsp?filterIds=100001430,100001428,100001426,100001425&start=10&pstart=6
     *
     * @param filterIds 过滤置顶数据
     * @param start     索引
     * @param pstart    索引2
     * @return
     */
    @GET("clt/jsp/v4/getNewsList.jsp")
    fun getNewsList(@Query("filterIds") filterIds: String, @Query("start") start: Int, @Query("pstart") pstart: Int): Observable<NewsBean>

    /**
     * 获取推荐页面数据
     * http://app.pearvideo.com/clt/jsp/v4/home.jsp?isHome=1&channelCode=110100
     *
     * @param isHome      1
     * @param channelCode 城市
     * @return
     */
    @GET("clt/jsp/v4/home.jsp")
    fun getHome(@Query("isHome") isHome: Int, @Query("channelCode") channelCode: String): Observable<RecommendBean>

    /**
     * 获取推荐页面数据
     * http://app.pearvideo.com/clt/jsp/v4/home.jsp?isHome=1&channelCode=110100&start=10&isHome=1&channelCode=110100
     *
     * @param isHome      1
     * @param channelCode 城市
     * @param start       索引
     * @return
     */
    @GET("clt/jsp/v4/home.jsp")
    fun getHome(@Query("isHome") isHome: Int, @Query("channelCode") channelCode: String, @Query("start") start: Int): Observable<RecommendBean>

    /**
     * 获取第一页城市页面数据
     *
     * @param channelCode 城市编码
     * @return
     */
    @GET("clt/jsp/v4/localChannelConts.jsp")
    fun getLocalChannelConts(@Query("channelCode") channelCode: String): Observable<LocalContsBean>

    /**
     * 获取第二页以及往后城市页面数据
     *
     * @param channelCode 城市编码
     * @param start       索引 +10
     * @return
     */
    @GET("clt/jsp/v4/localChannelConts2.jsp")
    fun getLocalChannelConts(@Query("channelCode") channelCode: String, @Query("start") start: Int): Observable<LocalContsBean>

    /**
     * 获取城市列表
     *
     * @return
     */
    @GET("clt/jsp/v4/localChannels.jsp")
    fun localChannels(): Observable<CityListBean>

    @POST("clt/jsp/v4/getAlbumConts.jsp")
    @FormUrlEncoded
    fun getAlbumConts(@Field("albumId") albumId: String?, @Query("start") start: Int): Observable<AlbumContBean>

    /**
     * 获取关注类型
     *
     * @param type ALL
     * @return
     */
    @GET("clt/jsp/v4/domainList.jsp")
    fun getDomainList(@Query("type") type: String): Observable<DomainListBean>

    /**
     * 获取关注用户
     * /clt/jsp/v4/getUsers.jsp?domainId=null&userId=&type=1        推荐
     * /clt/jsp/v4/getUsers.jsp?domainId=&userId=&type=0            最新
     * /clt/jsp/v4/getUsers.jsp?domainId=5&userId=&type=3           资讯
     * @param domainId
     * @param userId
     * @param type
     * @return
     */
    @GET("clt/jsp/v4/getUsers.jsp")
    fun getUsers(@Query("domainId") domainId: String, @Query("userId") userId: String, @Query("type") type: Int?): Observable<FollowUsersBean>
}
