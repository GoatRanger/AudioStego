package dji.pilot.dji_groundstation.a;

public enum b {
    EVENT_ENTERDIALOG_DATA_FINISH(0),
    EVENT_ENTERDIALOG_DISMISS(1),
    EVENT_ENTERDIALOG_MAIN(2),
    EVENT_SMARTDIALOG_DATA_FINISH(16),
    EVENT_SMARTDIALOG_DISMISS(17),
    EVENT_CONTROLDIALOG_SHOW(513),
    EVENT_ALERTDIALOG_SHOW(257),
    EVENT_CONFIRMDIALOG_SHOW(769),
    EVENT_EXITMISSIONDIALOG_SHOW(1025);
    
    private int j;

    private b(int i) {
        this.j = 0;
        this.j = i;
    }

    public int a() {
        return this.j;
    }
}
