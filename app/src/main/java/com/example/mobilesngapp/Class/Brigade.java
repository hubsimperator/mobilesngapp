package com.example.mobilesngapp.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Brigade implements Parcelable {
    public String ChildResourceName;

    public Brigade(String childResourceName) {
        ChildResourceName = childResourceName;
    }

    protected Brigade(Parcel in) {
        ChildResourceName = in.readString();
    }

    public static final Creator<Brigade> CREATOR = new Creator<Brigade>() {
        @Override
        public Brigade createFromParcel(Parcel in) {
            return new Brigade(in);
        }

        @Override
        public Brigade[] newArray(int size) {
            return new Brigade[size];
        }
    };

    public String getChildResourceName() {
        return ChildResourceName;
    }

    public void setChildResourceName(String childResourceName) {
        ChildResourceName = childResourceName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ChildResourceName);
    }
}
