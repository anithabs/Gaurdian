package capstone.uwm.com.gaurdian.Call;

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
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import capstone.uwm.com.gaurdian.Objects.CallHistroy;
import capstone.uwm.com.gaurdian.R;

import static capstone.uwm.com.gaurdian.R.id.editTextSearchKeyPhoneNo;

public class CallDurationFragment extends Fragment {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<CallHistroy> callList = new ArrayList<>();
    ArrayList<CallHistroy> callListTemp = new ArrayList<>();
    ArrayList<CallHistroy> callObject = new ArrayList<>();
    ArrayList<CallHistroy> callListText = new ArrayList<>();
    private CallDurationFragment.OnFragmentInteractionListener mListener;
    private RecyclerView rvCall;
    private RecyclerView.Adapter callAdaptor;
    private RecyclerView.LayoutManager eLayoutManager;
    private EditText phoneNumber;
    private static final int        DIALOG_DATE_PICKER  = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_callduration_list, container, false);

        phoneNumber = (EditText) getActivity().findViewById(editTextSearchKeyPhoneNo);


        Context context = getActivity();
        rvCall = (RecyclerView) rootView.findViewById(R.id.rvCallDuration);
        rvCall.setHasFixedSize(true);
        rvCall.setLayoutManager(eLayoutManager);
        eLayoutManager = new LinearLayoutManager(getActivity());
        phoneNumber.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                callListTemp =  getAllCallRecords();
                for(int i = 0 ; i < callListTemp.size() ; i++)
                    if(callListTemp.get(i).getPhoneNumber().contains(s.toString()) )
                        callListText.add(callListTemp.get(i));
                callList = calculateIncomingOutgoing();
                callAdaptor = new CallListAdapter(getActivity(), callList);
                rvCall.setAdapter(callAdaptor);
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
        callList = calculateIncomingOutgoing();
        callAdaptor = new CallDurationAdapter(getActivity(), callList);
        rvCall.setAdapter(callAdaptor);
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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return callObject;
    }

    public ArrayList<CallHistroy> calculateIncomingOutgoing(){
        if(callListText.size() > 0){
            callList = callListText;
        }else {
            callList = getAllCallRecords();
        }
        ArrayList<CallHistroy> callHistroyIncomingOutgoing = new ArrayList<>();
        if(callList.size() > 0){
            if(callList.get(0).getMode().equals("Outgoing")) {
                callHistroyIncomingOutgoing.add(new CallHistroy(callList.get(0).getPhoneNumber(), "0", callList.get(0).getDuration()));
            }else{
                callHistroyIncomingOutgoing.add(new CallHistroy(callList.get(0).getPhoneNumber(), callList.get(0).getDuration(), "0"));
            }
        }
        for( int i = 1  ; i < callList.size() ; i++){
            boolean setValue = false;
            for (int j = 0; j < callHistroyIncomingOutgoing.size() ;j++){
                if( callHistroyIncomingOutgoing.get(j).getPhoneNumber().equals(callList.get(i).getPhoneNumber())){
                    setValue = true;

                     int incoming = Integer.parseInt(callHistroyIncomingOutgoing.get(j).getIncomingDuration());
                    int outgoing =  Integer.parseInt( callHistroyIncomingOutgoing.get(j).getOutgoingDuration());
                    int duration = Integer.parseInt( callList.get(i).getDuration());
                    if(callList.get(i).getMode().equals("Outgoing")) {
                        callHistroyIncomingOutgoing.get(j).updateDuration(String.valueOf(incoming), String.valueOf(outgoing + duration));
                    }else{
                        callHistroyIncomingOutgoing.get(j).updateDuration(String.valueOf(incoming + duration), String.valueOf(outgoing));
                    }
                }
            }
            if(!setValue) {
                if(callList.get(i).getMode() == "Outgoing") {
                    callHistroyIncomingOutgoing.add(new CallHistroy(callList.get(i).getPhoneNumber(), "0", callList.get(i).getDuration()));
                }else{
                    callHistroyIncomingOutgoing.add(new CallHistroy(callList.get(i).getPhoneNumber(), callList.get(i).getDuration(), "0"));
                }
            }
        }
        return  callHistroyIncomingOutgoing;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallListFragment.OnFragmentInteractionListener) {
            mListener = (CallDurationFragment.OnFragmentInteractionListener) context;
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