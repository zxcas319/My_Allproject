#include <ThreadController.h>
#include <Stepper.h>
#include <VirtualWire.h>
ThreadController controll = ThreadController();
#include <SoftwareSerial.h>
#include <DFPlayer_Mini_Mp3.h>
#include <RCSwitch.h>
// 1.오른쪽 2.계단 3.벽 4.빨간불 5.초록불 6왼쪽
int trig1 = A7;      // 왼쪽 Trig
int trig2 = A1;      // 오른쪽 Trig
int trig3 = A2;      // 위 Trig
int trig4 = A3;      // 아래 Trig


int echo1 = A8;      // 왼쪽 Echo
int echo2 = A9;      // 오른쪽 Echo
int echo3 = A10;      //  위 Echo
int echo4 = A11;      // 아래 Echo

//light 코드 선언 부분
int val=0;
int LED=A0;
int percent;

//received 선언 부분
byte message[VW_MAX_MESSAGE_LEN]; // a buffer to store the incoming messages
byte messageLength = VW_MAX_MESSAGE_LEN; // the size of the message
const int RX_DIO_Pin = 11; // default 11 수신


int d = 45;               // Right, Left distance
int d3 = 75;                  // Front distance
int d4 = 50;                  // Upside distance
long duration1, value1;
long duration2, value2;
long duration3, value3;
long duration4, value4;
RCSwitch mySwitch = RCSwitch();
//쓰레드 선언 부분
Thread myThread = Thread();
Thread myThread2 = Thread();
Thread myThread3 = Thread();
Thread myThread4 = Thread();

Thread lightThread = Thread();
Thread ringThread = Thread();
Thread receivedThread=Thread();

long microsecondsToCentimeters(long microseconds)
{
  return microseconds/29/2; 
}



///////////////////////////////////왼쪽초음파//////////////////////////////////////////////////
void ch1(){
 
  digitalWrite(trig1,LOW);                           // Trigger Output    
  delayMicroseconds(2);       
  digitalWrite(trig1,HIGH);    
  delayMicroseconds(2);
  digitalWrite(trig1,LOW);
  duration1 = pulseIn(echo1,HIGH);                   // Read Pulse Duration
  value1 = microsecondsToCentimeters(duration1);     // Count Distance
  
  Serial.print(" 왼쪽 =");
  Serial.println(value1);
   if(value1<40){
    Serial.println("왼쪽가 먹힘");
     Serial.println(value1);
   //if(ringThread.shouldRun()){
     //ringThread.run();
       
      //}
    mp3_play (1);    //0001 파일 플레이
       delay (1300);
     //analogWrite(10,0);
     mp3_stop();
  } 
  
}

/////////////////////////////////오른쪽초음파 ///////////////////////////
void ch2(){
  
     digitalWrite(trig2,LOW);   
  delayMicroseconds(2);       
  digitalWrite(trig2,HIGH);    
  delayMicroseconds(2);
  digitalWrite(trig2,LOW);
  duration2 = pulseIn(echo2,HIGH);
  value2 = microsecondsToCentimeters(duration2);
  
Serial.print(" 오른쪽 =");
  Serial.println(value2);
  if(value2<40){
    Serial.println("오른쪽 먹힘");
   
     
   //if(ringThread.shouldRun()){
     // ringThread.run();
      
      //}
      mp3_play (5);    //0001 파일 플레이
    delay (1300);
    
       //analogWrite(10,0);
       mp3_stop();
  }
  }




 ///////////////////////////위쪽, 아래 초음파 ///////////////////////// 
void ch3(){
     digitalWrite(trig3,LOW);   
  delayMicroseconds(2);       
  digitalWrite(trig3,HIGH);    
  delayMicroseconds(5);
  digitalWrite(trig3,LOW);
  duration3 = pulseIn(echo3,HIGH);
  value3 = microsecondsToCentimeters(duration3);
  delay(100);
    Serial.print("위 =");
  Serial.println(value3);
  

digitalWrite(trig4,LOW);   
  delayMicroseconds(2);       
  digitalWrite(trig4,HIGH);    
  delayMicroseconds(5);
  digitalWrite(trig4,LOW);
  duration4 = pulseIn(echo4,HIGH);
  value4 = microsecondsToCentimeters(duration4);
  delay(50);
    Serial.print("아래 =");
  Serial.println(value4);




  
  int distance;
  distance= value3-value4;


Serial.print("distance");
Serial.println(distance);

  if(distance>10 && distance<20)
  {
    if(value3< 75+distance && value4 <75)   //아래초음파 기준 80cm이상의 허공 잡는 것을 막음
    {
    Serial.println("벽 먹힘");
 //if(ringThread.shouldRun()){
   //   ringThread.run();
      
      //}
      mp3_play(6);
      delay(1850);
      mp3_stop(); 
  }
  }


  else if(distance > 25 )
  {
     if(value4< 75)   //80cm이상의 허공 잡는 것을 막음
    {
    Serial.println("계단이 먹힘");
 //if(ringThread.shouldRun()){
     // ringThread.run();
      
      //}
      
       mp3_play(2);
       delay(1300);
       mp3_stop();
       analogWrite(10,0);
      
    
  }
  }
 

  
 
  }
  
