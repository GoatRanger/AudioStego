package com.e;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.DPoint;
import com.e.a.c;
import dji.pilot.usercenter.mode.n;

public class cp {
    Handler a;
    Context b;
    LocationManager c;
    AMapLocationClientOption d;
    long e = 1000;
    long f = 0;
    LocationListener g = new LocationListener(this) {
        final /* synthetic */ cp a;

        {
            this.a = r1;
        }

        public void onLocationChanged(Location location) {
            if (location != null) {
                try {
                    Bundle extras = location.getExtras();
                    int i = extras != null ? extras.getInt("satellites") : 0;
                    if (i <= 0 && !this.a.d.isMockEnable()) {
                        return;
                    }
                    if (br.b() - this.a.f > this.a.e) {
                        AMapLocation aMapLocation;
                        if (bc.a(location.getLatitude(), location.getLongitude()) && this.a.d.isOffset()) {
                            aMapLocation = new AMapLocation(location);
                            aMapLocation.setLocationType(1);
                            DPoint a = cr.a(this.a.b, location.getLongitude(), location.getLatitude());
                            aMapLocation.setLatitude(a.getLatitude());
                            aMapLocation.setLongitude(a.getLongitude());
                        } else {
                            aMapLocation = new AMapLocation(location);
                            aMapLocation.setLatitude(location.getLatitude());
                            aMapLocation.setLongitude(location.getLongitude());
                            aMapLocation.setLocationType(1);
                        }
                        aMapLocation.setSatellites(i);
                        Message message = new Message();
                        message.obj = aMapLocation;
                        message.what = 2;
                        if (this.a.a != null) {
                            this.a.a.sendMessage(message);
                        }
                        if (!bc.r) {
                            bc.r = true;
                            bq.a(this.a.b, "pref", "ded", true);
                        }
                        this.a.f = br.b();
                    } else if (this.a.a != null) {
                        this.a.a.sendEmptyMessage(5);
                    }
                } catch (Throwable th) {
                    bc.a(th, "GPSLocation", "onLocationChanged");
                }
            }
        }

        public void onProviderDisabled(String str) {
            try {
                if ("gps".equals(str) && this.a.a != null) {
                    this.a.a.sendEmptyMessage(3);
                }
            } catch (Throwable th) {
                bc.a(th, "GPSLocation", "onProviderDisabled");
            }
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0 || i == 1) {
                try {
                    if (this.a.a != null) {
                        this.a.a.sendEmptyMessage(3);
                    }
                } catch (Throwable th) {
                    bc.a(th, "GPSLocation", "onStatusChanged");
                }
            }
        }
    };

    public cp(Context context, c cVar) {
        this.b = context;
        this.a = cVar;
        this.c = (LocationManager) this.b.getSystemService(n.C);
    }

    public void a() {
        if (this.c != null && this.g != null) {
            this.c.removeUpdates(this.g);
        }
    }

    void a(long j, float f) {
        try {
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                myLooper = this.b.getMainLooper();
            }
            this.e = j;
            this.c.requestLocationUpdates("gps", 1000, f, this.g, myLooper);
        } catch (SecurityException e) {
            e.printStackTrace();
            if (AMapLocationMode.Device_Sensors.equals(this.d.getLocationMode())) {
                Message obtain = Message.obtain();
                AMapLocation aMapLocation = new AMapLocation("");
                aMapLocation.setProvider("gps");
                aMapLocation.setErrorCode(12);
                aMapLocation.setLocationType(1);
                obtain.what = 2;
                obtain.obj = aMapLocation;
                if (this.a != null) {
                    this.a.sendMessage(obtain);
                }
            }
        } catch (Throwable th) {
            bc.a(th, "GPSLocation", "requestLocationUpdates part2");
        }
    }

    public void a(AMapLocationClientOption aMapLocationClientOption) {
        this.d = aMapLocationClientOption;
        a(this.d.getInterval(), 0.0f);
    }
}
