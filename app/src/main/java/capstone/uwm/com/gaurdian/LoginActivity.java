package capstone.uwm.com.gaurdian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import capstone.uwm.com.gaurdian.Objects.UserPreference;
import capstone.uwm.com.gaurdian.SignIn.GoogleSignInActivity;


public class LoginActivity extends AppCompatActivity {

    Button login_button;
    EditText userName, passWord;
    private static final String USER_KEY = "userKey";
    private static final String PASSWORD_KEY ="passwordKey";
    private static final String USER_DETAILS ="userDetails";
    public static final String LA = "LoginActivity";
    public String Signout;
    public UserPreference pref;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create new user");
        pref = new UserPreference(this);
        setContentView(R.layout.login_main);
    }
    protected void onStart() {
        super.onStart();
        /*updateView();*/
        Log.w(LA, "inside LoginActivity:onStart()\n");
    }
    protected void onRestart() {
        super.onRestart();
        Log.v(LA, "inside LoginActivity:onRestart()\n");
    }
    protected void onResume() {
        super.onResume();
        Log.v(LA, "inside LoginActivity:onResume()\n");
    }
    protected void onPause() {
        super.onPause();

        Log.v(LA, "inside LoginActivity:onPause()\n");
    }
    protected void onStop() {
        super.onStop();
        Log.v(LA, "inside LoginActivity:onStop()\n");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LA, "inside LoginActivity:onDestroy()\n");
    }
    public void ChildDeviceLogin(View v){
        String device = "child";
        Intent intent = new Intent(this, GoogleSignInActivity.class);
        intent.putExtra("deviceDetails", device);
        startActivity(intent);
    }
    public void ParentDeviceLogin(View v){
        String device = "parent";
        Intent intent = new Intent(this, GoogleSignInActivity.class);
        intent.putExtra("deviceDetails", device);
        startActivity(intent);
    }

}