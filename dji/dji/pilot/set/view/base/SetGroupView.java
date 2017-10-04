package dji.pilot.set.view.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.pilot.set.R;
import dji.pilot.set.c;
import dji.pilot.set.longan.b;

public class SetGroupView extends LinearLayout implements OnClickListener {
    private static final int e = 0;
    private TextView a;
    protected TextView b;
    private int c;
    private int d;
    private Handler f = new Handler(new Callback(this) {
        final /* synthetic */ SetGroupView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a();
                    break;
            }
            return false;
        }
    });

    public SetGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.set);
        obtainStyledAttributes.getInt(R.styleable.set_view_type, 0);
        this.d = obtainStyledAttributes.getResourceId(R.styleable.set_title, 0);
        this.c = obtainStyledAttributes.getResourceId(R.styleable.set_sub_Layout, 0);
        obtainStyledAttributes.recycle();
        setOnClickListener(this);
    }

    public void onClick(View view) {
        if (!c.a()) {
            dji.thirdparty.a.c.a().e(new b(this.c, this.d, getContext()));
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (TextView) findViewById(R.id.set_item_title);
        if (this.a == null) {
            Log.d("czf", "onFinishInflate: ");
            addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_group, null, false));
        }
        this.b = (TextView) findViewById(R.id.set_item_value);
        this.a = (TextView) findViewById(R.id.set_item_title);
        this.a.setText(this.d);
    }

    private void a() {
        setEnabled(true);
    }
}
