package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;

public class RoutingRestResult {
    @Expose
    private Response response;

    public Response a() {
        return this.response;
    }
}
