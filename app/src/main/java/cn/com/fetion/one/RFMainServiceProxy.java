package cn.com.fetion.one;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import cn.com.fetion.two.PushService;
import cn.com.fetion.two.RFPushServiceProxy;

/**
 * Created by Administrator on 2017/11/8.
 */

public class RFMainServiceProxy {
    private static final String TAG = "RF_RFPushServiceProxy";

    private IMainService mBinder = null;
    private static RFMainServiceProxy instance;

    private RFMainServiceProxy() {

    }

    public static RFMainServiceProxy getInstance() {
        if (null == instance) {
            instance = new RFMainServiceProxy();
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
            mBinder = IMainService.Stub.asInterface(service);
        }
    };

    public void bindService(Application app) {
        Intent intent = new Intent(app, MainService.class);
        app.startService(intent);
        app.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
}
