package dji.pilot2.usercenter.activity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.dji.frame.c.d;
import com.dji.frame.c.l;
import com.google.android.gms.maps.GoogleMap;
import dji.gs.c.e;
import dji.gs.d.c;
import dji.gs.e.b;
import dji.gs.map.control.AmapControll;
import dji.gs.map.control.GmapControll;
import dji.gs.map.views.AmapView;
import dji.gs.map.views.GmapView;
import dji.gs.map.views.HmapView;
import dji.gs.views.EventView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.model.f;
import dji.pilot.fpv.model.h;
import dji.pilot.fpv.model.i;
import dji.pilot.publics.widget.DJISwitch;
import dji.pilot2.DJIActivityFullScreen;
import dji.pilot2.main.a.a;
import dji.pilot2.usercenter.widget.DJIProfileRoundImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DJIFlightRecordShareActivity extends DJIActivityFullScreen implements OnClickListener {
    public static b a = null;
    private static final String c = "yyyy-MM-dd-HH:mm:ss";
    private static final String d = "FlightRecordThumb/";
    private Thread A;
    private DJITextView B = null;
    private DJITextView C = null;
    private DJITextView D = null;
    private DJITextView E = null;
    private DJILinearLayout F = null;
    private DJITextView G = null;
    private View H = null;
    private View I = null;
    private DJIProfileRoundImageView J = null;
    private DJITextView K = null;
    private DJITextView L = null;
    private DJITextView M = null;
    private DJITextView N = null;
    private DJITextView O = null;
    private DJITextView P = null;
    private DJITextView Q = null;
    private DJITextView R = null;
    private DJITextView S = null;
    private DJISwitch T = null;
    private DecimalFormat U = new DecimalFormat("###,###,###,###");
    private c V = new c(this) {
        final /* synthetic */ DJIFlightRecordShareActivity a;

        {
            this.a = r1;
        }

        public void a(Bitmap bitmap) {
            this.a.a(true);
            String a = d.a(this.a.getBaseContext(), DJIFlightRecordShareActivity.d);
            File file = new File(a);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (this.a.a(bitmap, new File(a + "fr_" + l.a(new Date(), DJIFlightRecordShareActivity.c) + a.n), false)) {
                Toast.makeText(this.a, this.a.getString(R.string.flight_record_save_shot, new Object[]{a}), 0).show();
            }
        }
    };
    private dji.pilot2.share.e.a.b W = dji.pilot2.share.e.a.b.PLATFORM_TYPE_FACKBOOK;
    private c X = new c(this) {
        final /* synthetic */ DJIFlightRecordShareActivity a;

        {
            this.a = r1;
        }

        public void a(Bitmap bitmap) {
            this.a.a(true);
            File file = new File(d.a(this.a.getBaseContext(), "Tmp/"));
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                File createTempFile = File.createTempFile("fs_" + System.currentTimeMillis(), a.n, file);
                createTempFile.deleteOnExit();
                this.a.a(bitmap, createTempFile, true);
            } catch (IOException e) {
            }
        }
    };
    private volatile boolean Y = false;
    ArrayList<b> b = new ArrayList();
    private dji.gs.c.b t;
    private EventView u;
    private e v;
    private RelativeLayout w;
    private int x;
    private List<h> y;
    private f z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.x = getIntent().getIntExtra("POSITION", 0);
        setContentView(R.layout.v2_usercenter_flightrecord_share);
        this.B = (DJITextView) findViewById(R.id.d4e);
        this.C = (DJITextView) findViewById(R.id.d4f);
        this.D = (DJITextView) findViewById(R.id.d4g);
        this.E = (DJITextView) findViewById(R.id.d4h);
        this.F = (DJILinearLayout) findViewById(R.id.d4i);
        this.G = (DJITextView) findViewById(R.id.d4j);
        this.H = findViewById(R.id.d4c);
        this.I = findViewById(R.id.d4b);
        this.J = (DJIProfileRoundImageView) findViewById(R.id.d4d);
        this.M = (DJITextView) findViewById(R.id.d4l);
        this.M.setOnClickListener(this);
        this.N = (DJITextView) findViewById(R.id.d4m);
        this.N.setOnClickListener(this);
        this.O = (DJITextView) findViewById(R.id.d4n);
        this.O.setOnClickListener(this);
        this.P = (DJITextView) findViewById(R.id.d4o);
        this.P.setOnClickListener(this);
        this.Q = (DJITextView) findViewById(R.id.d4p);
        this.Q.setOnClickListener(this);
        this.R = (DJITextView) findViewById(R.id.d4q);
        this.R.setOnClickListener(this);
        this.S = (DJITextView) findViewById(R.id.d4r);
        this.S.setOnClickListener(this);
        this.T = (DJISwitch) findViewById(R.id.d4k);
        this.T.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DJIFlightRecordShareActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.b(z);
            }
        });
        this.K = (DJITextView) findViewById(R.id.d48);
        this.K.setOnClickListener(this);
        this.L = (DJITextView) findViewById(R.id.d49);
        this.L.setOnClickListener(this);
        this.w = (RelativeLayout) findViewById(R.id.d4_);
        this.u = (EventView) findViewById(R.id.d4a);
        a(bundle);
        a(this.x);
    }

    protected void onDestroy() {
        this.t.onDestroy();
        dji.thirdparty.a.c.a().d(this);
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        this.t.onResume();
        this.H.postInvalidate();
    }

    protected void onPause() {
        this.t.onPause();
        super.onPause();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.t.onSaveInstanceState(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.t.onLowMemory();
    }

    public Bitmap a() {
        Bitmap createBitmap = Bitmap.createBitmap(this.H.getWidth(), this.H.getHeight(), Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        this.H.buildDrawingCache();
        this.H.setDrawingCacheEnabled(true);
        canvas.drawBitmap(this.H.getDrawingCache(), 0.0f, 0.0f, null);
        this.H.destroyDrawingCache();
        return createBitmap;
    }

    private boolean a(Bitmap bitmap, File file, boolean z) {
        FileNotFoundException fileNotFoundException;
        IOException iOException;
        boolean z2 = false;
        if (bitmap == null) {
            return false;
        }
        Bitmap a = a();
        if (a == null) {
            DJILogHelper.getInstance().LOGD("", "viewShot failed", false, true);
            return false;
        }
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            new Canvas(bitmap).drawBitmap(a, 0.0f, (float) (bitmap.getHeight() - a.getHeight()), null);
        } else {
            int dimension = (int) getResources().getDimension(R.dimen.fu);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() - (dimension * 2), bitmap.getHeight(), Config.ARGB_4444);
            Canvas canvas = new Canvas(createBitmap);
            int width = bitmap.getWidth() - dimension;
            canvas.drawBitmap(bitmap, new Rect(dimension, 0, width, bitmap.getHeight()), new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight()), null);
            canvas.drawBitmap(a, new Rect(dimension, 0, width, a.getHeight()), new Rect(0, bitmap.getHeight() - a.getHeight(), createBitmap.getWidth(), bitmap.getHeight()), null);
            bitmap = createBitmap;
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (z) {
                try {
                    DJILogHelper.getInstance().LOGD("", "useShareSDK", false, true);
                    dji.pilot2.share.f.b.a(this, "", "", file.getAbsolutePath(), "", this.W, dji.pilot2.share.e.a.a.CONTENT_IMG, dji.pilot2.share.b.b.a.NONE);
                } catch (FileNotFoundException e) {
                    fileNotFoundException = e;
                    z2 = true;
                    fileNotFoundException.printStackTrace();
                    return z2;
                } catch (IOException e2) {
                    iOException = e2;
                    z2 = true;
                    iOException.printStackTrace();
                    return z2;
                }
            }
            return true;
        } catch (FileNotFoundException e3) {
            fileNotFoundException = e3;
            fileNotFoundException.printStackTrace();
            return z2;
        } catch (IOException e4) {
            iOException = e4;
            iOException.printStackTrace();
            return z2;
        }
    }

    private void a(boolean z) {
        if (z) {
            c(true);
            this.F.go();
            return;
        }
        this.G.setText(R.string.flight_record_capturing);
        c(false);
        this.F.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d48:
                finish();
                return;
            case R.id.d49:
                a(false);
                this.v.a(this.V);
                this.v.A();
                return;
            case R.id.d4l:
                a(false);
                this.v.a(this.X);
                this.W = dji.pilot2.share.e.a.b.PLATFORM_TYPE_FACKBOOK;
                this.v.A();
                return;
            case R.id.d4m:
                a(false);
                this.v.a(this.X);
                this.W = dji.pilot2.share.e.a.b.PLATFORM_TYPE_WECHAT;
                this.v.A();
                return;
            case R.id.d4n:
                a(false);
                this.v.a(this.X);
                this.W = dji.pilot2.share.e.a.b.PLATFORM_TYPE_WECHAT_MOMENTS;
                this.v.A();
                return;
            case R.id.d4o:
                a(false);
                this.v.a(this.X);
                this.W = dji.pilot2.share.e.a.b.PLATFORM_TYPE_TWITTER;
                this.v.A();
                return;
            case R.id.d4p:
                a(false);
                this.v.a(this.X);
                this.W = dji.pilot2.share.e.a.b.PLATFORM_TYPE_TUMBLR;
                this.v.A();
                return;
            case R.id.d4q:
                a(false);
                this.v.a(this.X);
                this.W = dji.pilot2.share.e.a.b.PLATFORM_TYPE_WEIBO;
                this.v.A();
                return;
            case R.id.d4r:
                a(false);
                this.v.a(this.X);
                this.W = dji.pilot2.share.e.a.b.PLATFORM_TYPE_QQ;
                this.v.A();
                return;
            default:
                return;
        }
    }

    private void b(boolean z) {
        if (z) {
            this.v.d(this.b);
        } else {
            this.v.z();
        }
    }

    private void c(boolean z) {
        this.M.setEnabled(z);
        this.N.setEnabled(z);
        this.O.setEnabled(z);
        this.P.setEnabled(z);
        this.Q.setEnabled(z);
        this.R.setEnabled(z);
        this.S.setEnabled(z);
        this.T.setEnabled(z);
        this.L.setEnabled(z);
    }

    private void a(b bVar) {
        if (bVar != null && bVar.a() && !this.Y) {
            try {
                this.v.h(bVar);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.Y = true;
        }
    }

    public void a(int i) {
        this.x = i;
        this.z = dji.pilot.usercenter.b.d.getInstance().b(i);
        c(false);
        if (this.z != null) {
            if (this.z.A != (byte) 2) {
                this.z.A = (byte) 2;
                dji.pilot.usercenter.b.d.getInstance().b(this.z);
            }
            this.B.setText(l.a(new Date(this.z.C), dji.pilot.usercenter.f.e.a));
            if (this.z.v.contains("Loading")) {
                this.C.setText(R.string.flight_record_maploading);
            } else {
                this.C.setText(this.z.v);
            }
            this.v.a(new b(this.z.E, this.z.D), true);
            this.J.setImageResource(dji.pilot.publics.c.d.getInstance().i(ProductType.find(this.z.Q)));
            this.A = new Thread(new Runnable(this) {
                final /* synthetic */ DJIFlightRecordShareActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.y = i.f(this.a, this.a.z);
                    this.a.F.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            float f = 0.0f;
                            if (this.a.a.y != null) {
                                float a;
                                int size = this.a.a.y.size() - 1;
                                h hVar = (h) this.a.a.y.get(0);
                                h hVar2 = (h) this.a.a.y.get(size);
                                b bVar = new b(0.0d, 0.0d);
                                b bVar2 = new b(0.0d, 0.0d);
                                if (hVar2.b != null) {
                                    b bVar3 = new b(hVar2.b.getLatitude(), hVar2.b.getLongitude());
                                    if (bVar3.a() && !bVar.a(bVar3)) {
                                        if (bVar.a()) {
                                            this.a.a.v.e(bVar3);
                                        } else {
                                            this.a.a.v.d(bVar3);
                                        }
                                    }
                                }
                                ArrayList arrayList = new ArrayList(size);
                                int i = 0;
                                while (i < size) {
                                    h hVar3 = (h) this.a.a.y.get(i);
                                    if (hVar3.a != null) {
                                        b bVar4 = new b(hVar3.a.getLatitude(), hVar3.a.getLongitude());
                                        if (bVar4.a()) {
                                            if (bVar2.a()) {
                                                a = (float) (dji.gs.utils.a.a(bVar4.b, bVar4.c, bVar2.b, bVar2.c) + ((double) f));
                                            } else {
                                                a = f;
                                            }
                                            arrayList.add(bVar4);
                                            if (hVar3.k.a() == (byte) 1) {
                                                this.a.a.b.add(bVar4);
                                            }
                                            f = a;
                                            bVar = bVar4;
                                            i++;
                                            bVar2 = bVar;
                                        }
                                    }
                                    bVar = bVar2;
                                    i++;
                                    bVar2 = bVar;
                                }
                                this.a.a.v.c(bVar2);
                                a = ((float) hVar2.a.getYaw()) * 0.1f;
                                this.a.a.v.a(a, true);
                                this.a.a.v.a(((((float) hVar2.c.getYawAngle()) * 0.1f) + a) + 180.0f, a, true);
                                this.a.a.v.b(arrayList);
                                if (DJIGenSettingDataManager.getInstance().v() == 1 || DJIGenSettingDataManager.getInstance().v() == 2) {
                                    this.a.a.D.setText(this.a.a.w.getResources().getString(R.string.flight_record_distance_m_format, new Object[]{this.a.a.U.format((double) f)}));
                                } else {
                                    this.a.a.D.setText(this.a.a.w.getResources().getString(R.string.flight_record_distance_ft_format, new Object[]{this.a.a.U.format((double) DJIGenSettingDataManager.getInstance().b(f))}));
                                }
                                int[] e = dji.pilot.fpv.d.b.e((int) ((hVar2.k.e() - hVar.k.e()) / 1000));
                                this.a.a.E.setText(this.a.a.getString(R.string.flight_record_time_format, new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])}));
                            }
                            this.a.a.F.go();
                            this.a.a.c(true);
                        }
                    });
                }
            });
            this.A.start();
            a(new b(this.z.E, this.z.D));
            return;
        }
        this.F.go();
        Toast.makeText(this, R.string.flight_record_loading_failed, 1).show();
    }

    private void a(Bundle bundle) {
        Object amapView;
        if (DJIGenSettingDataManager.getInstance().r()) {
            amapView = new AmapView(this);
            amapView.setLayoutParams(new LayoutParams(-1, -1));
            amapView.setClickable(true);
            amapView.setVisibility(0);
            this.u.addView(amapView);
            try {
                MapsInitializer.initialize(this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            amapView.onCreate(bundle);
            AMap map = amapView.getMap();
            UiSettings uiSettings = map.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setCompassEnabled(false);
            uiSettings.setScaleControlsEnabled(true);
            this.v = new AmapControll(this, map, this.w);
            this.t = amapView;
        } else if (dji.a.a.getInstance().a()) {
            amapView = new HmapView(this);
            amapView.setLayoutParams(new LayoutParams(-1, -1));
            amapView.setClickable(true);
            amapView.setVisibility(0);
            this.u.addView(amapView);
            amapView.onCreate(bundle);
            this.v = new dji.gs.map.control.a(this, amapView, this.w);
            this.t = amapView;
            this.v.a(this);
        } else {
            amapView = new GmapView(this);
            amapView.setLayoutParams(new LayoutParams(-1, -1));
            amapView.setClickable(true);
            amapView.setVisibility(0);
            this.u.addView(amapView);
            try {
                com.google.android.gms.maps.MapsInitializer.initialize(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            amapView.onCreate(bundle);
            GoogleMap map2 = amapView.getMap();
            com.google.android.gms.maps.UiSettings uiSettings2 = map2.getUiSettings();
            uiSettings2.setZoomControlsEnabled(false);
            uiSettings2.setCompassEnabled(false);
            this.v = new GmapControll(this, map2, this.w);
            this.t = amapView;
        }
    }
}