void light(){
val= analogRead(A6);
     percent = map(val,0,1023,0,100);
     if(percent<50) digitalWrite(LED, LOW);
     else digitalWrite(LED, HIGH); //여기서 빛의 양이 적으면 led가 작동
     Serial.println("조도");
     Serial.println("*");
     Serial.println(percent);
     Serial.println("*");
     delay(100);
      }
 void ring(){
    analogWrite(10,500);
  } 
  /*    
void received(){
     
Serial.println("확인");      
     if (vw_get_message(message, &messageLength)) // Non-blocking 

{ 
int blue=0;int red=0;
Serial.print("Received: "); 

//   for (int i = 0; i < messageLength; i++) 

//    { 
      
       
      if(message[0]=='1')
      
      {
        blue++;red=0;Serial.print("###blue###");//빨간불
        Serial.print(blue);
     if(blue==1&&red==0){
          analogWrite(10,255);
            Serial.print("*****");
           Serial.write(message[0]);
          Serial.print("*****");
         mp3_play(4);
         delay(1500);
         
        }
     }
      else{
        blue=0;red++;Serial.print("###red###");Serial.print(red);//초록불
        if(red==1&&blue==0){
              
            Serial.print("*****");
           Serial.write(message[0]);
          Serial.print("*****");
          
          mp3_play(5);
          delay(1500);
          }
      }
        analogWrite(10,0);
    } 

      Serial.println(); 

//} 



  }//received end
   */ 
void setup() 
{
  //왼오 초음파
  pinMode(trig1, OUTPUT);
  pinMode(trig2, OUTPUT);
  pinMode(echo1, INPUT)  ;
  pinMode(echo2, INPUT);
// 위아래 초음파
 pinMode(trig3, OUTPUT);
  pinMode(trig4, OUTPUT);
  pinMode(echo3, INPUT);
  pinMode(echo4, INPUT);
  
  
  Serial.begin(9600);
   mp3_set_serial (Serial);
   delay(1);
   mp3_set_volume (30);       
    //초음파 작동
    myThread.onRun(ch1);
    myThread.setInterval(50);
    myThread2.onRun(ch2);
    myThread2.setInterval(50);

     myThread3.onRun(ch3);
    myThread3.setInterval(50);
    myThread4.onRun(ch3);
    myThread4.setInterval(50);
// 수신


  pinMode(LED,OUTPUT);

    //led 작동
    
   lightThread.onRun(light);
   lightThread.setInterval(50);

  //ring 작동
    ringThread.onRun(ring);
   ringThread.setInterval(50);

  //received 작동
  vw_set_rx_pin(RX_DIO_Pin); // Initialize RX pin
  vw_setup(2000); // Transfer speed : 2000 bits per sec
  vw_rx_start(); // Start the receiver
  //receivedThread.onRun(received);
  // receivedThread.setInterval(50);

  
   
   //controll 추가
   controll.add(&myThread);
   controll.add(&myThread2);  
   controll.add(&myThread3);
   controll.add(&myThread4);
   
   controll.add(&lightThread);
   controll.add(&ringThread);
   controll.add(&receivedThread);

}
                                 
void loop() 
{

     
   
if (vw_get_message(message, &messageLength)) // Non-blocking 

{ 
int blue=0;int red=0;
Serial.print("Received: "); 

  // for (int i = 0; i < messageLength; i++) 

  //  { 
      
       
 if(message[0]=='1')
      
  {
       blue++;red=0;Serial.print("###blue###");//빨간불
       Serial.print(blue);
     if(ringThread.shouldRun()){
      ringThread.run();}
     if(blue==1&&red==0){
          analogWrite(10,255);
            Serial.print("*****");
           Serial.write(message[0]);
            Serial.print("*****");
           mp3_play(3);
           delay(3500);
         
        }
     }
      else{
        if(ringThread.shouldRun()){
      ringThread.run();}
        blue=0;
        red++;
        Serial.print("###red###");Serial.print(red);//초록불
        
        
        if(red==1&&blue==0) 
        {
              
           Serial.print("*****");
           Serial.write(message[0]);
           Serial.print("*****");
          
          mp3_play(4);
          delay(3500);
          }
      }
        analogWrite(10,0);
    } 






 
 
   if(myThread.shouldRun())
  myThread.run();
  if(myThread2.shouldRun())
   myThread2.run();
   
  if(myThread3.shouldRun())
   myThread3.run();

 if(myThread4.shouldRun())
   myThread4.run();
  /*
  if(receivedThread.shouldRun())
      receivedThread.run();
  */    
      if(lightThread.shouldRun())
        lightThread.run();
}



