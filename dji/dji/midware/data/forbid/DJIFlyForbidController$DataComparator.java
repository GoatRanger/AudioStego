package dji.midware.data.forbid;

import java.util.Comparator;

public class DJIFlyForbidController$DataComparator implements Comparator<FlyForbidElement> {
    final /* synthetic */ DJIFlyForbidController this$0;

    public DJIFlyForbidController$DataComparator(DJIFlyForbidController dJIFlyForbidController) {
        this.this$0 = dJIFlyForbidController;
    }

    public int compare(FlyForbidElement flyForbidElement, FlyForbidElement flyForbidElement2) {
        return ((int) (flyForbidElement2.lat * 1000000.0d)) - ((int) (flyForbidElement.lat * 1000000.0d));
    }
}
