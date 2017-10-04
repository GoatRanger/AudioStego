package com.here.services.location.hybrid;

import com.here.posclient.UpdateOptions;
import com.here.services.HereLocationApiClient;
import com.here.services.location.LocationApi;
import com.here.services.location.LocationListener;

public interface HybridLocationApi extends LocationApi {

    public static class GnssOptions implements com.here.services.Api.Options {
        boolean mEnabled = true;

        public GnssOptions setEnabled(boolean z) {
            this.mEnabled = z;
            return this;
        }

        private void apply(UpdateOptions updateOptions) {
            if (!this.mEnabled) {
                updateOptions.disableTechnologies(1);
                updateOptions.disableSources(64);
            }
        }
    }

    public static class HighAccuracyOptions implements com.here.services.Api.Options {
        boolean mEnabled = true;

        public HighAccuracyOptions setEnabled(boolean z) {
            this.mEnabled = z;
            return this;
        }

        private void apply(UpdateOptions updateOptions) {
            if (!this.mEnabled) {
                updateOptions.disableTechnologies(16640);
                updateOptions.disableSources(8);
            }
        }
    }

    public static class NetworkLocationOptions implements com.here.services.Api.Options {
        boolean mEnabled = true;

        public NetworkLocationOptions setEnabled(boolean z) {
            this.mEnabled = z;
            return this;
        }

        private void apply(UpdateOptions updateOptions) {
            if (!this.mEnabled) {
                updateOptions.disableTechnologies(12);
                updateOptions.disableSources(22);
            }
        }
    }

    public static class Options implements com.here.services.Api.Options {
        public static final long DEFAULT_DESIRED_INTERVAL = 15000;
        public static final long DEFAULT_SMALLEST_INTERVAL = 200;
        long mDesiredInterval = DEFAULT_DESIRED_INTERVAL;
        GnssOptions mGnnsOptions = new GnssOptions();
        HighAccuracyOptions mHighAccuracyOptions = new HighAccuracyOptions();
        NetworkLocationOptions mNetworkLocationOptions = new NetworkLocationOptions();
        long mSmallestInterval = 200;

        static class HybridPositioningOptions extends com.here.services.location.internal.Options {
            HybridPositioningOptions(Options options) {
                options.apply(getUpdateOptions().setHybridPositioningOptions());
            }
        }

        public Options setGnnsOptions(GnssOptions gnssOptions) {
            this.mGnnsOptions = gnssOptions;
            return this;
        }

        public Options setHighAccuracyOptions(HighAccuracyOptions highAccuracyOptions) {
            this.mHighAccuracyOptions = highAccuracyOptions;
            return this;
        }

        public Options setNetworkLocationOptions(NetworkLocationOptions networkLocationOptions) {
            this.mNetworkLocationOptions = networkLocationOptions;
            return this;
        }

        public Options setSmallestInterval(long j) {
            this.mSmallestInterval = Math.max(200, j);
            this.mDesiredInterval = Math.max(DEFAULT_DESIRED_INTERVAL, this.mSmallestInterval);
            return this;
        }

        protected com.here.services.location.internal.Options build() {
            return new HybridPositioningOptions(this);
        }

        private void apply(UpdateOptions updateOptions) {
            this.mGnnsOptions.apply(updateOptions);
            this.mHighAccuracyOptions.apply(updateOptions);
            this.mNetworkLocationOptions.apply(updateOptions);
            if (this.mGnnsOptions.mEnabled && !this.mHighAccuracyOptions.mEnabled && !this.mNetworkLocationOptions.mEnabled) {
                updateOptions.disableSources(128);
            } else if (!(!this.mHighAccuracyOptions.mEnabled || this.mGnnsOptions.mEnabled || this.mNetworkLocationOptions.mEnabled)) {
                updateOptions.disableSources(128);
            }
            updateOptions.setSmallestUpdateInterval(this.mSmallestInterval).setDesiredUpdateInterval(this.mDesiredInterval);
        }
    }

    boolean startLocationUpdates(HereLocationApiClient hereLocationApiClient, Options options, LocationListener locationListener);

    void stopLocationUpdates(HereLocationApiClient hereLocationApiClient, LocationListener locationListener);
}
