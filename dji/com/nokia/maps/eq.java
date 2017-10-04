package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.routing.Maneuver.Action;
import com.here.android.mpa.routing.Maneuver.Icon;
import com.here.android.mpa.routing.Maneuver.TrafficDirection;
import com.here.android.mpa.routing.Maneuver.Turn;
import com.here.android.mpa.routing.RouteElement;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.Signpost;
import com.here.android.mpa.routing.TransitManeuver.TransitLineStyle;
import com.here.android.mpa.routing.TransitRouteElement;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.restrouting.Link;
import com.nokia.maps.restrouting.Maneuver;
import com.nokia.maps.restrouting.Note;
import com.nokia.maps.restrouting.PublicTransportLine;
import com.nokia.maps.restrouting.Stop;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@HybridPlus
public class eq extends TransitManeuverImpl {
    private int A;
    private int B;
    private int C;
    private long D;
    private int E;
    private int F;
    private TrafficDirection G;
    private boolean J;
    private boolean K;
    private List<TransitRouteElement> L = new ArrayList();
    private List<RouteElement> M = new ArrayList();
    private List<RoadElement> N = new ArrayList();
    private boolean O;
    private GeoCoordinate a;
    private GeoBoundingBox b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private List<GeoCoordinate> j;
    private Action k;
    private Turn l;
    private Icon m;
    private Date n;
    private List<Note> o;
    private String p;
    private String q;
    private String r;
    private String s;
    private TransitType t;
    private String u;
    private TransitLineStyle v;
    private int w;
    private int x;
    private int y;
    private int z;

    public enum a {
        SOLID("solid"),
        DOTTED("dotted"),
        DASHED("dash");
        
        private String d;

        private a(String str) {
            this.d = str;
        }

        public String a() {
            return this.d;
        }
    }

    eq(int i, Maneuver maneuver, PublicTransportLine publicTransportLine, List<Link> list, Link link, int i2, int i3, String str) {
        boolean z;
        super(0);
        this.C = i;
        this.h = i2;
        this.i = i3;
        this.z = ee.a(link, list.size() > 0 ? (Link) list.get(0) : null);
        this.G = ee.f(str);
        a(maneuver);
        if (maneuver.a().equals("PublicTransportManeuverType") || (list.size() > 0 && !((Link) list.get(0)).m().isEmpty())) {
            z = true;
        } else {
            z = false;
        }
        this.O = z;
        if (this.O) {
            this.F = (int) (((double) this.F) + maneuver.s().doubleValue());
        }
        if (this.O) {
            a(publicTransportLine, (List) list);
        } else {
            a((List) list);
        }
        this.j = new ArrayList();
        for (RouteElement geometry : this.M) {
            this.j.addAll(geometry.getGeometry());
        }
    }

    public GeoCoordinate a() {
        return this.a;
    }

    public GeoBoundingBox b() {
        return this.b;
    }

    public Action c() {
        return this.k;
    }

    public Turn d() {
        return this.l;
    }

    public TransportMode e() {
        return this.O ? TransportMode.PUBLIC_TRANSPORT : TransportMode.PEDESTRIAN;
    }

    public int getDistanceFromStart() {
        return this.h;
    }

    public int getDistanceFromPreviousManeuver() {
        return this.i;
    }

    public int getDistanceToNextManeuver() {
        return this.g;
    }

    public String getRoadName() {
        return this.c;
    }

    public String getNextRoadName() {
        return this.e;
    }

    public String getRoadNumber() {
        return this.d;
    }

    public String getNextRoadNumber() {
        return this.f;
    }

    public TrafficDirection f() {
        return this.G;
    }

    public Icon g() {
        return this.m;
    }

    public int getAngle() {
        return this.z;
    }

    public int getMapOrientation() {
        return this.y;
    }

    public Date h() {
        return new Date(this.n.getTime());
    }

    public List<RoadElement> i() {
        return this.N;
    }

    public List<RouteElement> j() {
        return this.M;
    }

    public List<GeoCoordinate> k() {
        return this.j;
    }

    public Signpost l() {
        return null;
    }

    public Image m() {
        return null;
    }

    public String getDepartureStopName() {
        return this.p;
    }

    public String getArrivalStopName() {
        return this.q;
    }

    public String getTerminusStopName() {
        return this.r;
    }

    public String getLineName() {
        return this.s;
    }

    public boolean n() {
        return this.J;
    }

    public boolean o() {
        return this.K;
    }

