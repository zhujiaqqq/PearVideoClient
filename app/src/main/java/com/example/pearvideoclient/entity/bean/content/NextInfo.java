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
public class NextInfo {

    private String contId;
    private String name;
    private String pic;
    private String forwordType;
    private String isVr;
    private String aspectRatio;
    private List<Videos> videos;
    public void setContId(String contId) {
         this.contId = contId;
     }
     public String getContId() {
         return contId;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setPic(String pic) {
         this.pic = pic;
     }
     public String getPic() {
         return pic;
     }

    public void setForwordType(String forwordType) {
         this.forwordType = forwordType;
     }
     public String getForwordType() {
         return forwordType;
     }

    public void setIsVr(String isVr) {
         this.isVr = isVr;
     }
     public String getIsVr() {
         return isVr;
     }

    public void setAspectRatio(String aspectRatio) {
         this.aspectRatio = aspectRatio;
     }
     public String getAspectRatio() {
         return aspectRatio;
     }

    public void setVideos(List<Videos> videos) {
         this.videos = videos;
     }
     public List<Videos> getVideos() {
         return videos;
     }

}