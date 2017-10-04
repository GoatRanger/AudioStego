package dji.pilot.fpv.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetIoc.MODE;
import dji.midware.data.model.P3.DataFlycSetIoc;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.DJIEditText;
import dji.publics.DJIUI.DJIGridView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.text.DecimalFormat;

public class g extends dji.pilot.publics.objects.c {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final b[] e = new b[]{new b(0, R.drawable.ioc_mode_orientation_lock, R.string.ioc_mode_orientation_lock), new b(1, 0, R.string.ioc_mode_orientation_lock_desc), new b(1, 0, 0), new b(1, 0, 0)};
    private static final int w = 1;
    private float f;
    private float[] g;
    private int h;
    private DJIImageView i;
    private DJITextView j;
    private DJITextView k;
    private DJIImageView l;
    private DJIGridView m;
    private String[] n;
    private a o;
    private OnClickListener p;
    private OnClickListener q;
    private int r;
    private int s;
    private OnEditorActionListener t;
    private TextWatcher u;
    private DecimalFormat v;
    private Handler x;

    private final class a extends BaseAdapter {
        final /* synthetic */ g a;
        private final LayoutInflater b;
        private final int c;

        public a(g gVar, Context context) {
            this.a = gVar;
            this.b = LayoutInflater.from(context);
            this.c = (gVar.n.length - 1) / 2;
        }

        public int getCount() {
            return g.e.length;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemViewType(int i) {
            return g.e[i].a;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            int itemViewType = getItemViewType(i);
            if (view == null) {
                c cVar2 = new c();
                if (itemViewType == 0) {
                    view = this.b.inflate(R.layout.fpv_ioc_mode_btn_item, null);
                    cVar2.a = (DJIImageView) view.findViewById(R.id.a2v);
                    cVar2.b = (DJITextView) view.findViewById(R.id.a2w);
                    cVar2.c = (DJIImageView) view.findViewById(R.id.a2x);
                    cVar2.h = (DJITextView) view.findViewById(R.id.a2y);
                    cVar2.a.setOnClickListener(this.a.q);
                    cVar2.h.setOnClickListener(this.a.p);
                } else if (1 == itemViewType) {
                    view = this.b.inflate(R.layout.fpv_ioc_mode_tv_item, null);
                    cVar2.a = (DJIImageView) view.findViewById(R.id.a2v);
                    cVar2.b = (DJITextView) view.findViewById(R.id.a2w);
                } else if (2 == itemViewType) {
                    view = this.b.inflate(R.layout.fpv_ioc_mode_et_item, null);
                    cVar2.a = (DJIImageView) view.findViewById(R.id.a2v);
                    cVar2.b = (DJITextView) view.findViewById(R.id.a2w);
                    cVar2.c = (DJIImageView) view.findViewById(R.id.a2x);
                    cVar2.d = (DJILinearLayout) view.findViewById(R.id.a2z);
                    cVar2.e = (DJIEditText) view.findViewById(R.id.a31);
                    cVar2.f = (DJITextView) view.findViewById(R.id.a30);
                    cVar2.g = (DJITextView) view.findViewById(R.id.a32);
                    cVar2.e.addTextChangedListener(this.a.u);
                    cVar2.e.setOnEditorActionListener(this.a.t);
                    cVar2.a.setOnClickListener(this.a.q);
                }
                view.setTag(cVar2);
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new AbsListView.LayoutParams(-1, this.a.s);
            } else {
                layoutParams.height = this.a.s;
            }
            view.setLayoutParams(layoutParams);
            if (i / 2 != this.c) {
                if (i % 2 == 0) {
                    view.setBackgroundResource(R.drawable.bottomright_line_bg);
                } else {
                    view.setBackgroundResource(R.drawable.bottom_line_bg);
                }
            } else if (i % 2 == 0) {
                view.setBackgroundResource(R.drawable.right_line_bg);
            }
            b bVar = g.e[i];
            if (itemViewType == 0) {
                cVar.a.setTag(String.valueOf(i));
                if (bVar.b != 0) {
                    cVar.a.show();
                    cVar.a.setBackgroundResource(bVar.b);
                } else {
                    cVar.a.go();
                }
                if (i == this.a.r) {
                    cVar.c.setVisibility(0);
                } else {
                    cVar.c.setVisibility(8);
                }
            } else if (2 == itemViewType) {
                cVar.d.show();
                cVar.f.setText(this.a.b(bVar.b));
                cVar.g.setText(this.a.h);
                cVar.e.setTag(String.valueOf(bVar.b));
                cVar.e.setText(this.a.b(this.a.f));
                cVar.a.setTag(String.valueOf(i));
                if (bVar.b != 0) {
                    cVar.a.show();
                    cVar.a.setBackgroundResource(bVar.b);
                } else {
                    cVar.a.go();
                }
                if (i == this.a.r) {
                    cVar.c.setVisibility(0);
                } else {
                    cVar.c.setVisibility(8);
                }
            }
            if (bVar.c == 0) {
                cVar.b.go();
            } else {
                cVar.b.show();
                cVar.b.setText(bVar.c);
            }
            return view;
        }
    }

    private static final class b {
        public int a;
        public int b;
        public int c;

        private b(int i, int i2, int i3) {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    private static final class c {
        public DJIImageView a;
        public DJITextView b;
        public DJIImageView c;
        public DJILinearLayout d;
        public DJIEditText e;
        public DJITextView f;
        public DJITextView g;
        public DJITextView h;

        private c() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
        }
    }

    public g(Context context) {
        this(context, R.style.c6);
    }

