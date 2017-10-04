package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.zzb;
import com.google.android.gms.maps.model.internal.zzc;
import com.google.android.gms.maps.model.internal.zzf;
import com.google.android.gms.maps.model.internal.zzg;
import com.google.android.gms.maps.model.internal.zzh;

public abstract class IGoogleMapDelegate$zza extends Binder implements IGoogleMapDelegate {

    private static class zza implements IGoogleMapDelegate {
        private IBinder zznJ;

        zza(IBinder iBinder) {
            this.zznJ = iBinder;
        }

        public zzb addCircle(CircleOptions circleOptions) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (circleOptions != null) {
                    obtain.writeInt(1);
                    circleOptions.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(35, obtain, obtain2, 0);
                obtain2.readException();
                zzb zzcQ = com.google.android.gms.maps.model.internal.zzb.zza.zzcQ(obtain2.readStrongBinder());
                return zzcQ;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public zzc addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (groundOverlayOptions != null) {
                    obtain.writeInt(1);
                    groundOverlayOptions.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(12, obtain, obtain2, 0);
                obtain2.readException();
                zzc zzcR = com.google.android.gms.maps.model.internal.zzc.zza.zzcR(obtain2.readStrongBinder());
                return zzcR;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public zzf addMarker(MarkerOptions markerOptions) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (markerOptions != null) {
                    obtain.writeInt(1);
                    markerOptions.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(11, obtain, obtain2, 0);
                obtain2.readException();
                zzf zzcU = com.google.android.gms.maps.model.internal.zzf.zza.zzcU(obtain2.readStrongBinder());
                return zzcU;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public zzg addPolygon(PolygonOptions polygonOptions) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (polygonOptions != null) {
                    obtain.writeInt(1);
                    polygonOptions.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(10, obtain, obtain2, 0);
                obtain2.readException();
                zzg zzcV = com.google.android.gms.maps.model.internal.zzg.zza.zzcV(obtain2.readStrongBinder());
                return zzcV;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (polylineOptions != null) {
                    obtain.writeInt(1);
                    polylineOptions.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(9, obtain, obtain2, 0);
                obtain2.readException();
                IPolylineDelegate zzcW = com.google.android.gms.maps.model.internal.IPolylineDelegate.zza.zzcW(obtain2.readStrongBinder());
                return zzcW;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public zzh addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (tileOverlayOptions != null) {
                    obtain.writeInt(1);
                    tileOverlayOptions.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(13, obtain, obtain2, 0);
                obtain2.readException();
                zzh zzcX = com.google.android.gms.maps.model.internal.zzh.zza.zzcX(obtain2.readStrongBinder());
                return zzcX;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void animateCamera(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                this.zznJ.transact(5, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void animateCameraWithCallback(zzd com_google_android_gms_dynamic_zzd, zzb com_google_android_gms_maps_internal_zzb) throws RemoteException {
            IBinder iBinder = null;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                if (com_google_android_gms_maps_internal_zzb != null) {
                    iBinder = com_google_android_gms_maps_internal_zzb.asBinder();
                }
                obtain.writeStrongBinder(iBinder);
                this.zznJ.transact(6, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void animateCameraWithDurationAndCallback(zzd com_google_android_gms_dynamic_zzd, int i, zzb com_google_android_gms_maps_internal_zzb) throws RemoteException {
            IBinder iBinder = null;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                obtain.writeInt(i);
                if (com_google_android_gms_maps_internal_zzb != null) {
                    iBinder = com_google_android_gms_maps_internal_zzb.asBinder();
                }
                obtain.writeStrongBinder(iBinder);
                this.zznJ.transact(7, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IBinder asBinder() {
            return this.zznJ;
        }

        public void clear() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(14, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public CameraPosition getCameraPosition() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                CameraPosition zzfh = obtain2.readInt() != 0 ? CameraPosition.CREATOR.zzfh(obtain2) : null;
                obtain2.recycle();
                obtain.recycle();
                return zzfh;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public com.google.android.gms.maps.model.internal.zzd getFocusedBuilding() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(44, obtain, obtain2, 0);
                obtain2.readException();
                com.google.android.gms.maps.model.internal.zzd zzcS = com.google.android.gms.maps.model.internal.zzd.zza.zzcS(obtain2.readStrongBinder());
                return zzcS;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void getMapAsync(zzl com_google_android_gms_maps_internal_zzl) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzl != null ? com_google_android_gms_maps_internal_zzl.asBinder() : null);
                this.zznJ.transact(53, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public int getMapType() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(15, obtain, obtain2, 0);
                obtain2.readException();
                int readInt = obtain2.readInt();
                return readInt;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public float getMaxZoomLevel() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                float readFloat = obtain2.readFloat();
                return readFloat;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public float getMinZoomLevel() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                float readFloat = obtain2.readFloat();
                return readFloat;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public Location getMyLocation() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(23, obtain, obtain2, 0);
                obtain2.readException();
                Location location = obtain2.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(obtain2) : null;
                obtain2.recycle();
                obtain.recycle();
                return location;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IProjectionDelegate getProjection() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(26, obtain, obtain2, 0);
                obtain2.readException();
                IProjectionDelegate zzcJ = com.google.android.gms.maps.internal.IProjectionDelegate.zza.zzcJ(obtain2.readStrongBinder());
                return zzcJ;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IUiSettingsDelegate getUiSettings() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(25, obtain, obtain2, 0);
                obtain2.readException();
                IUiSettingsDelegate zzcO = com.google.android.gms.maps.internal.IUiSettingsDelegate.zza.zzcO(obtain2.readStrongBinder());
                return zzcO;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isBuildingsEnabled() throws RemoteException {
            boolean z = false;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(40, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isIndoorEnabled() throws RemoteException {
            boolean z = false;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(19, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isMyLocationEnabled() throws RemoteException {
            boolean z = false;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(21, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrafficEnabled() throws RemoteException {
            boolean z = false;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(17, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void moveCamera(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                this.zznJ.transact(4, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void onCreate(Bundle bundle) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(54, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void onDestroy() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(57, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void onEnterAmbient(Bundle bundle) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(81, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void onExitAmbient() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(82, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void onLowMemory() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(58, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void onPause() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(56, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void onResume() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(55, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void onSaveInstanceState(Bundle bundle) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(60, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    bundle.readFromParcel(obtain2);
                }
                obtain2.recycle();
                obtain.recycle();
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setBuildingsEnabled(boolean z) throws RemoteException {
            int i = 0;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (z) {
                    i = 1;
                }
                obtain.writeInt(i);
                this.zznJ.transact(41, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setContentDescription(String str) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeString(str);
                this.zznJ.transact(61, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean setIndoorEnabled(boolean z) throws RemoteException {
            boolean z2 = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeInt(z ? 1 : 0);
                this.zznJ.transact(20, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z2 = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z2;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setInfoWindowAdapter(zzd com_google_android_gms_maps_internal_zzd) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzd != null ? com_google_android_gms_maps_internal_zzd.asBinder() : null);
                this.zznJ.transact(33, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(iLocationSourceDelegate != null ? iLocationSourceDelegate.asBinder() : null);
                this.zznJ.transact(24, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setMapType(int i) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeInt(i);
                this.zznJ.transact(16, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setMyLocationEnabled(boolean z) throws RemoteException {
            int i = 0;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (z) {
                    i = 1;
                }
                obtain.writeInt(i);
                this.zznJ.transact(22, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnCameraChangeListener(zze com_google_android_gms_maps_internal_zze) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zze != null ? com_google_android_gms_maps_internal_zze.asBinder() : null);
                this.zznJ.transact(27, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnIndoorStateChangeListener(zzf com_google_android_gms_maps_internal_zzf) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzf != null ? com_google_android_gms_maps_internal_zzf.asBinder() : null);
                this.zznJ.transact(45, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnInfoWindowClickListener(zzg com_google_android_gms_maps_internal_zzg) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzg != null ? com_google_android_gms_maps_internal_zzg.asBinder() : null);
                this.zznJ.transact(32, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnMapClickListener(zzi com_google_android_gms_maps_internal_zzi) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzi != null ? com_google_android_gms_maps_internal_zzi.asBinder() : null);
                this.zznJ.transact(28, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnMapLoadedCallback(zzj com_google_android_gms_maps_internal_zzj) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzj != null ? com_google_android_gms_maps_internal_zzj.asBinder() : null);
                this.zznJ.transact(42, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnMapLongClickListener(zzk com_google_android_gms_maps_internal_zzk) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzk != null ? com_google_android_gms_maps_internal_zzk.asBinder() : null);
                this.zznJ.transact(29, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnMarkerClickListener(zzm com_google_android_gms_maps_internal_zzm) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzm != null ? com_google_android_gms_maps_internal_zzm.asBinder() : null);
                this.zznJ.transact(30, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnMarkerDragListener(zzn com_google_android_gms_maps_internal_zzn) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzn != null ? com_google_android_gms_maps_internal_zzn.asBinder() : null);
                this.zznJ.transact(31, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnMyLocationButtonClickListener(zzo com_google_android_gms_maps_internal_zzo) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzo != null ? com_google_android_gms_maps_internal_zzo.asBinder() : null);
                this.zznJ.transact(37, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnMyLocationChangeListener(zzp com_google_android_gms_maps_internal_zzp) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzp != null ? com_google_android_gms_maps_internal_zzp.asBinder() : null);
                this.zznJ.transact(36, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setOnPoiClickListener(zzq com_google_android_gms_maps_internal_zzq) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzq != null ? com_google_android_gms_maps_internal_zzq.asBinder() : null);
                this.zznJ.transact(80, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setPadding(int i, int i2, int i3, int i4) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeInt(i);
                obtain.writeInt(i2);
                obtain.writeInt(i3);
                obtain.writeInt(i4);
                this.zznJ.transact(39, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void setTrafficEnabled(boolean z) throws RemoteException {
            int i = 0;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (z) {
                    i = 1;
                }
                obtain.writeInt(i);
                this.zznJ.transact(18, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void snapshot(zzw com_google_android_gms_maps_internal_zzw, zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
            IBinder iBinder = null;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                obtain.writeStrongBinder(com_google_android_gms_maps_internal_zzw != null ? com_google_android_gms_maps_internal_zzw.asBinder() : null);
                if (com_google_android_gms_dynamic_zzd != null) {
                    iBinder = com_google_android_gms_dynamic_zzd.asBinder();
                }
                obtain.writeStrongBinder(iBinder);
                this.zznJ.transact(38, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public void stopAnimation() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(8, obtain, obtain2, 0);
                obtain2.readException();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean useViewLifecycleWhenInFragment() throws RemoteException {
            boolean z = false;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.zznJ.transact(59, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public static IGoogleMapDelegate zzcm(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof IGoogleMapDelegate)) ? new zza(iBinder) : (IGoogleMapDelegate) queryLocalInterface;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        int i3 = 0;
        IBinder iBinder = null;
        float maxZoomLevel;
        boolean isTrafficEnabled;
        boolean z;
        switch (i) {
            case 1:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                CameraPosition cameraPosition = getCameraPosition();
                parcel2.writeNoException();
                if (cameraPosition != null) {
                    parcel2.writeInt(1);
                    cameraPosition.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            case 2:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                maxZoomLevel = getMaxZoomLevel();
                parcel2.writeNoException();
                parcel2.writeFloat(maxZoomLevel);
                return true;
            case 3:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                maxZoomLevel = getMinZoomLevel();
                parcel2.writeNoException();
                parcel2.writeFloat(maxZoomLevel);
                return true;
            case 4:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                moveCamera(com.google.android.gms.dynamic.zzd.zza.zzbk(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCamera(com.google.android.gms.dynamic.zzd.zza.zzbk(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCameraWithCallback(com.google.android.gms.dynamic.zzd.zza.zzbk(parcel.readStrongBinder()), com.google.android.gms.maps.internal.zzb.zza.zzck(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.zzd.zza.zzbk(parcel.readStrongBinder()), parcel.readInt(), com.google.android.gms.maps.internal.zzb.zza.zzck(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                stopAnimation();
                parcel2.writeNoException();
                return true;
            case 9:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                IPolylineDelegate addPolyline = addPolyline(parcel.readInt() != 0 ? PolylineOptions.CREATOR.zzfp(parcel) : null);
                parcel2.writeNoException();
                if (addPolyline != null) {
                    iBinder = addPolyline.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 10:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                zzg addPolygon = addPolygon(parcel.readInt() != 0 ? PolygonOptions.CREATOR.zzfo(parcel) : null);
                parcel2.writeNoException();
                if (addPolygon != null) {
                    iBinder = addPolygon.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 11:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                zzf addMarker = addMarker(parcel.readInt() != 0 ? MarkerOptions.CREATOR.zzfm(parcel) : null);
                parcel2.writeNoException();
                if (addMarker != null) {
                    iBinder = addMarker.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 12:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                zzc addGroundOverlay = addGroundOverlay(parcel.readInt() != 0 ? GroundOverlayOptions.CREATOR.zzfj(parcel) : null);
                parcel2.writeNoException();
                if (addGroundOverlay != null) {
                    iBinder = addGroundOverlay.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 13:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                zzh addTileOverlay = addTileOverlay(parcel.readInt() != 0 ? TileOverlayOptions.CREATOR.zzfv(parcel) : null);
                parcel2.writeNoException();
                if (addTileOverlay != null) {
                    iBinder = addTileOverlay.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 14:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                clear();
                parcel2.writeNoException();
                return true;
            case 15:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int mapType = getMapType();
                parcel2.writeNoException();
                parcel2.writeInt(mapType);
                return true;
            case 16:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setMapType(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 17:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                isTrafficEnabled = isTrafficEnabled();
                parcel2.writeNoException();
                parcel2.writeInt(isTrafficEnabled ? 1 : 0);
                return true;
            case 18:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (parcel.readInt() != 0) {
                    z = true;
                }
                setTrafficEnabled(z);
                parcel2.writeNoException();
                return true;
            case 19:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                isTrafficEnabled = isIndoorEnabled();
                parcel2.writeNoException();
                if (isTrafficEnabled) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 20:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                isTrafficEnabled = setIndoorEnabled(parcel.readInt() != 0);
                parcel2.writeNoException();
                if (isTrafficEnabled) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 21:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                isTrafficEnabled = isMyLocationEnabled();
                parcel2.writeNoException();
                if (isTrafficEnabled) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 22:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (parcel.readInt() != 0) {
                    z = true;
                }
                setMyLocationEnabled(z);
                parcel2.writeNoException();
                return true;
            case 23:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                Location myLocation = getMyLocation();
                parcel2.writeNoException();
                if (myLocation != null) {
                    parcel2.writeInt(1);
                    myLocation.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            case 24:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setLocationSource(com.google.android.gms.maps.internal.ILocationSourceDelegate.zza.zzco(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 25:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                IUiSettingsDelegate uiSettings = getUiSettings();
                parcel2.writeNoException();
                if (uiSettings != null) {
                    iBinder = uiSettings.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 26:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                IProjectionDelegate projection = getProjection();
                parcel2.writeNoException();
                if (projection != null) {
                    iBinder = projection.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 27:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnCameraChangeListener(com.google.android.gms.maps.internal.zze.zza.zzcr(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 28:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapClickListener(com.google.android.gms.maps.internal.zzi.zza.zzcv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 29:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapLongClickListener(com.google.android.gms.maps.internal.zzk.zza.zzcx(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 30:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMarkerClickListener(com.google.android.gms.maps.internal.zzm.zza.zzcz(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 31:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMarkerDragListener(com.google.android.gms.maps.internal.zzn.zza.zzcA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 32:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnInfoWindowClickListener(com.google.android.gms.maps.internal.zzg.zza.zzct(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 33:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setInfoWindowAdapter(com.google.android.gms.maps.internal.zzd.zza.zzcn(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 35:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                zzb addCircle = addCircle(parcel.readInt() != 0 ? CircleOptions.CREATOR.zzfi(parcel) : null);
                parcel2.writeNoException();
                if (addCircle != null) {
                    iBinder = addCircle.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 36:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMyLocationChangeListener(com.google.android.gms.maps.internal.zzp.zza.zzcC(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 37:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMyLocationButtonClickListener(com.google.android.gms.maps.internal.zzo.zza.zzcB(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 38:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                snapshot(com.google.android.gms.maps.internal.zzw.zza.zzcK(parcel.readStrongBinder()), com.google.android.gms.dynamic.zzd.zza.zzbk(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 39:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setPadding(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 40:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                isTrafficEnabled = isBuildingsEnabled();
                parcel2.writeNoException();
                if (isTrafficEnabled) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 41:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                if (parcel.readInt() != 0) {
                    z = true;
                }
                setBuildingsEnabled(z);
                parcel2.writeNoException();
                return true;
            case 42:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapLoadedCallback(com.google.android.gms.maps.internal.zzj.zza.zzcw(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 44:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                com.google.android.gms.maps.model.internal.zzd focusedBuilding = getFocusedBuilding();
                parcel2.writeNoException();
                if (focusedBuilding != null) {
                    iBinder = focusedBuilding.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            case 45:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnIndoorStateChangeListener(com.google.android.gms.maps.internal.zzf.zza.zzcs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 53:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                getMapAsync(com.google.android.gms.maps.internal.zzl.zza.zzcy(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 54:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onCreate(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 55:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onResume();
                parcel2.writeNoException();
                return true;
            case 56:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onPause();
                parcel2.writeNoException();
                return true;
            case 57:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onDestroy();
                parcel2.writeNoException();
                return true;
            case 58:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onLowMemory();
                parcel2.writeNoException();
                return true;
            case 59:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                isTrafficEnabled = useViewLifecycleWhenInFragment();
                parcel2.writeNoException();
                if (isTrafficEnabled) {
                    i3 = 1;
                }
                parcel2.writeInt(i3);
                return true;
            case 60:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                onSaveInstanceState(bundle);
                parcel2.writeNoException();
                if (bundle != null) {
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            case 61:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setContentDescription(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 80:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnPoiClickListener(com.google.android.gms.maps.internal.zzq.zza.zzcD(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case Place.TYPE_RV_PARK /*81*/:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onEnterAmbient(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case Place.TYPE_SCHOOL /*82*/:
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                onExitAmbient();
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
