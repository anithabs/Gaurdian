package capstone.uwm.com.gaurdian;

import android.content.ContentResolver;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import capstone.uwm.com.gaurdian.GetDetails.SmsReceiver;
import capstone.uwm.com.gaurdian.Objects.CallHistroy;
import capstone.uwm.com.gaurdian.Objects.MessageHistroy;

public class PushDateActivity extends AppCompatActivity {
    private static final String TAG = "test";
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public final int REQUEST_CODE_ASK_PERMISSIONS = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_data);
    }

    public void enableTracking(View view) {
        DatabaseReference messageRef = database.getReference("Message");
        messageRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // File deleted successfully
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
            }
        });
        if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PushDateActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        }
        ContentResolver contentResolver = getContentResolver();

        Cursor cursorInbox = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        Cursor cursorSent = contentResolver.query(Uri.parse("content://sms/sent"), null, null, null, null);

        int indexBodySent = cursorSent.getColumnIndex(SmsReceiver.BODY);
        int indexAddrSent = cursorSent.getColumnIndex(SmsReceiver.ADDRESS);
        int indexDateSent = cursorSent.getColumnIndex(SmsReceiver.DATE);
        if (!(indexBodySent < 0 || !cursorSent.moveToFirst())) {
            do {
                String str = "Sender: " + cursorSent.getString(indexAddrSent) + "\n" + cursorSent.getString(indexBodySent);
                System.out.print(str);
                String toContent = cursorSent.getString(indexAddrSent);
                String toNumber = cursorSent.getString(indexBodySent);
                String date =  cursorSent.getString(cursorSent.getColumnIndex("date"));
                Long timestamp = Long.parseLong(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");;

                String _date = formatter.format(calendar.getTime());

                messageRef.push().setValue(new MessageHistroy(
                        null, null, toContent, toNumber, _date));
            } while (cursorSent.moveToNext());
        }
        int indexBodyInbox = cursorInbox.getColumnIndex(SmsReceiver.BODY);
        int indexAddrInbox = cursorInbox.getColumnIndex(SmsReceiver.ADDRESS);
        int indexDateInbox = cursorInbox.getColumnIndex(SmsReceiver.DATE);
        if (!(indexBodyInbox < 0 || !cursorInbox.moveToFirst())) {
            do {
                String fromContent = cursorInbox.getString(indexBodyInbox);
                String fromNumber = cursorInbox.getString(indexAddrInbox);
                String time = cursorInbox.getString(indexDateInbox);
                Long timestamp = Long.parseLong(time);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");;

                String _date = formatter.format(calendar.getTime());

                messageRef.push().setValue(new MessageHistroy(
                        fromContent, fromNumber, null, null, _date));
            } while (cursorInbox.moveToNext());
        }
        DatabaseReference callRef = database.getReference("Call");
        callRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // File deleted successfully
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
            }
        });
        if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_CALL_LOG") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CALL_LOG"}, REQUEST_CODE_ASK_PERMISSIONS);
        }
        Cursor cursorCallLog = contentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = cursorCallLog.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursorCallLog.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursorCallLog.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursorCallLog.getColumnIndex(CallLog.Calls.DURATION);
        while (cursorCallLog.moveToNext()) {
            String _phoneNumber = cursorCallLog.getString(number);
            String _mode = cursorCallLog.getString(type);
            String _date = cursorCallLog.getString(date);
            Long timestamp = Long.parseLong(_date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");;

            String dateValue = formatter.format(calendar.getTime());
            String _duration = cursorCallLog.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(_mode);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "Outgoing";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "Incoming";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "Missed";
                    break;
                default: dir = "unknown";
            }
            callRef.push().setValue(new CallHistroy(
                        _phoneNumber, dir, _duration, dateValue));
        }
       /* ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);
        long currentMillis = Calendar.getInstance().getTimeInMillis();
        Calendar cal = Calendar.getInstance();
        for (ActivityManager.RunningServiceInfo info : services) {
            cal.setTimeInMillis(currentMillis-info.activeSince);
            Log.i(TAG, String.format("Process %s with component %s has been running since %s (%d milliseconds)",
                    info.process, info.service.getClassName(), cal.getTime().toString(), info.activeSince));
        }
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getBaseContext().getPackageManager().queryIntentActivities( mainIntent, 0);

*/
        List<PackageInfo> packList = getPackageManager().getInstalledPackages(0);
        for (int i=0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            if ( (packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = packInfo.applicationInfo.loadLabel(getPackageManager()).toString();

                Log.e("App № " + Integer.toString(i), appName);
            }
        }
    }
}