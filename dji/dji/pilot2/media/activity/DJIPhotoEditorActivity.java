package dji.pilot2.media.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import com.dji.frame.c.l;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;
import dji.pilot.fpv.d.c.j;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.model.DJIGeocoderResult;
import dji.pilot.fpv.model.DJIGeocoderResult.FirstLevel;
import dji.pilot.fpv.model.DJIGeocoderResult.SecondLevel;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.media.view.DJIPhotoEditorCutView;
import dji.pilot2.media.view.DJIPhotoEditorSlidingBar;
import dji.pilot2.media.view.DJIPhotoEditorSlidingBar.a;
import dji.pilot2.media.view.DJIPhotoEditorTransformView;
import dji.pilot2.media.view.DJIPhotoEditorTransformView.b;
import dji.pilot2.media.view.DJIPhotoEditorTransformView.c;
import dji.pilot2.media.view.PhotoFilterScrollView;
import dji.pilot2.share.activity.DJIShareActivity;
import dji.pilot2.utils.m;
import dji.pilot2.widget.DJIWaitWidget;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import jp.co.cyberagent.android.gpuimage.d;
import jp.co.cyberagent.android.gpuimage.f;
import jp.co.cyberagent.android.gpuimage.i;
import jp.co.cyberagent.android.gpuimage.u;
import lecho.lib.hellocharts.model.h;

