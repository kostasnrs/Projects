/* server.c

   Sample code of 
   Assignment L1: Simple multi-threaded key-value server
   for the course MYY601 Operating Systems, University of Ioannina 

   (c) S. Anastasiadis, G. Kappes 2016

*/


#include <signal.h>
#include <sys/stat.h>
#include "utils.h"
#include "kissdb.h"
#include <sys/time.h>


#define MY_PORT                 6767
#define BUF_SIZE                1160
#define KEY_SIZE                 128
#define HASH_SIZE               1024
#define VALUE_SIZE              1024
#define MAX_PENDING_CONNECTIONS   10
#define QUEUE_SIZE 10
#define CONSUMERS 10


pthread_t consumerId[CONSUMERS];

// Definition of the operation type.
typedef enum operation {
  PUT,
  GET
} Operation; 

// Definition of the request.
typedef struct request {
  Operation operation;
  char key[KEY_SIZE];  
  char value[VALUE_SIZE];
} Request;

// Definition of the database.
KISSDB *db = NULL;
/*************apo edw*************/
struct queue {
    int connectionFd;
    struct timeval startTime;
};

int head, tail;
struct queue Queue[QUEUE_SIZE];

pthread_mutex_t locker = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t timeLocker = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t flagLocker = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t countLocker = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t dbLocker = PTHREAD_MUTEX_INITIALIZER;

pthread_cond_t nonEmptyQueue = PTHREAD_COND_INITIALIZER;
pthread_cond_t nonFullQueue = PTHREAD_COND_INITIALIZER;
pthread_cond_t turn = PTHREAD_COND_INITIALIZER;

struct timeval totalWaitingTime, totalServiceTime;
int completedRequests = 0;
int flag = 0;

int readerCount = 0, writerCount = 0, writing= 0;


void enqueue(struct queue request){
    if(tail  < QUEUE_SIZE - 1){
        tail = tail + 1;
    }
    else{
        tail = 0;
    }
    Queue[tail] = request;
    if(head == -1){
        head = 0;
    }
}

struct queue dequeue(){
    int tmp = head;

    if(head  < QUEUE_SIZE - 1){
        head = head + 1;
        if(head == tail + 1){
            head = -1;
            tail = -1;
        }
    }
    else{
        head = 0;
        if(tail == QUEUE_SIZE -1){
            head = -1;
            tail = -1;
        }
    }

    return Queue[tmp];
}

int isEmpty(){
    if(head == -1 && tail == -1){
        return 1;
    }
    return 0;
}

int isFull(){
    if((head == 0 && tail == QUEUE_SIZE - 1) || (tail == head - 1)){
        return 1;
    }
    return 0;
}

void signalFunction(){
    double second,usecond;
    int i;

    pthread_mutex_lock(&flagLocker);
    flag = 1;
    for(i=0;i<CONSUMERS;i++)
        pthread_cond_broadcast(&nonEmptyQueue);

    pthread_mutex_unlock(&flagLocker);

    for(i=0;i<CONSUMERS;i++){
        pthread_join(consumerId[i], NULL);
    }

    if(completedRequests == 0){
        printf("Are you serious???\n");
    }
    else{
        second = (totalWaitingTime.tv_sec + totalWaitingTime.tv_usec/1000000)/(double)completedRequests;
        usecond = (totalWaitingTime.tv_usec - 1000000 *(totalWaitingTime.tv_usec/1000000))/(double)completedRequests;
        printf("Average waiting time: %fsec %fusec\n", second, usecond);

        second = (totalServiceTime.tv_sec + totalServiceTime.tv_usec/1000000)/(double)completedRequests;
        usecond = (totalServiceTime.tv_usec - 1000000 *(totalServiceTime.tv_usec/1000000))/(double)completedRequests;
        printf("Average service time: %fsec %fusec\n", second, usecond);
        printf("completedRequests = %d\n", completedRequests);
    }

  // Destroy the database.
  // Close the database.
  KISSDB_close(db);

  // Free memory.
  if (db)
    free(db);
  db = NULL;

    exit(0);
}
/***************ws edw*****************************/


/**
 * @name parse_request - Parses a received message and generates a new request.
 * @param buffer: A pointer to the received message.
 *
 * @return Initialized request on Success. NULL on Error.
 */
