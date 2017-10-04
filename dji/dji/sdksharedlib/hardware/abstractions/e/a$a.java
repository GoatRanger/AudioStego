package dji.sdksharedlib.hardware.abstractions.e;

protected enum a$a {
    TABLE_CHOICE("table_choice"),
    SMOOTH_TRACK_PITCH_SPEED("pitch_speed"),
    SMOOTH_TRACK_YAW_SPEED("yaw_speed"),
    SMOOTH_TRACK_ROLL_SPEED("roll_speed"),
    SMOOTH_TRACK_PITCH_DEADBAND("pitch_deadband"),
    SMOOTH_TRACK_YAW_DEADBAND("yaw_deadband"),
    SMOOTH_TRACK_ROLL_DEADBAND("roll_deadband"),
    SMOOTH_TRACK_PITCH_ACCEL("pitch_accel"),
    SMOOTH_TRACK_YAW_ACCEL("yaw_accel"),
    SMOOTH_TRACK_ROLL_ACCEL("roll_accel"),
    CONTROLLER_PITCH_SPEED("pitch_expo"),
    CONTROLLER_YAW_SPEED("yaw_expo"),
    CONTROLLER_PITCH_SMOOTH("pitch_time_expo"),
    CONTROLLER_YAW_SMOOTH("yaw_time_expo"),
    PITCH_SMOOTH_TRACK_ENABLED("pitch_smooth_track"),
    YAW_SMOOTH_TRACK_ENABLED("yaw_smooth_track"),
    ROLL_SMOOTH_TRACK_ENABLED("roll_smooth_track");
    
    private String r;

    private a$a(String str) {
        this.r = str;
    }

    public String a() {
        return this.r;
    }
}
