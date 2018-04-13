package capstone.uwm.com.gaurdian.Call;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import capstone.uwm.com.gaurdian.Objects.CallHistroy;
import capstone.uwm.com.gaurdian.R;

/**
 * Created by Anitha on 4/10/2018.
 */

public class CallDurationAdapter extends RecyclerView.Adapter<CallDurationAdapter.ViewHolder> {

    private List<CallHistroy> mCalls;
    private Context mContext;

    public CallDurationAdapter(Context context, List<CallHistroy> calls) {
        mCalls = calls;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public CallDurationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View callDurationView = inflater.inflate(R.layout.item_call_duration_details, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(callDurationView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CallDurationAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final CallHistroy ch = mCalls.get(position);

        // Set item views based on your views and data model
        final TextView _phoneNoTextView = viewHolder._phoneNoTextView;
        _phoneNoTextView.setText(ch.getPhoneNumber());
        final TextView _incomingTextView = viewHolder._incomingTextView;
        _incomingTextView.setText(ch.getIncomingDuration());
        final TextView _outgoingTextView = viewHolder._outgoingTextView;
        _outgoingTextView.setText(ch.getOutgoingDuration());

        final String key = ch.getKey();
    }


    @Override
    public int getItemCount() {
        return mCalls.size();
    }

    // Provide a direct reference to each of the views within a data item
// Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView _phoneNoTextView;
        public TextView _incomingTextView;
        public TextView _outgoingTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            _phoneNoTextView = (TextView) itemView.findViewById(R.id.rvs_duration_phone_no);
            _incomingTextView = (TextView) itemView.findViewById(R.id.rvs_duration_outing_total_call);
            _outgoingTextView = (TextView) itemView.findViewById(R.id.rvs_duration_incoming_total_call);

        }
    }
}