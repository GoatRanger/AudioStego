package dji.phone.tutorial;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataGimbalGetPushTutorialStatus;
import dji.midware.data.model.P3.DataGimbalGetPushTutorialStatus.TutorialStatus;
import dji.midware.data.model.P3.DataGimbalSetTutorialStep;
import dji.midware.e.d;
import dji.phone.h.b;
import dji.phone.tutorial.c.a;
import dji.phone.tutorial.c.c;
import dji.pilot.fpv.R;
import dji.pilot.set.g;
import dji.publics.DJIUI.DJITextView;

public class DJILPToturialView extends RelativeLayout implements OnClickListener, c {
    static final int[] t = new int[]{R.string.longan_tutorial_welcome, R.string.longan_tutorial_unlock, R.string.lp_tutorial_upright, R.string.lp_tutorial_follow, R.string.lp_tutorial_follow_after, R.string.lp_tutorial_stick, R.string.lp_tutorial_stick_after, R.string.lp_tutorial_lock_direction, R.string.lp_tutorial_lock_direction_after, R.string.lp_tutorial_recenter, R.string.lp_tutorial_recenter_after, R.string.longan_tutorial_selfie, R.string.longan_tutorial_selfie_after, R.string.lp_tutorial_handle_push, R.string.lp_tutorial_handle_push_after, R.string.longan_tutorial_app_push, R.string.longan_tutorial_app_push_after, R.string.lp_tutorial_final};
    private int A = 0;
    private Animation B;
    private Animation C;
    private final int D = 1;
    private final int E = 3;
    private final int F = 2;
    private int G = 4000;
    private boolean H = false;
    TutorialStatus a = TutorialStatus.STEP_START;
    a b;
    CheckBox c;
    Button d;
    Button e;
    DJITextView f;
    ImageView g;
    Button h;
    RelativeLayout i;
    RelativeLayout j;
    RelativeLayout k;
    RelativeLayout l;
    RelativeLayout m;
    RelativeLayout n;
    RelativeLayout o;
    RelativeLayout p;
    RelativeLayout q;
    RelativeLayout r;
    RelativeLayout s;
    int u = getResources().getColor(R.color.longan_turorial_tip_bg_normal);
    int v = getResources().getColor(R.color.longan_turorial_tip_bg_complete);
    Handler w = new Handler(new Callback(this) {
        final /* synthetic */ DJILPToturialView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (this.a.H) {
                        if (DataGimbalGetPushTutorialStatus.getInstance().getCurStep().value() + 1 != TutorialStatus.STEP_SELFIE.value() && DataGimbalGetPushTutorialStatus.getInstance().getCurStep().value() + 1 != TutorialStatus.STEP_APP_CONTROL.value()) {
                            DataGimbalSetTutorialStep.getInstance().a(DataGimbalGetPushTutorialStatus.getInstance().getCurStep().value() + 1).start(new d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    Log.d("DJILPToturialView", "set step success" + this.a.a.a.value());
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    Log.d("DJILPToturialView", "set step failed" + aVar);
                                }
                            });
                            break;
                        }
                        Log.d("DJILPToturialView", "handleMessage: jump selfie");
                        DataGimbalSetTutorialStep.getInstance().a(DataGimbalGetPushTutorialStatus.getInstance().getCurStep().value() + 2).start(new d(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                Log.d("DJILPToturialView", "set step success" + this.a.a.a.value());
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                Log.d("DJILPToturialView", "set step failed" + aVar);
                            }
                        });
                        break;
                    }
                    break;
                case 2:
                case 3:
                    this.a.y = false;
                    dji.pilot.set.a.a(this.a.getContext(), g.v, true);
                    this.a.b.b();
                    break;
            }
            return false;
        }
    });
    private final String x = "DJILPToturialView";
    private boolean y;
    private b z = b.ROTATION_0;

    public DJILPToturialView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findView();
        DataGimbalSetTutorialStep.getInstance().a(1).start(null);
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        DataGimbalSetTutorialStep.getInstance().a(1).start(null);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DataGimbalSetTutorialStep.getInstance().a(0).start(null);
        dji.thirdparty.a.c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
        b();
    }

    public void onEventMainThread(b bVar) {
        this.z = bVar;
        Log.d("DJILPToturialView", "onEventMainThread: getOritation = " + bVar.a() + " getRotation = " + bVar.b());
        a();
        b();
    }

    private void a() {
        Log.d("DJILPToturialView", "resetView:");
        this.i = this.m;
        this.j = this.n;
        this.k = this.o;
        if (this.m.getVisibility() == 0) {
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.i.setVisibility(0);
            dji.phone.h.a.a(this.p, this.p.getRotation(), (float) this.z.b());
            this.j.setVisibility(4);
            this.k.setVisibility(4);
            this.d = (Button) this.i.findViewById(R.id.longan_toturial_continue_btn);
            this.e = (Button) this.i.findViewById(R.id.longan_toturial_cancle_btn);
        } else if (this.s.isShown()) {
            Log.d("DJILPToturialView", "resetView: mTutorialRemindPutPhonePage");
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.i.setVisibility(4);
            this.j.setVisibility(4);
            this.k.setVisibility(4);
            this.s.setVisibility(0);
            this.d = (Button) this.s.findViewById(R.id.lp_toturial_osmo_mobile_continue_btn);
            this.e = (Button) this.k.findViewById(R.id.longan_toturial_cancle_btn);
            this.f = (DJITextView) this.k.findViewById(R.id.longan_tutorial_decription_tv);
            this.l = (RelativeLayout) this.k.findViewById(R.id.longan_tutorial_step_ly);
            this.h = (Button) this.k.findViewById(R.id.lp_toturial_done_iv);
        } else if (this.n.isShown()) {
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.i.setVisibility(4);
            this.j.setVisibility(0);
            this.k.setVisibility(4);
            this.d = (Button) this.j.findViewById(R.id.longan_toturial_continue_btn);
            this.e = (Button) this.j.findViewById(R.id.longan_toturial_cancle_btn);
            this.f = (DJITextView) this.j.findViewById(R.id.longan_tutorial_decription_tv);
            this.g = (ImageView) this.j.findViewById(R.id.longan_tutorial_osmo_iv);
        } else if (this.o.isShown()) {
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.i.setVisibility(4);
            this.j.setVisibility(4);
            this.k.setVisibility(0);
            this.d = (Button) this.k.findViewById(R.id.longan_toturial_continue_btn);
            this.e = (Button) this.k.findViewById(R.id.longan_toturial_cancle_btn);
            this.f = (DJITextView) this.k.findViewById(R.id.longan_tutorial_decription_tv);
            this.l = (RelativeLayout) this.k.findViewById(R.id.longan_tutorial_step_ly);
            this.h = (Button) this.k.findViewById(R.id.lp_toturial_done_iv);
        }
        if (this.d != null) {
            this.d.setOnClickListener(this);
        }
        if (this.e != null) {
            this.e.setOnClickListener(this);
        }
        if (this.h != null) {
            this.h.setOnClickListener(this);
        }
    }

    public void findView() {
        if (!isInEditMode()) {
            this.m = (RelativeLayout) findViewById(R.id.lp_tutorial_page1_welcome_land);
            this.n = (RelativeLayout) findViewById(R.id.longan_tutorial_page2_3_land);
            this.o = (RelativeLayout) findViewById(R.id.lp_tutorial_page4_land);
            this.p = (RelativeLayout) findViewById(R.id.lp_tutorial_welcome);
            this.q = (RelativeLayout) findViewById(R.id.longan_tutorial_page2_3_port);
            this.r = (RelativeLayout) findViewById(R.id.longan_tutorial_page4_port);
            this.s = (RelativeLayout) findViewById(R.id.lp_tutorial_page2_3_land);
            this.c = (CheckBox) findViewById(R.id.lp_tutorial_not_reminded);
            this.c.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
                final /* synthetic */ DJILPToturialView a;

                {
                    this.a = r1;
                }

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    Log.d("DJILPToturialView", "onCheckedChanged: " + z);
                    dji.pilot.set.a.a(this.a.getContext(), g.v, z);
                }
            });
            this.m.setVisibility(0);
            a();
            this.B = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_top_in);
            this.C = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_top_out);
        }
    }

    private void a(int i) {
        DataGimbalSetTutorialStep.getInstance().a(i).start(new d(this) {
            final /* synthetic */ DJILPToturialView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                Log.d("DJILPToturialView", "set step success" + this.a.a.value());
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Log.d("DJILPToturialView", "set step failed" + aVar);
            }
        });
    }

    public void onPhonePutedGimbal() {
        Log.d("DJILPToturialView", "onPhonePutedGimbal: ");
        this.s.setVisibility(4);
        DataGimbalGetPushTutorialStatus instance = DataGimbalGetPushTutorialStatus.getInstance();
        Log.d("DJILPToturialView", "onPhonePutedGimbal: tustate = " + instance.getCurStep());
        if (instance.getCurStep() == TutorialStatus.STEP_START) {
            if (instance.getIsUnlock() == 0) {
                this.j.setVisibility(0);
                a();
                DataGimbalSetTutorialStep.getInstance().a(2).start(null);
            } else if (instance.getIsUpright() == 0) {
                this.j.setVisibility(0);
                a();
                this.f.setText(b(t[2]));
                this.g.setImageDrawable(getResources().getDrawable(R.drawable.longan_tutorial_uptight));
                DataGimbalSetTutorialStep.getInstance().a(3).start(null);
            } else {
                this.j.setVisibility(4);
                this.k.setVisibility(0);
                a();
                DataGimbalSetTutorialStep.getInstance().a(4).start(null);
            }
        } else if (instance.getCurStep() == TutorialStatus.STEP_UNLOCK_GIMBAL) {
            if (instance.getIsUpright() == 0) {
                this.w.sendEmptyMessage(1);
                return;
            }
            a(TutorialStatus.STEP_FOLLOW.value());
            this.j.setVisibility(4);
            this.k.setVisibility(0);
            a();
        } else if (instance.getCurStep() == TutorialStatus.STEP_HOLD_GIMBAL_UPRIGHT) {
            this.w.sendEmptyMessage(1);
            this.j.setVisibility(4);
            this.k.setVisibility(0);
            a();
        }
    }

    public void onReadyClicked() {
        Log.d("DJILPToturialView", "ready clicked");
        this.i.setVisibility(4);
        if (DataGimbalGetPushAbnormalStatus.getInstance().isPhoneOutGimbal()) {
            Log.d("DJILPToturialView", "onReadyClicked: isPhoneOutGimbal");
            this.y = false;
            this.s.setVisibility(0);
            a();
        } else if (this.A < 1) {
            postDelayed(new Runnable(this) {
                final /* synthetic */ DJILPToturialView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.onReadyClicked();
                }
            }, 500);
            this.A++;
        } else {
            this.y = true;
            DataGimbalGetPushTutorialStatus instance = DataGimbalGetPushTutorialStatus.getInstance();
            DJILogHelper.getInstance().LOGD("DJILPToturialView", "onReadyClicked: tustate = " + instance.getCurStep());
            if (instance.getCurStep() == TutorialStatus.STEP_START) {
                a();
                if (instance.getIsUnlock() == 0) {
                    this.j.setVisibility(0);
                    a();
                    DataGimbalSetTutorialStep.getInstance().a(2).start(null);
                } else if (instance.getIsUpright() == 0) {
                    this.j.setVisibility(0);
                    a();
                    this.f.setText(b(t[2]));
                    this.g.setImageDrawable(getResources().getDrawable(R.drawable.longan_tutorial_uptight));
                    DataGimbalSetTutorialStep.getInstance().a(3).start(null);
                } else {
                    this.j.setVisibility(4);
                    this.k.setVisibility(0);
                    a();
                    DataGimbalSetTutorialStep.getInstance().a(4).start(null);
                }
            } else if (instance.getCurStep() == TutorialStatus.STEP_UNLOCK_GIMBAL) {
                if (instance.getIsUpright() == 0) {
                    this.w.sendEmptyMessage(1);
                    return;
                }
                a(TutorialStatus.STEP_FOLLOW.value());
                this.j.setVisibility(4);
                this.k.setVisibility(0);
                a();
            } else if (instance.getCurStep() == TutorialStatus.STEP_HOLD_GIMBAL_UPRIGHT) {
                this.w.sendEmptyMessage(1);
                this.j.setVisibility(4);
                this.k.setVisibility(0);
                a();
            }
        }
    }

    public void onCancleClicked() {
        this.i.setVisibility(4);
        dji.thirdparty.a.c.a().e(c.b.STOP);
        this.b.b();
    }

    private String b(int i) {
        return getResources().getString(i);
    }

    public void onEventMainThread(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        boolean z;
        Log.d("DJILPToturialView", "onEventMainThread: gimbalStatus.isPhoneOutGimbal = " + dataGimbalGetPushAbnormalStatus.isPhoneOutGimbal());
        boolean z2 = this.y;
        if (dataGimbalGetPushAbnormalStatus.isPhoneOutGimbal()) {
            z = false;
        } else {
            z = true;
        }
        if (z2 != z) {
            if (dataGimbalGetPushAbnormalStatus.isPhoneOutGimbal()) {
                z = false;
            } else {
                z = true;
            }
            this.y = z;
            if (this.s.isShown()) {
                this.d = (Button) this.s.findViewById(R.id.lp_toturial_osmo_mobile_continue_btn);
                if (this.d == null) {
                    Log.d("DJILPToturialView", "onEventMainThread: mContinueBtn" + this.d);
                } else if (dataGimbalGetPushAbnormalStatus.isPhoneOutGimbal()) {
                    this.d.setEnabled(false);
                } else {
                    this.d.setEnabled(true);
                    a();
                }
            } else if (dataGimbalGetPushAbnormalStatus.isPhoneOutGimbal()) {
                switch (DataGimbalGetPushTutorialStatus.getInstance().getCurStep()) {
                    case STEP_FOLLOW:
                    case STEP_STICK:
                    case STEP_LOCK_DIRECTION:
                    case STEP_RECENTER:
                    case STEP_PUSH:
                        this.b.b();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void onEventMainThread(DataGimbalGetPushTutorialStatus dataGimbalGetPushTutorialStatus) {
        Log.d("DJILPToturialView", "tutorial push received " + dataGimbalGetPushTutorialStatus.getCurStep());
        switch (dataGimbalGetPushTutorialStatus.getCurStep()) {
            case STEP_FOLLOW:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsFollowFinish());
                if (dataGimbalGetPushTutorialStatus.getIsFollowFinish() == 0) {
                    this.H = false;
                    this.f.setText(t[3]);
                    this.l.setBackgroundColor(this.u);
                    this.e.setVisibility(0);
                    this.h.setVisibility(8);
                    return;
                }
                this.H = true;
                this.f.setText(t[4]);
                this.l.setBackgroundColor(this.v);
                this.w.sendEmptyMessageDelayed(1, (long) this.G);
                this.e.setVisibility(8);
                this.h.setVisibility(0);
                return;
            case STEP_STICK:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsStickFinish());
                if (dataGimbalGetPushTutorialStatus.getIsStickFinish() == 0) {
                    this.H = false;
                    this.f.setText(t[5]);
                    this.l.setBackgroundColor(this.u);
                    this.e.setVisibility(0);
                    this.h.setVisibility(8);
                    return;
                }
                this.H = true;
                this.f.setText(t[6]);
                this.l.setBackgroundColor(this.v);
                this.w.sendEmptyMessageDelayed(1, (long) this.G);
                this.e.setVisibility(8);
                this.h.setVisibility(0);
                return;
            case STEP_LOCK_DIRECTION:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsLockDirectionFinish());
                if (dataGimbalGetPushTutorialStatus.getIsLockDirectionFinish() == 0) {
                    this.H = false;
                    this.f.setText(t[7]);
                    this.l.setBackgroundColor(this.u);
                    this.e.setVisibility(0);
                    this.h.setVisibility(8);
                    return;
                }
                this.H = true;
                this.f.setText(t[8]);
                this.l.setBackgroundColor(this.v);
                this.w.sendEmptyMessageDelayed(1, (long) this.G);
                this.e.setVisibility(8);
                this.h.setVisibility(0);
                return;
            case STEP_RECENTER:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsRecentFinish());
                if (dataGimbalGetPushTutorialStatus.getIsRecentFinish() == 0) {
                    this.H = false;
                    this.f.setText(t[9]);
                    this.l.setBackgroundColor(this.u);
                    this.e.setVisibility(0);
                    this.h.setVisibility(8);
                    return;
                }
                this.H = true;
                this.f.setText(t[10]);
                this.l.setBackgroundColor(this.v);
                this.w.sendEmptyMessageDelayed(1, (long) this.G);
                this.e.setVisibility(8);
                this.h.setVisibility(0);
                return;
            case STEP_PUSH:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsHandlePushFinish());
                if (dataGimbalGetPushTutorialStatus.getIsHandlePushFinish() == 0) {
                    this.H = false;
                    this.f.setText(t[13]);
                    this.l.setBackgroundColor(this.u);
                    this.e.setVisibility(0);
                    this.h.setVisibility(8);
                    return;
                }
                this.H = true;
                this.f.setText(t[14]);
                this.l.setBackgroundColor(this.v);
                this.w.sendEmptyMessageDelayed(1, (long) this.G);
                this.e.setVisibility(8);
                this.h.setVisibility(0);
                return;
            case STEP_UNLOCK_GIMBAL:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsUnlock());
                if (dataGimbalGetPushTutorialStatus.getIsUnlock() == 0) {
                    this.H = false;
                    this.d.setEnabled(false);
                    return;
                }
                this.H = true;
                this.d.setEnabled(true);
                return;
            case STEP_HOLD_GIMBAL_UPRIGHT:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsUpright());
                if (dataGimbalGetPushTutorialStatus.getIsUpright() == 0) {
                    this.H = false;
                    this.f.setText(b(t[2]));
                    this.g.setImageDrawable(getResources().getDrawable(R.drawable.longan_tutorial_uptight));
                    this.d.setEnabled(false);
                    return;
                }
                this.H = true;
                this.d.setEnabled(true);
                return;
            case STEP_SELFIE:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsSelfieFinish());
                if (dataGimbalGetPushTutorialStatus.getIsSelfieFinish() == 0) {
                    this.H = false;
                    this.f.setText(t[11]);
                    this.l.setBackgroundColor(this.u);
                    this.e.setVisibility(0);
                    this.h.setVisibility(8);
                    return;
                }
                this.H = true;
                this.f.setText(t[12]);
                this.l.setBackgroundColor(this.v);
                this.w.sendEmptyMessageDelayed(1, (long) this.G);
                this.e.setVisibility(8);
                this.h.setVisibility(0);
                return;
            case STEP_APP_CONTROL:
                Log.d("DJILPToturialView", "" + dataGimbalGetPushTutorialStatus.getIsAppControlFinish());
                if (dataGimbalGetPushTutorialStatus.getIsAppControlFinish() == 0) {
                    this.H = false;
                    this.f.setText(t[15]);
                    this.l.setBackgroundColor(this.u);
                    this.e.setVisibility(0);
                    this.h.setVisibility(8);
                    return;
                }
                this.H = true;
                this.f.setText(t[16]);
                this.l.setBackgroundColor(this.v);
                this.w.sendEmptyMessageDelayed(1, (long) this.G);
                this.e.setVisibility(8);
                this.h.setVisibility(0);
                return;
            case STEP_FINISH:
                this.f.setText(t[17]);
                this.l.setBackgroundColor(this.v);
                this.e.setVisibility(4);
                this.h.setVisibility(4);
                this.w.sendEmptyMessageDelayed(2, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                return;
            default:
                return;
        }
    }

    private void b() {
        DataGimbalGetPushTutorialStatus instance = DataGimbalGetPushTutorialStatus.getInstance();
        if (instance.isGetted()) {
            switch (DataGimbalGetPushTutorialStatus.getInstance().getCurStep()) {
                case STEP_FOLLOW:
                    if (instance.getIsFollowFinish() == 0) {
                        this.f.setText(t[3]);
                        this.l.setBackgroundColor(this.u);
                        this.e.setVisibility(0);
                        this.h.setVisibility(8);
                        return;
                    }
                    this.f.setText(t[4]);
                    this.l.setBackgroundColor(this.v);
                    this.e.setVisibility(8);
                    this.h.setVisibility(0);
                    return;
                case STEP_STICK:
                    if (instance.getIsStickFinish() == 0) {
                        this.f.setText(t[5]);
                        this.l.setBackgroundColor(this.u);
                        this.e.setVisibility(0);
                        this.h.setVisibility(8);
                        return;
                    }
                    this.f.setText(t[6]);
                    this.l.setBackgroundColor(this.v);
                    this.e.setVisibility(8);
                    this.h.setVisibility(0);
                    return;
                case STEP_LOCK_DIRECTION:
                    if (instance.getIsLockDirectionFinish() == 0) {
                        this.f.setText(t[7]);
                        this.l.setBackgroundColor(this.u);
                        this.e.setVisibility(0);
                        this.h.setVisibility(8);
                        return;
                    }
                    this.f.setText(t[8]);
                    this.l.setBackgroundColor(this.v);
                    this.e.setVisibility(8);
                    this.h.setVisibility(0);
                    return;
                case STEP_RECENTER:
                    if (instance.getIsRecentFinish() == 0) {
                        this.f.setText(t[9]);
                        this.l.setBackgroundColor(this.u);
                        this.e.setVisibility(0);
                        this.h.setVisibility(8);
                        return;
                    }
                    this.f.setText(t[10]);
                    this.l.setBackgroundColor(this.v);
                    this.e.setVisibility(8);
                    this.h.setVisibility(0);
                    return;
                case STEP_PUSH:
                    if (instance.getIsHandlePushFinish() == 0) {
                        this.f.setText(t[13]);
                        this.l.setBackgroundColor(this.u);
                        this.e.setVisibility(0);
                        this.h.setVisibility(8);
                        return;
                    }
                    this.f.setText(t[14]);
                    this.l.setBackgroundColor(this.v);
                    this.e.setVisibility(8);
                    this.h.setVisibility(0);
                    return;
                case STEP_UNLOCK_GIMBAL:
                    if (instance.getIsUnlock() == 0) {
                        this.d.setEnabled(false);
                        return;
                    } else {
                        this.d.setEnabled(true);
                        return;
                    }
                case STEP_HOLD_GIMBAL_UPRIGHT:
                    this.f.setText(b(t[2]));
                    this.g.setImageDrawable(getResources().getDrawable(R.drawable.longan_tutorial_uptight));
                    if (instance.getIsUpright() == 0) {
                        this.d.setEnabled(false);
                        return;
                    } else {
                        this.d.setEnabled(true);
                        return;
                    }
                case STEP_SELFIE:
                    if (instance.getIsSelfieFinish() == 0) {
                        this.f.setText(t[11]);
                        this.l.setBackgroundColor(this.u);
                        this.e.setVisibility(0);
                        this.h.setVisibility(8);
                        return;
                    }
                    this.f.setText(t[12]);
                    this.l.setBackgroundColor(this.v);
                    this.e.setVisibility(8);
                    this.h.setVisibility(0);
                    return;
                case STEP_APP_CONTROL:
                    if (instance.getIsAppControlFinish() == 0) {
                        this.f.setText(t[15]);
                        this.l.setBackgroundColor(this.u);
                        this.e.setVisibility(0);
                        this.h.setVisibility(8);
                        return;
                    }
                    this.f.setText(t[16]);
                    this.l.setBackgroundColor(this.v);
                    this.e.setVisibility(8);
                    this.h.setVisibility(0);
                    return;
                case STEP_FINISH:
                    this.f.setText(t[17]);
                    this.l.setBackgroundColor(this.v);
                    this.e.setVisibility(4);
                    this.h.setVisibility(4);
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_toturial_continue_btn) {
            onReadyClicked();
        } else if (id == R.id.longan_toturial_cancle_btn) {
            onCancleClicked();
        } else if (id == R.id.lp_toturial_osmo_mobile_continue_btn) {
            Log.d("DJILPToturialView", "onClick: onPhonePutedGimbal");
            this.y = true;
            onPhonePutedGimbal();
        } else if (id == R.id.lp_toturial_done_iv) {
            if (this.w.hasMessages(1)) {
                this.w.removeMessages(1);
            }
            this.w.sendEmptyMessage(1);
        }
    }

    public void showWelcomPage() {
        Log.d("DJILPToturialView", "showWelcomPage: ");
        DataGimbalSetTutorialStep.getInstance().a(1).start(new d(this) {
            final /* synthetic */ DJILPToturialView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                Log.d("DJILPToturialView", "onSuccess: model = " + obj);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Log.d("DJILPToturialView", "onFailure: ccode = " + aVar);
            }
        });
        this.b.a(c.b.START);
        this.m.setVisibility(0);
        a();
    }

    public void hideWelcomPage() {
        this.m.setVisibility(4);
    }

    public void setPresenter(a aVar) {
        this.b = aVar;
    }
}
