package com.jwg.retrofit2test.database.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jwg.retrofit2test.model.SucLoginBean;


public class UserInfoUtils {

    private static final String XML_USER_INFO = "user_info";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_NICKNAME = "nickName";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String KEY_DOCTOR_ID = "doctorId";
    private static final String TOKEN_EXPIRE_TIME = "token_expire_time";                        //token过期时间
    private static final String DOCTOR_REMINDER_CONTENT = "doctor_reminder_content";            //医生提醒最新一条记录
    private static final String DOCTOR_REMINDER_TIME = "doctor_reminder_time";                  //医生提醒最新一条记录的时间
    private static final String DOCTOR_REMINDER_DOCTOR_NAME = "doctor_reminder_doctor_name";    //医生提醒医生姓名
    private static final String DOCTOR_REMINDER_DOCTOR_TITLE = "doctor_reminder_doctor_title";  //医生提醒医生职位
    private static final String DOCTOR_REMINDER_DOCTOR_AVATAR = "doctor_reminder_doctor_avatar";//医生提醒医生头像
    private static final String APP_REMINDER_BEAN = "app_reminder_bean";                        //心之力提醒最新一条记录的内容
    private static final String MEDICAL_TAKE_RATE = "medical_take_rate";                        //服药达标率

    public static String getNickName(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(KEY_NICKNAME, "");
    }

    public static String getUserId(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(KEY_USER_ID, "");
    }

    public static String getAccessToken(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(ACCESS_TOKEN, "");
    }

    public static String getRefreshToken(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(REFRESH_TOKEN, "");
    }

    public static String getDoctorId(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(KEY_DOCTOR_ID, "");
    }

    public static String getTokenExpireTime(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(TOKEN_EXPIRE_TIME, "0");
    }

    public static String getDoctorReminderContent(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(DOCTOR_REMINDER_CONTENT, "");
    }

    public static String getDoctorReminderDoctorName(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(DOCTOR_REMINDER_DOCTOR_NAME, "");
    }

    public static String getDoctorReminderDoctorTitle(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(DOCTOR_REMINDER_DOCTOR_TITLE, "");
    }

    public static String getDoctorReminderDoctorAvatar(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(DOCTOR_REMINDER_DOCTOR_AVATAR, "");
    }

    public static Long getDoctorReminderTime(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getLong(DOCTOR_REMINDER_TIME, 0);
    }

    public static String getAppReminderBean(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getString(APP_REMINDER_BEAN, "");
    }

    public static int getMedicalTakeRate(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        return share.getInt(MEDICAL_TAKE_RATE, 0);
    }

    public static boolean saveTokenInfo(Context context, SucLoginBean bean) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        String accessToken = bean.getAccess_token();
        String refreshToken = bean.getRefresh_token();
        String userId = bean.getUid();
        String tokenExpireTime = String.valueOf(bean.getExpires_in() * 1000 + System.currentTimeMillis());
        if (accessToken != null) {
            edit.putString(ACCESS_TOKEN, accessToken);
        }
        if (refreshToken != null) {
            edit.putString(REFRESH_TOKEN, refreshToken);
        }
        if (userId != null) {
            edit.putString(KEY_USER_ID, userId);
        }
        edit.putString(TOKEN_EXPIRE_TIME, tokenExpireTime);
        edit.commit();
        return true;
    }

    public static boolean saveDoctorId(Context context, String doctorId) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        if (doctorId != null) {
            edit.putString(KEY_DOCTOR_ID, doctorId);
        }
        edit.commit();
        return true;
    }

    public static boolean saveDoctorReminder(Context context, String doctorReminder, long doctorReminderTime) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        if (doctorReminder != null) {
            edit.putString(DOCTOR_REMINDER_CONTENT, doctorReminder);
            edit.putLong(DOCTOR_REMINDER_TIME, doctorReminderTime);
        }
        edit.commit();
        return true;
    }

    public static boolean saveDoctorReminder(Context context, String doctorReminder, long doctorReminderTime,
            String doctorReminderDoctorName, String doctorReminderDoctorTitle, String doctorReminderDoctorAvatar) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString(DOCTOR_REMINDER_CONTENT, doctorReminder);
        edit.putLong(DOCTOR_REMINDER_TIME, doctorReminderTime);
        edit.putString(DOCTOR_REMINDER_DOCTOR_NAME, doctorReminderDoctorName);
        edit.putString(DOCTOR_REMINDER_DOCTOR_TITLE, doctorReminderDoctorTitle);
        edit.putString(DOCTOR_REMINDER_DOCTOR_AVATAR, doctorReminderDoctorAvatar);
        edit.commit();
        return true;
    }

    public static boolean saveAppReminderBean(Context context, String appReminderBean) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        if (!TextUtils.isEmpty(appReminderBean)) {
            editor.putString(APP_REMINDER_BEAN, appReminderBean);
        }
        return editor.commit();
    }

    public static boolean saveMedicalTakeRate(Context context, int takeRate) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putInt(MEDICAL_TAKE_RATE, takeRate);
        edit.commit();
        return true;
    }

    public static void clear(Context context) {
        SharedPreferences share = context.getSharedPreferences(XML_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.clear();
        edit.commit();
    }
}
