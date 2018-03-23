package capstone.uwm.com.gaurdian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import capstone.uwm.com.gaurdian.SignIn.EmailPasswordActivity;
import capstone.uwm.com.gaurdian.SignIn.GoogleSignInActivity;


public class LoginActivity extends AppCompatActivity {
    Button login_button;
    EditText userName, passWord;
    private static final String USER_KEY = "userKey";
    private static final String PASSWORD_KEY ="passwordKey";
    private static final String USER_DETAILS ="userDetails";
    public static final String LA = "LoginActivity";
    public String Signout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create new user");
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

    public void GoogleLogin(View v){
        Intent intent = new Intent(this, GoogleSignInActivity.class);
        startActivity(intent);
    }
    public void FireBaseLogin(View v){
        Intent intent = new Intent(this, EmailPasswordActivity.class);
        startActivity(intent);

    }
}