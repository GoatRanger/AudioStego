package dji.pilot2.multimoment.videolib;

import java.io.Serializable;
import java.util.List;

public class EditRecoverInfo implements Serializable {
    private long howLong;
    private boolean isFromDraft = false;
    private boolean isRecovering = false;
    private List<d> mSegments;

    public boolean isFromDraft() {
        return this.isFromDraft;
    }

    public void setFromDraft(boolean z) {
        this.isFromDraft = z;
    }

    public boolean isRecovering() {
        return this.isRecovering;
    }

    public void setRecovering(boolean z) {
        this.isRecovering = z;
    }

    public long getHowLong() {
        return this.howLong;
    }

    public void setHowLong(long j) {
        this.howLong = j;
    }

    public EditRecoverInfo(List<d> list) {
        this.mSegments = list;
    }

    public int[] getFileDurations() {
        int size = this.mSegments.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((d) this.mSegments.get(i)).e();
        }
        return iArr;
    }

    public String[] getFileNames() {
        int size = this.mSegments.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((d) this.mSegments.get(i)).d();
        }
        return strArr;
    }

    public double[] getSegBright() {
        int size = this.mSegments.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.mSegments.get(i)).k();
        }
        return dArr;
    }

    public double[] getSegSaturation() {
        int size = this.mSegments.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.mSegments.get(i)).l();
        }
        return dArr;
    }

    public double[] getSegContrast() {
        int size = this.mSegments.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.mSegments.get(i)).m();
        }
        return dArr;
    }

    public double[] getFast() {
        int size = this.mSegments.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.mSegments.get(i)).j();
        }
        return dArr;
    }

    public void getNormalTimes(List<Integer> list, List<Integer> list2) {
        int size = this.mSegments.size();
        for (int i = 0; i < size; i++) {
            list.add(Integer.valueOf((int) ((d) this.mSegments.get(i)).f()));
            list2.add(Integer.valueOf((int) ((d) this.mSegments.get(i)).g()));
        }
    }

    public int[] getSegFilterNum() {
        int size = this.mSegments.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((d) this.mSegments.get(i)).o();
        }
        return iArr;
    }

    public double[] getSegFilterMuch() {
        int size = this.mSegments.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.mSegments.get(i)).p();
        }
        return dArr;
    }

    public double[] getSegVolume() {
        int size = this.mSegments.size();
        if (size == 0) {
            return null;
        }
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((d) this.mSegments.get(i)).n();
        }
        return dArr;
    }
}
