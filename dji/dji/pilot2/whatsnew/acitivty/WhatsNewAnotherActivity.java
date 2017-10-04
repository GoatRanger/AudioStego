package dji.pilot2.whatsnew.acitivty;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot.fpv.d.c$w;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot2.main.activity.DJIMainFragmentActivity;
import dji.pilot2.nativeexplore.activity.FullScreenLandscapeWebActivity;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import java.util.Locale;

public class WhatsNewAnotherActivity extends DJIBaseActivity implements OnClickListener, c$w {
    private ImageView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private String g;
    private String h;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_whatsnew_another);
        a();
    }

    private void a() {
        this.c = (ImageView) findViewById(R.id.i_);
        this.e = (TextView) findViewById(R.id.ib);
        this.f = (TextView) findViewById(R.id.ic);
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        String language = Locale.getDefault().getLanguage();
        if (language == null || !"cn".equals(language)) {
            this.g = "http://d16koec4ujdumm.cloudfront.net/djigo/1450420428905-70bb95727a17af2f249a2bb23b99a753.mp4";
        } else {
            this.g = "http://d16koec4ujdumm.cloudfront.net/djigo/1450420428905-70bb95727a17af2f249a2bb23b99a753.mp4";
        }
        if (language == null || !"cn".equals(language)) {
            this.h = "https://test.aasky.net/pilot/iwant/introduction";
        } else {
            this.h = "https://test.aasky.net/pilot/iwant/introduction";
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.i_:
                intent.setClass(this, FullScreenLandscapeWebActivity.class);
                intent.putExtra(DJIWebviewFragment.o, this.g);
                startActivity(intent);
                return;
            case R.id.ib:
                Intent[] intentArr = new Intent[]{new Intent(this, DJIMainFragmentActivity.class)};
                intentArr[0].putExtra(DJIMainFragmentActivity.M, 1);
                startActivities(intentArr);
                return;
            case R.id.ic:
                intent.setClass(this, DJIMainFragmentActivity.class);
                startActivity(intent);
                finish();
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(this, DJIMainFragmentActivity.class));
        e.b(c$w.b);
        finish();
    }
}
