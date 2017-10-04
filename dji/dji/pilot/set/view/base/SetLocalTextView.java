package dji.pilot.set.view.base;

import android.content.Context;
import android.util.AttributeSet;
import dji.pilot.set.a;

public abstract class SetLocalTextView extends SetTextView {
    protected abstract String getKey();

    public SetLocalTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        this.c = a.a(getContext(), getKey());
        setValueView(this.c);
    }

    protected void setValue(int i) {
        this.c = i;
        a.a(getContext(), getKey(), this.c);
        setValueView(this.c);
    }

    public void onEventBackgroundThread(a.a aVar) {
        String str = aVar.a;
        if (str != null && str.equals(getKey())) {
            a();
        }
    }
}
