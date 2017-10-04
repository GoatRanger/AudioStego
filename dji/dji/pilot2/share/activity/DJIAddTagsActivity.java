package dji.pilot2.share.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.Editable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dji.frame.c.h;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.util.i;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIEditText;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.e.a;
import dji.thirdparty.afinal.c;
import dji.thirdparty.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DJIAddTagsActivity extends DJIActivityNoFullScreen {
    public static final int a = 3;
    private int A = 0;
    private int B = 0;
    private int C = 0;
    private ArrayList<String> D;
    private TextView E;
    private Handler F;
    private DisplayMetrics G;
    private ProgressBar H;
    private OnClickListener I = new OnClickListener(this) {
        final /* synthetic */ DJIAddTagsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.c6r:
                    this.a.finish();
                    return;
                case R.id.c6x:
                    String str = "";
                    if (this.a.D.size() > 0) {
                        int i = 0;
                        while (i < this.a.D.size()) {
                            String str2 = str + ((String) this.a.D.get(i)) + ",";
                            i++;
                            str = str2;
                        }
                    }
                    Intent intent = new Intent();
                    if (!(str == null || str.isEmpty())) {
                        str = str.substring(0, str.length() - 1);
                    }
                    intent.putExtra("tags", str);
                    this.a.setResult(3, intent);
                    this.a.finish();
                    return;
                default:
                    return;
            }
        }
    };
    private TextWatcher J = new TextWatcher(this) {
        final /* synthetic */ DJIAddTagsActivity a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String obj = this.a.b.getText().toString();
            if (obj.length() > 1) {
                if (obj.charAt(obj.length() - 1) == '\n' && obj.length() > 2) {
                    if (this.a.D.size() > 0) {
                        for (int i4 = 0; i4 < this.a.D.size(); i4++) {
                            if (obj.substring(1, obj.length() - 1).equalsIgnoreCase((String) this.a.D.get(i4))) {
                                this.a.b.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                                this.a.b.setSelection(1);
                                return;
                            }
                        }
                    }
                    this.a.D.add(obj.substring(1, obj.length() - 1));
                    this.a.b.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    this.a.b.setSelection(1);
                    if (obj.length() > 0) {
                        View a = this.a.a(obj, this.a.y, false);
                        a.setOnLongClickListener(new OnLongClickListener(this) {
                            final /* synthetic */ AnonymousClass6 a;

                            {
                                this.a = r1;
                            }

                            public boolean onLongClick(View view) {
                                this.a.a.c.removeView(view);
                                return true;
                            }
                        });
                        this.a.a(obj, a);
                    }
                }
            } else if (!MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR.equals(obj) && this.a.c != null) {
                this.a.b.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                this.a.b.setSelection(1);
                if (this.a.t.getChildCount() > 1) {
                    if (this.a.c.getChildCount() <= 1) {
                        this.a.c.removeView(this.a.b);
                        this.a.t.removeViewAt(this.a.t.getChildCount() - 1);
                        this.a.h();
                        this.a.b.requestFocus();
                        if (this.a.u) {
                            this.a.i();
                            return;
                        }
                    } else if (this.a.u) {
                        this.a.i();
                        return;
                    }
                    this.a.D.remove(((TextView) this.a.c.getChildAt(this.a.c.getChildCount() - 2)).getText());
                    this.a.c.removeViewAt(this.a.c.getChildCount() - 2);
                } else if (this.a.c.getChildCount() > 1) {
                    if (this.a.u) {
                        this.a.i();
                        return;
                    } else {
                        this.a.D.remove(((TextView) this.a.c.getChildAt(this.a.c.getChildCount() - 2)).getText());
                        this.a.c.removeViewAt(this.a.c.getChildCount() - 2);
                    }
                }
                if (this.a.c.getChildCount() < 2) {
                    this.a.A = 0;
                } else {
                    this.a.A = ((int) this.a.c.getChildAt(this.a.c.getChildCount() - 2).getX()) + this.a.c.getChildAt(this.a.c.getChildCount() - 2).getWidth();
                }
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    };
    private DJIEditText b = null;
    private DJILinearLayout c = null;
    private DJILinearLayout d = null;
    private DJILinearLayout t = null;
    private boolean u = false;
    private String v = "";
    private c w;
    private ArrayList<String> x;
    private int y = 0;
    private int z = 1;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_add_tags);
        a();
        b();
        this.F = new Handler();
    }

    private void a(Intent intent) {
        if (intent != null) {
            int indexOf;
            this.D.clear();
            String stringExtra = intent.getStringExtra("tags");
            if (!(stringExtra == null || stringExtra.isEmpty())) {
                while (true) {
                    indexOf = stringExtra.indexOf(44);
                    if (indexOf == -1) {
                        break;
                    }
                    this.D.add(stringExtra.substring(0, indexOf));
                    stringExtra = stringExtra.substring(indexOf + 1);
                }
                this.D.add(stringExtra);
            }
            if (this.D.size() > 0) {
                for (indexOf = 0; indexOf < this.D.size(); indexOf++) {
                    a((String) this.D.get(indexOf), a((String) this.D.get(indexOf), this.z, false));
                }
            }
        }
    }

    private void a() {
        this.G = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.G);
        this.B = getWindowManager().getDefaultDisplay().getWidth();
        this.b = (DJIEditText) findViewById(R.id.c6u);
        this.b.addTextChangedListener(this.J);
        this.b.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        this.b.setSelection(1);
        this.H = (ProgressBar) findViewById(R.id.c6w);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.B -= a.b(this, 40.0f) * 2;
            this.C = (int) this.b.getPaint().measureText("ab", 0, 2);
        } else {
            this.B -= a.b(this, 20.0f) * 2;
            this.C = (int) this.b.getPaint().measureText("ab", 0, 2);
        }
        this.A = 0;
        findViewById(R.id.c6r).setOnClickListener(this.I);
        findViewById(R.id.c6x).setOnClickListener(this.I);
        this.c = (DJILinearLayout) findViewById(R.id.c6t);
        this.d = (DJILinearLayout) findViewById(R.id.c6v);
        this.t = (DJILinearLayout) findViewById(R.id.c6s);
        this.x = new ArrayList();
        this.D = new ArrayList();
    }

    private void b() {
        this.w = com.dji.frame.c.c.b(this);
        String language = Locale.getDefault().getLanguage();
        if (language != null && language.equalsIgnoreCase("zh")) {
            this.w.a("Accept-Language", dji.pilot2.publics.b.a.p);
        }
        this.w.a(k.e(), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIAddTagsActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.H.setVisibility(0);
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (str != null) {
                    try {
                        this.a.x = (ArrayList) h.a(str, new TypeToken<List<String>>(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.a.H.setVisibility(8);
                if (this.a.x != null) {
                    i.a(this.a.getApplicationContext(), "default_netlist", str);
                    this.a.f();
                }
            }

            public void a(Throwable th, int i, String str) {
                this.a.H.setVisibility(8);
                String b = i.b(this.a.getApplicationContext(), "default_netlist", "");
                if (b != null && !b.isEmpty()) {
                    this.a.x = (ArrayList) h.a(b, new TypeToken<List<String>>(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }
                    });
                    this.a.f();
                }
            }
        });
    }

    private void f() {
        int dimension = (int) getResources().getDimension(R.dimen.g1);
        if (this.x != null && this.x.size() > 0) {
            int size = this.x.size();
            View dJILinearLayout = new DJILinearLayout(this);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) getResources().getDimension(R.dimen.gc));
            layoutParams.topMargin = 10;
            dJILinearLayout.setLayoutParams(layoutParams);
            this.d.addView(dJILinearLayout);
            int i = 0;
            int i2 = 0;
            while (i < size) {
                int i3;
                final String str = (String) this.x.get(i);
                if (str == null) {
                    i3 = i2;
                } else {
                    View dJILinearLayout2;
                    View a = a((String) this.x.get(i), this.z, true);
                    a.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ DJIAddTagsActivity b;

                        public void onClick(View view) {
                            if (this.b.D.size() > 0 && str.length() > 0) {
                                int i = 0;
                                while (i < this.b.D.size()) {
                                    if (!str.equalsIgnoreCase((String) this.b.D.get(i))) {
                                        i++;
                                    } else {
                                        return;
                                    }
                                }
                            }
                            this.b.D.add(str);
                            this.b.a(str, this.b.a(str, this.b.z, false));
                        }
                    });
                    TextPaint paint = a.getPaint();
                    int desiredWidth = (int) Layout.getDesiredWidth(str, 0, str.length(), paint);
                    int measureText = (int) paint.measureText(str, 0, str.length());
                    DJILogHelper.getInstance().LOGI("bob", "1 viewWidth = " + measureText);
                    i3 = ((dimension * 2) + 10) + measureText;
                    measureText = i2 + i3;
                    if (measureText >= this.B) {
                        dJILinearLayout2 = new DJILinearLayout(this);
                        dJILinearLayout2.setLayoutParams(layoutParams);
                        this.d.addView(dJILinearLayout2);
                        i3 += 10;
                    } else {
                        i3 = measureText;
                        dJILinearLayout2 = dJILinearLayout;
                    }
                    dJILinearLayout2.addView(a);
                    dJILinearLayout2.requestLayout();
                    dJILinearLayout = dJILinearLayout2;
                }
                i++;
                i2 = i3;
            }
        }
    }

    protected void onResume() {
        super.onResume();
        this.F.postDelayed(new Runnable(this) {
            final /* synthetic */ DJIAddTagsActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a(this.a.getIntent());
            }
        }, 200);
        System.putInt(getContentResolver(), "accelerometer_rotation", 0);
    }

    protected void onPause() {
        super.onPause();
        System.putInt(getContentResolver(), "accelerometer_rotation", 1);
    }

    private TextView a(String str, int i, boolean z) {
        TextView textView = new TextView(getApplicationContext());
        if (i == this.z) {
            textView.setText(str);
            textView.setTag(str);
        }
        if (i == this.y) {
            textView.setText(str.subSequence(1, str.length() - 1));
            textView.setTag(str.subSequence(1, str.length() - 1));
        }
        int dimension = (int) getResources().getDimension(R.dimen.g1);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, (int) getResources().getDimension(R.dimen.g8));
        layoutParams.gravity = 16;
        layoutParams.leftMargin = 10;
        layoutParams.topMargin = 5;
        layoutParams.bottomMargin = 5;
        textView.setSingleLine();
        textView.setLayoutParams(layoutParams);
        textView.setClickable(true);
        textView.setGravity(16);
        if (z) {
            textView.setBackground(getResources().getDrawable(R.drawable.v2_tags_gary_background));
            textView.setTextColor(-6579301);
        } else {
            textView.setBackground(getResources().getDrawable(R.drawable.v2_tags_background));
            textView.setTextColor(getResources().getColor(R.color.om));
        }
        textView.setPadding(dimension, 5, dimension, 5);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIAddTagsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.u) {
                    this.a.v = (String) view.getTag();
                    this.a.u = true;
                    this.a.E = (TextView) view;
                    this.a.E.setBackground(this.a.getResources().getDrawable(R.drawable.v2_tags_background_pressed));
                } else if (this.a.E == null || ((String) this.a.E.getTag()).equalsIgnoreCase((String) view.getTag())) {
                    this.a.E.setBackground(this.a.getResources().getDrawable(R.drawable.v2_tags_background_normal));
                    this.a.E = null;
                    this.a.u = false;
                } else {
                    this.a.E.setBackground(this.a.getResources().getDrawable(R.drawable.v2_tags_background_normal));
                    this.a.E = (TextView) view;
                    this.a.E.setBackground(this.a.getResources().getDrawable(R.drawable.v2_tags_background_pressed));
                }
            }
        });
        return textView;
    }

    private void a(String str) {
        int dimension = (int) getResources().getDimension(R.dimen.g1);
        dimension = (int) Layout.getDesiredWidth(str, 0, str.length(), ((TextView) this.c.getChildAt(0)).getPaint());
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.A = (int) (((((double) dimension) * 1.6d) + ((double) (this.G.density * 20.0f))) + ((double) this.A));
        } else {
            this.A = (int) (((((double) dimension) * 1.3d) + ((double) (this.G.density * 20.0f))) + ((double) this.A));
        }
        if (this.B - this.A < this.C) {
            this.c.removeView(this.b);
            g();
            h();
            this.A = 0;
        }
    }

    private void a(String str, View view) {
        int measureText = ((int) ((TextView) this.c.getChildAt(0)).getPaint().measureText(str, 0, str.length())) + ((((int) getResources().getDimension(R.dimen.g1)) * 2) + 10);
        DJILogHelper.getInstance().LOGI("bob", "judgeTheSpaceEnoughable width=" + measureText);
        this.A += measureText;
        if (this.B < this.A) {
            this.c.removeView(this.b);
            g();
            h();
            this.c.addView(view, this.c.getChildCount() - 1);
            this.c.requestLayout();
            this.A = measureText;
            this.b.requestFocus();
        } else if (this.A >= this.B || this.A + this.C <= this.B) {
            this.c.addView(view, this.c.getChildCount() - 1);
            this.c.requestLayout();
        } else {
            this.c.addView(view, this.c.getChildCount() - 1);
            this.c.removeView(this.b);
            this.c.requestFocus();
            g();
            h();
            this.c.requestLayout();
            this.A = 0;
            this.b.requestFocus();
        }
    }

    private void g() {
        View dJILinearLayout = new DJILinearLayout(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = 10;
        layoutParams.bottomMargin = 10;
        dJILinearLayout.setLayoutParams(layoutParams);
        this.t.addView(dJILinearLayout);
        this.t.requestLayout();
    }

    private void h() {
        this.c = (DJILinearLayout) this.t.getChildAt(this.t.getChildCount() - 1);
        this.c.addView(this.b);
    }

    private void i() {
        this.D.remove(this.v);
        this.E.setText(((TextView) this.c.getChildAt(0)).getText());
        this.E.setBackground(getResources().getDrawable(R.drawable.v2_tags_background_normal));
        this.c.removeViewAt(0);
        this.E = null;
        this.u = false;
        if (this.c.getChildCount() < 2) {
            this.A = 0;
        } else {
            this.A = ((int) this.c.getChildAt(this.c.getChildCount() - 2).getX()) + this.c.getChildAt(this.c.getChildCount() - 2).getWidth();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(motionEvent);
    }
}
