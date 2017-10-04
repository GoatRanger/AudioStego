package dji.pilot.visual.stage;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataSingleVisualParam.TrackingMode;
import dji.pilot.R;
import dji.pilot.fpv.model.n$b;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.b;
import dji.pilot.publics.widget.e;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.f;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;

public class DJIVisualTrackOptView extends DJILinearLayout implements OnClickListener, a {
    private final TrackingMode[] a = new TrackingMode[]{TrackingMode.a, TrackingMode.c, TrackingMode.e};
    private b b = null;
    private ArrayList<b.a> c = null;
    private DJIImageView d = null;
    private ListView e = null;
    private OnItemClickListener f = null;
    private volatile TrackingMode g = TrackingMode.a;
    private f h = c.getInstance().b();
    private e i = null;

    public DJIVisualTrackOptView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setTrackingMode(TrackingMode trackingMode) {
        int i = 0;
        if (this.g != trackingMode) {
            this.g = trackingMode;
            if (trackingMode == TrackingMode.g) {
                this.b.a(0);
                return;
            }
            int length = this.a.length;
            while (i < length) {
                if (trackingMode == this.a[i]) {
                    this.b.a(i);
                    return;
                }
                i++;
            }
        }
    }

    private void a() {
        int length = this.a.length;
        int[] iArr = new int[]{R.string.visual_flight_mode_headless_follow, R.string.visual_flight_mode_parallel, R.string.visual_flight_mode_watch};
        int[] iArr2 = new int[]{R.drawable.selector_vision_track_normal, R.drawable.selector_vision_track_parallel, R.drawable.selector_vision_track_watch};
        this.c = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            this.c.add(new b.a(iArr2[i], iArr[i]));
        }
        this.b = new b(getContext(), this.c);
        this.f = new OnItemClickListener(this) {
            final /* synthetic */ DJIVisualTrackOptView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                TrackingMode trackingMode = this.a.a[i];
                if (this.a.g != trackingMode) {
                    if (this.a.h.b()) {
                        b.a(this.a.getContext(), (int) R.string.app_tip, (int) R.string.visual_track_mode_switch_tip, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (int) R.string.app_enter, new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                this.a.a.h.h();
                                dialogInterface.dismiss();
                            }
                        }).show();
                    } else if (TrackingMode.a == trackingMode || !this.a.h.a(trackingMode)) {
                        this.a.h.a(trackingMode, null);
                    } else {
                        this.a.a(trackingMode, false);
                    }
                } else if (!this.a.h.b()) {
                    this.a.h.f();
                }
            }
        };
    }

    private void a(final TrackingMode trackingMode, final boolean z) {
        if (this.i == null) {
            this.i = new e(getContext());
        }
        if (!this.i.isShowing()) {
            if (TrackingMode.e == trackingMode || TrackingMode.d == trackingMode) {
                this.i.a((int) R.string.visual_track_spotlight_tip_title).b(R.string.visual_track_spotlight_tip).c(R.drawable.visual_spotlight_icon).a(new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJIVisualTrackOptView c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!z) {
                            this.c.h.a(trackingMode, !this.c.i.b());
                            this.c.h.a(trackingMode, null);
                        }
                        this.c.b();
                    }
                });
            } else if (TrackingMode.c == trackingMode) {
                this.i.a((int) R.string.visual_track_profile_tip_title).b(R.string.visual_track_profile_tip).c(R.drawable.visual_track_parallel_icon).a(new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJIVisualTrackOptView c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!z) {
                            this.c.h.a(trackingMode, !this.c.i.b());
                            this.c.h.a(trackingMode, null);
                        }
                        this.c.b();
                    }
                });
            } else if (TrackingMode.a == trackingMode) {
                this.i.a((int) R.string.visual_track_normal_tip_title).b(R.string.visual_track_normal_tip).c(R.drawable.visual_track_normal_icon).a(new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJIVisualTrackOptView c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!z) {
                            this.c.h.a(trackingMode, !this.c.i.b());
                            this.c.h.a(trackingMode, null);
                        }
                        this.c.b();
                    }
                });
            }
            this.i.a(!z);
            dji.thirdparty.a.c.a().e(n$b.HIDE_VIEWS);
            this.i.show();
        }
    }

    private void b() {
        if (this.i != null && this.i.isShowing()) {
            dji.thirdparty.a.c.a().e(n$b.SHOW_VIEWS);
            this.i.dismiss();
            this.i = null;
        }
    }

    public void onEventMainThread(f.a aVar) {
        setTrackingMode(aVar.k);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            this.d = (DJIImageView) findViewById(R.id.d_q);
            this.d.setOnClickListener(this);
            this.e = (ListView) findViewById(R.id.d_r);
            this.e.setOnItemClickListener(this.f);
            this.e.setAdapter(this.b);
        }
    }

    public void onClick(View view) {
        if (R.id.d_q == view.getId()) {
            a(this.h.d().k, true);
        }
    }

    public void setHideClickListener(OnClickListener onClickListener) {
        ((DJITextView) findViewById(R.id.d_p)).setOnClickListener(onClickListener);
    }

    public void dispatchOnStart() {
        c();
        onEventMainThread(this.h.d());
        dji.thirdparty.a.c.a().a(this);
    }

    public void dispatchOnStop() {
        b();
        dji.thirdparty.a.c.a().d(this);
    }

    private void c() {
        if (i.getInstance().c() == ProductType.Orange2) {
            this.a[this.a.length - 1] = TrackingMode.d;
        } else {
            this.a[this.a.length - 1] = TrackingMode.e;
        }
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
