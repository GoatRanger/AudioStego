package dji.pilot.dji_groundstation.a;

public enum d$a {
    None(0),
    Point(1),
    Track(2),
    Gesture(3),
    Normal(4),
    Smart(5),
    Joystick(6),
    Trackselfie(7);
    
    private int i;

    private d$a(int i) {
        this.i = 0;
        this.i = i;
    }

    public int a() {
        return this.i;
    }
}
