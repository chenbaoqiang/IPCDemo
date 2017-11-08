package cn.com.fetion;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

import cn.com.fetion.one.RFMainServiceProxy;
import cn.com.fetion.two.RFPushServiceProxy;

/**
 * Created by Administrator on 2017/11/8.
 */

public class RFApplication extends Application {

    private static final String TAG = "RF_RFApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        String curProcessName = getCurProcessName();
        String mainProcessName = getPackageName();
        String pushProcessName = mainProcessName + ":push";
        if (curProcessName.equals(mainProcessName)) {
            Log.i(TAG, "主进程 启动");
            RFPushServiceProxy.getInstance().bindService(this);
        }else if (curProcessName.equals(pushProcessName)) {
            Log.i(TAG, "推送进程 启动");
            RFMainServiceProxy.getInstance().bindService(this);
        }
    }

    /**
     * 获取当前的进程名称
     *
     * @return
     */
    public String getCurProcessName() {
        ActivityManager.RunningAppProcessInfo process
                = getProcessInfo(null, android.os.Process.myPid());
        if (process != null) {
            return process.processName;
        }
        return null;
    }

    /**
     * 获取当前进程信息
     *
     * @param processName
     * @param pid
     * @return
     */
    private ActivityManager.RunningAppProcessInfo getProcessInfo(String processName, int pid) {
        ActivityManager actMgr =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processes = actMgr.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : processes) {
            if (processName != null) {
                if (processName.equals(process.processName)) {
                    return process;
                }
            } else if (process.pid == pid) {
                return process;
            }
        }
        return null;
    }
}
