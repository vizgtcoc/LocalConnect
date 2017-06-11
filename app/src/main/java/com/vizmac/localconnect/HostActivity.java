package com.vizmac.localconnect;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;

public class HostActivity extends AppCompatActivity {



    boolean closing=false;
    boolean switching=false;

    TextView ipview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        System.out.println("1");
        System.out.println("Inside on create");
        System.out.println("1");

        System.out.println("1");
        System.out.println("Setting ha refrence to SocketHandler");
        System.out.println("1");

        SocketHandler.setHa(HostActivity.this);
        System.out.println("1");
        System.out.println("Refrence Set \nStarting tth");
        System.out.println("1");



        SocketHandler.tth.execute();


        System.out.println("1");
        System.out.println("tth taaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa started on seperate thread");
        System.out.println("1");

        ipview=(TextView) findViewById(R.id.IPTextView);
        System.out.println("1");
        System.out.println("ipview created");
        System.out.println("1");

        ipview.setText(SocketHandler.getIpAddress());
        System.out.println("1");
        System.out.println("ip view updated");
        System.out.println("1");

        ipview.append("\nPort: "+SocketHandler.getPort());
        System.out.println("1");
        System.out.println("Main thread alllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll completed");
        System.out.println("1");




    }







    public void startchat()
    {
        switching=true;
        Intent inent = new Intent(HostActivity.this, ChatActivity.class);
        startActivity(inent);

    }


    public void toasts(String s)
    {
        switch (s)
        {
            case "connection not closed" : Toast.makeText(getApplicationContext(), "connection not closed\n", Toast.LENGTH_LONG).show();
            case "Client connection closed" : Toast.makeText(getApplicationContext(), "Client connection closed", Toast.LENGTH_LONG).show();
            case "Host connection closed" : Toast.makeText(getApplicationContext(), "Host connection closed", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onDestroy() {
        /**/
        Toast.makeText(getApplicationContext(), "Host Activity destroyed", Toast.LENGTH_LONG).show();
        closing=true;

        SocketHandler.closeall();


        super.onDestroy();
    }








}
