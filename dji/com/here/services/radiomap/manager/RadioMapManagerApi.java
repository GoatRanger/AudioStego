package com.here.services.radiomap.manager;

import com.here.services.HereLocationApiClient;
import com.here.services.radiomap.common.GeoArea;
import java.util.List;

public interface RadioMapManagerApi {

    public static class Options implements com.here.services.Api.Options {
        boolean mInclude2G = true;
        boolean mInclude3G = true;
        boolean mIncludeCdma = true;
        boolean mIncludeHighAccuracy = true;
        boolean mIncludeLte = true;
        boolean mIncludeWifiCoarse = true;
        boolean mIncludeWifiDetailed = false;

        public Options setInclude2G(boolean z) {
            this.mInclude2G = z;
            return this;
        }

        public Options setInclude3G(boolean z) {
            this.mInclude3G = z;
            return this;
        }

        public Options setIncludeLte(boolean z) {
            this.mIncludeLte = z;
            return this;
        }

        public Options setIncludeCdma(boolean z) {
            this.mIncludeCdma = z;
            return this;
        }

        public Options setIncludeWifiCoarse(boolean z) {
            this.mIncludeWifiCoarse = z;
            if (!this.mIncludeWifiCoarse) {
                this.mIncludeWifiDetailed = false;
            }
            return this;
        }

        public Options setIncludeWifiDetailed(boolean z) {
            this.mIncludeWifiDetailed = z;
            if (this.mIncludeWifiDetailed) {
                this.mIncludeWifiCoarse = true;
            }
            return this;
        }

        public Options setIncludeHighAccuracy(boolean z) {
            this.mIncludeHighAccuracy = z;
            return this;
        }
    }

    public static class QueryOptions extends Options {
        Target mTarget = Target.Local;

        public enum Target {
            Local,
            Update,
            Extend
        }

        public QueryOptions setTarget(Target target) {
            this.mTarget = target;
            return this;
        }
    }

    void clear(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, Options options, RadioMapManagerListener radioMapManagerListener);

    void extend(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, Options options, RadioMapManagerListener radioMapManagerListener);

    void query(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, QueryOptions queryOptions, RadioMapManagerListener radioMapManagerListener);

    void stop(HereLocationApiClient hereLocationApiClient, RadioMapManagerListener radioMapManagerListener);

    void update(HereLocationApiClient hereLocationApiClient, List<GeoArea> list, Options options, RadioMapManagerListener radioMapManagerListener);
}
