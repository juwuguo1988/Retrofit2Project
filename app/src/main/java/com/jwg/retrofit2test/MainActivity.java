package com.jwg.retrofit2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.jwg.retrofit2test.database.sp.SharedPreferencesUtil;
import com.jwg.retrofit2test.database.sp.UserInfoUtils;
import com.jwg.retrofit2test.model.BaseResponseBean;
import com.jwg.retrofit2test.model.ChatBean;
import com.jwg.retrofit2test.model.SucLoginAllDataBean;
import com.jwg.retrofit2test.model.SucLoginBean;
import com.jwg.retrofit2test.model.SucScanSearchBean;
import com.jwg.retrofit2test.util.config.AppConfig;
import com.jwg.retrofit2test.util.network.server.LoginRequestServer;
import com.jwg.retrofit2test.util.network.server.MedicPlanRequestServer;
import com.jwg.retrofit2test.util.network.server.MedicineSummaryRequestServer;
import com.jwg.retrofit2test.util.network.server.UserChatRequestServer;
import com.jwg.retrofit2test.util.network.server.UserRelativeRequestServer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btn_get_message, btn_get_plan_message, btn_get_medic_summary,btn_add_user_chat,btn_delete_user_relative;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_get_message = (Button) findViewById(R.id.btn_get_message);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_get_plan_message = (Button) findViewById(R.id.btn_get_plan_message);
        btn_get_medic_summary = (Button) findViewById(R.id.btn_get_medic_summary);
        btn_add_user_chat = (Button) findViewById(R.id.btn_add_user_chat);
        btn_delete_user_relative = (Button) findViewById(R.id.btn_delete_user_relative);
        btn_get_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequestServer.loginToApp("password", "17710189466", "123456", "android", "1111111111111111111111",
                        new Subscriber<SucLoginBean>() {
                            @Override
                            public void onCompleted() {
                                Log.i("LHD", "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("LHD", "onError:  " + e.getMessage());
                            }

                            @Override
                            public void onNext(SucLoginBean mSLBean) {
                                Log.i("LHD", "onNext:  " + mSLBean.getAccess_token());
                                if (!mSLBean.getUid().isEmpty()) {
                                    loginSuccess(mSLBean);
                                }
                            }
                        });
            }
        });

        btn_get_plan_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicPlanRequestServer.getMedicPlans(new Subscriber<SucLoginAllDataBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SucLoginAllDataBean sucLoginAllDataBean) {
                        Log.e(TAG, "==========ddddd=======>" + sucLoginAllDataBean.data.getPlans().size());
                    }
                });
            }
        });

        btn_get_medic_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicineSummaryRequestServer.getMedicineSummary("6921056970038", new Subscriber<SucScanSearchBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(SucScanSearchBean sucScanSearchBean) {
                        Log.e(TAG, "==========ddddd=======>" + sucScanSearchBean.data.getMedicineSummaries().size());
                    }
                });
            }
        });
        btn_add_user_chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ChatBean mChatBean = new ChatBean();
                mChatBean.setReceiver(UserInfoUtils.getDoctorId(MainActivity.this));
                mChatBean.setType(0);
                mChatBean.setContent("你在哪，还好吗？");
                String jsonObj = new Gson().toJson(mChatBean);
                UserChatRequestServer.addUserChats(jsonObj, new Subscriber<BaseResponseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponseBean baseResponseBean) {
                          if(baseResponseBean.isSuccess()){
                              Log.e(TAG, "==========添加成功！！！=======>");
                          }
                    }
                });
            }
        });
        btn_delete_user_relative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserRelativeRequestServer.DeleteUserRelativeChats("rpoBN5", new Subscriber<BaseResponseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponseBean baseResponseBean) {
                        Log.e(TAG, "==========删除成功！！！=======>");
                    }
                });
            }
        });
    }

    private void loginSuccess(SucLoginBean bean) {
        SharedPreferencesUtil.getInstance(this).putAppLogoutFlag(false);
        UserInfoUtils.saveTokenInfo(this, bean);// 如有user_info.xml被删除的现象
        AppConfig.currentUserId = bean.getUid();
    }
}
