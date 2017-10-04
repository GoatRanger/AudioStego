package dji.pilot.home.rc.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import dji.pilot.R;
import dji.pilot.gallery.entryActivity.DJIGalleryMainActivity;
import dji.pilot.home.rc.activity.DJIRcLibraryActivity;
import dji.pilot.home.rc.activity.DJIRcSkypixelActivity;
import dji.setting.a.a;

public class MainMenuRightView extends LinearLayout implements OnClickListener {
    protected Context a;
    private MainMenuRightButton b;
    private MainMenuRightButton c;
    private MainMenuRightButton d;

    public MainMenuRightView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    private void a() {
        a.a((View) this, (int) R.layout.rc_main_right_menu);
        if (!isInEditMode()) {
            this.b = (MainMenuRightButton) findViewById(R.id.bk7);
            this.c = (MainMenuRightButton) findViewById(R.id.bk6);
            this.d = (MainMenuRightButton) findViewById(R.id.bk5);
            this.b.setOnClickListener(this);
            this.c.setOnClickListener(this);
            this.d.setOnClickListener(this);
        }
    }

    private void b() {
        Intent intent = new Intent();
        intent.setClass(this.a, DJIRcSkypixelActivity.class);
        this.a.startActivity(intent);
    }

    private void c() {
        Intent intent = new Intent();
        intent.setClass(this.a, DJIGalleryMainActivity.class);
        this.a.startActivity(intent);
    }

    private void d() {
        Intent intent = new Intent();
        intent.setClass(this.a, DJIRcLibraryActivity.class);
        this.a.startActivity(intent);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bk5:
                d();
                return;
            case R.id.bk6:
                c();
                return;
            case R.id.bk7:
                b();
                return;
            default:
                return;
        }
    }
}
