package capstone.uwm.com.gaurdian.Call;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link}
 * interface.
 */

public class CallListFragment extends Fragment {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<CallHistroy> callList = new ArrayList<>();
    ArrayList<CallHistroy> callObject = new ArrayList<>();
    private CallListFragment.OnFragmentInteractionListener mListener;
    private RecyclerView rvCall;
    private RecyclerView.Adapter callAdaptor;
    private RecyclerView.LayoutManager eLayoutManager;
    private static final int        DIALOG_DATE_PICKER  = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calllist_list, container, false);
        rvCall = (RecyclerView) rootView.findViewById(R.id.rvCallList);
        rvCall.setHasFixedSize(true);
        eLayoutManager = new LinearLayoutManager(getActivity());
        Context context = getActivity();
        rvCall.setLayoutManager(eLayoutManager);
        callList = calculateIncomingOutgoingDuration();
        callAdaptor = new CallSearchAdapter(getActivity(), callList);
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
    public ArrayList<CallHistroy> calculateIncomingOutgoingDuration(){
        callList = getAllCallRecords();
        ArrayList<CallHistroy> callHistroyIncomingOutgoing = new ArrayList<>();
        if(callList.size() > 0){
            if(callList.get(0).getMode() == "Outgoing") {
                callHistroyIncomingOutgoing.add(new CallHistroy(callList.get(0).getDate(), 0, 1));
            }else{
                callHistroyIncomingOutgoing.add(new CallHistroy(callList.get(0).getDate(), 1, 0));
            }
        }
        for( int i = 1  ; i < callList.size() ; i++){
            String prevDate = callList.get(i - 1).getDate().substring(0,9);
            String curDate = callList.get(i).getDate().substring(0,9);
            if(prevDate.equals(curDate)){
                int size = callHistroyIncomingOutgoing.size();
                int incoming = callHistroyIncomingOutgoing.get(size - 1).getTotalIncoming();
                int outcoming = callHistroyIncomingOutgoing.get(size - 1).getTotalOutgoing();
                if(callList.get(i).getMode() == "Outgoing") {
                    callHistroyIncomingOutgoing.get(size -1).updateCall(incoming,outcoming +1);
                }else{
                    callHistroyIncomingOutgoing.get(size -1).updateCall(incoming+1,outcoming);
                }
            }else{
                if(callList.get(i).getMode() == "Outgoing") {
                    callHistroyIncomingOutgoing.add(new CallHistroy(callList.get(i).getDate(), 0, 1));
                }else{
                    callHistroyIncomingOutgoing.add(new CallHistroy(callList.get(i).getDate(), 1, 0));
                }
            }
        }
        return  callHistroyIncomingOutgoing;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallListFragment.OnFragmentInteractionListener) {
            mListener = (CallListFragment.OnFragmentInteractionListener) context;
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
