package capstone.uwm.com.gaurdian.Call;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import capstone.uwm.com.gaurdian.R;

public class MainCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_main);
    }

    public void keyWordSearch(View view){
        Intent intent = new Intent(this, CallSearchActivity.class);
        startActivity(intent);
    }

    public void callSummary(View view){
        Intent intent = new Intent(this, CallListActivity.class);
        startActivity(intent);
    }

    public void totalSummary(View view){
        Intent intent = new Intent(this, CallTotalSummaryActivity.class);
        startActivity(intent);
    }

    public void durationDetails(View view){
        Intent intent = new Intent(this, CallDurationActivity.class);
        startActivity(intent);
    }
}