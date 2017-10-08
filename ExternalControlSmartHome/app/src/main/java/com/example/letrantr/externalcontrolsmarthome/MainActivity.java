package com.example.letrantr.externalcontrolsmarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
public class MainActivity extends AppCompatActivity {
    String strEmail;
    String strSerEmail;
    String strPass;
    String IP;
    int PORT;
    TextView iPAdd;
    TextView passW;
    TextView serverEmail;
    TextView clientEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSend = (Button)this.findViewById(R.id.btnSend);
        final ToggleButton togSW1 = (ToggleButton)this.findViewById(R.id.togSW1);
        final ToggleButton togSW2 = (ToggleButton)this.findViewById(R.id.togSW2);
        final ToggleButton togSW3 = (ToggleButton)this.findViewById(R.id.togSW3);
        final ToggleButton togSW4 = (ToggleButton)this.findViewById(R.id.togSW4);
        final Switch swNetwork = (Switch)this.findViewById(R.id.swNetwork);
        clientEmail = (TextView)this.findViewById(R.id.txtEmail);
        serverEmail = (TextView)this.findViewById(R.id.txtServerEmail);
        passW = (TextView)this.findViewById(R.id.txtPass);
        iPAdd = (TextView)this.findViewById(R.id.txtIP);

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //sendEmail("Test","Content");
            }
        });
        togSW1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getIPandPort();
                if(togSW1.isChecked()&&!swNetwork.isChecked())
                {
                    sendEmail("Light_01_ON","Light_01_ON");
                }
                else if(!togSW1.isChecked()&&!swNetwork.isChecked())
                {
                    sendEmail("Light_01_OFF","Light_01_OFF");
                }
                else if(togSW1.isChecked()&&swNetwork.isChecked())
                {
                    sendWifiCmd(IP,PORT,"Light_01_ON");
                }
                else if(!togSW1.isChecked()&&swNetwork.isChecked())
                {
                    sendWifiCmd(IP,PORT,"Light_01_OFF");
                }
            }
        });
        togSW2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(togSW2.isChecked()&&!swNetwork.isChecked())
                {
                    sendEmail("Light_02_ON","Light_02_ON");
                }
                else if(!togSW2.isChecked()&&!swNetwork.isChecked())
                {
                    sendEmail("Light_02_OFF","Light_02_OFF");
                }
                else if(togSW2.isChecked()&&swNetwork.isChecked())
                {
                    sendWifiCmd(IP,PORT,"Light_02_ON");
                }
                else if(!togSW2.isChecked()&&swNetwork.isChecked())
                {
                    sendWifiCmd(IP,PORT,"Light_02_OFF");
                }
            }
        });
        togSW3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(togSW3.isChecked()&&!swNetwork.isChecked())
                {
                    sendEmail("Fan_ON","Fan_ON");
                }
                else if(!togSW3.isChecked()&&!swNetwork.isChecked())
                {
                    sendEmail("Fan_OFF","Fan_OFF");
                }
                else if(togSW3.isChecked()&&swNetwork.isChecked())
                {
                    sendWifiCmd(IP,PORT,"Fan_ON");
                }
                else if(!togSW3.isChecked()&&swNetwork.isChecked())
                {
                    sendWifiCmd(IP,PORT,"Fan_OFF");
                }
            }
        });
        togSW4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(togSW4.isChecked()&&!swNetwork.isChecked())
                {
                    sendEmail("AirC_ON","AirC_ON");
                }
                else if(!togSW4.isChecked()&&!swNetwork.isChecked())
                {
                    sendEmail("AirC_OFF","AirC_OFF");
                }
                else if(togSW4.isChecked()&&swNetwork.isChecked())
                {
                    sendWifiCmd(IP,PORT,"AirC_ON");
                }
                else if(!togSW4.isChecked()&&swNetwork.isChecked())
                {
                    sendWifiCmd(IP,PORT,"AirC_OFF");
                }
            }
        });
    }
    private void sendEmail(String subj, String msg) {
        strEmail = clientEmail.getText().toString();
        strSerEmail = serverEmail.getText().toString();
        strPass = passW.getText().toString();
        String email = strSerEmail;
        String subject = subj;
        String message = msg;
        //Creating SendMail object
        ActivitySendEmail sm = new ActivitySendEmail(this, email, subject, message, strEmail, strPass);
        //Executing sendmail to send email
        sm.execute();
    }
    private void sendWifiCmd(String ipAddress, int ipPort, String CMD)
    {
        WifiComActivity wifi =new WifiComActivity(ipPort,ipAddress,CMD);
        wifi.execute();
    }
    public void getIPandPort()
    {
        String iPadPort = iPAdd.getText().toString();
        String temp[]= iPadPort.split(":");
        IP = temp[0];
        PORT = Integer.valueOf(temp[1]);
    }

}
