#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>     // for open/close
#include <fcntl.h>      // for O_RDWR
#include <sys/ioctl.h>  // for ioctl
#include <sys/mman.h>
#include <errno.h>
#include <string.h>
#include <sys/poll.h>
#include <linux/fb.h>
#include <linux/videodev2.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <arpa/inet.h>
#include <time.h>
#include <malloc.h>
#include "videodev2.h"
#include "SecBuffer.h"
#include "camera.h"
#include <pthread.h>
#include <termios.h>
#include <sys/types.h>
#include <sys/stat.h>


	


     
 
	#define BAUDRATE B115200
        #define MODEMDEVICE "/dev/ttyACM0"
        #define _POSIX_SOURCE 1 /* POSIX compliant source */
        #define FALSE 0
        #define TRUE 1
        #define DRIVER_NAME		"/dev/cnbuzzer"
        #define MAX_BUZZER_NUMBER		36
	#define DRIVER_NAME2		"/dev/cnoled"

	#define RST_BIT_MASK	0xEFFF		
	#define CS_BIT_MASK		0xF7FF
	#define DC_BIT_MASK		0xFBFF
	#define WD_BIT_MASK		0xFDFF
	#define RD_BIT_MASK		0xFEFF
	#define DEFAULT_MASK	0xFFFF


	#define CMD_SET_COLUMN_ADDR		0x15
	#define CMD_SET_ROW_ADDR		0x75
	#define CMD_WRITE_RAM			0x5C
	#define CMD_READ_RAM			0x5D
	#define CMD_LOCK			0xFD

	#define FBDEV_FILE  "/dev/fb0"
	#define BIT_VALUE_24BIT   24

	#define V4L2_CID_CACHEABLE                      (V4L2_CID_BASE+40)
	

static unsigned short gamma[64]= 
{
0xB8,
0x02, 0x03, 0x04, 0x05,
0x06, 0x07, 0x08, 0x09,
0x0A, 0x0B, 0x0C, 0x0D,
0x0E, 0x0F, 0x10, 0x11,
0x12, 0x13, 0x15, 0x17,
0x19, 0x1B, 0x1D, 0x1F,
0x21, 0x23, 0x25, 0x27,
0x2A, 0x2D, 0x30, 0x33,
0x36, 0x39, 0x3C, 0x3F,
0x42, 0x45, 0x48, 0x4C,
0x50, 0x54, 0x58, 0x5C,
0x60, 0x64, 0x68, 0x6C,
0x70, 0x74, 0x78, 0x7D,
0x82, 0x87, 0x8C, 0x91,
0x96, 0x9B, 0xA0, 0xA5,
0xAA, 0xAF, 0xB4

};

static int oled;

int reset(void)
{
	unsigned short wdata;

	wdata = RST_BIT_MASK;
	write(oled, &wdata, 2);
	usleep(2000);
	wdata = DEFAULT_MASK;
	write(oled, &wdata, 2);
	return TRUE;
}
int writeCmd(int size , unsigned short* cmdArr)
{
	int i ;
	unsigned short wdata;

	//printf("wCmd : [0x%02X]",cmdArr[0]);
	//wdata = CS_BIT_MASK;
	//write(fd,&wdata,2);

	wdata = CS_BIT_MASK & DC_BIT_MASK;
	write(oled,&wdata,2);

	wdata = CS_BIT_MASK & DC_BIT_MASK & WD_BIT_MASK ;
	write(oled,&wdata,2);

	wdata = CS_BIT_MASK & DC_BIT_MASK & WD_BIT_MASK & (cmdArr[0]|0xFF00) ;
	write(oled,&wdata,2);
	
	wdata = CS_BIT_MASK & DC_BIT_MASK & (cmdArr[0] | 0xFF00) ;
	write(oled,&wdata,2);

	wdata = CS_BIT_MASK & ( cmdArr[0] | 0xFF00);
	write(oled,&wdata,2);

	for (i = 1; i < size ; i++ )
	{
	//	wdata = CS_BIT_MASK ;
	//	write(fd,&wdata,2);

	//	wdata = CS_BIT_MASK ;
	//	write(fd,&wdata,2);

		wdata = CS_BIT_MASK & WD_BIT_MASK ;
		write(oled,&wdata,2);

		wdata = CS_BIT_MASK & WD_BIT_MASK & (cmdArr[i] | 0xFF00) ;
		write(oled,&wdata,2);

		wdata = CS_BIT_MASK & (cmdArr[i] | 0xFF00);
		write(oled,&wdata,2);

	//	wdata = CS_BIT_MASK & (cmdArr[i] | 0xFF00);
	//	write(fd,&wdata,2);
	//	printf("[0x%02X]",cmdArr[i]);

	}
	wdata= DEFAULT_MASK;
	write(oled,&wdata,2);
	//printf("\n");
	return TRUE;
}
int writeData(int size , unsigned char* dataArr)
{
	int i ;
	unsigned short wdata;
	
	//wdata = CS_BIT_MASK;
	//write(fd,&wdata,2);

	wdata = CS_BIT_MASK & DC_BIT_MASK;
	write(oled,&wdata,2);

	//wdata = CS_BIT_MASK & DC_BIT_MASK & WD_BIT_MASK ;
	//write(fd,&wdata,2);

	wdata = CS_BIT_MASK & DC_BIT_MASK & WD_BIT_MASK & (CMD_WRITE_RAM | 0xFF00) ;
	write(oled,&wdata,2);

	wdata = CS_BIT_MASK & DC_BIT_MASK & (CMD_WRITE_RAM | 0xFF00);
	write(oled,&wdata,2);

	wdata = CS_BIT_MASK &  (CMD_WRITE_RAM | 0xFF00);
	write(oled,&wdata,2);

	for (i = 0; i < size ; i++ )
	{
		wdata = CS_BIT_MASK & WD_BIT_MASK ;
		write(oled,&wdata,2);

		wdata = CS_BIT_MASK & WD_BIT_MASK & ((unsigned char)dataArr[i] | 0xFF00 );
		write(oled,&wdata,2);

		wdata = CS_BIT_MASK & ( (unsigned char)dataArr[i] | 0xFF00);
		write(oled,&wdata,2);


	}
	wdata = DEFAULT_MASK;
	write(oled,&wdata,2);

	return TRUE;

}
int setCmdLock(int bLock)
{
	unsigned short  cmd[3];
	
	cmd[0] = CMD_LOCK;
	if (bLock)
	{
		cmd[1] = 0x16; // lock
		writeCmd(2,cmd);

	}
	else
	{
		cmd[1] = 0x12; // lock
		writeCmd(2,cmd);

		// A2,B1,B3,BB,BE accessible
		cmd[1] = 0xB1;
		writeCmd(2,cmd);
	}
	return TRUE;
}


