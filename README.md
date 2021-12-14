# Java_Sockets

Java Socket programming is used for communication between the applications running on different JRE. It can be connection-oriented or connection-less.
Socket and ServerSocket classes are used for connection-oriented socket programming.
The client in socket programming must know two pieces of information:
    1. IP Address of Server
    2. Port number.

This repo contains the code for creating 4 differents servers/client applications using socket programming:
  1. Bytes server/client: to send/recieve bytes between the server and the client.
  2. String server/client: to send/recieve strings between the server and the client.
  3. Object server/client: to send/recieve objects between the server and the client. I used a Planet class as a communication object.
  4. Multi-threaded server: created in a seperated class (MultiThreadedServer.java), this server use java threads to handle the connection of multiple clients (up to 4)
  
To create each server, the method executed in the main method of the Server class should be changed correspondingly.
Each server has its client in the Client class that connect/interract with it.
