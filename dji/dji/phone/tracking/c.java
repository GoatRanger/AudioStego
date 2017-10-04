package dji.phone.tracking;

import dji.pilot.fpv.R;

public enum c {
    TK_INIT_FAILED_SMALL(R.string.lp_tk_failed_too_small),
    TK_INIT_FAILED_BIG(R.string.lp_tk_failed_too_large),
    TK_INIT_FAILED_NO_TARGET(R.string.lp_tk_failed_low_detect),
    TK_INIT_FAILED_ERROR(R.string.lp_tk_failed_error);
    
    int e;

    private c(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }
}
