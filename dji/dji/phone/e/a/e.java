package dji.phone.e.a;

import dji.phone.e.b;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum e {
    VIEW_PREVIEW,
    VIEW_CAMERA_MODE,
    VIEW_CAMERA_CONTROL,
    VIEW_BLE_DIALOG,
    VIEW_UI_SWITCHER,
    VIEW_SHOTCUT_CAMERA,
    VIEW_SHOTCUT_GIMBAL,
    VIEW_TIMELAPSE_SETER,
    VIEW_GIMBAL_ROLL_TUNE,
    BTN_BLE_STATUS,
    BTN_CAMERA_MODE_SWITCHER,
    BTN_CAMERA_SHUTTER,
    BTN_CAMERA_ID_SWITCHER,
    BTN_TK_QUIT,
    BTN_CAMERA_MODE,
    BTN_SHOTCUT_GIMBAL,
    BTN_SHOTCUT_CAMERA,
    BTN_CAMERA_VIDEO_AUTO,
    BTN_CAMERA_VIDEO_TLP,
    OTHER;
    
    public EnumMap<c, List<b>> u;
    private e v;
    private b[] w;

    public e a(c cVar, b... bVarArr) {
        this.u.put(cVar, Arrays.asList(bVarArr));
        return this;
    }

    public e a(e eVar, b... bVarArr) {
        this.v = eVar;
        this.w = bVarArr;
        return this;
    }

    public e a(e eVar, c cVar, c cVar2) {
        this.v = eVar;
        b bVar = new b(cVar, cVar2);
        this.w = new b[1];
        this.w[0] = bVar;
        return this;
    }

    public List<b> a(c cVar) {
        return (List) this.u.get(cVar);
    }

    public boolean a() {
        return this.v != null;
    }

    public b b(c cVar) {
        for (b bVar : this.w) {
            if (bVar.a == cVar) {
                return new b(this.v, bVar.b);
            }
        }
        return null;
    }
}
