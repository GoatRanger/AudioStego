package com.nokia.maps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.RoadElement.Attribute;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.routing.Maneuver.Action;
import com.here.android.mpa.routing.Maneuver.Icon;
import com.here.android.mpa.routing.Maneuver.TrafficDirection;
import com.here.android.mpa.routing.Maneuver.Turn;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RouteTta.Detail;
import com.nokia.maps.restrouting.BoundingBox;
import com.nokia.maps.restrouting.Link;
import com.nokia.maps.restrouting.Note;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

final class ee {
    static final int a = Color.rgb(22, 82, 180);
    static final int b = Color.rgb(141, 174, 217);

    class a {
        private GeoCoordinate a;
        private GeoCoordinate b;

        public a(GeoCoordinate geoCoordinate, GeoCoordinate geoCoordinate2) {
            this.a = geoCoordinate;
            this.b = geoCoordinate2;
        }

        public double a() {
            return this.a.getLongitude() - this.b.getLongitude();
        }

        public double b() {
            return this.a.getLatitude() - this.b.getLatitude();
        }
    }

    enum b {
        BUS_PUBLIC("busPublic"),
        BUS_TOURISTIC("busTouristic"),
        BUS_INTERCITY("busIntercity"),
        BUS_EXPRESS("busExpress"),
        RAIL_METRO("railMetro"),
        RAIL_METRO_REGIONAL("railMetroRegional"),
        RAIL_LIGHT("railLight"),
        RAIL_REGIONAL("railRegional"),
        TRAIN_REGIONAL("trainRegional"),
        TRAIN_INTERCITY("trainIntercity"),
        TRAIN_HIGH_SPEED("trainHighSpeed"),
        MONORAIL("monoRail"),
        AERIAL("aerial"),
        INCLINED("inclined"),
        WATER("water"),
        PRIVATE_SERVICE("privateService");
        
        private String q;

        private b(String str) {
            this.q = str;
        }

        public String a() {
            return this.q;
        }
    }

    enum c {
        BOAT_FERRY("boatFerry"),
        DIRT_ROAD("dirtRoad"),
        FOUR_WHEEL_DRIVE("fourWheelDrive"),
        GATED_AREA("gatedArea"),
        HOV_LANE("HOVLane"),
        MOTORWAY("motorway"),
        NO_THROUGH_ROAD("noThroughRoad"),
        PARK("park"),
        PRIVATE_ROAD("privateRoad"),
        RAIL_FERRY("railFerry"),
        SCENIC("scenic"),
        STAIRS("stairs"),
        STATION("station"),
        TOLLROAD("tollroad"),
        TUNNEL("tunnel"),
        UNPAVED("unpaved"),
        BUILT_UP_AREA("builtUpArea");
        
        private String r;

        private c(String str) {
            this.r = str;
        }

        public String a() {
            return this.r;
        }
    }

    enum d {
        COPYRIGHT("copyright"),
        ROUTING_OPTION_VIOLATED("routingOptionViolated"),
        PASSING_PLACE("passingPlace"),
        ROAD_NAME_CHANGED("roadNameChanged"),
        SHARP_CURVE_AHEAD("sharpCurveAhead"),
        LINK_FEATURE_AHEAD("linkFeatureAhead"),
        TIME_DEPENDENT_RESTRICTION("timeDependentRestriction"),
        PREVIOUS_INTERSECTION("previousIntersection"),
        NEXT_INTERSECTION("nextIntersection"),
        ADMIN_DIVISION_CHANGE("adminDivisionChange"),
        COUNTRY_CHANGE("countryChange"),
        GATE_ACCESS("gateAccess"),
        PRIVATE_ROAD("privateRoad"),
        TOLL_BOOTH("tollbooth"),
        TOLL_ROAD("tollroad"),
        UNPAVED_ROAD("unpavedRoad"),
        RESTRICTED_TURN("restrictedTurn"),
        SEASONAL_CLOSURES("seasonalClosures"),
        CONGESTION("congestion"),
        ROADWORKS("roadworks"),
        ACCIDENT("accident"),
        CLOSURE("closure,"),
        TRAFFIC_FLOW("trafficFlow");
        
        private String x;

        private d(String str) {
            this.x = str;
        }
    }

