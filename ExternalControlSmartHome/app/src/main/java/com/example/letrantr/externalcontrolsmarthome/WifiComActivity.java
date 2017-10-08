package com.example.letrantr.externalcontrolsmarthome;

import android.os.AsyncTask;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class WifiComActivity extends AsyncTask<Void,Void,Void> {

    Socket socket;
    public static String wifiModuleIp = "";
    public static int wifiModulePort = 0;
    String CMD = "0";
    public WifiComActivity(int wifiModulePort, String wifiModuleIp,String CMD)
    {
        this.wifiModuleIp = wifiModuleIp;
        this.wifiModulePort = wifiModulePort;
        this.CMD = CMD;
    }

    @Override
    protected Void doInBackground(Void... params){
        try{
            InetAddress inetAddress = InetAddress.getByName(wifiModuleIp);
            socket = new java.net.Socket(inetAddress,wifiModulePort);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes(CMD);
            dataOutputStream.close();
            socket.close();
        }catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
        return null;
    }


}
