package com.here.posclient;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.here.odnp.posclient.pos.IPositioningSession;
import com.here.services.common.Types.Source;
import com.here.services.common.Types.Technology;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

public class UpdateOptions implements Parcelable, Cloneable {
    public static final Creator<UpdateOptions> CREATOR = new Creator<UpdateOptions>() {
        public UpdateOptions createFromParcel(Parcel parcel) {
            final UpdateOptions updateOptions = new UpdateOptions();
            updateOptions.idleMode = UpdateOptions.readBoolean(parcel);
            UpdateOptions.readOptionalLong(parcel, new ValueHandler<Long>() {
                public void handleValue(Long l) {
                    updateOptions.setAllowedSources(l.longValue());
                }
            });
            UpdateOptions.readOptionalLong(parcel, new ValueHandler<Long>() {
                public void handleValue(Long l) {
                    updateOptions.setAllowedTechnologies(l.longValue());
                }
            });
            UpdateOptions.readOptionalLong(parcel, new ValueHandler<Long>() {
                public void handleValue(Long l) {
                    updateOptions.setDesiredUpdateInterval(l.longValue());
                }
            });
            UpdateOptions.readOptionalLong(parcel, new ValueHandler<Long>() {
                public void handleValue(Long l) {
                    updateOptions.setSmallestUpdateInterval(l.longValue());
                }
            });
            UpdateOptions.readOptionalLong(parcel, new ValueHandler<Long>() {
                public void handleValue(Long l) {
                    updateOptions.setOptions(l.longValue());
                }
            });
            return updateOptions;
        }

        public UpdateOptions[] newArray(int i) {
            return new UpdateOptions[i];
        }
    };
    private static final String KEY_BUNDLED_INSTANCE = "com.here.posclient.UpdateOptions";
    public static final long OPTION_ENABLE_EXTERNAL_FOR_REFERENCE = 128;
    public static final long OPTION_NONE = 0;
    public static final long OPTION_ONLINE_FOR_FREE_CELLULAR = 16;
    public static final long OPTION_ONLINE_FOR_FREE_WLAN = 32;
    public static final long OPTION_RMD_COARSE_OVER_CELLULAR = 2;
    public static final long OPTION_RMD_COARSE_OVER_WLAN = 1;
    public static final long OPTION_RMD_DETAILED_OVER_CELLULAR = 8;
    public static final long OPTION_RMD_DETAILED_OVER_WLAN = 4;
    public static final long OPTION_WAKING_MSM_TIMER = 64;
    public static final long SOURCE_ANY = 2147483647L;
    public static final long SOURCE_CACHE = 16;
    public static final long SOURCE_EXTERNAL = 64;
    public static final long SOURCE_FUSION = 128;
    public static final long SOURCE_HAPL = 8;
    public static final long SOURCE_LAST_KNOWN = 1;
    private static final long SOURCE_LEARNING = 32;
    public static final long SOURCE_OFFLINE = 4;
    public static final long SOURCE_ONLINE = 2;
    public static final long SOURCE_UNSPECIFIED = 0;
    public static final long TECHNOLOGY_ALL = 32767;
    public static final long TECHNOLOGY_BLE = 16384;
    public static final long TECHNOLOGY_CELL = 4;
    public static final long TECHNOLOGY_CELLULAR = 12;
    public static final long TECHNOLOGY_CELLULAR_OBJECT = 15472;
    public static final long TECHNOLOGY_COUNTRY = 64;
    public static final long TECHNOLOGY_ECELL = 8;
    public static final long TECHNOLOGY_ENODEB = 4096;
    public static final long TECHNOLOGY_GNSS = 1;
    public static final long TECHNOLOGY_IP = 128;
    public static final long TECHNOLOGY_LOCATION_AREA = 16;
    public static final long TECHNOLOGY_MAP = 512;
    public static final long TECHNOLOGY_NETWORK = 32;
    public static final long TECHNOLOGY_RNC = 2048;
    public static final long TECHNOLOGY_SATELLITES = 32768;
    public static final long TECHNOLOGY_SENSORS = 256;
    public static final long TECHNOLOGY_SYSTEM = 8192;
    public static final long TECHNOLOGY_TRACKING_AREA = 1024;
    public static final long TECHNOLOGY_UNSPECIFIED = 0;
    public static final long TECHNOLOGY_WLAN = 2;
    public long allowedSources = 0;
    public boolean allowedSourcesSet;
    public long allowedTechnologies = 0;
    public boolean allowedTechnologiesSet;
    public long desiredUpdateInterval;
    public boolean desiredUpdateIntervalSet;
    public boolean idleMode;
    public long options;
    public boolean optionsSet;
    public long smallestUpdateInterval;
    public boolean smallestUpdateIntervalSet;

