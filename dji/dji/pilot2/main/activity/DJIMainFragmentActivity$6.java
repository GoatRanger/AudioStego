package dji.pilot2.main.activity;

import dji.pilot2.multimoment.template.TemplateController;
import dji.pilot2.multimoment.template.b;
import dji.pilot2.multimoment.template.c;
import dji.pilot2.utils.a.a.a;

class DJIMainFragmentActivity$6 implements a {
    final /* synthetic */ DJIMainFragmentActivity a;

    DJIMainFragmentActivity$6(DJIMainFragmentActivity dJIMainFragmentActivity) {
        this.a = dJIMainFragmentActivity;
    }

    public void a(String str, float f) {
    }

    public void a() {
        TemplateController.getInstance().getTemplates(this.a);
        c.getInstance().a(this.a);
        b.getInstance().b(DJIMainFragmentActivity.c(this.a));
    }

    public void b() {
    }
}
