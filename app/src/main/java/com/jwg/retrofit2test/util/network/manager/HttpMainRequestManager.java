package com.jwg.retrofit2test.util.network.manager;


import android.content.Context;
import com.jwg.retrofit2test.common.AppLication;
import com.jwg.retrofit2test.database.sp.UserInfoUtils;
import com.jwg.retrofit2test.util.network.HttpRequestService;
import com.jwg.retrofit2test.util.interceptor.TokenAuthenticator;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpMainRequestManager {
    private Context mContext;

    public String HOST = "";
    private static final int TIMEOUT = 10;
    protected HttpRequestService httpService;
    private volatile static HttpMainRequestManager sHttpMainRequestManager;

    protected HttpMainRequestManager(String host) {
        this.HOST = host;
        mContext = AppLication.getContext();
        //新建一个文件用来缓存网络请求
        File cacheDirectory = new File(mContext.getCacheDir().getAbsolutePath(), "HttpCache");
        //实例化一个OkHttpClient.Builder
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置连接超时
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        //设置从主机读信息超时
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        //设置写信息超时
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        //设置缓存文件
        builder.cache(new Cache(cacheDirectory, 10 * 1024 * 1024));
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + UserInfoUtils.getAccessToken(mContext))
                        .build();
                return chain.proceed(request);
            }
        });

        //拦截器
        builder.authenticator(new TokenAuthenticator());

        Retrofit.Builder rBuilder = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())   //http://blog.csdn.net/u013003052/article/details/50992436
                .addConverterFactory(GsonConverterFactory.create())      //此处顺序不能和上面对调，否则不能同时兼容普通字符串和Json格式字符串
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getHost());
        httpService = rBuilder.build().create(HttpRequestService.class);

//        使用Retrofit.Builder对象可以随时调节配置
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl(HOST)
//                .build();
//        httpService = retrofit.create(HttpService.class);
    }

    /**
     * 如果有不同的请求HOST可继承此类并Override
     *
     * @return
     */
    protected String getHost() {
        return HOST;
    }

    public HttpRequestService getHttpService() {
        return httpService;
    }

    public static HttpMainRequestManager getInstance(String host) {
        if (sHttpMainRequestManager == null) {
            synchronized (HttpMainRequestManager.class) {
                if (sHttpMainRequestManager == null) {
                    sHttpMainRequestManager = new HttpMainRequestManager(host);
                }
            }
        }
        return sHttpMainRequestManager;
    }

    /**
     * 处理http请求——常规
     *
     * @param pCall
     * @param pCallback
     */
    public void doHttpRequest(Call pCall, Callback pCallback) {
        Call call = pCall;
        call.enqueue(pCallback);
    }

    /**
     * 处理http请求——RX
     *
     * @param pObservable
     * @param pSubscriber
     */
    public void doHttpRequest(Observable pObservable, Subscriber pSubscriber) {
        Observable observable = pObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(pSubscriber);
    }

    /**
     * json方式传参
     *
     * @param pBody
     * @return
     */
    public RequestBody getPostBody(String pBody) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), pBody);
        return body;
    }

}
