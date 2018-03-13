package com.example.amyas.grocery.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.amyas.grocery.Person;
import com.example.amyas.grocery.PersonAidl;

/**
 * author: Amyas
 * date: 2018/3/12
 */

public class ComputerService extends Service {
    public static final String TAG = "ComputerService";

    private IBinder mIBinder = new PersonAidl.Stub() {
        @Override
        public String getInfo(String s) throws RemoteException {
            return "remote progress received string: "+s;
        }

        @Override
        public String sendPerson(Person person) throws RemoteException {
            return "remote progress received person: "+person.toString();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ComputerService");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }
}
