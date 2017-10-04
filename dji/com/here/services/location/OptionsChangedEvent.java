package com.here.services.location;

import com.here.posclient.UpdateOptions;
import com.here.services.common.Types.Source;
import com.here.services.common.Types.Technology;
import java.util.EnumSet;

public class OptionsChangedEvent {
    private final EnumSet<Source> mDisabledSources = this.mRequestedSources.clone();
    private final EnumSet<Technology> mDisabledTechnologies;
    private final EnumSet<Source> mRequestedSources;
    private final EnumSet<Technology> mRequestedTechnologies;

    public OptionsChangedEvent(UpdateOptions updateOptions, UpdateOptions updateOptions2) {
        this.mRequestedSources = updateOptions.getSourceSet();
        this.mDisabledSources.removeAll(updateOptions2.getSourceSet());
        this.mRequestedTechnologies = updateOptions.getTechnologySet();
        this.mDisabledTechnologies = this.mRequestedTechnologies.clone();
        this.mDisabledTechnologies.removeAll(updateOptions2.getTechnologySet());
    }

    public boolean hasChanged() {
        return (this.mDisabledSources.isEmpty() && this.mDisabledTechnologies.isEmpty()) ? false : true;
    }

    public EnumSet<Source> getRequestedSources() {
        return this.mRequestedSources.clone();
    }

    public EnumSet<Source> getDisabledSources() {
        return this.mDisabledSources.clone();
    }

    public EnumSet<Technology> getRequestedTechnologies() {
        return this.mRequestedTechnologies.clone();
    }

    public EnumSet<Technology> getDisabledTechnologies() {
        return this.mDisabledTechnologies.clone();
    }
}
