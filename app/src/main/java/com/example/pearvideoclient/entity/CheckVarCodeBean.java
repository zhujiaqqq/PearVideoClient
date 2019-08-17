package com.example.pearvideoclient.entity;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-21 16:18
 * @ClassName: CheckVarCodeBean
 */
public class CheckVarCodeBean {

    /**
     * reqId : a6978478-90d4-4302-80f4-dc00022c778d
     * resultMsg : 校验成功
     * systemTime : 1563695031673
     * token : 38c56842-c85b-484c-8560-0ac08a90f648
     * resultCode : 1
     * verCode : 672366
     * userInfo : {"address":null,"appType":0,"area":null,"biankeType":0,"birthday":null,"forbidSetHead":0,"id":13504593,"illegalHeadTimes":0,"images":[{"bucket":"pv-prod-img-ugc","description":null,"filePath":"user/20190619/13504593-215406.jpg","fileSize":2242,"fileType":"jpg","height":132,"id":15066778,"name":null,"objectId":13504593,"objectType":9,"permission":2,"status":1,"tag":"head","updateTime":null,"url":null,"width":132}],"interestJson":null,"isAuth":0,"isAuthor":0,"isBianke":0,"isBiankeAgreement":0,"isBlackList":0,"isIllegalHead":0,"isPaike":0,"isPaikeAgreement":0,"isSetInterest":0,"isSetPwd":1,"lastDeviceId":24864552,"lastLoginIp":"112.86.250.9","lastLoginTime":"2019-07-21T15:43:51","level":0,"mail":null,"mainId":null,"mainStatus":null,"managerId":null,"mobile":"13815865892","nickname":"zhujiaqqq","nodeId":null,"paikeType":0,"password":null,"pic":"http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg","regIp":"117.136.45.155","regTime":"2019-04-26T16:25:31","schema":"http","sex":2,"signature":"一二三四五上山","societyIdMap":{"WEIXIN":"ovRaPwGdgLMFAMS3PbdE1ahFw0y4"},"token":"38c56842-c85b-484c-8560-0ac08a90f648","userId":13504593,"userType":"station","youngModeEnable":0,"zenId":null}
     * verType : 5
     * loginName : 13815865892
     */