Request *parse_request(char *buffer) {
  char *token = NULL;
  Request *req = NULL;
  
  // Check arguments.
  if (!buffer)
    return NULL;
  
  // Prepare the request.
  req = (Request *) malloc(sizeof(Request));
  memset(req->key, 0, KEY_SIZE);
  memset(req->value, 0, VALUE_SIZE);

  // Extract the operation type.
  token = strtok(buffer, ":");    
  if (!strcmp(token, "PUT")) {
    req->operation = PUT;
  } else if (!strcmp(token, "GET")) {
    req->operation = GET;
  } else {
    free(req);
    return NULL;
  }
  
  // Extract the key.
  token = strtok(NULL, ":");
  if (token) {
    strncpy(req->key, token, KEY_SIZE);
  } else {
    free(req);
    return NULL;
  }
  
  // Extract the value.
  token = strtok(NULL, ":");
  if (token) {
    strncpy(req->value, token, VALUE_SIZE);
  } else if (req->operation == PUT) {
    free(req);
    return NULL;
  }
  return req;
}

/*
 * @name process_request - Process a client request.
 * @param socket_fd: The accept descriptor.
 *
 * @return
 */
void process_request(const int socket_fd) {
  char response_str[BUF_SIZE], request_str[BUF_SIZE];
    int numbytes = 0;
    Request *request = NULL;

    // Clean buffers.
    memset(response_str, 0, BUF_SIZE);
    memset(request_str, 0, BUF_SIZE);
    
    // receive message.
    numbytes = read_str_from_socket(socket_fd, request_str, BUF_SIZE);
    
    // parse the request.
    if (numbytes) {
      request = parse_request(request_str);
      if (request) {
        switch (request->operation) {
          case GET:

            pthread_mutex_lock(&countLocker);

            while(writing > 0){
                pthread_cond_wait(&turn, &countLocker);
            }

            readerCount ++;
            pthread_mutex_unlock(&countLocker);

            // Read the given key from the database.
            if (KISSDB_get(db, request->key, request->value))
              sprintf(response_str, "GET ERROR\n");
            else
              sprintf(response_str, "GET OK: %s\n", request->value);

            pthread_mutex_lock(&countLocker);
            readerCount--;
            pthread_cond_broadcast(&turn);

            pthread_mutex_unlock(&countLocker);
            break;

          case PUT:

            pthread_mutex_lock(&countLocker);
            writerCount ++;
            while(readerCount > 0 || writing > 0){
                pthread_cond_wait(&turn, &countLocker);
            }
            writing++;
            pthread_mutex_unlock(&countLocker);

            // Write the given key/value pair to the database.
            if (KISSDB_put(db, request->key, request->value)) 
              sprintf(response_str, "PUT ERROR\n");
            else
              sprintf(response_str, "PUT OK\n");

            pthread_mutex_lock(&countLocker);
            writerCount --;
            writing--;
            pthread_cond_broadcast(&turn);
            pthread_mutex_unlock(&countLocker);

            break;
          default:
            // Unsupported operation.
            sprintf(response_str, "UNKOWN OPERATION\n");
        }
        // Reply to the client.
        write_str_to_socket(socket_fd, response_str, strlen(response_str));
        if (request)
          free(request);
        request = NULL;
        return;
      }
    }
    // Send an Error reply to the client.
    sprintf(response_str, "FORMAT ERROR\n");
    write_str_to_socket(socket_fd, response_str, strlen(response_str));
}
/*************************apo edw************************/

