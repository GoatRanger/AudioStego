package com.here.services.playback.internal;

import android.os.Bundle;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.io.FileNotFoundException;

public class PlaybackOptions {
    private static final String KEY_BLE_TAG_TYPES = "ble_tag_types";
    private static final String KEY_FAST_FORWARD = "fast_forward";
    private static final String KEY_MODE = "mode";
    private static final String KEY_PLAYBACK_FILE = "playback_file";
    private static final String KEY_REPEAT = "repeat";
    private static final String KEY_TECHNOLOGIES = "technologies";
    public static final int PLAYBACK_ALL = -1;
    public static final int PLAYBACK_BLE = 8;
    public static final int PLAYBACK_BLE_TAG_ALL = -1;
    public static final int PLAYBACK_BLE_TAG_APPLE_IBEACON = 4;
    public static final int PLAYBACK_BLE_TAG_BLUVISION_SBEACON = 8;
    public static final int PLAYBACK_BLE_TAG_GOOGLE_EDDYSTONE = 32;
    public static final int PLAYBACK_BLE_TAG_NOKIA_V1 = 1;
    public static final int PLAYBACK_BLE_TAG_NOKIA_V2 = 2;
    public static final int PLAYBACK_BLE_TAG_OTHER = 16;
    public static final int PLAYBACK_CELL = 1;
    public static final int PLAYBACK_GNSS = 4;
    public static final int PLAYBACK_WIFI = 2;
    private static final String TAG = "odnp.test.PlaybackOptions";
    private int mBleTagTypes = -1;
    private boolean mFastForwardLongBreaks = false;
    private Mode mMode = Mode.Scheduled;
    private File mPlaybackFile;
    private boolean mRepeat = false;
    private int mTechnologies = -1;

    public enum Mode {
        Immediate,
        Scheduled
    }

    public PlaybackOptions setPlaybackFile(String str) throws FileNotFoundException {
        this.mPlaybackFile = new File(str);
        if (this.mPlaybackFile.exists()) {
            return this;
        }
        throw new FileNotFoundException(str);
    }

    public File getPlaybackFile() {
        return this.mPlaybackFile;
    }

    public PlaybackOptions setTechnologies(int i) {
        this.mTechnologies = i;
        return this;
    }

    public int getTechnologies() {
        return this.mTechnologies;
    }

    public PlaybackOptions setRepeat(boolean z) {
        this.mRepeat = z;
        return this;
    }

    public boolean getRepeat() {
        return this.mRepeat;
    }

    public PlaybackOptions setMode(Mode mode) {
        this.mMode = mode;
        return this;
    }

    public Mode getMode() {
        return this.mMode;
    }

    public PlaybackOptions setFastForwardLongBreaks(boolean z) {
        this.mFastForwardLongBreaks = z;
        return this;
    }

    public boolean getFastForwardLongBreaks() {
        return this.mMode == Mode.Scheduled && this.mFastForwardLongBreaks;
    }

    public PlaybackOptions setBleTagTypes(int i) {
        this.mBleTagTypes = i;
        return this;
    }

    public int getBleTagTypes() {
        return this.mBleTagTypes;
    }

    protected Bundle asBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PLAYBACK_FILE, this.mPlaybackFile.getAbsolutePath());
        bundle.putInt(KEY_TECHNOLOGIES, this.mTechnologies);
        bundle.putBoolean(KEY_REPEAT, this.mRepeat);
        bundle.putString("mode", this.mMode.name());
        bundle.putBoolean(KEY_FAST_FORWARD, this.mFastForwardLongBreaks);
        bundle.putInt(KEY_BLE_TAG_TYPES, this.mBleTagTypes);
        return bundle;
    }

    public static PlaybackOptions fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        PlaybackOptions playbackOptions = new PlaybackOptions();
        try {
            playbackOptions.setPlaybackFile(bundle.getString(KEY_PLAYBACK_FILE));
            playbackOptions.setTechnologies(bundle.getInt(KEY_TECHNOLOGIES, -1));
            playbackOptions.setRepeat(bundle.getBoolean(KEY_REPEAT, true));
            try {
                playbackOptions.setMode(Mode.valueOf(bundle.getString("mode")));
            } catch (Exception e) {
            }
            playbackOptions.setFastForwardLongBreaks(bundle.getBoolean(KEY_FAST_FORWARD, false));
            playbackOptions.setBleTagTypes(bundle.getInt(KEY_BLE_TAG_TYPES, -1));
            return playbackOptions;
        } catch (FileNotFoundException e2) {
            return null;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PlaybackOptions[");
        stringBuilder.append(this.mMode).append("|");
        stringBuilder.append("repeat:").append(this.mRepeat).append("|");
        stringBuilder.append("ff:").append(this.mFastForwardLongBreaks).append("|");
        stringBuilder.append("tech:").append(Integer.toHexString(this.mTechnologies)).append("|");
        stringBuilder.append("bleTags:").append(Integer.toHexString(this.mBleTagTypes)).append("|");
        stringBuilder.append(this.mPlaybackFile.getAbsolutePath());
        stringBuilder.append(d.H);
        return stringBuilder.toString();
    }
}