    public g(Context context, int i) {
        super(context, i);
        this.f = 2.0f;
        this.g = null;
        this.h = R.string.fpv_gensetting_metric;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = -1;
        this.s = 0;
        this.t = null;
        this.u = null;
        this.v = new DecimalFormat("#.#");
        this.x = new Handler(new Callback(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                switch (message.what) {
                }
                return false;
            }
        });
        d();
    }

    protected void onStart() {
        super.onStart();
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onStop() {
        dji.thirdparty.a.c.a().d(this);
        super.onStop();
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        this.r = dataOsdGetPushHome.getIOCMode().value() - 1;
        this.j.setText(this.N.getString(R.string.ioc_operating_mode, new Object[]{Integer.valueOf(this.r + 1)}));
        this.o.notifyDataSetChanged();
    }

    private void c() {
        this.u = new TextWatcher(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.a.a(charSequence, i, i2, i3);
            }

            public void afterTextChanged(Editable editable) {
            }
        };
        this.t = new OnEditorActionListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (6 == i) {
                    this.a.a(textView);
                }
                return false;
            }
        };
    }

    private void d() {
        c();
        setContentView(R.layout.fpv_ioc_mode_view);
        this.i = (DJIImageView) findViewById(R.id.sg);
        this.j = (DJITextView) findViewById(R.id.sh);
        this.k = (DJITextView) findViewById(R.id.sj);
        this.l = (DJIImageView) findViewById(R.id.sl);
        this.m = (DJIGridView) findViewById(R.id.a33);
        this.p = new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.sl == id) {
                    this.a.f();
                } else if (R.id.a2y == id) {
                    this.a.a(0);
                }
            }
        };
        this.q = new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    final int parseInt = Integer.parseInt((String) view.getTag());
                    DataFlycSetIoc.getInstance().a(MODE.find(parseInt + 1)).start(new d(this) {
                        final /* synthetic */ AnonymousClass5 b;

                        public void onSuccess(Object obj) {
                            this.b.a.r = parseInt;
                            this.b.a.x.sendEmptyMessage(1);
                            DJILogHelper.getInstance().LOGE("", "ioc success", false, true);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            DJILogHelper.getInstance().LOGE("", "ioc failure", false, true);
                        }
                    });
                } catch (NumberFormatException e) {
                }
            }
        };
        this.i.go();
        this.i.setImageResource(R.drawable.warning_icon_gray);
        this.j.go();
        this.j.setTextColor(this.N.getResources().getColor(R.color.ov));
        this.k.setText(R.string.ioc_settings);
        this.n = getContext().getResources().getStringArray(R.array.cm);
        this.o = new a(this, getContext());
        this.m.setAdapter(this.o);
        this.l.setOnClickListener(this.p);
    }

    private void a(int i) {
        dji.pilot.publics.widget.b.a(getContext(), this.N.getString(R.string.app_tip), this.N.getString(R.string.fpv_flyc_ioc_reset_confirm), this.N.getString(R.string.app_cancel), new DialogInterface.OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, this.N.getString(R.string.app_enter), new DialogInterface.OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.HOMEPOINT_LOC).start(new d(this) {
                    final /* synthetic */ AnonymousClass7 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.x.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                Toast.makeText(this.a.a.a.getContext(), R.string.fpv_flyc_ioc_reset_success, 0).show();
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.a.x.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                Toast.makeText(this.a.a.a.getContext(), R.string.fpv_flyc_ioc_reset_fail, 0).show();
                            }
                        });
                    }
                });
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void a(CharSequence charSequence, int i, int i2, int i3) {
    }

    private void a(TextView textView) {
        Object tag = textView.getTag();
        if (tag instanceof String) {
            try {
                if (Integer.parseInt((String) tag) == R.drawable.ioc_mode_limit_lock) {
                    float b = b(textView);
                    if (this.g[0] > b || b > this.g[1]) {
                        Toast.makeText(this.N, "param interval check fail!", 0).show();
                    } else {
                        DJIGenSettingDataManager.getInstance().d(b);
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    private float b(TextView textView) {
        String charSequence = textView.getText().toString();
        float f = -1.0f;
        if (!(charSequence == null || charSequence.isEmpty())) {
            try {
                f = Float.parseFloat(charSequence);
            } catch (Exception e) {
            }
        }
        return f;
    }

    private String b(float f) {
        return this.v.format((double) f);
    }

    private String b(int i) {
        String str = "";
        if (R.drawable.ioc_mode_limit_lock != i) {
            return str;
        }
        return this.N.getString(R.string.ioc_interval, new Object[]{Float.valueOf(this.g[0]), Float.valueOf(this.g[1])});
    }

    protected void onCreate(Bundle bundle) {
        a(DJIBaseActivity.screenWidth - dji.pilot.fpv.model.b.a(this.N, R.dimen.ix), DJIBaseActivity.screenHeight - dji.pilot.fpv.model.b.a(this.N, R.dimen.iv), 0, 17, true, true);
        this.s = ((this.P - dji.pilot.fpv.model.b.a(this.N, R.dimen.iw)) - dji.pilot.fpv.model.b.a(this.N, R.dimen.nu)) / 2;
        this.o.notifyDataSetChanged();
    }

    private void e() {
        int v = DJIGenSettingDataManager.getInstance().v();
        this.g = DJIGenSettingDataManager.getInstance().y();
        if (v == 1) {
            this.h = R.string.fpv_gensetting_metric;
        } else {
            this.h = R.string.fpv_gensetting_foot;
        }
    }

    private void f() {
        dismiss();
    }

    public void show() {
        super.show();
        this.r = DataOsdGetPushHome.getInstance().getIOCMode().value() - 1;
        this.j.setText(this.N.getString(R.string.ioc_operating_mode, new Object[]{Integer.valueOf(this.r + 1)}));
        this.o.notifyDataSetChanged();
        e();
    }
}
