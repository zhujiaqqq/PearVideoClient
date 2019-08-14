package com.example.pearvideoclient;

import com.example.pearvideoclient.entity.bean.AuthorHomeBean;
import com.example.pearvideoclient.entity.bean.CategoryBean;
import com.example.pearvideoclient.entity.bean.CategoryContsBean;
import com.example.pearvideoclient.entity.bean.ContPraise;
import com.example.pearvideoclient.entity.bean.LoginBean;
import com.example.pearvideoclient.entity.bean.LogoutBean;
import com.example.pearvideoclient.entity.bean.MsgMarkBean;
import com.example.pearvideoclient.entity.bean.MyFollowContBean;
import com.example.pearvideoclient.entity.bean.MyReadHisListBean;
import com.example.pearvideoclient.entity.bean.UserConts;
import com.example.pearvideoclient.entity.bean.UserFollowBean;
import com.example.pearvideoclient.entity.bean.UserHomeBean;
import com.example.pearvideoclient.entity.bean.UserInfoBean;
import com.example.pearvideoclient.entity.bean.content.ContentBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author jiazhu
 */
public interface Api {

    /**
     * 查分类
     *
     * @return
     */
    @GET("clt/jsp/v4/getCategorys.jsp")
    Observable<CategoryBean> getCategory();

    /**
     * 查单类中的数据
     *
     * @param hotPageidx
     * @param categoryId
     * @param start
     * @return
     */
    @GET("clt/jsp/v4/getCategoryConts.jsp")
    Observable<CategoryContsBean> getCategoryConts(
            @Query("hotPageidx") String hotPageidx,
            @Query("categoryId") String categoryId,
            @Query("start") String start);

    /**
     * 获取我的浏览数据
     *
     * @return
     */
    @POST("clt/jsp/v4/myReadHisList.jsp")
    Observable<MyReadHisListBean> getMyReadHisList();

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    @GET("/clt/jsp/v4/userInfo.jsp")
    Observable<UserInfoBean> getUserInfo(@Query("userId") String userId);

    /**
     * 获取用户主页信息
     *
     * @param userId
     * @param start
     * @return
     */
    @POST("/clt/jsp/v4/userHome.jsp")
    @FormUrlEncoded
    Observable<AuthorHomeBean> getUserHome(@Query("start") String start, @Field("userId") String userId);

    /**
     * 登出
     *
     * @return
     */
    @GET("/clt/v4/logout.msp")
    Observable<LogoutBean> logout();

    /**
     * 刷新用户状态
     *
     * @param lastSysTime
     * @return
     */
    @GET("clt/v4/getMsgMark.msp")
    Observable<MsgMarkBean> getMsgMark(@Query("lastSysTime") String lastSysTime);


    /**
     * 登录
     *
     * @param loginName 用户名
     * @param pwd       密码（MD5加密）
     * @return 登录信息
     */
    @FormUrlEncoded
    @POST("clt/v4/login.msp")
    Observable<LoginBean> login(@Field("loginName") String loginName, @Field("pwd") String pwd);

    /**
     * 获取视频内容
     *
     * @param contId 视频Id
     * @return 内容
     */
    @GET("clt/jsp/v4/content.jsp")
    Observable<ContentBean> getContent(@Query("contId") String contId);

    /**
     * 给视频star
     *
     * @return
     */
    @POST("clt/v4/contPraise.msp")
    @FormUrlEncoded
    Observable<ContPraise> toStar(@Field("contId") String contId);

    /**
     * 获取作者信息
     *
     * @param userId 用户ID
     * @return 作者信息
     */
    @POST("clt/jsp/v4/getUserConts.jsp")
    @FormUrlEncoded
    Observable<UserConts> getUserConts(@Field("userId") String userId);

    /**
     * 关注/取关用户
     *
     * @param opt     1：关注 2：取关
     * @param userIds 用户ID
     * @return 返回结果
     */
    @POST("clt/v4/optUserFollow.msp")
    @FormUrlEncoded
    Observable<UserFollowBean> optUserFollow(@Field("opt") String opt, @Field("userIds") String userIds);

    /**
     * 获取我关注的用户列表
     *
     * @param start 页码索引
     * @return 返回关注列表
     */
    @POST("clt/jsp/v4/myFollowContList.jsp")
    Observable<MyFollowContBean> myFollowContList(@Query("start") String start);

}