    enum e {
        DEPART("depart"),
        DEPART_AIRPORT("departAirport"),
        ARRIVE("arrive"),
        ARRIVE_AIRPORT("arriveAirport"),
        ARRIVE_LEFT("arriveLeft"),
        ARRIVE_RIGHT("arriveRight"),
        LEFT_LOOP("leftLoop"),
        LEFT_UTURN("leftUTurn"),
        SHARP_LEFT_TURN("sharpLeftTurn"),
        LEFT_TURN("leftTurn"),
        SLIGHT_LEFT_TURN("slightLeftTurn"),
        CONTINUE("continue"),
        SLIGHT_RIGHT_TURN("slightRightTurn"),
        RIGHT_TURN("rightTurn"),
        SHARP_RIGHT_TURN("sharpRightTurn"),
        RIGHT_UTURN("rightUTurn"),
        RIGHT_LOOP("rightLoop"),
        LEFT_EXIT("leftExit"),
        RIGHT_EXIT("rightExit"),
        LEFT_RAMP("leftRamp"),
        RIGHT_RAMP("rightRamp"),
        LEFT_FORK("leftFork"),
        MIDDLE_FORK("middleFork"),
        RIGHT_FORK("rightFork"),
        LEFT_MERGE("leftMerge"),
        RIGHT_MERGE("rightMerge"),
        NAME_CHANGE("nameChange"),
        TRAFFIC_CIRCLE("trafficCircle"),
        FERRY("ferry"),
        LEFT_ROUNDABOUT_EXIT_1("leftRoundaboutExit1"),
        LEFT_ROUNDABOUT_EXIT_2("leftRoundaboutExit2"),
        LEFT_ROUNDABOUT_EXIT_3("leftRoundaboutExit3"),
        LEFT_ROUNDABOUT_EXIT_4("leftRoundaboutExit4"),
        LEFT_ROUNDABOUT_EXIT_5("leftRoundaboutExit5"),
        LEFT_ROUNDABOUT_EXIT_6("leftRoundaboutExit6"),
        LEFT_ROUNDABOUT_EXIT_7("leftRoundaboutExit7"),
        LEFT_ROUNDABOUT_EXIT_8("leftRoundaboutExit8"),
        LEFT_ROUNDABOUT_EXIT_9("leftRoundaboutExit9"),
        LEFT_ROUNDABOUT_EXIT_10("leftRoundaboutExit10"),
        LEFT_ROUNDABOUT_EXIT_11("leftRoundaboutExit11"),
        LEFT_ROUNDABOUT_EXIT_12("leftRoundaboutExit12"),
        RIGHT_ROUNDABOUT_EXIT_1("rightRoundaboutExit1"),
        RIGHT_ROUNDABOUT_EXIT_2("rightRoundaboutExit2"),
        RIGHT_ROUNDABOUT_EXIT_3("rightRoundaboutExit3"),
        RIGHT_ROUNDABOUT_EXIT_4("rightRoundaboutExit4"),
        RIGHT_ROUNDABOUT_EXIT_5("rightRoundaboutExit5"),
        RIGHT_ROUNDABOUT_EXIT_6("rightRoundaboutExit6"),
        RIGHT_ROUNDABOUT_EXIT_7("rightRoundaboutExit7"),
        RIGHT_ROUNDABOUT_EXIT_8("rightRoundaboutExit8"),
        RIGHT_ROUNDABOUT_EXIT_9("rightRoundaboutExit9"),
        RIGHT_ROUNDABOUT_EXIT_10("rightRoundaboutExit10"),
        RIGHT_ROUNDABOUT_EXIT_11("rightRoundaboutExit11"),
        RIGHT_ROUNDABOUT_EXIT_12("rightRoundaboutExit12"),
        ENTER("enter"),
        CHANGE("change"),
        LEAVE("leave");
        
        private String ae;

        private e(String str) {
            this.ae = str;
        }
    }

