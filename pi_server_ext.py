from socket import *
from time import ctime
import RPi.GPIO as GPIO
import email
import time
import smtplib
import imaplib
from email.MIMEMultipart import MIMEMultipart
from email.MIMEText import MIMEText
from email.MIMEBase import MIMEBase
from email import encoders

ctrCmd = ['Light_01_ON','Light_01_OFF','Light_02_ON','Light_02_OFF','Fan_ON','Fan_OFF','AirC_ON','AirC_OFF']
LIGHT01 = 29
LIGHT02 = 31
FAN = 33
AIR = 35
GPIO.setwarnings(False)
GPIO.cleanup()
GPIO.setmode(GPIO.BOARD)
GPIO.setup(LIGHT01,GPIO.OUT)
GPIO.setup(LIGHT02,GPIO.OUT)
GPIO.setup(FAN,GPIO.OUT)
GPIO.setup(AIR,GPIO.OUT)
def checkEmail():
        mail = imaplib.IMAP4_SSL('imap.gmail.com')
        print("Initial Step 1")
        mail.login('server@gmail.com','passw')
        print("Initial Step 2")
        mail.list()  # Gives list of folders or labels in gmail.
        print("Initial Step 3")
        while True:
                print(">>Checking...")
                try:
                        files = []
                        # Connect to inbox
                        mail.select("inbox"); 
                        #print("first email checking")
                        # Search for an unread email from user's email address
                        result, data = mail.search(None,'(UNSEEN FROM "client@gmail.com")');
                        ids = data[0]   # data is a list
                        id_list = ids.split() # ids is a space separated string

                        latest_email_id = id_list[-1] # get the latest
                        result, data = mail.fetch(latest_email_id, "(RFC822)");

                        raw_email = data[0][1];

                        recv_msg = email.message_from_string(raw_email)

                        if(recv_msg['Subject'] == ctrCmd[0]):
                              GPIO.setmode(GPIO.BOARD)
                              GPIO.output(LIGHT01,False)
                              time.sleep(1)
                              print 'LIGHT01 TUNRED ON'
                        elif(recv_msg['Subject'] == ctrCmd[1]):
                              GPIO.output(LIGHT01,True)
                              time.sleep(1)
                              print 'LIGHT01 TUNRED OFF'
                        elif(recv_msg['Subject'] == ctrCmd[2]):
                              GPIO.output(LIGHT02,False)
                              time.sleep(1)
                              print 'LIGHT02 TUNRED ON'
                        elif(recv_msg['Subject'] == ctrCmd[3]):
                              GPIO.output(LIGHT02,True)
                              time.sleep(1)
                              print 'LIGHT02 TUNRED OFF'
                        elif(recv_msg['Subject'] == ctrCmd[4]):
                              GPIO.output(FAN,False)
                              time.sleep(1)
                              print 'FAN TUNRED ON'
                        elif(recv_msg['Subject'] == ctrCmd[5]):
                              GPIO.output(FAN,True)
                              time.sleep(1)
                              print 'FAN TUNRED OFF'
                        elif(recv_msg['Subject'] == ctrCmd[6]):
                              GPIO.output(AIR,False)
                              time.sleep(1)
                              print 'AIR CONDITION TUNRED ON'
                        elif(recv_msg['Subject'] == ctrCmd[7]):
                              GPIO.output(AIR,True)
                              time.sleep(1)
                              print 'AIR CONDITION TUNRED OFF' 
                        else:
                              print("Not the command: ")+recv_msg['Subject']   
                                                    
                except Exception as e:
                        err = str(e)
                        print(">>>Checking...") 
                        #GPIO.cleanup()
                        time.sleep(1)
                        continue
loop = 1;
while loop:
        try:
                print(">Checking...")
                checkEmail()
        except:
                print("Exception Main Continue Checking...")
                continue