int Init(void)
{
	unsigned short wdata[10];
	unsigned char  wcdata[10];
	int i,j;
	wdata[0]= 0xFD;
	wdata[1] = 0x12;
	writeCmd(2,wdata);

	
	wdata[0] = 0xFD;
	wdata[1] = 0xB1;
	writeCmd(2,wdata);

	wdata[0] = 0xAE;
	writeCmd(1,wdata);

	wdata[0] = 0xB3;
	wdata[1] = 0xF1;
	writeCmd(2,wdata);

	wdata[0] = 0xCA;
	wdata[1] = 0x7F;
	writeCmd(2,wdata);

	wdata[0] = 0xA2;
	wdata[1] = 0x00;
	writeCmd(2,wdata);

	wdata[0]= 0xA1;
	wdata[1]=0x00;
	writeCmd(2,wdata);

	wdata[0]= 0xA0;
	wdata[1] = 0xB4;
	writeCmd(2,wdata);

	wdata[0] = 0xAB;
	wdata[1] = 0x01;
	writeCmd(2,wdata);

	wdata[0] = 0xB4;
	wdata[1] = 0xA0;
	wdata[2] = 0xB5;
	wdata[3] = 0x55;
	writeCmd(4,wdata);

	wdata[0] = 0xC1;
	wdata[1] = 0xC8;
	wdata[2] = 0x80;
	wdata[3] = 0xC8;
	writeCmd(4,wdata);

	wdata[0] = 0xC7;
	wdata[1] = 0x0F;
	writeCmd(2,wdata);

	// gamma setting 
	writeCmd(64,gamma);


	wdata[0] = 0xB1;
	wdata[1] = 0x32;
	writeCmd(2,wdata);

	wdata[0] = 0xB2;
	wdata[1] = 0xA4;
	wdata[2] = 0x00;
	wdata[3] = 0x00;
	writeCmd(4,wdata);

	wdata[0] = 0xBB;
	wdata[1] = 0x17;
	writeCmd(2,wdata);

	wdata[0] = 0xB6;
	wdata[1] = 0x01;
	writeCmd(2, wdata);

	wdata[0]= 0xBE;
	wdata[1] = 0x05;
	writeCmd(2, wdata);

	wdata[0] = 0xA6;
	writeCmd(1,wdata);
	

	for (i = 0; i < 128;i++ )
	{
		for(j = 0; j < 128; j++ )
		{
			wcdata[0]= 0x3F;
			wcdata[1]= 0;
			wcdata[2] = 0;
			writeData(3,wcdata);
		}
	
	}

	wdata[0] = 0xAF;
	writeCmd(1,wdata);





	return TRUE;
}
int imageLoading(char* fileName)
{
	int imgfile;
	unsigned char* data =NULL;
	int  width , height;

	imgfile = open(fileName , O_RDONLY );
	if ( imgfile < 0 ) 
	{
		printf ("imageloading(%s)  file is not exist . err.\n",fileName);
		return FALSE;
	}
	setCmdLock(FALSE);


	read(imgfile ,&width , sizeof(unsigned char));
	read(imgfile ,&height , sizeof(unsigned char));

	data = malloc( 128 * 128 * 3 );

	read(imgfile, data , 128 * 128 *3 );

	close(imgfile);

	writeData(128 * 128 *3 , data );

	setCmdLock(TRUE);
	return TRUE;
}

