package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class BusinessLinkCardPageObject extends BaseMediaObject {
    public static final Creator<BusinessLinkCardPageObject> CREATOR = new Creator<BusinessLinkCardPageObject>() {
        public BusinessLinkCardPageObject createFromParcel(Parcel parcel) {
            return new BusinessLinkCardPageObject(parcel);
        }

        public BusinessLinkCardPageObject[] newArray(int i) {
            return new BusinessLinkCardPageObject[i];
        }
    };
    public String[] nineImageLocalPath;
    public String[] nineImageUrlPath;
    public byte[] nineThumbData_1;
    public byte[] nineThumbData_2;
    public byte[] nineThumbData_3;
    public byte[] nineThumbData_4;
    public byte[] nineThumbData_5;
    public byte[] nineThumbData_6;
    public byte[] nineThumbData_7;
    public byte[] nineThumbData_8;
    public byte[] nineThumbData_9;

    public BusinessLinkCardPageObject() {
        this.nineImageUrlPath = new String[9];
        this.nineImageLocalPath = new String[9];
    }

    public BusinessLinkCardPageObject(Parcel parcel) {
        this.nineImageUrlPath = new String[9];
        this.nineImageLocalPath = new String[9];
        this.nineImageUrlPath = parcel.createStringArray();
        this.nineImageLocalPath = parcel.createStringArray();
        this.nineThumbData_1 = parcel.createByteArray();
        this.nineThumbData_2 = parcel.createByteArray();
        this.nineThumbData_3 = parcel.createByteArray();
        this.nineThumbData_4 = parcel.createByteArray();
        this.nineThumbData_5 = parcel.createByteArray();
        this.nineThumbData_6 = parcel.createByteArray();
        this.nineThumbData_7 = parcel.createByteArray();
        this.nineThumbData_8 = parcel.createByteArray();
        this.nineThumbData_9 = parcel.createByteArray();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(this.nineImageUrlPath);
        parcel.writeStringArray(this.nineImageLocalPath);
        parcel.writeByteArray(this.nineThumbData_1);
        parcel.writeByteArray(this.nineThumbData_2);
        parcel.writeByteArray(this.nineThumbData_3);
        parcel.writeByteArray(this.nineThumbData_4);
        parcel.writeByteArray(this.nineThumbData_5);
        parcel.writeByteArray(this.nineThumbData_6);
        parcel.writeByteArray(this.nineThumbData_7);
        parcel.writeByteArray(this.nineThumbData_8);
        parcel.writeByteArray(this.nineThumbData_9);
    }

    public boolean checkArgs() {
        if (super.checkArgs()) {
            return true;
        }
        return false;
    }

    public int getObjType() {
        return 8;
    }

    protected BaseMediaObject toExtraMediaObject(String str) {
        return null;
    }

    protected String toExtraMediaString() {
        return "";
    }

    public String[] getNineImageUrlPath() {
        return this.nineImageUrlPath;
    }

    public void setNineImageUrlPath(String[] strArr) {
        this.nineImageUrlPath = strArr;
    }

    public String[] getNineImageLocalPath() {
        return this.nineImageLocalPath;
    }

    public void setNineImageLocalPath(String[] strArr) {
        this.nineImageLocalPath = strArr;
    }

    public byte[] getNineThumbData_1() {
        return this.nineThumbData_1;
    }

    public void setNineThumbData_1(byte[] bArr) {
        this.nineThumbData_1 = bArr;
    }

    public byte[] getNineThumbData_2() {
        return this.nineThumbData_2;
    }

    public void setNineThumbData_2(byte[] bArr) {
        this.nineThumbData_2 = bArr;
    }

    public byte[] getNineThumbData_3() {
        return this.nineThumbData_3;
    }

    public void setNineThumbData_3(byte[] bArr) {
        this.nineThumbData_3 = bArr;
    }

    public byte[] getNineThumbData_4() {
        return this.nineThumbData_4;
    }

    public void setNineThumbData_4(byte[] bArr) {
        this.nineThumbData_4 = bArr;
    }

    public byte[] getNineThumbData_5() {
        return this.nineThumbData_5;
    }

    public void setNineThumbData_5(byte[] bArr) {
        this.nineThumbData_5 = bArr;
    }

    public byte[] getNineThumbData_6() {
        return this.nineThumbData_6;
    }

    public void setNineThumbData_6(byte[] bArr) {
        this.nineThumbData_6 = bArr;
    }

    public byte[] getNineThumbData_7() {
        return this.nineThumbData_7;
    }

    public void setNineThumbData_7(byte[] bArr) {
        this.nineThumbData_7 = bArr;
    }

    public byte[] getNineThumbData_8() {
        return this.nineThumbData_8;
    }

    public void setNineThumbData_8(byte[] bArr) {
        this.nineThumbData_8 = bArr;
    }

    public byte[] getNineThumbData_9() {
        return this.nineThumbData_9;
    }

    public void setNineThumbData_9(byte[] bArr) {
        this.nineThumbData_9 = bArr;
    }
}
