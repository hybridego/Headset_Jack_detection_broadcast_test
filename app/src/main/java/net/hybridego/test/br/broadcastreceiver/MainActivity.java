package net.hybridego.test.br.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG="HOHO";
    private BroadcastReceiver brm = null;
    TextView result_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_msg = (TextView) findViewById(R.id.RESULT);

        // create an instance of a BroadcastReceiver
        brm = new BroadcastReceiver() {
            public void onReceive(Context context,Intent intent) {
                Log.e(TAG, intent.getAction());
                if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                    int state = intent.getIntExtra("state", -1);
                    switch (state) {
                        case 0:
                            result_msg.setText("Headset is unplugged");
                            Log.d(TAG, "Headset is unplugged");
                            break;
                        case 1:
                            result_msg.setText("Headset is plugged");
                            Log.d(TAG, "Headset is plugged");
                            break;
                        default:
                            Log.d(TAG, "I have no idea what the headset state is");
                    }
                }
            }
        };
    }

    @Override
    public void onResume()
    {
        super.onResume();
//        registerReceiver(brm,new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED"));
//        registerReceiver(brm,new IntentFilter("com.kakao.i.bluetooth.action.CONNECTION_REQUESTED"));

        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(brm, filter);
    }
    @Override
    public void onPause()
    {
        super.onPause();
        // unregister
        unregisterReceiver(brm);
    }
}
