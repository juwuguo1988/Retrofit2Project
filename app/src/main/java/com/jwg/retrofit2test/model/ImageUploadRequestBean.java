package com.jwg.retrofit2test.model;

public class ImageUploadRequestBean {

    public String filename;          //原图片名称
    public String scope;             //头像类别（患者头像、家属头像）

    public ImageUploadRequestBean(String filename, String scope) {
        this.filename = filename;
        this.scope = scope;
    }
}
