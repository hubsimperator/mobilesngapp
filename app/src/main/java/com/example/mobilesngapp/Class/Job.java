package com.example.mobilesngapp.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Job implements Parcelable {
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

    protected Job(Parcel in) {
        if (in.readByte() == 0) {
            WorkPlanId = null;
        } else {
            WorkPlanId = in.readInt();
        }
        WorkStart = in.readString();
        Duration = in.readString();
        WorkName = in.readString();
        NBR = in.readString();
        Desig = in.readString();
        JobStart = in.readString();
        JobEnd = in.readString();
        JobMinStart = in.readString();
        JobMaxStart = in.readString();
        SNGUser = in.readString();
        Dzial = in.readString();
        Status = in.readString();
        if (in.readByte() == 0) {
            StatusId = null;
        } else {
            StatusId = in.readInt();
        }
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (WorkPlanId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(WorkPlanId);
        }
        parcel.writeString(WorkStart);
        parcel.writeString(Duration);
        parcel.writeString(WorkName);
        parcel.writeString(NBR);
        parcel.writeString(Desig);
        parcel.writeString(JobStart);
        parcel.writeString(JobEnd);
        parcel.writeString(JobMinStart);
        parcel.writeString(JobMaxStart);
        parcel.writeString(SNGUser);
        parcel.writeString(Dzial);
        parcel.writeString(Status);
        if (StatusId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(StatusId);
        }
    }
}
