package com.jwg.retrofit2test.util.network.server;

import com.jwg.retrofit2test.model.BaseResponseBean;
import com.jwg.retrofit2test.util.config.AppConfig;
import com.jwg.retrofit2test.util.network.manager.HttpMainRequestManager;
import rx.Subscriber;

public class UserRelativeRequestServer {

    private static HttpMainRequestManager sHttpMainRequestManager = HttpMainRequestManager.getInstance(AppConfig.XZL_DEBUG_BASE_URL);

    public static void DeleteUserRelativeChats(String userRelativeId, Subscriber<BaseResponseBean> subscriber) {
        sHttpMainRequestManager
                .doHttpRequest(sHttpMainRequestManager.getHttpService().deleteUserRelative(userRelativeId),
                        subscriber);
    }
}
