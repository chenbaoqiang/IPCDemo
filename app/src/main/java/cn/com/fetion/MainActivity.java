package cn.com.fetion;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import cn.com.fetion.one.Person;
import cn.com.fetion.one.R;
import cn.com.fetion.two.RFPushServiceProxy;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "OneMainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_get).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //发送消息
                RFPushServiceProxy.getInstance().sendMessage("123456");
                Person p  = new Person();
                p.setName("小靴子");
                RFPushServiceProxy.getInstance().sendImage(p);

                //接受消息
            }
        });
    }
}
