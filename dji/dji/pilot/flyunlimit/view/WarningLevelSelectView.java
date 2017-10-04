package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.gs.b.c;
import dji.gs.c.e;
import dji.pilot.R;
import dji.pilot.flyunlimit.view.WarningTypeView.b;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.List;

public class WarningLevelSelectView extends DJILinearLayout implements a {
    private DJIStateImageView a;
    private DJITextView b;
    private DJITextView c;
    private DJITextView d;
    private DJITextView e;
    private List<DJITextView> f = new ArrayList();
    private b g;
    private int h = 0;
    private OnClickListener i;
    private e j;

    public WarningLevelSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        this.a = (DJIStateImageView) findViewById(R.id.dac);
        this.b = (DJITextView) findViewById(R.id.dad);
        this.c = (DJITextView) findViewById(R.id.dae);
        this.f.add(this.c);
        this.d = (DJITextView) findViewById(R.id.daf);
        this.f.add(this.d);
        this.e = (DJITextView) findViewById(R.id.dag);
        this.f.add(this.e);
        this.i = new OnClickListener(this) {
            final /* synthetic */ WarningLevelSelectView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.dac:
                        DJIStageView dJIStageView = (DJIStageView) this.a.getParent();
                        if (dJIStageView.canGoBack()) {
                            dJIStageView.destoryStageView(true);
                            return;
                        }
                        return;
                    case R.id.dae:
                        this.a.setSelect(0);
                        return;
                    case R.id.daf:
                        this.a.setSelect(1);
                        return;
                    case R.id.dag:
                        this.a.setSelect(2);
                        return;
                    default:
                        return;
                }
            }
        };
        this.a.setOnClickListener(this.i);
        this.c.setOnClickListener(this.i);
        this.d.setOnClickListener(this.i);
        this.e.setOnClickListener(this.i);
    }

    private void setSelect(int i) {
        if (!((DJITextView) this.f.get(i)).isSelected()) {
            b();
            ((DJITextView) this.f.get(i)).setSelected(true);
            c.getInstance(getContext()).a(this.h, i);
            this.j.c(this.g.b, i);
        }
    }

    public void setPointerManager(e eVar) {
        this.j = eVar;
    }

    private void b() {
        for (DJITextView selected : this.f) {
            selected.setSelected(false);
        }
    }

    public void setWarnAlertStruct(b bVar, int i) {
        this.h = i;
        this.g = bVar;
        b();
        ((DJITextView) this.f.get(bVar.c)).setSelected(true);
        this.b.setText(bVar.a);
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
