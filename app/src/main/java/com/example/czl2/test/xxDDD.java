package com.example.czl2.test;

import android.os.Parcel;
import android.os.Parcelable;

public class xxDDD implements Parcelable {

    int age;
    String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(age);
        parcel.writeString(name);
    }

    public static final Creator<xxDDD> CREATOR = new Creator<xxDDD>() {
        @Override
        public xxDDD createFromParcel(Parcel parcel) {
            return new xxDDD(parcel);
        }

        @Override
        public xxDDD[] newArray(int i) {
            return new xxDDD[i];
        }
    };

    public xxDDD(Parcel parcel) {
        this.age = parcel.readInt();
        this.name = parcel.readString();
    }
}
