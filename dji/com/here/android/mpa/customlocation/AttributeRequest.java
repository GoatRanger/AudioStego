package com.here.android.mpa.customlocation;

import com.nokia.maps.annotation.HybridPlus;

public class AttributeRequest extends Request {
    @HybridPlus
    public AttributeRequest(int i, QueryBuilder queryBuilder) {
        super(i, queryBuilder);
    }

    @HybridPlus
    public AttributeRequest(int i, CustomQueryBuilder customQueryBuilder) {
        super(i, customQueryBuilder);
    }
}
