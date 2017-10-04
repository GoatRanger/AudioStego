package dji.setting.ui.rc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import dji.apppublic.reflect.AppPublicReflect;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataRcDeleteMaster;
import dji.midware.data.model.P3.DataRcDeleteSlave;
import dji.midware.data.model.P3.DataRcGetConnectMaster;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetName;
import dji.midware.data.model.P3.DataRcGetPassword;
import dji.midware.data.model.P3.DataRcGetSearchMasters;
import dji.midware.data.model.P3.DataRcGetSearchMode;
import dji.midware.data.model.P3.DataRcGetSlaveList;
import dji.midware.data.model.P3.DataRcGetSlaveList.RcModel;
import dji.midware.data.model.P3.DataRcGetToggle;
import dji.midware.data.model.P3.DataRcRequestGimbalCtrPermission;
import dji.midware.data.model.P3.DataRcRequestGimbalCtrPermission.RcGimbalError;
import dji.midware.data.model.P3.DataRcSetConnectMaster;
import dji.midware.data.model.P3.DataRcSetConnectMaster.RcConnectError;
import dji.midware.data.model.P3.DataRcSetMaster;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.model.P3.DataRcSetName;
import dji.midware.data.model.P3.DataRcSetPassword;
import dji.midware.data.model.P3.DataRcSetSearchMode;
import dji.midware.data.model.P3.DataRcSetToggle;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.DividerLinearLayout;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RcMasterSlaveView extends DividerLinearLayout implements OnClickListener {
    private static final String a = "RcMasterSlaveView";
    private Timer A;
    private LinearLayout B;
    private c C = new c();
    private TextView D;
    private Context E = null;
    private dji.setting.ui.widget.DJISpinnerButton.b F = new dji.setting.ui.widget.DJISpinnerButton.b(this) {
        final /* synthetic */ RcMasterSlaveView a;

        {
            this.a = r1;
        }

        public void onItemClick(int i) {
            if (i == 1) {
                if (!this.a.h || dji.pilot.c.d.b != MODE.a) {
                    e.a("FPV_RCSettings_SetRCStatus_SegmentControl_Master");
                    dji.setting.ui.widget.a.a(this.a.getContext(), R.string.setting_ui_rc_change_function, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.setRcMode(MODE.a);
                            dialogInterface.dismiss();
                        }
                    });
                }
            } else if (i == 2) {
                if (!this.a.h || dji.pilot.c.d.b != MODE.b) {
                    e.a("FPV_RCSettings_SetRCStatus_SegmentControl_Slave");
                    dji.setting.ui.widget.a.a(this.a.getContext(), R.string.setting_ui_rc_change_function, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.setRcMode(MODE.b);
                            dialogInterface.dismiss();
                        }
                    });
                }
            } else if (this.a.h) {
                e.a("FPV_RCSettings_SetRCStatus_SegmentControl_OFF");
                dji.setting.ui.widget.a.a(this.a.getContext(), R.string.setting_ui_rc_change_function, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a.a(false, dji.pilot.c.d.b);
                        dialogInterface.dismiss();
                    }
                });
            }
        }
    };
    private Handler G = new Handler(new Callback(this) {
        final /* synthetic */ RcMasterSlaveView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.c();
                    break;
                case 2:
                    this.a.p.setText(this.a.f);
                    break;
                case 3:
                    this.a.q.setText(String.format("%04d", new Object[]{Integer.valueOf(this.a.g)}));
                    break;
                case 4:
                    this.a.c.a();
                    break;
                case 5:
                    if (!this.a.h) {
                        this.a.y.setVisibility(8);
                        this.a.b(false);
                        this.a.a(false);
                        break;
                    }
                    this.a.y.setVisibility(0);
                    break;
                case 6:
                    this.a.l.clear();
                    this.a.c.a();
                    break;
                case 7:
                    this.a.setRcMode((MODE) message.obj);
                    break;
            }
            return false;
        }
    });
    private boolean H = false;
    private OnClickListener I = new OnClickListener(this) {
        final /* synthetic */ RcMasterSlaveView a;

        {
            this.a = r1;
        }

        public void onClick(final View view) {
            dji.setting.ui.widget.a.b(this.a.getContext(), R.string.setting_rc_delete_confirm, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ AnonymousClass7 b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    int i2 = ((RcModel) this.b.a.c.getItem(((Integer) view.getTag()).intValue())).id;
                    if (dji.pilot.c.d.b == MODE.a) {
                        DataRcDeleteSlave.getInstance().setID(i2).start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                Log.d("pm820", "delete rc onSuccess");
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                Log.d("pm820", "delete rc onFailure");
                            }
                        });
                    } else {
                        DataRcDeleteMaster.getInstance().setID(i2).start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                Log.d("pm820", "delete rc onSuccess");
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                Log.d("pm820", "delete rc onFailure");
                            }
                        });
                    }
                }
            });
        }
    };
    private OnItemClickListener J = new OnItemClickListener(this) {
        final /* synthetic */ RcMasterSlaveView a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i >= 0 && i < this.a.l.size() && dji.pilot.c.d.b != MODE.a) {
                this.a.e = i;
                this.a.f();
            }
        }
    };
    private OnCheckedChangeListener K = new OnCheckedChangeListener(this) {
        final /* synthetic */ RcMasterSlaveView a;

        {
            this.a = r1;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (compoundButton.equals(this.a.n)) {
                e.a("FPV_RCSettings_SlaveRCStatus_Button_SearchForMasterController");
                if (this.a.H != z) {
                    this.a.H = z;
                    this.a.b(z);
                }
            }
        }
    };
    private TextWatcher L = new TextWatcher(this) {
        final /* synthetic */ RcMasterSlaveView a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.p.isFocused() || !this.a.q.isFocused()) {
            }
        }

        public void afterTextChanged(Editable editable) {
            if (this.a.p.isFocused() || !this.a.q.isFocused()) {
            }
        }
    };
    private OnEditorActionListener M = new OnEditorActionListener(this) {
        final /* synthetic */ RcMasterSlaveView a;

        {
            this.a = r1;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (this.a.p.isFocused()) {
                e.a("FPV_RCSettings_RCStatus_TextField_RCName");
                final String charSequence = textView.getText().toString();
                if (charSequence != null && charSequence.length() > 0) {
                    DataRcSetName.getInstance().a(charSequence).start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass15 b;

                        public void onSuccess(Object obj) {
                            this.b.a.f = charSequence;
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.G.sendEmptyMessage(2);
                        }
                    });
                }
            } else if (this.a.q.isFocused()) {
                e.a("FPV_RCSettings_RCStatus_TextField_ConnectionPassword");
                final int b = this.a.b(textView.getText().toString());
                if (b >= 0) {
                    DataRcSetPassword.getInstance().a(b).start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass15 b;

                        public void onSuccess(Object obj) {
                            this.b.a.g = b;
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.G.sendEmptyMessage(3);
                        }
                    });
                }
            }
            return false;
        }
    };
    private OnFocusChangeListener N = new OnFocusChangeListener(this) {
        final /* synthetic */ RcMasterSlaveView a;

        {
            this.a = r1;
        }

        public void onFocusChange(View view, boolean z) {
            if (view.getId() == this.a.p.getId()) {
                if (!z) {
                    this.a.G.sendEmptyMessage(2);
                    this.a.p.setCursorVisible(false);
                }
            } else if (view.getId() == this.a.q.getId() && !z) {
                this.a.G.sendEmptyMessage(3);
                this.a.q.setCursorVisible(false);
            }
        }
    };
    private int[] b = new int[]{R.string.setting_ui_rc_close_mode, R.string.setting_ui_rc_host_mode_desc, R.string.setting_ui_rc_slaver_mode_desc};
    private b c;
    private RcModel d = new RcModel();
    private int e;
    private String f;
    private int g;
    private boolean h;
    private boolean i = true;
    private ArrayList<RcModel> l = new ArrayList(10);
    private TextView m = null;
    private Switch n;
    private LinearLayout o;
    private EditText p;
    private EditText q;
    private TextView r;
    private LinearLayout s;
    private DJISpinnerButton t;
    private TextView u;
    private ListView v;
    private EditText w;
    private LinearLayout x;
    private LinearLayout y;
    private Timer z;

    static /* synthetic */ class AnonymousClass17 {
        static final /* synthetic */ int[] a = new int[MODE.values().length];
        static final /* synthetic */ int[] b = new int[RcConnectError.values().length];
        static final /* synthetic */ int[] c = new int[p.values().length];
        static final /* synthetic */ int[] d = new int[RcGimbalError.values().length];

        static {
            try {
                d[RcGimbalError.Refused.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                d[RcGimbalError.TimeOut.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                d[RcGimbalError.Getted.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                c[p.b.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                c[p.a.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[RcConnectError.a.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[RcConnectError.b.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[RcConnectError.c.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[RcConnectError.d.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[MODE.a.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[MODE.b.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public class a implements InputFilter {
        final /* synthetic */ RcMasterSlaveView a;
        private int b;

        public a(RcMasterSlaveView rcMasterSlaveView, int i) {
            this.a = rcMasterSlaveView;
            this.b = i;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            char[] toCharArray = charSequence.toString().toCharArray();
            byte[] bytes = charSequence.toString().getBytes(Charset.forName("UTF-8"));
            byte[] bytes2 = spanned.toString().getBytes(Charset.forName("UTF-8"));
            int length = bytes.length;
            int length2 = bytes2.length;
            if (length + length2 <= this.b) {
                return charSequence;
            }
            length = 0;
            while (length < toCharArray.length) {
                length2 += a(toCharArray[length]).length;
                if (length2 > this.b) {
                    break;
                }
                length++;
            }
            if (length > 0) {
                return charSequence.subSequence(i, length);
            }
            return "";
        }

        private byte[] a(char c) {
            Charset forName = Charset.forName("UTF-8");
            CharBuffer allocate = CharBuffer.allocate(1);
            allocate.put(c);
            allocate.flip();
            return forName.encode(allocate).array();
        }
    }

    private final class b extends BaseAdapter {
        final /* synthetic */ RcMasterSlaveView a;
        private final LayoutInflater b;
        private int c;
        private int d;

        public b(RcMasterSlaveView rcMasterSlaveView, Context context) {
            this.a = rcMasterSlaveView;
            this.b = LayoutInflater.from(context);
        }

        public void a() {
            int count = getCount();
            if (count != this.d) {
                this.d = count;
                if (this.c == 0) {
                    this.c = (int) this.a.getContext().getResources().getDimension(R.dimen.dp_30);
                }
                LayoutParams layoutParams = this.a.v.getLayoutParams();
                layoutParams.height = (this.c * count) + (this.a.v.getDividerHeight() * (count - 1));
                this.a.v.setLayoutParams(layoutParams);
                DJILogHelper.getInstance().LOGD("", "curCount=" + count + " height=" + this.c, false, true);
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.a.l.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= this.a.l.size()) {
                return null;
            }
            return (RcModel) this.a.l.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            d dVar;
            int i2 = 0;
            if (view == null) {
                view = this.b.inflate(R.layout.setting_ui_rc_master_slave_item, null);
                view.setLayoutParams(new AbsListView.LayoutParams(-1, this.c));
                dVar = new d();
                dVar.b = (ImageView) view.findViewById(R.id.fpv_rcsetting_rcitem_select_img);
                dVar.c = (ImageView) view.findViewById(R.id.fpv_rcsetting_rcitem_mode_img);
                dVar.d = (TextView) view.findViewById(R.id.fpv_rcsetting_rcitem_name_tv);
                dVar.e = (TextView) view.findViewById(R.id.fpv_rcsetting_rcitem_signal);
                dVar.a = (ImageView) view.findViewById(R.id.fpv_rcsetting_rcitem_pitch_img);
                dVar.f = (TextView) view.findViewById(R.id.fpv_rc_setting_delete_rc);
                dVar.f.setTag(Integer.valueOf(i));
                dVar.f.setOnClickListener(this.a.I);
                view.setTag(dVar);
            } else {
                dVar = (d) view.getTag();
            }
            RcModel rcModel = (RcModel) getItem(i);
            if (rcModel != null) {
                dVar.d.setText(rcModel.name);
                dVar.e.setText(String.format("%d%%", new Object[]{Integer.valueOf(rcModel.quality)}));
                ImageView imageView;
                if (dji.pilot.c.d.b == MODE.a) {
                    int i3;
                    dVar.b.setVisibility(4);
                    imageView = dVar.a;
                    if (rcModel.pitch) {
                        i3 = 0;
                    } else {
                        i3 = 4;
                    }
                    imageView.setVisibility(i3);
                    dVar.c.setBackgroundResource(R.drawable.setting_ui_rc_slaver_icon);
                } else {
                    dVar.a.setVisibility(4);
                    imageView = dVar.b;
                    if (rcModel.id != this.a.d.id) {
                        i2 = 4;
                    }
                    imageView.setVisibility(i2);
                    dVar.c.setBackgroundResource(R.drawable.setting_ui_rc_slaver_icon);
                }
            }
            return view;
        }
    }

    public static class c {
        public MODE a;
        public boolean b;
        public boolean c;
    }

    private static final class d {
        public ImageView a;
        public ImageView b;
        public ImageView c;
        public TextView d;
        public TextView e;
        public TextView f;

        private d() {
        }
    }

    public RcMasterSlaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = context;
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_rc_master_slave);
        if (!isInEditMode()) {
            this.m = (TextView) findViewById(R.id.fpv_rcsetting_status_tv);
            this.y = (LinearLayout) findViewById(R.id.rc_setting_toogle_ly);
            this.B = (LinearLayout) findViewById(R.id.rc_setting_perm_ly);
            this.x = (LinearLayout) findViewById(R.id.rc_setting_search_ly);
            this.n = (Switch) findViewById(R.id.fpv_rcsetting_search_switch);
            this.o = (LinearLayout) findViewById(R.id.rc_setting_name_ly);
            this.p = (EditText) findViewById(R.id.fpv_rcsetting_name_et);
            this.q = (EditText) findViewById(R.id.fpv_rcsetting_connpwd_et);
            this.D = (TextView) findViewById(R.id.fpv_rcsetting_connpwd_desc_tv);
            this.r = (TextView) findViewById(R.id.fpv_rcsetting_rclist_title_tv);
            this.t = (DJISpinnerButton) findViewById(R.id.setting_ui_item_spinner_btn);
            this.s = (LinearLayout) findViewById(R.id.rc_setting_mode_ly);
            this.u = (TextView) findViewById(R.id.fpv_rcsetting_getperm);
            this.v = (ListView) findViewById(R.id.fpv_rcsetting_rclist_lv);
            this.t.setData(this.b, 0, this.F);
            this.u.setOnClickListener(this);
            this.p.setFilters(new InputFilter[]{new a(this, 6)});
            this.n.setOnCheckedChangeListener(this.K);
            this.p.setOnFocusChangeListener(this.N);
            this.p.addTextChangedListener(this.L);
            this.p.setOnEditorActionListener(this.M);
            this.q.setOnFocusChangeListener(this.N);
            this.q.addTextChangedListener(this.L);
            this.q.setOnEditorActionListener(this.M);
            this.v.setOnItemClickListener(this.J);
            this.c = new b(this, getContext());
            this.v.setAdapter(this.c);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().a(this);
            a(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        b(false);
        a(false);
        dji.thirdparty.a.c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void b() {
        DJILogHelper.getInstance().LOGD(a, "initData", false, true);
        DataRcGetToggle.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                Log.d("", "isOpen=onSuccess");
                this.a.G.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass18 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.h = DataRcGetToggle.getInstance().getIsOpen();
                        Log.d("", "isOpen=" + this.a.a.h);
                        this.a.a.G.sendEmptyMessage(5);
                        this.a.a.C.b = this.a.a.h;
                        dji.thirdparty.a.c.a().e(this.a.a.C);
                        this.a.a.getMode();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(RcMasterSlaveView.a, "get isOpen=onFailure" + aVar, false, true);
            }
        });
        DataRcGetName.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.f = DataRcGetName.getInstance().getName();
                this.a.G.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass19 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.G.sendEmptyMessage(2);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
        DataRcGetPassword.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.g = DataRcGetPassword.getInstance().getPw();
                this.a.G.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass20 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.G.sendEmptyMessage(3);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void getMode() {
        DJILogHelper.getInstance().LOGD(a, "getMode", false, true);
        DataRcGetMaster.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                MODE mode = DataRcGetMaster.getInstance().getMode();
                if (mode == dji.pilot.c.d.b) {
                    Log.d("", "rc mode 2=" + mode);
                    this.a.G.sendEmptyMessage(1);
                    return;
                }
                dji.pilot.c.d.b = mode;
                Log.d("", "rc mode=" + mode);
                this.a.G.sendEmptyMessage(1);
                this.a.C.a = dji.pilot.c.d.b;
                dji.thirdparty.a.c.a().e(this.a.C);
                dji.thirdparty.a.c.a().e(dji.pilot.c.d.b);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "get is master=onFailure" + aVar, false, true);
            }
        });
    }

    private void c() {
        if (this.h) {
            switch (AnonymousClass17.a[dji.pilot.c.d.b.ordinal()]) {
                case 1:
                    this.t.setIndex(1);
                    break;
                case 2:
                    this.t.setIndex(2);
                    break;
                default:
                    this.t.setIndex(1);
                    break;
            }
            MarginLayoutParams marginLayoutParams;
            if (dji.pilot.c.d.b == MODE.a) {
                marginLayoutParams = (MarginLayoutParams) this.o.getLayoutParams();
                marginLayoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.dp_6);
                this.o.setLayoutParams(marginLayoutParams);
                this.B.setVisibility(8);
                this.x.setVisibility(8);
                this.q.setVisibility(0);
                this.D.setVisibility(0);
                this.r.setText(R.string.setting_ui_rc_slave_rclist);
                b(false);
                a(true);
                return;
            }
            marginLayoutParams = (MarginLayoutParams) this.o.getLayoutParams();
            marginLayoutParams.bottomMargin = 0;
            this.o.setLayoutParams(marginLayoutParams);
            if (dji.pilot.c.d.b == MODE.c) {
                this.B.setVisibility(8);
            } else {
                this.B.setVisibility(0);
                marginLayoutParams = (MarginLayoutParams) this.B.getLayoutParams();
                marginLayoutParams.bottomMargin = 0;
                this.B.setLayoutParams(marginLayoutParams);
            }
            this.x.setVisibility(0);
            this.q.setVisibility(4);
            this.D.setVisibility(4);
            this.r.setText(R.string.setting_ui_rc_master_rclist);
            d();
            e();
            a(false);
            this.G.sendEmptyMessage(6);
            return;
        }
        this.t.setIndex(0);
    }

    private void d() {
        DataRcGetConnectMaster.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.d = DataRcGetConnectMaster.getInstance().getMaster();
                this.a.d.quality = 100;
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void e() {
        DataRcGetSearchMode.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.G.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass23 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.n.setChecked(DataRcGetSearchMode.getInstance().getIsOpen());
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void f() {
        final RcModel rcModel = (RcModel) this.c.getItem(this.e);
        this.w = new EditText(getContext());
        AlertDialog a = dji.setting.ui.widget.a.a(getContext());
        a.setTitle(rcModel.name);
        a.setView(this.w);
        a.setButton(-1, getResources().getString(17039379), new DialogInterface.OnClickListener(this) {
            final /* synthetic */ RcMasterSlaveView b;

            public void onClick(DialogInterface dialogInterface, int i) {
                int b = this.b.b(this.b.w.getEditableText().toString());
                if (b >= 0) {
                    final int i2 = rcModel.password;
                    rcModel.password = b;
                    DataRcSetConnectMaster.getInstance().a(rcModel).start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass24 b;

                        public void onSuccess(Object obj) {
                            this.b.b.d = rcModel;
                        }

                        public void onFailure(final dji.midware.data.config.P3.a aVar) {
                            rcModel.password = i2;
                            final RcConnectError a = DataRcSetConnectMaster.getInstance().a(aVar);
                            this.b.b.G.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 c;

                                public void run() {
                                    String str = "";
                                    switch (AnonymousClass17.b[a.ordinal()]) {
                                        case 1:
                                            str = this.c.b.b.E.getString(R.string.setting_ui_rc_pwd_error);
                                            break;
                                        case 2:
                                            str = this.c.b.b.E.getString(R.string.setting_ui_rc_host_refuse);
                                            break;
                                        case 3:
                                            str = this.c.b.b.E.getString(R.string.setting_ui_rc_slaver_limit);
                                            break;
                                        case 4:
                                            str = this.c.b.b.E.getString(R.string.setting_ui_rc_timeout);
                                            break;
                                        default:
                                            str = aVar.toString();
                                            break;
                                    }
                                    dji.setting.ui.widget.a.a(this.c.b.b.getContext(), str);
                                }
                            });
                        }
                    });
                    dialogInterface.dismiss();
                }
            }
        });
        a.setButton(-2, getResources().getString(17039369), new DialogInterface.OnClickListener(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        a.show();
    }

    private int b(String str) {
        if (str == null || str.equals("")) {
            Toast.makeText(getContext(), R.string.setting_ui_rc_pwd_warning, 0).show();
            return -1;
        }
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt >= 0 && parseInt <= 9999) {
                return parseInt;
            }
            Toast.makeText(getContext(), R.string.setting_ui_rc_pwd_warning, 0).show();
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    private void a(boolean z) {
        if (z) {
            if (this.z != null) {
                this.z.cancel();
            }
            this.z = new Timer();
            this.z.schedule(new TimerTask(this) {
                final /* synthetic */ RcMasterSlaveView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.getSlaveList();
                }
            }, 0, 600);
        } else if (this.z != null) {
            this.z.cancel();
        }
    }

    private void getSlaveList() {
        DataRcGetSlaveList.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(RcMasterSlaveView.a, "DataRcGetSlaveList success" + DataRcGetSlaveList.getInstance().getList().size(), false, true);
                this.a.setListData(DataRcGetSlaveList.getInstance().getList());
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(RcMasterSlaveView.a, "DataRcGetSlaveList false", false, true);
                this.a.setListData(null);
            }
        });
    }

    private void b(boolean z) {
        if (z) {
            DataRcSetSearchMode.getInstance().a(true).start(null);
            if (this.A != null) {
                this.A.cancel();
            }
            this.A = new Timer();
            this.A.schedule(new TimerTask(this) {
                final /* synthetic */ RcMasterSlaveView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.getMasterList();
                }
            }, 0, 1500);
            return;
        }
        DataRcSetSearchMode.getInstance().a(false).start(null);
        if (this.A != null) {
            this.A.cancel();
        }
    }

    private void getMasterList() {
        e();
        DataRcGetSearchMasters.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ RcMasterSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.setListData(DataRcGetSearchMasters.getInstance().getList());
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.setListData(null);
            }
        });
    }

    private void setListData(SparseArray<RcModel> sparseArray) {
        this.l.clear();
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                this.l.add(i, sparseArray.get(sparseArray.keyAt(i)));
            }
        }
        this.G.sendEmptyMessage(4);
    }

    public void onEventBackgroundThread(p pVar) {
        switch (AnonymousClass17.c[pVar.ordinal()]) {
            case 1:
                b();
                return;
            case 2:
                this.h = false;
                this.G.sendEmptyMessage(1);
                this.G.sendEmptyMessage(5);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.fpv_rcsetting_getperm) {
            e.a("FPV_RCSettings_SlaveRCStatus_Button_RequestControl");
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_rc_request_permission, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ RcMasterSlaveView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    DataRcRequestGimbalCtrPermission.getInstance().start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass9 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            AppPublicReflect.postErrorModel(R.string.setting_ui_rc_request_success, R.string.setting_ui_rc_request_success_desc, 0);
                            this.a.a.G.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    dji.setting.ui.widget.a.a(this.a.a.a.getContext(), R.string.setting_ui_rc_request_success);
                                }
                            });
                        }

                        public void onFailure(final dji.midware.data.config.P3.a aVar) {
                            final RcGimbalError error = DataRcRequestGimbalCtrPermission.getInstance().getError(aVar);
                            this.a.a.G.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 c;

                                public void run() {
                                    String str = "";
                                    switch (AnonymousClass17.d[error.ordinal()]) {
                                        case 1:
                                            str = this.c.a.a.E.getString(R.string.setting_ui_rc_host_refuse);
                                            break;
                                        case 2:
                                            str = this.c.a.a.E.getString(R.string.setting_ui_rc_timeout);
                                            break;
                                        case 3:
                                            str = this.c.a.a.E.getString(R.string.setting_ui_rc_has_permission);
                                            break;
                                        default:
                                            str = this.c.a.a.E.getString(dji.setting.a.a.a(aVar));
                                            break;
                                    }
                                    dji.setting.ui.widget.a.a(this.c.a.a.getContext(), str);
                                }
                            });
                        }
                    });
                    dialogInterface.dismiss();
                }
            });
        }
    }

    private void a(final boolean z, final MODE mode) {
        if (this.h != z) {
            DataRcSetToggle.getInstance().a(z).start(new dji.midware.e.d(this) {
                final /* synthetic */ RcMasterSlaveView c;

                public void onSuccess(Object obj) {
                    this.c.h = z;
                    this.c.G.sendEmptyMessage(5);
                    this.c.C.b = this.c.h;
                    dji.thirdparty.a.c.a().e(this.c.C);
                    if (this.c.h) {
                        this.c.G.sendMessage(this.c.G.obtainMessage(7, mode));
                    } else {
                        this.c.G.sendEmptyMessage(1);
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("", "set isopen=onFailure" + aVar, false, true);
                }
            });
        }
    }

    private void setRcMode(final MODE mode) {
        DJILogHelper.getInstance().LOGD("", "setRcMode isopen=" + this.h, false, true);
        if (this.h) {
            DataRcSetMaster.getInstance().a(mode).start(new dji.midware.e.d(this) {
                final /* synthetic */ RcMasterSlaveView b;

                public void onSuccess(Object obj) {
                    dji.pilot.c.d.b = mode;
                    this.b.G.sendEmptyMessage(1);
                    this.b.C.a = mode;
                    dji.thirdparty.a.c.a().e(this.b.C);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("", "get ismaster=onFailure" + aVar, false, true);
                }
            });
        } else {
            a(true, mode);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a(productType);
    }

    private void a(ProductType productType) {
        if (productType != null) {
            if (dji.pilot.publics.e.a.j(productType)) {
                setVisibility(0);
                b();
            } else if (dji.pilot.publics.e.a.b(productType)) {
                setVisibility(8);
            }
        }
    }
}
