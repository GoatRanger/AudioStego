package dji.pilot.set.view.base;

import android.content.Context;
import android.util.AttributeSet;
import dji.pilot.set.a;

public abstract class SetLocalSwitchView extends SetSwitchView {
    protected abstract String getKey();

    public SetLocalSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        this.d = a.b(getContext(), getKey(), getDefaultValue());
        if (this.c.isChecked() != this.d) {
            this.c.setChecked(this.d);
        }
    }

    protected void setValue(boolean z) {
        this.d = z;
        a.a(getContext(), getKey(), this.d);
    }

    public void onEventBackgroundThread(a.a aVar) {
        String str = aVar.a;
        if (str != null && str.equals(getKey())) {
            a();
        }
    }

    protected boolean getDefaultValue() {
        return false;
    }
}
