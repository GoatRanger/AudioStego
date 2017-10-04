package com.here.android.mpa.customlocation;

import com.here.android.mpa.customlocation.Result.Geometry;
import java.util.List;

final class d extends CLEResponse {
    List<Geometry> a;

    d() {
    }

    Result a() {
        return new Result(this);
    }
}
