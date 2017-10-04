package dji.sdksharedlib.hardware.abstractions.h;

import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;

public class d extends a {
    public d() {
        this.b.customButton1.isPresent = true;
        this.b.customButton2.isPresent = true;
        this.b.flightModeSwitch.isPresent = true;
        this.b.goHomeButton.isPresent = true;
        if (c()) {
            this.b.pauseButton.isPresent = true;
        } else {
            this.b.playbackButton.isPresent = true;
        }
        this.b.recordButton.isPresent = true;
        this.b.rightWheel.isPresent = true;
        this.b.shutterButton.isPresent = true;
        this.b.transformSwitch.isPresent = false;
    }

    public void a(String str, int i, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
    }
}