    static Action a(String str) {
        Action action = Action.UNDEFINED;
        if (e.ARRIVE.ae.contentEquals(str)) {
            return Action.END;
        }
        if (e.ARRIVE_AIRPORT.ae.contentEquals(str)) {
            return Action.END;
        }
        if (e.ARRIVE_LEFT.ae.contentEquals(str)) {
            return Action.END;
        }
        if (e.ARRIVE_RIGHT.ae.contentEquals(str)) {
            return Action.END;
        }
        if (e.CONTINUE.ae.contentEquals(str)) {
            return Action.CONTINUE_HIGHWAY;
        }
        if (e.DEPART.ae.contentEquals(str)) {
            return Action.UNDEFINED;
        }
        if (e.DEPART_AIRPORT.ae.contentEquals(str)) {
            return Action.UNDEFINED;
        }
        if (e.FERRY.ae.contentEquals(str)) {
            return Action.FERRY;
        }
        if (e.LEFT_EXIT.ae.contentEquals(str)) {
            return Action.LEAVE_HIGHWAY;
        }
        if (e.LEFT_FORK.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.LEFT_LOOP.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.LEFT_MERGE.ae.contentEquals(str)) {
            return Action.ENTER_HIGHWAY_FROM_RIGHT;
        }
        if (e.LEFT_RAMP.ae.contentEquals(str)) {
            return Action.LEAVE_HIGHWAY;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_1.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_2.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_3.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_4.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_5.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_6.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_7.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_8.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_9.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_10.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_11.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_12.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.LEFT_TURN.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.LEFT_UTURN.ae.contentEquals(str)) {
            return Action.UTURN;
        }
        if (e.MIDDLE_FORK.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.RIGHT_EXIT.ae.contentEquals(str)) {
            return Action.LEAVE_HIGHWAY;
        }
        if (e.RIGHT_FORK.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.RIGHT_LOOP.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.RIGHT_MERGE.ae.contentEquals(str)) {
            return Action.ENTER_HIGHWAY_FROM_LEFT;
        }
        if (e.RIGHT_RAMP.ae.contentEquals(str)) {
            return Action.LEAVE_HIGHWAY;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_1.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_2.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_3.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_4.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_5.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_6.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_7.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_8.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_9.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_10.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_11.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_12.ae.contentEquals(str)) {
            return Action.ROUNDABOUT;
        }
        if (e.RIGHT_TURN.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.RIGHT_UTURN.ae.contentEquals(str)) {
            return Action.UTURN;
        }
        if (e.SHARP_LEFT_TURN.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.SHARP_RIGHT_TURN.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.SLIGHT_LEFT_TURN.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.SLIGHT_RIGHT_TURN.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.TRAFFIC_CIRCLE.ae.contentEquals(str)) {
            return Action.JUNCTION;
        }
        if (e.ENTER.ae.contentEquals(str)) {
            return Action.PASS_STATION;
        }
        if (e.CHANGE.ae.contentEquals(str)) {
            return Action.CHANGE_LINE;
        }
        return action;
    }

