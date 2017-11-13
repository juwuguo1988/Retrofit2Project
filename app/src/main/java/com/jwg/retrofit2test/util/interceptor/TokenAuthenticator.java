package com.jwg.retrofit2test.util.interceptor;

import android.content.Context;
import android.text.TextUtils;
import com.jwg.retrofit2test.common.AppLication;
import com.jwg.retrofit2test.database.sp.UserInfoUtils;
import com.jwg.retrofit2test.model.SucLoginBean;
import com.jwg.retrofit2test.util.config.AppConfig;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by Juwuguo on 06/02/2017.
 */
public class TokenAuthenticator implements Authenticator {

    private Context mContext = AppLication.getContext();

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        //如果是Refresh_Token也过期了，那么重新登录
        if ("Basic cGF0aWVudF9hcHA6".equals(response.request().header("Authorization"))) {
            //重新登录
            return null;
        }
        //同步请求方式，获取最新的Token
        String accessToken = getNewAccessToken();
        if (!TextUtils.isEmpty(accessToken)) {
            //使用新的Token，创建新的请求
            return response.request().newBuilder().header("Authorization", "Bearer " + accessToken).build();
        } else {
            //重新登录
            return null;
        }
    }

    /**
     * 通过RT获取AT
     */
    private String getNewAccessToken() {
        String accessToken = null;
        String jsonData = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "refresh_token")
                .add("refresh_token", UserInfoUtils.getRefreshToken(mContext))
                .build();
        Request request = new Request.Builder()
                .url(AppConfig.WEB_SERVICE_TOKEN_URL)
                .header("Authorization", "Basic cGF0aWVudF9hcHA6")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response getAccessTokenResponse = call.execute();
            jsonData = getAccessTokenResponse.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SucLoginBean bean = new Gson().fromJson(jsonData, SucLoginBean.class);
        if (bean != null && bean.getError() == null && !"error".equals(bean.getStatus()) && !"fail".equals(bean.getStatus())) {
            UserInfoUtils.saveTokenInfo(mContext, bean);
            accessToken = UserInfoUtils.getAccessToken(mContext);
        }
        return accessToken;
    }
}
