package com.here.a.a.a.a;

import com.here.a.a.a.s;

public class v {
    public final a a;
    public final b b;
    public final long c;
    public final String d;
    public final n e;
    public final ad<String> f;
    public final ad<String> g;
    public final ad<Integer> h;

    public enum a {
        UNDEFINED,
        DEPART,
        DEPART_AIRPORT,
        ARRIVE,
        ARRIVE_AIRPORT,
        ARRIVE_LEFT,
        ARRIVE_RIGHT,
        LEFT_LOOP,
        LEFT_UTURN,
        SHARP_LEFT_TURN,
        LEFT_TURN,
        SLIGHT_LEFT_TURN,
        CONTINUE,
        SLIGHT_RIGHT_TURN,
        RIGHT_TURN,
        SHARP_RIGHT_TURN,
        RIGHT_UTURN,
        RIGHT_LOOP,
        LEFT_EXIT,
        RIGHT_EXIT,
        LEFT_RAMP,
        RIGHT_RAMP,
        LEFT_FORK,
        MIDDLE_FORK,
        RIGHT_FORK,
        LEFT_MERGE,
        RIGHT_MERGE,
        NAME_CHANGE,
        TRAFFIC_CIRCLE,
        FERRY,
        LEFT_ROUNDABOUT_EXIT_1,
        LEFT_ROUNDABOUT_EXIT_2,
        LEFT_ROUNDABOUT_EXIT_3,
        LEFT_ROUNDABOUT_EXIT_4,
        LEFT_ROUNDABOUT_EXIT_5,
        LEFT_ROUNDABOUT_EXIT_6,
        LEFT_ROUNDABOUT_EXIT_7,
        LEFT_ROUNDABOUT_EXIT_8,
        LEFT_ROUNDABOUT_EXIT_9,
        LEFT_ROUNDABOUT_EXIT_10,
        LEFT_ROUNDABOUT_EXIT_11,
        LEFT_ROUNDABOUT_EXIT_12,
        RIGHT_ROUNDABOUT_EXIT_1,
        RIGHT_ROUNDABOUT_EXIT_2,
        RIGHT_ROUNDABOUT_EXIT_3,
        RIGHT_ROUNDABOUT_EXIT_4,
        RIGHT_ROUNDABOUT_EXIT_5,
        RIGHT_ROUNDABOUT_EXIT_6,
        RIGHT_ROUNDABOUT_EXIT_7,
        RIGHT_ROUNDABOUT_EXIT_8,
        RIGHT_ROUNDABOUT_EXIT_9,
        RIGHT_ROUNDABOUT_EXIT_10,
        RIGHT_ROUNDABOUT_EXIT_11,
        RIGHT_ROUNDABOUT_EXIT_12,
        ENTER,
        CHANGE,
        LEAVE;

        public static a a(String str) {
            if ("depart".equalsIgnoreCase(str)) {
                return DEPART;
            }
            if ("departAirport".equalsIgnoreCase(str)) {
                return DEPART_AIRPORT;
            }
            if ("arrive".equalsIgnoreCase(str)) {
                return ARRIVE;
            }
            if ("arriveAirport".equalsIgnoreCase(str)) {
                return ARRIVE_AIRPORT;
            }
            if ("arriveLeft".equalsIgnoreCase(str)) {
                return ARRIVE_LEFT;
            }
            if ("arriveRight".equalsIgnoreCase(str)) {
                return ARRIVE_RIGHT;
            }
            if ("leftLoop".equalsIgnoreCase(str)) {
                return LEFT_LOOP;
            }
            if ("leftUTurn".equalsIgnoreCase(str)) {
                return LEFT_UTURN;
            }
            if ("sharpLeftTurn".equalsIgnoreCase(str)) {
                return SHARP_LEFT_TURN;
            }
            if ("leftTurn".equalsIgnoreCase(str)) {
                return LEFT_TURN;
            }
            if ("slightLeftTurn".equalsIgnoreCase(str)) {
                return SLIGHT_LEFT_TURN;
            }
            if ("continue".equalsIgnoreCase(str)) {
                return CONTINUE;
            }
            if ("slightRightTurn".equalsIgnoreCase(str)) {
                return SLIGHT_RIGHT_TURN;
            }
            if ("rightTurn".equalsIgnoreCase(str)) {
                return RIGHT_TURN;
            }
            if ("sharpRightTurn".equalsIgnoreCase(str)) {
                return SHARP_RIGHT_TURN;
            }
            if ("rightUTurn".equalsIgnoreCase(str)) {
                return RIGHT_UTURN;
            }
            if ("rightLoop".equalsIgnoreCase(str)) {
                return RIGHT_LOOP;
            }
            if ("leftExit".equalsIgnoreCase(str)) {
                return LEFT_EXIT;
            }
            if ("rightExit".equalsIgnoreCase(str)) {
                return RIGHT_EXIT;
            }
            if ("leftRamp".equalsIgnoreCase(str)) {
                return LEFT_RAMP;
            }
            if ("rightRamp".equalsIgnoreCase(str)) {
                return RIGHT_RAMP;
            }
            if ("leftFork".equalsIgnoreCase(str)) {
                return LEFT_FORK;
            }
            if ("middleFork".equalsIgnoreCase(str)) {
                return MIDDLE_FORK;
            }
            if ("rightFork".equalsIgnoreCase(str)) {
                return RIGHT_FORK;
            }
            if ("leftMerge".equalsIgnoreCase(str)) {
                return LEFT_MERGE;
            }
            if ("rightMerge".equalsIgnoreCase(str)) {
                return RIGHT_MERGE;
            }
            if ("nameChange".equalsIgnoreCase(str)) {
                return NAME_CHANGE;
            }
            if ("trafficCircle".equalsIgnoreCase(str)) {
                return TRAFFIC_CIRCLE;
            }
            if ("ferry".equalsIgnoreCase(str)) {
                return FERRY;
            }
            if ("leftRoundaboutExit1".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_1;
            }
            if ("leftRoundaboutExit2".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_2;
            }
            if ("leftRoundaboutExit3".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_3;
            }
            if ("leftRoundaboutExit4".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_4;
            }
            if ("leftRoundaboutExit5".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_5;
            }
            if ("leftRoundaboutExit6".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_6;
            }
            if ("leftRoundaboutExit7".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_7;
            }
            if ("leftRoundaboutExit8".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_8;
            }
            if ("leftRoundaboutExit9".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_9;
            }
            if ("leftRoundaboutExit10".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_10;
            }
            if ("leftRoundaboutExit11".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_11;
            }
            if ("leftRoundaboutExit12".equalsIgnoreCase(str)) {
                return LEFT_ROUNDABOUT_EXIT_12;
            }
            if ("rightRoundaboutExit1".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_1;
            }
            if ("rightRoundaboutExit2".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_2;
            }
            if ("rightRoundaboutExit3".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_3;
            }
            if ("rightRoundaboutExit4".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_4;
            }
            if ("rightRoundaboutExit5".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_5;
            }
            if ("rightRoundaboutExit6".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_6;
            }
            if ("rightRoundaboutExit7".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_7;
            }
            if ("rightRoundaboutExit8".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_8;
            }
            if ("rightRoundaboutExit9".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_9;
            }
            if ("rightRoundaboutExit10".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_10;
            }
            if ("rightRoundaboutExit11".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_11;
            }
            if ("rightRoundaboutExit12".equalsIgnoreCase(str)) {
                return RIGHT_ROUNDABOUT_EXIT_12;
            }
            if ("enter".equalsIgnoreCase(str)) {
                return ENTER;
            }
            if ("change".equalsIgnoreCase(str)) {
                return CHANGE;
            }
            if ("leave".equalsIgnoreCase(str)) {
                return LEAVE;
            }
            return UNDEFINED;
        }
    }

