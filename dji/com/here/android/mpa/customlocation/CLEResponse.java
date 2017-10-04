package com.here.android.mpa.customlocation;

import com.google.gson.annotations.Expose;

abstract class CLEResponse {
    @Expose
    String status;

    abstract Result a();

    CLEResponse() {
    }
}
