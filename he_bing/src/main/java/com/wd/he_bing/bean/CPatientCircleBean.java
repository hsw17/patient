package com.wd.he_bing.bean;

import java.util.List;

/**
 *@describe(描述)：CPatientCircleBean 查看病友的病友圈发帖列表
 *@data（日期）: 2019/12/24
 *@time（时间）: 10:29
 *@author（作者）: Liuhe
 **/
public class CPatientCircleBean {

    /**
     * result : [{"amount":10,"collectionNum":0,"commentNum":0,"detail":"详情","releaseTime":1564070400000,"sickCircleId":587,"title":"标题"},{"amount":10,"collectionNum":0,"commentNum":0,"detail":"详情","releaseTime":1564070400000,"sickCircleId":576,"title":"标题"},{"amount":10,"collectionNum":0,"commentNum":0,"detail":"详情","releaseTime":1563984000000,"sickCircleId":564,"title":"标题"},{"amount":10,"collectionNum":1,"commentNum":21,"detail":"详情","releaseTime":1556985600000,"sickCircleId":7,"title":"急寻：面神经炎的治疗方法"},{"amount":10,"collectionNum":1,"commentNum":7,"detail":"详情","releaseTime":1556985600000,"sickCircleId":8,"title":"急寻：面神经炎的治疗方法"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * amount : 10
         * collectionNum : 0
         * commentNum : 0
         * detail : 详情
         * releaseTime : 1564070400000
         * sickCircleId : 587
         * title : 标题
         */

        private String amount;
        private String collectionNum;
        private String commentNum;
        private String detail;
        private long releaseTime;
        private String sickCircleId;
        private String title;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(String collectionNum) {
            this.collectionNum = collectionNum;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSickCircleId() {
            return sickCircleId;
        }

        public void setSickCircleId(String sickCircleId) {
            this.sickCircleId = sickCircleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
