package com.jwg.retrofit2test.model;

import java.io.Serializable;
import org.json.JSONObject;

public class PlanBean extends BaseBean<PlanBean> implements Serializable, Comparable<PlanBean> {
    private static final long serialVersionUID = 1L;
    private int takeAt;// 提醒时间
    private int cycleDays;// 服药计划频率（单位：天数, 默认为0-每天服药，1-隔一天一服，-1 - 只吃一次的）
    private String medicineId;// 药品id
    private int count;// 单次服药数量
    private int positionNo;// 药仓号
    private int dosage;// 单次服药剂量
    private int zone;// 早中晚区分 0:早晨 1:中午 2:晚上
    private String medicineName;// 药品名
    private String id; // 服药计划id;
    private Long started;// 服药计划开始时间
    private Long ended;// 服药计划开始时间
    private Long middleTime;// takeAt+昨天的date或者今天的date拼接而来，用于判断是否生成一条服药历史
    private boolean isSelected;// 已选，未选
    private String dosageUnit;//药品单位
    private int medicineVia;//药品信息来源（1 是扫码 2是手工输入 默认2）
    private String medicineHash;//药品信息的哈希值
    private Long remindFirstAt;//首次提醒时间

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getTakeAt() {
        return takeAt;
    }

    public void setTakeAt(int takeAt) {
        this.takeAt = takeAt;
    }

    public int getCycleDays() {
        return cycleDays;
    }

    public void setCycleDays(int cycleDays) {
        this.cycleDays = cycleDays;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPositionNo() {
        return positionNo;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public void setPositionNo(int positionNo) {
        this.positionNo = positionNo;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getMiddleTime() {
        return middleTime;
    }

    public void setMiddleTime(long middleTime) {
        this.middleTime = middleTime;
    }

    public Long getEnded() {
        return ended;
    }

    public void setEnded(Long ended) {
        this.ended = ended;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public int getMedicineVia() {
        return medicineVia;
    }

    public void setMedicineVia(int medicineVia) {
        this.medicineVia = medicineVia;
    }

    public String getMedicineHash() {
        return medicineHash;
    }

    public void setMedicineHash(String medicineHash) {
        this.medicineHash = medicineHash;
    }

    public Long getRemindFirstAt() {
        return remindFirstAt;
    }

    public void setRemindFirstAt(Long remindFirstAt) {
        this.remindFirstAt = remindFirstAt;
    }


    @Override
    public PlanBean parseJSON(JSONObject jsonObj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject toJSON() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int compareTo(PlanBean another) {
        try {
            if (this.takeAt < another.takeAt) {
                return -1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
