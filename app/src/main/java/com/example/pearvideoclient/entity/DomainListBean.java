package com.example.pearvideoclient.entity;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 16:03
 * @ClassName: DomainListBean
 */
public class DomainListBean {

    /**
     * resultCode : 1
     * resultMsg : success
     * reqId : 9ff021ca-f117-4780-9428-b7d281406036
     * systemTime : 1567411341725
     * sex :
     * domainList : [{"domainId":"5","name":"资讯","selected":"0"},{"domainId":"12","name":"社会","selected":"0"},{"domainId":"8","name":"明星","selected":"0"},{"domainId":"34","name":"影视","selected":"0"},{"domainId":"32","name":"综艺","selected":"0"},{"domainId":"21","name":"搞笑","selected":"0"},{"domainId":"20","name":"两性","selected":"0"},{"domainId":"22","name":"美食","selected":"0"},{"domainId":"4","name":"养生","selected":"0"},{"domainId":"24","name":"旅行","selected":"0"},{"domainId":"23","name":"创意","selected":"0"},{"domainId":"2","name":"科技","selected":"0"},{"domainId":"7","name":"教育","selected":"0"},{"domainId":"9","name":"百科","selected":"0"},{"domainId":"15","name":"文化","selected":"0"},{"domainId":"33","name":"艺术","selected":"0"},{"domainId":"14","name":"历史","selected":"0"},{"domainId":"30","name":"纪录","selected":"0"},{"domainId":"19","name":"人物","selected":"0"},{"domainId":"1","name":"体育","selected":"0"},{"domainId":"37","name":"运动","selected":"0"},{"domainId":"35","name":"音乐","selected":"0"},{"domainId":"36","name":"舞蹈","selected":"0"},{"domainId":"3","name":"军事","selected":"0"},{"domainId":"13","name":"财经","selected":"0"},{"domainId":"38","name":"汽车","selected":"0"},{"domainId":"25","name":"时尚","selected":"0"},{"domainId":"39","name":"美妆","selected":"0"},{"domainId":"26","name":"亲子","selected":"0"},{"domainId":"40","name":"母婴","selected":"0"},{"domainId":"27","name":"萌宠","selected":"0"},{"domainId":"41","name":"动漫","selected":"0"},{"domainId":"42","name":"游戏","selected":"0"},{"domainId":"28","name":"暖闻","selected":"0"},{"domainId":"29","name":"家居","selected":"0"},{"domainId":"44","name":"VR","selected":"0"},{"domainId":"43","name":"Vlog","selected":"0"}]
     */

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private String sex;
    private List<DomainBean> domainList;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<DomainBean> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<DomainBean> domainList) {
        this.domainList = domainList;
    }

    public static class DomainBean {
        /**
         * domainId : 5
         * name : 资讯
         * selected : 0
         */

        private String domainId;
        private String name;
        private String selected;
        /**
         * 添加字段 类型
         * type = 0 最新
         * type = 1 推荐
         * type = 3 其他类别
         */
        private Integer type;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getDomainId() {
            return domainId;
        }

        public void setDomainId(String domainId) {
            this.domainId = domainId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
        }
    }
}
