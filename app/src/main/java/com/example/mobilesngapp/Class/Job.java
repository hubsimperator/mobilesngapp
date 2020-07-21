package com.example.mobilesngapp.Class;

public class Job {
    public Integer WorkPlanId;
    public String WorkStart;
    public String Duration;
    public String WorkName;
    public String NBR;
    public String Desig;
    public String JobStart;
    public String JobEnd;
    public String JobMinStart;
    public String JobMaxStart;
    public String SNGUser;
    public String Dzial;
    public String Status;
    public Integer StatusId;

    public Job(Integer workPlanId, String workStart, String duration, String workName, String NBR, String desig, String jobStart, String jobEnd, String jobMinStart, String jobMaxStart, String SNGUser, String dzial, String status, Integer statusId) {
        WorkPlanId = workPlanId;
        WorkStart = workStart;
        Duration = duration;
        WorkName = workName;
        this.NBR = NBR;
        Desig = desig;
        JobStart = jobStart;
        JobEnd = jobEnd;
        JobMinStart = jobMinStart;
        JobMaxStart = jobMaxStart;
        this.SNGUser = SNGUser;
        Dzial = dzial;
        Status = status;
        StatusId = statusId;
    }

    public Integer getWorkPlanId() {
        return WorkPlanId;
    }

    public void setWorkPlanId(Integer workPlanId) {
        WorkPlanId = workPlanId;
    }

    public String getWorkStart() {
        return WorkStart;
    }

    public void setWorkStart(String workStart) {
        WorkStart = workStart;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getWorkName() {
        return WorkName;
    }

    public void setWorkName(String workName) {
        WorkName = workName;
    }

    public String getNBR() {
        return NBR;
    }

    public void setNBR(String NBR) {
        this.NBR = NBR;
    }

    public String getDesig() {
        return Desig;
    }

    public void setDesig(String desig) {
        Desig = desig;
    }

    public String getJobStart() {
        return JobStart;
    }

    public void setJobStart(String jobStart) {
        JobStart = jobStart;
    }

    public String getJobEnd() {
        return JobEnd;
    }

    public void setJobEnd(String jobEnd) {
        JobEnd = jobEnd;
    }

    public String getJobMinStart() {
        return JobMinStart;
    }

    public void setJobMinStart(String jobMinStart) {
        JobMinStart = jobMinStart;
    }

    public String getJobMaxStart() {
        return JobMaxStart;
    }

    public void setJobMaxStart(String jobMaxStart) {
        JobMaxStart = jobMaxStart;
    }

    public String getSNGUser() {
        return SNGUser;
    }

    public void setSNGUser(String SNGUser) {
        this.SNGUser = SNGUser;
    }

    public String getDzial() {
        return Dzial;
    }

    public void setDzial(String dzial) {
        Dzial = dzial;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getStatusId() {
        return StatusId;
    }

    public void setStatusId(Integer statusId) {
        StatusId = statusId;
    }
}
