package dji.pilot2.nativeexplore.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map.Animation;
import com.here.android.mpa.mapping.Map.InfoBubbleAdapter;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.gs.c.e;
import dji.gs.map.control.AmapControll;
import dji.gs.map.control.GmapControll;
import dji.gs.map.views.AmapView;
import dji.gs.map.views.GmapView;
import dji.gs.map.views.HmapView;
import dji.gs.views.EventView;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GLMapActivity extends DJIFragmentActivityNoFullScreen {
    private static List<a> X = new ArrayList();
    private static a Y = null;
    private static boolean Z = false;
    public static final String a = "latitude";
    public static final String r = "longtitude";
    public static final String s = "latitude_fly";
    public static final String t = "longtitude_fly";
    public static final String u = "location_img";
    public static final String v = "location_content";
    public static final String w = "location_title";
    private dji.gs.c.b A;
    private EventView B;
    private AMap C;
    private Context D;
    private ImageView E;
    private double F;
    private double G;
    private double H;
    private double I;
    private ImageLoader J;
    private String K;
    private String L;
    private String M;
    private TextView N;
    private TextView O;
    private PackageManager P = null;
    private Button Q;
    private Button R;
    private DJIRelativeLayout S;
    private TextView T;
    private ListView U;
    private Map V;
    private HmapView W;
    private boolean x;
    private boolean y;
    private e z;

    private enum a {
        NOMap,
        GDMap,
        BDMap,
        GGMap
    }

    class b extends BaseAdapter {
        final /* synthetic */ GLMapActivity a;

        b(GLMapActivity gLMapActivity) {
            this.a = gLMapActivity;
        }

        public int getCount() {
            if (GLMapActivity.X == null || GLMapActivity.X.size() <= 0) {
                return 0;
            }
            return GLMapActivity.X.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View textView;
            if (view == null) {
                textView = new TextView(this.a.D);
            } else {
                textView = view;
            }
            textView.setLayoutParams(new LayoutParams(-1, 100));
            ((TextView) textView).setGravity(17);
            if (GLMapActivity.X.get(i) == a.GDMap) {
                ((TextView) textView).setText(this.a.getString(R.string.v2_map_gd));
                textView.setTag(a.GDMap);
            } else if (GLMapActivity.X.get(i) == a.GGMap) {
                ((TextView) textView).setText(this.a.getString(R.string.v2_map_gg));
                textView.setTag(a.GGMap);
            } else if (GLMapActivity.X.get(i) == a.BDMap) {
                ((TextView) textView).setText(this.a.getString(R.string.v2_map_bd));
                textView.setTag(a.BDMap);
            }
            return textView;
        }
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        int i = 0;
        super.onCreate(bundle);
        this.D = this;
        View inflate = LayoutInflater.from(this.D).inflate(R.layout.gl_map_layout, null);
        setContentView(inflate);
        this.E = (ImageView) inflate.findViewById(R.id.gr);
        this.N = (TextView) inflate.findViewById(R.id.ai_);
        this.O = (TextView) inflate.findViewById(R.id.aia);
        this.Q = (Button) inflate.findViewById(R.id.aid);
        this.R = (Button) inflate.findViewById(R.id.aic);
        this.S = (DJIRelativeLayout) inflate.findViewById(R.id.cq8);
        this.T = (TextView) inflate.findViewById(R.id.cq9);
        this.U = (ListView) inflate.findViewById(R.id.cq_);
        this.T.setText(getString(R.string.v2_map_title));
        this.S.setVisibility(8);
        this.S.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GLMapActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.S.go();
            }
        });
        this.U.setAdapter(new b(this));
        this.U.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ GLMapActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                a aVar = (a) view.getTag();
                if (aVar != null) {
                    GLMapActivity.Y = aVar;
                    String string = aVar == a.GDMap ? this.a.getString(R.string.v2_type_gd) : aVar == a.GGMap ? this.a.getString(R.string.v2_type_gg) : aVar == a.BDMap ? this.a.getString(R.string.v2_type_bd) : null;
                    Toast.makeText(this.a.D, this.a.getString(R.string.v2_tips_map_select) + string, 0).show();
                    this.a.S.go();
                }
            }
        });
        this.Q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GLMapActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.z.h(dji.gs.e.a.b);
                this.a.Q.setTextColor(this.a.getResources().getColor(R.color.a_));
                this.a.R.setTextColor(this.a.getResources().getColor(R.color.e7));
            }
        });
        this.R.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GLMapActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.z.h(dji.gs.e.a.c);
                this.a.Q.setTextColor(this.a.getResources().getColor(R.color.e7));
                this.a.R.setTextColor(this.a.getResources().getColor(R.color.a_));
            }
        });
        this.O.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GLMapActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (GLMapActivity.X == null || GLMapActivity.X.size() == 0) {
                    Toast.makeText(this.a.D, this.a.getString(R.string.v2_tips_map), 0).show();
                } else {
                    this.a.S.show();
                }
            }
        });
        this.E.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GLMapActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.Q.setTextColor(getResources().getColor(R.color.a_));
        this.R.setTextColor(getResources().getColor(R.color.e7));
        this.B = (EventView) findViewById(R.id.aib);
        createMapView(bundle, inflate);
        this.J = ImageLoader.getInstance();
        g();
        if (!Z) {
            this.P = getPackageManager();
            ArrayList arrayList = new ArrayList();
            List installedPackages = this.P.getInstalledPackages(0);
            while (i < installedPackages.size()) {
                PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                if (packageInfo.applicationInfo.packageName.equals("com.baidu.BaiduMap")) {
                    X.add(a.BDMap);
                } else if (packageInfo.applicationInfo.packageName.equals("com.autonavi.minimap")) {
                    X.add(a.GDMap);
                } else if (packageInfo.applicationInfo.packageName.equals("com.google.android.apps.maps")) {
                    X.add(a.GGMap);
                }
                i++;
            }
            Z = true;
        }
        Log.i("maps", "mHasMapList:" + X.size());
        Y = a.GDMap;
    }

    protected void onResume() {
        this.A.onResume();
        this.z.h(dji.gs.e.a.b);
        a(this.F, this.G, this.H, this.I);
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
        this.A.onPause();
    }

    private void g() {
        Intent intent = getIntent();
        if (intent != null) {
            this.F = intent.getDoubleExtra("latitude", 0.0d);
            this.G = intent.getDoubleExtra(r, 0.0d);
            this.H = intent.getDoubleExtra(s, 0.0d);
            this.I = intent.getDoubleExtra(t, 0.0d);
            this.L = intent.getStringExtra(v);
            this.K = intent.getStringExtra(u);
            this.M = intent.getStringExtra(w);
            this.N.setText(this.M);
        }
    }

    private void createMapView(Bundle bundle, View view) {
        if (DJIGenSettingDataManager.getInstance().r()) {
            this.y = true;
            if (dji.a.a.getInstance().a()) {
                this.W = new HmapView(this.D);
                this.W.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                this.W.setClickable(true);
                this.W.setVisibility(0);
                this.B.addView(this.W);
                this.W.onCreate(bundle);
                this.z = new dji.gs.map.control.a(this.D, this.W, view);
                this.V = this.W.getMap();
                this.A = this.W;
                this.z.a(this.D);
                return;
            }
            Object gmapView = new GmapView(this.D);
            gmapView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            gmapView.setClickable(true);
            gmapView.setVisibility(0);
            this.B.addView(gmapView);
            try {
                MapsInitializer.initialize(this.D);
            } catch (Exception e) {
                e.printStackTrace();
            }
            gmapView.onCreate(bundle);
            GoogleMap map = gmapView.getMap();
            UiSettings uiSettings = map.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setCompassEnabled(false);
            this.z = new GmapControll(this.D, map, view);
            this.A = gmapView;
            this.z.a(this.D);
            return;
        }
        this.y = false;
        gmapView = new AmapView(this.D);
        gmapView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        gmapView.setClickable(true);
        gmapView.setVisibility(0);
        this.C = gmapView.getMap();
        gmapView.onCreate(bundle);
        this.B.addView(gmapView);
        try {
            com.amap.api.maps.MapsInitializer.initialize(this.D);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.osd_attitude_gimbal));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.strokeWidth(0.0f);
        com.amap.api.maps.UiSettings uiSettings2 = this.C.getUiSettings();
        uiSettings2.setZoomControlsEnabled(false);
        uiSettings2.setZoomGesturesEnabled(true);
        uiSettings2.setCompassEnabled(false);
        uiSettings2.setScaleControlsEnabled(true);
        this.z = new AmapControll(this.D, this.C, view);
        this.A = gmapView;
        this.z.a(this.D);
    }

    private void a(double d, double d2, double d3, double d4) {
        if (this.C != null) {
            LatLng latLng = new LatLng(d, d2);
            LatLng latLng2 = new LatLng(d3, d4);
            this.C.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
            this.C.moveCamera(CameraUpdateFactory.zoomTo(7.0f));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.v2_gl_camera));
            markerOptions.visible(true);
            markerOptions.draggable(false);
            markerOptions.title(dji.publics.b.a.b.v);
            this.C.addMarker(markerOptions);
            MarkerOptions markerOptions2 = new MarkerOptions();
            markerOptions2.position(latLng2);
            markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.v2_gl_fly));
            markerOptions2.title("fly");
            markerOptions2.visible(true);
            markerOptions2.draggable(false);
            this.C.setInfoWindowAdapter(new InfoWindowAdapter(this) {
                final /* synthetic */ GLMapActivity a;

                {
                    this.a = r1;
                }

                public View getInfoWindow(Marker marker) {
                    View inflate = LayoutInflater.from(this.a.D).inflate(R.layout.gl_layout_map_adapter, null);
                    this.a.J.displayImage(this.a.K, (ImageView) inflate.findViewById(R.id.ahx));
                    ((TextView) inflate.findViewById(R.id.ahz)).setText(this.a.L);
                    ((TextView) inflate.findViewById(R.id.ahy)).setText(this.a.M);
                    return inflate;
                }

                public View getInfoContents(Marker marker) {
                    return null;
                }
            });
            this.C.addMarker(markerOptions2);
            this.C.setOnInfoWindowClickListener(new OnInfoWindowClickListener(this) {
                final /* synthetic */ GLMapActivity a;

                {
                    this.a = r1;
                }

                public void onInfoWindowClick(Marker marker) {
                    try {
                        if (GLMapActivity.X == null || GLMapActivity.X.size() == 0) {
                            Toast.makeText(this.a.D, this.a.getString(R.string.v2_tips_map), 0).show();
                        } else if (GLMapActivity.Y == a.GDMap) {
                            r0 = new Intent("android.intent.action.VIEW", Uri.parse("androidamap://navi?sourceApplication=test&lat=" + marker.getPosition().latitude + "&lon=" + marker.getPosition().longitude + "&dev=0"));
                            r0.setPackage("com.autonavi.minimap");
                            this.a.startActivity(r0);
                        } else if (GLMapActivity.Y == a.BDMap) {
                            this.a.startActivity(Intent.getIntent("intent://map/marker?location=" + marker.getPosition().latitude + "," + marker.getPosition().longitude + "&title=我的位置&content=百度奎科大厦&src=test|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end"));
                        } else if (GLMapActivity.Y == a.GGMap) {
                            r0 = new Intent("android.intent.action.VIEW", Uri.parse("http://ditu.google.cn/maps?hl=zh&mrt=loc&q=" + marker.getPosition().latitude + "," + marker.getPosition().longitude));
                            r0.addFlags(0);
                            r0.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                            this.a.startActivity(r0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this.a.D, this.a.getString(R.string.v2_tips_map), 0).show();
                    }
                }
            });
            this.C.setOnMarkerClickListener(new OnMarkerClickListener(this) {
                final /* synthetic */ GLMapActivity a;

                {
                    this.a = r1;
                }

                public boolean onMarkerClick(Marker marker) {
                    marker.showInfoWindow();
                    return true;
                }
            });
        }
        if (this.V != null) {
            GeoCoordinate geoCoordinate = new GeoCoordinate(d, d2);
            GeoCoordinate geoCoordinate2 = new GeoCoordinate(d3, d4);
            this.V.setCenter(geoCoordinate, Animation.BOW, 7.0d, -1.0f, -1.0f);
            MapObject mapMarker = new MapMarker();
            mapMarker.setCoordinate(geoCoordinate);
            Image image = new Image();
            try {
                image.setImageResource(R.drawable.v2_gl_camera);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mapMarker.setIcon(image);
            mapMarker.setVisible(true);
            mapMarker.setDraggable(false);
            mapMarker.setTitle(dji.publics.b.a.b.v);
            this.V.addMapObject(mapMarker);
            mapMarker = new MapMarker();
            mapMarker.setCoordinate(geoCoordinate2);
            Image image2 = new Image();
            try {
                image2.setImageResource(R.drawable.v2_gl_camera);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            mapMarker.setIcon(image2);
            mapMarker.setTitle("fly");
            mapMarker.setVisible(true);
            mapMarker.setDraggable(false);
            this.V.setInfoBubbleAdapter(new InfoBubbleAdapter(this) {
                final /* synthetic */ GLMapActivity a;

                {
                    this.a = r1;
                }

                public View getInfoBubbleContents(MapMarker mapMarker) {
                    return null;
                }

                public View getInfoBubble(MapMarker mapMarker) {
                    View inflate = LayoutInflater.from(this.a.D).inflate(R.layout.gl_layout_map_adapter, null);
                    this.a.J.displayImage(this.a.K, (ImageView) inflate.findViewById(R.id.ahx));
                    ((TextView) inflate.findViewById(R.id.ahz)).setText(this.a.L);
                    ((TextView) inflate.findViewById(R.id.ahy)).setText(this.a.M);
                    return inflate;
                }
            });
            this.V.addMapObject(mapMarker);
        }
    }
}
