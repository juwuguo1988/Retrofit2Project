package com.jwg.retrofit2test.util.network.server;

import com.jwg.retrofit2test.model.SucLoginBean;
import com.jwg.retrofit2test.util.network.manager.HttpLoginRequestManager;
import com.jwg.retrofit2test.util.config.AppConfig;
import rx.Subscriber;

public class LoginRequestServer {
    public static void loginToApp(String grant_type, String username, String password,
            String deviceType, String deviceToken, Subscriber<SucLoginBean> subscriber) {
        HttpLoginRequestManager.getInstance(AppConfig.XZL_DEBUG_BASE_URL)
                .doHttpRequest(HttpLoginRequestManager.getInstance(AppConfig.XZL_DEBUG_BASE_URL).getHttpService()
                        .userLoginAction(grant_type, username, password, deviceType, deviceToken), subscriber);
    }
}
