package dji.device.common.view.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import dji.logic.f.b;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus2;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus2.CtrlDirection;
import dji.midware.data.model.P3.DataRcSetFollowFocusInfo;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class LonganFallowFocusView extends ScrollView implements OnCheckedChangeListener, dji.device.common.view.set.view.DJIStageViewCompat.a {
    private static final String b = LonganFallowFocusView.class.getSimpleName();
    private static final int[] d = new int[]{R.id.longan_camera_follow_Aperture, R.id.longan_camera_follow_focus, R.id.longan_camera_follow_zoom};
    CtrlDirection a;
    private RadioGroup c;
    private int e = -1;
    private final a[] f = new a[d.length];
    private Context g;
    private OnClickListener h = null;

    private static final class a {
        public DJIRelativeLayout a;
        public DJITextView b;
        public DJIImageView c;
        public DJIImageView d;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public LonganFallowFocusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = context;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onFinishInflate() {
        int i = 0;
        super.onFinishInflate();
        c();
        this.c = (RadioGroup) findViewById(R.id.shotcuts_follow_focus_rg);
        this.c.setOnCheckedChangeListener(this);
        Log.d(b, "onFinishInflate: mCurrentDirectionIndex = " + this.a.value());
        if (this.a == CtrlDirection.CW) {
            this.c.check(R.id.shotcuts_follow_focus_normal);
        } else if (this.a == CtrlDirection.CCW) {
            this.c.check(R.id.shotcuts_follow_focus_reverse);
        }
        int[] iArr = new int[]{R.string.longan_follow_focus_Aperture, R.string.longan_follow_focus_Focus, R.string.longan_follow_focus_Zoom};
        while (i < d.length) {
            a aVar = new a();
            aVar.a = (DJIRelativeLayout) findViewById(d[i]);
            aVar.b = (DJITextView) aVar.a.findViewById(R.id.longan_shotcuts_itemlist_title);
            aVar.c = (DJIImageView) aVar.a.findViewById(R.id.longan_shotcuts_itemlist_value_iv);
            aVar.d = (DJIImageView) aVar.a.findViewById(R.id.longan_shotcuts_itemlist_arrow);
            aVar.a.setOnClickListener(this.h);
            aVar.b.setText(iArr[i]);
            aVar.d.setVisibility(4);
            this.f[i] = aVar;
            i++;
        }
    }

    private void c() {
        d();
        this.h = new OnClickListener(this) {
            final /* synthetic */ LonganFallowFocusView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                for (int i = 0; i < LonganFallowFocusView.d.length; i++) {
                    if (id == LonganFallowFocusView.d[i]) {
                        this.a.a(i);
                        return;
                    }
                }
            }
        };
    }

    private void d() {
        this.e = DataRcGetPushFollowFocus2.getInstance().getCtrlType().value();
        this.a = DataRcGetPushFollowFocus2.getInstance().getCtrlDirection();
    }

    private void a(int i) {
        Log.d(b, "handleItemClick = " + i);
        if (i != 2 || b.a(DataCameraGetPushShotInfo.getInstance())) {
            this.e = i;
            f();
            return;
        }
        Log.d(b, "handleItemClick: don't support focus distance change");
        e();
    }

    private void e() {
        final dji.device.camera.view.focus.a.a aVar = new dji.device.camera.view.focus.a.a(this.g, R.style.LpBaseDialog);
        aVar.a(false).e(false).a(1, false);
        aVar.setTitle(R.string.longan_focus_handwheel_warning_title);
        aVar.a(R.string.longan_focus_handwheel_warning_message);
        aVar.a(0, getResources().getString(R.string.app_ikonw));
        aVar.a(new OnClickListener(this) {
            final /* synthetic */ LonganFallowFocusView b;

            public void onClick(View view) {
                aVar.dismiss();
            }
        });
        aVar.show();
    }

    private void f() {
        DataRcSetFollowFocusInfo.getInstance().a(this.e).a(new d(this) {
            final /* synthetic */ LonganFallowFocusView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void onEventMainThread(DataRcGetPushFollowFocus2 dataRcGetPushFollowFocus2) {
        this.e = dataRcGetPushFollowFocus2.getCtrlType().value();
        for (a aVar : this.f) {
            aVar.d.setVisibility(8);
        }
        this.f[this.e].d.setVisibility(0);
        if (this.a != dataRcGetPushFollowFocus2.getCtrlDirection()) {
            this.a = dataRcGetPushFollowFocus2.getCtrlDirection();
            if (this.a == CtrlDirection.CW || this.a != CtrlDirection.CCW) {
            }
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public void onEventMainThread(dji.device.camera.view.focus.a.b.a aVar) {
        switch (aVar) {
            case hideUI:
                ((DJIStageViewCompat) getParent()).destoryStageView(true);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Log.d(b, "onCheckedChanged: checkedId = " + i);
        if (i == R.id.shotcuts_follow_focus_normal) {
            this.a = CtrlDirection.CW;
            DataRcSetFollowFocusInfo.getInstance().b(this.a.value()).a(new d(this) {
                final /* synthetic */ LonganFallowFocusView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    Log.d(LonganFallowFocusView.b, "onSuccess: setCtrDirection(0)");
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        } else if (i == R.id.shotcuts_follow_focus_reverse) {
            this.a = CtrlDirection.CCW;
            DataRcSetFollowFocusInfo.getInstance().b(this.a.value()).a(new d(this) {
                final /* synthetic */ LonganFallowFocusView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    Log.d(LonganFallowFocusView.b, "onSuccess: setCtrDirection(1)");
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    public View getView() {
        return this;
    }
}
