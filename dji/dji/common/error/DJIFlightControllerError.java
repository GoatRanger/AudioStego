package dji.common.error;

import com.alipay.sdk.j.f;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.midware.data.config.P3.a;

public class DJIFlightControllerError extends DJIError {
    public static final DJIFlightControllerError AIRCRAFT_FLYING_ERROR = new DJIFlightControllerError("The aircraft is flying and the motors could not be shut down.");
    public static final DJIFlightControllerError FAIL_TO_ENTER_TRANSPORT_MODE_WHEN_MOTORS_ON = new DJIFlightControllerError("When the motors are on, the aircraft could not get into transport mode.");
    public static final DJIFlightControllerError FLIGHT_CONTROLLER_COULD_NOT_ENTER_TRANSPORT_MODE = new DJIFlightControllerError("Aircraft could not enter transport mode, since the gimbal is still connected");
    public static final DJIFlightControllerError FLIGHT_CONTROLLER_GPS_IS_NOT_HIGH_ENOUGH = new DJIFlightControllerError("GPS level is not high enough to allow flight controller to obtain accurate location.");
    public static final DJIFlightControllerError FLIGHT_CONTROLLER_INTERNAL_ERROR_ABOUT_UNEXPECTED_NULL_OBJECT = new DJIFlightControllerError(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
    public static final DJIFlightControllerError FLIGHT_CONTROLLER_INVALID_PARAMETER = new DJIFlightControllerError("FlightController received invalid parameters");
    public static final DJIFlightControllerError FLIGHT_CONTROLLER_OBJECT_EMPTY_OR_NOT_AVAILABLE = new DJIFlightControllerError(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
    public static final DJIFlightControllerError FLIGHT_CONTROLLER_UNSUPPORT = new DJIFlightControllerError("Unsupport");
    public static final DJIFlightControllerError GOHOME_ALTITUDE_TOO_HIGH = new DJIFlightControllerError("The go home altitude is too high (higher than 500m).");
    public static final DJIFlightControllerError GOHOME_ALTITUDE_TOO_LOW = new DJIFlightControllerError("The go home altitude is too low (lower than 20m).");
    public static final DJIFlightControllerError HOMEPOINT_TOO_FAR = new DJIFlightControllerError("Error! A location is valid if it is within 30M of initial take-off location OR current RC location as shown by RC GPS or mobile device GPS.");
    public static final DJIFlightControllerError IMU_CALIBRATION_ERROR_IN_THE_AIR_OR_MOTORS_ON = new DJIFlightControllerError("IMU calibration is now allowed if the aircraft's motors are on or the aircraft is in the air.");
    public static final DJIFlightControllerError MISSION_RESULT_AIRCRAFT_GOINGHOME = new DJIFlightControllerError("The aircraft is going home");
    public static final DJIFlightControllerError MISSION_RESULT_AIRCRAFT_IN_NOFLYZONE = new DJIFlightControllerError("The aircraft is in the no fly zone");
    public static final DJIFlightControllerError MISSION_RESULT_AIRCRAFT_LANDING = new DJIFlightControllerError("The aircraft is landing");
    public static final DJIFlightControllerError MISSION_RESULT_AIRCRAFT_NOT_IN_THE_AIR = new DJIFlightControllerError("The aircraft is not in the air");
    public static final DJIFlightControllerError MISSION_RESULT_AIRCRAFT_STARTING_MOTOR = new DJIFlightControllerError("The aircraft is starting the motor");
    public static final DJIFlightControllerError MISSION_RESULT_AIRCRAFT_TAKINGOFF = new DJIFlightControllerError("The aircraft is taking off");
    public static final DJIFlightControllerError MISSION_RESULT_ALTITUDE_TOO_HIGH = new DJIFlightControllerError("The altitude is too high");
    public static final DJIFlightControllerError MISSION_RESULT_ALTITUDE_TOO_LOW = new DJIFlightControllerError("The altitude is too low");
    public static final DJIFlightControllerError MISSION_RESULT_BEGAN = new DJIFlightControllerError("began");
    public static final DJIFlightControllerError MISSION_RESULT_CANCELED = new DJIFlightControllerError("canceled");
    public static final DJIFlightControllerError MISSION_RESULT_DISTANCE_FROM_MISSION_TARGET_TOO_LONG = new DJIFlightControllerError("Navigation in the product is unsupported");
    public static final DJIFlightControllerError MISSION_RESULT_FAILED = new DJIFlightControllerError(f.b);
    public static final DJIFlightControllerError MISSION_RESULT_FOLLOWME_DISCONNECT_TIME_TOO_LONG = new DJIFlightControllerError("The disconnect time of follow me mission is too long");
    public static final DJIFlightControllerError MISSION_RESULT_FOLLOWME_DISTANCE_TOO_LARGE = new DJIFlightControllerError("Distance between the aircraft and mobile phone is beyond acceptable limit(must be lower than 20000m)");
    public static final DJIFlightControllerError MISSION_RESULT_FOLLOWME_GIMBAL_PITCH_ERROR = new DJIFlightControllerError("The initial pitch angle of gimbal is too large");
    public static final DJIFlightControllerError MISSION_RESULT_GPS_NOT_READY = new DJIFlightControllerError("GPS of aircraft is not ready");
    public static final DJIFlightControllerError MISSION_RESULT_GPS_SIGNAL_WEAK = new DJIFlightControllerError("The GPS signal of the aircraft is weak");
    public static final DJIFlightControllerError MISSION_RESULT_HIGH_PRIORITY_MISSION_EXECUTING = new DJIFlightControllerError("A higher priority mission is executing");
    public static final DJIFlightControllerError MISSION_RESULT_HOMEPOINT_DIRECTION_UNKNOWN = new DJIFlightControllerError("The direction of the home point is unknown");
    public static final DJIFlightControllerError MISSION_RESULT_HOMEPOINT_LOCATION_INVALID = new DJIFlightControllerError("The latitude and longitude of the home point are invalid");
    public static final DJIFlightControllerError MISSION_RESULT_HOMEPOINT_NOT_PAUSED = new DJIFlightControllerError("The home point is not paused");
    public static final DJIFlightControllerError MISSION_RESULT_HOMEPOINT_NOT_RECORDED = new DJIFlightControllerError("The home point of aircraft is not recorded");
    public static final DJIFlightControllerError MISSION_RESULT_HOMEPOINT_PAUSED = new DJIFlightControllerError("The home point is paused");
    public static final DJIFlightControllerError MISSION_RESULT_HOMEPOINT_VALUE_INVALID = new DJIFlightControllerError("The home point is not a valid float value");
    public static final DJIFlightControllerError MISSION_RESULT_IN_NOVICE_MODE = new DJIFlightControllerError("The aircraft is in novice mode now");
    public static final DJIFlightControllerError MISSION_RESULT_IOC_TYPE_UNKNOWN = new DJIFlightControllerError("The type of IOC is unknown");
    public static final DJIFlightControllerError MISSION_RESULT_IOC_WORKING = new DJIFlightControllerError("The IOC mode is working");
    public static final DJIFlightControllerError MISSION_RESULT_IS_FLYING = new DJIFlightControllerError("Aircraft is flying");
    public static final DJIFlightControllerError MISSION_RESULT_KEY_LEVEL_LOW = new DJIFlightControllerError("The API key provided to you is not at the correct permission level");
    public static final DJIFlightControllerError MISSION_RESULT_LOW_BATTERY = new DJIFlightControllerError("Low battery level warning");
    public static final DJIFlightControllerError MISSION_RESULT_MC_MODE_ERROR = new DJIFlightControllerError("The mode of the main controller is error");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_ACROSS_NOFLYZONE = new DJIFlightControllerError("The mission is across the no fly zone");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_CONDITION_NOT_SATISFIED = new DJIFlightControllerError("The condition of the mission is not satisfied");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_CONFLICT = new DJIFlightControllerError("There is a conflicting setting in the mission");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_ENTRYPOINT_INVALID = new DJIFlightControllerError("The entry point of the mission is invalid");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_ESTIMATE_TIME_TOO_LONG = new DJIFlightControllerError("The estimated time for the mission is too long");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_HEADING_MODE_INVALID = new DJIFlightControllerError("The heading mode of the mission is invalid");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_INFO_INVALID = new DJIFlightControllerError("The information of the mission is invalid");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_NOT_EXIST = new DJIFlightControllerError("The mission does not exist");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_NOT_INIT = new DJIFlightControllerError("The mission is not initialized");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_PARAM_INVALID = new DJIFlightControllerError("The parameters of the mission are invalid");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_RADIUS_INVALID = new DJIFlightControllerError("The radius of the mission is invalid");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_RADIUS_OVERLIMITED = new DJIFlightControllerError("The radius of mission is over the acceptable limit");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_RESUME_FAILED = new DJIFlightControllerError("Failed to resume the mission");
    public static final DJIFlightControllerError MISSION_RESULT_MISSION_SPEED_TOO_LARGE = new DJIFlightControllerError("The speed of the mission is too large");
    public static final DJIFlightControllerError MISSION_RESULT_MODE_ERROR = new DJIFlightControllerError("The control mode of the aircraft is not in the correct state");
    public static final DJIFlightControllerError MISSION_RESULT_MOTOR_NOT_START = new DJIFlightControllerError("The aircraft's motor has not started");
    public static final DJIFlightControllerError MISSION_RESULT_NAVIGATION_IS_NOT_OPEN = new DJIFlightControllerError("Navigation is not open");
    public static final DJIFlightControllerError MISSION_RESULT_NOT_AUTO_MODE = new DJIFlightControllerError("Aircraft is not in the auto mode");
    public static final DJIFlightControllerError MISSION_RESULT_RC_MODE_ERROR = new DJIFlightControllerError("Mode error, please make sure the remote controller's mode switch is in 'F' or 'P' mode. For Phantom 4, all of the intelligent missions are allowed to execute in the 'P' mode");
    public static final DJIFlightControllerError MISSION_RESULT_TAKEOFF = new DJIFlightControllerError("Aircraft is taking off");
    public static final DJIFlightControllerError MISSION_RESULT_TIMEOUT = new DJIFlightControllerError("Execution of this process has timed out");
    public static final DJIFlightControllerError MISSION_RESULT_TOO_CLOSE_TO_HOMEPOINT = new DJIFlightControllerError("Aircraft is too close to home point");
    public static final DJIFlightControllerError MISSION_RESULT_UNKNOWN = new DJIFlightControllerError("Unknown result");
    public static final DJIFlightControllerError MISSION_RESULT_UNSUPPORTED_NAVIGATION_FOR_THE_PRODUCT = new DJIFlightControllerError("Navigation in the product is unsupported");
    public static final DJIFlightControllerError MISSION_RESULT_UPLOAD_WAYPOINT_NUM_MAX_LIMIT = new DJIFlightControllerError("Waypoint mission has reached the maximum points limit");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINTS_UPLOADING = new DJIFlightControllerError("The waypoints are still uploading");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_ACTION_PARAM_INVALID = new DJIFlightControllerError("The parameter of the waypoint action is invalid");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_DAMPING_CHECK_FAILED = new DJIFlightControllerError("The damping check is failed");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_DISTANCE_TOO_CLOSE = new DJIFlightControllerError("The waypoint distance is too close");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_DISTANCE_TOO_LONG = new DJIFlightControllerError("The waypoint distance is too long");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_IDLE_VELOCITY_INVALID = new DJIFlightControllerError("The idle velocity is invalid");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_INDEX_OVERRANGE = new DJIFlightControllerError("The index of the waypoint is over range");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_INFO_INVALID = new DJIFlightControllerError("The information of the waypoint is invalid");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_MISSION_INFO_NOT_UPLOADED = new DJIFlightControllerError("The info of the waypoint mission is not completely uploaded");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_NOT_RUNNING = new DJIFlightControllerError("The waypoint mission is not running");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_REQUEST_IS_RUNNING = new DJIFlightControllerError("The waypoint request is running");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_TOTAL_TRACE_TOO_LONG = new DJIFlightControllerError("The total trace of the waypoint is too long");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_TRACE_TOO_LONG = new DJIFlightControllerError("The trace of the waypoint is too long");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_UPLOADING = new DJIFlightControllerError("Waypoint mission is uploading");
    public static final DJIFlightControllerError MISSION_RESULT_WAYPOINT_UPLOAD_NOT_COMPLETE = new DJIFlightControllerError("The waypoint uploading is not complete");
    public static final DJIFlightControllerError MISSION_RESULT_WRONG_CMD = new DJIFlightControllerError("The command is wrong");
    public static final DJIFlightControllerError RTK_BS_ANTENNA_ERROR = new DJIFlightControllerError("The RTK base station antenna is broken.");
    public static final DJIFlightControllerError RTK_BS_COORDINATE_RESETED = new DJIFlightControllerError("The RTK base station location has been reseted");
    public static final DJIFlightControllerError RTK_CONNECTION_BROKEN = new DJIFlightControllerError("The RTK connection is lost.");
    public static final DJIFlightControllerError RTK_START_ERROR = new DJIFlightControllerError("The RTK starting is failed.");
    public static final DJIFlightControllerError UNABLE_TO_TAKE_OFF = new DJIFlightControllerError("If the motors are already turned on or the aircraft is already flying, the takeoff() did not execute.");
    private String mDescription;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$dji$midware$data$config$P3$Ccode = new int[a.values().length];

        static {
            try {
                $SwitchMap$dji$midware$data$config$P3$Ccode[a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private DJIFlightControllerError() {
    }

    private DJIFlightControllerError(String str) {
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
        switch (AnonymousClass1.$SwitchMap$dji$midware$data$config$P3$Ccode[aVar.ordinal()]) {
            case 1:
                return COMMON_TIMEOUT;
            default:
                return COMMON_UNKNOWN;
        }
    }
}
