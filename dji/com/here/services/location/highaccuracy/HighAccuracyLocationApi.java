package com.here.services.location.highaccuracy;

import com.here.posclient.UpdateOptions;
import com.here.services.HereLocationApiClient;
import com.here.services.location.LocationApi;
import com.here.services.location.LocationListener;

public interface HighAccuracyLocationApi extends LocationApi {

    public static class Options implements com.here.services.Api.Options {
        public static final long DEFAULT_DESIRED_INTERVAL = 1000;
        public static final long DEFAULT_SMALLEST_INTERVAL = 200;
        private static final long MIN_DESIRED_INTERVAL = 1000;
        private static final long MIN_SMALLEST_INTERVAL = 200;
        boolean mBleEnabled = true;
        long mDesiredInterval = 1000;
        boolean mSensorsEnabled = true;
        long mSmallestInterval = 200;
        boolean mWifiEnabled = true;

        static class HighAccuracyPositioningOptions extends com.here.services.location.internal.Options {
            HighAccuracyPositioningOptions(Options options) {
                options.apply(getUpdateOptions().setHighAccuracyPositioningOptions());
            }
        }

        public Options setWifiEnabled(boolean z) {
            this.mWifiEnabled = z;
            return this;
        }

        public Options setBleEnabled(boolean z) {
            this.mBleEnabled = z;
            return this;
        }

        public Options setSensorsEnabled(boolean z) {
            this.mSensorsEnabled = z;
            return this;
        }

        public Options setSmallestInterval(long j) {
            this.mSmallestInterval = Math.max(200, j);
            this.mDesiredInterval = Math.max(this.mSmallestInterval, this.mDesiredInterval);
            return this;
        }

        public Options setDesiredInterval(long j) {
            this.mDesiredInterval = Math.max(1000, j);
            this.mSmallestInterval = Math.min(this.mSmallestInterval, this.mDesiredInterval);
            return this;
        }

        protected com.here.services.location.internal.Options build() {
            return new HighAccuracyPositioningOptions(this);
        }

        private void apply(UpdateOptions updateOptions) {
            updateOptions.setSmallestUpdateInterval(this.mSmallestInterval).setDesiredUpdateInterval(this.mDesiredInterval);
            if (!this.mWifiEnabled) {
                updateOptions.disableTechnologies(2);
            }
            if (!this.mBleEnabled) {
                updateOptions.disableTechnologies(16384);
            }
            if (!this.mSensorsEnabled) {
                updateOptions.disableTechnologies(256);
            }
        }
    }

    boolean startLocationUpdates(HereLocationApiClient hereLocationApiClient, Options options, LocationListener locationListener);

    void stopLocationUpdates(HereLocationApiClient hereLocationApiClient, LocationListener locationListener);
}
