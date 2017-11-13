package com.jwg.retrofit2test.util.network.server;

import com.jwg.retrofit2test.model.BaseResponseBean;
import com.jwg.retrofit2test.util.config.AppConfig;
import com.jwg.retrofit2test.util.network.manager.HttpMainRequestManager;
import rx.Subscriber;

public class UserChatRequestServer {

    private static HttpMainRequestManager sHttpLoginRequestManager = HttpMainRequestManager.getInstance(AppConfig.XZL_DEBUG_BASE_URL);

    public static void addUserChats(String jsonData, Subscriber<BaseResponseBean> subscriber) {
        sHttpLoginRequestManager
                .doHttpRequest(sHttpLoginRequestManager.getHttpService().addUserChat(sHttpLoginRequestManager.getPostBody(jsonData)),
                        subscriber);
    }
}