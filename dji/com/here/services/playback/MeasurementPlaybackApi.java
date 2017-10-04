package com.here.services.playback;

import com.here.services.Api.Options.Optional;
import com.here.services.HereLocationApiClient;
import com.here.services.playback.internal.PlaybackOptions;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.io.FileNotFoundException;

public interface MeasurementPlaybackApi {
    public static final int ALL = -1;
    public static final int BLE = 8;
    public static final int BLE_TAG_ALL = -1;
    public static final int BLE_TAG_APPLE_IBEACON = 4;
    public static final int BLE_TAG_BLUVISION_SBEACON = 8;
    public static final int BLE_TAG_GOOGLE_EDDYSTONE = 32;
    public static final int BLE_TAG_NOKIA_V1 = 1;
    public static final int BLE_TAG_NOKIA_V2 = 2;
    public static final int BLE_TAG_OTHER = 16;
    public static final int CELL = 1;
    public static final int GNSS = 4;
    public static final int WIFI = 2;

    public interface Listener {

        public enum Error {
            General
        }

        void onPlaybackError(Error error);

        void onPlaybackFinished();

        void onPlaybackStarted();
    }

    public enum Mode {
        Immediate,
        Scheduling
    }

    public static class Options implements Optional {
        int mBleTagTypes = -1;
        boolean mFastforwardLongBreaks = false;
        Mode mMode = Mode.Scheduling;
        File mPlaybackFile;
        boolean mRepeat = false;
        int mTechnologies = -1;

        public Options(String str) throws FileNotFoundException {
            this.mPlaybackFile = new File(str);
            if (!this.mPlaybackFile.exists()) {
                throw new FileNotFoundException("file '" + str + "' does not exist!");
            }
        }

        public Options setMode(Mode mode) {
            this.mMode = mode;
            return this;
        }

        public Options setRepeat(boolean z) {
            this.mRepeat = z;
            return this;
        }

        public Options setFastforwardLongBreaks(boolean z) {
            this.mFastforwardLongBreaks = z;
            return this;
        }

        public Options setTechnologies(int i) {
            this.mTechnologies = i;
            return this;
        }

        public Options setBleTagTypes(int i) {
            this.mBleTagTypes = i;
            return this;
        }

        protected PlaybackOptions asPlaybackOptions() throws FileNotFoundException {
            return new PlaybackOptions().setMode(modeToPlaybackMode(this.mMode)).setPlaybackFile(this.mPlaybackFile.getAbsolutePath()).setRepeat(this.mRepeat).setFastForwardLongBreaks(this.mFastforwardLongBreaks).setTechnologies(technologiesToPlaybackTechnologies(this.mTechnologies)).setBleTagTypes(bleTagTypesToPlaybackBleTagTypes(this.mBleTagTypes));
        }

        private com.here.services.playback.internal.PlaybackOptions.Mode modeToPlaybackMode(Mode mode) {
            switch (mode) {
                case Immediate:
                    return com.here.services.playback.internal.PlaybackOptions.Mode.Immediate;
                default:
                    return com.here.services.playback.internal.PlaybackOptions.Mode.Scheduled;
            }
        }

        private int technologiesToPlaybackTechnologies(int i) {
            int i2 = 1;
            if (!isSet(i, 1)) {
                i2 = 0;
            }
            if (isSet(i, 8)) {
                i2 |= 8;
            }
            if (isSet(i, 4)) {
                i2 |= 4;
            }
            if (isSet(i, 2)) {
                return i2 | 2;
            }
            return i2;
        }

        private int bleTagTypesToPlaybackBleTagTypes(int i) {
            int i2 = 1;
            if (!isSet(i, 1)) {
                i2 = 0;
            }
            if (isSet(i, 2)) {
                i2 |= 2;
            }
            if (isSet(i, 4)) {
                i2 |= 4;
            }
            if (isSet(i, 8)) {
                i2 |= 8;
            }
            if (isSet(i, 16)) {
                i2 |= 16;
            }
            if (isSet(i, 32)) {
                return i2 | 32;
            }
            return i2;
        }

        private static boolean isSet(int i, int i2) {
            return (i & i2) == i2;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("MeasurementPlaybackApi.Options[");
            stringBuilder.append(this.mMode).append("|");
            stringBuilder.append("repeat:").append(this.mRepeat).append("|");
            stringBuilder.append("ff:").append(this.mFastforwardLongBreaks).append("|");
            stringBuilder.append("tech:").append(Integer.toHexString(this.mTechnologies)).append("|");
            stringBuilder.append("bleTags:").append(Integer.toHexString(this.mBleTagTypes)).append("|");
            stringBuilder.append(this.mPlaybackFile.getAbsolutePath());
            stringBuilder.append(d.H);
            return stringBuilder.toString();
        }
    }

    void playback(HereLocationApiClient hereLocationApiClient, Options options, Listener listener);

    void stop(HereLocationApiClient hereLocationApiClient);
}
