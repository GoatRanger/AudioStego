package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.flightmode.c$d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.g$d;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIExitCurrentMessionView extends DJILinearLayout implements a {
    private boolean a = false;
    private Handler b = new Handler();
    private OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ DJIExitCurrentMessionView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ajc:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    return;
                case R.id.ajd:
                    if (this.a.a) {
                        c.getInstance().g();
                        dji.pilot.groundStation.a.a.getInstance(null).a(this.a);
                        dji.thirdparty.a.c.a().e(g$d.EXIT_VISUAL);
                        this.a.a = false;
                        return;
                    }
                    this.a.findViewById(R.id.ajd).setEnabled(false);
                    switch (dji.pilot.groundStation.a.a.getInstance(null).z()) {
                        case HOME_LOCK:
                        case COURSE_LOCK:
                            dji.pilot.groundStation.a.a.getInstance(null).c(new d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    if (this.a.a.b != null) {
                                        this.a.a.b.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                dji.pilot.groundStation.a.a.getInstance(null).a(dji.pilot.groundStation.a.a.d.NONE);
                                                dji.pilot.groundStation.a.a.getInstance(null).a(this.a.a.a);
                                                this.a.a.a.a();
                                            }
                                        });
                                    }
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    this.a.a.b();
                                }
                            });
                            return;
                        case FOLLOW_ME:
                            dji.pilot.groundStation.a.a.getInstance(null).d(new d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    if (this.a.a.b != null) {
                                        this.a.a.b.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass2 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                dji.pilot.groundStation.a.a.getInstance(null).a(dji.pilot.groundStation.a.a.d.NONE);
                                                dji.pilot.groundStation.a.a.getInstance(null).a(this.a.a.a);
                                                this.a.a.a.a();
                                            }
                                        });
                                    }
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    this.a.a.b();
                                }
                            });
                            return;
                        case POI_AUTO:
                            dji.pilot.groundStation.a.a.getInstance(null).f(new d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    if (this.a.a.b != null) {
                                        this.a.a.b.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass3 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                dji.pilot.groundStation.a.a.getInstance(null).a(dji.pilot.groundStation.a.a.d.NONE);
                                                dji.pilot.groundStation.a.a.getInstance(null).i().l().b(null, 0.0d);
                                                dji.pilot.groundStation.a.a.getInstance(null).a(this.a.a.a);
                                                this.a.a.a.a();
                                            }
                                        });
                                    }
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    this.a.a.b();
                                }
                            });
                            return;
                        case WP_AUTO:
                            dji.pilot.groundStation.a.a.getInstance(null).g(new d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    if (this.a.a.b != null) {
                                        this.a.a.b.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass4 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                dji.pilot.groundStation.a.a.getInstance(null).a(dji.pilot.groundStation.a.a.d.NONE);
                                                dji.pilot.groundStation.a.a.getInstance(null).i().l().g();
                                                dji.pilot.groundStation.a.a.getInstance(null).a(this.a.a.a);
                                                this.a.a.a.a();
                                            }
                                        });
                                    }
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    this.a.a.b();
                                }
                            });
                            return;
                        case TERRAIN_TRACKING:
                            dji.pilot.groundStation.a.a.getInstance(null).a(new d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    if (this.a.a.b != null) {
                                        this.a.a.b.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass5 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                dji.pilot.groundStation.a.a.getInstance(null).a(dji.pilot.groundStation.a.a.d.NONE);
                                                dji.pilot.groundStation.a.a.getInstance(null).i().l().g();
                                                dji.pilot.groundStation.a.a.getInstance(null).a(this.a.a.a);
                                                this.a.a.a.a();
                                            }
                                        });
                                    }
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                }
                            });
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    };

    public DJIExitCurrentMessionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.removeCallbacksAndMessages(null);
        this.b = null;
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.a = false;
    }

    public void dispatchOnResume() {
        findViewById(R.id.ajd).setEnabled(true);
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    public void setFromVisual() {
        this.a = true;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.ajc).setOnClickListener(this.c);
            findViewById(R.id.ajd).setOnClickListener(this.c);
        }
    }

    private void a() {
        dji.pilot.groundStation.a.a.getInstance(getContext()).n();
        dji.pilot.fpv.flightmode.c.getInstance().a(c$d.NONE);
        if (dji.pilot.publics.e.a.c(null)) {
            dji.pilot.fpv.flightmode.c.getInstance().a(c$b.NORMAL);
        }
    }

    private void b() {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJIExitCurrentMessionView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.findViewById(R.id.ajd).setEnabled(true);
                ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_main_exit_help_view, 24, false);
            }
        });
    }
}
