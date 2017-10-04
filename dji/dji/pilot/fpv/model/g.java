package dji.pilot.fpv.model;

import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycGetFsAction;

public class g extends n {
    public static final int a = 2;
    public byte b;
    public boolean c;
    public boolean d;
    public boolean e;
    public byte f;

    public g(boolean z) {
        super(z);
    }

    public void a() {
        boolean z;
        int i = 1;
        this.b = (byte) DataFlycGetFsAction.getInstance().getFsAction().value();
        this.c = d.read("g_config.mvo_cfg.mvo_func_en_0").value.intValue() == 1;
        if (d.read("g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0").value.intValue() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.d = z;
        if (d.read("g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0").value.intValue() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.e = z;
        if (!this.c) {
            i = 0;
        }
        if (this.d) {
            i ^= 2;
        }
        if (this.e) {
            i ^= 4;
        }
        this.f = (byte) i;
    }

    public void setRecData(byte[] bArr) {
        boolean z;
        boolean z2 = true;
        this.b = bArr[0];
        this.f = bArr[1];
        if ((this.f & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.c = z;
        if (((this.f >> 1) & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.d = z;
        if (((this.f >> 2) & 1) == 0) {
            z2 = false;
        }
        this.e = z2;
    }

    public byte[] getRecData() {
        a();
        return new byte[]{this.b, this.f};
    }

    protected void doPack() {
    }
}
