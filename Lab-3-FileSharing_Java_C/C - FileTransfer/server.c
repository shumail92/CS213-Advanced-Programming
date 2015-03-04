/*
  Advanced Programming - Lab 3

  Simple File Sharing
  Server.c

  by Shumail Mohy-ud-Din

*/

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>

int main(void)
{
    int listenfd = 0;
    int connfd = 0;
    
    //sockaddr_in is struct for storing the address and related socket parameters e.g port etc
    struct sockaddr_in serv_addr;
    char sendBuff[1025];
    int numrv;

    //initializing the socket
    listenfd = socket(AF_INET, SOCK_STREAM, 0);

    printf("Successfully created socket\n");

    // Setting the Memory for buffer
    memset(&serv_addr, '0', sizeof(serv_addr));
    memset(sendBuff, '0', sizeof(sendBuff));

    //initializing the serv_addr for socket parameters
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(55556);  //port

    //Binding the socket to provided address and port
    bind(listenfd, (struct sockaddr*)&serv_addr,sizeof(serv_addr));
    printf("binding successful\n");

    //start listening
    if(listen(listenfd, 10) == -1)
    {
        printf("Failed to listen\n");
        return -1;
    } else {
      printf("in else\n");
    }


    while(1)
    {
        // printf("in while\n");

        // get file name of file to send from user
        printf("Enter File Name: \n");
        char *s = (char*) malloc( 100 );
        scanf("%s",s);

        // accepting the connection from client
        connfd = accept(listenfd, (struct sockaddr*)NULL ,NULL);
        printf("--transfering file: %s \n", s);
 
        /* Open the file that we wish to transfer */

        FILE *fp = fopen(s,"rb");
        if(fp==NULL)
        {
            printf("File opern error");
            close(connfd);
            return 1;   
        }   

        // Now it really starts reading the file in chuncks and sends it to client
        while(1)
        {
            // Reading file chunk 256 byte
            unsigned char buff[256]={0};
            int nread = fread(buff,1,256,fp);
            printf("Bytes read %d \n", nread);        

            // if it read - send it
            if(nread > 0)
            {
                printf("Sending \n");
                write(connfd, buff, nread);
            }

            // check for EOF or File Read error (can be due to permissions)
            
            if (nread < 256)
            {
                if (feof(fp))
                    printf("Warning: EOF Reched\n");
                if (ferror(fp))
                    printf("Error: Reading file error\n");
                break;
            }

        }

        close(connfd);
        sleep(1);
    }


    return 0;
}