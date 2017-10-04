package dji.pilot.fpv.view;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.dji.frame.c.l;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycPushRedundancyStatus.RedundancySwitchInfo;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.COLOR_STATUS;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.IMUStatus;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIRedundancySysController;
import dji.pilot.fpv.control.DJIRedundancySysController.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class DJIChecklistRedundancyHistoryView extends DJILinearLayout implements dji.pilot.fpv.view.DJIStageView.a {
    private DJITextView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJITextView d = null;
    private DJILinearLayout e = null;
    private DJILinearLayout f = null;
    private ListView g = null;
    private ListView h = null;
    private OnClickListener i = new OnClickListener(this) {
        final /* synthetic */ DJIChecklistRedundancyHistoryView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.w1:
                    this.a.a.setSelected(true);
                    this.a.b.setSelected(false);
                    this.a.c.setSelected(false);
                    this.a.d.setSelected(false);
                    this.a.e.show();
                    this.a.f.go();
                    this.a.j.notifyDataSetChanged();
                    return;
                case R.id.w2:
                    this.a.a.setSelected(false);
                    this.a.b.setSelected(true);
                    this.a.c.setSelected(false);
                    this.a.d.setSelected(false);
                    this.a.e.go();
                    this.a.f.show();
                    this.a.k.notifyDataSetChanged();
                    return;
                case R.id.w3:
                    this.a.a.setSelected(false);
                    this.a.b.setSelected(false);
                    this.a.c.setSelected(true);
                    this.a.d.setSelected(false);
                    this.a.e.go();
                    this.a.f.show();
                    this.a.k.notifyDataSetChanged();
                    return;
                case R.id.w4:
                    this.a.a.setSelected(false);
                    this.a.b.setSelected(false);
                    this.a.c.setSelected(false);
                    this.a.d.setSelected(true);
                    this.a.e.go();
                    this.a.f.show();
                    this.a.k.notifyDataSetChanged();
                    return;
                case R.id.w9:
                    DJIRedundancySysController.getInstance(this.a.getContext()).d();
                    DJIRedundancySysController.getInstance(this.a.getContext()).f();
                    DJIRedundancySysController.getInstance(this.a.getContext()).h();
                    DJIRedundancySysController.getInstance(this.a.getContext()).j();
                    this.a.k.notifyDataSetChanged();
                    this.a.j.notifyDataSetChanged();
                    return;
                default:
                    return;
            }
        }
    };
    private a j = null;
    private b k = null;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[COLOR_STATUS.values().length];

        static {
            try {
                a[COLOR_STATUS.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[COLOR_STATUS.e.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[COLOR_STATUS.d.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[COLOR_STATUS.g.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[COLOR_STATUS.c.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[COLOR_STATUS.f.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private class a extends BaseAdapter {
        LayoutInflater a = null;
        final /* synthetic */ DJIChecklistRedundancyHistoryView b;

        public a(DJIChecklistRedundancyHistoryView dJIChecklistRedundancyHistoryView) {
            this.b = dJIChecklistRedundancyHistoryView;
            this.a = LayoutInflater.from(dJIChecklistRedundancyHistoryView.getContext());
        }

        public int getCount() {
            return DJIRedundancySysController.getInstance(this.b.getContext()).c().size();
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEnabled(int i) {
            return false;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.a.inflate(R.layout.fpv_redundancy_switch_history_item, null);
            }
            List c = DJIRedundancySysController.getInstance(this.b.getContext()).c();
            if (i < DJIRedundancySysController.getInstance(this.b.getContext()).c().size()) {
                DJITextView dJITextView = (DJITextView) view.findViewById(R.id.aah);
                DJITextView dJITextView2 = (DJITextView) view.findViewById(R.id.aai);
                RedundancySwitchInfo redundancySwitchInfo = (RedundancySwitchInfo) c.get(i);
                Context context = this.b.getContext();
                c a = DJIRedundancySysController.a(this.b.getContext(), ((int) (redundancySwitchInfo.srcErrCode >> 8)) & 255, ((int) (redundancySwitchInfo.srcErrCode >> 24)) & 255);
                String str = "";
                if (a.d == null || redundancySwitchInfo.reqID != 2) {
                    DJILogHelper.getInstance().LOGD("", "RedundancySwitchInfo.srcErrCode=" + redundancySwitchInfo.srcErrCode, false, true);
                } else {
                    str = "(" + a.d.getUserErrTips() + ")";
                }
                String string = ((redundancySwitchInfo.srcErrCode >> 2) & 1) == 1 ? context.getString(R.string.fpv_redundancy_history_in_air) : "";
                StringBuilder stringBuilder = new StringBuilder();
                String string2 = context.getString(R.string.fpv_redundancy_switch_history_desc);
                Object[] objArr = new Object[4];
                objArr[0] = redundancySwitchInfo.reqID == 1 ? context.getString(R.string.fpv_redundancy_switch_type_user) : context.getString(R.string.fpv_redundancy_switch_type_system);
                objArr[1] = this.b.a(redundancySwitchInfo.srcImuIndex);
                objArr[2] = str;
                objArr[3] = this.b.a(redundancySwitchInfo.dstImuIndex);
                dJITextView.setText(stringBuilder.append(String.format(string2, objArr)).append(string).toString());
                dJITextView2.setText(DateFormat.format("yyyy-MM-dd HH:mm", redundancySwitchInfo.time).toString());
            }
            return view;
        }
    }

    private class b extends BaseAdapter {
        LayoutInflater a = null;
        final /* synthetic */ DJIChecklistRedundancyHistoryView b;

        public b(DJIChecklistRedundancyHistoryView dJIChecklistRedundancyHistoryView) {
            this.b = dJIChecklistRedundancyHistoryView;
            this.a = LayoutInflater.from(dJIChecklistRedundancyHistoryView.getContext());
        }

        public int getCount() {
            if (this.b.b.isSelected()) {
                return DJIRedundancySysController.getInstance(this.b.getContext()).e().size();
            }
            if (this.b.c.isSelected()) {
                return DJIRedundancySysController.getInstance(this.b.getContext()).g().size();
            }
            if (this.b.d.isSelected()) {
                return DJIRedundancySysController.getInstance(this.b.getContext()).i().size();
            }
            return 0;
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEnabled(int i) {
            return false;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            List list = null;
            if (view == null) {
                view = this.a.inflate(R.layout.fpv_redundancy_system_history_item, null);
            }
            DJITextView dJITextView = (DJITextView) view.findViewById(R.id.aak);
            DJITextView dJITextView2 = (DJITextView) view.findViewById(R.id.aal);
            DJIImageView dJIImageView = (DJIImageView) view.findViewById(R.id.aaj);
            if (this.b.b.isSelected()) {
                list = DJIRedundancySysController.getInstance(this.b.getContext()).e();
            } else if (this.b.c.isSelected()) {
                list = DJIRedundancySysController.getInstance(this.b.getContext()).g();
            } else if (this.b.d.isSelected()) {
                list = DJIRedundancySysController.getInstance(this.b.getContext()).i();
            }
            if (list != null && i < list.size()) {
                IMUStatus iMUStatus = (IMUStatus) list.get(i);
                c a = DJIRedundancySysController.a(this.b.getContext(), iMUStatus);
                String string = iMUStatus.isRealInAir ? this.b.getContext().getString(R.string.fpv_redundancy_history_in_air) : "";
                if (a.d != null && a.d.history_enable == 1) {
                    dJITextView.setText(String.format("[%s]%s", new Object[]{a.b, a.d.usr_err_tips}) + string);
                } else if (l.a(a.b)) {
                    dJITextView.setText(String.format("[%d]%d", new Object[]{Integer.valueOf(iMUStatus.devType), Integer.valueOf(iMUStatus.devErrCode)}) + string);
                } else {
                    dJITextView.setText(String.format("[%s]%d", new Object[]{a.b, Integer.valueOf(iMUStatus.devErrCode)}) + string);
                }
                dJIImageView.setImageResource(this.b.b(iMUStatus.colorStatus));
                dJITextView2.setText(DateFormat.format("yyyy-MM-dd HH:mm", iMUStatus.time).toString());
            }
            return view;
        }
    }

    public DJIChecklistRedundancyHistoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        this.j.notifyDataSetChanged();
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.a = (DJITextView) findViewById(R.id.w1);
            this.b = (DJITextView) findViewById(R.id.w2);
            this.c = (DJITextView) findViewById(R.id.w3);
            this.d = (DJITextView) findViewById(R.id.w4);
            this.a.setOnClickListener(this.i);
            this.b.setOnClickListener(this.i);
            this.c.setOnClickListener(this.i);
            this.d.setOnClickListener(this.i);
            findViewById(R.id.w9).setOnClickListener(this.i);
            this.a.setSelected(true);
            this.b.setSelected(false);
            this.c.setSelected(false);
            this.d.setSelected(false);
            this.e = (DJILinearLayout) findViewById(R.id.w5);
            this.f = (DJILinearLayout) findViewById(R.id.w7);
            this.e.show();
            this.f.go();
            this.g = (ListView) findViewById(R.id.w6);
            this.j = new a(this);
            this.g.setAdapter(this.j);
            this.h = (ListView) findViewById(R.id.w8);
            this.k = new b(this);
            this.h.setAdapter(this.k);
            if (i.getInstance().c() == ProductType.N3) {
                this.d.setVisibility(8);
                this.c.setText(R.string.fpv_redundancy_history_system_extend_imu);
                return;
            }
            this.d.setVisibility(0);
            this.c.setText(R.string.fpv_redundancy_history_system2);
        }
    }

    private String a(int i) {
        switch (i) {
            case 0:
                return getContext().getString(R.string.fpv_redundancy_system_mc);
            case 1:
                if (i.getInstance().c() == ProductType.N3) {
                    return getContext().getString(R.string.fpv_redundancy_history_system_extend_imu);
                }
                return getContext().getString(R.string.fpv_redundancy_history_system2);
            case 2:
                return getContext().getString(R.string.fpv_redundancy_history_system3);
            default:
                return "";
        }
    }

    private int b(int i) {
        switch (AnonymousClass2.a[COLOR_STATUS.ofValue(i).ordinal()]) {
            case 1:
            case 2:
                return R.drawable.fpv_redundancy_system__status_green;
            case 3:
            case 4:
                return R.drawable.fpv_redundancy_system__status_red;
            case 5:
            case 6:
                return R.drawable.fpv_redundancy_system__status_yellow;
            default:
                return R.drawable.fpv_redundancy_system__status_gray;
        }
    }
}
