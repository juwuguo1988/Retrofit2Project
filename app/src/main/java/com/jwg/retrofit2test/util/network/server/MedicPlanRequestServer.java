package com.jwg.retrofit2test.util.network.server;

import com.jwg.retrofit2test.model.SucLoginAllDataBean;
import com.jwg.retrofit2test.util.config.AppConfig;
import com.jwg.retrofit2test.util.network.manager.HttpMainRequestManager;
import rx.Subscriber;

public class MedicPlanRequestServer {

    private static HttpMainRequestManager sHttpMainRequestManager = HttpMainRequestManager.getInstance(AppConfig.XZL_DEBUG_BASE_URL);

    public static void getMedicPlans(Subscriber<SucLoginAllDataBean> subscriber) {
        sHttpMainRequestManager.doHttpRequest(sHttpMainRequestManager.getHttpService().getALLMedicPlans(), subscriber);
    }
}
