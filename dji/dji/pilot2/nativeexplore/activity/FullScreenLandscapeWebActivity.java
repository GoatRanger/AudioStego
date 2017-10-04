package dji.pilot2.nativeexplore.activity;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.here.odnp.config.OdnpConfigStatic;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import java.lang.ref.WeakReference;

public class FullScreenLandscapeWebActivity extends DJIBaseActivity {
    View a;
    View b;
    private String c = null;
    private String d = null;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private Handler i;

    public static class a extends Handler {
        public static final int a = 1030294;
        WeakReference<FullScreenLandscapeWebActivity> b;

        public a(FullScreenLandscapeWebActivity fullScreenLandscapeWebActivity) {
            this.b = new WeakReference(fullScreenLandscapeWebActivity);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case a /*1030294*/:
                    try {
                        ((FullScreenLandscapeWebActivity) this.b.get()).b.setVisibility(8);
                        break;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        break;
                    }
            }
            super.handleMessage(message);
        }
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.v2_activity_fullscreen_web);
        a();
        this.i = new a(this);
        FragmentManager fragmentManager = getFragmentManager();
        Fragment findFragmentById = fragmentManager.findFragmentById(R.id.i5);
        this.b = findViewById(R.id.c98);
        this.a = findViewById(R.id.c97);
        if (findFragmentById == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(DJIWebviewFragment.o, this.c);
            bundle2.putString(DJIWebviewFragment.p, this.d);
            bundle2.putBoolean(DJIWebviewFragment.r, this.e);
            bundle2.putBoolean(DJIWebviewFragment.s, this.f);
            bundle2.putBoolean(DJIWebviewFragment.t, this.g);
            bundle2.putBoolean(DJIWebviewFragment.u, this.h);
            fragmentManager.beginTransaction().add(R.id.i5, DJIWebviewFragment.b(bundle2)).commit();
        }
        this.a.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ FullScreenLandscapeWebActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.b.setVisibility(0);
                if (motionEvent.getAction() == 0) {
                    if (this.a.i.hasMessages(a.a)) {
                        this.a.i.removeMessages(a.a);
                    }
                    this.a.i.sendEmptyMessageDelayed(a.a, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                }
                return false;
            }
        });
        this.i.sendEmptyMessageDelayed(a.a, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getStringExtra(DJIWebviewFragment.o);
            this.d = intent.getStringExtra(DJIWebviewFragment.p);
            this.e = intent.getBooleanExtra(DJIWebviewFragment.r, false);
            this.f = intent.getBooleanExtra(DJIWebviewFragment.s, false);
            this.g = intent.getBooleanExtra(DJIWebviewFragment.t, false);
            this.h = intent.getBooleanExtra(DJIWebviewFragment.u, false);
        }
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c98:
                finish();
                return;
            default:
                return;
        }
    }
}
