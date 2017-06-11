package com.vizmac.localconnect;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pranavam on 7/6/17.
 */

public class MyService extends Service {

    //Thread send,recieve;
    String msg="";
    BufferedReader br;
    int newmsg=0;
    AsyncTask listen;

    boolean startlistening=true;
    //ChatActivity ca;

   /* public void setCa(ChatActivity ca) {
        this.ca = ca;
    }*/

    class MyServiceBinder extends Binder
    {
        public MyService getService(){
            return MyService.this;
        }
    }

    private IBinder mBinder=new MyServiceBinder();




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("1");
        System.out.println("On iBinder");
        System.out.println("1");
        return mBinder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            br=new BufferedReader(new InputStreamReader(SocketHandler.sock.getInputStream()));
        } catch (Exception e) {
            System.out.println("1");
            System.out.println("");
            System.out.println("1");
            System.out.println("1");
            System.out.println();
            e.printStackTrace();
            System.out.println("1");
            System.out.println("1");
            System.out.println("");
            System.out.println("1");
        }
        startlistening=true;




        listen=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                while(startlistening)
                {
                    try {
                        System.out.println("1");
                        System.out.println("Waiting for message");
                        System.out.println("1");
                       // msg=br.readLine();
                        System.out.println("1");
                        System.out.println("Read message");
                        System.out.println("1");
                        try {
                            System.out.println("1");
                            System.out.println("Thread sleeping");
                            System.out.println("1");
                            Thread.currentThread();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    } catch (Exception e) {
                        try {
                            System.out.println("1");
                            System.out.println("Thread sleeping");
                            System.out.println("1");
                            Thread.currentThread();
                            Thread.sleep(1000);
                        } catch (InterruptedException ee) {
                            ee.printStackTrace();
                        }
                        System.out.println("1");
                        System.out.println("");
                        System.out.println("1");
                        System.out.println("1");
                        System.out.println();
                        e.printStackTrace();
                        System.out.println("1");
                        System.out.println("1");
                        System.out.println("");
                        System.out.println("1");                    }

                }

                return null;
            }


        };
        listen.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        System.out.println("1");
        System.out.println("Service started");
        System.out.println("1");


        return super.onStartCommand(intent, flags, startId);
    }

    public String getMsg() {
        return msg;
    }

    public String getMessage() {
        String s=msg;
        msg=null;
        return s;
    }

    @Override
    public void onDestroy() {
        System.out.println("1");
        System.out.println("Service destroyed");
        System.out.println("1");
        startlistening=false;
        if(listen.getStatus()==AsyncTask.Status.RUNNING||listen.getStatus()==AsyncTask.Status.PENDING)
        {
            listen.cancel(true);
        }
        super.onDestroy();
    }
}
