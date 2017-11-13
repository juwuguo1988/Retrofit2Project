package com.jwg.retrofit2test.model;

import java.io.Serializable;
import org.json.JSONObject;

public class KeyWordBean extends BaseBean<KeyWordBean> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;//药品id
    private String name;//药品名称
    private String company;//药品生产厂商
    private String uid;//用户id
    private String medicineHash;//加密字段
    private String dosageFormUnit;//剂型单位
    private String commodityName;//商品名
    private String strength;//剂量
    private String ingredient;//成份
    private String dosageForm;//剂型

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMedicineHash() {
        return medicineHash;
    }

    public void setMedicineHash(String medicineHash) {
        this.medicineHash = medicineHash;
    }

    public String getDosageFormUnit() {
        return dosageFormUnit;
    }

    public void setDosageFormUnit(String dosageFormUnit) {
        this.dosageFormUnit = dosageFormUnit;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public KeyWordBean parseJSON(JSONObject jsonObj) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }
}
