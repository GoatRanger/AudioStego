package dji.common.camera;

public class CameraSpotMeteringArea {
    private int colIndex;
    private int rowIndex;

    public void setColIndex(int i) {
        this.colIndex = i;
    }

    public void setRowIndex(int i) {
        this.rowIndex = i;
    }

    public int getColIndex() {
        return this.colIndex;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }
}
