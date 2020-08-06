package com.example.mobilesngapp.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Job implements Parcelable {
    public String WorkPlanId;
    public String WorkReqCode;
    public String WorkReqName;
    public String Location;
    public String WorkKind;
    public String Status;
    public String Progress;
    public String ObjectName;
    public String WorkDescription;
    public String ApproachDuration;
    public String WorkDuration;
    public String ResourceEiTID;
    public String WorkPlanTimeRangeID;
    public String pWorkPlan;
    public String WorkStart;
    public String WorkEnd;
    public String Real;
    public String ProblemReason;
    public String WorkReqId;
    public String ProblemReasonName;
    public String ResourceName;
    public String C;
    public String D;

    public Job(String workPlanId, String workReqCode, String workReqName, String location, String workKind, String status, String progress, String objectName, String workDescription, String approachDuration, String workDuration, String resourceEiTID, String workPlanTimeRangeID, String pWorkPlan, String workStart, String workEnd, String real, String problemReason, String workReqId, String problemReasonName, String resourceName, String c, String d) {
        WorkPlanId = workPlanId;
        WorkReqCode = workReqCode;
        WorkReqName = workReqName;
        Location = location;
        WorkKind = workKind;
        Status = status;
        Progress = progress;
        ObjectName = objectName;
        WorkDescription = workDescription;
        ApproachDuration = approachDuration;
        WorkDuration = workDuration;
        ResourceEiTID = resourceEiTID;
        WorkPlanTimeRangeID = workPlanTimeRangeID;
        this.pWorkPlan = pWorkPlan;
        WorkStart = workStart;
        WorkEnd = workEnd;
        Real = real;
        ProblemReason = problemReason;
        WorkReqId = workReqId;
        ProblemReasonName = problemReasonName;
        ResourceName = resourceName;
        C = c;
        D = d;
    }


    protected Job(Parcel in) {
        WorkPlanId = in.readString();
        WorkReqCode = in.readString();
        WorkReqName = in.readString();
        Location = in.readString();
        WorkKind = in.readString();
        Status = in.readString();
        Progress = in.readString();
        ObjectName = in.readString();
        WorkDescription = in.readString();
        ApproachDuration = in.readString();
        WorkDuration = in.readString();
        ResourceEiTID = in.readString();
        WorkPlanTimeRangeID = in.readString();
        this.pWorkPlan = in.readString();
        WorkStart = in.readString();
        WorkEnd = in.readString();
        Real = in.readString();
        ProblemReason = in.readString();
        WorkReqId = in.readString();
        ProblemReasonName = in.readString();
        ResourceName = in.readString();
        C = in.readString();
        D = in.readString();
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

    public String getWorkPlanId() {
        return WorkPlanId;
    }

    public void setWorkPlanId(String workPlanId) {
        WorkPlanId = workPlanId;
    }

    public String getWorkReqCode() {
        return WorkReqCode;
    }

    public void setWorkReqCode(String workReqCode) {
        WorkReqCode = workReqCode;
    }

    public String getWorkReqName() {
        return WorkReqName;
    }

    public void setWorkReqName(String workReqName) {
        WorkReqName = workReqName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getWorkKind() {
        return WorkKind;
    }

    public void setWorkKind(String workKind) {
        WorkKind = workKind;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getProgress() {
        return Progress;
    }

    public void setProgress(String progress) {
        Progress = progress;
    }

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    public String getWorkDescription() {
        return WorkDescription;
    }

    public void setWorkDescription(String workDescription) {
        WorkDescription = workDescription;
    }

    public String getApproachDuration() {
        return ApproachDuration;
    }

    public void setApproachDuration(String approachDuration) {
        ApproachDuration = approachDuration;
    }

    public String getWorkDuration() {
        return WorkDuration;
    }

    public void setWorkDuration(String workDuration) {
        WorkDuration = workDuration;
    }

    public String getResourceEiTID() {
        return ResourceEiTID;
    }

    public void setResourceEiTID(String resourceEiTID) {
        ResourceEiTID = resourceEiTID;
    }

    public String getWorkPlanTimeRangeID() {
        return WorkPlanTimeRangeID;
    }

    public void setWorkPlanTimeRangeID(String workPlanTimeRangeID) {
        WorkPlanTimeRangeID = workPlanTimeRangeID;
    }

    public String getpWorkPlan() {
        return pWorkPlan;
    }

    public void setpWorkPlan(String pWorkPlan) {
        this.pWorkPlan = pWorkPlan;
    }

    public String getWorkStart() {
        return WorkStart;
    }

    public void setWorkStart(String workStart) {
        WorkStart = workStart;
    }

    public String getWorkEnd() {
        return WorkEnd;
    }

    public void setWorkEnd(String workEnd) {
        WorkEnd = workEnd;
    }

    public String getReal() {
        return Real;
    }

    public void setReal(String real) {
        Real = real;
    }

    public String getProblemReason() {
        return ProblemReason;
    }

    public void setProblemReason(String problemReason) {
        ProblemReason = problemReason;
    }

    public String getWorkReqId() {
        return WorkReqId;
    }

    public void setWorkReqId(String workReqId) {
        WorkReqId = workReqId;
    }

    public String getProblemReasonName() {
        return ProblemReasonName;
    }

    public void setProblemReasonName(String problemReasonName) {
        ProblemReasonName = problemReasonName;
    }

    public String getResourceName() {
        return ResourceName;
    }

    public void setResourceName(String resourceName) {
        ResourceName = resourceName;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(WorkPlanId);
        parcel.writeString(WorkReqCode);
        parcel.writeString(WorkReqName);
        parcel.writeString(Location);
        parcel.writeString(WorkKind);
        parcel.writeString(Status);
        parcel.writeString(Progress);
        parcel.writeString(ObjectName);
        parcel.writeString(WorkDescription);
        parcel.writeString(ApproachDuration);
        parcel.writeString(WorkDuration);
        parcel.writeString(ResourceEiTID);
        parcel.writeString(WorkPlanTimeRangeID);
        parcel.writeString(this.pWorkPlan);
        parcel.writeString(WorkStart);
        parcel.writeString(WorkEnd);
        parcel.writeString(Real);
        parcel.writeString(ProblemReason);
        parcel.writeString(WorkReqId);
        parcel.writeString(ProblemReasonName);
        parcel.writeString(ResourceName);
        parcel.writeString(C);
        parcel.writeString(D);
    }
}
