package com.jwg.retrofit2test.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.jwg.retrofit2test.R;
import com.jwg.retrofit2test.common.AppLication;
import com.jwg.retrofit2test.model.ImageConfirmResponseBean;
import com.jwg.retrofit2test.model.ImageUploadRequestBean;
import com.jwg.retrofit2test.util.config.AppConfig;
import com.jwg.retrofit2test.util.network.server.UserAvatarRequestServer;
import java.io.File;
import rx.Subscriber;

public class PhotoUploadActivity extends AppCompatActivity {

    private static final String TAG = "PhotoUploadActivity";
    private Button btn_upload_photo;
    private File mCropPhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_upload);
        btn_upload_photo = (Button) findViewById(R.id.btn_upload_photo);
        setListener();
    }

    private void setListener() {
        btn_upload_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCropPhotoFile = new File(AppLication.getContext().getExternalCacheDir(), "IMG_1484188750937.jpg");
                if (mCropPhotoFile.exists()) {
                    Log.e(TAG, "=====文件存在=====");
                    final ImageUploadRequestBean uploadBean = new ImageUploadRequestBean(mCropPhotoFile.getName(),
                            AppConfig.AVATAR_SCOPE_PATIENT);
                    UserAvatarRequestServer.uploadUserAvatarRoad(uploadBean, mCropPhotoFile, new Subscriber<ImageConfirmResponseBean>() {
                        @Override
                        public void onCompleted() {
                            Log.e("LHD", "onCompleted");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("LHD", "onError:  " + e.getMessage());
                        }

                        @Override
                        public void onNext(ImageConfirmResponseBean ImageConfirmResponseBean) {
                            String url = ImageConfirmResponseBean.data.url;
                            Log.e(TAG, "======url======>" + url);
                        }
                    });

                } else {
                    Log.e(TAG, "=====文件不存在=====");
                }

            }
        });
    }


}
