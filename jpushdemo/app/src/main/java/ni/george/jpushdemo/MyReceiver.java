package ni.george.jpushdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ASUS on 12/29/2016.
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "TalkReceiver";
    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();

//        Log.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + AndroidUtil.printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("自定义消息");
            builder.setCancelable(true);
            builder.setMessage(bundleToString(bundle));
            Dialog dialog=builder.show();
            // Push Talk messages are push down by custom message format
//            processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("通知");
            builder.setCancelable(true);
            builder.setMessage(bundleToString(bundle));
            Dialog dialog=builder.show();
//            receivingNotification(context,bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");

//            openNotification(context,bundle);

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private String bundleToString(Bundle bundle){
        StringBuilder sb=new StringBuilder();
        Set<String> set=bundle.keySet();
        for (String key:set){
            sb.append(key).append(":").append(bundle.get(key)).append(",");
        }
        return sb.toString();
    }

}
