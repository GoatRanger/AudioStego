package dji.device.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import dji.device.common.DJIUIEventManagerLongan;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.midware.data.model.P3.DataOsdSetPower;
import dji.midware.data.model.P3.DataOsdSetPower.POWER_TYPE;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.pilot.set.e;
import dji.thirdparty.a.c;

public class LonganSleepView extends RelativeLayout {
    private static final String j = "LonganSleepView";
    private static final String k = "screen_lock";
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 3;
    ImageView a;
    ImageView b;
    ImageButton c;
    TextView d;
    DJIStateImageViewCompat e;
    boolean f = false;
    Animation g = null;
    Animation h = null;
    Handler i = new Handler(new Callback(this) {
        final /* synthetic */ LonganSleepView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Toast.makeText(this.a.getContext(), R.string.longan_sleep_power_off_failed_tip, 0).show();
                    break;
                case 2:
                    Toast.makeText(this.a.getContext(), R.string.longan_sleep_sleep_mode_failed_tip, 0).show();
                    break;
                case 3:
                    this.a.f();
                    break;
            }
            return false;
        }
    });
    private GestureDetector o;
    private int p = 0;
    private OnGestureListener q = new OnGestureListener(this) {
        final /* synthetic */ LonganSleepView a;

        {
            this.a = r1;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent.getX() - motionEvent2.getX() > ((float) (this.a.p / 4)) && Math.abs(motionEvent2.getY() - motionEvent.getY()) < ((float) this.a.p)) {
                this.a.f();
            }
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }
    };
    private boolean r = false;

    public LonganSleepView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = AnimationUtils.loadAnimation(context, R.anim.slide_left_in_decelerate);
        this.h = AnimationUtils.loadAnimation(context, R.anim.slide_left_out_decelerate);
        this.h.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ LonganSleepView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.setVisibility(4);
            }
        });
        this.o = new GestureDetector(getContext(), this.q);
        this.o.setIsLongpressEnabled(false);
        setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ LonganSleepView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return this.a.o.onTouchEvent(motionEvent);
            }
        });
        this.p = getResources().getDimensionPixelOffset(R.dimen.dp_70_in_sw320dp);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            this.d = (TextView) findViewById(R.id.longan_sleep_descripe_tv);
            this.a = (ImageView) findViewById(R.id.longan_sleep_awake_btn);
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ LonganSleepView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (DataOsdGetPushPowerStatus.getInstance().getPowerStatus() == 0) {
                        this.a.b();
                    } else {
                        DataOsdSetPower.getInstance().a(POWER_TYPE.AWEAK).start(new d(this) {
                            final /* synthetic */ AnonymousClass7 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                this.a.a.i.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.a.d.setText(R.string.longan_sleep_awaking);
                                    }
                                });
                            }

                            public void onFailure(a aVar) {
                            }
                        });
                    }
                }
            });
            this.b = (ImageView) findViewById(R.id.longan_power_off_iv);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ LonganSleepView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a();
                }
            });
            this.c = (ImageButton) findViewById(R.id.longan_sleep_lock_iv);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ LonganSleepView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.c();
                }
            });
            setScreenLock(a(k));
            this.e = (DJIStateImageViewCompat) findViewById(R.id.longan_sleep_cancle_iv);
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ LonganSleepView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.f();
                    this.a.f = true;
                }
            });
            onEventMainThread(DataOsdGetPushPowerStatus.getInstance());
            d();
        }
    }

    private void a() {
        e.a(getContext(), R.string.longan_sleep_power_off_tip, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LonganSleepView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                DataOsdSetPower.getInstance().a(POWER_TYPE.POWER_OFF).start(new d(this) {
                    final /* synthetic */ AnonymousClass11 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.i.sendEmptyMessage(3);
                    }

                    public void onFailure(a aVar) {
                        this.a.a.i.sendEmptyMessage(1);
                    }
                });
            }
        });
    }

    private void b() {
        if (DataGimbalGetPushAbnormalStatus.getInstance().getFanDirection() == 1) {
            e.b(getContext(), R.string.longan_unsupport_sleepmode_tip, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ LonganSleepView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        } else {
            e.a(getContext(), R.string.longan_sleep_sleep_mode_tip, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ LonganSleepView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    DataOsdSetPower.getInstance().a(POWER_TYPE.SLEEP).start(new d(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(a aVar) {
                            this.a.a.i.sendEmptyMessage(2);
                        }
                    });
                }
            });
        }
    }

    private void c() {
        setScreenLock(!this.r);
        a(this.r);
    }

    private void setScreenLock(boolean z) {
        Context context = getContext();
        if (!(context instanceof Activity)) {
            return;
        }
        if (z) {
            if (getResources().getConfiguration().orientation == 2) {
                ((Activity) context).setRequestedOrientation(6);
            } else {
                ((Activity) context).setRequestedOrientation(7);
            }
            this.r = true;
            this.c.setSelected(true);
            return;
        }
        ((Activity) context).setRequestedOrientation(4);
        this.r = false;
        this.c.setSelected(false);
    }

    private void a(boolean z) {
        Context context = getContext();
        context.getSharedPreferences(context.getPackageName(), 0).edit().putBoolean(k, this.r).commit();
        Log.d(j, "save local:" + z);
    }

    private boolean a(String str) {
        Context context = getContext();
        boolean z = context.getSharedPreferences(context.getPackageName(), 0).getBoolean(k, false);
        Log.d(j, "get local:" + z);
        return z;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        d();
    }

    private void d() {
        LayoutParams layoutParams = (LayoutParams) findViewById(R.id.longan_options_ly).getLayoutParams();
        if (getResources().getConfiguration().orientation == 2) {
            layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.longan_power_view_btn_margin_top_land);
        } else {
            layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.longan_power_view_btn_margin_top_port);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(DataOsdGetPushPowerStatus dataOsdGetPushPowerStatus) {
        Log.d("osd status", "power status:" + dataOsdGetPushPowerStatus.getPowerStatus() + "is power off:" + dataOsdGetPushPowerStatus.getIsPowerOff());
        if (dataOsdGetPushPowerStatus.getIsPowerOff()) {
            f();
            if (this.a != null && this.a.isSelected()) {
                this.a.setSelected(false);
            }
        } else if (dataOsdGetPushPowerStatus.getPowerStatus() == 1) {
            c.a().e(DJIUIEventManagerLongan.e.ENTER_SLEEP_MODE);
            this.a.setSelected(true);
            this.d.setText(R.string.longan_sleep_description);
            if (!this.f) {
                e();
                c.a().e(m.SHOW_ALL);
            }
        } else if (dataOsdGetPushPowerStatus.getPowerStatus() == 0) {
            this.f = false;
            if (ServiceManager.getInstance().isConnected()) {
                c.a().e(o.b);
            }
            this.a.setSelected(false);
            this.d.setText("");
            if (isShown()) {
                f();
            }
        }
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.SHOW_POWER_VIEW) {
            e();
        }
    }

    private void e() {
        if (getVisibility() != 0) {
            setVisibility(0);
            startAnimation(this.g);
        }
    }

    private void f() {
        if (getVisibility() != 4) {
            startAnimation(this.h);
        }
    }
}
