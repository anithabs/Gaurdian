package capstone.uwm.com.gaurdian.Call;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CallSearchFragment extends Fragment {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<CallHistroy> callListFromDate  = new ArrayList<>();
    ArrayList<CallHistroy> callListToDate  = new ArrayList<>();
    ArrayList<CallHistroy> callList = new ArrayList<>();
    ArrayList<CallHistroy> callSearchList = new ArrayList<>();
    ArrayList<CallHistroy> callSearchTemp = new ArrayList<>();
    ArrayList<CallHistroy> callObject = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    /* DatabaseManager dbManager;*/
    private RecyclerView rvCall;
    private RecyclerView.Adapter callAdaptor;
    private RecyclerView.LayoutManager eLayoutManager;

    String userName;
    private static final int        DIALOG_DATE_PICKER  = 100;
    private int                     datePickerInput;
    public EditText editTextFromDate,editTextToDate;
    private int day;
    private int month;
    private int year, hour,minute;
    TextView editTextSearch;

    public CallSearchFragment() {

    }

    public static CallSearchFragment newInstance(String param1, String param2) {
        CallSearchFragment fragment = new CallSearchFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        editTextSearch =(EditText)getActivity().findViewById(R.id.editTextSearchKeyPhoneNo);
        editTextFromDate =(EditText) getActivity().findViewById(R.id.editTextCallSearchFromDate);
        editTextToDate =(EditText) getActivity().findViewById(R.id.editTextCallSearchToDate);
        View rootView = inflater.inflate(R.layout.fragment_callsearch_list, container, false);
        rvCall = (RecyclerView) rootView.findViewById(R.id.rvCallSearch);
        rvCall.setHasFixedSize(true);
        callList = getAllCallRecords();
        eLayoutManager = new LinearLayoutManager(getActivity());
        Context context =getActivity();
        final DatePickerDialog.OnDateSetListener from_dateListener,to_dateListener;
        rvCall.setLayoutManager(eLayoutManager);
        from_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yr, int mnth, int monthday) {
                year =yr;
                month = mnth + 1;
                day = monthday;
                updateFromDisplay();
                callList = FromDate(year,  month,  day);
                callAdaptor = new CallSearchAdapter(getActivity(), callList);
                rvCall.setAdapter(callAdaptor);
                editTextToDate.setText("");
            }
        };
        to_dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yr, int mnth, int monthday) {
                year =yr;
                month = mnth+1;
                day = monthday;
                updateToDisplay();
                callList = ToDate(year,  month,  day);
                callAdaptor = new CallSearchAdapter(getActivity(), callList);
                rvCall.setAdapter(callAdaptor);
            }
        };
        editTextFromDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),from_dateListener,calender.get(calender.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        editTextToDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),to_dateListener,calender.get(calender.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        editTextSearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                callList.clear();
                callSearchTemp = getAllCallRecords();
                for(int i = 0; i < callSearchList.size() ; i++)
                    if(callSearchTemp.get(i).getPhoneNumber().contains(s.toString()) )
                        callSearchList.add(callSearchTemp.get(i));

                callList = callSearchList;
                callAdaptor = new CallSearchAdapter(getActivity(), callList);
                rvCall.setAdapter(callAdaptor);
                editTextFromDate.setText("");
                editTextToDate.setText("");
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
        return rootView;
    }
    public ArrayList<CallHistroy> getAllCallRecords(){
        DatabaseReference ref = database.getReference();
        ref.child("Call").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callObject.clear();
                HashMap<String,Object> call = null;
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    Log.e("Listener",item.toString() );
                    call =(HashMap<String, Object>) item.getValue();
                    callObject.add(new CallHistroy(call.get("phoneNumber").toString(),call.get("mode").toString(),call.get("duration").toString(), call.get("date").toString(),item.getKey()));
                }
                return;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                return;
            }
        });
        return callObject;
    }
    public ArrayList<CallHistroy> FromDate(int year, int month, int date){
        String y, m, d;
        callListFromDate.clear();
        ArrayList<CallHistroy> FromDate = new ArrayList<>();
        if(callSearchList.size() !=0 || editTextSearch.getText().toString()!= null){
            FromDate = callSearchList;
        }else
            FromDate = getAllCallRecords();
        for( int i = 0 ; i < FromDate.size() ; i++){
            String sdate = FromDate.get(i).getDate();
            String[] dateArray = sdate.split("-");
            y = dateArray[0];
            m = dateArray[1];
            d = dateArray[2];
            if(Integer.parseInt(y) > year) {
                callListFromDate.add(FromDate.get(i));
            }else if(Integer.parseInt(y) >= year && Integer.parseInt(m) > month ){
                callListFromDate.add(FromDate.get(i));
            }else if (Integer.parseInt(y) >= year && Integer.parseInt(m) >= month && Integer.parseInt(d) >= date){
                callListFromDate.add(FromDate.get(i));
            }
        }
        return  callListFromDate;
    }
    public ArrayList<CallHistroy> ToDate(int year, int month, int date){
        String y, m, d;
        callListToDate.clear();
        ArrayList<CallHistroy> ToDate = new ArrayList<>();
        if(callListFromDate.size() !=0 ||  editTextFromDate.getText().toString()!= null) {
            ToDate = callListFromDate;
        }else if(callSearchList.size() !=0 || editTextSearch.getText().toString()!= null){
            ToDate = callSearchList;
        }else
            ToDate = getAllCallRecords();

        for( int i = 0 ; i < ToDate.size() ; i++){
            String sdate = ToDate.get(i).getDate();
            String[] dateArray = sdate.split("-");
            y = dateArray[0];
            m = dateArray[1];
            d = dateArray[2];
            if(Integer.parseInt(y) < year) {
                callListToDate.add(ToDate.get(i));
            }else if(Integer.parseInt(y) <= year && Integer.parseInt(m) < month ){
                callListToDate.add(ToDate.get(i));
            }else if (Integer.parseInt(y) <= year && Integer.parseInt(m) <= month && Integer.parseInt(d) <= date){
                callListToDate.add(ToDate.get(i));
            }
        }
        return  callListToDate;
    }
    private void updateFromDisplay(){
        if(month <10 && day <10 ) {
            editTextFromDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-0").append(day));
        }else if(month <10){
            editTextFromDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-").append(day));
        }else if(day <10){
            editTextFromDate.setText(new StringBuilder().append(year).append("-").append(month).append("-0").append(day));
        }else
            editTextFromDate.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }
    private void updateToDisplay(){
        if(month <10 && day <10 ) {
            editTextToDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-0").append(day));
        }else if(month <10){
            editTextToDate.setText(new StringBuilder().append(year).append("-0").append(month).append("-").append(day));
        }else if(day <10){
            editTextToDate.setText(new StringBuilder().append(year).append("-").append(month).append("-0").append(day));
        }else
            editTextToDate.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallSearchFragment.OnFragmentInteractionListener) {
            mListener = (CallSearchFragment.OnFragmentInteractionListener) context;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onPause(){
        super.onPause();
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
