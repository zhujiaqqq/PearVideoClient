/**
  * Copyright 2019 bejson.com 
  */
package com.example.pearvideoclient.entity.bean.content;
import java.util.List;

/**
 * Auto-generated: 2019-07-27 17:54:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ChildList {

    private String commentId;
    private String content;
    private String pubTime;
    private String topTimes;
    private String stepTimes;
    private String replyTimes;
    private UserInfo userInfo;
    private SinaComment sinaComment;
    private List<String> childList;
    private String moreReplyUrl;
    public void setCommentId(String commentId) {
         this.commentId = commentId;
     }
     public String getCommentId() {
         return commentId;
     }

    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

    public void setPubTime(String pubTime) {
         this.pubTime = pubTime;
     }
     public String getPubTime() {
         return pubTime;
     }

    public void setTopTimes(String topTimes) {
         this.topTimes = topTimes;
     }
     public String getTopTimes() {
         return topTimes;
     }

    public void setStepTimes(String stepTimes) {
         this.stepTimes = stepTimes;
     }
     public String getStepTimes() {
         return stepTimes;
     }

    public void setReplyTimes(String replyTimes) {
         this.replyTimes = replyTimes;
     }
     public String getReplyTimes() {
         return replyTimes;
     }

    public void setUserInfo(UserInfo userInfo) {
         this.userInfo = userInfo;
     }
     public UserInfo getUserInfo() {
         return userInfo;
     }

    public void setSinaComment(SinaComment sinaComment) {
         this.sinaComment = sinaComment;
     }
     public SinaComment getSinaComment() {
         return sinaComment;
     }

    public void setChildList(List<String> childList) {
         this.childList = childList;
     }
     public List<String> getChildList() {
         return childList;
     }

    public void setMoreReplyUrl(String moreReplyUrl) {
         this.moreReplyUrl = moreReplyUrl;
     }
     public String getMoreReplyUrl() {
         return moreReplyUrl;
     }

}