package dji.pilot.fpv.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIImageButton;

public class a extends c implements OnClickListener {
    private DJIImageButton a;
    private CheckBox b;
    private DJIStateTextView c;
    private Context d;
    private String e = "https://www.skypixel.com/videos/459f544d-3cc7-4921-b1ea-71351129638c";

    public a(Context context) {
        super(context);
        this.d = context;
        b();
    }

    protected void onCreate(Bundle bundle) {
        a(-1, -1, 0, 17, false, false);
    }

    private void b() {
        setContentView(R.layout.fullscreen_precise_landing_view);
        this.a = (DJIImageButton) findViewById(R.id.afj);
        this.b = (CheckBox) findViewById(R.id.afk);
        this.c = (DJIStateTextView) findViewById(R.id.afl);
        this.b.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    this.a.c.setEnabled(true);
                }
            }
        });
        this.c.setOnClickListener(this);
        this.a.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.afj:
                Intent intent = new Intent(this.d, DJISupportShareWebviewActivity.class);
                intent.putExtra(DJIWebviewFragment.o, this.e);
                this.d.startActivity(intent);
                return;
            case R.id.afl:
                if (this.b.isChecked()) {
                    dismiss();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
