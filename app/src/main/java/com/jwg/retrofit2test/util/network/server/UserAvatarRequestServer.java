package com.jwg.retrofit2test.util.network.server;

import android.util.Log;
import com.jwg.retrofit2test.model.AvatarUploadResponseBean;
import com.jwg.retrofit2test.model.ImageConfirmRequestBean;
import com.jwg.retrofit2test.model.ImageConfirmResponseBean;
import com.jwg.retrofit2test.model.ImageUploadRequestBean;
import com.jwg.retrofit2test.util.config.AppConfig;
import com.jwg.retrofit2test.util.network.manager.HttpMainRequestManager;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class UserAvatarRequestServer {

    private static final String TAG = "UserAvatarRequestServer";
    private static String awsId = "";
    private static HttpMainRequestManager sHttpMainRequestManager = HttpMainRequestManager.getInstance(AppConfig.XZL_DEBUG_BASE_URL);

    public static void uploadUserAvatar(ImageUploadRequestBean imageUploadRequestBean, Subscriber<AvatarUploadResponseBean> subscriber) {
        sHttpMainRequestManager
                .doHttpRequest(sHttpMainRequestManager.getHttpService().uploadUserAvatarBean(imageUploadRequestBean), subscriber);
    }


    public static void uploadUserAvatarRoad(ImageUploadRequestBean imageUploadRequestBean,
            final File mCropPhotoFile, Subscriber<ImageConfirmResponseBean> subscriber) {
        Observable<AvatarUploadResponseBean> observableAvatar = sHttpMainRequestManager.getHttpService()
                .uploadUserAvatarBean(imageUploadRequestBean);

        sHttpMainRequestManager
                .doHttpRequest(observableAvatar.flatMap(new Func1<AvatarUploadResponseBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(AvatarUploadResponseBean avatarUploadResponseBean) {
                        RequestBody requestBody =
                                RequestBody.create(MediaType.parse("multipart/form-data"), mCropPhotoFile);
                        awsId = avatarUploadResponseBean.data.uploadData.awsId;
                        Log.e("=========signUrl====", "======" + avatarUploadResponseBean.data.uploadData.signUrl);
                        return HttpMainRequestManager.getInstance(avatarUploadResponseBean.data.uploadData.signUrl).getHttpService()
                                .uploadPhotoFileToS3(requestBody);
                    }
                }).flatMap(new Func1<String, Observable<ImageConfirmResponseBean>>() {
                    @Override
                    public Observable<ImageConfirmResponseBean> call(String s) {
                        ImageConfirmRequestBean confirmBean = new ImageConfirmRequestBean(awsId);
                        return sHttpMainRequestManager.getHttpService().uploadUserAvatarConfirm(confirmBean);
                    }
                }), subscriber);


       /* observableAvatar.flatMap(new Func1<AvatarUploadResponseBean, Observable<String>>() {
            @Override
            public Observable<String> call(AvatarUploadResponseBean avatarUploadResponseBean) {
                RequestBody requestBody =
                        RequestBody.create(MediaType.parse("multipart/form-data"), mCropPhotoFile);
                awsId = avatarUploadResponseBean.data.uploadData.awsId;
                return HttpMainRequestManager.getInstance(avatarUploadResponseBean.data.uploadData.signUrl).getHttpService()
                        .uploadPhotoFileToS3(requestBody);
            }
        }).flatMap(new Func1<String, Observable<ImageConfirmResponseBean>>() {
            @Override
            public Observable<ImageConfirmResponseBean> call(String s) {
                ImageConfirmRequestBean confirmBean = new ImageConfirmRequestBean(awsId);
                return sHttpMainRequestManager.getHttpService().uploadUserAvatarConfirm(confirmBean);
            }
        }).subscribe(new Action1<ImageConfirmResponseBean>() {
            @Override
            public void call(ImageConfirmResponseBean imageConfirmResponseBean) {
                String url = imageConfirmResponseBean.data.url;
                Log.d(TAG, "======url======>" + url);
            }
        });*/
    }

}
