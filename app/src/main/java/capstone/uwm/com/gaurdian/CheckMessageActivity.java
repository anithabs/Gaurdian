package capstone.uwm.com.gaurdian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import capstone.uwm.com.gaurdian.Objects.MessageHistroy;

public class CheckMessageActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<MessageHistroy> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_message);
    }

    public ArrayList<MessageHistroy> getAllMessageRecords(){

        DatabaseReference ref = database.getReference();
        ref.child("Message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageList.clear();
                HashMap<String,Object> message = null;
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    Log.e("Listener",item.toString() );
                    message =(HashMap<String, Object>) item.getValue();
                    messageList.add(new MessageHistroy(message.get("fromContent").toString(),message.get("fromNumber").toString(),message.get("toContent").toString(), message.get("toNumber").toString(), message.get("date").toString(),item.getKey()));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return messageList;
    }
}