    interface ValueHandler<T> {
        void handleValue(T t);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        writeBoolean(parcel, this.idleMode);
        writeOptionalLong(parcel, this.allowedSourcesSet, this.allowedSources);
        writeOptionalLong(parcel, this.allowedTechnologiesSet, this.allowedTechnologies);
        writeOptionalLong(parcel, this.desiredUpdateIntervalSet, this.desiredUpdateInterval);
        writeOptionalLong(parcel, this.smallestUpdateIntervalSet, this.smallestUpdateInterval);
        writeOptionalLong(parcel, this.optionsSet, this.options);
    }

    static void readOptionalInt(Parcel parcel, ValueHandler<Integer> valueHandler) {
        if (parcel.readByte() != (byte) 0) {
            valueHandler.handleValue(Integer.valueOf(parcel.readInt()));
        }
    }

    static void readOptionalLong(Parcel parcel, ValueHandler<Long> valueHandler) {
        if (parcel.readByte() != (byte) 0) {
            valueHandler.handleValue(Long.valueOf(parcel.readLong()));
        }
    }

    static boolean readBoolean(Parcel parcel) {
        return parcel.readByte() != (byte) 0;
    }

    static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeByte(z ? (byte) 1 : (byte) 0);
    }

    static void writeOptionalInt(Parcel parcel, boolean z, int i) {
        if (z) {
            parcel.writeByte((byte) 1);
            parcel.writeInt(i);
            return;
        }
        parcel.writeByte((byte) 0);
    }

    static void writeOptionalLong(Parcel parcel, boolean z, long j) {
        if (z) {
            parcel.writeByte((byte) 1);
            parcel.writeLong(j);
            return;
        }
        parcel.writeByte((byte) 0);
    }

    protected UpdateOptions(UpdateOptions updateOptions) {
        if (updateOptions != null) {
            this.idleMode = updateOptions.idleMode;
            this.allowedSources = updateOptions.allowedSources;
            this.allowedSourcesSet = updateOptions.allowedSourcesSet;
            this.allowedTechnologies = updateOptions.allowedTechnologies;
            this.allowedTechnologiesSet = updateOptions.allowedTechnologiesSet;
            this.desiredUpdateInterval = updateOptions.desiredUpdateInterval;
            this.desiredUpdateIntervalSet = updateOptions.desiredUpdateIntervalSet;
            this.smallestUpdateInterval = updateOptions.smallestUpdateInterval;
            this.smallestUpdateIntervalSet = updateOptions.smallestUpdateIntervalSet;
            this.options = updateOptions.options;
            this.optionsSet = updateOptions.optionsSet;
        }
    }

    public UpdateOptions setAllowedSources(long j) {
        this.allowedSources = j;
        this.allowedSourcesSet = true;
        return this;
    }

    public boolean isSourceAllowed(long j) {
        return this.allowedSourcesSet && (this.allowedSources & j) == j;
    }

    public UpdateOptions setAllowedTechnologies(long j) {
        this.allowedTechnologies = j;
        this.allowedTechnologiesSet = true;
        return this;
    }

    public boolean isTechnologyAllowed(long j) {
        return this.allowedTechnologiesSet && (this.allowedTechnologies & j) == j;
    }

    public UpdateOptions setDesiredUpdateInterval(long j) {
        this.desiredUpdateInterval = j;
        this.desiredUpdateIntervalSet = true;
        return this;
    }

    public UpdateOptions setSmallestUpdateInterval(long j) {
        this.smallestUpdateInterval = j;
        this.smallestUpdateIntervalSet = true;
        return this;
    }

    public UpdateOptions setOptions(long j) {
        this.options = j;
        this.optionsSet = true;
        return this;
    }

    public UpdateOptions disableOptions(long j) {
        this.options &= -1 ^ j;
        this.optionsSet = true;
        return this;
    }

    public UpdateOptions disableSources(long j) {
        this.allowedSources &= -1 ^ j;
        this.allowedSourcesSet = true;
        return this;
    }

    public UpdateOptions disableTechnologies(long j) {
        this.allowedTechnologies &= -1 ^ j;
        this.allowedTechnologiesSet = true;
        return this;
    }

    public EnumSet<Source> getSourceSet() {
        if (this.allowedSourcesSet) {
            return getSourceSet(this.allowedSources);
        }
        return getSourceSet(0);
    }

    public static EnumSet<Source> getSourceSet(long j) {
        Collection arrayList = new ArrayList();
        if (j == 0) {
            arrayList.add(Source.Unspecified);
        } else {
            addIfSet(j, 1, arrayList, Source.LastKnown);
            addIfSet(j, 2, arrayList, Source.Online);
            addIfSet(j, 4, arrayList, Source.Offline);
            addIfSet(j, 8, arrayList, Source.HighAccuracy);
            addIfSet(j, 16, arrayList, Source.Cache);
            addIfSet(j, 32, arrayList, Source.Learning);
            addIfSet(j, 64, arrayList, Source.Hardware);
            addIfSet(j, 128, arrayList, Source.Fusion);
        }
        if (arrayList.isEmpty()) {
            return EnumSet.noneOf(Source.class);
        }
        return EnumSet.copyOf(arrayList);
    }

    public EnumSet<Technology> getTechnologySet() {
        if (this.allowedTechnologiesSet) {
            return getTechnologySet(this.allowedTechnologies);
        }
        return getTechnologySet(0);
    }

    public static EnumSet<Technology> getTechnologySet(long j) {
        Collection arrayList = new ArrayList();
        if (j == 0) {
            return EnumSet.noneOf(Technology.class);
        }
        addIfSet(j, 1, arrayList, Technology.Gnss);
        addIfSet(j, 2, arrayList, Technology.Wlan);
        addIfSet(j, 12, arrayList, Technology.Cellular);
        addIfSet(j, 4, arrayList, Technology.Cell);
        addIfSet(j, 8, arrayList, Technology.ECell);
        addIfSet(j, 16, arrayList, Technology.LocationArea);
        addIfSet(j, 32, arrayList, Technology.Network);
        addIfSet(j, 64, arrayList, Technology.Country);
        addIfSet(j, 128, arrayList, Technology.Ip);
        addIfSet(j, 256, arrayList, Technology.Sensors);
        addIfSet(j, 512, arrayList, Technology.Map);
        addIfSet(j, 1024, arrayList, Technology.TrackingArea);
        addIfSet(j, 2048, arrayList, Technology.Rnc);
        addIfSet(j, 4096, arrayList, Technology.ENodeB);
        addIfSet(j, 8192, arrayList, Technology.System);
        addIfSet(j, 16384, arrayList, Technology.BluetoothLE);
        if (arrayList.isEmpty()) {
            return EnumSet.noneOf(Technology.class);
        }
        return EnumSet.copyOf(arrayList);
    }

    public UpdateOptions clone() {
        return new UpdateOptions(this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UpdateOptions [ ");
        stringBuilder.append(" idleMode: ").append(this.idleMode);
        if (this.desiredUpdateIntervalSet) {
            stringBuilder.append(" desired: ").append(this.desiredUpdateInterval).append("ms");
        }
        if (this.smallestUpdateIntervalSet) {
            stringBuilder.append(" smallest: ").append(this.smallestUpdateInterval).append("ms");
        }
        if (this.allowedSourcesSet) {
            stringBuilder.append(" sources: ").append(this.allowedSources);
        }
        if (this.allowedTechnologiesSet) {
            stringBuilder.append(" techs: ").append(this.allowedTechnologies);
        }
        if (this.optionsSet) {
            stringBuilder.append(" options: ").append(this.options);
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    public boolean isEqual(UpdateOptions updateOptions) {
        if (updateOptions != null && this.idleMode == updateOptions.idleMode && this.allowedSources == updateOptions.allowedSources && this.allowedSourcesSet == updateOptions.allowedSourcesSet && this.allowedTechnologies == updateOptions.allowedTechnologies && this.allowedTechnologiesSet == updateOptions.allowedTechnologiesSet && this.desiredUpdateInterval == updateOptions.desiredUpdateInterval && this.desiredUpdateIntervalSet == updateOptions.desiredUpdateIntervalSet && this.smallestUpdateInterval == updateOptions.smallestUpdateInterval && this.smallestUpdateIntervalSet == updateOptions.smallestUpdateIntervalSet && this.options == updateOptions.options && this.optionsSet == updateOptions.optionsSet) {
            return true;
        }
        return false;
    }

    public UpdateOptions setHighPowerOptions() {
        this.idleMode = false;
        setDesiredUpdateInterval(1000);
        setSmallestUpdateInterval(200);
        setAllowedTechnologies(16654);
        setAllowedSources(190);
        setOptions(55);
        return this;
    }

    public UpdateOptions setMediumPowerOptions() {
        this.idleMode = false;
        setDesiredUpdateInterval(30000);
        setSmallestUpdateInterval(1000);
        setAllowedTechnologies(14);
        setAllowedSources(182);
        setOptions(119);
        return this;
    }

    public UpdateOptions setLowPowerOptions() {
        this.idleMode = false;
        setDesiredUpdateInterval(60000);
        setSmallestUpdateInterval(1000);
        setAllowedTechnologies(14);
        setAllowedSources(182);
        setOptions(1);
        return this;
    }

    public UpdateOptions setNoPowerOptions() {
        this.idleMode = false;
        setDesiredUpdateInterval(900000);
        setSmallestUpdateInterval(1000);
        setAllowedTechnologies(12);
        setAllowedSources(180);
        setOptions(0);
        return this;
    }

    public UpdateOptions setDisabledPowerOptions() {
        this.idleMode = true;
        setDesiredUpdateInterval(IPositioningSession.NotSet);
        setSmallestUpdateInterval(IPositioningSession.NotSet);
        setAllowedTechnologies(0);
        setAllowedSources(0);
        setOptions(0);
        return this;
    }

    public UpdateOptions setHybridPositioningOptions() {
        this.idleMode = false;
        setDesiredUpdateInterval(1000);
        setSmallestUpdateInterval(200);
        setAllowedTechnologies(16647);
        setAllowedSources(254);
        setOptions(135);
        return this;
    }

    public UpdateOptions setHighAccuracyPositioningOptions() {
        this.idleMode = false;
        setDesiredUpdateInterval(1000);
        setSmallestUpdateInterval(200);
        setAllowedTechnologies(16646);
        setAllowedSources(8);
        setOptions(131);
        return this;
    }

    public Bundle asBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_BUNDLED_INSTANCE, this);
        return bundle;
    }

    public static UpdateOptions fromBundle(Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("bundle is null");
        }
        bundle.setClassLoader(UpdateOptions.class.getClassLoader());
        return (UpdateOptions) bundle.getParcelable(KEY_BUNDLED_INSTANCE);
    }

    static boolean isSet(long j, long j2) {
        return (j & j2) == j2;
    }

    static <T> void addIfSet(long j, long j2, Collection<T> collection, T t) {
        if (isSet(j, j2)) {
            collection.add(t);
        }
    }
}
