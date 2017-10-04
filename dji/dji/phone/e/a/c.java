package dji.phone.e.a;

public enum c {
    common,
    v_clicked,
    v_pressed,
    v_selected,
    v_unselected,
    v_show,
    v_hide,
    v_gone,
    NONE;

    public boolean a() {
        return this == v_show;
    }

    public boolean b() {
        return this == v_hide;
    }
}
