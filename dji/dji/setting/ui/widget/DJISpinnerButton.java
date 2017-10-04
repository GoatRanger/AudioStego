package dji.setting.ui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import dji.pilot.setting.ui.R;

public class DJISpinnerButton extends Button implements OnDismissListener, OnClickListener, OnItemClickListener {
    private d a;
    private a b;
    private b c;
    private int d = 0;
    private String[] e;

    public interface b {
        void onItemClick(int i);
    }

    private class a extends BaseAdapter {
        final /* synthetic */ DJISpinnerButton a;

        private a(DJISpinnerButton dJISpinnerButton) {
            this.a = dJISpinnerButton;
        }

        public int getCount() {
            return this.a.e.length;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.a.getContext()).inflate(R.layout.setting_ui_widget_popupwindow_list_item, null);
            }
            TextView textView = (TextView) view.findViewById(R.id.setting_ui_widget_popupwindow_list_tv);
            textView.setHeight(this.a.getHeight());
            textView.setText(this.a.e[i]);
            if (this.a.d == i) {
                textView.setSelected(true);
            } else {
                textView.setSelected(false);
            }
            return view;
        }
    }

    public DJISpinnerButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            setOnClickListener(this);
            this.b = new a();
        }
    }

    public void setData(String[] strArr, int i, b bVar) {
        if (strArr != null && strArr.length > i) {
            this.e = strArr;
            this.d = i;
            this.c = bVar;
            setText(this.e[this.d]);
        }
    }

    public void setData(int[] iArr, int i, b bVar) {
        if (iArr != null && iArr.length > i && iArr.length != 0) {
            this.e = new String[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                this.e[i2] = getContext().getResources().getString(iArr[i2]);
            }
            this.d = i;
            this.c = bVar;
            setText(this.e[this.d]);
        }
    }

    public void setIndex(int i) {
        if (this.e != null && this.e.length >= i) {
            this.d = i;
            setText(this.e[this.d]);
        }
    }

    public int getCurIndex() {
        return this.d;
    }

    public void onClick(View view) {
        if (this.e != null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.setting_ui_widget_popupwindow_list, null);
            ListView listView = (ListView) inflate.findViewById(R.id.setting_ui_widget_popupwindow_list);
            listView.setAdapter(this.b);
            listView.setOnItemClickListener(this);
            int width = getWidth();
            int height = this.e.length > 5 ? getHeight() * 5 : this.e.length * getHeight();
            this.a = new d(getContext(), inflate, this, width, height);
            this.a.setOnDismissListener(this);
            a(this.a, this, width, height);
            setVisibility(4);
        }
    }

    private void a(d dVar, View view, int i, int i2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        i3 = displayMetrics.widthPixels;
        i4 = displayMetrics.heightPixels;
        dVar.show();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.dismiss();
        if (this.d != i) {
            this.d = i;
            setText(this.e[this.d]);
            if (this.c != null) {
                this.c.onItemClick(i);
            }
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        setVisibility(0);
    }
}
