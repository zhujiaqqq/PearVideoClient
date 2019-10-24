package com.example.pearvideoclient.entity

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-21 15:59
 * @ClassName: UserInfoBean
 */
data class UserInfoBean(val resultCode: String,
                        val resultMsg: String,
                        val reqId: String,
                        val systemTime: String,
                        val userInfo: InfoBean)

data class InfoBean(
        val userId: String,
        val nickname: String,
        val signature: String,
        val pic: String,
        val isFollow: String,
        val level: String,
        val backgroundImg: String,
        val hasAlbum: String,
        val shareInfo: ShareInfoBean
)


data class ShareInfoBean(
        val url: String,
        val title: String,
        val summary: String,
        val logo: String
)