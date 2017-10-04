package dji.pilot.visual.a;

import com.alipay.sdk.j.i;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.pilot.R;
import dji.pilot.fpv.model.o.a;
import dji.pilot.visual.a.g.c;

public class g$b {
    public final SensorType a;
    public int b = 0;
    public c c = c.a;
    public volatile int d = R.string.visual_radar_detect_abnormal;
    public int e = Integer.MAX_VALUE;
    public a f = a.NON;
    public final g$a g = new g$a();

    public g$b(SensorType sensorType) {
        this.a = sensorType;
    }

    public void a(a aVar, boolean z) {
        this.b = 0;
        this.d = R.string.visual_radar_detect_abnormal;
        this.e = Integer.MAX_VALUE;
        this.f = aVar;
        if (a.RADAR == aVar) {
            this.c = z ? c.e : c.a;
        } else if (a.TOF == aVar) {
            this.c = c.e;
        }
        this.g.a();
    }

    public boolean a() {
        return a.RADAR == this.f;
    }

    public boolean b() {
        return a.NON != this.f;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("type-").append(this.a).append(i.b);
        stringBuilder.append("status-").append(this.c).append(i.b);
        stringBuilder.append("times-").append(String.valueOf(this.b)).append(i.b);
        stringBuilder.append("reason-").append(String.valueOf(this.d)).append(i.b);
        stringBuilder.append("level-").append(String.valueOf(this.e)).append(i.b);
        stringBuilder.append("at-").append(String.valueOf(this.f)).append(i.b);
        return stringBuilder.toString();
    }

    public int hashCode() {
        return super.hashCode();
    }
}
