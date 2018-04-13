#include <stdio.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <time.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <mysql.h>
#include<my_global.h>
#define DB_HOST "127.0.0.1"
#define DB_USER "root"
#define DB_PASS "12345"
#define DB_NAME "test"

int main(void)
{
	int			servSock;
	int			so_reuseaddr = 1;
	int			clntSock;
	struct sockaddr_in	servAddr;
	int			servAddrLen;
	struct sockaddr_in	clntAddr;
	int			clntAddrLen;
	int			count = 0;
	char*			bmp;
	char			cmd[100];
	FILE*			fbmp;
	int			segment;
	char*			buffer;
	int			bufferLen;

	if((servSock = socket(PF_INET, SOCK_STREAM, 0)) == -1)
	{
		printf("socket create error\n");
		return 0;
	}
	servAddrLen = sizeof(servAddr);
	memset(&servAddr, 0, servAddrLen);
	servAddr.sin_family = AF_INET;
	servAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servAddr.sin_port = htons((unsigned short)3000);
	setsockopt(servSock, SOL_SOCKET, SO_REUSEADDR, &so_reuseaddr, sizeof(so_reuseaddr));
	if(bind(servSock, (struct sockaddr*)&servAddr, servAddrLen))
	{
		printf("bind error\n");
		close(servSock);
		return 0;
	}
	if(listen(servSock, 1000))
	{
		printf("listen error\n");
		return 0;
	}

	bmp = (char*)malloc(sizeof(char) * 24);
	buffer = (char*)malloc(sizeof(char) * 1001);

	while(1)
	{
		clntAddrLen = sizeof(clntAddr);
		if((clntSock = accept(servSock, (struct sockaddr*)&clntAddr, &clntAddrLen)) < 0)
		{
			printf("accept error\n");
			close(servSock);
			return 0;
		}

		if((bufferLen = recv(clntSock, buffer, 1000, 0)) <= 0)
		{
			printf("receive error 0\n");
			close(clntSock);
			return 0;
		}
		send(clntSock, buffer, 1,0);

		for(int i = 0; i < 19; i++)
			bmp[i] = buffer[i];
		bmp[19] = 0;
		strcat(bmp, ".bmp");
		fbmp = fopen(bmp, "wb");
		sscanf(buffer + 19, "%03d", &segment);
		fwrite(buffer + 22, 1, bufferLen - 22, fbmp);
		for(int i = 1; i < segment; i++)
		{
			if((bufferLen = recv(clntSock, buffer, 1000, 0)) <= 0)
			{
				printf("receive error %d\n", i);
				fclose(fbmp);
				close(clntSock);
				return 0;
			}
			fwrite(buffer, 1, bufferLen, fbmp);
			send(clntSock, buffer, 1,0);
		}
		fclose(fbmp);
		sprintf(cmd, "mv '%s' ./public", bmp);
		system(cmd);
		printf("received\n");

		MYSQL		*connection=NULL, conn;
		MYSQL_RES	*sql_result;
		MYSQL_ROW	sql_row;
		char		query[255];

		mysql_init(&conn);

		connection = mysql_real_connect(&conn, DB_HOST, DB_USER, DB_PASS, DB_NAME, 3306, (char *)NULL, 0);
		if(connection == NULL)
		{
			fprintf(stderr, "Mysql connection error : %s", mysql_error(&conn));
			return 0;
		}
		sprintf(query, "insert into pic(image, url) values ('%s', 'http://13.124.115.165:8080/%s')", bmp, bmp);

		if(mysql_query(connection, query) != 0)
		{
			fprintf(stderr, "Mysql query error : %s", mysql_error(&conn));
			return 0;
		}

		mysql_close(connection);
	}

	return 0;
}
