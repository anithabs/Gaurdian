package capstone.uwm.com.gaurdian.Call;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import capstone.uwm.com.gaurdian.R;

public class CallSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_search);
        CallSearchFragment newFragment = new CallSearchFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.recordFragmentContainer, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void refreshCallSearch(View v)
    {
        Intent intent = new Intent(this, CallSearchActivity.class);
        startActivity(intent);
    }
    protected void onPause() {
        super.onPause();
    }
    protected void onStop() {
        super.onStop();
    }
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackCallSearch(View v) {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
