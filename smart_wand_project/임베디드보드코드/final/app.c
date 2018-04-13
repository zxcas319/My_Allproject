#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/ioctl.h>
#include<ctype.h>
#include<sys/ipc.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<unistd.h>

int key()
{
	int keypad_num;
	int fd;
	int num;
	int i;

	fd=open("/dev/key", O_RDWR);
	if( fd < 0 )
	{
		perror("driver open error. \n");
		return 1;
	}
	while(1)
	{
		int repeat;
		read(fd, &keypad_num, 4);
		if(keypad_num != 0 && repeat != keypad_num)
		{
			printf("NO:%d \n", keypad_num);
			repeat = keypad_num;
		}
	}
	close(fd);
	return keypad_num;
}
