package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import dji.pilot.dji_groundstation.R;

public class CustomerSpinner extends TextView implements OnClickListener, OnItemClickListener {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private PopupWindow d = null;
    private int e = 0;
    private OnItemClickListener f;
    private String[] g;
    private a h;
    private ListView i;
    private int j;
    private int k;
    private int l;
    private View m;
    private int n;
    private ColorStateList o = null;
    private int p = 0;

    private class a extends BaseAdapter {
        final /* synthetic */ CustomerSpinner a;

        private a(CustomerSpinner customerSpinner) {
            this.a = customerSpinner;
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public int getCount() {
            return this.a.g.length;
        }

        public String a(int i) {
            return this.a.g[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                textView = new TextView(this.a.getContext());
                textView.setTextSize(1, 12.0f);
                textView.setLayoutParams(new LayoutParams(this.a.j, this.a.k));
                textView.setGravity(17);
                textView.setTextColor(this.a.getContext().getResources().getColor(R.color.spinner_text2));
                view = textView;
            } else {
                textView = (TextView) view;
            }
            textView.setText(a(i));
            if (this.a.e == i) {
                textView.setTextColor(this.a.n);
            } else {
                textView.setTextColor(this.a.getContext().getResources().getColorStateList(R.color.spinner_text2));
            }
            return view;
        }
    }

    public CustomerSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.m = inflate(context, R.layout.custom_spinner, null);
            this.i = (ListView) this.m.findViewById(R.id.custom_spinner_list);
            setClickable(true);
            setOnClickListener(this);
            this.i.setOnItemClickListener(this);
            this.n = getResources().getColor(R.color.setting_ui_btn_hover);
        }
    }

    public void setShowType(int i) {
        this.p = i;
    }

    public void setData(String[] strArr) {
        this.g = strArr;
        if (strArr != null && strArr.length > 0) {
            setText(strArr[0]);
        }
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    private void a() {
        this.d = new PopupWindow(this, this.m) {
            final /* synthetic */ CustomerSpinner a;

            public void dismiss() {
                super.dismiss();
            }
        };
        this.d.setWindowLayoutMode(-2, -2);
        if (this.p == 1) {
            this.d.setAnimationStyle(R.style.gs_customSpinnerBottomAnim);
        } else if (this.p == 2) {
            this.d.setAnimationStyle(R.style.gs_customSpinnerTopAnim);
        } else {
            this.d.setAnimationStyle(R.style.gs_customSpinnerAnim);
        }
        this.d.setBackgroundDrawable(new BitmapDrawable(getContext().getResources()));
        this.d.setFocusable(true);
        this.d.setOutsideTouchable(true);
        this.l = this.k * getMySize();
        this.i.setLayoutParams(new LinearLayout.LayoutParams(this.j, this.l));
        this.h = new a();
        this.i.setAdapter(this.h);
    }

    private int getMySize() {
        return this.g.length > 6 ? 6 : this.g.length;
    }

    public void onClick(View view) {
        if (isEnabled() && this.g != null && this.g.length != 0) {
            if (this.d == null) {
                this.j = getWidth();
                this.k = getHeight();
                a();
            }
            if (1 == this.p) {
                this.d.showAsDropDown(this, 0, -this.l);
            } else if (this.p == 2) {
                this.d.showAsDropDown(this, 0, -this.k);
            } else {
                this.d.showAsDropDown(this, 0, -((this.l + this.k) / 2));
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f = onItemClickListener;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        setSelection(i);
        if (this.f != null) {
            this.f.onItemClick(adapterView, view, i, j);
        }
    }

    public void setSelection(int i) {
        this.e = i;
        setText(this.g[i]);
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
    }

    public int getSelectedItemPosition() {
        return this.e;
    }
}
