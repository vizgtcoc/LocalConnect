package com.vizmac.localconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button host,connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        host=(Button) findViewById(R.id.HostButton);
        connect=(Button) findViewById(R.id.ConnectButton);

        host.getBackground().setAlpha(64);
        connect.getBackground().setAlpha(64);

        host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inent = new Intent(MainActivity.this, HostActivity.class);

                // calling an activity using <intent-filter> action name
                //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                startActivity(inent);

            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inent = new Intent(MainActivity.this, ConnectActivity.class);
                startActivity(inent);

                System.out.println("1");
                System.out.println("Called connect activity");
                System.out.println("1");

            }
        });

    }
}
