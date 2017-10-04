package com.here.android.mpa.guidance;

import com.nokia.maps.LaneInfoImpl;
import com.nokia.maps.annotation.HybridPlus;
import java.util.EnumSet;

@HybridPlus
@Deprecated
public final class LaneInfo {
    private LaneInfoImpl a;

    @HybridPlus
    @Deprecated
    public enum Directions {
        UNDEFINED(0),
        STRAIGHT(1),
        SLIGHTLY_RIGHT(2),
        RIGHT(3),
        SHARP_RIGHT(4),
        U_TURN_LEFT(5),
        SHARP_LEFT(6),
        LEFT(7),
        SLIGHTLY_LEFT(8),
        U_TURN_RIGHT(9),
        UNKNOWN(10);
        
        private int a;

        private Directions(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @HybridPlus
    @Deprecated
    public enum Position {
        OFF_ROUTE(0),
        ON_ROUTE(1),
        UNKNOWN(2);
        
        private int a;

        private Position(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    private LaneInfo(LaneInfoImpl laneInfoImpl) {
        this.a = laneInfoImpl;
    }

    @Deprecated
    public EnumSet<Directions> getDirections() {
        return this.a.a();
    }

    @Deprecated
    public Position getOnRoutePosition() {
        return this.a.b();
    }

    static {
        LaneInfoImpl.a(new 1(), new 2());
    }
}
