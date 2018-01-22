### Threaded-Client-with-Blocking-Server-MVC

A simple java system that uses TCP sockets for communication. Its consists of several parts:

* Multithreaded commandline client app
  - Parallel and Asynchronous Programming with Streams and CompletableFuture
  - Interactive commandline interface
  - Customized Communication protocol(Enums and Serializable classes for messages and commands)
  - Layered architecture(Clear separation of concerns between classes)

* Blocking Server that uses Java IO 
  - One main server class that handles everything in the original main thread
  - Only one client can connect to it at a time
  
* Threaded Blocking Server
  - More than one client can connect to it at a time,clients can send requests concurrently
  - Have a new thread that handles server operations.
  ![Threaded Blocking Server](https://github.com/crakama/Concurrency-Non-Blocking-in-Distributed-Systems/blob/master/images/ThreadedBlockingServer.png)

* Thread Pool Blocking Server

* Java NIO Blocking

* Java NIO Non Blocking

