package com.google.android.gms.auth.api.credentials.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzlb.zzb;

public final class zzc implements CredentialsApi {

    private static class zza extends zza {
        private zzb<Status> zzSI;

        zza(zzb<Status> com_google_android_gms_internal_zzlb_zzb_com_google_android_gms_common_api_Status) {
            this.zzSI = com_google_android_gms_internal_zzlb_zzb_com_google_android_gms_common_api_Status;
        }

        public void zzg(Status status) {
            this.zzSI.zzp(status);
        }
    }

    public PendingResult<Status> delete(GoogleApiClient googleApiClient, final Credential credential) {
        return googleApiClient.zzb(new zzd<Status>(this, googleApiClient) {
            final /* synthetic */ zzc zzSF;

            protected void zza(Context context, zzh com_google_android_gms_auth_api_credentials_internal_zzh) throws RemoteException {
                com_google_android_gms_auth_api_credentials_internal_zzh.zza(new zza(this), new DeleteRequest(credential));
            }

            protected /* synthetic */ Result zzb(Status status) {
                return zzd(status);
            }

            protected Status zzd(Status status) {
                return status;
            }
        });
    }

    public PendingResult<Status> disableAutoSignIn(GoogleApiClient googleApiClient) {
        return googleApiClient.zzb(new zzd<Status>(this, googleApiClient) {
            final /* synthetic */ zzc zzSF;

            protected void zza(Context context, zzh com_google_android_gms_auth_api_credentials_internal_zzh) throws RemoteException {
                com_google_android_gms_auth_api_credentials_internal_zzh.zza(new zza(this));
            }

            protected /* synthetic */ Result zzb(Status status) {
                return zzd(status);
            }

            protected Status zzd(Status status) {
                return status;
            }
        });
    }

    public PendingResult<CredentialRequestResult> request(GoogleApiClient googleApiClient, final CredentialRequest credentialRequest) {
        return googleApiClient.zza(new zzd<CredentialRequestResult>(this, googleApiClient) {
            final /* synthetic */ zzc zzSF;

            protected void zza(Context context, zzh com_google_android_gms_auth_api_credentials_internal_zzh) throws RemoteException {
                com_google_android_gms_auth_api_credentials_internal_zzh.zza(new zza(this) {
                    final /* synthetic */ AnonymousClass1 zzSG;

                    {
                        this.zzSG = r1;
                    }

                    public void zza(Status status, Credential credential) {
                        this.zzSG.zzb(new zzb(status, credential));
                    }

                    public void zzg(Status status) {
                        this.zzSG.zzb(zzb.zzh(status));
                    }
                }, credentialRequest);
            }

            protected /* synthetic */ Result zzb(Status status) {
                return zzi(status);
            }

            protected CredentialRequestResult zzi(Status status) {
                return zzb.zzh(status);
            }
        });
    }

    public PendingResult<Status> save(GoogleApiClient googleApiClient, final Credential credential) {
        return googleApiClient.zzb(new zzd<Status>(this, googleApiClient) {
            final /* synthetic */ zzc zzSF;

            protected void zza(Context context, zzh com_google_android_gms_auth_api_credentials_internal_zzh) throws RemoteException {
                com_google_android_gms_auth_api_credentials_internal_zzh.zza(new zza(this), new SaveRequest(credential));
            }

            protected /* synthetic */ Result zzb(Status status) {
                return zzd(status);
            }

            protected Status zzd(Status status) {
                return status;
            }
        });
    }
}