    public enum b {
        UNDEFINED,
        FORWARD,
        RIGHT,
        LEFT,
        BEAR_RIGHT,
        LIGHT_RIGHT,
        HARD_RIGHT,
        UTURN_RIGHT,
        UTURN_LEFT,
        HARD_LEFT,
        LIGHT_LEFT,
        BEAR_LEFT;

        public static b a(String str) {
            if ("forward".equalsIgnoreCase(str)) {
                return FORWARD;
            }
            if ("right".equalsIgnoreCase(str)) {
                return RIGHT;
            }
            if ("left".equalsIgnoreCase(str)) {
                return LEFT;
            }
            if ("bearRight".equalsIgnoreCase(str)) {
                return BEAR_RIGHT;
            }
            if ("lightRight".equalsIgnoreCase(str)) {
                return LIGHT_RIGHT;
            }
            if ("hardRight".equalsIgnoreCase(str)) {
                return HARD_RIGHT;
            }
            if ("uTurnRight".equalsIgnoreCase(str)) {
                return UTURN_RIGHT;
            }
            if ("uTurnLeft".equalsIgnoreCase(str)) {
                return UTURN_LEFT;
            }
            if ("hardLeft".equalsIgnoreCase(str)) {
                return HARD_LEFT;
            }
            if ("lightLeft".equalsIgnoreCase(str)) {
                return LIGHT_LEFT;
            }
            if ("bearLeft".equalsIgnoreCase(str)) {
                return BEAR_LEFT;
            }
            return UNDEFINED;
        }
    }

    public v(a aVar, b bVar, long j, String str, n nVar, String str2, String str3, Integer num) {
        if (aVar == null) {
            throw new IllegalArgumentException("Action can't be null!");
        } else if (bVar == null) {
            throw new IllegalArgumentException("Direction can't be null!");
        } else if (j < 0) {
            throw new IllegalArgumentException("Duration can't less than 0!");
        } else if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Instruction can't be null nor empty!");
        } else if (nVar == null) {
            throw new IllegalArgumentException("Graph can't be null!");
        } else {
            this.a = aVar;
            this.b = bVar;
            this.c = j;
            this.d = str;
            this.e = nVar;
            this.f = ad.b(str2);
            this.g = ad.b(str3);
            this.h = ad.b(num);
        }
    }

    public static v fromJSON(o oVar) {
        Integer num = null;
        a a = a.a(oVar.i("@action"));
        b a2 = b.a(oVar.i("@direction"));
        long b = s.b(oVar.i("@duration"));
        String i = oVar.i("Instruction");
        n a3 = n.a(oVar.i("Graph"));
        String a4 = oVar.a("@next_number", null);
        String a5 = oVar.a("@next_road", null);
        if (!oVar.b("@distance")) {
            num = oVar.j("@distance");
        }
        return new v(a, a2, b, i, a3, a4, a5, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        v vVar = (v) obj;
        if (this.c == vVar.c && this.a == vVar.a && this.b == vVar.b && this.d.equals(vVar.d) && this.e.equals(vVar.e) && this.f.equals(vVar.f) && this.g.equals(vVar.g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode();
    }
}