void *producer(void *args){
    int socket_fd,              // listen on this socket for new connections
      new_fd;                 // use this socket to service a new connection
  socklen_t clen;
  struct sockaddr_in server_addr,  // my address information
                     client_addr;  // connector's address information

    struct queue request;
    struct timeval startTime;

  // create socket
  if ((socket_fd = socket(AF_INET, SOCK_STREAM, 0)) == -1)
    ERROR("socket()");

  // Ignore the SIGPIPE signal in order to not crash when a
  // client closes the connection unexpectedly.
  signal(SIGPIPE, SIG_IGN);




  signal(SIGTSTP, signalFunction);




  // create socket adress of server (type, IP-adress and port number)
  bzero(&server_addr, sizeof(server_addr));
  server_addr.sin_family = AF_INET;
  server_addr.sin_addr.s_addr = htonl(INADDR_ANY);    // any local interface
  server_addr.sin_port = htons(MY_PORT);

  // bind socket to address
  if (bind(socket_fd, (struct sockaddr *) &server_addr, sizeof(server_addr)) == -1)
    ERROR("bind()");

  // start listening to socket for incomming connections
  listen(socket_fd, MAX_PENDING_CONNECTIONS);
  fprintf(stderr, "(Info) main: Listening for new connections on port %d ...\n", MY_PORT);
  clen = sizeof(client_addr);

  // Allocate memory for the database.
  if (!(db = (KISSDB *)malloc(sizeof(KISSDB)))) {
    fprintf(stderr, "(Error) main: Cannot allocate memory for the database.\n");
    return NULL;
  }

  // Open the database.
  if (KISSDB_open(db, "mydb.db", KISSDB_OPEN_MODE_RWCREAT, HASH_SIZE, KEY_SIZE, VALUE_SIZE)) {
    fprintf(stderr, "(Error) main: Cannot open the database.\n");
    return NULL;
  }

  // main loop: wait for new connection/requests
  while (1) {
    // wait for incomming connection
    if ((new_fd = accept(socket_fd, (struct sockaddr *)&client_addr, &clen)) == -1) {
      ERROR("accept()");
    }

    // got connection, serve request
    fprintf(stderr, "(Info) main: Got connection from '%s'\n", inet_ntoa(client_addr.sin_addr));

    request.connectionFd = new_fd;
    gettimeofday(&startTime, NULL);
    request.startTime = startTime;

    pthread_mutex_lock(&locker);
    while(isFull() == 1){
            pthread_cond_wait(&nonFullQueue, &locker);
    }
    enqueue(request);
    pthread_cond_signal(&nonEmptyQueue);
    pthread_mutex_unlock(&locker);
  }


}

void *consumer(void *args){
    struct queue request;
    struct timeval processStartTime, processEndTime;
    int i = (int)args;
    while(1){
        pthread_mutex_lock(&locker);
        while(isEmpty() == 1){
                pthread_mutex_lock(&flagLocker);
                if(flag == 1){
                    pthread_mutex_unlock(&locker);
                    pthread_mutex_unlock(&flagLocker);
                    printf("eimai o %d kai termatizw \n",i);
                    pthread_exit(NULL);
                }
                pthread_mutex_unlock(&flagLocker);
                printf("Einai adeia %d\n",i);

                printf("Kselokarw %d\n",i);
                pthread_cond_wait(&nonEmptyQueue, &locker);
                //pthread_mutex_lock(&locker);
                printf("Lokarw %d\n",i);

        }
        request = dequeue();

        pthread_cond_signal(&nonFullQueue);
        pthread_mutex_unlock(&locker);

        gettimeofday(&processStartTime, NULL);

        pthread_mutex_lock(&timeLocker);

        totalWaitingTime.tv_sec += processStartTime.tv_sec - request.startTime.tv_sec;
        totalWaitingTime.tv_usec += processStartTime.tv_usec - request.startTime.tv_usec;
        pthread_mutex_unlock(&timeLocker);

        process_request(request.connectionFd);
        close(request.connectionFd);

        gettimeofday(&processEndTime, NULL);

        pthread_mutex_lock(&timeLocker);
        completedRequests++;
        totalServiceTime.tv_sec += processEndTime.tv_sec - processStartTime.tv_sec;
        totalServiceTime.tv_usec += processEndTime.tv_usec - processStartTime.tv_usec;
        pthread_mutex_unlock(&timeLocker);


    }
}
/******************ws edw****************/
/*
 * @name main - The main routine.
 *
 * @return 0 on success, 1 on error.
 */
int main() {
    int i;

    totalServiceTime.tv_sec = 0;
    totalServiceTime.tv_usec = 0;
    totalWaitingTime.tv_sec = 0;
    totalWaitingTime.tv_usec = 0;

    head = -1;
    tail = -1;
    pthread_t producerId;

    pthread_create(&producerId, NULL, producer, NULL);
    for(i=0;i<CONSUMERS;i++){
        pthread_create(&consumerId[i], NULL, consumer, (void *)i);
    }

    pthread_join(producerId, NULL);


  return 0; 
}

