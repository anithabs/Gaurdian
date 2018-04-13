package capstone.uwm.com.gaurdian.Call;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

import capstone.uwm.com.gaurdian.Objects.CallHistroy;
import capstone.uwm.com.gaurdian.R;

public class CallTotalSummaryActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<CallHistroy> callTotalSummaryFromDate = new ArrayList<>();
    ArrayList<CallHistroy> callTotalSummaryToDate = new ArrayList<>();
    ArrayList<CallHistroy> callTotalSummary = new ArrayList<>();
    ArrayList<CallHistroy> callTotalSummaryTemp = new ArrayList<>();
    ArrayList<CallHistroy> callTotalSummaryObject = new ArrayList<>();
    int day,month,year, incomingDuration = 0, outgoingDuration =0, totalDuration = 0;
    TextView  textViewIncomingDuration, textViewOutgoingDuration, textViewTotalDuration;
    EditText textViewFromDate ,textViewToDate ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_total_summary);
        setTitle("Call Total Summary");
        textViewFromDate = (EditText) findViewById(R.id.rvs_total_Summary_from_date);
        textViewToDate = (EditText) findViewById(R.id.rvs_total_Summary_to_date);
        textViewIncomingDuration = (TextView) findViewById(R.id.rvs_total_Summary_incoming_call);
        textViewOutgoingDuration =  (TextView) findViewById(R.id.rvs_total_Summary_outgoing_call);
        textViewTotalDuration = (TextView) findViewById(R.id.rvs_total_Summary_call);
        final DatePickerDialog.OnDateSetListener from_dateListener,to_dateListener;

        callTotalSummary = getAllCallRecords();
        incomingDuration = totalIncomingCall(callTotalSummary);
        outgoingDuration = totalOutgoingCall(callTotalSummary);
        totalDuration =  totalIncomingCall(callTotalSummary) + totalOutgoingCall(callTotalSummary);;
        textViewIncomingDuration.setText(Integer.toString(incomingDuration));
        textViewOutgoingDuration.setText(Integer.toString(outgoingDuration));
        textViewTotalDuration.setText(Integer.toString(totalDuration));

        from_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yr, int mnth, int monthday) {
                year =yr;
                month = mnth + 1;
                day = monthday;
                updateFromDisplay();
                callTotalSummaryTemp = FromDate(year,  month,  day);
                incomingDuration = totalIncomingCall(callTotalSummaryTemp);
                outgoingDuration =  totalOutgoingCall(callTotalSummaryTemp);
                totalDuration = totalIncomingCall(callTotalSummaryTemp) + totalOutgoingCall(callTotalSummaryTemp);
                textViewIncomingDuration.setText(Integer.toString(incomingDuration));
                textViewOutgoingDuration.setText(Integer.toString(outgoingDuration));
                textViewTotalDuration.setText( Integer.toString(totalDuration));

            }
        };
        to_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yr, int mnth, int monthday) {
                year =yr;
                month = mnth+1;
                day = monthday;
                updateToDisplay();
                callTotalSummaryTemp = ToDate(year,  month,  day);
                incomingDuration = totalIncomingCall(callTotalSummaryTemp);
                outgoingDuration =  totalOutgoingCall(callTotalSummaryTemp);
                totalDuration = totalIncomingCall(callTotalSummaryTemp) + totalOutgoingCall(callTotalSummaryTemp);
                textViewIncomingDuration.setText(Integer.toString(incomingDuration));
                textViewOutgoingDuration.setText(Integer.toString(outgoingDuration));
                textViewTotalDuration.setText(Integer.toString(totalDuration));

            }
        };
        textViewFromDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog dialog = new DatePickerDialog(v.getContext() ,from_dateListener ,calender.get(calender.YEAR) ,calender.get(Calendar.MONTH) ,calender.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        textViewToDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog dialog = new DatePickerDialog(v.getContext() ,to_dateListener ,calender.get(calender.YEAR) ,calender.get(Calendar.MONTH) ,calender.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });




    }
    @Override
    public void onPause(){
        super.onPause();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();

    }

    private int totalIncomingCall(ArrayList<CallHistroy> list){
        int totalIncomingCall = 0;
        for (int i = 0; i < list.size(); i++){
            String mode = list.get(i).getMode();
            if( mode.equals("Incoming")){
                String duration = list.get(i).getDuration();
                totalIncomingCall = Integer.parseInt(duration);
            }
        }
        return totalIncomingCall;
    }

    private int totalOutgoingCall(ArrayList<CallHistroy> list){
        int totalOutgoingCall = 0;
        for (int i = 0; i < list.size(); i++){
            String mode = list.get(i).getMode();
            if( mode.equals("Outgoing")){
                String duration = list.get(i).getDuration();
                totalOutgoingCall = Integer.parseInt(duration);
            }
        }
        return totalOutgoingCall;
    }

    private ArrayList<CallHistroy> FromDate ( int year, int month, int date){
        String y, m, d;
        callTotalSummaryFromDate.clear();
        ArrayList<CallHistroy> FromDate = new ArrayList<>();
        FromDate = getAllCallRecords();
        for (int i = 0; i < FromDate.size(); i++) {
            String sdate = FromDate.get(i).getDate();
            String[] dateArray = sdate.split("/");
            d = dateArray[0];
            m = dateArray[1];
            String yr = dateArray[2];
            y = yr.substring(0,4);
            if (Integer.parseInt(y) > year) {
                callTotalSummaryFromDate.add(FromDate.get(i));
            } else if (Integer.parseInt(y) >= year && Integer.parseInt(m) > month) {
                callTotalSummaryFromDate.add(FromDate.get(i));
            } else if (Integer.parseInt(y) >= year && Integer.parseInt(m) >= month && Integer.parseInt(d) >= date) {
                callTotalSummaryFromDate.add(FromDate.get(i));
            }
        }
        return callTotalSummaryFromDate;
    }

    public ArrayList<CallHistroy> ToDate ( int year, int month, int date){
        String y, m, d;
        callTotalSummaryToDate.clear();
        ArrayList<CallHistroy> ToDate = new ArrayList<>();
        if (callTotalSummaryFromDate.size() != 0 || textViewFromDate.getText().toString() != null) {
            ToDate = callTotalSummaryFromDate;
        } else
            ToDate = getAllCallRecords();

        for (int i = 0; i < ToDate.size(); i++) {
            String sdate = ToDate.get(i).getDate();
            String[] dateArray = sdate.split("/");
            d = dateArray[0];
            m = dateArray[1];
            String yr = dateArray[2];
            y = yr.substring(0,4);
            if (Integer.parseInt(y) < year) {
                callTotalSummaryToDate.add(ToDate.get(i));
            } else if (Integer.parseInt(y) <= year && Integer.parseInt(m) < month) {
                callTotalSummaryToDate.add(ToDate.get(i));
            } else if (Integer.parseInt(y) <= year && Integer.parseInt(m) <= month && Integer.parseInt(d) <= date) {
                callTotalSummaryToDate.add(ToDate.get(i));
            }
        }
        return callTotalSummaryToDate;
    }

    private void updateFromDisplay(){
        if(month <10 && day <10 ) {
            textViewFromDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-0").append(day));
        }else if(month <10){
            textViewFromDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-").append(day));
        }else if(day <10){
            textViewFromDate.setText(new StringBuilder().append(year).append("-").append(month).append("-0").append(day));
        }else
            textViewFromDate.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }

    private void updateToDisplay(){
        if(month <10 && day <10 ) {
            textViewToDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-0").append(day));
        }else if(month <10){
            textViewToDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-").append(day));
        }else if(day <10){
            textViewToDate.setText(new StringBuilder().append(year).append("-").append(month).append("-0").append(day));
        }else
            textViewToDate.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }

    public ArrayList<CallHistroy> getAllCallRecords(){
        DatabaseReference ref = database.getReference();
        ref.child("Call").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callTotalSummaryObject.clear();
                HashMap<String,Object> call = null;
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    Log.e("Listener",item.toString() );
                    call =(HashMap<String, Object>) item.getValue();
                    if(call != null) {
                        callTotalSummaryObject.add(new CallHistroy(call.get("phoneNumber").toString(), call.get("mode").toString(), call.get("duration").toString(), call.get("date").toString(), item.getKey()));
                    }else{
                        System.out.print("error");
                    }

                }
                return;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return callTotalSummaryObject;
    }
}