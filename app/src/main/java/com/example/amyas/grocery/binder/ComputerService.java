package com.example.amyas.grocery.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * author: Amyas
 * date: 2018/3/12
 */

public class ComputerService extends Service{

    private IBinder mIBinder = new Binder(){
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code==1){
                String args0 = data.readString();
                String args1 = data.readString();
                String result = strcat(args0, args1);
                reply.writeString(result);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        public String strcat(String a, String b){
            return a+b;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }
}
