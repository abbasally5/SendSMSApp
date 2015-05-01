package abbas.sendsmsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private EditText phoneNumber;
    private EditText message;
    private EditText numMsgs;
    private CheckBox delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = (EditText) findViewById(R.id.phoneNum);
        message = (EditText) findViewById(R.id.message);
        numMsgs = (EditText) findViewById(R.id.numMsgs);
        delay = (CheckBox) findViewById(R.id.boolDelay);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void findContacts(View view) {
        Intent intent = new Intent();
    }

    public void sendMessages(View view){
        String phoneNo = phoneNumber.getText().toString();
        String msg = message.getText().toString();
        int num = Integer.parseInt(numMsgs.getText().toString());

        for (int i = 0; i < num; i++){
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            delay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
        Log.d("sent message", msg);
    }
}
