package cn.com.fetion.one;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by chenbaoqiang on 2017/6/21.
 */
public class MainService extends Service {
    private static final String TAG = "RF_MainService";

    IMainService.Stub mBinder = new IMainService.Stub() {

        @Override
        public String getToken() throws RemoteException {
            return "121212121212";
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
