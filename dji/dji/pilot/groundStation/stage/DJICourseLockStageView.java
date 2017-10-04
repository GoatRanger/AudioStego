package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycStartIoc;
import dji.midware.data.model.P3.DataFlycStartIoc.IOCType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.view.DJIGSCourseLockView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJICourseLockStageView extends DJIRelativeLayout implements c$i, a {
    private DJIGSCourseLockView n = null;
    private View o = null;
    private OnClickListener p = new OnClickListener(this) {
        final /* synthetic */ DJICourseLockStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aj2:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    dji.pilot.groundStation.a.a.getInstance(null).a(this.a);
                    this.a.r = true;
                    return;
                case R.id.aj3:
                    final dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
                    this.a.o.setEnabled(false);
                    instance.a(IOCType.IOCTypeCourseLock, new d(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void onSuccess(Object obj) {
                            int result = DataFlycStartIoc.getInstance().getResult();
                            if (result == 0) {
                                DataFlycFunctionControl instance = DataFlycFunctionControl.getInstance();
                                instance.setCommand(FLYC_COMMAND.HOMEPOINT_LOC);
                                instance.start(new d(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onSuccess(Object obj) {
                                        this.a.b.a.q.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                e.c(c$i.f);
                                                this.a.a.b.a.r = true;
                                                instance.a(dji.pilot.groundStation.a.a.d.COURSE_LOCK);
                                                instance.a(((float) DataOsdGetPushCommon.getInstance().getYaw()) / 10.0f);
                                                ((DJIStageView) this.a.a.b.a.getParent()).createStageView(R.layout.gs_course_lock_status_view, 12, true);
                                            }
                                        });
                                    }

                                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                                        this.a.b.a.a();
                                        instance.a((int) R.string.gs_course_lock_send_command_failed, aVar, true);
                                    }
                                });
                                return;
                            }
                            this.b.a.a();
                            instance.a((int) R.string.gs_course_lock_send_command_failed, dji.pilot.groundStation.a.a(this.b.a.getContext(), result), false);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.a();
                            instance.a((int) R.string.gs_course_lock_send_command_failed, aVar, false);
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler q = new Handler();
    private boolean r = false;
    private final Runnable s = new Runnable(this) {
        final /* synthetic */ DJICourseLockStageView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.r) {
                DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                float yaw = (((float) instance.getYaw()) / 10.0f) - this.a.n.getPhoneAzimuth();
                if (yaw < 0.0f) {
                    yaw += 360.0f;
                } else if (yaw > 360.0f) {
                    yaw -= 360.0f;
                }
                this.a.n.updateRotate(yaw);
                this.a.q.postDelayed(this.a.s, 200);
            }
        }
    };

    public DJICourseLockStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.r = true;
    }

    public void dispatchOnResume() {
        this.r = false;
        this.q.post(this.s);
        this.o.setEnabled(true);
    }

    public void dispatchOnPause() {
        this.r = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.aj2).setOnClickListener(this.p);
            this.n = (DJIGSCourseLockView) findViewById(R.id.aj5);
            this.o = findViewById(R.id.aj3);
            this.o.setOnClickListener(this.p);
        }
    }

    private void a() {
        this.q.post(new Runnable(this) {
            final /* synthetic */ DJICourseLockStageView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.o.setEnabled(true);
            }
        });
    }
}
