package com.here.posclient;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ClientConfiguration implements Parcelable {
    public static final Creator<ClientConfiguration> CREATOR = new Creator<ClientConfiguration>() {
        public ClientConfiguration createFromParcel(Parcel parcel) {
            return new ClientConfiguration(parcel);
        }

        public ClientConfiguration[] newArray(int i) {
            return new ClientConfiguration[i];
        }
    };
    public String clientId;
    public long features;
    public String rmdBaseUrl;
    public String rmdHighAccaryUrl;
    public String rmdServerAddress;
    public String slpServerAddress;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.clientId);
        parcel.writeLong(this.features);
        parcel.writeString(this.rmdServerAddress);
        parcel.writeString(this.rmdBaseUrl);
        parcel.writeString(this.rmdHighAccaryUrl);
        parcel.writeString(this.slpServerAddress);
    }

    private ClientConfiguration(Parcel parcel) {
        this.clientId = parcel.readString();
        this.features = parcel.readLong();
        this.rmdServerAddress = parcel.readString();
        this.rmdBaseUrl = parcel.readString();
        this.rmdHighAccaryUrl = parcel.readString();
        this.slpServerAddress = parcel.readString();
    }

    protected ClientConfiguration() {
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ClientConfiguration [");
        stringBuilder.append("clientId(").append(this.clientId);
        stringBuilder.append(") features(0x").append(Long.toHexString(this.features));
        stringBuilder.append(") rmdSA(").append(this.rmdServerAddress);
        stringBuilder.append(") rmdBU(").append(this.rmdHighAccaryUrl);
        stringBuilder.append(") rmdHAU(").append(this.rmdHighAccaryUrl);
        stringBuilder.append(") slpSA(").append(this.slpServerAddress);
        return stringBuilder.append(")]").toString();
    }
}
