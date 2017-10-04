package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class Mode {
    @Expose
    private List<Object> feature = new ArrayList();
    @Expose
    private String trafficMode;
    @Expose
    private List<String> transportModes = new ArrayList();
    @Expose
    private String type;
}
