package com.jwg.retrofit2test.util.network;


import com.jwg.retrofit2test.model.AvatarUploadResponseBean;
import com.jwg.retrofit2test.model.BaseResponseBean;
import com.jwg.retrofit2test.model.ChatBean;
import com.jwg.retrofit2test.model.ImageConfirmRequestBean;
import com.jwg.retrofit2test.model.ImageConfirmResponseBean;
import com.jwg.retrofit2test.model.ImageUploadRequestBean;
import com.jwg.retrofit2test.model.SucLoginAllDataBean;
import com.jwg.retrofit2test.model.SucLoginBean;
import com.jwg.retrofit2test.model.SucScanSearchBean;
import com.jwg.retrofit2test.model.WeatherBean;
import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * http://blog.csdn.net/qq_26787115/article/details/53034267
 */
public interface HttpRequestService {

    /**这里介绍一些常用的注解的使用
     * @Query、@QueryMap：用于Http Get请求传递参数
     @Field：用于Post方式传递参数,需要在请求接口方法上添加@FormUrlEncoded,即以表单的方式传递参数
     @Body：用于Post,根据转换方式将实例对象转化为对应字符串传递参数.比如Retrofit添加GsonConverterFactory则是将body转化为gson字符串进行传递
     @Path：用于URL上占位符
     @Part：配合@Multipart使用,一般用于文件上传
     @Header：添加http header
     @Headers：跟@Header作用一样,只是使用方式不一样,@Header是作为请求方法的参数传入,@Headers是以固定方式直接添加到请求方法上
     */


    /**
     *
     * @param grantType
     * @param username
     * @param password
     * @param deviceType
     * @param deviceToken
     * @return
     */
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/token")
    @FormUrlEncoded
    Observable<SucLoginBean> userLoginAction(
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password,
            @Field("device_type") String deviceType,
            @Field("device_token") String deviceToken
    );

    @GET("patient/plan")
    Observable<SucLoginAllDataBean> getALLMedicPlans();

    //根据商品码查询药品信息
    @GET("patient/medicine/summary")
    Observable<SucScanSearchBean> getMedicineSsummary(@Query("code") String code);

    //添加用户聊天
    @POST("patient/chat")
    Observable<BaseResponseBean> addUserChat(@Body RequestBody datas);

    //添加用户聊天
    @POST("patient/chat")
    Observable<BaseResponseBean> addUserChatBean(@Body ChatBean mChatBean);

    //上传用户头像
    @POST("patient/user/avatar")
    Observable<AvatarUploadResponseBean> uploadUserAvatarBean(@Body ImageUploadRequestBean imageUploadRequestBean);

    @PUT//没有数据就写.或者/
    @Headers("Content-Type: image/jpeg")
    @Multipart
    Observable<String> uploadPhotoFileToS3(@Url String url,@Part("file\"; filename=\"image.jpg\"") RequestBody img);

    //上传用户头像确认
    @PATCH("patient/user/avatar")
    Observable<ImageConfirmResponseBean> uploadUserAvatarConfirm(@Body ImageConfirmRequestBean confirmBean);

    //删除用户亲友
    @DELETE("patient/user/relative/{id}")
    Observable<BaseResponseBean> deleteUserRelative(@Path("id") String id);


    //测试加解密
    @POST("/api/wifi/sync")
    Call<String> uploadWifi(@Body RequestBody datas);

    @GET("/api/wifi/getWifiPass")
    Call<String> downloadWifi();

    //统计服务器     反馈的接口
    @POST("/manage-weshare/device/addFeedback")
    Call<String> uploadFeedbackInfo(@Body RequestBody body);

    //3.3 上传头像修改信息
    @GET("/weshare/ren/gtx/{iconfileid}/{userid}")
    Observable<Integer> saveUserIcon(@Path("iconfileid") String iconpath, @Path("userid") String userid);

    // @GET("onebox/weather/query?cityname=深圳")
    @GET("onebox/weather/query?")
    Call<WeatherBean> getWeather(@QueryMap Map<String, String> params);

    //上传头像
    @Multipart
    @POST("https://global.18wifibank.com/api/wezz/slai")
    Observable<String> uploadUserIcon(
            @Part("describe") String describe,
            @Part("type") String type,
            @Part("userid") String userid,
            @Part("fileName") RequestBody description,
            @Part("file\"; filename=\"image.png\"") RequestBody img
    );

    //1.1保存用户信息
    @POST("api/user/save")
    Observable<String> saveUserInfo(@Body RequestBody body);
    //todo 1.2查询用户信息

    //2.1 获取我的未读取私信个数接口
    @POST("api/msg/count/{userid}")
    Observable<String> getUnreadMsgCount(@Path("userid") String userid);

    //2.2 获取我的私信列表接口
    @POST("api/msg/myrecied")
    Observable<String> getMyPrivateLetter(@Body RequestBody body);

    //todo 2.3 标识已读私信接口
    @GET("api/msg/setread/{msgid}/{readuser}")
    Observable<String> getReaded(@Path("msgid") String msgid, @Path("readuser") String readuser);

    //todo 2.4 根据原图ID查询对应模板ID接口
    @GET("api/templet/qtmpid/{parentid}")
    Observable<String> getModeIdByPicId(@Path("parentid") String parentid);

    //todo 2.5 根据原图id 查询模板以及对应资源IDs 接口
    @GET("api/templet/qtempinfo/{parentid}")
    Observable<String> getResourceIdByPicId(@Path("parentid") String parentid);

    //todo 2.6 根据模板id 查询模板以及对应资源IDs 接口
    @GET("api/templet/qtempinfox/{parentid}")
    Observable<String> getResourceIdByModeId(@Path("parentid") String parentid);

    //todo 3.1 我的照片信息接口
    @POST("api/myphoto/pts")
    Observable<String> getMyPicInfoByFBID(@Body RequestBody body);

    //4.1  图片，语言信息 流上传
//    userid	上传用户的facebookid	String	f_+fbid
//    uploadType	上传文件类型(打包文件是子文件传2;原图上传是父文件传1)	int
//    context	文本内容(子模板文件传NUll)	String	原图上传的时候带文本内容
//    tempId	子类文件不传，父类文件上传对应的模板id	String
    @Multipart
    @POST("api/file/upload")
    Observable<String> upLoadPicInfo(
            @Part("userid") String facebookid,
            @Part("uploadType") String type,
            @Part("context") String content,
            @Part("tempId") String tempId
    );

    //11111   http://japi.juhe.cn/funny/type.from
    //KEY: efb683a6adee94720e27bc481629004e
    //笑话大全
    @GET("type.from?key=efb683a6adee94720e27bc481629004e")
    Observable<String> getComics();


}