package dji.pilot.fpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.dji.frame.c.l;
import com.here.odnp.config.OdnpConfigStatic;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycRedundancyStatus;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.COLOR_STATUS;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.IMUStatus;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.RS_CMD_TYPE;
import dji.midware.data.model.P3.DataFlycSetAndGetRedundancyIMUIndex;
import dji.midware.data.model.P3.DataFlycSetAndGetRedundancyIMUIndex.CMD_ACTION;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIRedundancySysController;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIChecklistRedundancyView extends DJILinearLayout implements a {
    private DJITextView[] a;
    private DJITextView[] b;
    private DJIImageView[] c;
    private DataFlycRedundancyStatus d;
    private DJITextView[] e;
    private DJITextView[] f;
    private boolean g;
    private boolean h;
    private View i;
    private TextView j;
    private OnClickListener k;

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[COLOR_STATUS.values().length];

        static {
            try {
                a[COLOR_STATUS.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[COLOR_STATUS.e.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[COLOR_STATUS.d.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[COLOR_STATUS.g.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[COLOR_STATUS.c.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[COLOR_STATUS.f.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public DJIChecklistRedundancyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new DJITextView[3];
        this.b = new DJITextView[3];
        this.c = new DJIImageView[3];
        this.d = null;
        this.e = new DJITextView[3];
        this.f = new DJITextView[3];
        this.g = true;
        this.h = false;
        this.k = new OnClickListener(this) {
            final /* synthetic */ DJIChecklistRedundancyView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.wf:
                        this.a.a(0);
                        return;
                    case R.id.wm:
                        this.a.a(1);
                        return;
                    case R.id.wt:
                        this.a.a(2);
                        return;
                    case R.id.wu:
                        ((DJIStageView) this.a.getParent()).createStageView(R.layout.fpv_checklist_redundancy_history_view, R.string.fpv_redundancy_history_title, true);
                        return;
                    default:
                        return;
                }
            }
        };
        this.d = DataFlycRedundancyStatus.getInstance();
    }

    public void dispatchOnStart() {
        c.a().a(this);
        this.d.a(RS_CMD_TYPE.a).start(new d(this) {
            final /* synthetic */ DJIChecklistRedundancyView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                if (this.a.d.a() != RS_CMD_TYPE.a) {
                    return;
                }
                if (this.a.d.b().a()) {
                    this.a.g = true;
                    return;
                }
                this.a.g = false;
                this.a.c[0].post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b[0].setText(R.string.fpv_redundancy_version_not_match);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void dispatchOnStop() {
        this.h = false;
        c.a().d(this);
    }

    public void dispatchOnResume() {
        this.h = true;
        a(-1);
        onEventMainThread(DJIRedundancySysController.getInstance(getContext()).a());
        onEventMainThread(DataFlycRedundancyStatus.getInstance());
    }

    public void dispatchOnPause() {
        this.h = false;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.b[0] = (DJITextView) findViewById(R.id.wc);
            this.b[1] = (DJITextView) findViewById(R.id.wj);
            this.b[2] = (DJITextView) findViewById(R.id.wq);
            this.c[0] = (DJIImageView) findViewById(R.id.wa);
            this.c[1] = (DJIImageView) findViewById(R.id.wh);
            this.c[2] = (DJIImageView) findViewById(R.id.wo);
            this.a[0] = (DJITextView) findViewById(R.id.wf);
            this.a[1] = (DJITextView) findViewById(R.id.wm);
            this.a[2] = (DJITextView) findViewById(R.id.wt);
            findViewById(R.id.wu).setOnClickListener(this.k);
            this.a[0].setOnClickListener(this.k);
            this.a[1].setOnClickListener(this.k);
            this.a[2].setOnClickListener(this.k);
            this.e[0] = (DJITextView) findViewById(R.id.wd);
            this.e[1] = (DJITextView) findViewById(R.id.wk);
            this.e[2] = (DJITextView) findViewById(R.id.wr);
            this.f[0] = (DJITextView) findViewById(R.id.we);
            this.f[1] = (DJITextView) findViewById(R.id.wl);
            this.f[2] = (DJITextView) findViewById(R.id.ws);
            this.i = findViewById(R.id.wn);
            this.j = (TextView) findViewById(R.id.wi);
        }
    }

    private void a(final int i) {
        DataFlycSetAndGetRedundancyIMUIndex.getInstance().setCmdAction(i == -1 ? CMD_ACTION.GET : CMD_ACTION.SET).setDeviceIndex(i).start(new d(this) {
            final /* synthetic */ DJIChecklistRedundancyView b;

            public void onSuccess(Object obj) {
                if (i == -1) {
                    this.b.a[0].post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            int curIMUIndex = DataFlycSetAndGetRedundancyIMUIndex.getInstance().getCurIMUIndex();
                            if (curIMUIndex == 0) {
                                this.a.b.a[0].setSelected(true);
                                this.a.b.a[1].setSelected(false);
                                this.a.b.a[2].setSelected(false);
                            } else if (curIMUIndex == 1) {
                                this.a.b.a[0].setSelected(false);
                                this.a.b.a[1].setSelected(true);
                                this.a.b.a[2].setSelected(false);
                            } else if (curIMUIndex == 2) {
                                this.a.b.a[0].setSelected(false);
                                this.a.b.a[1].setSelected(false);
                                this.a.b.a[2].setSelected(true);
                            }
                        }
                    });
                    if (this.b.h) {
                        try {
                            Thread.sleep(OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                            this.b.a(-1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (this.b.h) {
                    try {
                        Thread.sleep(1000);
                        this.b.a(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void a(IMUStatus iMUStatus) {
        if (i.getInstance().c() == ProductType.N3) {
            this.i.setVisibility(8);
            this.j.setText(R.string.fpv_redundancy_history_system_extend_imu);
        } else {
            this.i.setVisibility(0);
            this.j.setText(R.string.fpv_redundancy_history_system2);
        }
        int i = iMUStatus.imuIndex;
        if (!this.g) {
            this.b[0].setText(R.string.fpv_redundancy_version_not_match);
        } else if (i >= 0 && i <= 2) {
            if (COLOR_STATUS.ofValue(iMUStatus.colorStatus) == COLOR_STATUS.a) {
                this.c[i].setImageResource(R.drawable.fpv_redundancy_system__status_gray);
                this.a[i].setEnabled(false);
                this.f[i].go();
                this.b[i].setText("");
                return;
            }
            this.c[i].setImageResource(b(iMUStatus.colorStatus));
            this.a[i].setEnabled(true);
            if (iMUStatus.devErrCode == 0) {
                this.b[i].setText(String.format(": %s", new Object[]{getContext().getString(R.string.fpv_redundancy_normal)}));
                this.f[i].go();
                return;
            }
            DJIRedundancySysController.c a = DJIRedundancySysController.a(getContext(), iMUStatus);
            CharSequence charSequence = "";
            if (a.d != null && a.d.usr_show_enable == 1) {
                this.b[i].setText(String.format(": [%s]%s", new Object[]{a.b, a.d.getUserErrTips()}));
                charSequence = iMUStatus.isRealInAir ? a.d.in_air_used_action : a.d.ground_action;
            } else if (l.a(a.b)) {
                this.b[i].setText(String.format(": [%d]%d", new Object[]{Integer.valueOf(iMUStatus.devType), Integer.valueOf(iMUStatus.devErrCode)}));
            } else {
                this.b[i].setText(String.format(": [%s]%d", new Object[]{a.b, Integer.valueOf(iMUStatus.devErrCode)}));
            }
            if (l.a(charSequence)) {
                this.f[i].go();
                return;
            }
            this.f[i].setText(charSequence);
            this.f[i].show();
        }
    }

    private int b(int i) {
        switch (AnonymousClass4.a[COLOR_STATUS.ofValue(i).ordinal()]) {
            case 1:
            case 2:
                return R.drawable.fpv_redundancy_system__status_green;
            case 3:
            case 4:
                return R.drawable.fpv_redundancy_system__status_red;
            case 5:
            case 6:
                return R.drawable.fpv_redundancy_system__status_yellow;
            default:
                return R.drawable.fpv_redundancy_system__status_gray;
        }
    }

    public boolean isCurUse(int i) {
        int i2 = 1;
        COLOR_STATUS ofValue = COLOR_STATUS.ofValue(i);
        int i3 = (ofValue == COLOR_STATUS.e ? 1 : 0) | (ofValue == COLOR_STATUS.g ? 1 : 0);
        if (ofValue != COLOR_STATUS.f) {
            i2 = 0;
        }
        return i3 | i2;
    }

    public void onEventMainThread(DataFlycRedundancyStatus dataFlycRedundancyStatus) {
        if (dataFlycRedundancyStatus.a() == RS_CMD_TYPE.b) {
            for (IMUStatus a : dataFlycRedundancyStatus.c()) {
                a(a);
            }
        }
    }

    public void onEventMainThread(DJIRedundancySysController.d dVar) {
        int i = dVar.a;
        for (int i2 = 0; i2 < 3; i2++) {
            if (i == i2 + 1) {
                this.e[i2].show();
            } else {
                this.e[i2].go();
            }
        }
    }
}
