package com.jwg.retrofit2test.database.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {

    private static final String showAlarmTips = "showAlarmTips";
    private static final String completeInterUser = "completeInterUser";
    private static final String appActionLog = "appActionLog";
    private static final String showMedicPlanNotice = "showMedicPlanNotice";
    private static final String appLogoutFlag = "appLogoutFlag";                    //用户是否退出应用

    private static final String showAssistantNotice = "showAssistantNotice";        //显示问医生的红点
    private static final String showAppNotice = "showAppNotice";                    //显示大数据提醒的红点

    private static final String PEDOMETER_SENSOR_COUNT = "pedometer_sensor_count";  //计步器读取的数据
    private static final String PEDOMETER_STEP_COUNT = "pedometer_step_count";      //步数
    private static final String PEDOMETER_STEP_ANCHOR = "pedometer_step_anchor";    //起点
    private static final String PEDOMETER_UPDATE_TIME = "pedometer_update_time";    //最后一次记录的时间
    private static final String PEDOMETER_IS_UPLOAD = "pedometer_is_upload";        //是否上传过数据

    private static final String sh_data_name = "heart_data";
    private volatile static SharedPreferencesUtil uniqueInstance;
    private static SharedPreferences saveInfo;
    private static Editor saveEditor;

    private SharedPreferencesUtil() {
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (uniqueInstance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new SharedPreferencesUtil();
                    saveInfo = context.getSharedPreferences(sh_data_name, Context.MODE_PRIVATE);
                    saveEditor = saveInfo.edit();
                }
            }
        }
        return uniqueInstance;
    }

    public String getAppActionLog() {
        return saveInfo.getString(appActionLog, "");
    }

    public boolean putAppActionLog(String value) {
        saveEditor.putString(appActionLog, value);
        return saveEditor.commit();
    }

    public boolean showAssistantNotice() {
        return saveInfo.getBoolean(showAssistantNotice, false);
    }

    public boolean putShowAssistantNotice(boolean value) {
        saveEditor.putBoolean(showAssistantNotice, value);
        return saveEditor.commit();
    }

    public boolean showAppNotice() {
        return saveInfo.getBoolean(showAppNotice, false);
    }

    public boolean putShowAppNotice(boolean value) {
        saveEditor.putBoolean(showAppNotice, value);
        return saveEditor.commit();
    }

    public boolean showMedicPlanNotice() {
        return saveInfo.getBoolean(showMedicPlanNotice, false);
    }

    public boolean putShowMedicPlanNotice(boolean value) {
        saveEditor.putBoolean(showMedicPlanNotice, value);
        return saveEditor.commit();
    }

    public boolean appLogoutFlag() {
        return saveInfo.getBoolean(appLogoutFlag, true);
    }

    public boolean putAppLogoutFlag(boolean value) {
        saveEditor.putBoolean(appLogoutFlag, value);
        return saveEditor.commit();
    }

    public static void putPedometerStepCount(int stepCount) {
        saveEditor.putInt(PEDOMETER_STEP_COUNT, stepCount).apply();
    }

    public static int getPedometerStepCount() {
        return saveInfo.getInt(PEDOMETER_STEP_COUNT, 0);
    }

    public static void putPedometerStepAnchor(int stepAnchor) {
        saveEditor.putInt(PEDOMETER_STEP_ANCHOR, stepAnchor).apply();
    }

    public static int getPedometerStepAnchor() {
        return saveInfo.getInt(PEDOMETER_STEP_ANCHOR, 0);
    }

    public static void putPedometerUpdateTime(long updateTime) {
        saveEditor.putLong(PEDOMETER_UPDATE_TIME, updateTime).apply();
    }

    public static long getPedometerUpdateTime() {
        return saveInfo.getLong(PEDOMETER_UPDATE_TIME, 0);
    }

    public static void putPedometerIsUpload(boolean isUpload) {
        saveEditor.putBoolean(PEDOMETER_IS_UPLOAD, isUpload).apply();
    }

    public static boolean getPedometerIsUpload() {
        return saveInfo.getBoolean(PEDOMETER_IS_UPLOAD, false);
    }

    public static void putPedometerSensorCount(int sensorCount) {
        saveEditor.putInt(PEDOMETER_SENSOR_COUNT, sensorCount).apply();
    }

    public static int getPedometerSensorCount() {
        return saveInfo.getInt(PEDOMETER_SENSOR_COUNT, 0);
    }

    public boolean showAlarmTips() {
        return saveInfo.getBoolean(showAlarmTips, true);
    }

    public boolean putShowAlarmTips(boolean value) {
        saveEditor.putBoolean(showAlarmTips, value);
        return saveEditor.commit();
    }

    public boolean completeInterUser() {
        return saveInfo.getBoolean(completeInterUser, false);
    }

    public boolean putCompleteInterUser(boolean value) {
        saveEditor.putBoolean(completeInterUser, value);
        return saveEditor.commit();
    }

    public boolean clear() {
        saveEditor.clear();
        return saveEditor.commit();
    }
}
