package dji.d.a;

import android.graphics.RectF;
import com.here.odnp.config.OdnpConfigStatic;
import dji.common.camera.DJICameraSettingsDef.CameraThermalExternalParamProfile;
import dji.common.camera.DJICameraSettingsDef.CameraThermalFFCMode;
import dji.common.camera.ThermalAreaTemperatureAggregations;
import dji.common.camera.ThermalExternalSceneSettings;
import dji.common.camera.ThermalSpotMeteringTargetPoint;
import dji.sdksharedlib.b.c.a;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.c.d.j;
import dji.sdksharedlib.hardware.abstractions.f;
import dji.thirdparty.f.d;
import dji.thirdparty.f.j.e;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class c extends j {
    private int o = 0;

    public c() {
        P();
    }

    private void P() {
        d.b(1, TimeUnit.SECONDS, e.c()).n(new 1(this)).w().C();
    }

    protected dji.sdksharedlib.b.c a(String str) {
        return new a().b(h.a).d(str).a();
    }

    @f(a = "ThermalFFCMode")
    public void a(CameraThermalFFCMode cameraThermalFFCMode, b.e eVar) {
        b(cameraThermalFFCMode, dji.sdksharedlib.b.b.ao);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "ThermalTriggerFFC")
    public void a(b.e eVar) {
        if (eVar != null) {
            a(Integer.valueOf(new Random(OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME).nextInt()), c(dji.sdksharedlib.b.b.am));
            a(new ThermalAreaTemperatureAggregations((float) new Random(OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME).nextInt(), (float) new Random(1000).nextInt(), new Random(1000).nextInt(), new Random(1000).nextInt(), (float) new Random(OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL).nextInt(), new Random(OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL).nextInt(), new Random(OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL).nextInt()), c(dji.sdksharedlib.b.b.au));
            eVar.a(null);
        }
    }

    protected void a() {
        a(dji.sdksharedlib.b.b.class, getClass());
    }

    @f(a = "ThermalSpotMeteringTargetPoint")
    public void a(ThermalSpotMeteringTargetPoint thermalSpotMeteringTargetPoint, b.e eVar) {
        a(thermalSpotMeteringTargetPoint, c(dji.sdksharedlib.b.b.at));
        a(Integer.valueOf(0), c(dji.sdksharedlib.b.b.am));
    }

    @f(a = "ThermalSpotMeteringArea")
    public void a(RectF rectF, b.e eVar) {
        a(rectF, c(dji.sdksharedlib.b.b.ar));
        a(new ThermalAreaTemperatureAggregations(0.0f, 0.0f, 0, 0, 0.0f, 0, 0), c(dji.sdksharedlib.b.b.au));
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalCustomExternalSceneSettingsProfile")
    public void a(CameraThermalExternalParamProfile cameraThermalExternalParamProfile, b.e eVar) {
        b(cameraThermalExternalParamProfile, dji.sdksharedlib.b.b.aw);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalCustomExternalSceneSettingsProfile")
    public void a(ThermalExternalSceneSettings thermalExternalSceneSettings, b.e eVar) {
        b(thermalExternalSceneSettings, dji.sdksharedlib.b.b.aw);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalAtmosphericTemperature")
    public void a(short s, b.e eVar) {
        b(Short.valueOf(s), dji.sdksharedlib.b.b.ax);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalAtmosphericTransmissionCoefficient")
    public void b(short s, b.e eVar) {
        b(Short.valueOf(s), dji.sdksharedlib.b.b.ay);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalBackgroundTemperature")
    public void c(short s, b.e eVar) {
        b(Short.valueOf(s), dji.sdksharedlib.b.b.az);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalSceneEmissivity")
    public void d(short s, b.e eVar) {
        b(Short.valueOf(s), dji.sdksharedlib.b.b.aA);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalWindowReflection")
    public void e(short s, b.e eVar) {
        b(Short.valueOf(s), dji.sdksharedlib.b.b.aB);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalWindowReflectedTemperature")
    public void f(short s, b.e eVar) {
        b(Short.valueOf(s), dji.sdksharedlib.b.b.aC);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalWindowTemperature")
    public void g(short s, b.e eVar) {
        b(Short.valueOf(s), dji.sdksharedlib.b.b.aD);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @f(a = "ThermalWindowTransmissionCoefficient")
    public void h(short s, b.e eVar) {
        b(Short.valueOf(s), dji.sdksharedlib.b.b.aE);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    protected boolean b() {
        return true;
    }
}
