package dji.pilot.publics.objects;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import com.dji.frame.c.c;
import com.facebook.o;
import com.here.odnp.config.OdnpConfigStatic;
import com.tencent.android.tpush.common.Constants;
import dji.dbox.upgrade.p4.a.a;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.pilot.R;
import dji.pilot.flyforbid.FlyforbidUpdateService;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.u;
import dji.pilot.fpv.control.v;
import dji.pilot.publics.c.h;
import dji.pilot.publics.e.d;
import dji.pilot.server.b;
import dji.pilot.upgrade.e;
import dji.pilot.usercenter.b.f;
import dji.pilot2.mine.activity.LanguageSettingActivity;
import dji.pilot2.publics.b.a$j;
import dji.pilot2.publics.object.DJINotificationDialog;
import dji.publics.DJIObject.DJIBaseApplication;
import java.security.SignatureException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class DJIApplication extends DJIBaseApplication {
    public static final String a = "DJI GO START";
    public static final String b = "X529Q7SM6P224YP253M4";
    public static final String c = "Expired_App_Version";
    public static String e = "";
    public static String f = "";
    private static Context g;
    b d;
    private dji.thirdparty.afinal.b.b h = new dji.thirdparty.afinal.b.b(this) {
        final /* synthetic */ DJIApplication a;

        {
            this.a = r1;
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i2 != 5 || i == 1) {
                if (i2 == 5 && i == 2) {
                    try {
                        sQLiteDatabase.execSQL("ALTER TABLE dji_pilot2_mine_db_DraftBean ADD COLUMN createTime TEXT default '0';");
                    } catch (Exception e) {
                    }
                }
                if (i2 == 5 && i == 3) {
                    try {
                        sQLiteDatabase.execSQL("ALTER TABLE dji_pilot_groundStation_db_DJIWPCollectionItem ADD COLUMN autoAddFlag INTEGER default 0;");
                    } catch (Exception e2) {
                    }
                }
                if (i2 == 5 && i == 4) {
                    try {
                        sQLiteDatabase.execSQL("ALTER TABLE dji_pilot2_mine_db_DraftBean ADD COLUMN tag TEXT default '';");
                        return;
                    } catch (Exception e3) {
                        return;
                    }
                }
            }
            sQLiteDatabase.execSQL("ALTER TABLE dji_pilot2_mine_db_DraftBean ADD COLUMN createTime TEXT default '0';");
            sQLiteDatabase.execSQL("ALTER TABLE dji_pilot_groundStation_db_DJIWPCollectionItem ADD COLUMN autoAddFlag INTEGER default 0;");
            if (i2 == 5) {
            }
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    };
    private h i;

    public DJIApplication() {
        g = this;
    }

    public static Context a() {
        return g;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Log.e(a, "step 1 DJIApplication  MultiDex.install befor " + System.currentTimeMillis());
        MultiDex.install(this);
        Log.e(a, "step 1 DJIApplication  MultiDex.install after " + System.currentTimeMillis());
    }

    public void onCreate() {
        super.onCreate();
        f = getPackageName();
        if (f.equals(a(this))) {
            o.a((Context) this);
            d.c((Context) this);
            a.j = getResources().getString(R.string.versionname);
            e = a.j;
            dji.pilot.c.b.a(this);
            c();
            c.a(5, this.h);
            dji.a.a.getInstance().a((Context) this, false);
            dji.sdksharedlib.a.getInstance().a(this);
            this.d = new b();
            try {
                this.d.a();
            } catch (SignatureException e) {
                e.printStackTrace();
            }
            v.getInstance().a((Context) this);
            dji.pilot.publics.c.a.a(this);
            dji.pilot.publics.control.upgrade.b.getInstance().a((Context) this);
            dji.pilot.publics.control.rc.b.getInstance().a((Context) this);
            dji.pilot.publics.control.a.getInstance(this);
            DJIGenSettingDataManager.getInstance().a((Context) this);
            dji.pilot.fpv.camera.more.a.getInstance().a((Context) this);
            b(this);
            dji.pilot.fpv.camera.more.a.getInstance().y();
            f.getInstance().a((Context) this);
            dji.pilot.battery.a.a.getInstance().a((Context) this);
            dji.pilot.publics.c.d.a((Context) this);
            this.i = h.getInstance(this);
            d.b((Context) this);
            e = getResources().getString(R.string.versionname);
            dji.pilot.fpv.d.a.getInstance().a((Context) this);
            dji.pilot2.b.a(this);
            dji.pilot.publics.c.c.getInstance();
            DJINotificationDialog.a = g.b((Context) this, "tips", false);
            dji.pilot.upgrade.b.getInstance().a();
            e.getInstance().a((Context) this);
            dji.pilot.upgrade.f.getInstance().a((Context) this);
            dji.pilot.upgrade.a.getInstance().a((Context) this);
            dji.pilot.fpv.flightmode.c.getInstance();
            dji.logic.c.b.getInstance();
            u.getInstance();
            dji.pilot.fpv.camera.focus.a.a.getInstance();
            DJIFlyForbidController.getInstance(this);
            dji.pilot.flyunlimit.b.getInstance(this);
            dji.pilot2.publics.a.a.a((Context) this);
            dji.pilot.flyforbid.b.a();
            int maxMemory = ((int) Runtime.getRuntime().maxMemory()) / 20;
            new Thread(new Runnable(this) {
                final /* synthetic */ DJIApplication a;

                {
                    this.a = r1;
                }

                public void run() {
                    dji.pilot.usercenter.b.a instance = dji.pilot.usercenter.b.a.getInstance();
                    instance.a(new 1(this));
                    instance.c(this.a.getApplicationContext());
                }
            }).start();
            c.a((Context) this).e(maxMemory);
            if (!dji.thirdparty.a.c.a().c((Object) this)) {
                dji.thirdparty.a.c.a().a((Object) this);
            }
            if (getString(R.string.versionname).compareTo(g.b((Context) this, c, "")) == 0) {
                dji.thirdparty.a.c.a().e(FlyforbidUpdateService.a.appExpired);
            }
            dji.pilot.fpv.control.a.b.getInstance().a();
            b();
            dji.pilot.liveshare.c.getInstance().setContext(getApplicationContext());
            dji.pilot.fpv.d.e.a((Context) this);
            com.dji.a.a.a(a(), "437132", "NYSADRUHDSHAFSFC");
            com.dji.a.a.a(dji.pilot.c.a.F);
            if (dji.pilot.c.a.F) {
                com.dji.a.a.b(true);
            }
            com.dji.a.a.d(dji.pilot2.mine.b.e.getInstance().h());
            dji.pilot.fpv.control.b.a.getInstance().a(getApplicationContext());
            dji.pilot.popup.b.a.getInstance();
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ DJIApplication a;

                {
                    this.a = r1;
                }

                public void run() {
                    dji.pilot.popup.b.b.getInstance().a(DJIApplication.a(), true);
                }
            }, 10001);
        }
    }

    public void b() {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = d();
        resources.updateConfiguration(configuration, displayMetrics);
    }

    private Locale d() {
        String b = g.b(getApplicationContext(), LanguageSettingActivity.a, "auto");
        if (b.equals("auto")) {
            return Resources.getSystem().getConfiguration().locale;
        }
        if (b.equals("cn")) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        if (b.equals("tw")) {
            return Locale.TRADITIONAL_CHINESE;
        }
        if (b.equals(a$j.e)) {
            return Locale.JAPAN;
        }
        if (b.equals("en")) {
            return Locale.ENGLISH;
        }
        return Resources.getSystem().getConfiguration().locale;
    }

    String a(Context context) {
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public void onEventMainThread(FlyforbidUpdateService.a aVar) {
        if (aVar == FlyforbidUpdateService.a.failed) {
            Toast.makeText(this, getString(R.string.fly_unlimit_check_airmap_file_failed_desc), 1).show();
        } else if (aVar == FlyforbidUpdateService.a.appExpired) {
            g.a((Context) this, c, getString(R.string.versionname));
            Toast.makeText(this, getString(R.string.fly_unlimit_app_expired), 1).show();
        }
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ DJIApplication a;

            {
                this.a = r1;
            }

            public void run() {
                System.exit(0);
            }
        }, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
    }

    public void onEventMainThread(dji.pilot2.publics.a.a.a aVar) {
        if (aVar == dji.pilot2.publics.a.a.a.exitApp) {
            new Timer().schedule(new TimerTask(this) {
                final /* synthetic */ DJIApplication a;

                {
                    this.a = r1;
                }

                public void run() {
                    System.exit(0);
                }
            }, 2000);
        }
    }

    private void b(Context context) {
        try {
            Class cls = Class.forName("dji.device.camera.datamanager.DJICameraDataManagerCompat");
            cls.getMethod("initEventBus", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
