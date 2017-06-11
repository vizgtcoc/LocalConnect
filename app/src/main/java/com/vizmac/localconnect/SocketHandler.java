package com.vizmac.localconnect;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;

/**
 * Created by pranavam on 3/6/17.
 */

public class SocketHandler {

    static HostActivity ha=null;
    static ConnectActivity ca=null;

    static ServerSocket servsock;
    static Socket sock;
    static int port=0;
    static boolean updatedport=false;
    static String ip=null;

    public static void setIp(String ip) {
        SocketHandler.ip = ip;
    }

    public static void setHa(HostActivity ha) {
        SocketHandler.ha = ha;
    }

    public static void setCa(ConnectActivity ca) {
        SocketHandler.ca = ca;
    }



    public static synchronized Socket getSocket(){
        return sock;
    }

    public static synchronized void setSocket(Socket socket){
        SocketHandler.sock = socket;
    }

    public synchronized static void setPort(int port) {
        SocketHandler.port = port;
    }

    public static int getPort() {
        while(!updatedport)
        {
            try {

                System.out.println("1");
                System.out.println("Thread sleeping ");
                System.out.println("1");
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("1");
                System.out.println(e);
                System.out.println("1");
                System.out.println("1");
                System.out.println("Thrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrreeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaddddddddddddddd not sleeping");
                System.out.println("1");
            }
            System.out.println("1");
            System.out.println("Waiting for boolean to be true");
            System.out.println("1");
        }
        if(port==0)
        {
            System.out.println("1");
            System.out.println("portiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszeroportiszero");
            System.out.println("1");

        }

        return port;
    }

//Cannot create client socketjava.net.ConnectException: failed to connect to /192.168.43.1 (port 8085): connect failed: ENETUNREACH (Network is unreachable)


    static AsyncTask tth=new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {
            System.out.println("1");
            System.out.println("Starting async tack tt");
            System.out.println("1");

            try {
                System.out.println("1");
                System.out.println("Server sock creating");
                System.out.println("1");
                servsock=new ServerSocket(8085);
                System.out.println("1");
                System.out.println("port updating"+servsock.getLocalPort());
                System.out.println("1");
                setPort(servsock.getLocalPort());
                System.out.println("1");
                System.out.println("boolean updating");
                System.out.println("1");
                setUpdatedport(true);
                System.out.println("1");
                System.out.println("Waiting for client");
                System.out.println("1");
                sock=servsock.accept();
                System.out.println("1");
                System.out.println("Clirnt connected");
                System.out.println("1");
                System.out.println("1");
                System.out.println("Async task aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaal completed");
                System.out.println("1");

            }catch (Exception e)
            {
                System.out.println("1");
                System.out.println(e);
                System.out.println("1");


            }
                /*catch (SocketException e)
                {
                    e.printStackTrace();
                    System.out.println("1");
                    System.out.println("");
                    System.out.println("1");
                    Toast.makeText(getApplicationContext(), "No friends connected!\n"+e, Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("1");
                    System.out.println(e);
                    System.out.println("1");
                    Toast.makeText(getApplicationContext(), "No friends connected!\n"+e, Toast.LENGTH_LONG).show();
                }*/


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            //
            //SocketHandler.setSocket(sock);
            System.out.println("1");
            System.out.println("Socket set to socket handler");
            System.out.println("1");
            //Toast.makeText(getApplicationContext(), "Connection Sucessful", Toast.LENGTH_LONG).show();
            ha.startchat();



            super.onPostExecute(o);
        }
    };
    static AsyncTask ttc=new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {

            try {
                System.out.println("1");
                System.out.println("Creating socket ");
                System.out.println("1");
                sock=new Socket(ip,port);
                System.out.println("1");
                System.out.println("Socket created");
                System.out.println("1");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("1");
                System.out.println("Cannot create client socket"+e);
                System.out.println("1");

            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            //
            //SocketHandler.setSocket(sock);
            System.out.println("1");
            System.out.println("Socket set to socket handler");
            System.out.println("1");

            ca.startchat();




            super.onPostExecute(o);
        }
    };


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }



    public static String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "SiteLocalAddress: "
                                + inetAddress.getHostAddress() + "\n";
                    }

                }

            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ip += "Something Wrong! please reload " + e.toString() + "\n";
        }

        return ip;
    }


    public static void closeall()
    {
        if(tth.getStatus()==AsyncTask.Status.RUNNING||tth.getStatus()==AsyncTask.Status.PENDING)
        {
            tth.cancel(true);
        }
        if(ttc.getStatus()==AsyncTask.Status.RUNNING||ttc.getStatus()==AsyncTask.Status.PENDING)
        {
            ttc.cancel(true);
        }
        try {

            if (servsock != null) {
                servsock.close();
                System.out.println("1");
                System.out.println("servsockclosedservsockclosedservsockclosedservsockclosedservsockclosedservsockclosedservsockclosedservsockclosedservsockclosedservsockclosedservsockclosed");
                System.out.println("1");
                ha.toasts("Host connection closed");

            }
            if(sock!=null)
            {
                sock.close();
                System.out.println("1");
                System.out.println("clientsockclosedclientsockclosedclientsockclosedclientsockclosedclientsockclosedclientsockclosedclientsockclosedclientsockclosedclientsockclosedclientsockclosed");
                System.out.println("1");
                ha.toasts("Client connection closed");

            }
        }catch (Exception e)
        {
            System.out.println("1");
            System.out.println("");
            System.out.println("1");
            System.out.println("1");
            System.out.println("unabletocloseunabletocloseunabletocloseunabletocloseunabletocloseunabletocloseunabletocloseunabletocloseunabletocloseunabletocloseunabletocloseunabletoclose"+e);
            System.out.println("1");


        }
    }

    public static synchronized void setUpdatedport(boolean updatedport) {
        SocketHandler.updatedport = updatedport;
    }



}
