package dji.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import dji.pilot.usercenter.mode.n;
import dji.sdksharedlib.e.c;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class LocationUtils {
    private static Location currentBestLocation = null;
    private static LocationManager locationManager = null;

    public static boolean checkValidGPSCoordinate(double d, double d2) {
        return d >= -90.0d && d <= 90.0d && d2 >= -180.0d && d2 <= 180.0d;
    }

    public static boolean validateLatitude(double d) {
        return d > 90.0d || d < -90.0d;
    }

    public static boolean validateLongitude(double d) {
        return d > 180.0d || d < -180.0d;
    }

    public static Location getLastBestLocation() {
        Context context;
        ClassNotFoundException classNotFoundException;
        NoSuchMethodException noSuchMethodException;
        InvocationTargetException invocationTargetException;
        IllegalAccessException illegalAccessException;
        if (locationManager == null) {
            try {
                Context applicationContext = c.a().getApplicationContext();
                try {
                    locationManager = (LocationManager) c.a().getSystemService(n.C);
                    context = applicationContext;
                } catch (ClassNotFoundException e) {
                    ClassNotFoundException classNotFoundException2 = e;
                    context = applicationContext;
                    classNotFoundException = classNotFoundException2;
                    classNotFoundException.printStackTrace();
                    return context.checkCallingOrSelfPermission("gps") == 0 ? null : null;
                } catch (NoSuchMethodException e2) {
                    NoSuchMethodException noSuchMethodException2 = e2;
                    context = applicationContext;
                    noSuchMethodException = noSuchMethodException2;
                    noSuchMethodException.printStackTrace();
                    if (context.checkCallingOrSelfPermission("gps") == 0) {
                    }
                } catch (InvocationTargetException e3) {
                    InvocationTargetException invocationTargetException2 = e3;
                    context = applicationContext;
                    invocationTargetException = invocationTargetException2;
                    invocationTargetException.printStackTrace();
                    if (context.checkCallingOrSelfPermission("gps") == 0) {
                    }
                } catch (IllegalAccessException e4) {
                    IllegalAccessException illegalAccessException2 = e4;
                    context = applicationContext;
                    illegalAccessException = illegalAccessException2;
                    illegalAccessException.printStackTrace();
                    if (context.checkCallingOrSelfPermission("gps") == 0) {
                    }
                }
            } catch (ClassNotFoundException e5) {
                classNotFoundException = e5;
                context = null;
                classNotFoundException.printStackTrace();
                if (context.checkCallingOrSelfPermission("gps") == 0) {
                }
            } catch (NoSuchMethodException e22) {
                noSuchMethodException = e22;
                context = null;
                noSuchMethodException.printStackTrace();
                if (context.checkCallingOrSelfPermission("gps") == 0) {
                }
            } catch (InvocationTargetException e32) {
                invocationTargetException = e32;
                context = null;
                invocationTargetException.printStackTrace();
                if (context.checkCallingOrSelfPermission("gps") == 0) {
                }
            } catch (IllegalAccessException e42) {
                illegalAccessException = e42;
                context = null;
                illegalAccessException.printStackTrace();
                if (context.checkCallingOrSelfPermission("gps") == 0) {
                }
            }
        }
        context = null;
        if (context.checkCallingOrSelfPermission("gps") == 0 && context.checkCallingOrSelfPermission("network") == 0) {
            long time;
            long time2;
            Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
            Location lastKnownLocation2 = locationManager.getLastKnownLocation("network");
            if (lastKnownLocation != null) {
                time = lastKnownLocation.getTime();
            } else {
                time = 0;
            }
            if (lastKnownLocation2 != null) {
                time2 = lastKnownLocation2.getTime();
            } else {
                time2 = 0;
            }
            if (0 < time - time2) {
                currentBestLocation = lastKnownLocation;
            } else {
                currentBestLocation = lastKnownLocation2;
            }
            if (currentBestLocation != null) {
                return currentBestLocation;
            }
            if (lastKnownLocation != null) {
                return lastKnownLocation;
            }
            if (lastKnownLocation2 != null) {
                return lastKnownLocation2;
            }
            return null;
        }
    }

    public static boolean checkLocationPermission() {
        int checkCallingOrSelfPermission;
        try {
            checkCallingOrSelfPermission = c.a().getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            checkCallingOrSelfPermission = 0;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            checkCallingOrSelfPermission = 0;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            checkCallingOrSelfPermission = 0;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            checkCallingOrSelfPermission = 0;
        }
        if (checkCallingOrSelfPermission == 0) {
            return true;
        }
        return false;
    }

    @SuppressLint({"FloatMath"})
    public static double gps2m(double d, double d2, double d3, double d4) {
        double toRadians = Math.toRadians(d3 - d);
        double toRadians2 = Math.toRadians(d4 - d2);
        toRadians = (Math.sin(toRadians / 2.0d) * Math.sin(toRadians / 2.0d)) + (Math.sin(toRadians2 / 2.0d) * ((Math.cos(Math.toRadians(d)) * Math.cos(Math.toRadians(d3))) * Math.sin(toRadians2 / 2.0d)));
        return (double) ((float) (6371000.0d * (Math.atan2(Math.sqrt(toRadians), Math.sqrt(1.0d - toRadians)) * 2.0d)));
    }

    public static double DegreeToRadian(double d) {
        return (3.141592653589793d * d) / 180.0d;
    }

    public static boolean isInUSA() {
        return Locale.getDefault().getISO3Country().equals("USA");
    }

    public static double getDistanceInMeterFromTwoGPSLocations(double d, double d2, double d3, double d4) {
        double degreeToRadius = degreeToRadius(d3 - d);
        double degreeToRadius2 = degreeToRadius(d4 - d2);
        degreeToRadius = (Math.sin(degreeToRadius / 2.0d) * ((Math.cos(degreeToRadius(d2)) * Math.cos(degreeToRadius(d4))) * Math.sin(degreeToRadius / 2.0d))) + (Math.sin(degreeToRadius2 / 2.0d) * Math.sin(degreeToRadius2 / 2.0d));
        return (Math.atan2(Math.sqrt(degreeToRadius), Math.sqrt(1.0d - degreeToRadius)) * 2.0d) * 6371000.0d;
    }

    private static double degreeToRadius(double d) {
        return 0.017453292519943295d * d;
    }

    public static double RadianToDegree(double d) {
        return (180.0d * d) / 3.141592653589793d;
    }
}
