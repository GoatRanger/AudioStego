package dji.pilot2.academy.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.publics.DJIUI.DJITextView;

public class DJIFaqAnswerActivity extends DJIActivityNoFullScreen {
    public static String a = "name";
    public static String b = "answer";
    private DJITextView c;
    private DJITextView d;
    private DJIStateImageView t;
    private OnClickListener u = null;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_faq_answer_activity);
        this.d = (DJITextView) findViewById(R.id.cn1);
        this.c = (DJITextView) findViewById(R.id.c43);
        this.u = new OnClickListener(this) {
            final /* synthetic */ DJIFaqAnswerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.c42:
                        this.a.finish();
                        return;
                    default:
                        return;
                }
            }
        };
        this.t = (DJIStateImageView) findViewById(R.id.c42);
        this.t.setOnClickListener(this.u);
        Intent intent = getIntent();
        this.c.setText(intent.getExtras().getString(a, ""));
        this.d.setText(intent.getExtras().getString(b, ""));
    }
}
