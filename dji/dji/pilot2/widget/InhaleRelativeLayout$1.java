package dji.pilot2.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class InhaleRelativeLayout$1 extends Animation {
    final /* synthetic */ InhaleRelativeLayout a;

    InhaleRelativeLayout$1(InhaleRelativeLayout inhaleRelativeLayout) {
        this.a = inhaleRelativeLayout;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        for (int i = 0; i < InhaleRelativeLayout.a(this.a).length; i++) {
            InhaleRelativeLayout.b(this.a).getPosTan((InhaleRelativeLayout.b(this.a).getLength() * f) + (((float) InhaleRelativeLayout.c(this.a).getHeight()) * (((float) i) / 20.0f)), InhaleRelativeLayout.d(this.a), null);
            InhaleRelativeLayout.a(this.a)[i].set(InhaleRelativeLayout.d(this.a)[0], InhaleRelativeLayout.d(this.a)[1]);
            InhaleRelativeLayout.e(this.a).getPosTan((InhaleRelativeLayout.e(this.a).getLength() * f) + (((float) InhaleRelativeLayout.c(this.a).getHeight()) * (((float) i) / 20.0f)), InhaleRelativeLayout.d(this.a), null);
            InhaleRelativeLayout.f(this.a)[i].set(InhaleRelativeLayout.d(this.a)[0], InhaleRelativeLayout.d(this.a)[1]);
        }
        InhaleRelativeLayout.g(this.a).a(InhaleRelativeLayout.a(this.a), InhaleRelativeLayout.f(this.a));
        this.a.invalidate();
        if (f >= 1.0f) {
            if (InhaleRelativeLayout.h(this.a) != null) {
                InhaleRelativeLayout.h(this.a).a();
            }
            InhaleRelativeLayout.a(this.a, false);
        }
    }
}
