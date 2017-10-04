package com.nokia.maps.restrouting;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class TruckRestrictions {
    @Expose
    private List<String> forbiddenHazardousGoods = new ArrayList();
    @Expose
    private Double height;
    @Expose
    private Double length;
    @Expose
    private Double limitedWeight;
    @Expose
    private boolean trailerForbidden;
    @Expose
    private Double trailerWeight;
    @Expose
    private Double weightPerAxle;
    @Expose
    private Double width;
}
