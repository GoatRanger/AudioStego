package dji.pilot.visual.b;

import com.alipay.sdk.j.i;
import dji.midware.data.model.P3.DataEyeGetPushEasySelfCalibration.CaliStatusCode;
import dji.midware.data.model.P3.DataEyeGetPushEasySelfCalibration.VisionSensorType;

public class a {
    public int a = 0;
    public VisionSensorType b = VisionSensorType.OTHER;
    public CaliStatusCode c = CaliStatusCode.OTHER;
    public int d = 0;

    public a a(a aVar) {
        return a(aVar.a, aVar.b, aVar.c, aVar.d);
    }

    public a a(int i, VisionSensorType visionSensorType, CaliStatusCode caliStatusCode, int i2) {
        this.a = i;
        this.b = visionSensorType;
        this.c = caliStatusCode;
        this.d = i2;
        return this;
    }

    public int hashCode() {
        return ((((this.c.value() + 527) * 31) + this.b.value()) * 31) + this.d;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof a)) {
            return equals;
        }
        a aVar = (a) obj;
        return aVar.c == this.c && aVar.b == this.b && aVar.d == this.d;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Code-").append(this.c);
        stringBuilder.append(i.b).append("Type-").append(this.b);
        stringBuilder.append(i.b).append("Pgb-").append(String.valueOf(this.d));
        stringBuilder.append(i.b);
        return stringBuilder.toString();
    }
}
