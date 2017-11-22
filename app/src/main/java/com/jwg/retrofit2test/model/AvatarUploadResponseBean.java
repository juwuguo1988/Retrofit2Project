package com.jwg.retrofit2test.model;

/**
 * Created by fengzhenye on 16/10/21.
 * 请求头像上传URL返回结果的bean
 */
public class AvatarUploadResponseBean extends BaseResponseBean<AvatarUploadResponseBean.AvatarUploadBean> {

    public class AvatarUploadBean {

        public UploadData uploadData;

        public class UploadData {
            public String signUrl;
            public String awsId;
            public String rawUrl;       //图片最终加载时使用的Url
            public String contentType;
        }
    }
}
