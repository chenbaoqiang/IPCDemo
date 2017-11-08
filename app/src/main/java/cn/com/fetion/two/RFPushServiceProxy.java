package cn.com.fetion.two;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import cn.com.fetion.one.IPushService;
import cn.com.fetion.one.Person;

/**
 * Created by Administrator on 2017/11/8.
 */

public class RFPushServiceProxy {
    private static final String TAG = "RF_RFPushServiceProxy";

    private IPushService mBinder = null;
    private static RFPushServiceProxy instance;

    private RFPushServiceProxy() {

    }

    public static RFPushServiceProxy getInstance() {
        if (null == instance) {
            instance = new RFPushServiceProxy();
        }
        return instance;
    }


    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
            mBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            mBinder = IPushService.Stub.asInterface(service);
        }
    };

    public void bindService(Application app) {
        Intent intent = new Intent(app,PushService.class);
        app.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    public void sendMessage(String content){
        try {
            //mBinder 可能为null
            if(null == mBinder){
                Log.e(TAG, "mBinder is null");
//                bindService(mContext);
            }
            mBinder.sendMessage(content);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendImage(Person content){
        try {
            //mBinder 可能为null
            if(null == mBinder){
                Log.e(TAG, "mBinder is null");
//                bindService(mContext);
            }
            mBinder.sendImage(content);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
