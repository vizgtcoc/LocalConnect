package com.vizmac.localconnect;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ChatActivity extends AppCompatActivity {

    TextView chat;
    EditText message;
    Button send;
    //Socket sock;
    BufferedReader br;
    OutputStreamWriter osw;
    Handler handler;
    //Thread listen,post;
    Boolean keeplistening=true;
    String msg=null;
    private Intent serviceIntent;
    private MyService myService;
    private ServiceConnection serviceConnection;
    boolean isServiceBound;
    //Thread checkformsg;
    int msgcount=0;
    AsyncTask getmsg;
    AsyncTask sendmsg;
    String s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //myService.setCa(this);


        System.out.println("1");
        System.out.println("Chat activity started");
        System.out.println("1");



        System.out.println("1");
        System.out.println("Handler and thread declared");
        System.out.println("1");

        chat=(TextView) findViewById(R.id.MessageView);
        message=(EditText) findViewById(R.id.MessageText);
        send=(Button) findViewById(R.id.SendButton);

        System.out.println("1");
        System.out.println("All UI items declared");
        System.out.println("1");
        serviceIntent=new Intent(getApplicationContext(),MyService.class);
        startService(serviceIntent);

        try{


            System.out.println("1");
            System.out.println("getting sock from SocketHandler class");
            System.out.println("1");
            //sock=SocketHandler.getSocket();
            System.out.println("1");
            System.out.println("got socket");
            System.out.println("1");
            br=new BufferedReader(new InputStreamReader(SocketHandler.sock.getInputStream()));
            osw=new OutputStreamWriter(SocketHandler.sock.getOutputStream());
            System.out.println("1");
            System.out.println("Streams created");
            System.out.println("1");

        }catch (Exception e)
        {
            System.out.println("1");
            System.out.println("Exception in socket and streams creation\n"+e);
            System.out.println("1");

        }
        System.out.println("1");
        System.out.println("Starting listen");
        System.out.println("1");

        //listen.start();
        System.out.println("1");
        System.out.println("Listen Started");
        System.out.println("1");





        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("1");
                System.out.println("Send Button clicked");
                System.out.println("1");
                chat.append("\nYOU : "+message.getText());
                s=message.getText().toString();
                sendmsg=new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] params) {
                        System.out.println("1");
                        System.out.println("Inside The async task");
                        System.out.println("1");
                        try {
                            System.out.println("1");
                            System.out.println("Writing message");
                            System.out.println("1");
                            osw.write(s);
                            System.out.println("1");
                            System.out.println("Getting message");
                            System.out.println("1");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                sendmsg.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                message.setText(null);

            }
        });
        bindService();

        getmsg=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                while(keeplistening)
                {
                    try {
                        Thread.currentThread();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1");
                    System.out.println("updating chat");
                    System.out.println("1");
                    String str="hi";
                    //myService.getMsg();
                    if(!str.equals(""))
                    {
                        System.out.println("1");
                        System.out.println("get message not null");
                        System.out.println("1");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("1");
                                System.out.println("Running on UI thrad");
                                System.out.println("1");
                                chat.append("\nFRIEND : "+myService.getMessage());
                                System.out.println("1");
                                System.out.println("Chat updated");
                                System.out.println("1");
                            }
                        });
                    }


                }
                return null;
            }
        };

        getmsg.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);






    }

    public synchronized void setKeeplistening(Boolean keeplistening) {
        this.keeplistening = keeplistening;
    }

    void bindService()
    {
        if(serviceConnection==null)
        {
            serviceConnection=new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder iBinder) {
                    System.out.println("1");
                    System.out.println("inside on service connected in chat activity");
                    System.out.println("1");
                    MyService.MyServiceBinder myServiceBinder=(MyService.MyServiceBinder)iBinder;
                    myService=myServiceBinder.getService();
                    isServiceBound=true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    System.out.println("1");
                    System.out.println("inside on service destroyed in chat activity");
                    System.out.println("1");
                    isServiceBound=false;

                }
            };
        }
        System.out.println("1");
        System.out.println("calling bind service in chat activity");
        System.out.println("1");
        bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);

    }

    void unbindService()
    {
        if(isServiceBound)
        {
            unbindService(serviceConnection);
            isServiceBound=false;
        }

    }
    void updatemessage(String s)
    {

    }

    @Override
    protected void onDestroy() {
        setKeeplistening(false);
        SocketHandler.closeall();
        stopService(serviceIntent);

        super.onDestroy();
    }
}
