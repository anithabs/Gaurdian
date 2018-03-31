package capstone.uwm.com.gaurdian.GetDetails;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Anitha on 3/30/2018.
 */

public class MessageListner  extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            if (bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        String msgBody = msgs[i].getMessageBody();
                        PushMessage.smsReceived(msgBody);
                    }
                } catch(Exception e) {
                    Log.e( e.getMessage(),"failed to insert message");
                }
            }
        }
    }
}