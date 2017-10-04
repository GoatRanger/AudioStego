package dji.common.error;

import com.alipay.sdk.j.f;
import dji.midware.data.config.P3.a;

public class DJIMissionManagerError extends DJIError {
    public static final DJIMissionManagerError COMMON_DISCONNECTED = new DJIMissionManagerError("Disconnected");
    public static final DJIMissionManagerError COMMON_UNSUPPORTED = new DJIMissionManagerError("Not supported");
    public static final DJIMissionManagerError MISSION_NOT_EXISTED = new DJIMissionManagerError("Mission not existed");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_ALTITUDE_TOO_HIGH = new DJIMissionManagerError("The aircraft's altitude is too high.");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_ALTITUDE_TOO_LOW = new DJIMissionManagerError("The aircraft's altitude is too low.");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_GOINGHOME = new DJIMissionManagerError("The aircraft is going home");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_IN_NOFLYZONE = new DJIMissionManagerError("The aircraft is in the no fly zone");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_LANDING = new DJIMissionManagerError("The aircraft is landing");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_NOT_IN_THE_AIR = new DJIMissionManagerError("The aircraft is not in the air");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_RUNNING_MISSION = new DJIMissionManagerError("The aircraft is running a mission");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_STARTING_MOTOR = new DJIMissionManagerError("The aircraft is starting the motor");
    public static final DJIMissionManagerError MISSION_RESULT_AIRCRAFT_TAKINGOFF = new DJIMissionManagerError("The aircraft is taking off");
    public static final DJIMissionManagerError MISSION_RESULT_ALTITUDE_TOO_HIGH = new DJIMissionManagerError("The altitude is too high");
    public static final DJIMissionManagerError MISSION_RESULT_ALTITUDE_TOO_LOW = new DJIMissionManagerError("The altitude is too low");
    public static final DJIMissionManagerError MISSION_RESULT_BEGAN = new DJIMissionManagerError("began");
    public static final DJIMissionManagerError MISSION_RESULT_CANCELED = new DJIMissionManagerError("canceled");
    public static final DJIMissionManagerError MISSION_RESULT_CANNOT_BYPASS_OBSTACLE = new DJIMissionManagerError("The aircraft cannot bypass the obstacle.");
    public static final DJIMissionManagerError MISSION_RESULT_DISTANCE_FROM_MISSION_TARGET_TOO_LONG = new DJIMissionManagerError("Navigation in the product is unsupported");
    public static final DJIMissionManagerError MISSION_RESULT_FAILED = new DJIMissionManagerError(f.b);
    public static final DJIMissionManagerError MISSION_RESULT_FEATURE_POINT_CANNOT_MATCH = new DJIMissionManagerError("The feature points found by both vision sensors cannot match.");
    public static final DJIMissionManagerError MISSION_RESULT_FOLLOWME_DISCONNECT_TIME_TOOLONG = new DJIMissionManagerError("The disconnect time of follow me mission is too long");
    public static final DJIMissionManagerError MISSION_RESULT_FOLLOWME_DISTANCE_TOO_LARGE = new DJIMissionManagerError("Distance between the aircraft and mobile phone is beyond acceptable limit(must be lower than 20000m)");
    public static final DJIMissionManagerError MISSION_RESULT_FOLLOWME_GIMBAL_PITCH_ERROR = new DJIMissionManagerError("The initial pitch angle of gimbal is too large");
    public static final DJIMissionManagerError MISSION_RESULT_GPS_NOT_READY = new DJIMissionManagerError("GPS of aircraft is not ready");
    public static final DJIMissionManagerError MISSION_RESULT_GPS_SIGNAL_WEAK = new DJIMissionManagerError("The GPS signal of the aircraft is weak");
    public static final DJIMissionManagerError MISSION_RESULT_HIGH_PRIORITY_MISSION_EXECUTING = new DJIMissionManagerError("A higher priority mission is executing");
    public static final DJIMissionManagerError MISSION_RESULT_HOMEPOINT_DIRECTION_UNKNOWN = new DJIMissionManagerError("The direction of homepoint is unknown");
    public static final DJIMissionManagerError MISSION_RESULT_HOMEPOINT_LOCATION_INVALID = new DJIMissionManagerError("The latitude and longitude of homepoint are invalid");
    public static final DJIMissionManagerError MISSION_RESULT_HOMEPOINT_NOT_PAUSED = new DJIMissionManagerError("The homepoint is not paused");
    public static final DJIMissionManagerError MISSION_RESULT_HOMEPOINT_NOT_RECORDED = new DJIMissionManagerError("The home point of aircraft is not recorded");
    public static final DJIMissionManagerError MISSION_RESULT_HOMEPOINT_PAUSED = new DJIMissionManagerError("The homepoint is paused");
    public static final DJIMissionManagerError MISSION_RESULT_HOMEPOINT_VALUE_INVALID = new DJIMissionManagerError("The homepoint is not a valid float value");
    public static final DJIMissionManagerError MISSION_RESULT_IN_NOVICE_MODE = new DJIMissionManagerError("The aircraft is in novice mode now");
    public static final DJIMissionManagerError MISSION_RESULT_IOC_TYPE_UNKNOWN = new DJIMissionManagerError("The type of IOC is unknown");
    public static final DJIMissionManagerError MISSION_RESULT_IOC_WORKING = new DJIMissionManagerError("The IOC mode is working");
    public static final DJIMissionManagerError MISSION_RESULT_IS_FLYING = new DJIMissionManagerError("Aircraft is flying");
    public static final DJIMissionManagerError MISSION_RESULT_KEY_LEVEL_LOW = new DJIMissionManagerError("The API key provided to you is not at the correct permission level");
    public static final DJIMissionManagerError MISSION_RESULT_LOW_BATTERY = new DJIMissionManagerError("Low battery level warning");
    public static final DJIMissionManagerError MISSION_RESULT_MC_MODE_ERROR = new DJIMissionManagerError("The mode of main controller is error");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_ACROSS_NOFLYZONE = new DJIMissionManagerError("The mission is across the no fly zone");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_CONDITION_NOT_SATISFIED = new DJIMissionManagerError("The condition of mission is not satisfied");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_CONFLICT = new DJIMissionManagerError("There is a conflicting setting in the mission");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_ENTRYPOINT_INVALID = new DJIMissionManagerError("The entry point of mission is invalid");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_ESTIMATE_TIME_TOO_LONG = new DJIMissionManagerError("The estimated time for the mission is too long");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_HEADING_MODE_INVALID = new DJIMissionManagerError("The heading mode of mission is invalid");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_INFO_INVALID = new DJIMissionManagerError("The information of mission is invalid");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_NOT_EXIST = new DJIMissionManagerError("The mission does not exist");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_NOT_INIT = new DJIMissionManagerError("The mission is not initialized");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_PARAM_INVALID = new DJIMissionManagerError("The parameters of the mission are invalid");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_RADIUS_INVALID = new DJIMissionManagerError("The radius of mission is invalid");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_RADIUS_OVERLIMITED = new DJIMissionManagerError("The radius of mission is over the acceptable limit");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_RESUME_FAILED = new DJIMissionManagerError("Failed to resume the mission");
    public static final DJIMissionManagerError MISSION_RESULT_MISSION_SPEED_TOO_LARGE = new DJIMissionManagerError("The speed of mission is too large");
    public static final DJIMissionManagerError MISSION_RESULT_MODE_ERROR = new DJIMissionManagerError("The control mode of the aircraft is not in the correct state");
    public static final DJIMissionManagerError MISSION_RESULT_MOTOR_NOT_START = new DJIMissionManagerError("The aircraft's motor has not started");
    public static final DJIMissionManagerError MISSION_RESULT_NAVIGATION_IS_NOT_OPEN = new DJIMissionManagerError("Navigation is not open");
    public static final DJIMissionManagerError MISSION_RESULT_NOT_AUTO_MODE = new DJIMissionManagerError("Aircraft is not in auto mode");
    public static final DJIMissionManagerError MISSION_RESULT_NO_VIDEO_FEED = new DJIMissionManagerError("No live video feed is captured for the ActiveTrack Mission. ");
    public static final DJIMissionManagerError MISSION_RESULT_POINTING_AIRCRAFT_NOT_IN_THE_AIR = new DJIMissionManagerError("The aircraft is not in the air. Please take off first.");
    public static final DJIMissionManagerError MISSION_RESULT_RC_MODE_ERROR = new DJIMissionManagerError("Mode error, please make sure the remote controller's mode switch is in 'F' mode.");
    public static final DJIMissionManagerError MISSION_RESULT_REACH_ALTITUDE_LOWER_BOUND = new DJIMissionManagerError("The aircraft reaches the altitude lower bound of the TapFly Mission.");
    public static final DJIMissionManagerError MISSION_RESULT_REACH_FLIGHT_LIMITATION = new DJIMissionManagerError("The aircraft has reached the flight limitation.");
    public static final DJIMissionManagerError MISSION_RESULT_TAKEOFF = new DJIMissionManagerError("Aircraft is taking off");
    public static final DJIMissionManagerError MISSION_RESULT_TIMEOUT = new DJIMissionManagerError("Execution of this process has timed out");
    public static final DJIMissionManagerError MISSION_RESULT_TOO_CLOSE_TO_HOMEPOINT = new DJIMissionManagerError("Aircraft is too close to home point");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_GIMBAL_PITCH_TOO_LOW = new DJIMissionManagerError("The gimbal pitch is too low.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_OBSTACLE_DETECTED = new DJIMissionManagerError("Obstacles are detected.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_PAUSED_BY_USER = new DJIMissionManagerError("Mission is paused by user.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_RECT_TOO_LARGE = new DJIMissionManagerError("The tracking rectangle is too large.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_RECT_TOO_SMALL = new DJIMissionManagerError("The tracking rectangle is too small.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_TARGET_LOST = new DJIMissionManagerError("The tracking target is lost.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_TARGET_LOW_CONFIDENCE = new DJIMissionManagerError("The ActiveTrack mission is too unsure the tracking object and confirmation is required.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_TARGET_NOT_ENOUGH_FEATURE = new DJIMissionManagerError("The tracking target doesn't have enough features to lock onto.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_TARGET_SHAKING = new DJIMissionManagerError("The tracking target is shaking too much.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_TARGET_TOO_CLOSE = new DJIMissionManagerError("The tracking target is too close to the aircraft.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_TARGET_TOO_FAR = new DJIMissionManagerError("The tracking target is too far away from the aircraft.");
    public static final DJIMissionManagerError MISSION_RESULT_TRACKING_TARGET_TOO_HIGH = new DJIMissionManagerError("The tracking target is too high.");
    public static final DJIMissionManagerError MISSION_RESULT_UNKNOWN = new DJIMissionManagerError("Unknown result");
    public static final DJIMissionManagerError MISSION_RESULT_UNSUPPORTED_NAVIGATION_FOR_THE_PRODUCT = new DJIMissionManagerError("Navigation in the product is unsupported");
    public static final DJIMissionManagerError MISSION_RESULT_UPLOAD_WAYPOINT_NUM_MAX_LIMIT = new DJIMissionManagerError("Waypoint mission has reached the maximum points limit");
    public static final DJIMissionManagerError MISSION_RESULT_USER_STOPPED = new DJIMissionManagerError("Mission was stoped by the user.");
    public static final DJIMissionManagerError MISSION_RESULT_VIDEO_FRAMERATE_TOO_LOW = new DJIMissionManagerError("The frame rate of the live video feed is too low.");
    public static final DJIMissionManagerError MISSION_RESULT_VISION_DATA_ABNORMAL = new DJIMissionManagerError("The data from the vision system is abnormal.");
    public static final DJIMissionManagerError MISSION_RESULT_VISION_SENSOR_LOW_QUALITY = new DJIMissionManagerError("The quality of vision sensor is low.");
    public static final DJIMissionManagerError MISSION_RESULT_VISION_SENSOR_OVEREXPOSED = new DJIMissionManagerError("The vision sensors are overexposed.");
    public static final DJIMissionManagerError MISSION_RESULT_VISION_SENSOR_UNDEREXPOSED = new DJIMissionManagerError("The vision sensors are underexposed.");
    public static final DJIMissionManagerError MISSION_RESULT_VISION_SYSTEM_ERROR = new DJIMissionManagerError("The vision system encounters system error.");
    public static final DJIMissionManagerError MISSION_RESULT_VISION_SYSTEM_NEED_CALIBRATION = new DJIMissionManagerError("The vision system requires calibration.");
    public static final DJIMissionManagerError MISSION_RESULT_VISION_SYSTEM_NOT_AUTHORIZED = new DJIMissionManagerError("The vision system cannot get the authorization to control the aircraft. ");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINTS_UPLOADING = new DJIMissionManagerError("The waypoints are still uploading");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_ACTION_PARAM_INVALID = new DJIMissionManagerError("The parameter of waypoint action is invalid");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_DAMPING_CHECK_FAILED = new DJIMissionManagerError("The damping check is failed");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_DISTANCE_TOO_CLOSE = new DJIMissionManagerError("The waypoint distance is too close");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_DISTANCE_TOO_LONG = new DJIMissionManagerError("The waypoint distance is too long");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_IDLE_VELOCITY_INVALID = new DJIMissionManagerError("The idle velocity is invalid");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_INDEX_OVERRANGE = new DJIMissionManagerError("The index of waypoint is over range");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_INFO_INVALID = new DJIMissionManagerError("The information of waypoint is invalid");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_MISSION_INFO_NOT_UPLOADED = new DJIMissionManagerError("The info of waypoint mission is not completely uploaded");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_NOT_RUNNING = new DJIMissionManagerError("The waypoint mission is not running");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_REQUEST_IS_RUNNING = new DJIMissionManagerError("The waypoint request is running");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_TOTAL_TRACE_TOO_LONG = new DJIMissionManagerError("The total trace of waypoint is too long");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_TRACE_TOO_LONG = new DJIMissionManagerError("The trace of waypoint is too long");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_UPLOADING = new DJIMissionManagerError("Waypoint mission is uploading");
    public static final DJIMissionManagerError MISSION_RESULT_WAYPOINT_UPLOAD_NOT_COMPLETE = new DJIMissionManagerError("The waypoint uploading is not complete");
    public static final DJIMissionManagerError MISSION_RESULT_WRONG_CMD = new DJIMissionManagerError("The command is wrong");
    private String mDescription;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dji$midware$data$config$P3$Ccode = new int[a.values().length];
    }

    private DJIMissionManagerError() {
    }

    private DJIMissionManagerError(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public static DJIError getDJIError(a aVar) {
        if (COMMON_UNKNOWN != DJIError.getDJIError(aVar)) {
            return DJIError.getDJIError(aVar);
        }
        int i = AnonymousClass1.$SwitchMap$dji$midware$data$config$P3$Ccode[aVar.ordinal()];
        return COMMON_UNKNOWN;
    }
}