    public int p() {
        if (this.J) {
            return this.A;
        }
        return 0;
    }

    public int q() {
        if (this.K) {
            return this.B;
        }
        return 0;
    }

    public TransitLineStyle r() {
        return this.v;
    }

    public String getSystemOfficialName() {
        return "";
    }

    public String s() {
        return "";
    }

    public String getSystemShortName() {
        return "";
    }

    public String getTransitTypeName() {
        return this.u;
    }

    public TransitType getTransitType() {
        return this.t;
    }

    public int getTransitTravelTime() {
        return this.w;
    }

    public List<TransitRouteElement> v() {
        return this.L;
    }

    public boolean isTransit() {
        return this.O;
    }

    private void a(Maneuver maneuver) {
        this.o = new ArrayList();
        if (maneuver != null) {
            this.p = maneuver.p();
            this.a = new GeoCoordinate(maneuver.d().a().doubleValue(), maneuver.d().b().doubleValue());
            this.x = maneuver.c().intValue();
            this.w = maneuver.e().intValue();
            this.c = maneuver.k();
            this.d = maneuver.n();
            this.e = maneuver.j();
            this.f = maneuver.l();
            this.k = ee.a(maneuver.b());
            this.l = ee.b(maneuver.b());
            this.m = ee.c(maneuver.b());
            this.b = ee.a(maneuver.h());
            this.y = maneuver.o().intValue();
            this.g = maneuver.f().intValue();
            this.n = ee.e(maneuver.g());
            this.D = this.n.getTime();
            for (Note note : maneuver.m()) {
                Note note2 = new Note();
                note2.a(note.a());
                note2.c(note.c());
                note2.b(note.b());
                this.o.add(note2);
            }
        }
    }

    private void a(List<Link> list) {
        if (list.size() > 0) {
            long longValue = ((Link) list.get(0)).g().longValue();
            for (Link eaVar : list) {
                this.M.add(RouteElementImpl.a(new eb(RoadElementImpl.a(new ea(eaVar, this.D, longValue)))));
            }
        }
    }

    private void a(PublicTransportLine publicTransportLine, List<Link> list) {
        if (publicTransportLine != null) {
            this.r = publicTransportLine.e();
            this.s = publicTransportLine.a();
            this.t = ee.i(publicTransportLine.f());
            this.u = publicTransportLine.g();
            this.J = true;
            try {
                this.A = Color.parseColor(publicTransportLine.b());
            } catch (Exception e) {
                this.J = false;
            }
            this.K = true;
            try {
                this.B = Color.parseColor(publicTransportLine.c());
            } catch (Exception e2) {
                this.K = false;
            }
            this.v = a(publicTransportLine.d());
            a(publicTransportLine.h(), (List) list);
        }
    }

    private void a(List<Stop> list, List<Link> list2) {
        if (list2.size() > 0 && list.size() > 0) {
            long longValue = ((Link) list2.get(0)).g().longValue();
            int size = list.size();
            Link link = null;
            this.E = 0;
            while (this.E < size - 1) {
                for (Link link2 : list2) {
                    if (link2.l().equals(((Stop) list.get(this.E + 1)).b())) {
                        this.q = link2.l();
                        link = link2;
                        break;
                    }
                }
                if (link != null) {
                    TransitRouteElement a = TransitRouteElementImpl.a(new er(this, (Stop) list.get(this.E), (Stop) list.get(this.E + 1), link, this.D, longValue));
                    this.L.add(a);
                    this.M.add(RouteElementImpl.a(new eb(a)));
                }
                this.E++;
            }
        }
    }

    private TransitLineStyle a(String str) {
        TransitLineStyle transitLineStyle = TransitLineStyle.UNDEFINED;
        if (str.equals(a.SOLID.a())) {
            return TransitLineStyle.SOLID;
        }
        if (str.equals(a.DOTTED.a())) {
            return TransitLineStyle.DOTTED;
        }
        if (str.equals(a.DASHED.a())) {
            return TransitLineStyle.DASHED;
        }
        return transitLineStyle;
    }

    int w() {
        return this.E;
    }

    int x() {
        return this.F;
    }

    int y() {
        return this.C;
    }

    int z() {
        return this.x;
    }

    List<Note> A() {
        return this.o;
    }

    void B() {
        this.k = Action.HEAD_TO;
        this.m = Icon.HEAD_TO;
        this.l = Turn.UNDEFINED;
    }
}
