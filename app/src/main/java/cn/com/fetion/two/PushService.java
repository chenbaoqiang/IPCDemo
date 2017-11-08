package cn.com.fetion.two;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.com.fetion.one.IPushService;
import cn.com.fetion.one.Person;


/**
 * Created by Administrator on 2017/11/8.
 */

public class PushService extends Service {
    private static final String TAG = "RF_PushService";

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return new PushServiceBinder();
    }

    public  class PushServiceBinder extends IPushService.Stub{

        @Override
        public String sendMessage(String content) throws RemoteException {
            Log.e(TAG, "sendMessage  content = " + content);

            return null;
        }

        @Override
        public void sendImage(Person person) throws RemoteException {
            Log.e(TAG, "sendMessage  name = " + person.getName());

        }
    }
}
