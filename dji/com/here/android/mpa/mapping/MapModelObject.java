package com.here.android.mpa.mapping;

import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.MotionEventCompat;
import com.here.android.mpa.common.Vector3f;
import com.nokia.maps.MapModelObjectImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import java.util.ArrayList;
import java.util.List;

@Online
public class MapModelObject extends MapObject {
    private List<Light> a = new ArrayList();
    private MapModelObjectImpl c;

    @Online
    public interface Light {
    }

    @Online
    public static final class DirectionalLight implements Light {
        private Vector3f a;

        public DirectionalLight(Vector3f vector3f) {
            this.a = vector3f;
        }

        public void setSource(Vector3f vector3f) {
            this.a = vector3f;
        }

        public Vector3f getSource() {
            return this.a;
        }
    }

    @Online
    public interface Material {
    }

    @Online
    public static final class PhongMaterial implements Material {
        public static final int DEFAULT_AMBIANT_COLOR = -16777216;
        public static final int DEFAULT_DIFFUSE_COLOR = -1;
        int a;
        int b;

        public PhongMaterial() {
            this.a = -1;
            this.b = -16777216;
        }

        public PhongMaterial(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public PhongMaterial setDiffuseColor(int i) {
            this.a = i;
            return this;
        }

        public int getDiffuseColor() {
            return this.a;
        }

        public PhongMaterial setAmbientColor(int i) {
            this.b = i;
            return this;
        }

        public int getAmbientColor() {
            return this.b;
        }
    }

    protected MapModelObject(MapModelObjectImpl mapModelObjectImpl) {
        super(mapModelObjectImpl);
        this.c = mapModelObjectImpl;
    }

    public static int ARGBToRGBA(int i) {
        return ((((-16777216 & i) >>> 24) | ((16711680 & i) << 8)) | ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) << 8)) | ((i & 255) << 8);
    }

    public static int RGBAToARGB(int i) {
        return ((i & 255) << 24) | ((i & InputDeviceCompat.SOURCE_ANY) >>> 8);
    }

    public Material getMaterial() {
        int[] phongMaterial = this.c.getPhongMaterial();
        return new PhongMaterial(RGBAToARGB(phongMaterial[0]), RGBAToARGB(phongMaterial[1]));
    }

    public boolean setMaterial(Material material) {
        if (!(material instanceof PhongMaterial)) {
            return false;
        }
        PhongMaterial phongMaterial = (PhongMaterial) material;
        return this.c.a(ARGBToRGBA(phongMaterial.a), ARGBToRGBA(phongMaterial.b));
    }

    public int getNumberLightsSupported() {
        return this.c.getNumberLightsSupported();
    }

    public boolean addLight(Light light) {
        boolean z = false;
        if (light instanceof DirectionalLight) {
            Vector3f source = ((DirectionalLight) light).getSource();
            z = this.c.addDirectionalLight((double) source.getX(), (double) source.getY(), (double) source.getZ());
            if (z) {
                this.a.add(light);
            }
        }
        return z;
    }

    public Light getLight(int i) {
        dy.a(i < this.a.size(), "Light is out of array bounds.");
        return (Light) this.a.get(i);
    }

    public boolean setLight(int i, Light light) {
        boolean z = false;
        dy.a(i < this.a.size(), "Light is out of array bounds.");
        if (light instanceof DirectionalLight) {
            Vector3f source = ((DirectionalLight) light).getSource();
            z = this.c.a(i, (double) source.getX(), (double) source.getY(), (double) source.getZ());
            if (z) {
                this.a.set(i, light);
            }
        }
        return z;
    }

    public boolean removeAllLights() {
        if (this.a.size() <= 0) {
            return true;
        }
        boolean d = this.c.d();
        if (!d) {
            return d;
        }
        this.a.clear();
        return d;
    }

    public int getNumLights() {
        return this.a.size();
    }
}
