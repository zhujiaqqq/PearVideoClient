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
public class UserInfo {

    private String userId;
    private String nickname;
    private String pic;
    private String level;
    private String signature;
    private String isFollow;

    public String getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setUserId(String userId) {
         this.userId = userId;
     }
     public String getUserId() {
         return userId;
     }

    public void setNickname(String nickname) {
         this.nickname = nickname;
     }
     public String getNickname() {
         return nickname;
     }

    public void setPic(String pic) {
         this.pic = pic;
     }
     public String getPic() {
         return pic;
     }

    public void setLevel(String level) {
         this.level = level;
     }
     public String getLevel() {
         return level;
     }

}