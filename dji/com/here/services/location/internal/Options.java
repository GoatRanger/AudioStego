package com.here.services.location.internal;

import android.os.Bundle;
import com.here.posclient.UpdateOptions;

public class Options {
    private static final int MIN_UPDATE_INTERVAL = 0;
    private UpdateOptions mUpdateOptions = new UpdateOptions().setNoPowerOptions();

    public static Options getHighPowerOptions() {
        return new Options().setHighPowerOptions();
    }

    public static Options getMediumPowerOptions() {
        return new Options().setMediumPowerOptions();
    }

    public static Options getLowPowerOptions() {
        return new Options().setLowPowerOptions();
    }

    public static Options getNoPowerOptions() {
        return new Options().setNoPowerOptions();
    }

    public void setDesiredUpdateInterval(long j) {
        this.mUpdateOptions.setDesiredUpdateInterval(Math.max(0, j));
    }

    public void setSmallestUpdateInterval(long j) {
        this.mUpdateOptions.setSmallestUpdateInterval(Math.max(0, j));
    }

    public long getDesiredUpdateInterval() {
        return this.mUpdateOptions.desiredUpdateInterval;
    }

    public long getSmallestUpdateInterval() {
        return this.mUpdateOptions.smallestUpdateInterval;
    }

    protected void setAllowedTechnologies(long j) {
        this.mUpdateOptions.setAllowedTechnologies(j);
    }

    protected void setAllowedSources(long j) {
        this.mUpdateOptions.setAllowedSources(j);
    }

    protected void setOptions(long j) {
        this.mUpdateOptions.setOptions(j);
    }

    protected UpdateOptions getUpdateOptions() {
        return this.mUpdateOptions;
    }

    protected Bundle getUpdateOptionsAsBundle() {
        return this.mUpdateOptions.asBundle();
    }

    protected boolean isEqual(Options options) {
        return options != null && this.mUpdateOptions.isEqual(options.mUpdateOptions);
    }

    private Options setHighPowerOptions() {
        this.mUpdateOptions.setHighPowerOptions();
        return this;
    }

    private Options setMediumPowerOptions() {
        this.mUpdateOptions.setMediumPowerOptions();
        return this;
    }

    private Options setLowPowerOptions() {
        this.mUpdateOptions.setLowPowerOptions();
        return this;
    }

    private Options setNoPowerOptions() {
        this.mUpdateOptions.setNoPowerOptions();
        return this;
    }
}