    private String reqId;
    private String resultMsg;
    private long systemTime;
    private String token;
    private String resultCode;
    private String verCode;
    private UserInfoBean userInfo;
    private int verType;
    private String loginName;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(long systemTime) {
        this.systemTime = systemTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public int getVerType() {
        return verType;
    }

    public void setVerType(int verType) {
        this.verType = verType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public static class UserInfoBean {
        /**
         * address : null
         * appType : 0
         * area : null
         * biankeType : 0
         * birthday : null
         * forbidSetHead : 0
         * id : 13504593
         * illegalHeadTimes : 0
         * images : [{"bucket":"pv-prod-img-ugc","description":null,"filePath":"user/20190619/13504593-215406.jpg","fileSize":2242,"fileType":"jpg","height":132,"id":15066778,"name":null,"objectId":13504593,"objectType":9,"permission":2,"status":1,"tag":"head","updateTime":null,"url":null,"width":132}]
         * interestJson : null
         * isAuth : 0
         * isAuthor : 0
         * isBianke : 0
         * isBiankeAgreement : 0
         * isBlackList : 0
         * isIllegalHead : 0
         * isPaike : 0
         * isPaikeAgreement : 0
         * isSetInterest : 0
         * isSetPwd : 1
         * lastDeviceId : 24864552
         * lastLoginIp : 112.86.250.9
         * lastLoginTime : 2019-07-21T15:43:51
         * level : 0
         * mail : null
         * mainId : null
         * mainStatus : null
         * managerId : null
         * mobile : 13815865892
         * nickname : zhujiaqqq
         * nodeId : null
         * paikeType : 0
         * password : null
         * pic : http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg
         * regIp : 117.136.45.155
         * regTime : 2019-04-26T16:25:31
         * schema : http
         * sex : 2
         * signature : 一二三四五上山
         * societyIdMap : {"WEIXIN":"ovRaPwGdgLMFAMS3PbdE1ahFw0y4"}
         * token : 38c56842-c85b-484c-8560-0ac08a90f648
         * userId : 13504593
         * userType : station
         * youngModeEnable : 0
         * zenId : null
         */

        private Object address;
        private int appType;
        private Object area;
        private int biankeType;
        private Object birthday;
        private int forbidSetHead;
        private int id;
        private int illegalHeadTimes;
        private Object interestJson;
        private int isAuth;
        private int isAuthor;
        private int isBianke;
        private int isBiankeAgreement;
        private int isBlackList;
        private int isIllegalHead;
        private int isPaike;
        private int isPaikeAgreement;
        private int isSetInterest;
        private int isSetPwd;
        private int lastDeviceId;
        private String lastLoginIp;
        private String lastLoginTime;
        private int level;
        private Object mail;
        private Object mainId;
        private Object mainStatus;
        private Object managerId;
        private String mobile;
        private String nickname;
        private Object nodeId;
        private int paikeType;
        private Object password;
        private String pic;
        private String regIp;
        private String regTime;
        private String schema;
        private int sex;
        private String signature;
        private SocietyIdMapBean societyIdMap;
        private String token;
        private int userId;
        private String userType;
        private int youngModeEnable;
        private Object zenId;
        private List<ImagesBean> images;

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public int getAppType() {
            return appType;
        }

        public void setAppType(int appType) {
            this.appType = appType;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public int getBiankeType() {
            return biankeType;
        }

        public void setBiankeType(int biankeType) {
            this.biankeType = biankeType;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public int getForbidSetHead() {
            return forbidSetHead;
        }

        public void setForbidSetHead(int forbidSetHead) {
            this.forbidSetHead = forbidSetHead;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIllegalHeadTimes() {
            return illegalHeadTimes;
        }

        public void setIllegalHeadTimes(int illegalHeadTimes) {
            this.illegalHeadTimes = illegalHeadTimes;
        }

        public Object getInterestJson() {
            return interestJson;
        }

        public void setInterestJson(Object interestJson) {
            this.interestJson = interestJson;
        }

        public int getIsAuth() {
            return isAuth;
        }

        public void setIsAuth(int isAuth) {
            this.isAuth = isAuth;
        }

        public int getIsAuthor() {
            return isAuthor;
        }

        public void setIsAuthor(int isAuthor) {
            this.isAuthor = isAuthor;
        }

        public int getIsBianke() {
            return isBianke;
        }

        public void setIsBianke(int isBianke) {
            this.isBianke = isBianke;
        }

        public int getIsBiankeAgreement() {
            return isBiankeAgreement;
        }

        public void setIsBiankeAgreement(int isBiankeAgreement) {
            this.isBiankeAgreement = isBiankeAgreement;
        }

        public int getIsBlackList() {
            return isBlackList;
        }

        public void setIsBlackList(int isBlackList) {
            this.isBlackList = isBlackList;
        }

        public int getIsIllegalHead() {
            return isIllegalHead;
        }

        public void setIsIllegalHead(int isIllegalHead) {
            this.isIllegalHead = isIllegalHead;
        }

        public int getIsPaike() {
            return isPaike;
        }

        public void setIsPaike(int isPaike) {
            this.isPaike = isPaike;
        }

        public int getIsPaikeAgreement() {
            return isPaikeAgreement;
        }

        public void setIsPaikeAgreement(int isPaikeAgreement) {
            this.isPaikeAgreement = isPaikeAgreement;
        }

        public int getIsSetInterest() {
            return isSetInterest;
        }

        public void setIsSetInterest(int isSetInterest) {
            this.isSetInterest = isSetInterest;
        }

        public int getIsSetPwd() {
            return isSetPwd;
        }

        public void setIsSetPwd(int isSetPwd) {
            this.isSetPwd = isSetPwd;
        }

        public int getLastDeviceId() {
            return lastDeviceId;
        }

        public void setLastDeviceId(int lastDeviceId) {
            this.lastDeviceId = lastDeviceId;
        }

        public String getLastLoginIp() {
            return lastLoginIp;
        }

        public void setLastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public Object getMail() {
            return mail;
        }

        public void setMail(Object mail) {
            this.mail = mail;
        }

        public Object getMainId() {
            return mainId;
        }

        public void setMainId(Object mainId) {
            this.mainId = mainId;
        }

        public Object getMainStatus() {
            return mainStatus;
        }

        public void setMainStatus(Object mainStatus) {
            this.mainStatus = mainStatus;
        }

        public Object getManagerId() {
            return managerId;
        }

        public void setManagerId(Object managerId) {
            this.managerId = managerId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getNodeId() {
            return nodeId;
        }

        public void setNodeId(Object nodeId) {
            this.nodeId = nodeId;
        }

        public int getPaikeType() {
            return paikeType;
        }

        public void setPaikeType(int paikeType) {
            this.paikeType = paikeType;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getRegIp() {
            return regIp;
        }

        public void setRegIp(String regIp) {
            this.regIp = regIp;
        }

        public String getRegTime() {
            return regTime;
        }

        public void setRegTime(String regTime) {
            this.regTime = regTime;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public SocietyIdMapBean getSocietyIdMap() {
            return societyIdMap;
        }

        public void setSocietyIdMap(SocietyIdMapBean societyIdMap) {
            this.societyIdMap = societyIdMap;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public int getYoungModeEnable() {
            return youngModeEnable;
        }

        public void setYoungModeEnable(int youngModeEnable) {
            this.youngModeEnable = youngModeEnable;
        }

        public Object getZenId() {
            return zenId;
        }

        public void setZenId(Object zenId) {
            this.zenId = zenId;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class SocietyIdMapBean {
            /**
             * WEIXIN : ovRaPwGdgLMFAMS3PbdE1ahFw0y4
             */

            private String WEIXIN;

            public String getWEIXIN() {
                return WEIXIN;
            }

            public void setWEIXIN(String WEIXIN) {
                this.WEIXIN = WEIXIN;
            }
        }

        public static class ImagesBean {
            /**
             * bucket : pv-prod-img-ugc
             * description : null
             * filePath : user/20190619/13504593-215406.jpg
             * fileSize : 2242
             * fileType : jpg
             * height : 132
             * id : 15066778
             * name : null
             * objectId : 13504593
             * objectType : 9
             * permission : 2
             * status : 1
             * tag : head
             * updateTime : null
             * url : null
             * width : 132
             */

            private String bucket;
            private Object description;
            private String filePath;
            private int fileSize;
            private String fileType;
            private int height;
            private int id;
            private Object name;
            private int objectId;
            private int objectType;
            private int permission;
            private int status;
            private String tag;
            private Object updateTime;
            private Object url;
            private int width;

            public String getBucket() {
                return bucket;
            }

            public void setBucket(String bucket) {
                this.bucket = bucket;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public int getFileSize() {
                return fileSize;
            }

            public void setFileSize(int fileSize) {
                this.fileSize = fileSize;
            }

            public String getFileType() {
                return fileType;
            }

            public void setFileType(String fileType) {
                this.fileType = fileType;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public int getObjectId() {
                return objectId;
            }

            public void setObjectId(int objectId) {
                this.objectId = objectId;
            }

            public int getObjectType() {
                return objectType;
            }

            public void setObjectType(int objectType) {
                this.objectType = objectType;
            }

            public int getPermission() {
                return permission;
            }

            public void setPermission(int permission) {
                this.permission = permission;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }
}
