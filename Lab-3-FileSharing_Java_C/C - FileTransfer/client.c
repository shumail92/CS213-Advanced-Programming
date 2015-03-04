/*
  Advanced Programming - Lab 3

  Simple File Sharing
  client.c

  by Shumail Mohy-ud-Din

*/

#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>

int main(void)
{
    int sockfd = 0;
    int bytesReceived = 0;
    
    //buffer for storing the chunk of file being recieved form server
    char recvBuff[256];

    //setting the memory for buffer
    memset(recvBuff, '0', sizeof(recvBuff));

    //sockaddr_in is a structure
    struct sockaddr_in serv_addr;

    /* Create a socket first */
    if((sockfd = socket(AF_INET, SOCK_STREAM, 0))< 0)
    {
        printf("\n Error : Could not create socket \n");
        return 1;
    }

    /* Initialize sockaddr_in structure */
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(55556); // port
    serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");

    /* Try to Connect and check if the connection is successful  */
    int v = 0;
    if(v = connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr))<0)
    {
        printf("\n Error : Connect Failed: %d\n", v);
        close(sockfd);
        return 1;
    }

    /* Creating file on client side where data will be stored */
    FILE *fp;
    //opening the file
    fp = fopen("javaserver_tocC", "ab"); 
    if(NULL == fp)
    {
        printf("Error opening file");
        close(sockfd);
        return 1;
    }

    // While loop - here it Receives data in chunks of 256 bytes 
    while((bytesReceived = read(sockfd, recvBuff, 256)) > 0)
    {
        printf("Bytes received %d\n",bytesReceived);    
        // recvBuff[n] = 0;
        //writing the recieved data into buffer
        fwrite(recvBuff, 1,bytesReceived,fp);
        // printf("%s \n", recvBuff);
    }

    if(bytesReceived < 0)
    {
        printf("\nError: Can't read the file \n");
    }

    close(sockfd);
    return 0;
}