public class DJIPhotoEditorActivity extends DJIActivityNoFullScreen implements j {
    public static final String B = "key_filepath";
    private static final int as = 4;
    private static final float at = 0.0f;
    private static final float au = 1.0f;
    private static final float av = 1.0f;
    a C = new a(this) {
        final /* synthetic */ DJIPhotoEditorActivity a;

        {
            this.a = r1;
        }

        public void a(View view, int i, boolean z) {
            String format;
            if (i > 0) {
                format = String.format("+%d", new Object[]{Integer.valueOf(i)});
            } else if (i < 0) {
                format = String.format("-%d", new Object[]{Integer.valueOf(-i)});
            } else {
                format = "0";
            }
            switch (view.getId()) {
                case R.id.cdp:
                    this.a.S.setText(String.format(this.a.getString(R.string.v2_photo_editor_property_light), new Object[]{format}));
                    if (z) {
                        this.a.S.setVisibility(8);
                    } else {
                        this.a.S.setVisibility(0);
                    }
                    this.a.M.a(((float) i) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
                    this.a.a(false);
                    return;
                case R.id.cdq:
                    this.a.S.setText(String.format(this.a.getString(R.string.v2_photo_editor_property_color), new Object[]{format}));
                    if (z) {
                        this.a.S.setVisibility(8);
                    } else {
                        this.a.S.setVisibility(0);
                    }
                    this.a.N.a(((float) (i + 100)) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
                    this.a.a(false);
                    return;
                case R.id.cdr:
                    this.a.S.setText(String.format(this.a.getString(R.string.v2_photo_editor_property_b_and_w), new Object[]{format}));
                    if (z) {
                        this.a.S.setVisibility(8);
                    } else {
                        this.a.S.setVisibility(0);
                    }
                    if (i < 0) {
                        i = (int) (((double) i) * 0.75d);
                    }
                    this.a.O.a(((float) (i + 100)) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
                    this.a.a(false);
                    return;
                case R.id.ce0:
                    this.a.S.setText(String.format(this.a.getString(R.string.v2_photo_editor_rotation_angle), new Object[]{format}));
                    if (z) {
                        this.a.S.setVisibility(8);
                    } else {
                        this.a.S.setVisibility(0);
                    }
                    this.a.ag = (float) ((3.141592653589793d * ((double) i)) / 180.0d);
                    this.a.b(true);
                    this.a.a(false);
                    return;
                default:
                    return;
            }
        }
    };
    OnCheckedChangeListener D = new OnCheckedChangeListener(this) {
        final /* synthetic */ DJIPhotoEditorActivity a;

        {
            this.a = r1;
        }

        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.cdk:
                    this.a.f();
                    this.a.T.setVisibility(0);
                    return;
                case R.id.cdl:
                    this.a.f();
                    this.a.U.setVisibility(0);
                    this.a.L.enterCutPage(true);
                    return;
                case R.id.cdm:
                    this.a.f();
                    this.a.V.setVisibility(0);
                    return;
                case R.id.cdn:
                    this.a.f();
                    this.a.W.setVisibility(0);
                    return;
                default:
                    return;
            }
        }
    };
    OnClickListener E = new OnClickListener(this) {
        final /* synthetic */ DJIPhotoEditorActivity a;

        {
            this.a = r1;
        }

        private void a() {
            this.a.X.invalidate();
            this.a.Y.invalidate();
            this.a.Z.invalidate();
            this.a.aa.invalidate();
            this.a.ab.invalidate();
            this.a.ac.invalidate();
            this.a.L.updateWaterMarks(this.a.aw, this.a.ax, this.a.ay);
            this.a.b(false);
            this.a.a(0.0f, 0.0f);
            this.a.a(false);
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cdu:
                    if (!this.a.X.isChecked()) {
                        this.a.g();
                        this.a.X.setChecked(true);
                        this.a.L.setCutType(b.CUT_ORIGINAL);
                        a();
                        return;
                    }
                    return;
                case R.id.cdv:
                    if (!this.a.Y.isChecked()) {
                        this.a.g();
                        this.a.Y.setChecked(true);
                        this.a.L.setCutType(b.CUT_1X1);
                        a();
                        return;
                    }
                    return;
                case R.id.cdw:
                    if (!this.a.Z.isChecked()) {
                        this.a.g();
                        this.a.Z.setChecked(true);
                        this.a.L.setCutType(b.CUT_3X4);
                        a();
                        return;
                    }
                    return;
                case R.id.cdx:
                    if (!this.a.aa.isChecked()) {
                        this.a.g();
                        this.a.aa.setChecked(true);
                        this.a.L.setCutType(b.CUT_4X3);
                        a();
                        return;
                    }
                    return;
                case R.id.cdy:
                    if (!this.a.ab.isChecked()) {
                        this.a.g();
                        this.a.ab.setChecked(true);
                        this.a.L.setCutType(b.CUT_16X9);
                        a();
                        return;
                    }
                    return;
                case R.id.cdz:
                    if (!this.a.ac.isChecked()) {
                        this.a.g();
                        this.a.ac.setChecked(true);
                        this.a.L.setCutType(b.CUT_9X16);
                        a();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    c F = new c(this) {
        final /* synthetic */ DJIPhotoEditorActivity a;

        {
            this.a = r1;
        }

        public void a(float f, float f2) {
            this.a.a(f * 0.002f, (-f2) * 0.002f);
            this.a.a(false);
        }

        public void a(float f, float f2, float f3) {
            this.a.aj = this.a.aj * f;
            this.a.b(false);
            this.a.a(0.0f, 0.0f);
            this.a.P.a(this.a.ag, this.a.aj, this.a.ah, this.a.ai);
            this.a.a(false);
        }
    };
    OnClickListener G = new OnClickListener(this) {
        final /* synthetic */ DJIPhotoEditorActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ce5:
                    if (this.a.ad.getVisibility() != 0) {
                        this.a.ad.setVisibility(0);
                        this.a.ay = true;
                        if (!l.a(this.a.ao)) {
                            e.b(j.bU_);
                            break;
                        }
                        e.b(j.s);
                        this.a.L.setNoLocationInfo(this.a.getString(R.string.v2_photo_editor_no_location_info));
                        this.a.ad.setVisibility(4);
                        this.a.ay = false;
                        break;
                    }
                    this.a.ad.setVisibility(4);
                    this.a.ay = false;
                    break;
                case R.id.ce7:
                    if (this.a.ae.getVisibility() != 0) {
                        this.a.ae.setVisibility(0);
                        this.a.aw = true;
                        if (!l.a(this.a.am)) {
                            e.b(j.bV_);
                            break;
                        }
                        e.b(j.t);
                        this.a.L.setNoLocationInfo(this.a.getString(R.string.v2_photo_editor_can_not_get_user_name));
                        this.a.ae.setVisibility(4);
                        this.a.aw = false;
                        break;
                    }
                    this.a.ae.setVisibility(4);
                    this.a.aw = false;
                    break;
                case R.id.ce9:
                    if (this.a.af.getVisibility() != 0) {
                        this.a.af.setVisibility(0);
                        if (!l.a(this.a.an)) {
                            this.a.ax = true;
                            break;
                        }
                        this.a.L.setNoLocationInfo(this.a.getString(R.string.v2_photo_editor_no_date_info));
                        this.a.af.setVisibility(4);
                        this.a.ax = false;
                        break;
                    }
                    this.a.af.setVisibility(4);
                    this.a.ax = false;
                    break;
            }
            this.a.L.updateWaterMarks(this.a.aw, this.a.ax, this.a.ay);
        }
    };
    public String H = null;
    OnClickListener I = new OnClickListener(this) {
        final /* synthetic */ DJIPhotoEditorActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cdc:
                    this.a.finish();
                    return;
                case R.id.cde:
                    e.b(j.v);
                    this.a.ak.setVisibility(0);
                    DJITextView dJITextView = (DJITextView) this.a.findViewById(R.id.cde);
                    dJITextView.setClickable(false);
                    dJITextView.setTextColor(Color.rgb(100, 100, 100));
                    this.a.a();
                    this.a.h();
                    return;
                default:
                    return;
            }
        }
    };
    private GPUImageView J = null;
    private f K = null;
    private DJIPhotoEditorTransformView L = null;
    private jp.co.cyberagent.android.gpuimage.c M = null;
    private i N = null;
    private d O = null;
    private jp.co.cyberagent.android.gpuimage.j P = null;
    private u Q = null;
    private String R;
    private DJITextView S = null;
    private View T = null;
    private View U = null;
    private PhotoFilterScrollView V = null;
    private View W = null;
    private DJIPhotoEditorCutView X = null;
    private DJIPhotoEditorCutView Y = null;
    private DJIPhotoEditorCutView Z = null;
    private DJIPhotoEditorCutView aa = null;
    private DJIPhotoEditorCutView ab = null;
    private DJIPhotoEditorCutView ac = null;
    private DJIImageView ad = null;
    private DJIImageView ae = null;
    private DJIImageView af = null;
    private float ag = 0.0f;
    private float ah = 0.0f;
    private float ai = 0.0f;
    private float aj = 1.0f;
    private DJIWaitWidget ak = null;
    private int al = 0;
    private String am = "";
    private String an = "";
    private String ao = "";
    private Bitmap ap = null;
    private ExifInterface aq = null;
    private DJIRelativeLayout ar = null;
    private boolean aw = false;
    private boolean ax = false;
    private boolean ay = false;
    private boolean az = false;

