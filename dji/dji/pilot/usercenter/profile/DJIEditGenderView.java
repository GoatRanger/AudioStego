package dji.pilot.usercenter.profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.j;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIEditGenderView extends DJILinearLayout implements dji.pilot.fpv.view.DJIStageView.a {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int[] d = new int[]{R.id.bj5, R.id.bj6, R.id.bj7};
    private final a[] e = new a[d.length];
    private OnClickListener f = null;

    private static final class a {
        public View a;
        public DJITextView b;
        public DJIImageView c;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
        }
    }

    public DJIEditGenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
        a(f.getInstance().h().n);
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    private void a(int i) {
        if (i == 1) {
            this.e[0].c.show();
            this.e[1].c.go();
            this.e[2].c.go();
        } else if (i == 2) {
            this.e[0].c.go();
            this.e[1].c.show();
            this.e[2].c.go();
        } else {
            this.e[0].c.go();
            this.e[1].c.go();
            this.e[2].c.show();
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.f = new OnClickListener(this) {
                final /* synthetic */ DJIEditGenderView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    int id = view.getId();
                    j h = f.getInstance().h();
                    if (id == DJIEditGenderView.d[0]) {
                        h.n = 1;
                    } else if (id == DJIEditGenderView.d[1]) {
                        h.n = 2;
                    } else if (id == DJIEditGenderView.d[2]) {
                        h.n = 0;
                    }
                    this.a.a(h.n);
                }
            };
            for (int i = 0; i < d.length; i++) {
                a aVar = new a();
                aVar.a = findViewById(d[i]);
                aVar.b = (DJITextView) aVar.a.findViewById(R.id.bj3);
                aVar.c = (DJIImageView) aVar.a.findViewById(R.id.bj4);
                this.e[i] = aVar;
                aVar.a.setOnClickListener(this.f);
            }
            this.e[0].b.setText(R.string.usercenter_my_info_gender_male);
            this.e[1].b.setText(R.string.usercenter_my_info_gender_female);
            this.e[2].b.setText(R.string.usercenter_my_info_gender_nottell);
        }
    }

    public View getView() {
        return this;
    }
}
