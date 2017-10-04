package dji.setting.ui.gimbal.ronin;

import android.app.Dialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.WheelVerticalView;
import antistatic.spinnerwheel.a.e;
import com.dji.frame.c.c;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.a;

public class b extends Dialog {
    private TextView a = null;
    private WheelVerticalView b = null;
    private c c = null;
    private int d = 0;
    private int e = 100;
    private LayoutInflater f = null;

    public b(Context context, TextView textView, int i, int i2, int i3, c cVar) {
        super(context, R.style.setting_ui_dialog);
        this.e = i2;
        this.d = i;
        this.f = LayoutInflater.from(context);
        this.a = textView;
        setContentView(R.layout.setting_number_input_dialog);
        this.b = (WheelVerticalView) findViewById(R.id.setting_number_input_dialog_np);
        this.b.setViewAdapter(new e(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void b(DataSetObserver dataSetObserver) {
            }

            public void a(DataSetObserver dataSetObserver) {
            }

            public int h() {
                return (this.a.e - this.a.d) + 1;
            }

            public View a(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = this.a.f.inflate(R.layout.setting_number_input_item, null);
                }
                ((TextView) view.findViewById(R.id.setting_number_input_tx)).setText(String.format("%d", new Object[]{Integer.valueOf(this.a.e - i)}));
                return view;
            }

            public View a(View view, ViewGroup viewGroup) {
                return new View(this.a.getContext());
            }
        });
        this.b.addChangingListener(new antistatic.spinnerwheel.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel, int i, int i2) {
                if (this.a.a != null) {
                    int a = this.a.e - i2;
                    this.a.a.setText(String.valueOf(a));
                    if (this.a.c != null) {
                        this.a.c.a(this.a.a, a);
                    }
                }
            }
        });
        this.b.setCurrentItem(this.e - i3);
        this.c = cVar;
    }

    public void show() {
        getWindow().setFlags(8, 8);
        c.a(getWindow());
        a.a = getWindow().getDecorView().getSystemUiVisibility();
        super.show();
        getWindow().clearFlags(8);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) getContext().getResources().getDimension(R.dimen.dp_100);
        attributes.x = 0;
        attributes.height = -1;
        attributes.y = 0;
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        attributes.gravity = 3;
        getWindow().setAttributes(attributes);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}
