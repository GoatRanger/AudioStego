package dji.pilot2.mine.activity;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.objects.g;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.thirdparty.afinal.a.b.c;

public class NetworkStatActivity extends DJIActivityNoFullScreen {
    private static final String[] E = new String[]{"b", "Kb", "Mb", "Gb", "Tb", "Pb"};
    public static final String a = "upload_size";
    public static final String b = "download_size";
    public static final String c = "upload_size_zero";
    public static final String d = "download_size_zero";
    public static final String t = "network_monitor";
    public static final int u = 1;
    public static final boolean v = false;
    long A;
    boolean B;
    Handler C;
    Runnable D = new 1(this);
    @c(a = 2131366042)
    TextView w;
    @c(a = 2131366043)
    TextView x;
    @c(a = 2131366045)
    TextView y;
    long z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_network_stat);
        this.C = new a();
        this.B = g.b((Context) this, t, false);
        this.z = g.b((Context) this, c, 0);
        this.A = g.b((Context) this, d, 0);
        if (this.B) {
            a();
            Message obtainMessage = this.C.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = this.D;
            this.C.sendMessageDelayed(obtainMessage, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
            this.y.setText(getString(R.string.v2_net_statistics_stop));
            return;
        }
        this.y.setText(getString(R.string.v2_net_statistics_start));
    }

    private void a() {
        int i = getApplicationInfo().uid;
        long uidTxBytes = TrafficStats.getUidTxBytes(i);
        long uidRxBytes = TrafficStats.getUidRxBytes(i);
        DJILogHelper.getInstance().LOGI("Lyric", "send: " + uidTxBytes + " sendZero: " + this.z);
        DJILogHelper.getInstance().LOGI("Lyric", "receive: " + uidRxBytes + " receiveZero: " + this.A);
        this.w.setText(a(uidTxBytes - this.z));
        this.x.setText(a(uidRxBytes - this.A));
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void finish() {
        this.C.removeMessages(1);
        super.finish();
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c77:
                finish();
                return;
            case R.id.cda:
                if (this.B) {
                    this.B = false;
                    g.a((Context) this, t, false);
                    this.C.removeMessages(1);
                    this.y.setText(getString(R.string.v2_net_statistics_start));
                    return;
                }
                this.B = true;
                g.a((Context) this, t, true);
                int i = getApplicationInfo().uid;
                this.z = TrafficStats.getUidTxBytes(i);
                this.A = TrafficStats.getUidRxBytes(i);
                g.a((Context) this, c, this.z);
                g.a((Context) this, d, this.A);
                a();
                Message obtainMessage = this.C.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = this.D;
                this.C.sendMessageDelayed(obtainMessage, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                this.y.setText(getString(R.string.v2_net_statistics_stop));
                return;
            default:
                return;
        }
    }

    private String a(long j) {
        double d = (double) j;
        int i = 0;
        while (d >= 1024.0d && i < E.length) {
            d /= 1024.0d;
            i++;
        }
        if (i == 0) {
            return String.format("%.0f %s", new Object[]{Double.valueOf(d), E[i]});
        }
        return String.format("%.2f %s", new Object[]{Double.valueOf(d), E[i]});
    }
}
