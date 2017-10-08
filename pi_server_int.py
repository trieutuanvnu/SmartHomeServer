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

HOST = ''
PORT = 21567
BUFSIZE = 1024
ADDR = (HOST,PORT)
LIGHT01 = 29
LIGHT02 = 31
FAN = 33
AIR = 35
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
GPIO.setup(LIGHT01,GPIO.OUT)
GPIO.setup(LIGHT02,GPIO.OUT)
GPIO.setup(FAN,GPIO.OUT)
GPIO.setup(AIR,GPIO.OUT)
##
tcpSerSock = socket(AF_INET, SOCK_STREAM)
tcpSerSock.bind(ADDR)
tcpSerSock.listen(5)

while True:
        print 'Waiting for connection'
        tcpCliSock,addr = tcpSerSock.accept()
        print '...connected from :', addr
        try:
                while True:
                        data = ''
                        data = tcpCliSock.recv(BUFSIZE)
                        if not data:
                                        break
                        if data == ctrCmd[0]:
                                GPIO.output(LIGHT01,False)
                                print 'LIGHT01 TUNRED ON'
                        if data == ctrCmd[1]:
                                GPIO.output(LIGHT01,True)
                                print 'LIGHT01 TUNRED OFF'
                        if data == ctrCmd[2]:
                                GPIO.output(LIGHT02,False)
                                print 'LIGHT02 TUNRED ON'
                        if data == ctrCmd[3]:
                                GPIO.output(LIGHT02,True)
                                print 'LIGHT02 TUNRED OFF'      
                        if data == ctrCmd[4]:
                                GPIO.output(FAN,False)
                                print 'FAN TUNRED ON'
                        if data == ctrCmd[5]:
                                GPIO.output(FAN,True)
                                print 'FAN TUNRED OFF'  
                        if data == ctrCmd[6]:
                                GPIO.output(AIR,False)
                                print 'AIR CONDITION TUNRED ON'
                        if data == ctrCmd[7]:
                                GPIO.output(AIR,True)
                                print 'AIR CONDITION TUNRED OFF'                                                                
        except KeyboardInterrupt:
                        GPIO.cleanup()
tcpSerSock.close();
