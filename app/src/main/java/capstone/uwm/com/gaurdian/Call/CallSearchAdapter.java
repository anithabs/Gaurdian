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
 * Created by Anitha on 7/26/2017.
 */
public class CallSearchAdapter extends RecyclerView.Adapter<CallSearchAdapter.ViewHolder> {

    private List<CallHistroy> mCalls;
    private Context mContext;

    public CallSearchAdapter(Context context, List<CallHistroy> calls){
        mCalls = calls;
        mContext = context;
    }
    private Context getContext(){
        return mContext;
    }
    @Override
    public CallSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View exerciseView = inflater.inflate(R.layout.item_call_search_deatils, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(exerciseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CallSearchAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final CallHistroy ch = mCalls.get(position);

        // Set item views based on your views and data model
        final TextView dateTextView = viewHolder.dateTextView;
        dateTextView.setText(ch.getDate());
        final TextView timeTextView = viewHolder.phoneNoTextView;
        timeTextView.setText(ch.getPhoneNumber());
        final TextView eTypeTextView = viewHolder.durationTextView;
        eTypeTextView.setText(ch.getDuration());
        final TextView eDurationTextView = viewHolder.modeTextView;
        eDurationTextView.setText(ch.getMode());
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
        public TextView durationTextView;
        public TextView modeTextView;
        public TextView phoneNoTextView;
        public TextView dateTextView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            durationTextView = (TextView) itemView.findViewById(R.id.rvs_duration_call_histroy);
            modeTextView = (TextView) itemView.findViewById(R.id.rvs_mode_call_histroy);
            phoneNoTextView = (TextView) itemView.findViewById(R.id.rvs_phoneno_call_histroy);
            dateTextView = (TextView) itemView.findViewById(R.id.rvs_date_call_histroy);

        }
    }
}