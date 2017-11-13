package com.jwg.retrofit2test.model;

import java.util.ArrayList;

/*
登录进app所需数据
 */
public class SucLoginAllDataBean extends BaseResponseBean<SucLoginAllDataBean.LoginAllDataBean> {

    public class LoginAllDataBean {

        private ArrayList<PlanBean> plans = new ArrayList<PlanBean>();

        public ArrayList<PlanBean> getPlans() {
            return plans;
        }

        public void setPlans(ArrayList<PlanBean> plans) {
            this.plans = plans;
        }

    }
}
