package com.vizmac.localconnect;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class ConnectActivity extends AppCompatActivity {


    String ip;
    int port;

    EditText iptext,porttext;
    Button chat;

    //Socket sock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("1");
        System.out.println("Connect activity started");
        System.out.println("1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        iptext=(EditText) findViewById(R.id.IPEditText);
        porttext=(EditText) findViewById(R.id.PortEditText);
        chat=(Button) findViewById(R.id.ChatButton);

        System.out.println("1");
        System.out.println("All mapped with r.id");
        System.out.println("1");

        SocketHandler.setCa(this);



        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("1");
                System.out.println("Chat clicked");
                System.out.println("1");
                ip= String.valueOf(iptext.getText());
                System.out.println("1");
                System.out.println("no problem in ip");
                System.out.println("1");
                port=0;
                System.out.println("1");
                System.out.println("no problem in port variable");
                System.out.println("1");
                port+=Integer.parseInt(porttext.getText().toString());

                System.out.println("1");
                System.out.println("ip and port variables set");
                System.out.println("1");

                SocketHandler.setIp(iptext.getText().toString());
                SocketHandler.setPort(Integer.parseInt(porttext.getText().toString()));

                //create on destroy
                SocketHandler.ttc.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);




            }
        });




    }

    public void startchat()
    {
        System.out.println("1");
        System.out.println("Calling cHAT ACTIVITY");
        System.out.println("1");
        Intent inent = new Intent(ConnectActivity.this, ChatActivity.class);
        System.out.println("1");
        System.out.println("Starting activity");
        System.out.println("1");
        try {
            startActivity(inent);
        }catch (Exception e)
        {
            System.out.println("1");System.out.println("1");
            e.printStackTrace();System.out.println("1");System.out.println("1");
        }


    }

    @Override
    protected void onDestroy() {


        SocketHandler.closeall();

        super.onDestroy();
    }
}
