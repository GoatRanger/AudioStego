package com.here.android.mpa.customlocation;

import com.here.android.mpa.customlocation.QueryBuilder.Condition;
import com.here.android.mpa.customlocation.QueryBuilder.Operation;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class CustomQueryBuilder extends QueryBuilder {
    public CustomQueryBuilder(Condition condition, String str, String str2) {
        super(condition, str, str2);
    }

    public CustomQueryBuilder addCondition(Operation operation, Condition condition, String str, String str2) {
        super.addCondition(operation, condition, str.toString(), str2);
        return this;
    }
}
