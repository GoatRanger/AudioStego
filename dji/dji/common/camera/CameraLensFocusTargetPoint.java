package dji.common.camera;

public class CameraLensFocusTargetPoint {
    private float x;
    private float y;

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    public CameraLensFocusTargetPoint(float f, float f2) {
        this.x = f;
        this.y = f2;
    }
}
