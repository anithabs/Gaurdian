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
 * {@link RecyclerView.Adapter} that can display a { @link } and makes a call to the
 * specified { @link OnListFragmentInteractionListener }.
 * TODO: Replace the implementation with code for your data type.
 */
public class CallListAdapter extends RecyclerView.Adapter<CallListAdapter.ViewHolder> {


    private List<CallHistroy> mCalls;
    private Context mContext;

    public CallListAdapter(Context context, List<CallHistroy> calls){
        mCalls = calls;
        mContext = context;
    }
    private Context getContext(){
        return mContext;
    }
    @Override
    public CallListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View exerciseView = inflater.inflate(R.layout.item_call_list_details, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(exerciseView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(CallListAdapter.ViewHolder viewHolder, final int position) {

        final CallHistroy ch = mCalls.get(position);
        final TextView dateTextView = viewHolder.dateTextView;
        dateTextView.setText(ch.getDate());
        final TextView timeTextView = viewHolder.incomingTextView;
        timeTextView.setText(ch.getPhoneNumber());
        final TextView eTypeTextView = viewHolder.outgoingTextView;
        eTypeTextView.setText(ch.getDuration());
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
        public TextView incomingTextView;
        public TextView outgoingTextView;
        public TextView dateTextView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            incomingTextView = (TextView) itemView.findViewById(R.id.rvs_incoming_list_call_details);
            outgoingTextView = (TextView) itemView.findViewById(R.id.rvs_outgoing_list_call_details);
            dateTextView = (TextView) itemView.findViewById(R.id.rvs_date_list_call_details);
        }
    }
}
