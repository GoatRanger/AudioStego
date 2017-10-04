package dji.common.camera;

import java.util.Set;
import java.util.TreeSet;

public class BaseRange<T> {
    public static final int TYPE_LIST = 2;
    public static final int TYPE_MAX_MIN = 1;
    protected T maxValue;
    protected T minValue;
    protected Set<T> rangeList;
    protected int type;

    public BaseRange(int i) {
        this.type = 2;
        this.rangeList = new TreeSet();
        this.type = i;
    }

    public BaseRange(Set<T> set) {
        this.type = 2;
        this.rangeList = set;
        this.type = 2;
    }

    public BaseRange(T t, T t2) {
        this.type = 2;
        this.maxValue = t;
        this.minValue = t2;
        this.type = 1;
    }

    public Set<T> getRangeList() {
        return this.rangeList;
    }

    public T getMaxValue() {
        return this.maxValue;
    }

    public T getMinValue() {
        return this.minValue;
    }

    public int getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.type == 2) {
            if ((obj instanceof BaseRange) && ((BaseRange) obj).type == 2) {
                BaseRange baseRange = (BaseRange) obj;
                if (this.rangeList.size() == baseRange.rangeList.size()) {
                    boolean z;
                    for (Object contains : this.rangeList) {
                        if (!baseRange.rangeList.contains(contains)) {
                            z = false;
                            break;
                        }
                    }
                    z = true;
                    return z;
                }
            }
        } else if (this.type == 1 && (obj instanceof BaseRange) && ((BaseRange) obj).type == 1 && ((BaseRange) obj).maxValue.equals(this.maxValue) && ((BaseRange) obj).minValue.equals(this.minValue)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        if (this.type == 1) {
            return this.minValue.hashCode() + this.maxValue.hashCode();
        }
        if (this.type != 2) {
            return super.hashCode();
        }
        int[] iArr = new int[this.rangeList.size()];
        int size = this.rangeList.size();
        Object[] toArray = this.rangeList.toArray();
        int i2 = 0;
        while (i2 < size) {
            iArr[i2] = toArray[i2].hashCode();
            int i3 = iArr[i2] ^ i;
            i2++;
            i = i3;
        }
        return i;
    }

    public String toString() {
        if (this.type == 1) {
            return "BaseRange{, maxValue=" + this.maxValue + ", minValue=" + this.minValue + '}';
        }
        if (this.type == 2) {
            return "BaseRange{rangeList=" + this.rangeList + '}';
        }
        return super.toString();
    }
}
