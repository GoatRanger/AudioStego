package dji.pilot.set.longan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.pilot.set.R;

public class SetViewHeadBar extends LinearLayout implements OnClickListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private a d;

    public interface a {
        void a();

        void b();
    }

    public SetViewHeadBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SetViewHeadBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        b();
        a();
    }

    public void onClick(View view) {
        if (this.d == null) {
            return;
        }
        if (view.getId() == R.id.set_back) {
            this.d.b();
        } else if (view.getId() == R.id.set_done) {
            this.d.a();
        }
    }

    private void b() {
        this.a = (TextView) findViewById(R.id.set_title);
        this.c = (TextView) findViewById(R.id.set_done);
        this.b = (TextView) findViewById(R.id.set_back);
        this.c.setOnClickListener(this);
        this.b.setOnClickListener(this);
        a();
    }

    protected void a() {
    }

    public void setOnclickListenerInterface(a aVar) {
        this.d = aVar;
    }

    public void setTitle(int i) {
        this.a.setText(i);
    }

    public void setBackText(int i) {
        this.b.setText(i);
    }

    public void setBackVisibility(boolean z) {
        if (z) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
    }

    public void setDoneVisibility(boolean z) {
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }
}
