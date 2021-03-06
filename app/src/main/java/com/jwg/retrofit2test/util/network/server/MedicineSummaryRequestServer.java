package com.jwg.retrofit2test.util.network.server;

import com.jwg.retrofit2test.model.SucScanSearchBean;
import com.jwg.retrofit2test.util.config.AppConfig;
import com.jwg.retrofit2test.util.network.manager.HttpMainRequestManager;
import rx.Subscriber;

public class MedicineSummaryRequestServer {

    private static HttpMainRequestManager sHttpMainRequestManager = HttpMainRequestManager.getInstance(AppConfig.XZL_DEBUG_BASE_URL);

    public static void getMedicineSummary(String code, Subscriber<SucScanSearchBean> subscriber) {
        sHttpMainRequestManager.doHttpRequest(sHttpMainRequestManager.getHttpService().getMedicineSsummary(code), subscriber);
    }
}