    static Turn b(String str) {
        Turn turn = Turn.UNDEFINED;
        if (e.ARRIVE.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        if (e.ARRIVE_AIRPORT.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        if (e.ARRIVE_LEFT.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        if (e.ARRIVE_RIGHT.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        if (e.CONTINUE.ae.contentEquals(str)) {
            return Turn.NO_TURN;
        }
        if (e.DEPART.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        if (e.DEPART_AIRPORT.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        if (e.FERRY.ae.contentEquals(str)) {
            return Turn.NO_TURN;
        }
        if (e.LEFT_EXIT.ae.contentEquals(str)) {
            return Turn.KEEP_LEFT;
        }
        if (e.LEFT_FORK.ae.contentEquals(str)) {
            return Turn.KEEP_LEFT;
        }
        if (e.LEFT_LOOP.ae.contentEquals(str)) {
            return Turn.HEAVY_LEFT;
        }
        if (e.LEFT_MERGE.ae.contentEquals(str)) {
            return Turn.LIGHT_LEFT;
        }
        if (e.LEFT_RAMP.ae.contentEquals(str)) {
            return Turn.KEEP_LEFT;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_1.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_1;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_2.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_2;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_3.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_3;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_4.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_4;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_5.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_5;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_6.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_6;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_7.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_7;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_8.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_8;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_9.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_9;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_10.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_10;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_11.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_11;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_12.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_12;
        }
        if (e.LEFT_TURN.ae.contentEquals(str)) {
            return Turn.QUITE_LEFT;
        }
        if (e.LEFT_UTURN.ae.contentEquals(str)) {
            return Turn.RETURN;
        }
        if (e.MIDDLE_FORK.ae.contentEquals(str)) {
            return Turn.KEEP_MIDDLE;
        }
        if (e.RIGHT_EXIT.ae.contentEquals(str)) {
            return Turn.KEEP_RIGHT;
        }
        if (e.RIGHT_FORK.ae.contentEquals(str)) {
            return Turn.KEEP_RIGHT;
        }
        if (e.RIGHT_LOOP.ae.contentEquals(str)) {
            return Turn.HEAVY_RIGHT;
        }
        if (e.RIGHT_MERGE.ae.contentEquals(str)) {
            return Turn.LIGHT_RIGHT;
        }
        if (e.RIGHT_RAMP.ae.contentEquals(str)) {
            return Turn.KEEP_RIGHT;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_1.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_1;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_2.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_2;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_3.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_3;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_4.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_4;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_5.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_5;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_6.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_6;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_7.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_7;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_8.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_8;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_9.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_9;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_10.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_10;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_11.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_11;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_12.ae.contentEquals(str)) {
            return Turn.ROUNDABOUT_12;
        }
        if (e.RIGHT_TURN.ae.contentEquals(str)) {
            return Turn.QUITE_RIGHT;
        }
        if (e.RIGHT_UTURN.ae.contentEquals(str)) {
            return Turn.RETURN;
        }
        if (e.SHARP_LEFT_TURN.ae.contentEquals(str)) {
            return Turn.HEAVY_LEFT;
        }
        if (e.SHARP_RIGHT_TURN.ae.contentEquals(str)) {
            return Turn.HEAVY_RIGHT;
        }
        if (e.SLIGHT_LEFT_TURN.ae.contentEquals(str)) {
            return Turn.LIGHT_LEFT;
        }
        if (e.SLIGHT_RIGHT_TURN.ae.contentEquals(str)) {
            return Turn.LIGHT_RIGHT;
        }
        if (e.TRAFFIC_CIRCLE.ae.contentEquals(str)) {
            return Turn.NO_TURN;
        }
        if (e.ENTER.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        if (e.LEAVE.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        if (e.CHANGE.ae.contentEquals(str)) {
            return Turn.UNDEFINED;
        }
        return turn;
    }

    static Icon c(String str) {
        Icon icon = Icon.UNDEFINED;
        if (e.ARRIVE.ae.contentEquals(str)) {
            return Icon.END;
        }
        if (e.ARRIVE_AIRPORT.ae.contentEquals(str)) {
            return Icon.END;
        }
        if (e.ARRIVE_LEFT.ae.contentEquals(str)) {
            return Icon.END;
        }
        if (e.ARRIVE_RIGHT.ae.contentEquals(str)) {
            return Icon.END;
        }
        if (e.CONTINUE.ae.contentEquals(str)) {
            return Icon.GO_STRAIGHT;
        }
        if (e.DEPART.ae.contentEquals(str)) {
            return Icon.START;
        }
        if (e.DEPART_AIRPORT.ae.contentEquals(str)) {
            return Icon.START;
        }
        if (e.FERRY.ae.contentEquals(str)) {
            return Icon.FERRY;
        }
        if (e.LEFT_EXIT.ae.contentEquals(str)) {
            return Icon.LEAVE_HIGHWAY_LEFT_LANE;
        }
        if (e.LEFT_FORK.ae.contentEquals(str)) {
            return Icon.KEEP_LEFT;
        }
        if (e.LEFT_LOOP.ae.contentEquals(str)) {
            return Icon.HEAVY_LEFT;
        }
        if (e.LEFT_MERGE.ae.contentEquals(str)) {
            return Icon.ENTER_HIGHWAY_RIGHT_LANE;
        }
        if (e.LEFT_RAMP.ae.contentEquals(str)) {
            return Icon.LEAVE_HIGHWAY_LEFT_LANE;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_1.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_1;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_2.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_2;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_3.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_3;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_4.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_4;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_5.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_5;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_6.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_6;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_7.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_7;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_8.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_8;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_9.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_9;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_10.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_10;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_11.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_11;
        }
        if (e.LEFT_ROUNDABOUT_EXIT_12.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_12;
        }
        if (e.LEFT_TURN.ae.contentEquals(str)) {
            return Icon.QUITE_LEFT;
        }
        if (e.LEFT_UTURN.ae.contentEquals(str)) {
            return Icon.UTURN_LEFT;
        }
        if (e.MIDDLE_FORK.ae.contentEquals(str)) {
            return Icon.KEEP_MIDDLE;
        }
        if (e.RIGHT_EXIT.ae.contentEquals(str)) {
            return Icon.LEAVE_HIGHWAY_RIGHT_LANE;
        }
        if (e.RIGHT_FORK.ae.contentEquals(str)) {
            return Icon.KEEP_RIGHT;
        }
        if (e.RIGHT_LOOP.ae.contentEquals(str)) {
            return Icon.HEAVY_RIGHT;
        }
        if (e.RIGHT_MERGE.ae.contentEquals(str)) {
            return Icon.ENTER_HIGHWAY_LEFT_LANE;
        }
        if (e.RIGHT_RAMP.ae.contentEquals(str)) {
            return Icon.LEAVE_HIGHWAY_RIGHT_LANE;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_1.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_1;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_2.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_2;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_3.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_3;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_4.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_4;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_5.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_5;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_6.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_6;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_7.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_7;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_8.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_8;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_9.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_9;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_10.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_10;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_11.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_11;
        }
        if (e.RIGHT_ROUNDABOUT_EXIT_12.ae.contentEquals(str)) {
            return Icon.ROUNDABOUT_12;
        }
        if (e.RIGHT_TURN.ae.contentEquals(str)) {
            return Icon.QUITE_RIGHT;
        }
        if (e.RIGHT_UTURN.ae.contentEquals(str)) {
            return Icon.UTURN_RIGHT;
        }
        if (e.SHARP_LEFT_TURN.ae.contentEquals(str)) {
            return Icon.HEAVY_LEFT;
        }
        if (e.SHARP_RIGHT_TURN.ae.contentEquals(str)) {
            return Icon.HEAVY_RIGHT;
        }
        if (e.SLIGHT_LEFT_TURN.ae.contentEquals(str)) {
            return Icon.LIGHT_LEFT;
        }
        if (e.SLIGHT_RIGHT_TURN.ae.contentEquals(str)) {
            return Icon.LIGHT_RIGHT;
        }
        if (e.TRAFFIC_CIRCLE.ae.contentEquals(str)) {
            return Icon.UNDEFINED;
        }
        if (e.ENTER.ae.contentEquals(str)) {
            return Icon.PASS_STATION;
        }
        if (e.CHANGE.ae.contentEquals(str)) {
            return Icon.CHANGE_LINE;
        }
        return icon;
    }

    static EnumSet<Detail> a(List<Note> list) {
        EnumSet<Detail> noneOf = EnumSet.noneOf(Detail.class);
        for (Note note : list) {
            if (d.CLOSURE.x.contentEquals(note.b())) {
                noneOf.add(Detail.BLOCKED_ROAD);
            } else if (d.RESTRICTED_TURN.x.contentEquals(note.b())) {
                noneOf.add(Detail.RESTRICTED_TURN);
            }
            if (noneOf.size() == 2) {
                break;
            }
        }
        return noneOf;
    }

    static Attribute d(String str) {
        if (c.BOAT_FERRY.a().contentEquals(str)) {
            return Attribute.FERRY;
        }
        if (c.DIRT_ROAD.a().contentEquals(str)) {
            return Attribute.DIRT_ROAD;
        }
        if (c.FOUR_WHEEL_DRIVE.a().contentEquals(str)) {
            return Attribute.DIRT_ROAD;
        }
        if (c.GATED_AREA.a().contentEquals(str)) {
            return Attribute.NO_THROUGH_TRAFFIC;
        }
        if (c.HOV_LANE.a().contentEquals(str)) {
            return Attribute.CARPOOL;
        }
        if (c.MOTORWAY.a().contentEquals(str)) {
            return Attribute.HIGHWAY;
        }
        if (c.NO_THROUGH_ROAD.a().contentEquals(str)) {
            return Attribute.NO_THROUGH_TRAFFIC;
        }
        if (c.PARK.a().contentEquals(str)) {
            return Attribute.EXPLICATION;
        }
        if (c.PRIVATE_ROAD.a().contentEquals(str)) {
            return Attribute.NO_THROUGH_TRAFFIC;
        }
        if (c.RAIL_FERRY.a().contentEquals(str)) {
            return Attribute.FERRY;
        }
        if (c.SCENIC.a().contentEquals(str)) {
            return Attribute.EXPLICATION;
        }
        if (c.STAIRS.a().contentEquals(str)) {
            return Attribute.EXPLICATION;
        }
        if (c.STATION.a().contentEquals(str)) {
            return Attribute.EXPLICATION;
        }
        if (c.TOLLROAD.a().contentEquals(str)) {
            return Attribute.TOLLROAD;
        }
        if (c.TUNNEL.a().contentEquals(str)) {
            return Attribute.TUNNEL;
        }
        if (c.UNPAVED.a().contentEquals(str)) {
            return Attribute.DIRT_ROAD;
        }
        if (c.BUILT_UP_AREA.a().contentEquals(str)) {
            return Attribute.URBAN;
        }
        return null;
    }

    static List<GeoCoordinate> b(List<Double> list) {
        List<GeoCoordinate> arrayList = new ArrayList();
        if (list != null) {
            int i = 0;
            while (i < list.size() - 1) {
                int i2 = i + 1;
                arrayList.add(new GeoCoordinate(((Double) list.get(i)).doubleValue(), ((Double) list.get(i2)).doubleValue()));
                i = i2 + 1;
            }
        }
        return arrayList;
    }

    static String a(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(date);
        return (format.length() - 2);
    }

    static Date e(String str) {
        Date date = null;
        StringBuilder stringBuilder = new StringBuilder(str);
        int length = stringBuilder.length();
        if (stringBuilder.charAt(length - 1) == 'Z') {
            stringBuilder.replace(length - 1, length - 1, "+0000");
        } else if (stringBuilder.length() > 2 && stringBuilder.lastIndexOf(":") == stringBuilder.length() - 3) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 3);
        }
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).parse(stringBuilder.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    static GeoBoundingBox a(BoundingBox boundingBox) {
        if (boundingBox == null) {
            return null;
        }
        com.nokia.maps.restrouting.GeoCoordinate a = boundingBox.a();
        com.nokia.maps.restrouting.GeoCoordinate b = boundingBox.b();
        return new GeoBoundingBox(new GeoCoordinate(a.a().doubleValue(), a.b().doubleValue()), new GeoCoordinate(b.a().doubleValue(), b.b().doubleValue()));
    }

    static TrafficDirection f(String str) {
        TrafficDirection trafficDirection = TrafficDirection.RIGHT;
        if (str.contentEquals("ATG") || str.contentEquals("AIA") || str.contentEquals("AUS") || str.contentEquals("BRB") || str.contentEquals("BGD") || str.contentEquals("BMU") || str.contentEquals("BRN") || str.contentEquals("BHS") || str.contentEquals("BTN") || str.contentEquals("BWA") || str.contentEquals("CCK") || str.contentEquals("COK") || str.contentEquals("CXR") || str.contentEquals("CYP") || str.contentEquals("DOM") || str.contentEquals("FJI") || str.contentEquals("FLK") || str.contentEquals("GBR") || str.contentEquals("GRD") || str.contentEquals("GGY") || str.contentEquals("GUY") || str.contentEquals("HKG") || str.contentEquals("IDN") || str.contentEquals("IRL") || str.contentEquals("IMN") || str.contentEquals("IND") || str.contentEquals("JEY") || str.contentEquals("JAM") || str.contentEquals("JPN") || str.contentEquals("KEN") || str.contentEquals("KIR") || str.contentEquals("KNA") || str.contentEquals("CYM") || str.contentEquals("LCA") || str.contentEquals("LKA") || str.contentEquals("LSO") || str.contentEquals("MAC") || str.contentEquals("MSE") || str.contentEquals("MLT") || str.contentEquals("MUS") || str.contentEquals("MDV") || str.contentEquals("MWI") || str.contentEquals("MYS") || str.contentEquals("MOZ") || str.contentEquals("NAM") || str.contentEquals("NFK") || str.contentEquals("NPL") || str.contentEquals("NRU") || str.contentEquals("NIU") || str.contentEquals("NZL") || str.contentEquals("PNG") || str.contentEquals("PAK") || str.contentEquals("PCN") || str.contentEquals("SLB") || str.contentEquals("SYC") || str.contentEquals("SGP") || str.contentEquals("SHN") || str.contentEquals("SUR") || str.contentEquals("SWZ") || str.contentEquals("TCA") || str.contentEquals("THA") || str.contentEquals("TKL") || str.contentEquals("TLS") || str.contentEquals("TON") || str.contentEquals("TTO") || str.contentEquals("TUV") || str.contentEquals("TZA") || str.contentEquals("UGA") || str.contentEquals("VCT") || str.contentEquals("VGB") || str.contentEquals("VIR") || str.contentEquals("WSM") || str.contentEquals("ZAF") || str.contentEquals("ZMN") || str.contentEquals("ZWE")) {
            return TrafficDirection.LEFT;
        }
        return trafficDirection;
    }

    public static int a(Link link, Link link2) {
        Double valueOf = Double.valueOf(3.141592653589793d);
        if (!(link == null || link2 == null)) {
            List b = b(link.d());
            double distanceTo = ((GeoCoordinate) b.get(b.size() - 2)).distanceTo((GeoCoordinate) b.get(b.size() - 1));
            List b2 = b(link2.d());
            double distanceTo2 = ((GeoCoordinate) b2.get(0)).distanceTo((GeoCoordinate) b2.get(1));
            double distanceTo3 = ((GeoCoordinate) b.get(b.size() - 2)).distanceTo((GeoCoordinate) b2.get(1));
            valueOf = Double.valueOf(Math.acos((((distanceTo * distanceTo) + (distanceTo2 * distanceTo2)) - (distanceTo3 * distanceTo3)) / ((distanceTo * 2.0d) * distanceTo2)));
            if (a(b, b2).doubleValue() < 0.0d) {
                valueOf = Double.valueOf(6.283185307179586d - valueOf.doubleValue());
            }
        }
        return (int) Math.round(Math.toDegrees(valueOf.doubleValue()));
    }

    private static Double a(List<GeoCoordinate> list, List<GeoCoordinate> list2) {
        a aVar = new a((GeoCoordinate) list.get(list.size() - 2), (GeoCoordinate) list.get(list.size() - 1));
        a aVar2 = new a((GeoCoordinate) list2.get(0), (GeoCoordinate) list2.get(1));
        double b = aVar.b() / Math.sqrt(Math.pow(aVar.a(), 2.0d) + Math.pow(aVar.b(), 2.0d));
        double a = aVar2.a() / Math.sqrt(Math.pow(aVar2.a(), 2.0d) + Math.pow(aVar2.b(), 2.0d));
        return Double.valueOf(((aVar.a() / Math.sqrt(Math.pow(aVar.a(), 2.0d) + Math.pow(aVar.b(), 2.0d))) * (aVar2.b() / Math.sqrt(Math.pow(aVar2.b(), 2.0d) + Math.pow(aVar2.a(), 2.0d)))) - (a * b));
    }

    static boolean g(String str) {
        return e.CHANGE.ae.contentEquals(str);
    }

    static boolean h(String str) {
        return e.LEAVE.ae.contentEquals(str);
    }

    static Image a(int i) {
        Image image = new Image();
        Bitmap createBitmap = Bitmap.createBitmap(20, 20, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setColor(-1);
        paint.setStyle(Style.FILL);
        canvas.drawPaint(paint);
        paint.setColor(-16777216);
        paint.setTextSize(DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        Rect rect = new Rect();
        String valueOf = String.valueOf(i);
        paint.getTextBounds(valueOf, 0, valueOf.length(), rect);
        canvas.drawText(valueOf, (((float) createBitmap.getWidth()) - ((float) rect.width())) / 2.0f, (((float) rect.height()) + ((float) createBitmap.getHeight())) / 2.0f, paint);
        paint.setStrokeWidth(0.0f);
        paint.setStyle(Style.STROKE);
        canvas.drawRect(0.0f, 0.0f, 20.0f, 20.0f, paint);
        image.setBitmap(createBitmap);
        return image;
    }

    static int a(double d, double d2, int i, int i2) {
        float c = c(d2, i2);
        float a = a(d, i);
        if (d < 20.0d && c > 0.001f) {
            a += c * (a(1.0d + d, i) - a);
        }
        return (int) a;
    }

    static int[] b(double d, double d2, int i, int i2) {
        int[] iArr = new int[2];
        float c = c(d2, i2);
        float[] b = b(d, i);
        float[] b2 = b(1.0d + d, i);
        for (int i3 = 0; i3 < b.length; i3++) {
            if (d >= 20.0d || c <= 0.001f) {
                iArr[i3] = (int) b[i3];
            } else {
                iArr[i3] = (int) (((b2[i3] - b[i3]) * c) + b[i3]);
            }
        }
        return iArr;
    }

    private static float a(double d, int i) {
        return b(d) * b(i);
    }

    private static float[] b(double d, int i) {
        float b = b(i);
        float[] a = a(d);
        for (int i2 = 0; i2 < a.length; i2++) {
            a[i2] = a[i2] * b;
        }
        return a;
    }

    private static float[] a(double d) {
        float f = 7.0f;
        float f2 = 6.0f;
        if (0.0d <= d && d < 6.0d) {
            f2 = 5.0f;
        } else if (d < 7.0d) {
            f = 8.0f;
        } else if (d < 11.0d) {
            f = 9.0f;
        } else if (d < 15.0d) {
            f2 = 7.0f;
            f = 10.0f;
        } else if (d < 16.0d) {
            f2 = 9.0f;
            f = 11.0f;
        } else if (d < 17.0d) {
            f2 = 11.0f;
            f = 13.0f;
        } else if (d < 18.0d) {
            f = DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity;
            f2 = 13.0f;
        } else if (d < 19.0d) {
            f = 17.0f;
            f2 = DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity;
        } else if (d < 20.0d) {
            f = 21.0f;
            f2 = 19.0f;
        } else {
            f = 33.0f;
            f2 = 31.0f;
        }
        return new float[]{f, f2};
    }

    private static float b(double d) {
        if (0.0d <= d && d < 6.0d) {
            return 2.0f;
        }
        if (d < 8.0d) {
            return 3.0f;
        }
        if (d < 16.0d) {
            return DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
        }
        if (d < 17.0d) {
            return 5.0f;
        }
        if (d < 18.0d) {
            return 7.0f;
        }
        if (d < 19.0d) {
            return 12.0f;
        }
        if (d < 20.0d) {
            return dji.gs.e.b.a;
        }
        return 25.0f;
    }

    private static float b(int i) {
        return (((float) (i - 72)) * 0.00139f) + 1.0f;
    }

    private static float c(double d, int i) {
        return e(d(d, i), i) - 1.0f;
    }

    private static double d(double d, int i) {
        double f = f(3.18767104E8d, i);
        if (d <= 0.0d) {
            return f;
        }
        return Math.min((4.0007863E9d * ((double) i)) / d, f);
    }

    private static float e(double d, int i) {
        long g = (long) g(d, i);
        return (float) (((double) g) / ((double) (1 << ((int) a(g)))));
    }

    private static double f(double d, int i) {
        return (125.0d + (((double) i) * d)) / 250.0d;
    }

    private static double g(double d, int i) {
        return (double) ((((long) (i >> 1)) + (((long) d) * 250)) / ((long) i));
    }

    private static long a(long j) {
        return b(j);
    }

    private static long b(long j) {
        long j2;
        long j3;
        if ((j & -65536) > 0) {
            j2 = -65536 << 8;
            j3 = 16;
        } else {
            j2 = -65536 >> 8;
            j3 = 0;
        }
        if ((j & j2) > 0) {
            j2 <<= 4;
            j3 |= 8;
        } else {
            j2 >>= 4;
        }
        if ((j & j2) > 0) {
            j2 <<= 2;
            j3 |= 4;
        } else {
            j2 >>= 2;
        }
        if ((j & j2) > 0) {
            j2 <<= 1;
            j3 |= 2;
        } else {
            j2 >>= 1;
        }
        if ((j2 & j) > 0) {
            return j3 | 1;
        }
        return j3;
    }

    static String a(RouteOptions routeOptions) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.BUS_PUBLIC)) {
            stringBuilder.append(b.BUS_PUBLIC.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.BUS_TOURISTIC)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.BUS_TOURISTIC.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.BUS_INTERCITY)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.BUS_INTERCITY.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.BUS_EXPRESS)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.BUS_EXPRESS.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.RAIL_METRO)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.RAIL_METRO.a()).append(",").append(b.RAIL_METRO_REGIONAL.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.RAIL_LIGHT)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.RAIL_LIGHT.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.RAIL_REGIONAL)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.RAIL_REGIONAL.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.TRAIN_REGIONAL)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.TRAIN_REGIONAL.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.TRAIN_INTERCITY)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.TRAIN_INTERCITY.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.TRAIN_HIGH_SPEED)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.TRAIN_HIGH_SPEED.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.MONORAIL)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.MONORAIL.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.AERIAL)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.AERIAL.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.INCLINED)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.INCLINED.a());
        }
        if (!routeOptions.isPublicTransportTypeAllowed(TransitType.WATER)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b.WATER.a());
        }
        return stringBuilder.toString();
    }

    static TransitType i(String str) {
        TransitType transitType = TransitType.UNKNOWN;
        if (str.equals(b.BUS_PUBLIC.a())) {
            return TransitType.BUS_PUBLIC;
        }
        if (str.equals(b.BUS_TOURISTIC.a())) {
            return TransitType.BUS_TOURISTIC;
        }
        if (str.equals(b.BUS_INTERCITY.a())) {
            return TransitType.BUS_INTERCITY;
        }
        if (str.equals(b.BUS_EXPRESS.a())) {
            return TransitType.BUS_EXPRESS;
        }
        if (str.equals(b.RAIL_METRO.a()) || str.equals(b.RAIL_METRO_REGIONAL.a())) {
            return TransitType.RAIL_METRO;
        }
        if (str.equals(b.RAIL_LIGHT.a())) {
            return TransitType.RAIL_LIGHT;
        }
        if (str.equals(b.RAIL_REGIONAL.a())) {
            return TransitType.RAIL_REGIONAL;
        }
        if (str.equals(b.TRAIN_REGIONAL.a())) {
            return TransitType.TRAIN_REGIONAL;
        }
        if (str.equals(b.TRAIN_INTERCITY.a())) {
            return TransitType.TRAIN_INTERCITY;
        }
        if (str.equals(b.TRAIN_HIGH_SPEED.a())) {
            return TransitType.TRAIN_HIGH_SPEED;
        }
        if (str.equals(b.MONORAIL.a())) {
            return TransitType.MONORAIL;
        }
        if (str.equals(b.AERIAL.a())) {
            return TransitType.AERIAL;
        }
        if (str.equals(b.INCLINED.a())) {
            return TransitType.INCLINED;
        }
        if (str.equals(b.WATER.a())) {
            return TransitType.WATER;
        }
        return transitType;
    }
}
