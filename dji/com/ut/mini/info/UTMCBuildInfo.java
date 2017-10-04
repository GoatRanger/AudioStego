package com.ut.mini.info;

import com.ut.mini.base.IUTMCBuildInfo;

public class UTMCBuildInfo implements IUTMCBuildInfo {
    private static final String BUILD_ID = "347369";
    private static final String FULL_SDK_VERSION = "4.3.9.347369";
    private static final String GIT_COMMIT_ID = "8d1a02b84bebe302ec5e78332fbc5f45975ee9b9";
    private static final String SHORT_SDK_VERSION = "4.3.9";
    private static UTMCBuildInfo s_instance = new UTMCBuildInfo();

    public static UTMCBuildInfo getInstance() {
        return s_instance;
    }

    public String getBuildID() {
        return BUILD_ID;
    }

    public String getGitCommitID() {
        return GIT_COMMIT_ID;
    }

    public String getShortSDKVersion() {
        return SHORT_SDK_VERSION;
    }

    public String getFullSDKVersion() {
        return FULL_SDK_VERSION;
    }
}
