package com.here.android.mpa.common;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class UnintializedMapEngineException extends RuntimeException {
    public UnintializedMapEngineException() {
        super("Cannot created HERE SDK objects before MapEngine is initialized.  See MapEngine.init()");
    }
}
