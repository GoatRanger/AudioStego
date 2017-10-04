package dji.pilot.liveshare.custom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.b;
import dji.pilot.set.g;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public class LiveCustomCreateView extends DJIRelativeLayout implements OnClickListener {
    protected b dialog;
    protected Activity mActivity;
    protected Button mNextBtn;
    protected DJIEditText mRtmpEt;

    public LiveCustomCreateView(Activity activity) {
        this(activity, null);
        this.mActivity = activity;
    }

    public LiveCustomCreateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    protected void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_custom_create);
        if (!isInEditMode()) {
            this.mRtmpEt = (DJIEditText) findViewById(R.id.a6m);
            this.mNextBtn = (Button) findViewById(R.id.a6l);
            this.mNextBtn.setOnClickListener(this);
            dji.pilot.f.a.a.F = dji.pilot.set.a.b(getContext(), g.p, dji.pilot.f.a.a.F);
            this.mRtmpEt.setText(dji.pilot.f.a.a.F);
        }
    }

    private boolean isUrlValid() {
        if (this.mRtmpEt.getText().toString().trim().toLowerCase().startsWith("rtmp://") || this.mRtmpEt.getText().toString().trim().toLowerCase().startsWith(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            return true;
        }
        return false;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a6l:
                if (isUrlValid()) {
                    dji.pilot.f.a.a.F = this.mRtmpEt.getText().toString();
                    dji.pilot.set.a.a(getContext(), g.p, dji.pilot.f.a.a.F);
                    c.a().e(new dji.pilot.f.a.a(17));
                    return;
                }
                if (this.dialog == null) {
                    this.dialog = new b(this.mActivity);
                }
                this.dialog.d((int) R.string.ok).a(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).a((int) R.string.liveshare_custom_error_title).b((int) R.string.liveshare_custom_error_description).show();
                return;
            default:
                return;
        }
    }
}
