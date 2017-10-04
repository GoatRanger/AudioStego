package dji.pilot.visual.stage;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.model.P3.DataSingleSetPointPos.TapMode;
import dji.pilot.R;
import dji.pilot.fpv.model.n$b;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.b;
import dji.pilot.publics.widget.e;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIVisualPointOptView extends DJILinearLayout implements OnClickListener, a {
    private final TapMode[] a = new TapMode[]{TapMode.a, TapMode.b, TapMode.d};
    private DJIImageView b = null;
    private final DJILinearLayout[] c = new DJILinearLayout[3];
    private e d = null;
    private final d e = c.getInstance().a();

    public DJIVisualPointOptView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void show() {
        super.show();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.b = (DJIImageView) findViewById(R.id.d9u);
            this.b.setOnClickListener(this);
            int[] iArr = new int[]{R.id.d9v, R.id.d9w, R.id.d9x};
            int[] iArr2 = new int[]{R.string.visual_point_normal, R.string.visual_point_reverse, R.string.visual_point_hlparallel};
            int[] iArr3 = new int[]{R.drawable.selector_vision_point_normal, R.drawable.selector_vision_point_reverse, R.drawable.selector_vision_point_hlparallel};
            int length = this.a.length;
            for (int i = 0; i < length; i++) {
                DJILinearLayout dJILinearLayout = (DJILinearLayout) findViewById(iArr[i]);
                ((DJIImageView) dJILinearLayout.findViewById(R.id.d9n)).setBackgroundResource(iArr3[i]);
                ((DJITextView) dJILinearLayout.findViewById(R.id.d9o)).setText(iArr2[i]);
                dJILinearLayout.setOnClickListener(this);
                this.c[i] = dJILinearLayout;
            }
            TapMode b = this.e.b();
            for (int i2 = 0; i2 < length; i2++) {
                if (b == this.a[i2]) {
                    setSelectedPos(i2);
                    return;
                }
            }
        }
    }

    private void a(final TapMode tapMode, final boolean z) {
        if (this.d == null) {
            this.d = new e(getContext());
        }
        if (!this.d.isShowing()) {
            if (TapMode.b == tapMode) {
                this.d.a((int) R.string.visual_point_reverse).b(R.string.visual_point_reverse_desc).c(R.drawable.visual_point_backward_icon).a(new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJIVisualPointOptView c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean z = true;
                        if (!z) {
                            this.c.e.b(tapMode);
                            this.c.setSelectedPos(1);
                            d a = this.c.e;
                            TapMode tapMode = tapMode;
                            if (this.c.d.b()) {
                                z = false;
                            }
                            a.a(tapMode, z);
                        }
                        this.c.a();
                    }
                });
            } else if (TapMode.d == tapMode) {
                this.d.a((int) R.string.visual_point_hlparallel).b(R.string.visual_point_hlparallel_desc).c(R.drawable.visual_point_free_icon).a(new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJIVisualPointOptView c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!z) {
                            this.c.e.b(tapMode);
                            this.c.setSelectedPos(2);
                            this.c.e.a(tapMode, !this.c.d.b());
                        }
                        this.c.a();
                    }
                });
            } else if (TapMode.a == tapMode) {
                this.d.a((int) R.string.visual_point_normal).b(R.string.visual_point_normal_desc).c(R.drawable.visual_point_forward_icon).a(new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJIVisualPointOptView c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!z) {
                            this.c.e.b(tapMode);
                            this.c.setSelectedPos(0);
                        }
                        this.c.a();
                    }
                });
            }
            this.d.a(!z);
            dji.thirdparty.a.c.a().e(n$b.HIDE_VIEWS);
            this.d.show();
        }
    }

    private void a() {
        if (this.d != null && this.d.isShowing()) {
            dji.thirdparty.a.c.a().e(n$b.SHOW_VIEWS);
            this.d.dismiss();
            this.d = null;
        }
    }

    public void onClick(View view) {
        if (view == this.b) {
            a(this.e.b(), true);
        } else if (this.e.a()) {
            b.a(getContext(), (int) R.string.app_tip, (int) R.string.visual_point_mode_switch_tip, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIVisualPointOptView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (int) R.string.app_enter, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIVisualPointOptView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    c.getInstance().g();
                    dialogInterface.dismiss();
                }
            }).show();
        } else {
            int length = this.a.length;
            int i = 0;
            while (i < length) {
                if (view != this.c[i]) {
                    i++;
                } else if (this.e.a(this.a[i])) {
                    a(this.a[i], false);
                    return;
                } else {
                    this.e.b(this.a[i]);
                    setSelectedPos(i);
                    return;
                }
            }
        }
    }

    private void setSelectedPos(int i) {
        int length = this.a.length;
        for (int i2 = 0; i2 < length; i2++) {
            boolean z;
            DJILinearLayout dJILinearLayout = this.c[i2];
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            dJILinearLayout.setSelected(z);
        }
    }

    public void setHideClickListener(OnClickListener onClickListener) {
        ((DJITextView) findViewById(R.id.d9t)).setOnClickListener(onClickListener);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
