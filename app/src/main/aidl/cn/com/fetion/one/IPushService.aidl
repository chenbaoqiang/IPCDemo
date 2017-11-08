// IMyAidlInterface.aidl
package cn.com.fetion.one;

import cn.com.fetion.one.Person;

interface IPushService {

    String sendMessage(String content);
    void sendImage(in Person person);
}
