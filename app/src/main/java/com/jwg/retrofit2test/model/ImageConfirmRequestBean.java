package com.jwg.retrofit2test.model;

public class ImageConfirmRequestBean {

    public String awsId;
    public String id;       //图片数据的Id
    public String status;   //图片上传的状态(成功：success / 失败：fail

    public ImageConfirmRequestBean(String awsId) {
        this.awsId = awsId;
    }

    public ImageConfirmRequestBean(String id, String status) {
        this.id = id;
        this.status = status;
    }
}
