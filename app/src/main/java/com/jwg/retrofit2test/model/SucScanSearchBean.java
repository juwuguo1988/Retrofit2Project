package com.jwg.retrofit2test.model;

import java.util.List;

/*
扫描结果
 */
public class SucScanSearchBean extends BaseResponseBean<SucScanSearchBean.ScanSearchBean> {

    public class ScanSearchBean {
        private List<List<KeyWordBean>> medicineSummaries;

        public List<List<KeyWordBean>> getMedicineSummaries() {
            return medicineSummaries;
        }

        public void setMedicineSummaries(List<List<KeyWordBean>> medicineSummaries) {
            this.medicineSummaries = medicineSummaries;
        }
    }
}
