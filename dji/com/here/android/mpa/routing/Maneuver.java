package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.nokia.maps.ManeuverImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.Date;
import java.util.List;

public class Maneuver {
    private ManeuverImpl a;

    @Online
    public enum Action {
        UNDEFINED(0),
        NO_ACTION(1),
        END(2),
        STOPOVER(3),
        JUNCTION(4),
        ROUNDABOUT(5),
        UTURN(6),
        ENTER_HIGHWAY_FROM_RIGHT(7),
        ENTER_HIGHWAY_FROM_LEFT(8),
        ENTER_HIGHWAY(9),
        LEAVE_HIGHWAY(10),
        CHANGE_HIGHWAY(11),
        CONTINUE_HIGHWAY(12),
        FERRY(13),
        PASS_JUNCTION(14),
        HEAD_TO(15),
        PASS_STATION(16),
        CHANGE_LINE(17),
        INVALID(18);
        
        private int a;

        private Action(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @Online
    public enum Icon {
        UNDEFINED(0),
        GO_STRAIGHT(1),
        UTURN_RIGHT(2),
        UTURN_LEFT(3),
        KEEP_RIGHT(4),
        LIGHT_RIGHT(5),
        QUITE_RIGHT(6),
        HEAVY_RIGHT(7),
        KEEP_MIDDLE(8),
        KEEP_LEFT(9),
        LIGHT_LEFT(10),
        QUITE_LEFT(11),
        HEAVY_LEFT(12),
        ENTER_HIGHWAY_RIGHT_LANE(13),
        ENTER_HIGHWAY_LEFT_LANE(14),
        LEAVE_HIGHWAY_RIGHT_LANE(15),
        LEAVE_HIGHWAY_LEFT_LANE(16),
        HIGHWAY_KEEP_RIGHT(17),
        HIGHWAY_KEEP_LEFT(18),
        ROUNDABOUT_1(19),
        ROUNDABOUT_2(20),
        ROUNDABOUT_3(21),
        ROUNDABOUT_4(22),
        ROUNDABOUT_5(23),
        ROUNDABOUT_6(24),
        ROUNDABOUT_7(25),
        ROUNDABOUT_8(26),
        ROUNDABOUT_9(27),
        ROUNDABOUT_10(28),
        ROUNDABOUT_11(29),
        ROUNDABOUT_12(30),
        ROUNDABOUT_1_LH(31),
        ROUNDABOUT_2_LH(32),
        ROUNDABOUT_3_LH(33),
        ROUNDABOUT_4_LH(34),
        ROUNDABOUT_5_LH(35),
        ROUNDABOUT_6_LH(36),
        ROUNDABOUT_7_LH(37),
        ROUNDABOUT_8_LH(38),
        ROUNDABOUT_9_LH(39),
        ROUNDABOUT_10_LH(40),
        ROUNDABOUT_11_LH(41),
        ROUNDABOUT_12_LH(42),
        START(43),
        END(44),
        FERRY(45),
        PASS_STATION(46),
        HEAD_TO(47),
        CHANGE_LINE(48);
        
        private int a;

        private Icon(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @Online
    public enum TrafficDirection {
        LEFT(0),
        RIGHT(1);
        
        private int a;

        private TrafficDirection(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @Online
    public enum Turn {
        UNDEFINED(0),
        NO_TURN(1),
        KEEP_MIDDLE(2),
        KEEP_RIGHT(3),
        LIGHT_RIGHT(4),
        QUITE_RIGHT(5),
        HEAVY_RIGHT(6),
        KEEP_LEFT(7),
        LIGHT_LEFT(8),
        QUITE_LEFT(9),
        HEAVY_LEFT(10),
        RETURN(11),
        ROUNDABOUT_1(12),
        ROUNDABOUT_2(13),
        ROUNDABOUT_3(14),
        ROUNDABOUT_4(15),
        ROUNDABOUT_5(16),
        ROUNDABOUT_6(17),
        ROUNDABOUT_7(18),
        ROUNDABOUT_8(19),
        ROUNDABOUT_9(20),
        ROUNDABOUT_10(21),
        ROUNDABOUT_11(22),
        ROUNDABOUT_12(23);
        
        private int a;

        private Turn(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    Maneuver(ManeuverImpl maneuverImpl) {
        this.a = maneuverImpl;
    }

    @Online
    public GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    @Online
    public GeoBoundingBox getBoundingBox() {
        return this.a.b();
    }

    @Online
    public Action getAction() {
        return this.a.c();
    }

    @Online
    public Turn getTurn() {
        return this.a.d();
    }

    @Online
    public TransportMode getTransportMode() {
        return this.a.e();
    }

    @Online
    public int getDistanceFromStart() {
        return this.a.getDistanceFromStart();
    }

    @Online
    public int getDistanceFromPreviousManeuver() {
        return this.a.getDistanceFromPreviousManeuver();
    }

    @Online
    public int getDistanceToNextManeuver() {
        return this.a.getDistanceToNextManeuver();
    }

    @Online
    public String getRoadName() {
        return this.a.getRoadName();
    }

    @Online
    public String getNextRoadName() {
        return this.a.getNextRoadName();
    }

    @Online
    public String getRoadNumber() {
        return this.a.getRoadNumber();
    }

    @Online
    public String getNextRoadNumber() {
        return this.a.getNextRoadNumber();
    }

    @Online
    public TrafficDirection getTrafficDirection() {
        return this.a.f();
    }

    @Online
    public Icon getIcon() {
        return this.a.g();
    }

    @Online
    public int getAngle() {
        return this.a.getAngle();
    }

    @Online
    public int getMapOrientation() {
        return this.a.getMapOrientation();
    }

    @Online
    public Date getStartTime() {
        return this.a.h();
    }

    @HybridPlus
    public List<RoadElement> getRoadElements() {
        return this.a.i();
    }

    @HybridPlus
    public List<RouteElement> getRouteElements() {
        return this.a.j();
    }

    @Online
    public List<GeoCoordinate> getManeuverGeometry() {
        return this.a.k();
    }

    @Online
    public Signpost getSignpost() {
        return this.a.l();
    }

    @HybridPlus
    public Image getNextRoadImage() {
        return this.a.m();
    }

    static {
        ManeuverImpl.a(new k<Maneuver, ManeuverImpl>() {
            public ManeuverImpl a(Maneuver maneuver) {
                return maneuver.a;
            }
        }, new am<Maneuver, ManeuverImpl>() {
            public Maneuver a(ManeuverImpl maneuverImpl) {
                return maneuverImpl != null ? new Maneuver(maneuverImpl) : null;
            }
        });
    }
}
