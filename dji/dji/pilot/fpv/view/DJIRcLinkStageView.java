package dji.pilot.fpv.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import dji.midware.data.model.P3.DataOsdGetPushSdrUpwardSelectChannel;
import dji.midware.data.model.P3.DataOsdSetSweepFrequency;
import dji.pilot.R;
import dji.pilot.c.d;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;

public class DJIRcLinkStageView extends DJILinearLayout implements a {
    private static final int d = 1;
    private Context a = null;
    private TextView b;
    private FreqSnrSdrRcLinkView c;
    private Handler e = new Handler(new Callback(this) {
        final /* synthetic */ DJIRcLinkStageView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    String str = "";
                    if (message.obj != null) {
                        this.a.b.setText((String) message.obj);
                        break;
                    }
                    break;
            }
            return false;
        }
    });

    public DJIRcLinkStageView(Context context) {
        super(context);
        this.a = context;
    }

    public DJIRcLinkStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            this.b = (TextView) findViewById(R.id.a_5);
            this.c = (FreqSnrSdrRcLinkView) findViewById(R.id.a_3);
        }
    }

    private void b() {
        if (d.i == 1) {
            DataOsdSetSweepFrequency.getInstance().b(1).a(true).start(null);
        }
    }

    public void dispatchOnStart() {
        c.a().a(this);
    }

    public void dispatchOnStop() {
        if (d.i == 1) {
            DataOsdSetSweepFrequency.getInstance().b(1).a(false).start(null);
        }
        c.a().d(this);
    }

    public void dispatchOnResume() {
        b();
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrUpwardSelectChannel dataOsdGetPushSdrUpwardSelectChannel) {
        float selectChannelType = dataOsdGetPushSdrUpwardSelectChannel.getSelectChannelType();
        int selectChannelCount = dataOsdGetPushSdrUpwardSelectChannel.getSelectChannelCount();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.a.getString(R.string.fpv_hd_bandwidth_desc)).append(" : ").append("" + selectChannelType).append("\n");
        stringBuffer.append(this.a.getString(R.string.fpv_hd_sdr_cnt)).append(" : ").append("" + selectChannelCount).append("\n");
        float[] selectChannelList = dataOsdGetPushSdrUpwardSelectChannel.getSelectChannelList();
        if (selectChannelList != null && selectChannelList.length == selectChannelCount && selectChannelCount > 0) {
            stringBuffer.append(this.a.getString(R.string.fpv_hd_sdr_position)).append(" : ").append("\n");
            int i = 1;
            for (int i2 = 0; i2 < selectChannelCount; i2++) {
                if (i2 % 8 == 0) {
                    if (i2 > 0) {
                        stringBuffer.append("\n").append("" + i + "    -->     ");
                    } else {
                        stringBuffer.append("" + i + "    -->     ");
                    }
                    i++;
                }
                if (i2 % 7 == 0) {
                    if (i2 > 0) {
                        stringBuffer.append(selectChannelList[i2]);
                    } else if (selectChannelList[i2] < 10.0f) {
                        stringBuffer.append(selectChannelList[i2]).append("       ");
                    } else {
                        stringBuffer.append(selectChannelList[i2]).append("     ");
                    }
                } else if (selectChannelList[i2] < 10.0f) {
                    stringBuffer.append(selectChannelList[i2]).append("       ");
                } else {
                    stringBuffer.append(selectChannelList[i2]).append("     ");
                }
            }
            if (this.c != null) {
                this.c.setChannelInfo(selectChannelType, selectChannelCount, selectChannelList);
            }
        }
        this.e.sendMessageDelayed(Message.obtain(this.e, 1, stringBuffer.toString()), 100);
    }
}
