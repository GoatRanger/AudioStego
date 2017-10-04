package dji.pilot.visual.util;

public class b {

    public enum a {
        NON(0.0f, false),
        UP(0.0f, true),
        DOWN(180.0f, true),
        LEFT(270.0f, true),
        RIGHT(90.0f, true);
        
        private float f;
        private boolean g;

        private a(float f, boolean z) {
            this.f = 0.0f;
            this.g = false;
            this.f = f;
            this.g = z;
        }

        public float a() {
            return this.f;
        }

        public boolean b() {
            return this.g;
        }
    }
}
