package dji.pilot2.academy.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.dji.frame.c.c;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.f.a;

public class DJINewAcademyFeedback extends DJIActivityNoFullScreen {
    private DJITextView a;
    private DJIStateImageView b;
    private OnClickListener c = null;
    private EditText d;
    private EditText t;
    private DJIStateTextView u;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_academy_feedback);
        a();
        b();
        f();
    }

    protected void a() {
        this.a = (DJITextView) findViewById(R.id.c43);
        this.b = (DJIStateImageView) findViewById(R.id.c42);
        this.u = (DJIStateTextView) findViewById(R.id.c5q);
        this.d = (EditText) findViewById(R.id.c5c);
        this.t = (EditText) findViewById(R.id.c5p);
    }

    protected void b() {
        this.c = new OnClickListener(this) {
            final /* synthetic */ DJINewAcademyFeedback a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.c42:
                        this.a.finish();
                        return;
                    case R.id.c5q:
                        String obj = this.a.d.getText().toString();
                        String obj2 = this.a.t.getText().toString();
                        if (obj == null || obj2 == null || obj == "" || obj2 == "") {
                            this.a.finish();
                            return;
                        }
                        DJILogHelper.getInstance().LOGI("bob", "content = " + obj + "contact =" + obj2);
                        c.b(this.a.getApplicationContext()).b(k.e(obj, obj2), new a<String>(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(String str) {
                                DJILogHelper.getInstance().LOGI("bob", "onSuccess " + str);
                            }

                            public void a(boolean z) {
                                DJILogHelper.getInstance().LOGI("bob", "onStart " + z);
                            }

                            public void a(long j, long j2) {
                                DJILogHelper.getInstance().LOGI("bob", "onLoading " + j2);
                            }

                            public void a(Throwable th, int i, String str) {
                                DJILogHelper.getInstance().LOGI("bob", "onFailure " + str);
                            }
                        });
                        this.a.finish();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    protected void f() {
        this.a.setOnClickListener(this.c);
        this.a.setText(R.string.v2_academy_feedback);
        this.b.setOnClickListener(this.c);
        this.u.setOnClickListener(this.c);
    }

    public void onHandlerClick(View view) {
        Toast.makeText(this, "id = " + view.getId(), 0).show();
        DJIStateTextView dJIStateTextView = (DJIStateTextView) view;
        switch (view.getId()) {
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(motionEvent);
    }
}
