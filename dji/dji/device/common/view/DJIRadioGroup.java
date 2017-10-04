package dji.device.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class DJIRadioGroup extends LinearLayout {
    private static final String a = DJIRadioGroup.class.getSimpleName();
    private int b = -1;
    private OnCheckedChangeListener c;
    private boolean d = false;
    private b e;
    private c f;
    private HashMap<Integer, RadioButton> g = new HashMap();

    public interface b {
        void onCheckedChanged(DJIRadioGroup dJIRadioGroup, int i);
    }

    public static class LayoutParams extends android.widget.LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2, f);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            if (typedArray.hasValue(i)) {
                this.width = typedArray.getLayoutDimension(i, "layout_width");
            } else {
                this.width = -2;
            }
            if (typedArray.hasValue(i2)) {
                this.height = typedArray.getLayoutDimension(i2, "layout_height");
            } else {
                this.height = -2;
            }
        }
    }

    private class a implements OnCheckedChangeListener {
        final /* synthetic */ DJIRadioGroup a;

        private a(DJIRadioGroup dJIRadioGroup) {
            this.a = dJIRadioGroup;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!this.a.d) {
                this.a.d = true;
                if (this.a.b != -1) {
                    this.a.a(this.a.b, false);
                }
                this.a.d = false;
                this.a.setCheckedId(compoundButton.getId());
            }
        }
    }

    private class c implements OnHierarchyChangeListener {
        final /* synthetic */ DJIRadioGroup a;
        private OnHierarchyChangeListener b;
        private int c;
        private final AtomicInteger d;

        private c(DJIRadioGroup dJIRadioGroup) {
            this.a = dJIRadioGroup;
            this.c = 0;
            this.d = new AtomicInteger(1);
        }

        public ArrayList<RadioButton> a(ViewGroup viewGroup) {
            ArrayList<RadioButton> arrayList = new ArrayList();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof RadioButton) {
                    arrayList.add((RadioButton) childAt);
                } else if (childAt instanceof ViewGroup) {
                    arrayList.addAll(a((ViewGroup) childAt));
                }
            }
            return arrayList;
        }

        @SuppressLint({"NewApi"})
        public void a(RadioButton radioButton) {
            int id = radioButton.getId();
            if (id == -1) {
                if (VERSION.SDK_INT > 16) {
                    id = View.generateViewId();
                } else {
                    id = a();
                }
                radioButton.setId(id);
            }
            radioButton.setOnCheckedChangeListener(this.a.c);
            if (radioButton.isChecked()) {
                int i = this.c;
                this.c = i + 1;
                if (i > 1) {
                    Log.e(DJIRadioGroup.a, "You can only select one RadioButton");
                }
                this.a.b = radioButton.getId();
            }
            if (this.c > 1) {
                this.a.removeAllViews();
            }
            this.a.g.put(Integer.valueOf(id), radioButton);
        }

        public int a() {
            int i;
            int i2;
            do {
                i = this.d.get();
                i2 = i + 1;
                if (i2 > ViewCompat.MEASURED_SIZE_MASK) {
                    i2 = 1;
                }
            } while (!this.d.compareAndSet(i, i2));
            return i;
        }

        public void onChildViewAdded(View view, View view2) {
            if (view == this.a && (view2 instanceof RadioButton)) {
                a((RadioButton) view2);
            } else if (view == this.a && (view2 instanceof ViewGroup)) {
                Iterator it = a((ViewGroup) view2).iterator();
                while (it.hasNext()) {
                    a((RadioButton) it.next());
                }
            }
            if (this.b != null) {
                this.b.onChildViewAdded(view, view2);
            }
        }

        public void onChildViewRemoved(View view, View view2) {
            if (view == this.a && (view2 instanceof RadioButton)) {
                ((RadioButton) view2).setOnCheckedChangeListener(null);
            }
            if (this.b != null) {
                this.b.onChildViewRemoved(view, view2);
            }
        }
    }

    public DJIRadioGroup(Context context) {
        super(context);
        setOrientation(1);
        b();
    }

    public DJIRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(getOrientation());
        b();
    }

    private void b() {
        this.c = new a();
        this.f = new c();
        super.setOnHierarchyChangeListener(this.f);
    }

    public void setOnHierarchyChangeListener(OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f.b = onHierarchyChangeListener;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.b != -1) {
            this.d = true;
            a(this.b, true);
            this.d = false;
            setCheckedId(this.b);
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        if (view instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) view;
            if (radioButton.isChecked()) {
                this.d = true;
                if (this.b != -1) {
                    a(this.b, false);
                }
                this.d = false;
                setCheckedId(radioButton.getId());
            }
        }
        super.addView(view, i, layoutParams);
    }

    public void check(int i) {
        if (i == -1 || i != this.b) {
            if (this.b != -1) {
                a(this.b, false);
            }
            if (i != -1) {
                a(i, true);
            }
            setCheckedId(i);
        }
    }

    private void setCheckedId(int i) {
        this.b = i;
        if (this.e != null) {
            this.e.onCheckedChanged(this, this.b);
        }
    }

    private void a(int i, boolean z) {
        View findViewById = findViewById(i);
        if (findViewById != null && (findViewById instanceof RadioButton)) {
            ((RadioButton) findViewById).setChecked(z);
        }
    }

    public int getCheckedRadioButtonId() {
        return this.b;
    }

    public void clearCheckedId() {
        this.b = -1;
    }

    public void clearCheck() {
        check(-1);
    }

    public void setOnCheckedChangeListener(b bVar) {
        this.e = bVar;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(DJIRadioGroup.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DJIRadioGroup.class.getName());
    }

    public RadioButton getRadioButtonByCheckedId(int i) {
        return (RadioButton) this.g.get(Integer.valueOf(i));
    }
}
