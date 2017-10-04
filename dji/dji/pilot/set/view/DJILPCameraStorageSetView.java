package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.e.d;
import dji.pilot.phonecamera.a.c;
import dji.pilot.set.R;
import dji.pilot.storage.a.a;

public class DJILPCameraStorageSetView extends LinearLayout implements OnClickListener {
    protected TextView a;
    protected Button b;
    protected d c;
    private TextView d;

    public DJILPCameraStorageSetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public DJILPCameraStorageSetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.camera_storage_set_item_btn, null, false));
        this.a = (TextView) findViewById(R.id.set_item_title);
        this.b = (Button) findViewById(R.id.set_item_btn);
        this.d = (TextView) findViewById(R.id.set_item_context);
        this.d.setText(c.a().b(getContext()));
        this.b.setOnClickListener(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().d(this);
    }

    public void onEventMainThread(a aVar) {
        this.d.setText(aVar.a());
    }

    public void onClick(View view) {
        dji.pilot.storage.a.d(getContext());
    }
}
