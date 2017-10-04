package dji.midware.stat;

public abstract class StatBase {
    private final String name;

    public abstract void addEvent(double d);

    public abstract double getValue();

    public abstract double getValueAndReset();

    public StatBase(String str) {
        if (str == null) {
            throw new RuntimeException("name should not be null");
        }
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (obj instanceof StatBase) {
            return ((StatBase) obj).name.equals(this.name);
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}
