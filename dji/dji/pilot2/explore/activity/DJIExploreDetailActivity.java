package dji.pilot2.explore.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.google.android.gms.maps.GoogleMap;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map.Animation;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import dji.gs.c.b;
import dji.gs.c.e;
import dji.gs.map.control.AmapControll;
import dji.gs.map.control.GmapControll;
import dji.gs.map.views.AmapView;
import dji.gs.map.views.GmapView;
import dji.gs.map.views.HmapView;
import dji.gs.views.EventView;
import dji.gs.views.PaintView;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.explore.model.DJIDetailBean;
import dji.pilot2.explore.model.DJIPersonalInfo;
import dji.pilot2.nativeexplore.activity.ExploreLikesActivity;
import dji.pilot2.utils.c;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class DJIExploreDetailActivity extends Activity implements OnClickListener {
    private final String A = "DJIExploreDetailActivity";
    private String B = "north-maroubra-cliffs-sydney-australia";
    private Handler C = new Handler(this) {
        final /* synthetic */ DJIExploreDetailActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 101) {
                this.a.s.displayImage(this.a.u.headImgLink, this.a.k, new ImageLoadingListener(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onLoadingStarted(String str, View view) {
                    }

                    public void onLoadingFailed(String str, View view, FailReason failReason) {
                    }

                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        this.a.a.k.setImageBitmap(this.a.a.a(bitmap));
                    }

                    public void onLoadingCancelled(String str, View view) {
                    }
                });
                this.a.n.setText(this.a.u.name);
                this.a.o.setText(c.a(this.a.z, this.a.a));
                int a = c.a(this.a.a, this.a.z);
                if (a == 0) {
                    this.a.m.go();
                } else {
                    this.a.m.setVisibility(0);
                    this.a.m.setImageDrawable(this.a.getResources().getDrawable(a));
                }
                this.a.p.setText(this.a.t.title);
                this.a.q.setText(this.a.t.desc);
                this.a.r.setText(this.a.t.equipment);
                if (this.a.y || this.a.t.latitude == 0.0d || this.a.t.longitude == 0.0d) {
                    this.a.f.setVisibility(8);
                    return;
                }
                this.a.f.setVisibility(0);
                this.a.a(this.a.t.latitude, this.a.t.longitude);
            }
        }
    };
    private Context a;
    private boolean b;
    private boolean c;
    private e d;
    private b e;
    private EventView f;
    private PaintView g;
    private final int h = 101;
    private dji.gs.e.b i = new dji.gs.e.b(-33.8548157d, 151.2164539d);
    private LatLng j = new LatLng(-33.8548157d, 151.2164539d);
    private ImageView k;
    private DJIImageView l;
    private DJIImageView m;
    private DJITextView n;
    private DJITextView o;
    private DJITextView p;
    private DJITextView q;
    private DJITextView r;
    private ImageLoader s;
    private DJIDetailBean t = new DJIDetailBean();
    private DJIPersonalInfo u = new DJIPersonalInfo();
    private AMap v;
    private Map w;
    private String x;
    private boolean y;
    private String z;

    class a extends Thread {
        final /* synthetic */ DJIExploreDetailActivity a;
        private boolean b;

        public a(DJIExploreDetailActivity dJIExploreDetailActivity, boolean z) {
            this.a = dJIExploreDetailActivity;
            this.b = z;
        }

        public void run() {
            String f;
            if (this.b) {
                f = k.f(this.a.x);
            } else {
                f = k.e(this.a.x);
            }
            com.dji.frame.c.c.b(this.a.a).a(f, new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null) {
                            this.a.a.t.id = jSONObject.getString("id");
                            this.a.a.t.title = jSONObject.getString("title");
                            this.a.a.t.type = jSONObject.getString("type");
                            this.a.a.t.updateTime = jSONObject.getString(n.M);
                            this.a.a.t.middleImgLink = jSONObject.getString("image_url");
                            this.a.a.t.shareUrl = jSONObject.getString("share_url");
                            this.a.a.t.desc = jSONObject.getString("description");
                            this.a.a.t.equipment = jSONObject.getString("equipment");
                            JSONObject jSONObject2 = jSONObject.getJSONObject(n.C);
                            if (jSONObject2 != null) {
                                this.a.a.t.country = jSONObject2.getString("country");
                            }
                            Log.i("device", " detailBean.equipment:" + this.a.a.t.equipment);
                            if (this.a.a.t.equipment == null) {
                                this.a.a.t.equipment = "";
                            } else if (this.a.a.t.equipment.indexOf(34) == -1 || this.a.a.t.equipment.lastIndexOf(34) == -1) {
                                this.a.a.t.equipment = "";
                            } else {
                                this.a.a.t.equipment = this.a.a.t.equipment.substring(this.a.a.t.equipment.indexOf(34) + 1, this.a.a.t.equipment.lastIndexOf(34));
                            }
                            jSONObject2 = jSONObject.getJSONObject("account");
                            this.a.a.u.id = jSONObject2.getString("id");
                            this.a.a.u.name = jSONObject2.getString("name");
                            this.a.a.u.headImgLink = jSONObject2.getString("avatar") + "@!128x128";
                            Log.i("detail", "person :" + this.a.a.u.headImgLink);
                            this.a.a.t.latitude = jSONObject.getDouble("latitude");
                            this.a.a.t.longitude = jSONObject.getDouble("longitude");
                            this.a.a.C.sendEmptyMessage(101);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        this.a.a.C.sendEmptyMessage(101);
                    }
                }

                public void a(Throwable th, int i, String str) {
                }
            });
        }
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = this;
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.v2_explore_mediadetal_layout, null);
        setContentView(inflate);
        a(bundle, inflate);
        a();
        new a(this, this.y).start();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.x = intent.getStringExtra(ExploreLikesActivity.a);
            this.y = intent.getBooleanExtra("detail_video", false);
            this.z = intent.getStringExtra("country");
        }
    }

    private void a(Bundle bundle, View view) {
        this.f = (EventView) findViewById(R.id.cm3);
        b(bundle, view);
        this.s = ImageLoader.getInstance();
        this.k = (ImageView) findViewById(R.id.cls);
        this.n = (DJITextView) findViewById(R.id.clu);
        this.o = (DJITextView) findViewById(R.id.clv);
        this.m = (DJIImageView) findViewById(R.id.clt);
        this.p = (DJITextView) findViewById(R.id.clw);
        this.q = (DJITextView) findViewById(R.id.cly);
        this.l = (DJIImageView) findViewById(R.id.clr);
        this.r = (DJITextView) findViewById(R.id.cm0);
        this.l.setOnClickListener(this);
    }

    private Bitmap a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int i;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            i = width;
        } else {
            i = height;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, (float) i, (float) i);
        canvas.drawRoundRect(rectF, (float) (i / 2), (float) (i / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);
        return createBitmap;
    }

    protected void onResume() {
        this.e.onResume();
        if (this.d != null) {
            this.d.h(dji.gs.e.a.b);
        }
        super.onResume();
    }

    protected void onPause() {
        this.e.onPause();
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        this.e.onPause();
        super.onStop();
    }

    protected void onDestroy() {
        this.e.onDestroy();
        super.onDestroy();
    }

    private void a(double d, double d2) {
        dji.gs.e.b bVar = new dji.gs.e.b(d, d2);
        if (this.v != null) {
            LatLng latLng = new LatLng(d, d2);
            this.v.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
            this.v.moveCamera(CameraUpdateFactory.zoomTo(7.0f));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.v2_point_map));
            markerOptions.visible(true);
            markerOptions.draggable(false);
            this.v.addMarker(markerOptions);
        }
        if (this.w != null) {
            this.w.setCenter(new GeoCoordinate(d, d2), Animation.BOW, 7.0d, -1.0f, -1.0f);
            MapObject mapMarker = new MapMarker();
            mapMarker.setCoordinate(new GeoCoordinate(d, d2));
            Image image = new Image();
            try {
                image.setImageResource(R.drawable.v2_point_map);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mapMarker.setIcon(image);
            mapMarker.setVisible(true);
            mapMarker.setDraggable(false);
            this.w.addMapObject(mapMarker);
        }
    }

    private void b(Bundle bundle, View view) {
        if (DJIGenSettingDataManager.getInstance().r()) {
            this.c = false;
            Object amapView = new AmapView(this.a);
            amapView.setLayoutParams(new LayoutParams(-1, -1));
            amapView.setClickable(true);
            amapView.setVisibility(0);
            this.v = amapView.getMap();
            amapView.onCreate(bundle);
            this.f.addView(amapView);
            try {
                MapsInitializer.initialize(this.a);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.osd_attitude_gimbal));
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
            myLocationStyle.strokeWidth(0.0f);
            this.e = amapView;
            if (this.v != null) {
                UiSettings uiSettings = this.v.getUiSettings();
                uiSettings.setZoomControlsEnabled(false);
                uiSettings.setZoomGesturesEnabled(true);
                uiSettings.setCompassEnabled(false);
                uiSettings.setScaleControlsEnabled(true);
                this.d = new AmapControll(this.a, this.v, view);
                this.d.a(this.a);
                return;
            }
            return;
        }
        this.c = true;
        if (dji.a.a.getInstance().a()) {
            Object hmapView = new HmapView(this.a);
            hmapView.setLayoutParams(new LayoutParams(-1, -1));
            hmapView.setClickable(true);
            hmapView.setVisibility(0);
            this.f.addView(hmapView);
            hmapView.onCreate(bundle);
            this.d = new dji.gs.map.control.a(this.a, hmapView, view);
            this.e = hmapView;
            this.d.a(this.a);
            return;
        }
        amapView = new GmapView(this.a);
        amapView.setLayoutParams(new LayoutParams(-1, -1));
        amapView.setClickable(true);
        amapView.setVisibility(0);
        this.f.addView(amapView);
        try {
            com.google.android.gms.maps.MapsInitializer.initialize(this.a);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        amapView.onCreate(bundle);
        GoogleMap map = amapView.getMap();
        this.e = amapView;
        if (map != null) {
            com.google.android.gms.maps.UiSettings uiSettings2 = map.getUiSettings();
            uiSettings2.setZoomControlsEnabled(false);
            uiSettings2.setCompassEnabled(false);
            this.d = new GmapControll(this.a, map, view);
            this.d.a(this.a);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clr:
                finish();
                return;
            default:
                return;
        }
    }
}
