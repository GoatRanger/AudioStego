package com.here.android.mpa.routing;

import com.here.android.mpa.urbanmobility.Alert;
import com.here.android.mpa.urbanmobility.ErrorCode;
import com.here.android.mpa.urbanmobility.Link;
import com.here.android.mpa.urbanmobility.Operator;
import com.nokia.maps.a.h;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

@HybridPlus
public class UMCalculateResult {
    private final h a;

    @HybridPlus
    public enum ViolatedOption {
        MAXIMUM_WALKING_DISTANCE,
        WALKING_SPEED,
        MAXIMUM_CHANGES_COUNT,
        RESTRICT_TRANSIT_TYPES
    }

    public ErrorCode getError() {
        return this.a.a();
    }

    public String getErrorMessage() {
        return this.a.b();
    }

    public List<UMRouteResult> getResults() {
        return this.a.c();
    }

    public boolean isSubsequentRouteSupported() {
        return this.a.d();
    }

    public Collection<Operator> getOperators() {
        return this.a.j();
    }

    public Collection<Alert> getAlerts() {
        return this.a.h();
    }

    public Collection<Link> getOperatorDisclaimers() {
        return this.a.g();
    }

    public EnumSet<ViolatedOption> getViolatedOptions() {
        return this.a.i();
    }

    UMCalculateResult(h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = hVar;
    }

    static {
        h.a(new k<UMCalculateResult, h>() {
            public h a(UMCalculateResult uMCalculateResult) {
                return uMCalculateResult.a;
            }
        }, new am<UMCalculateResult, h>() {
            public UMCalculateResult a(h hVar) {
                if (hVar == null) {
                    return null;
                }
                try {
                    return new UMCalculateResult(hVar);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
