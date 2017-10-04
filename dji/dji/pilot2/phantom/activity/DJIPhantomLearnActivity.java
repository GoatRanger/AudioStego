package dji.pilot2.phantom.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class DJIPhantomLearnActivity extends DJIBaseActivity {
    private DJITextView a;
    private int b;
    private String[] c = new String[]{"INSPIRE 1", "PHANTOM 3 ADVANCED", "PHANTOM 3 PROFESSIONAL"};
    private WebView d;
    private String[] e = new String[]{"http://store.dji.com/product/inspire-1?from=buy_now", "http://store.dji.com/product/phantom-3-professional?position=1", "http://store.dji.com/product/phantom-3-advanced?position=1"};
    private DJIImageView f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_title_web);
        this.a = (DJITextView) findViewById(R.id.d05);
        this.d = (WebView) findViewById(R.id.d06);
        this.f = (DJIImageView) findViewById(R.id.d04);
        this.b = getIntent().getIntExtra("producename", 0);
        this.a.setText(this.c[this.b]);
        this.d.loadUrl(this.e[this.b]);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPhantomLearnActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }
}
