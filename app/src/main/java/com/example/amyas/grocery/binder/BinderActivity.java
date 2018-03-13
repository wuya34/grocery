package com.example.amyas.grocery.binder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.amyas.grocery.Person;
import com.example.amyas.grocery.PersonAidl;
import com.example.amyas.grocery.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: Amyas
 * date: 2018/3/12
 */

public class BinderActivity extends AppCompatActivity {
    @BindView(R.id.option)
    Button mOption;
    private boolean isBound;
    private Unbinder bind;
    private PersonAidl mPersonAidl;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPersonAidl = PersonAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_only);
        bind = ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    private void bind() {
        Intent intent = new Intent(this, ComputerService.class);
        isBound = bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void unbind() {
        if (isBound) {
            this.unbindService(mConnection);
            isBound = false;
        }
    }

    private String communicate(String s) throws RemoteException {
        String result;
        result = mPersonAidl.getInfo(s);
        return result;

    }

    private String sendPerson() throws RemoteException {
        return mPersonAidl.sendPerson(new Person("amyas", 24));
    }

    @OnClick(R.id.option)
    public void onViewClicked() {
        try {
//            String result = communicate("amyas");
            String result = sendPerson();
            mOption.setText(result);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        unbind();
    }
}
