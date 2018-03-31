package capstone.uwm.com.gaurdian.GetDetails;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import capstone.uwm.com.gaurdian.Objects.MessageHistroy;

/**
 * Created by Anitha on 3/31/2018.
 */

public class PushMessage {
    static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static void smsReceived(String sms) {
        DatabaseReference ref = database.getReference("Exercise");
        Date date = new Date();
        ref.push().setValue(new MessageHistroy(
                "",
                sms,
                "",
                "",
                date));

    }
    /* public static void smsReceiveError(Exception err) {
        if(onFail != null) {
            FailureCallback f = onFail;
            onFail = null;
            SMSInterceptor.unbindListener();
            onSuccess = null;
            callSerially(() -> f.onError(null, err, 1, err.toString()));
        } else {
            if(onSuccess != null) {
                SMSInterceptor.unbindListener();
                onSuccess = null;
            }
        }
    }  */
}
