/**
  * Copyright 2019 bejson.com 
  */
package com.example.pearvideoclient.entity.content;

/**
 * Auto-generated: 2019-07-27 17:54:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PostInfo {

    private String postId;
    private String name;
    private UserInfo userInfo;
    private String commentTimes;
    private CommunityInfo communityInfo;
    private String isfavorited;
    private String postHtml;
//    private List<ChildList> childList;
    private String moreCommentUrl;
    public void setPostId(String postId) {
         this.postId = postId;
     }
     public String getPostId() {
         return postId;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setUserInfo(UserInfo userInfo) {
         this.userInfo = userInfo;
     }
     public UserInfo getUserInfo() {
         return userInfo;
     }

    public void setCommentTimes(String commentTimes) {
         this.commentTimes = commentTimes;
     }
     public String getCommentTimes() {
         return commentTimes;
     }

    public void setCommunityInfo(CommunityInfo communityInfo) {
         this.communityInfo = communityInfo;
     }
     public CommunityInfo getCommunityInfo() {
         return communityInfo;
     }

    public void setIsfavorited(String isfavorited) {
         this.isfavorited = isfavorited;
     }
     public String getIsfavorited() {
         return isfavorited;
     }

    public void setPostHtml(String postHtml) {
         this.postHtml = postHtml;
     }
     public String getPostHtml() {
         return postHtml;
     }

//    public void setChildList(List<ChildList> childList) {
//         this.childList = childList;
//     }
//     public List<ChildList> getChildList() {
//         return childList;
//     }

    public void setMoreCommentUrl(String moreCommentUrl) {
         this.moreCommentUrl = moreCommentUrl;
     }
     public String getMoreCommentUrl() {
         return moreCommentUrl;
     }

}