package com.here.services.location.network;

import com.here.posclient.UpdateOptions;
import com.here.services.HereLocationApiClient;
import com.here.services.location.LocationApi;
import com.here.services.location.LocationListener;

public interface NetworkLocationApi extends LocationApi {

    public static class Options implements com.here.services.Api.Options {
        public static final long DEFAULT_DESIRED_INTERVAL = 30000;
        public static final long DEFAULT_SMALLEST_INTERVAL = 200;
        public static final long MIN_DESIRED_INTERVAL = 20000;
        public static final long MIN_SMALLEST_INTERVAL = 200;
        long mDesiredInterval = 30000;
        long mSmallestInterval = 200;
        boolean mUseCache = true;
        boolean mUseCell = true;
        boolean mUseOffline = true;
        boolean mUseWifi = true;

        static class NetworkPositioningOptions extends com.here.services.location.internal.Options {
            NetworkPositioningOptions(Options options) {
                options.apply(getUpdateOptions().setMediumPowerOptions());
            }
        }

        public Options setUseCell(boolean z) {
            this.mUseCell = z;
            return this;
        }

        public Options setUseWifi(boolean z) {
            this.mUseWifi = z;
            return this;
        }

        public Options setUseCache(boolean z) {
            this.mUseCache = z;
            return this;
        }

        public Options setUseOffline(boolean z) {
            this.mUseOffline = z;
            return this;
        }

        public Options setSmallestInterval(long j) {
            this.mSmallestInterval = Math.max(200, j);
            this.mDesiredInterval = Math.max(this.mSmallestInterval, this.mDesiredInterval);
            return this;
        }

        public Options setDesiredInterval(long j) {
            this.mDesiredInterval = Math.max(MIN_DESIRED_INTERVAL, j);
            this.mSmallestInterval = Math.min(this.mSmallestInterval, this.mDesiredInterval);
            return this;
        }

        protected com.here.services.location.internal.Options build() {
            return new NetworkPositioningOptions(this);
        }

        private void apply(UpdateOptions updateOptions) {
            updateOptions.setSmallestUpdateInterval(this.mSmallestInterval).setDesiredUpdateInterval(this.mDesiredInterval);
            if (!this.mUseCell) {
                updateOptions.disableTechnologies(12);
            }
            if (!this.mUseWifi) {
                updateOptions.disableTechnologies(2);
            }
            if (!this.mUseCache) {
                updateOptions.disableSources(16);
                updateOptions.disableSources(1);
                updateOptions.disableSources(128);
            }
            if (!this.mUseOffline) {
                updateOptions.disableSources(4);
            }
        }
    }

    boolean startLocationUpdates(HereLocationApiClient hereLocationApiClient, Options options, LocationListener locationListener);

    void stopLocationUpdates(HereLocationApiClient hereLocationApiClient, LocationListener locationListener);
}
