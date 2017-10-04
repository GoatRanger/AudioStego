package dji.pilot2.mine.activity;

import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.NfzAccountEvent;
import dji.midware.util.i;
import dji.pilot.R;
import dji.pilot.flyunlimit.jsonbean.UnlockListItem;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.c$m;
import dji.pilot.publics.e.d;
import dji.pilot.support.longan.DJISupportLongan;
import dji.pilot.usercenter.b.f;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.main.fragment.DJIMineFragment;
import dji.pilot2.mine.b.e;
import dji.pilot2.mine.jsonbean.UpdateJsonBean;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.utils.k;
import dji.pilot2.utils.l;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.pilot2.widget.a;

public class SettingsActivity extends DJIActivityNoFullScreen implements c$m {
    public static final String G = "key_force_landscap";
    private static final String I = "SettingsActivity";
    public boolean H = false;
    private Switch J;
    private Switch K;
    private View L;
    private View M;
    private View N;
    private View O;
    private View P;
    private View Q;
    private TextView R;
    private TextView S;
    private TextView T;
    private Boolean U;
    private Boolean V = Boolean.valueOf(false);
    private Boolean W;
    private Boolean X;
    private String Y;
    private String Z;
    private String[] aa;
    private boolean ab;

    protected void onCreate(Bundle bundle) {
        setContentView(R.layout.v2_activity_settings);
        this.H = getIntent().getBooleanExtra("key_force_landscap", false);
        a();
        b();
        super.onCreate(bundle);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !this.ab) {
            int dimension = (int) getResources().getDimension(R.dimen.gw);
            this.ab = true;
            if (this.Q != null) {
                int[] iArr = new int[2];
                Point point = new Point();
                this.Q.getLocationInWindow(iArr);
                getWindowManager().getDefaultDisplay().getSize(point);
                if (iArr[1] + this.Q.getHeight() < point.y) {
                    this.Q.setPadding(0, point.y - (iArr[1] + this.Q.getHeight()), 0, dimension);
                } else {
                    this.Q.setPadding(dimension, dimension, dimension, dimension);
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onResume() {
        super.onResume();
        if (this.H && getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
        if (b.a(getContentResolver())) {
            this.V = Boolean.valueOf(true);
        } else {
            this.V = Boolean.valueOf(false);
        }
        this.K.setChecked(this.V.booleanValue());
        new dji.pilot2.mine.d.b(this.aa, this.R).execute(new Void[]{(Void) null});
    }

    private void a() {
        this.aa = new String[]{CleanCacheActivity.G + CleanCacheActivity.I, CleanCacheActivity.G + CleanCacheActivity.J, CleanCacheActivity.G + CleanCacheActivity.K, CleanCacheActivity.G + CleanCacheActivity.L, CleanCacheActivity.G + CleanCacheActivity.M, CleanCacheActivity.G + CleanCacheActivity.N, CleanCacheActivity.G + CleanCacheActivity.O};
        this.U = Boolean.valueOf(e.getInstance().h());
        this.X = Boolean.valueOf(e.getInstance().i());
        String b = dji.pilot.c.b.b(this);
        if (b == null) {
            b = getResources().getString(R.string.buildname);
        }
        this.Z = getResources().getString(R.string.mine_settings_version, new Object[]{getResources().getString(R.string.versionname), b});
        this.ab = false;
    }

    private void b() {
        this.J = (Switch) findViewById(R.id.cf8);
        this.K = (Switch) findViewById(R.id.cf9);
        this.P = findViewById(R.id.cfe);
        this.P.setVisibility(8);
        this.L = findViewById(R.id.cfi);
        this.M = findViewById(R.id.cfk);
        this.N = findViewById(R.id.cfn);
        this.O = findViewById(R.id.cfg);
        this.R = (TextView) findViewById(R.id.cfd);
        this.S = (TextView) findViewById(R.id.cfm);
        this.T = (TextView) findViewById(R.id.cfq);
        this.Q = findViewById(R.id.cfo);
        this.T.setText(this.Z);
        if (f.getInstance().c()) {
            this.N.setVisibility(0);
        } else {
            this.N.setVisibility(4);
        }
        this.J.setChecked(this.U.booleanValue());
        this.J.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ SettingsActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.U = Boolean.valueOf(z);
                e.getInstance().a(z);
            }
        });
        if (b.a(getContentResolver())) {
            this.V = Boolean.valueOf(true);
        } else {
            this.V = Boolean.valueOf(false);
        }
        this.K.setChecked(this.V.booleanValue());
        this.K.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ SettingsActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                try {
                    this.a.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
                    com.dji.frame.c.b.a(this.a, 3);
                } catch (Exception e) {
                    d.b(this.a, false);
                }
            }
        });
        if (this.X.booleanValue()) {
            this.S.setVisibility(0);
        } else {
            this.S.setVisibility(4);
        }
    }

    public void onClickHandler(View view) {
        Builder bVar;
        switch (view.getId()) {
            case R.id.c77:
                finish();
                return;
            case R.id.cf_:
                bVar = new dji.pilot2.publics.object.b(this);
                bVar.setMessage(R.string.mine_settings_reset_guide_confirm);
                bVar.setPositiveButton(17039379, new OnClickListener(this) {
                    final /* synthetic */ SettingsActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        a.a(this.a);
                        i.a(this.a.getApplicationContext(), "is_FirstTime");
                        DJISupportLongan.resetBeginnerGuide(this.a.getApplicationContext());
                        dji.pilot.visual.beginner.a.getInstance().k();
                        Toast.makeText(this.a, R.string.mine_settings_reset_guide_toast, 0).show();
                    }
                });
                bVar.setNegativeButton(17039369, new OnClickListener(this) {
                    final /* synthetic */ SettingsActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                bVar.show();
                return;
            case R.id.cfb:
                startActivity(new Intent(this, CleanCacheActivity.class));
                return;
            case R.id.cfe:
                startActivity(new Intent(this, NetworkStatActivity.class));
                return;
            case R.id.cff:
                startActivity(new Intent(this, LanguageSettingActivity.class));
                return;
            case R.id.cfg:
                startActivity(new Intent(this, PrivacyActivity.class));
                return;
            case R.id.cfi:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
                    return;
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Couldn't launch the market", 1).show();
                    return;
                }
            case R.id.cfk:
                c.b(this).a(k.j(), new dji.thirdparty.afinal.f.a<String>(this) {
                    final /* synthetic */ SettingsActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                    }

                    public void a(long j, long j2) {
                    }

                    public void a(String str) {
                        if (str != null) {
                            UpdateJsonBean updateJsonBean = (UpdateJsonBean) h.b(str, UpdateJsonBean.class);
                            if (updateJsonBean == null) {
                                Toast.makeText(this.a, R.string.mine_settings_cannot_retrive_update, 1).show();
                            } else if (updateJsonBean.notice.update == 1) {
                                e.getInstance().b(true);
                                this.a.S.setVisibility(0);
                                Toast.makeText(this.a, R.string.mine_settings_has_update, 1).show();
                            } else {
                                Toast.makeText(this.a, R.string.mine_settings_no_update, 1).show();
                            }
                        }
                    }

                    public void a(Throwable th, int i, String str) {
                    }
                });
                return;
            case R.id.cfn:
                bVar = new dji.pilot2.publics.object.b(this);
                bVar.setMessage(R.string.mine_settings_confirm_sign_out_detail);
                bVar.setPositiveButton(17039379, new OnClickListener(this) {
                    final /* synthetic */ SettingsActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        NfzAccountEvent nfzAccountEvent = new NfzAccountEvent();
                        nfzAccountEvent.setAccount("");
                        nfzAccountEvent.setAccount(dji.pilot.flyforbid.a.getInstance(this.a).d());
                        dji.thirdparty.a.c.a().e(nfzAccountEvent);
                        DJIFlyForbidController.getInstance().getDb().a(UnlockListItem.class);
                        f.getInstance().p();
                        dji.thirdparty.a.c.a().e(ExploreEvent.USER_LOGOUT);
                        DJIMineFragment.P.sendEmptyMessage(1);
                        dji.pilot2.mine.b.a.getInstance().c();
                        dji.pilot2.mine.b.c.getInstance().a(null);
                        k.a(this.a);
                        l.getInstance().b();
                        dji.pilot.fpv.d.a.getInstance().a();
                        this.a.finish();
                    }
                });
                bVar.setNegativeButton(17039369, new OnClickListener(this) {
                    final /* synthetic */ SettingsActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                bVar.show();
                return;
            case R.id.cfp:
                Location e2 = dji.a.a.getInstance().e();
                Intent intent;
                if (e2 != null) {
                    DJILogHelper.getInstance().LOGI("Lyric", "device location: lat: " + e2.getLatitude() + " lng: " + e2.getLongitude());
                    intent = new Intent(this, WebActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, k.B());
                    startActivity(intent);
                    return;
                }
                DJILogHelper.getInstance().LOGI("Lyric", "device location: null");
                intent = new Intent(this, WebActivity.class);
                intent.putExtra(DJIWebviewFragment.o, k.B());
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b(this);
    }

    protected void onStop() {
        dji.pilot.fpv.d.e.c(this);
        super.onStop();
    }
}