    public static void a(Context context, String str, int i) {
        if (str != null && str.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("key_filepath", str);
            com.dji.frame.c.b.a(context, DJIPhotoEditorActivity.class, bundle, i);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.R = intent.getStringExtra("key_filepath");
            if (this.R == null || !new File(this.R).exists()) {
                finish();
                return;
            }
            setContentView(R.layout.v2_activity_photo_editor);
            DJIOriLayout.setOrientationByDevice(this);
            WindowManager windowManager = getWindowManager();
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            this.al = point.x;
            findViewById(R.id.cdc).setOnClickListener(this.I);
            findViewById(R.id.cde).setOnClickListener(this.I);
            this.ar = (DJIRelativeLayout) findViewById(R.id.cdf);
            this.L = (DJIPhotoEditorTransformView) findViewById(R.id.cdh);
            this.L.setOnTransformListener(this.F);
            this.L.setVisibility(4);
            this.J = (GPUImageView) findViewById(R.id.cdg);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.ar.setLayoutParams(new LayoutParams(this.al, (this.al * 3) / 4));
            } else {
                int dimension = (int) (((float) (point.y - findViewById(R.id.cdc).getLayoutParams().height)) - getResources().getDimension(R.dimen.g2));
                int i = (dimension * 4) / 3;
                ViewGroup.LayoutParams layoutParams = new LayoutParams(i, dimension);
                layoutParams.setMargins((this.al - i) / 2, 0, (this.al - i) / 2, 0);
                this.ar.setLayoutParams(layoutParams);
            }
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ DJIPhotoEditorActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b();
                    if (this.a.ap == null) {
                        this.a.finish();
                        return;
                    }
                    try {
                        this.a.aq = new ExifInterface(this.a.R);
                        Bitmap a = dji.pilot2.utils.a.a(this.a.ap, this.a.aq);
                        if (this.a.ap != a) {
                            this.a.ap.recycle();
                            this.a.ap = a;
                        }
                        this.a.an = this.a.aq.getAttribute("DateTime");
                        if (this.a.an == null) {
                            this.a.an = dji.pilot2.media.b.a(this.a.R);
                            if (this.a.an == null) {
                                this.a.an = this.a.a(this.a.R);
                            }
                        }
                        if (this.a.an == null) {
                            this.a.an = "";
                        } else {
                            int indexOf = this.a.an.indexOf(32);
                            if (indexOf > 0) {
                                this.a.an = this.a.an.substring(0, indexOf);
                            }
                            this.a.an = this.a.an.replace(':', '-');
                        }
                        float[] fArr = new float[2];
                        this.a.aq.getLatLong(fArr);
                        double d = (double) fArr[0];
                        double d2 = (double) fArr[1];
                        if (!(d == 0.0d || d2 == 0.0d)) {
                            DJIGeocoderResult.get(this.a.getApplicationContext(), d, d2, new com.dji.frame.b.c(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void a(Object obj) {
                                    DJIGeocoderResult dJIGeocoderResult = (DJIGeocoderResult) obj;
                                    if (dJIGeocoderResult != null && dJIGeocoderResult.status != null && dJIGeocoderResult.status.equals("OK")) {
                                        FirstLevel streetAdress = DJIGeocoderResult.getStreetAdress(dJIGeocoderResult);
                                        if (streetAdress != null) {
                                            Iterator it = streetAdress.address_components.iterator();
                                            String str = null;
                                            String str2 = null;
                                            String str3 = null;
                                            String str4 = null;
                                            while (it.hasNext()) {
                                                String str5;
                                                SecondLevel secondLevel = (SecondLevel) it.next();
                                                if (secondLevel.types.contains("administrative_area_level_1")) {
                                                    str5 = str;
                                                    str = str2;
                                                    str2 = str3;
                                                    str3 = str4;
                                                } else if (secondLevel.types.contains(dji.pilot.usercenter.protocol.c.W) || secondLevel.types.contains("administrative_area_level_2")) {
                                                    str3 = str4;
                                                    r9 = str2;
                                                    str2 = secondLevel.long_name;
                                                    str5 = str;
                                                    str = r9;
                                                } else if (secondLevel.types.contains("route")) {
                                                    str2 = str3;
                                                    str3 = str4;
                                                    r9 = str;
                                                    str = secondLevel.long_name;
                                                    str5 = r9;
                                                } else if (secondLevel.types.contains("sublocality")) {
                                                    str5 = str;
                                                    str = str2;
                                                    str2 = str3;
                                                    str3 = str4;
                                                } else if (secondLevel.types.contains("country")) {
                                                    str5 = secondLevel.long_name;
                                                    str = str2;
                                                    str2 = str3;
                                                    str3 = str4;
                                                } else if (secondLevel.types.contains("sublocality_level_1")) {
                                                    r9 = str;
                                                    str = str2;
                                                    str2 = str3;
                                                    str3 = secondLevel.long_name;
                                                    str5 = r9;
                                                } else {
                                                    str5 = str;
                                                    str = str2;
                                                    str2 = str3;
                                                    str3 = str4;
                                                }
                                                str4 = str3;
                                                str3 = str2;
                                                str2 = str;
                                                str = str5;
                                            }
                                            if (Locale.getDefault().getLanguage().contains("zh")) {
                                                if (!l.a(str3)) {
                                                    if (!l.a(str4)) {
                                                        str3 = str3 + str4;
                                                    }
                                                    if (!l.a(str2)) {
                                                        str3 = str3 + str2;
                                                    }
                                                    this.a.a.ao = str3;
                                                } else if (!l.a(str)) {
                                                    this.a.a.ao = str;
                                                }
                                            } else if (!l.a(str3)) {
                                                if (l.a(str2)) {
                                                    str2 = null;
                                                }
                                                if (!(l.a(str4) || str2 == null)) {
                                                    str2 = str2 + ", " + str4;
                                                }
                                                this.a.a.ao = str2 + ", " + str3;
                                            } else if (!l.a(str)) {
                                                this.a.a.ao = str;
                                            }
                                            this.a.a.L.setWaterMarks(this.a.a.am, this.a.a.an, this.a.a.ao);
                                        }
                                    }
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.a.am = dji.pilot.usercenter.b.f.getInstance().m();
                    this.a.M = new jp.co.cyberagent.android.gpuimage.c(0.0f);
                    this.a.N = new i(1.0f);
                    this.a.O = new d(1.0f);
                    this.a.P = new jp.co.cyberagent.android.gpuimage.j();
                    this.a.L.setPhotoSize((float) this.a.ap.getWidth(), (float) this.a.ap.getHeight());
                    this.a.T = this.a.findViewById(R.id.cdo);
                    this.a.U = this.a.findViewById(R.id.cds);
                    this.a.V = (PhotoFilterScrollView) this.a.findViewById(R.id.ce2);
                    this.a.W = this.a.findViewById(R.id.ce4);
                    this.a.S = (DJITextView) this.a.findViewById(R.id.cdi);
                    ((DJIPhotoEditorSlidingBar) this.a.findViewById(R.id.cdp)).setOnValueChanged(this.a.C);
                    ((DJIPhotoEditorSlidingBar) this.a.findViewById(R.id.cdq)).setOnValueChanged(this.a.C);
                    ((DJIPhotoEditorSlidingBar) this.a.findViewById(R.id.cdr)).setOnValueChanged(this.a.C);
                    final DJIPhotoEditorSlidingBar dJIPhotoEditorSlidingBar = (DJIPhotoEditorSlidingBar) this.a.findViewById(R.id.ce0);
                    dJIPhotoEditorSlidingBar.setRange(-90, 90, false);
                    dJIPhotoEditorSlidingBar.setOnValueChanged(this.a.C);
                    if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                        dJIPhotoEditorSlidingBar.setFontSize(20);
                    }
                    ((RadioGroup) this.a.findViewById(R.id.cdj)).setOnCheckedChangeListener(this.a.D);
                    this.a.X = (DJIPhotoEditorCutView) this.a.findViewById(R.id.cdu);
                    this.a.Y = (DJIPhotoEditorCutView) this.a.findViewById(R.id.cdv);
                    this.a.Z = (DJIPhotoEditorCutView) this.a.findViewById(R.id.cdw);
                    this.a.aa = (DJIPhotoEditorCutView) this.a.findViewById(R.id.cdx);
                    this.a.ab = (DJIPhotoEditorCutView) this.a.findViewById(R.id.cdy);
                    this.a.ac = (DJIPhotoEditorCutView) this.a.findViewById(R.id.cdz);
                    this.a.X.setText(this.a.getString(R.string.v2_photo_editor_rotation_cut_original));
                    this.a.X.setChecked(true);
                    Resources resources = this.a.getResources();
                    this.a.X.setRectSize(resources.getDimension(R.dimen.t3), resources.getDimension(R.dimen.t2));
                    this.a.X.setOnClickListener(this.a.E);
                    this.a.Y.setText(this.a.getString(R.string.v2_photo_editor_rotation_cut_1x1));
                    this.a.Y.setRectSize(resources.getDimension(R.dimen.st), resources.getDimension(R.dimen.ss));
                    this.a.Y.setOnClickListener(this.a.E);
                    this.a.Z.setText(this.a.getString(R.string.v2_photo_editor_rotation_cut_3x4));
                    this.a.Z.setRectSize(resources.getDimension(R.dimen.sv), resources.getDimension(R.dimen.su));
                    this.a.Z.setOnClickListener(this.a.E);
                    this.a.aa.setText(this.a.getString(R.string.v2_photo_editor_rotation_cut_4x3));
                    this.a.aa.setRectSize(resources.getDimension(R.dimen.sx), resources.getDimension(R.dimen.sw));
                    this.a.aa.setOnClickListener(this.a.E);
                    this.a.ab.setText(this.a.getString(R.string.v2_photo_editor_rotation_cut_16x9));
                    this.a.ab.setRectSize(resources.getDimension(R.dimen.sr), resources.getDimension(R.dimen.sq));
                    this.a.ab.setOnClickListener(this.a.E);
                    this.a.ac.setText(this.a.getString(R.string.v2_photo_editor_rotation_cut_9x16));
                    this.a.ac.setRectSize(resources.getDimension(R.dimen.sz), resources.getDimension(R.dimen.sy));
                    this.a.ac.setOnClickListener(this.a.E);
                    this.a.findViewById(R.id.ce1).setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void onClick(View view) {
                            this.b.a.ag = 0.0f;
                            this.b.a.aj = 1.0f;
                            this.b.a.ah = 0.0f;
                            this.b.a.ai = 0.0f;
                            dJIPhotoEditorSlidingBar.setValue(0);
                            this.b.a.b(false);
                            this.b.a.a(false);
                        }
                    });
                    this.a.V.initInnerViews((DJILinearLayout) this.a.findViewById(R.id.ce3));
                    this.a.V.setOnItemClickListener(new PhotoFilterScrollView.a(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(int i) {
                            this.a.a.Q = dji.pilot2.media.a.a.b(this.a.a, i);
                            this.a.a.a(true);
                        }
                    });
                    this.a.findViewById(R.id.ce5).setOnClickListener(this.a.G);
                    this.a.findViewById(R.id.ce7).setOnClickListener(this.a.G);
                    this.a.findViewById(R.id.ce9).setOnClickListener(this.a.G);
                    this.a.ad = (DJIImageView) this.a.findViewById(R.id.cea);
                    this.a.ae = (DJIImageView) this.a.findViewById(R.id.ceb);
                    this.a.af = (DJIImageView) this.a.findViewById(R.id.cec);
                    this.a.ak = (DJIWaitWidget) this.a.findViewById(R.id.ceg);
                    this.a.ak.setLabelAndPosY(this.a.getString(R.string.v2_photo_editor_save_wait_info), lecho.lib.hellocharts.d.c.a);
                    this.a.L.setWaterMarks(this.a.am, this.a.an, this.a.ao);
                    this.a.L.setCutType(b.CUT_ORIGINAL);
                    this.a.L.setVisibility(0);
                    this.a.a(true);
                    this.a.J.setImage(this.a.ap);
                }
            }, 100);
            return;
        }
        finish();
    }

    protected void onResume() {
        super.onResume();
        System.putInt(getContentResolver(), "accelerometer_rotation", 0);
    }

    protected void onPause() {
        super.onPause();
        System.putInt(getContentResolver(), "accelerometer_rotation", 1);
        System.gc();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.J != null && this.J.isDrawingCacheEnabled()) {
            this.J.destroyDrawingCache();
        }
        if (!(this.ap == null || this.ap.isRecycled())) {
            this.ap.recycle();
            this.ap = null;
        }
        dji.pilot2.media.a.a.b();
    }

    private String a(String str) {
        String name = new File(str).getName();
        if (name != null) {
            String[] split = name.split("_");
            if (split.length == 3) {
                name = split[2].substring(0, split[2].indexOf(46));
                if (m.b(name)) {
                    return DateFormat.format("yyyy:MM:dd hh:mm:ss", Long.parseLong(name)).toString();
                }
            }
        }
        return null;
    }

    private boolean b() {
        Options options = new Options();
        options.inSampleSize = 1;
        do {
            try {
                this.ap = BitmapFactory.decodeFile(this.R, options);
                if (this.ap != null) {
                    break;
                }
            } catch (OutOfMemoryError e) {
                options.inSampleSize++;
            }
        } while (options.inSampleSize < 8);
        if (this.ap != null && ((double) ((long) b(this.ap))) > 3.8220595199999996E7d) {
            Bitmap a = a(this.ap, (float) lecho.lib.hellocharts.model.l.n);
            if (a != null) {
                this.ap.recycle();
                this.ap = a;
            }
        }
        if (this.ap != null) {
            return true;
        }
        return false;
    }

    private void a(boolean z) {
        if (z) {
            List arrayList = new ArrayList();
            if (this.Q != null) {
                arrayList.add(this.Q);
            }
            arrayList.add(this.M);
            arrayList.add(this.N);
            arrayList.add(this.O);
            arrayList.add(this.P);
            this.K = new f(arrayList);
            this.J.setFilter(this.K);
        }
        this.J.requestRender();
    }

    private void b(boolean z) {
        DJIPhotoEditorTransformView.a cutRect = this.L.getCutRect();
        float height = ((float) this.ap.getHeight()) / ((float) this.ap.getWidth());
        float f = cutRect.c;
        float f2 = cutRect.d * h.l;
        if (((double) Math.abs(this.ag)) < 1.0E-4d) {
            f2 = Math.max(f / 1.0f, f2 / height);
        } else if (Math.abs(((double) Math.abs(this.ag)) - 1.5707963267948966d) < 1.0E-4d) {
            f2 = Math.max(f2 / 1.0f, f / height);
        } else {
            float sqrt = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
            f2 = (float) Math.abs(((double) sqrt) * Math.cos((double) (((float) Math.acos((double) (f2 / sqrt))) - Math.abs(this.ag))));
            f2 = Math.max(f2 / height, ((float) Math.abs(((double) sqrt) * Math.cos((double) (((float) Math.acos((double) (f / sqrt))) - Math.abs(this.ag))))) / 1.0f);
        }
        if (z) {
            this.ah = 0.0f;
            this.ai = 0.0f;
            this.aj = f2;
        } else if (f2 > this.aj) {
            this.aj = f2;
        }
        this.P.a(this.ag, this.aj, this.ah, this.ai);
    }

    private void a(float f, float f2) {
        DJIPhotoEditorTransformView.a cutRect = this.L.getCutRect();
        float height = ((float) this.ap.getHeight()) / ((float) this.ap.getWidth());
        float f3 = cutRect.c;
        float f4 = cutRect.d * h.l;
        float f5;
        if (((double) Math.abs(this.ag)) < 1.0E-4d) {
            this.ah += f;
            this.ai += f2;
            f5 = (1.0f * this.aj) - f3;
            f4 = (height * this.aj) - f4;
            if (this.ah > f5) {
                this.ah = f5;
            } else if (this.ah < (-f5)) {
                this.ah = -f5;
            }
            if (this.ai > f4) {
                this.ai = f4;
            } else if (this.ai < (-f4)) {
                this.ai = -f4;
            }
        } else if (Math.abs(((double) Math.abs(this.ag)) - 1.5707963267948966d) < 1.0E-4d) {
            this.ah += f;
            this.ai += f2;
            height = (height * this.aj) - f3;
            f4 = (1.0f * this.aj) - f4;
            if (this.ah > height) {
                this.ah = height;
            } else if (this.ah < (-height)) {
                this.ah = -height;
            }
            if (this.ai > f4) {
                this.ai = f4;
            } else if (this.ai < (-f4)) {
                this.ai = -f4;
            }
        } else {
            float f6 = this.ah + f;
            float f7 = this.ai + f2;
            f5 = (1.0f * this.aj) - ((float) ((((double) f3) * Math.cos((double) this.ag)) + (((double) f4) * Math.sin((double) Math.abs(this.ag)))));
            f4 = (height * this.aj) - ((float) ((((double) f3) * Math.sin((double) Math.abs(this.ag))) + (((double) f4) * Math.cos((double) this.ag))));
            f3 = (float) ((((double) f6) * Math.cos((double) this.ag)) - (((double) f7) * Math.sin((double) this.ag)));
            height = (float) ((((double) f7) * Math.cos((double) this.ag)) + (((double) f6) * Math.sin((double) this.ag)));
            if (f3 <= f5) {
                if (f3 < (-f5)) {
                    f5 = -f5;
                } else {
                    f5 = f3;
                }
            }
            if (height <= f4) {
                f4 = height < (-f4) ? -f4 : height;
            }
            this.ah = (float) ((((double) f5) * Math.cos((double) this.ag)) + (((double) f4) * Math.sin((double) this.ag)));
            this.ai = (float) ((((double) f4) * Math.cos((double) this.ag)) + (((double) (-f5)) * Math.sin((double) this.ag)));
        }
        this.P.a(this.ag, this.aj, this.ah, this.ai);
    }

    private void f() {
        this.T.setVisibility(8);
        this.U.setVisibility(8);
        this.V.setVisibility(8);
        this.W.setVisibility(8);
        this.L.enterCutPage(false);
        a(false);
    }

    private void g() {
        this.X.setChecked(false);
        this.Y.setChecked(false);
        this.Z.setChecked(false);
        this.aa.setChecked(false);
        this.ab.setChecked(false);
        this.ac.setChecked(false);
    }

    private void a(Bitmap bitmap) {
        float width;
        float width2 = (((float) bitmap.getWidth()) / ((float) this.L.getWidth())) / this.L.getCutRect().c;
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);
        Rect rect = new Rect();
        float width3 = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        if (this.an.isEmpty()) {
            paint.setTextSize(width2 * 50.0f);
            paint.getTextBounds(this.ao, 0, this.ao.length(), rect);
            width = 0.0f + ((float) rect.width());
        } else {
            paint.setTextSize(width2 * 35.0f);
            paint.getTextBounds(this.an, 0, this.an.length(), rect);
            width = 0.0f + ((float) rect.width());
        }
        paint.setTextSize(width2 * 35.0f);
        paint.getTextBounds(this.am, 0, this.am.length(), rect);
        width = (width + ((float) rect.width())) + ((width2 * 37.0f) * 2.0f);
        if (width > width3) {
            width = (width3 / width) * 0.8f;
        } else {
            width = width2;
        }
        paint.setTextSize(width * 35.0f);
        paint.getTextBounds(this.an, 0, this.an.length(), rect);
        width2 = width * 37.0f;
        float f = height - (width * 37.0f);
        if (this.af.getVisibility() == 0) {
            canvas.drawText(this.an, width2, f, paint);
        }
        paint.setTextSize(width * 50.0f);
        paint.getTextBounds(this.ao, 0, this.ao.length(), rect);
        float f2 = width * 37.0f;
        width2 = height - (width * 37.0f);
        if (this.af.getVisibility() == 0) {
            width2 = (f - ((float) rect.height())) - (20.0f * width);
        }
        if (this.ad.getVisibility() == 0) {
            canvas.drawText(this.ao, f2, width2, paint);
        }
        paint.setTextSize(width * 35.0f);
        paint.getTextBounds(this.am, 0, this.am.length(), rect);
        width2 = (width3 - ((float) rect.width())) - (width * 37.0f);
        width = height - (width * 37.0f);
        if (this.ae.getVisibility() == 0) {
            canvas.drawText(this.am, width2, width, paint);
        }
    }

    private void h() {
        final Handler handler = new Handler();
        final Runnable anonymousClass7 = new Runnable(this) {
            final /* synthetic */ DJIPhotoEditorActivity a;

            {
                this.a = r1;
            }

            public void run() {
                int i = 0;
                if (this.a.H != null) {
                    MediaScannerConnection.scanFile(this.a, new String[]{this.a.H}, null, null);
                    String str = this.a.H;
                    try {
                        ExifInterface exifInterface = new ExifInterface(str);
                        String[] strArr = new String[]{"FNumber", "DateTime", dji.pilot2.utils.a.a.m, "Flash", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "Make", "WhiteBalance", "ExposureTime", "ISOSpeedRatings", dji.sdksharedlib.b.b.bW};
                        int length = strArr.length;
                        while (i < length) {
                            String str2 = strArr[i];
                            String attribute = this.a.aq.getAttribute(str2);
                            if (attribute != null) {
                                exifInterface.setAttribute(str2, attribute);
                            }
                            i++;
                        }
                        exifInterface.saveAttributes();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(this.a, DJIShareActivity.class);
                    intent.putExtra("file_path", str);
                    intent.putExtra(DJIShareActivity.L, 1);
                    this.a.startActivityForResult(intent, 4);
                    return;
                }
                if (this.a.az) {
                    Toast.makeText(this.a, this.a.getString(R.string.v2_out_of_memory_info), 1).show();
                } else {
                    Toast.makeText(this.a, this.a.getString(R.string.v2_save_photo_failed), 1).show();
                }
                this.a.finish();
            }
        };
        new Thread(this) {
            final /* synthetic */ DJIPhotoEditorActivity c;

            public void run() {
                try {
                    if (a()) {
                        this.c.a(false);
                        DJIPhotoEditorTransformView.a cutRect = this.c.L.getCutRect();
                        Bitmap cutBitmapBaseOnPhotoSize = this.c.J.cutBitmapBaseOnPhotoSize(this.c.ap.getWidth(), this.c.ap.getHeight(), cutRect.a, cutRect.b, cutRect.c, cutRect.d);
                        this.c.a(cutBitmapBaseOnPhotoSize);
                        String a = com.dji.frame.c.d.a(this.c.getApplicationContext(), "PhotoEditor");
                        this.c.H = a(cutBitmapBaseOnPhotoSize, a);
                        cutBitmapBaseOnPhotoSize.recycle();
                        System.gc();
                    } else {
                        this.c.az = true;
                    }
                } catch (InterruptedException e) {
                    this.c.az = false;
                    e.printStackTrace();
                } catch (OutOfMemoryError e2) {
                    this.c.az = true;
                    e2.printStackTrace();
                }
                handler.post(anonymousClass7);
            }

            private String a(Bitmap bitmap, String str) {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                file = new File(str, String.format("%d.jpg", new Object[]{Long.valueOf(System.currentTimeMillis())}));
                if (file.exists()) {
                    file.delete();
                }
                try {
                    OutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return file.getAbsolutePath();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }

            private boolean a() {
                try {
                    this.c.L.setConverBitmap(this.c.J.getBitmap());
                    return true;
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    return false;
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
        }.start();
    }

    @TargetApi(17)
    protected void a() {
        if (this.M.c() != 0.0f) {
            e.b(j.ej_);
        }
        if (this.N.c() != 1.0f) {
            e.b(j.bS_);
        }
        if (this.O.c() != 1.0f) {
            e.b(j.ek_);
        }
        if (this.L.getCutType() == b.CUT_ORIGINAL) {
            e.b(j.bT_);
        } else if (this.L.getCutType() == b.CUT_1X1) {
            e.b(j.el_);
        } else if (this.L.getCutType() == b.CUT_3X4) {
            e.b(j.em_);
        } else if (this.L.getCutType() == b.CUT_4X3) {
            e.b(j.en_);
        } else if (this.L.getCutType() == b.CUT_9X16) {
            e.b(j.ep_);
        } else if (this.L.getCutType() == b.CUT_16X9) {
            e.b(j.eo_);
        }
        if (this.ag != 0.0f) {
            e.b(j.o);
        }
        HashMap hashMap = new HashMap();
        if (VERSION.SDK_INT >= 17) {
            Configuration configuration = new Configuration(getResources().getConfiguration());
            configuration.setLocale(Locale.US);
            hashMap.put(dji.pilot.fpv.d.d.dH, createConfigurationContext(configuration).getResources().getString(dji.pilot2.media.a.a.a(this.V.getCurSelectIndex())));
        }
        e.a(j.eq_, hashMap);
        e.b(j.d);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 4:
                if (i2 != 1) {
                    finish();
                    return;
                } else {
                    this.ak.setVisibility(4);
                    return;
                }
            default:
                return;
        }
    }

    protected void onStart() {
        super.onStart();
        e.b(this);
    }

    protected void onStop() {
        e.c(this);
        super.onStop();
    }

    private Bitmap a(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @SuppressLint({"NewApi"})
    private int b(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 19) {
            return bitmap.getAllocationByteCount();
        }
        if (VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