void* d_function(char *data){
	
	oled = open("/dev/cnoled",O_RDWR);
	if ( oled < 0 )
	{
		perror("driver open error.\n");
		return 1;
	}
	reset();
	Init();
	imageLoading("r.bmp.img");
	close(oled);

}
 void *bus_function(char *data)
{
	int retvalue;
	int segnum;
	int fd, fd1;
	int fd3;
	
	int buzzerNumber;

	const unsigned short segNum[8] = { 0x7F, 0x27, 0x7D, 0x6D, 0x66, 0x4F, 0x5B, 0x06 };
	const unsigned short segLoc[1] = { 0xdf00 };
	int i, j;
	int led[8] = { 0x7F,0x3F, 0x1F, 0x0F, 0x07, 0x03, 0x01, 0x00 };


	fd = open("/dev/led", O_RDWR);
	fd1 = open(DRIVER_NAME, O_RDWR);
	fd3 = open("/dev/SEGMENT", O_RDWR);
	oled =  open("/dev/cnoled",O_RDWR);
	

	if ( oled < 0 )
	{
		perror("driver open error.\n");
		return 1;
	}
	
	 if ( fd1 < 0 )
        {
                perror("driver (//dev//cnbuzzer) open error.\n");
                return 1;
        }
        // control led 
        

	if(fd <0){
		perror("error \n");
		return 1;
	}	
	if(fd3 <0){
		perror("error \n");
		return 1;
	}
	
   	 	buzzerNumber = 10;
		retvalue = 0xFF;
                write(fd, &retvalue, 2);
		
		segnum = 0xdf + 0x7f;
		write(fd3, &segnum, 2);
		
		for(i = 0; i <8; i++){
		
		 segnum = segLoc[0] + segNum[i];
		 write(fd3, &segnum, 2);
		 imageLoading("g.bmp.img");
		 write(fd, &retvalue, 2);
		 retvalue = led[i];
		 
		 write(fd1, &buzzerNumber, 4); 
	 	 usleep(1000000);
		
		if(i % 2 == 0)
		{
			buzzerNumber = i + 10;
		}
	
		 
		
		}
		retvalue = 0x00;
		write(fd, &retvalue, 2);
		imageLoading("r.bmp.img");
		segnum = segLoc[0] + 0x3F;
		 write(fd3, &segnum, 2);
		buzzerNumber = 0;
		write(fd1, &buzzerNumber, 4);
	
			
	
	close(fd);
	close(fd1);
	close(fd3);
	close(oled);
}


int main(int argc, char **argv)
{
	pthread_t p_thread1, p_thread2;
	int fd,c,res;
	char buf1;
        struct termios oldtio,newtio;
        char buf[255];
	int retvalue;
	int status, status1;
	int fd1;


	fd = open("/dev/dipsw", O_RDWR);
	fd1 = open(MODEMDEVICE, O_RDWR | O_NOCTTY);
	if (fd < 0){
		perror("driver open error. \n");
		return 1;
	}
	if (fd1 <0) {perror(MODEMDEVICE); exit(-1); }

	  tcgetattr(fd1, &oldtio); /* save current port settings */
        
        bzero(&newtio, sizeof(newtio));
        newtio.c_cflag = BAUDRATE | CRTSCTS | CS8 | CLOCAL | CREAD;
        newtio.c_iflag = IGNPAR;
        newtio.c_oflag = 0;
        
        /* set input mode (non-canonical, no echo,...) */
        newtio.c_lflag = 0;
         
        newtio.c_cc[VTIME]    = 0;   /* inter-character timer unused */
        newtio.c_cc[VMIN]     = 5;   /* blocking read until 5 chars received */
        
        tcflush(fd1, TCIFLUSH);
        tcsetattr(fd1,TCSANOW,&newtio);
        

	while(1){
	read(fd, &retvalue, 4);
	retvalue &= 0xF;
	printf("dipsw state : %X\n", retvalue);
	if(retvalue == 1)
	{
		buf1 = 2;
		write(fd1, &buf1, 2);
		
		pthread_create(&p_thread1, NULL, bus_function, buf);
		pthread_join(p_thread1, (void *)&status);
		
		usleep(100000);
		
	}
	else if(retvalue == 2)
	{
		
          	buf1 = 1;
		write(fd1, &buf1, 2);
		
		

		pthread_create(&p_thread2, NULL, d_function, buf);
		pthread_join(p_thread2, (void *)&status1);
		usleep(100000);
		
	}
	else if(retvalue == 4)
	{
		
	
		make_camera();
		
		
	}
	
	usleep(100000);
	}
	

	close(fd);
	tcsetattr(fd1,TCSANOW,&oldtio);
	return retvalue;
}
