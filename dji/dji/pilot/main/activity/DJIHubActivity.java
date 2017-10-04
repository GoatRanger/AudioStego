package dji.pilot.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.dji.frame.c.c;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot.c.b;
import dji.pilot.fpv.activity.DJIPreviewActivity;
import dji.pilot.fpv.activity.DJIPreviewActivityGrape;
import dji.pilot.fpv.activity.DJIPreviewActivityKumquat;
import dji.pilot.fpv.activity.DJIPreviewActivityLitchi;
import dji.pilot.fpv.activity.DJIPreviewActivityTomato;
import dji.pilot.reflect.FpvReflect;
import dji.pilot2.mine.activity.LanguageSettingBaseActivity;

public class DJIHubActivity extends LanguageSettingBaseActivity {
    private String a = "DJIHubActivity";
    private Class<?> b = DJIPreviewActivity.class;
    private boolean c;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ProductType.values().length];

        static {
            try {
                a[ProductType.litchiC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ProductType.litchiS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ProductType.litchiX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ProductType.P34K.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ProductType.Tomato.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ProductType.Orange2.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ProductType.Pomato.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[ProductType.KumquatX.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[ProductType.KumquatS.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[ProductType.Longan.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[ProductType.LonganPro.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[ProductType.LonganRaw.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[ProductType.LonganZoom.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[ProductType.LonganMobile.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[ProductType.A2.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[ProductType.Grape2.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
        }
    }

    public enum a {
        START,
        FINISH
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hub_activity);
        this.c = getIntent().getBooleanExtra(b.a, true);
        c.a(getWindow());
        Log.d(this.a, "onCreate");
        dji.a.a.getInstance().a(getApplicationContext());
        a();
    }

    private void a() {
        Class cls;
        Intent intent = new Intent();
        if (!ServiceManager.getInstance().isOK() || ServiceManager.getInstance().isRemoteOK()) {
            switch (AnonymousClass1.a[i.getInstance().c().ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    cls = DJIPreviewActivityLitchi.class;
                    break;
                case 7:
                    cls = DJIPreviewActivityTomato.class;
                    break;
                case 8:
                case 9:
                    cls = DJIPreviewActivityKumquat.class;
                    break;
                case 10:
                case 11:
                case 12:
                case 13:
                    cls = FpvReflect.getLonganClass();
                    if (cls == null) {
                        cls = DJIPreviewActivityLitchi.class;
                        break;
                    }
                    break;
                case 14:
                    cls = FpvReflect.getLpClass();
                    if (cls == null) {
                        cls = FpvReflect.getLpClass();
                    }
                    intent.addFlags(536870912);
                    break;
                case 15:
                case 16:
                    cls = DJIPreviewActivityGrape.class;
                    break;
                default:
                    cls = this.b;
                    break;
            }
        } else if (i.getInstance().a() == ProductType.Orange || i.getInstance().a() == ProductType.BigBanana || i.getInstance().a() == ProductType.Olives || i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3 || dji.pilot.publics.e.a.d(null) || i.getInstance().a() == ProductType.OrangeRAW || i.getInstance().a() == ProductType.OrangeCV600 || i.getInstance().a() == ProductType.None) {
            cls = DJIPreviewActivity.class;
        } else if (i.getInstance().a() == ProductType.Grape2 || i.getInstance().c() == ProductType.A2) {
            cls = DJIPreviewActivityGrape.class;
        } else if (i.getInstance().a() == ProductType.KumquatX) {
            cls = DJIPreviewActivityKumquat.class;
        } else if (i.getInstance().a() == ProductType.Pomato) {
            cls = DJIPreviewActivityTomato.class;
        } else {
            cls = DJIPreviewActivityLitchi.class;
        }
        ServiceManager.getInstance().pauseService(true);
        intent.setClass(this, cls);
        intent.putExtra(b.a, this.c);
        startActivityForResult(intent, 0);
        overridePendingTransition(17432576, 0);
    }

    protected void onStart() {
        Log.d(this.a, "onStart");
        super.onStart();
    }

    protected void onRestart() {
        Log.d(this.a, "onRestart");
        super.onRestart();
    }

    protected void onResume() {
        Log.d(this.a, "onResume");
        super.onResume();
    }

    protected void onPause() {
        Log.d(this.a, "onPause");
        super.onPause();
    }

    protected void onStop() {
        Log.d(this.a, "onStop");
        super.onStop();
    }

    protected void onDestroy() {
        Log.d(this.a, "onDestroy");
        super.onDestroy();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        finish();
    }
}
