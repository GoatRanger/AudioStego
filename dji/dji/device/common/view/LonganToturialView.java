package dji.device.common.view;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.here.odnp.config.OdnpConfigStatic;
import dji.device.common.DJIUIEventManagerLongan.e;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataGimbalGetPushTutorialStatus;
import dji.midware.data.model.P3.DataGimbalGetPushTutorialStatus.TutorialStatus;
import dji.midware.data.model.P3.DataGimbalSetTutorialStep;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.pilot.set.g;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class LonganToturialView extends RelativeLayout implements OnClickListener {
    static final int[] q = new int[]{R.string.longan_tutorial_welcome, R.string.longan_tutorial_unlock, R.string.longan_tutorial_upright, R.string.longan_tutorial_follow, R.string.longan_tutorial_follow_after, R.string.longan_tutorial_stick, R.string.longan_tutorial_stick_after, R.string.longan_tutorial_lock_direction, R.string.longan_tutorial_lock_direction_after, R.string.longan_tutorial_recenter, R.string.longan_tutorial_recenter_after, R.string.longan_tutorial_selfie, R.string.longan_tutorial_selfie_after, R.string.longan_tutorial_handle_push, R.string.longan_tutorial_handle_push_after, R.string.longan_tutorial_app_push, R.string.longan_tutorial_app_push_after, R.string.longan_tutorial_final};
    private int A = 5000;
    private boolean B = false;
    TutorialStatus a = TutorialStatus.STEP_START;
    Button b;
    Button c;
    DJITextView d;
    ImageView e;
    ImageView f;
    RelativeLayout g;
    RelativeLayout h;
    RelativeLayout i;
    RelativeLayout j;
    RelativeLayout k;
    RelativeLayout l;
    RelativeLayout m;
    RelativeLayout n;
    RelativeLayout o;
    RelativeLayout p;
    int r = getResources().getColor(R.color.longan_turorial_tip_bg_normal);
    int s = getResources().getColor(R.color.longan_turorial_tip_bg_complete);
    Handler t = new Handler(new Callback(this) {
        final /* synthetic */ LonganToturialView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (this.a.B) {
                        DataGimbalSetTutorialStep.getInstance().a(DataGimbalGetPushTutorialStatus.getInstance().getCurStep().value() + 1).start(new d(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                Log.d("LonganToturialView", "set step success" + this.a.a.a.value());
                            }

                            public void onFailure(a aVar) {
                                Log.d("LonganToturialView", "set step failed" + aVar);
                            }
                        });
                        break;
                    }
                    break;
                case 2:
                case 3:
                    c.a().e(e.TUTORIAL_FINISHED);
                    break;
            }
            return false;
        }
    });
    private final String u = "LonganToturialView";
    private Animation v;
    private Animation w;
    private final int x = 1;
    private final int y = 3;
    private final int z = 2;

    public LonganToturialView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findView();
        c.a().a(this);
        DataGimbalSetTutorialStep.getInstance().a(1).start(null);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DataGimbalSetTutorialStep.getInstance().a(0).start(null);
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
        b();
    }

    private void a() {
        if (getResources().getConfiguration().orientation == 2) {
            this.g = this.k;
            this.h = this.l;
            this.i = this.m;
        } else {
            this.g = this.n;
            this.h = this.o;
            this.i = this.p;
        }
        Log.d("LonganToturialView", "visible:" + this.n.getVisibility());
        if (this.k.getVisibility() == 0 || this.n.getVisibility() == 0) {
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.p.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.g.setVisibility(0);
            this.h.setVisibility(4);
            this.i.setVisibility(4);
            this.b = (Button) this.g.findViewById(R.id.longan_toturial_continue_btn);
            this.c = (Button) this.g.findViewById(R.id.longan_toturial_cancle_btn);
        } else if (this.l.isShown() || this.o.isShown()) {
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.p.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.g.setVisibility(4);
            this.h.setVisibility(0);
            this.i.setVisibility(4);
            this.b = (Button) this.h.findViewById(R.id.longan_toturial_continue_btn);
            this.c = (Button) this.h.findViewById(R.id.longan_toturial_cancle_btn);
            this.d = (DJITextView) this.h.findViewById(R.id.longan_tutorial_decription_tv);
            this.e = (ImageView) this.h.findViewById(R.id.longan_tutorial_osmo_iv);
        } else if (this.m.isShown() || this.p.isShown()) {
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.p.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.g.setVisibility(4);
            this.h.setVisibility(4);
            this.i.setVisibility(0);
            this.b = (Button) this.i.findViewById(R.id.longan_toturial_continue_btn);
            this.c = (Button) this.i.findViewById(R.id.longan_toturial_cancle_btn);
            this.d = (DJITextView) this.i.findViewById(R.id.longan_tutorial_decription_tv);
            this.j = (RelativeLayout) this.i.findViewById(R.id.longan_tutorial_step_ly);
            this.f = (ImageView) this.i.findViewById(R.id.longan_toturial_done_iv);
        }
        if (this.b != null) {
            this.b.setOnClickListener(this);
        }
        if (this.c != null) {
            this.c.setOnClickListener(this);
        }
    }

    public void findView() {
        if (!isInEditMode()) {
            this.k = (RelativeLayout) findViewById(R.id.longan_tutorial_page1_welcome_land);
            this.l = (RelativeLayout) findViewById(R.id.longan_tutorial_page2_3_land);
            this.m = (RelativeLayout) findViewById(R.id.longan_tutorial_page4_land);
            this.n = (RelativeLayout) findViewById(R.id.longan_tutorial_page1_welcome_port);
            this.o = (RelativeLayout) findViewById(R.id.longan_tutorial_page2_3_port);
            this.p = (RelativeLayout) findViewById(R.id.longan_tutorial_page4_port);
            if (getResources().getConfiguration().orientation == 2) {
                this.k.setVisibility(0);
            } else {
                this.n.setVisibility(0);
            }
            a();
            this.v = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_top_in);
            this.w = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_top_out);
        }
    }

    private void a(int i) {
        DataGimbalSetTutorialStep.getInstance().a(i).start(new d(this) {
            final /* synthetic */ LonganToturialView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                Log.d("LonganToturialView", "set step success" + this.a.a.value());
            }

            public void onFailure(a aVar) {
                Log.d("LonganToturialView", "set step failed" + aVar);
            }
        });
    }

    public void onReadyClicked() {
        Log.d("LonganToturialView", "ready clicked");
        this.g.setVisibility(4);
        DataGimbalGetPushTutorialStatus instance = DataGimbalGetPushTutorialStatus.getInstance();
        if (instance.getCurStep() == TutorialStatus.STEP_START) {
            if (instance.getIsUnlock() == 0) {
                this.h.setVisibility(0);
                a();
                DataGimbalSetTutorialStep.getInstance().a(2).start(null);
            } else if (instance.getIsUpright() == 0) {
                this.h.setVisibility(0);
                a();
                this.d.setText(b(q[2]));
                this.e.setImageDrawable(getResources().getDrawable(R.drawable.longan_tutorial_uptight));
                DataGimbalSetTutorialStep.getInstance().a(3).start(null);
            } else {
                this.h.setVisibility(4);
                this.i.setVisibility(0);
                a();
                DataGimbalSetTutorialStep.getInstance().a(4).start(null);
            }
        } else if (instance.getCurStep() == TutorialStatus.STEP_UNLOCK_GIMBAL) {
            if (instance.getIsUpright() == 0) {
                this.t.sendEmptyMessage(1);
                return;
            }
            a(TutorialStatus.STEP_FOLLOW.value());
            this.h.setVisibility(4);
            this.i.setVisibility(0);
            a();
        } else if (instance.getCurStep() == TutorialStatus.STEP_HOLD_GIMBAL_UPRIGHT) {
            this.t.sendEmptyMessage(1);
            this.h.setVisibility(4);
            this.i.setVisibility(0);
            a();
        }
    }

    public void onCancleClicked() {
        this.t.sendEmptyMessage(2);
        if (this.i.isShown()) {
            dji.pilot.set.a.a(getContext(), g.t, true);
        }
    }

    private String b(int i) {
        return getResources().getString(i);
    }

    public void onEventMainThread(DataGimbalGetPushTutorialStatus dataGimbalGetPushTutorialStatus) {
        Log.d("LonganToturialView", "tutorial push received" + dataGimbalGetPushTutorialStatus.getCurStep());
        switch (dataGimbalGetPushTutorialStatus.getCurStep()) {
            case STEP_UNLOCK_GIMBAL:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsUnlock());
                if (dataGimbalGetPushTutorialStatus.getIsUnlock() == 0) {
                    this.B = false;
                    this.b.setEnabled(false);
                    return;
                }
                this.B = true;
                this.b.setEnabled(true);
                return;
            case STEP_HOLD_GIMBAL_UPRIGHT:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsUpright());
                if (dataGimbalGetPushTutorialStatus.getIsUpright() == 0) {
                    this.B = false;
                    this.d.setText(b(q[2]));
                    this.e.setImageDrawable(getResources().getDrawable(R.drawable.longan_tutorial_uptight));
                    this.b.setEnabled(false);
                    return;
                }
                this.B = true;
                this.b.setEnabled(true);
                return;
            case STEP_FOLLOW:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsFollowFinish());
                if (dataGimbalGetPushTutorialStatus.getIsFollowFinish() == 0) {
                    this.B = false;
                    this.d.setText(q[3]);
                    this.j.setBackgroundColor(this.r);
                    this.c.setVisibility(0);
                    this.f.setVisibility(8);
                    return;
                }
                this.B = true;
                this.d.setText(q[4]);
                this.j.setBackgroundColor(this.s);
                this.t.sendEmptyMessageDelayed(1, (long) this.A);
                this.c.setVisibility(8);
                this.f.setVisibility(0);
                return;
            case STEP_STICK:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsStickFinish());
                if (dataGimbalGetPushTutorialStatus.getIsStickFinish() == 0) {
                    this.B = false;
                    this.d.setText(q[5]);
                    this.j.setBackgroundColor(this.r);
                    this.c.setVisibility(0);
                    this.f.setVisibility(8);
                    return;
                }
                this.B = true;
                this.d.setText(q[6]);
                this.j.setBackgroundColor(this.s);
                this.t.sendEmptyMessageDelayed(1, (long) this.A);
                this.c.setVisibility(8);
                this.f.setVisibility(0);
                return;
            case STEP_LOCK_DIRECTION:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsLockDirectionFinish());
                if (dataGimbalGetPushTutorialStatus.getIsLockDirectionFinish() == 0) {
                    this.B = false;
                    this.d.setText(q[7]);
                    this.j.setBackgroundColor(this.r);
                    this.c.setVisibility(0);
                    this.f.setVisibility(8);
                    return;
                }
                this.B = true;
                this.d.setText(q[8]);
                this.j.setBackgroundColor(this.s);
                this.t.sendEmptyMessageDelayed(1, (long) this.A);
                this.c.setVisibility(8);
                this.f.setVisibility(0);
                return;
            case STEP_RECENTER:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsRecentFinish());
                if (dataGimbalGetPushTutorialStatus.getIsRecentFinish() == 0) {
                    this.B = false;
                    this.d.setText(q[9]);
                    this.j.setBackgroundColor(this.r);
                    this.c.setVisibility(0);
                    this.f.setVisibility(8);
                    return;
                }
                this.B = true;
                this.d.setText(q[10]);
                this.j.setBackgroundColor(this.s);
                this.t.sendEmptyMessageDelayed(1, (long) this.A);
                this.c.setVisibility(8);
                this.f.setVisibility(0);
                return;
            case STEP_SELFIE:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsSelfieFinish());
                if (dataGimbalGetPushTutorialStatus.getIsSelfieFinish() == 0) {
                    this.B = false;
                    this.d.setText(q[11]);
                    this.j.setBackgroundColor(this.r);
                    this.c.setVisibility(0);
                    this.f.setVisibility(8);
                    return;
                }
                this.B = true;
                this.d.setText(q[12]);
                this.j.setBackgroundColor(this.s);
                this.t.sendEmptyMessageDelayed(1, (long) this.A);
                this.c.setVisibility(8);
                this.f.setVisibility(0);
                return;
            case STEP_PUSH:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsHandlePushFinish());
                if (dataGimbalGetPushTutorialStatus.getIsHandlePushFinish() == 0) {
                    this.B = false;
                    this.d.setText(q[13]);
                    this.j.setBackgroundColor(this.r);
                    this.c.setVisibility(0);
                    this.f.setVisibility(8);
                    return;
                }
                this.B = true;
                this.d.setText(q[14]);
                this.j.setBackgroundColor(this.s);
                this.t.sendEmptyMessageDelayed(1, (long) this.A);
                this.c.setVisibility(8);
                this.f.setVisibility(0);
                return;
            case STEP_APP_CONTROL:
                Log.d("LonganToturialView", "" + dataGimbalGetPushTutorialStatus.getIsAppControlFinish());
                if (dataGimbalGetPushTutorialStatus.getIsAppControlFinish() == 0) {
                    this.B = false;
                    this.d.setText(q[15]);
                    this.j.setBackgroundColor(this.r);
                    this.c.setVisibility(0);
                    this.f.setVisibility(8);
                    return;
                }
                this.B = true;
                this.d.setText(q[16]);
                this.j.setBackgroundColor(this.s);
                this.t.sendEmptyMessageDelayed(1, (long) this.A);
                this.c.setVisibility(8);
                this.f.setVisibility(0);
                return;
            case STEP_FINISH:
                this.d.setText(q[17]);
                this.j.setBackgroundColor(this.s);
                this.c.setVisibility(4);
                this.f.setVisibility(4);
                dji.pilot.set.a.a(getContext(), g.t, true);
                this.t.sendEmptyMessageDelayed(2, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                return;
            default:
                return;
        }
    }

    private void b() {
        DataGimbalGetPushTutorialStatus instance = DataGimbalGetPushTutorialStatus.getInstance();
        if (instance.isGetted()) {
            switch (DataGimbalGetPushTutorialStatus.getInstance().getCurStep()) {
                case STEP_UNLOCK_GIMBAL:
                    if (instance.getIsUnlock() == 0) {
                        this.b.setEnabled(false);
                        return;
                    } else {
                        this.b.setEnabled(true);
                        return;
                    }
                case STEP_HOLD_GIMBAL_UPRIGHT:
                    this.d.setText(b(q[2]));
                    this.e.setImageDrawable(getResources().getDrawable(R.drawable.longan_tutorial_uptight));
                    if (instance.getIsUpright() == 0) {
                        this.b.setEnabled(false);
                        return;
                    } else {
                        this.b.setEnabled(true);
                        return;
                    }
                case STEP_FOLLOW:
                    if (instance.getIsFollowFinish() == 0) {
                        this.d.setText(q[3]);
                        this.j.setBackgroundColor(this.r);
                        this.c.setVisibility(0);
                        this.f.setVisibility(8);
                        return;
                    }
                    this.d.setText(q[4]);
                    this.j.setBackgroundColor(this.s);
                    this.c.setVisibility(8);
                    this.f.setVisibility(0);
                    return;
                case STEP_STICK:
                    if (instance.getIsStickFinish() == 0) {
                        this.d.setText(q[5]);
                        this.j.setBackgroundColor(this.r);
                        this.c.setVisibility(0);
                        this.f.setVisibility(8);
                        return;
                    }
                    this.d.setText(q[6]);
                    this.j.setBackgroundColor(this.s);
                    this.c.setVisibility(8);
                    this.f.setVisibility(0);
                    return;
                case STEP_LOCK_DIRECTION:
                    if (instance.getIsLockDirectionFinish() == 0) {
                        this.d.setText(q[7]);
                        this.j.setBackgroundColor(this.r);
                        this.c.setVisibility(0);
                        this.f.setVisibility(8);
                        return;
                    }
                    this.d.setText(q[8]);
                    this.j.setBackgroundColor(this.s);
                    this.c.setVisibility(8);
                    this.f.setVisibility(0);
                    return;
                case STEP_RECENTER:
                    if (instance.getIsRecentFinish() == 0) {
                        this.d.setText(q[9]);
                        this.j.setBackgroundColor(this.r);
                        this.c.setVisibility(0);
                        this.f.setVisibility(8);
                        return;
                    }
                    this.d.setText(q[10]);
                    this.j.setBackgroundColor(this.s);
                    this.c.setVisibility(8);
                    this.f.setVisibility(0);
                    return;
                case STEP_SELFIE:
                    if (instance.getIsSelfieFinish() == 0) {
                        this.d.setText(q[11]);
                        this.j.setBackgroundColor(this.r);
                        this.c.setVisibility(0);
                        this.f.setVisibility(8);
                        return;
                    }
                    this.d.setText(q[12]);
                    this.j.setBackgroundColor(this.s);
                    this.c.setVisibility(8);
                    this.f.setVisibility(0);
                    return;
                case STEP_PUSH:
                    if (instance.getIsHandlePushFinish() == 0) {
                        this.d.setText(q[13]);
                        this.j.setBackgroundColor(this.r);
                        this.c.setVisibility(0);
                        this.f.setVisibility(8);
                        return;
                    }
                    this.d.setText(q[14]);
                    this.j.setBackgroundColor(this.s);
                    this.c.setVisibility(8);
                    this.f.setVisibility(0);
                    return;
                case STEP_APP_CONTROL:
                    if (instance.getIsAppControlFinish() == 0) {
                        this.d.setText(q[15]);
                        this.j.setBackgroundColor(this.r);
                        this.c.setVisibility(0);
                        this.f.setVisibility(8);
                        return;
                    }
                    this.d.setText(q[16]);
                    this.j.setBackgroundColor(this.s);
                    this.c.setVisibility(8);
                    this.f.setVisibility(0);
                    return;
                case STEP_FINISH:
                    this.d.setText(q[17]);
                    this.j.setBackgroundColor(this.s);
                    this.c.setVisibility(4);
                    this.f.setVisibility(4);
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
        }
    }
}
