package dji.setting.ui.general;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.b.a.b;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;

public class FlycSnView extends ItemViewText implements OnClickListener {
    final DataFlycActiveStatus a = new DataFlycActiveStatus();
    int b = 0;

    public FlycSnView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
        a();
        setOnClickListener(this);
        this.b = 0;
    }

    private void a() {
        this.a.setVersion(DataFlycActiveStatus.getInstance().getVersion()).setType(b.b).start(new d(this) {
            final /* synthetic */ FlycSnView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b();
                    }
                });
            }

            public void onFailure(a aVar) {
            }
        });
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void b() {
        CharSequence sn = this.a.getSN();
        if (sn == null) {
            this.d.setText(R.string.setting_ui_general_default_str);
        } else {
            this.d.setText(sn);
        }
    }

    public void onClick(View view) {
        if (this.b < 10) {
            this.b++;
            return;
        }
        Builder builder = new Builder(getContext());
        final AlertDialog create = builder.create();
        create.setMessage("uuid=" + com.dji.a.a.e());
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ FlycSnView b;

            public void onClick(DialogInterface dialogInterface, int i) {
                create.dismiss();
            }
        });
        create.show();
        this.b = 0;
    }
}
