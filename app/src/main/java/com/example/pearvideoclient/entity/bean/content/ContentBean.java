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
public class ContentBean {

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private List<AreaList> areaList;
    private Content content;
    private NextInfo nextInfo;
    private PostInfo postInfo;
    private List<NextInfos> nextInfos;
    private List<RelateConts> relateConts;
    private List<HotConts> hotConts;
    public void setResultCode(String resultCode) {
         this.resultCode = resultCode;
     }
     public String getResultCode() {
         return resultCode;
     }

    public void setResultMsg(String resultMsg) {
         this.resultMsg = resultMsg;
     }
     public String getResultMsg() {
         return resultMsg;
     }

    public void setReqId(String reqId) {
         this.reqId = reqId;
     }
     public String getReqId() {
         return reqId;
     }

    public void setSystemTime(String systemTime) {
         this.systemTime = systemTime;
     }
     public String getSystemTime() {
         return systemTime;
     }

    public void setAreaList(List<AreaList> areaList) {
         this.areaList = areaList;
     }
     public List<AreaList> getAreaList() {
         return areaList;
     }

    public void setContent(Content content) {
         this.content = content;
     }
     public Content getContent() {
         return content;
     }

    public void setNextInfo(NextInfo nextInfo) {
         this.nextInfo = nextInfo;
     }
     public NextInfo getNextInfo() {
         return nextInfo;
     }

    public void setPostInfo(PostInfo postInfo) {
         this.postInfo = postInfo;
     }
     public PostInfo getPostInfo() {
         return postInfo;
     }

    public void setNextInfos(List<NextInfos> nextInfos) {
         this.nextInfos = nextInfos;
     }
     public List<NextInfos> getNextInfos() {
         return nextInfos;
     }

    public void setRelateConts(List<RelateConts> relateConts) {
         this.relateConts = relateConts;
     }
     public List<RelateConts> getRelateConts() {
         return relateConts;
     }

    public void setHotConts(List<HotConts> hotConts) {
         this.hotConts = hotConts;
     }
     public List<HotConts> getHotConts() {
         return hotConts;
     }

}