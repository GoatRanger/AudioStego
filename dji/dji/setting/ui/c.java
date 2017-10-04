package dji.setting.ui;

import android.view.LayoutInflater;
import android.view.View;

public class c {
    private int a;
    private View b;
    private SettingUIStageView c = null;

    public c(int i, int i2, View view) {
        a(LayoutInflater.from(view.getContext()).inflate(i, null), i2, view);
    }

    public c(int i, int i2, View view, Object obj) {
        View inflate = LayoutInflater.from(view.getContext()).inflate(i, null);
        inflate.setTag(obj);
        a(inflate, i2, view);
    }

    public c(View view, int i, View view2) {
        a(view, i, view2);
    }

    private void a(View view, int i, View view2) {
        this.b = view;
        this.a = i;
        if (view2 instanceof SettingUIStageView) {
            this.c = (SettingUIStageView) view2;
            return;
        }
        while (view2.getParent() != null) {
            if (view2.getParent() instanceof SettingUIStageView) {
                this.c = (SettingUIStageView) view2.getParent();
                return;
            }
            view2 = (View) view2.getParent();
        }
    }

    public c(View view, int i) {
        this.b = view;
        this.a = i;
        while (view.getParent() != null) {
            if (view.getParent() instanceof SettingUIStageView) {
                this.c = (SettingUIStageView) view.getParent();
                return;
            }
            view = (View) view.getParent();
        }
    }

    public int a() {
        return this.a;
    }

    public View b() {
        return this.b;
    }

    public SettingUIStageView c() {
        return this.c;
    }
}
