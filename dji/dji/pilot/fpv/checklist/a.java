package dji.pilot.fpv.checklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.midware.data.model.P3.DataOsdGetPushHome.MotorEscmState;
import dji.pilot.R;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class a extends BaseAdapter {
    private final Context a;
    private final LayoutInflater b;
    private List<a> c = null;

    public static final class a {
        public MotorEscmState a = null;
        public int b = 0;

        public a(MotorEscmState motorEscmState, int i) {
            this.a = motorEscmState;
            this.b = i;
        }
    }

    private static final class b {
        private DJITextView a;

        private b() {
            this.a = null;
        }
    }

    public a(Context context) {
        this.a = context;
        this.b = LayoutInflater.from(context);
    }

    public a a(List<a> list) {
        this.c = list;
        return this;
    }

    public int getCount() {
        if (this.c != null) {
            return this.c.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.c == null || this.c.size() <= i) {
            return null;
        }
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    private String a(a aVar) {
        int i;
        int i2 = R.string.motor_escm_error_resart;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a.getString(R.string.motor_escm_index, new Object[]{Integer.valueOf(aVar.b + 1)}));
        if (MotorEscmState.DISCONNECT == aVar.a) {
            i = R.string.motor_escm_disconnect;
        } else if (MotorEscmState.SIGNAL_ERROR == aVar.a) {
            i = R.string.motor_escm_signal_error;
        } else if (MotorEscmState.RESISTANCE_ERROR == aVar.a) {
            i = R.string.motor_escm_resistance_eror;
            i2 = R.string.motor_escm_motor_error;
        } else if (MotorEscmState.BLOCK == aVar.a) {
            i2 = R.string.motor_escm_block;
            i = 0;
        } else if (MotorEscmState.NON_BALANCE == aVar.a) {
            i = R.string.motor_escm_non_balance;
            i2 = R.string.motor_escm_motor_error;
        } else if (MotorEscmState.ESCM_ERROR == aVar.a) {
            i = R.string.motor_escm_eror;
        } else if (MotorEscmState.PROPELLER_OFF == aVar.a) {
            i2 = R.string.motor_escm_propeller_off;
            i = 0;
        } else {
            i2 = R.string.fpv_checklist_check_abnormal;
            i = 0;
        }
        if (i != 0) {
            stringBuilder.append(this.a.getString(i2, new Object[]{""}));
        } else {
            stringBuilder.append(this.a.getString(i2));
        }
        return stringBuilder.toString();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            bVar = new b();
            view = this.b.inflate(R.layout.fpv_check_item, null);
            bVar.a = (DJITextView) view.findViewById(R.id.vy);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        Object item = getItem(i);
        if (item instanceof a) {
            bVar.a.setText(a((a) item));
        }
        return view;
    }
}
